package cn.cust.oa.service;

import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	/**
	 * 查询顶级部门列表
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询所有子部门
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
