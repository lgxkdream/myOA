package cn.cust.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.oa.base.DaoSupportImpl;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements
		ForumService {

	/**
	 * 查询所有实体(按位置号升序)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Forum> findAll() {
		return getSession().createQuery(
				"FROM Forum f ORDER BY f.position").list();
	}
	
	/**
	 * 保存实体(自动提供最大位置号)
	 */
	@Override
	public void save(Forum forum) {
		super.save(forum);
		forum.setPosition(forum.getId().intValue());
	}
	
	/**
	 * 向上移动，最上面不能上移
	 */
	public void moveUp(Long id) {
		Forum forum = findById(id);
		Forum other = (Forum) getSession().createQuery(
				"FROM Forum f WHERE f.position < ? ORDER BY f.position DESC")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
		if(other != null){
			int position = forum.getPosition();
			forum.setPosition(other.getPosition());
			other.setPosition(position);
			getSession().update(forum);
			getSession().update(other);
		}
	}

	/**
	 * 向下移动，最下面不能下移
	 */
	public void moveDown(Long id) {
		Forum forum = findById(id);
		Forum other = (Forum) getSession().createQuery(
				"FROM Forum f WHERE f.position > ? ORDER BY f.position")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
		        .setMaxResults(1).uniqueResult();
		if(other != null){
			int position = forum.getPosition();
			forum.setPosition(other.getPosition());
			other.setPosition(position);
			getSession().update(forum);
			getSession().update(other);
		}
	}

}
