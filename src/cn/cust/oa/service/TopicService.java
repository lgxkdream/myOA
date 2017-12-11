package cn.cust.oa.service;

import java.util.List;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.PageBean;
import cn.cust.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	/**
	 * 按版块查询所有主题(按最后更新时间排序，并且置顶帖在上)
	 * 
	 * @param forum
	 * @return
	 */
	@Deprecated
	List<Topic> findByTorum(Forum forum);

	/**
	 * 根据当前页，每页显示条数及版块查询主题(按最后更新时间排序，并且置顶帖在上)
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param forum
	 * @return
	 */
	@Deprecated
	PageBean<Topic> findPageBeanByForum(int currentPage, int pageSize,
			Forum forum);

	/**
	 * 根据版块查询最新主题
	 * 
	 * @param oldForum
	 * @return
	 */
	Topic findLastByForum(Forum forum);

	/**
	 * 根据当前页，每页显示条数，版块及各种条件查询主题(运用QueryHelper）
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条数
	 * @param qHelper
	 *            HQL语句及参数列表
	 * @return
	 */
	// PageBean<Topic> findPageBean(int currentPage, int pageSize,
	// QueryHelper qHelper);

}
