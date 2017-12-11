package cn.cust.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.PageBean;
import cn.cust.oa.domain.Reply;
import cn.cust.oa.domain.Topic;
import cn.cust.oa.service.ReplyService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements
		ReplyService {

	/**
	 * 按主题查询所有回复(按回复时间排序)
	 */
	@Deprecated
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(
				"FROM Reply r WHERE r.topic = ? ORDER BY r.postTime")
				.setParameter(0, topic).list();
	}

	/**
	 * 保存回复
	 */
	@Override
	public void save(Reply reply) {
		super.save(reply);
		// 维护相关的特殊属性
		Topic topic = reply.getTopic();
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		getSession().update(topic);
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() + 1);
		getSession().update(forum);
	}

	/**
	 * 根据当前页，每页显示条数及主题查询回复(按回复时间排序)
	 */
	@Deprecated
	public PageBean<Reply> findPageBeanByTopic(int currentPage, int pageSize,
			Topic topic) {
		List<Reply> recordList = getSession().createQuery(
				"FROM Reply r WHERE r.topic = ? ORDER BY r.postTime")
				.setParameter(0, topic).setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		Long recordCount = (Long) getSession().createQuery(
				"SELECT COUNT(*) FROM Reply r WHERE r.topic = ?")
				.setParameter(0, topic).uniqueResult();
		return new PageBean<Reply>(currentPage, pageSize, recordCount.intValue(),
				recordList);
	}

}
