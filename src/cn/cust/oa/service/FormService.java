package cn.cust.oa.service;

import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.Form;

public interface FormService extends DaoSupport<Form> {

	/**
	 * 根据申请人查询申请
	 * @param applicator
	 * @return
	 */
	List<Form> findByApplicator(String applicator);
}
