package cn.cust.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Department;
import cn.cust.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	public List<Department> findTopList() {
		return getSession().createQuery(
				"FROM Department d where d.parent IS NULL").list();
	}

	public List<Department> findChildren(Long parentId) {
		return getSession().createQuery(
				"FROM Department d where d.parent.id = ?").setParameter(0,
				parentId).list();
	}

}
