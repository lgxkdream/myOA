package cn.cust.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.oa.base.BaseAction;
import cn.cust.oa.domain.Reply;
import cn.cust.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

/**
 * 回复管理
 * 
 * @author LiGang
 * @date 2016-12-20 下午09:09:47
 * @version v1.0
 */
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {

	private static final long serialVersionUID = -9038185315130751176L;

	/**
	 * 添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		Topic topic = topicService.findById(model.getTopic().getId());
		model.getTopic().setForum(topic.getForum());
		model.getTopic().setTitle(topic.getTitle());
		ActionContext.getContext().put("topic", topic);
		return "saveUI";
	}

	/**
	 * 添加功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//表单参数，已经封装了
		//model.setTitle(title);
		//model.setContent(content);
		model.setTopic(topicService.findById(model.getTopic().getId()));
		//当前直接获取的信息
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		replyService.save(model);
		return "toTopicShow"; //转到新回复所在主题的显示页面
	}
	
	/**
	 * 删除功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		replyService.delete(model.getId());
		return "toTopicShow";
	}
	
	/**
	 * 修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception {
		Reply reply = replyService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(reply);
		return "saveUI";
	}
	
	/**
	 * 修改功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		Reply reply = replyService.findById(model.getId());
		reply.setTitle(model.getTitle());
		reply.setContent(model.getContent());
		reply.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		replyService.update(reply);
		return "toTopicShow";
	}
	
}
