package cn.cust.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Form;
import cn.cust.oa.service.FormService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class FormServiceImpl extends DaoSupportImpl<Form> implements
		FormService {

	/**
	 * 根据申请人查询申请
	 */
	public List<Form> findByApplicator(String applicator) {
		return getSession().createQuery(
				"FROM Form f WHERE f.applicator = ? ORDER BY f.applyTime DESC")
				.setParameter(0, applicator).list();
	}

}
