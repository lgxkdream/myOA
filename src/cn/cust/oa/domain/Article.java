package cn.cust.oa.domain;

import java.util.Date;

/**
 * 文章实体
 * 
 * @author LiGang
 * @date 2016-12-20 下午02:51:54
 * @version v1.0
 */
public class Article {
 
	private Long id; //主键id
	private String title; //标题
	private String content; //内容
	private Date postTime; //发表时间
	private User author; //作者
	private String ipAddr; //ip地址

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
