package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户实体
 * 
 * @author LiGang
 * @date 2016-12-11 下午12:11:48
 * @version v1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1520849781831283507L;
	
	private Long id; // 主键id
	private Department department; // 所属部门
	private Set<Role> roles = new HashSet<Role>(); // 职位
	private String loginName; // 用户名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String headimg; //头像
	private String description; // 说明

	/**
	 * 判断本用户是否有指定id的权限
	 * 
	 * @param privilegeId
	 * @return
	 */
	public boolean hasPrivilegeById(Long privilegeId) {
		// 超级管理员有所有权限
		if (isAdmin()) {
			return true;
		}
		// 普通用户要判断是否含有这个权限
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getId().equals(privilegeId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断本用户是否有指定Url的权限
	 * 
	 * @param privilegeUrl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasPrivilegeByUrl(String privilegeUrl) {
		// 超级管理员有所有权限
		if (isAdmin()) {
			return true;
		}

		int pos = privilegeUrl.indexOf("?");
		if (pos > -1) {
			privilegeUrl = privilegeUrl.substring(0, pos);
		}

		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");

		if (!allPrivilegeUrls.contains(privilegeUrl)) {
			// 如果这个权限不需要控制，则登陆用户可以使用
			return true;
		} else {
			// 普通用户要判断是否含有这个权限
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privilegeUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}

	}

	/**
	 * 判断本用户是否是超级管理员
	 * 
	 * @return
	 */
	private boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
