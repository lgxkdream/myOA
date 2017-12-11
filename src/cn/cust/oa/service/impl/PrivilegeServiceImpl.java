package cn.cust.oa.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Privilege;
import cn.cust.oa.service.PrivilegeService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements
		PrivilegeService {

	/**
	 * 查询顶级权限列表（左侧菜单显示）
	 */
	public List<Privilege> findTopList() {
		return getSession().createQuery(
				"FROM Privilege p where p.parent IS NULL").list();
	}

	/**
	 * 查询所有权限对应的Url(不重复)
	 */
	public Collection<String> findAllPrivilegeUrls() {
		return getSession().createQuery(
				"SELECT DISTINCT p.url FROM Privilege p where p.url IS NOT NULL").list();
	}

}
