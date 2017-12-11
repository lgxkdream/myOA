package cn.cust.oa.view.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.ApproveInfo;
import cn.cust.oa.domain.DocumentTemplate;
import cn.cust.oa.domain.Form;
import cn.cust.oa.domain.TaskView;
import cn.cust.oa.domain.User;
import cn.cust.oa.util.UploadUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 业务流程管理
 * 
 * @author LiGang
 * @date 2017-1-21 下午10:33:27
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class FlowAction extends BaseAction<Form> {

	private static final long serialVersionUID = -5962176571498058680L;

	private Long documentTemplateId;
	private String approval; // 是否同意
	private String comment; // 审批意见
	private String taskId;

	public Long getDocumentTemplateId() {
		return documentTemplateId;
	}

	public void setDocumentTemplateId(Long documentTemplateId) {
		this.documentTemplateId = documentTemplateId;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 申请模板列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String templateList() throws Exception {
		List<DocumentTemplate> documentTempList = documentTemplateService
				.findAll();
		ActionContext.getContext().put("documentTempList", documentTempList);
		return "templateList";
	}

	/**
	 * 申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submitUI() throws Exception {
		return "submitUI";
	}

	/**
	 * 申请功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		// 上传表单
		String url = UploadUtils.uploadFile(resource, resourceFileName,
				"/upload/document");

		// 往form表中插入数据
		User user = getCurrentUser();
		DocumentTemplate documentTemplate = documentTemplateService
				.findById(documentTemplateId);
		String title = documentTemplate.getName() + "_" + user.getName() + "_"
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Form form = new Form();
		form.setTitle(title);
		form.setApplicator(user.getName());
		form.setApplyTime(new Date());
		form.setDocumentTemplate(documentTemplate);
		form.setState(Form.ONGOING);
		form.setUrl(url);
		formService.save(form);

		// jbpm业务
		// 启动流程实例，设置流程变量
		Map<String, Form> variables = new HashMap<String, Form>();
		variables.put("form", form);
		ProcessInstance processInstance = processEngine.getExecutionService()
				.startProcessInstanceByKey(documentTemplate.getProcessKey(),
						variables);
		// 根据当前正在执行的流程实例获取正在执行的任务
		Task task = processEngine.getTaskService().createTaskQuery()
				.executionId(processInstance.getId()).uniqueResult();
		// 完成业务申请
		processEngine.getTaskService().completeTask(task.getId());

		return "toMyApplicationList";
	}

	/**
	 * 我的申请查询
	 * 
	 * @return
	 */
	public String myApplicationList() throws Exception {
		User user = getCurrentUser();
		List<Form> formList = formService.findByApplicator(user.getName());
		ActionContext.getContext().put("formList", formList);
		return "myApplicationList";
	}

	/**
	 * 待我审批
	 * 
	 * @return
	 */
	public String myTaskList() throws Exception {
		// 1.根据登陆人查询当前执行的所有任务
		List<Task> taskList = processEngine.getTaskService().createTaskQuery()
				.assignee(getCurrentUser().getName()).list();
		// 2.遍历所有任务，得到executionId
		// 3.通过executionId和"form"的值把流程变量form提取出来
		// 4.form和task共同组成taskView
		List<TaskView> taskViewList = new ArrayList<TaskView>();
		for (Task task : taskList) {
			TaskView taskView = new TaskView();
			Form form = (Form) processEngine.getExecutionService().getVariable(
					task.getExecutionId(), "form");
			taskView.setForm(form);
			taskView.setTask(task);
			taskViewList.add(taskView);
		}
		ActionContext.getContext().put("taskViewList", taskViewList);
		return "myTaskList";
	}
	
	/**
	 * 审批处理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approveUI() throws Exception {
		
		return "approveUI";
	}

	/**
	 * 审批处理功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String approve() throws Exception {
		Form form = formService.findById(model.getId());
		ApproveInfo approveInfo = new ApproveInfo();
		approveInfo.setApprover(getCurrentUser().getName());
		approveInfo.setApproveTime(new Date());
		approveInfo.setComment(comment);
		
		//此处有重大bug，并未取到approval的值，问题在前端页面
		System.out.println("======="+approval+"=======================");
		approveInfo.setApproval(approval);
		approveInfo.setForm(form);
		
		Task task = processEngine.getTaskService()
		.getTask(taskId);
		if(ApproveInfo.DISAGREE.equals(approval)){
			//如果页面点击的是不同意
			//      流程实例直接结束
			//      把相应的form表的状态变成"未通过"
			processEngine.getExecutionService()
			.endProcessInstance(task.getExecutionId(), "ended");
			approveInfo.getForm().setState(Form.UNPASS);
		}else{
			//完成任务
			processEngine.getTaskService()
			.completeTask(taskId);
			//如果页面点击的是同意
			//      完成任务
			//      判断该流程实例是否结束
			//      如果结束，把相应的form表的状态变成"已通过"
			ProcessInstance processInstance = processEngine.getExecutionService()
			.createProcessInstanceQuery()
			.processInstanceId(task.getExecutionId())
			.uniqueResult();
			if(processInstance == null) {
				approveInfo.getForm().setState(Form.PASS);
			}
		}
		approveInfoService.save(approveInfo);
		return "toMyTaskList";
	}
	
}
