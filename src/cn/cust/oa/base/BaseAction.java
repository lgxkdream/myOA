package cn.cust.oa.base;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;

import cn.cust.oa.domain.User;
import cn.cust.oa.service.ApproveInfoService;
import cn.cust.oa.service.DepartmentService;
import cn.cust.oa.service.DocumentTemplateService;
import cn.cust.oa.service.FormService;
import cn.cust.oa.service.ForumService;
import cn.cust.oa.service.PrivilegeService;
import cn.cust.oa.service.ProcessDefinitionService;
import cn.cust.oa.service.ReplyService;
import cn.cust.oa.service.RoleService;
import cn.cust.oa.service.TopicService;
import cn.cust.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Action公共内容抽取类
 * 
 * @author LiGang
 * @date 2016-12-12 上午10:35:09
 * @version v1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	private static final long serialVersionUID = 4771555418743186961L;

	// Service实例的声明
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected DocumentTemplateService documentTemplateService;
	@Resource
	protected FormService formService;
	@Resource
	protected ApproveInfoService approveInfoService;
	@Resource
	protected ProcessEngine processEngine;

	// ModelDriven的支持
	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// 分页所用的参数
	protected int currentPage = 1; // 当前页
	protected int pageSize = 10; // 每页显示条数

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前登录的用户
	 * 
	 * @return
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	// 修改密码及头像所用的参数
	protected String oldPassword; // 原密码
	protected File resource; // 头像文件
	protected String resourceFileName; // 头像名称

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public void setResourceFileName(String resourceFileName) {
		this.resourceFileName = resourceFileName;
	}
	
	protected InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	
}
