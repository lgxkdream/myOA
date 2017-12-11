package cn.cust.oa.service;

import cn.cust.oa.base.DaoSupport;
import cn.cust.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {
	
	/**
	 * 向上移动，最上面不能上移
	 * 
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 向下移动，最下面不能下移
	 * 
	 * @param id
	 */
	void moveDown(Long id);

}
