package cn.cust.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Privilege;
import cn.cust.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;

/**
 * 岗位管理
 * 
 * @author LiGang
 * @date 2016-12-10 上午12:48:15
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 5714443026734570684L;

	private Long[] privilegeIds;

	/**
	 * 列表功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// Role role = new Role();
		// role.setName(model.getName());
		// role.setDescription(model.getDescription());
		roleService.save(model);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Role role = roleService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		// this.name = role.getName(); 与上面等结果，不过麻烦
		// this.description = role.getDescription();
		return "saveUI";
	}

	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Role role = roleService.findById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}

	/**
	 * 设置权限页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilegeUI() throws Exception {
		// 准备所有权限数据
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		// 准备职位（角色）回显的权限数据
		Role role = roleService.findById(model.getId());
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId();
			}
		}
		ActionContext.getContext().getValueStack().push(role);
		return "setPrivilegeUI";
	}

	/**
	 * 设置权限功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPrivilege() throws Exception {
		Role role = roleService.findById(model.getId());
		List<Privilege> privileges = privilegeService.findByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privileges));
		roleService.update(role);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
