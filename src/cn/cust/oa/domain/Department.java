package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门实体
 * 
 * @author LiGang
 * @date 2016-12-10 下午07:58:54
 * @version v1.0
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 5678209215913122775L;
	
	private Long id; //主键id
	private String name; //部门名称
	private String description; //部门描述
	private Set<User> users = new HashSet<User>(); //下属用户
	private Department parent; //所属部门
	private Set<Department> children = new HashSet<Department>(); //下属部门

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

}
