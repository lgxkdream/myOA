package cn.cust.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 版块实体
 * 
 * @author LiGang
 * @date 2016-12-19 下午08:25:54
 * @version v1.0
 */
public class Forum {

	private Long id; // 主键id
	private String name; // 版块名称
	private String description; // 版块说明
	private int position; // 位置号(上移与下移)
	private Set<Topic> topics = new HashSet<Topic>(); // 下属主题
	private int topicCount; // 主题数量
	private int articleCount; // 文章数量(主题+回复)
	private Topic lastTopic; // 最新主题

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

}
