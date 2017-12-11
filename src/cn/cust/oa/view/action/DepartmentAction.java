package cn.cust.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Department;
import cn.cust.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 部门管理
 * 
 * @author LiGang
 * @date 2016-12-10 下午08:38:07
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private static final long serialVersionUID = 496870660960776264L;

	/**
	 * 列表功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		if(model.getParent() == null || model.getParent().getId() == null){
			departmentList = departmentService.findTopList();
		}else{
			departmentList = departmentService.findChildren(model.getParent().getId());
			Department parent = departmentService.findById(model.getParent().getId());
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//准备顶级部门数据
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Department parent = departmentService.findById(model.getParent().getId());
		model.setParent(parent);
		departmentService.save(model);
		return "toList";
	}

	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		//准备顶级部门数据
		//List<Department> departmentList = departmentService.findAll();
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		//准备回显数据
		Department department = departmentService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "saveUI";
	}

	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Department department = departmentService.findById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		Department parent = departmentService.findById(model.getParent().getId());
		department.setParent(parent);
		departmentService.update(department);
		return "toList";
	}

}
