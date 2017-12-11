package cn.cust.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.Topic;
import cn.cust.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * 版块管理
 * 
 * @author LiGang
 * @date 2016-12-20 下午09:09:52
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	private static final long serialVersionUID = 8781862935314272916L;

	/**
	 * 0表示全部主题<br>
	 * 1表示全部精华贴
	 */
	private int viewType = 0;

	/**
	 * 0表示默认排序（按最后更新时间排序，但所有置顶帖都在前面）<br>
	 * 1表示按最后更新时间排序<br>
	 * 2表示按主题发表时间排序<br>
	 * 3表示按回复数量排序
	 */
	private int orderBy = 0;

	/**
	 * true表示升序<br>
	 * false表示降序
	 */
	private boolean reverse = false;

	/**
	 * 版块列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/**
	 * 显示单个版块(主题列表)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		Forum forum = forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		// 准备主题列表
		// List<Topic> topicList = topicService.findByTorum(forum);
		// ActionContext.getContext().put("topicList", topicList);

		// 准备分页信息 v1.0
		// PageBean<Topic> pageBean = topicService.findPageBeanByForum(
		// currentPage, pageSize, forum);

		// 准备分页信息 v2.0
		// String hql =
		// "FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(forum);

		// 准备分页信息 v3.0
		// String hql = "FROM Topic t WHERE t.forum = ?";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(forum);
		// if (viewType == 1) {
		// hql += " AND t.type = ? ";
		// parameters.add(Topic.TYPE_BEST);
		// }
		// if (orderBy == 1) {
		// hql += " ORDER BY t.lastUpdateTime " + (reverse ? "ASC" : "DESC");
		// } else if (orderBy == 2) {
		// hql += " ORDER BY t.postTime " + (reverse ? "ASC" : "DESC");
		// } else if (orderBy == 3) {
		// hql += " ORDER BY t.replyCount " + (reverse ? "ASC" : "DESC");
		// } else {
		// hql +=
		// " ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC";
		// }
		// PageBean<Topic> pageBean = topicService.findPageBean(currentPage,
		// pageSize, hql, parameters);

		// 准备分页信息 v4.0
		new QueryHelper(Topic.class, "t")
		        .addCondition("t.forum=?", forum)
		        .addCondition(viewType == 1, "t.type=?",Topic.TYPE_BEST)
		        .addOrderProperty(orderBy == 1,"t.lastUpdateTime", reverse)
		        .addOrderProperty(orderBy == 2,"t.postTime", reverse)
		        .addOrderProperty(orderBy == 3,"t.replyCount", reverse)
		        .addOrderProperty(orderBy == 0,"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
				.addOrderProperty(orderBy == 0, "t.lastUpdateTime", false)
				.preparePageBean(topicService, currentPage, pageSize);

        //PageBean<Topic> pageBean = topicService.findPageBean(currentPage,
        //		pageSize, qHelper);
        //ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

}
