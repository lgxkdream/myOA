package cn.cust.oa.service;

import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.PageBean;
import cn.cust.oa.domain.Reply;
import cn.cust.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 按主题查询所有回复(按回复时间排序)
	 * 
	 * @param topic
	 * @return
	 */
	@Deprecated
	List<Reply> findByTopic(Topic topic);

	/**
	 * 根据当前页，每页显示条数及主题查询回复(按回复时间排序)
	 * @param currentPage
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	@Deprecated
	PageBean<Reply> findPageBeanByTopic(int currentPage, int pageSize,
			Topic topic);

}
