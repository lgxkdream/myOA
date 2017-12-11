package cn.cust.oa.view.action;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.service.ProcessDefinitionService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 流程定义
 * 
 * @author LiGang
 * @date 2017-1-21 上午11:14:40
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {

	private static final long serialVersionUID = -4536480690935648150L;

    @Resource
    protected ProcessDefinitionService processDefinitionService;
    
    private File resource;
    private String key;
    private InputStream inputStream;
    private String deploymentId;

	public void setResource(File resource) {
		this.resource = resource;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	/**
	 * 审批流程列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Collection<ProcessDefinition> processDefList = processDefinitionService
				.findLastVersion();
		ActionContext.getContext().put("processDefList", processDefList);
		return "list";
	}

	/**
	 * 部署流程定义文档
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deployUI() throws Exception {
		return "deployUI";
	}
	
	/**
	 * 部署流程定义
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deploy() throws Exception {
		processDefinitionService.deploy(resource);
		return "toList";
	}
	
	/**
	 * 删除流程定义
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		processDefinitionService.delete(key);
		return "toList";
	}
	
	/**
	 * 查看流程图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		inputStream = processDefinitionService.show(deploymentId);
		return SUCCESS;
	}

}
