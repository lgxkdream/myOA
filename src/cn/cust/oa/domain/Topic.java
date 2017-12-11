package cn.cust.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/***
 * 主题实体
 * 
 * @author LiGang
 * @date 2016-12-20 下午02:52:03
 * @version v1.0
 */
public class Topic extends Article {

	// 各种主题类型
	public static final int TYPE_NORMAL = 0; // 普通帖
	public static final int TYPE_BEST = 1; // 精华帖
	public static final int TYPE_TOP = 2; // 置顶帖

	private Forum forum; // 所属版块
	private Set<Reply> replies = new HashSet<Reply>(); // 下属回复
	private int type; // 类型
	private int replyCount; // 回复数量
	private Reply lastReply; // 最新回复
	private Date lastUpdateTime; // 最后更新时间

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
