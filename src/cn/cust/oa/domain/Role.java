package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 职位(角色)实体
 * 
 * @author LiGang
 * @date 2016-12-9 下午11:29:05
 * @version v1.0
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1606416867693707615L;
	
	private Long id; // 主键id
	private String name; // 职位名称
	private String description; // 职位描述
	private Set<User> users = new HashSet<User>(); // 下属用户
	private Set<Privilege> privileges = new HashSet<Privilege>(); //下属权限

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

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
