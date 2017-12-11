package cn.cust.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Department;
import cn.cust.oa.domain.Role;
import cn.cust.oa.domain.User;
import cn.cust.oa.util.DepartmentUtils;
import cn.cust.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户管理
 * 
 * @author LiGang
 * @date 2016-12-12 下午12:28:03
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = -2648764023228634381L;

	private Long[] roleIds;

	/**
	 * 列表功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// List<User> userList = userService.findAll();
		// ActionContext.getContext().put("userList", userList);
		new QueryHelper(User.class, "u").preparePageBean(userService, currentPage, pageSize);
		return "list";
	}

	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 准备所属部门数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备职位数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 设置所属部门
		Department department = departmentService.findById(model
				.getDepartment().getId());
		model.setDepartment(department);
		// 设置初始密码12345
		String md5Digest = DigestUtils.md5Hex("12345"); // 对密码进行加密处理
		model.setPassword(md5Digest);
		// 设置关联岗位
		List<Role> roles = roleService.findByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		userService.save(model);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		// 准备所属部门数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备职位数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 准备用户回显数据
		User user = userService.findById(model.getId());
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		ActionContext.getContext().getValueStack().push(user);
		return "saveUI";
	}

	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		User user = userService.findById(model.getId());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// 设置所属部门
		Department department = departmentService.findById(model
				.getDepartment().getId());
		user.setDepartment(department);
		// 设置关联岗位
		List<Role> roles = roleService.findByIds(roleIds);
		user.setRoles(new HashSet<Role>(roles));
		userService.update(user);
		return "toList";
	}

	/**
	 * 初始化密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {
		User user = userService.findById(model.getId());
		String md5Digest = DigestUtils.md5Hex("12345"); // 对密码进行加密处理
		user.setPassword(md5Digest);
		userService.update(user);
		return "toList";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/**
	 * 登录功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		if (model.getLoginName().equals("") || model.getPassword().equals("")) {
			addFieldError("login", "请填写用户名与密码！");
			return "loginUI";
		}
		User user = userService.findByLoginNameAndPassword(
				model.getLoginName(), model.getPassword());
		if (user == null) {
			// 登录失败
			addFieldError("login", "对不起，您的用户名或密码错误！");
			return "loginUI";
		} else {
			// 登录成功
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}

	/**
	 * 注销功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		//ActionContext.getContext().getSession().remove("user");
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
