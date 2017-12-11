package cn.cust.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 * 
 * @author LiGang
 * @date 2016-12-14 下午06:53:59
 * @version v1.0
 */
public class Privilege implements Serializable {

	private static final long serialVersionUID = 6192727418012246228L;
	
	private Long id; // 主键id
	private String name; // 权限名称
	private String url; // 请求地址
	private Set<Role> roles = new HashSet<Role>(); // 所属角色（岗位）
	private Privilege parent; // 上属权限
	private Set<Privilege> children = new HashSet<Privilege>(); // 下属权限
	
	public Privilege() {
	}

	public Privilege(String name, String url, Privilege parent) {
		this.name = name;
		this.url = url;
		this.parent = parent;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
