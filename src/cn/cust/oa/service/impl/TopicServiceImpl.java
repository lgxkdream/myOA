package cn.cust.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.PageBean;
import cn.cust.oa.domain.Topic;
import cn.cust.oa.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements
		TopicService {

	/**
	 * 按版块查询所有主题(按最后更新时间排序，并且置顶帖在上)
	 */
	@Deprecated
	public List<Topic> findByTorum(Forum forum) {
		return getSession().createQuery(
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")
				.setParameter(0, forum).list();
	}

	/**
	 * 保存主题
	 */
	@Override
	public void save(Topic topic) {
		// 设置属性并保存
		topic.setType(Topic.TYPE_NORMAL);
		topic.setLastReply(null);
		topic.setReplyCount(0);
		topic.setLastUpdateTime(topic.getPostTime());
		super.save(topic);
		// 维护相关的特殊属性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setArticleCount(forum.getArticleCount() + 1);
		forum.setLastTopic(topic);
		getSession().update(forum);
	}

	/**
	 * 根据当前页，每页显示条数及版块查询主题(按最后更新时间排序，并且置顶帖在上)
	 */
	@Deprecated
	public PageBean<Topic> findPageBeanByForum(int currentPage, int pageSize,
			Forum forum) {
		List<Topic> recordList = getSession().createQuery(
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")
				.setParameter(0, forum).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
		Long recordCount =(Long) getSession().createQuery(
				"SELECT COUNT(*) FROM Topic t WHERE t.forum = ?")
				.setParameter(0, forum).uniqueResult();
		return new PageBean<Topic>(currentPage, pageSize, recordCount.intValue(), recordList);
	}

	/**
	 * 根据版块查询最新主题
	 */
	public Topic findLastByForum(Forum forum) {
		return (Topic) getSession().createQuery(
				"FROM Topic WHERE postTime = (" +
				"SELECT MAX(postTime) FROM Topic t  WHERE t.forum = ? )")
				.setParameter(0, forum).uniqueResult();
	}

}
