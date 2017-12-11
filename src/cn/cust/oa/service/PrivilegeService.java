package cn.cust.oa.service;

import java.util.Collection;
import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 查询顶级权限列表（左侧菜单显示）
	 * 
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * 查询所有权限对应的Url(不重复)
	 * 
	 * @return
	 */
	Collection<String> findAllPrivilegeUrls();

}
