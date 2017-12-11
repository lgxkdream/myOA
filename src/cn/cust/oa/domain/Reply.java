package cn.cust.oa.domain;

/**
 * 回复实体
 * 
 * @author LiGang
 * @date 2016-12-20 下午02:51:59
 * @version v1.0
 */
public class Reply extends Article {

	private Topic topic; //所属主题

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
