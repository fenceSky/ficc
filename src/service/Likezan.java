package service;

import java.sql.Timestamp;

/**
 * Likezan entity. @author MyEclipse Persistence Tools
 */

public class Likezan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityId;
	private Integer userId;
	private Integer state;
	private Timestamp ctime;
	private String username;
	private String userheadimg;

	// Constructors

	/** default constructor */
	public Likezan() {
	}

	/** full constructor */
	public Likezan(Integer activityId, Integer userId, Integer state,
			Timestamp ctime, String username, String userheadimg) {
		this.activityId = activityId;
		this.userId = userId;
		this.state = state;
		this.ctime = ctime;
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

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
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