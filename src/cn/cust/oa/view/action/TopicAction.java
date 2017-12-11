package cn.cust.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Forum;
import cn.cust.oa.domain.Reply;
import cn.cust.oa.domain.Topic;
import cn.cust.oa.util.QueryHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * 主题管理
 * 
 * @author LiGang
 * @date 2016-12-20 下午09:09:56
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	private static final long serialVersionUID = -6894057121413196725L;

	/**
	 * 显示单个主题(主帖+回帖列表)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// 准备主帖topic
		Topic topic = topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		// //准备回复列表replyList
		// List<Reply> replyList = replyService.findByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);
		// PageBean<Reply> pageBean = replyService.findPageBeanByTopic(
		// currentPage, pageSize, topic);
		// 准备分页信息 v1.0
		// String hql = "FROM Reply r WHERE r.topic = ? ORDER BY r.postTime";
		// List<Object> parameters = new ArrayList<Object>();
		// parameters.add(topic);
		// PageBean<Reply> pageBean = replyService.findPageBean(currentPage,
		// pageSize, hql, parameters);

		// 准备分页信息 v2.0
		new QueryHelper(Reply.class, "r").addCondition("r.topic=?", topic)
				.addOrderProperty("r.postTime", true).preparePageBean(
						replyService, currentPage, pageSize);
		// PageBean<Reply> pageBean = replyService.findPageBean(currentPage,
		// pageSize, qHelper);
		// ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		// 准备数据
		Forum forum = forumService.findById(model.getForum().getId());
		model.getForum().setName(forum.getName());
		// Forum forum = forumService.findById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 表单参数，已经封装了
		// model.setTitle(title);
		// model.setContent(content);
		Forum forum = forumService.findById(model.getForum().getId());
		model.setForum(forum);
		// 当前直接获取的信息
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		// 应放到业务方法中 --> TopicServiceImpl
		// model.setType(Topic.TYPE_NORMAL);
		// model.setLastReply(lastReply);
		// model.setReplyCount(replyCount);
		// model.setLastUpdateTime(lastUpdateTime);
		// 保存
		topicService.save(model);
		return "toShow"; // 转到新主题的显示页面
	}
	
	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		topicService.delete(model.getId());
		return "toForumShow";
	}
	
	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Topic topic = topicService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(topic);
		return "saveUI";
	}
	
	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Topic topic = topicService.findById(model.getId());
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topic.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		topic.setPostTime(new Date());
		topicService.update(topic);
		return "toShow";
	}
	
	

	/**
	 * 移动主题页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String moveUI() throws Exception {
		// 准备数据
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		Topic topic = topicService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(topic);
		return "moveUI";
	}

	/**
	 * 移动主题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String move() throws Exception {
		Topic topic = topicService.findById(model.getId());
		Forum oldForum = topic.getForum();
		Forum newForum = forumService.findById(model.getForum().getId());
		// 更新主题
		topic.setForum(newForum);
		topicService.update(topic);
		// 维护原版块特殊属性
		Topic oldLastTopic = topicService.findLastByForum(oldForum);
		oldForum.setTopicCount(oldForum.getTopicCount() - 1);
		oldForum.setArticleCount(oldForum.getArticleCount() - 1 - topic.getReplyCount());
		if(oldForum.getLastTopic() != oldLastTopic){
			oldForum.setLastTopic(oldLastTopic);
		}
		forumService.update(oldForum);
		// 维护新版块特殊属性
		Topic newLastTopic = topicService.findLastByForum(newForum);
		newForum.setTopicCount(newForum.getTopicCount() + 1);
		newForum.setArticleCount(newForum.getArticleCount() + 1 + topic.getReplyCount());
		if(newForum.getLastTopic() != newLastTopic){
			newForum.setLastTopic(newLastTopic);
		}
		forumService.update(newForum);
		return "toShow";
	}

	/**
	 * 修改主题类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeType() throws Exception {
		Topic topic = topicService.findById(model.getId());
		topic.setType(model.getType());
		topicService.update(topic);
		return "toShow";
	}

}
