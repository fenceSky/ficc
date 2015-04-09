package model;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityId;
	private Integer userId;
	private String title;
	private String content;
	private Timestamp ctime;
	private Integer state;
	private String username;
	private String userheadimg;
	
	public static Integer NORMAL_STATE = 1;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Integer activityId, Integer userId, String title,
			String content, Timestamp ctime, Integer state, String username,
			String userheadimg) {
		this.activityId = activityId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.ctime = ctime;
		this.state = state;
		this.username = username;
		this.userheadimg = userheadimg;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserheadimg() {
		return this.userheadimg;
	}

	public void setUserheadimg(String userheadimg) {
		this.userheadimg = userheadimg;
	}

}