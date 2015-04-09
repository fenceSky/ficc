package model;

import java.sql.Timestamp;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */

public class Tag implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityId;
	private Integer name;
	private Integer userId;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** full constructor */
	public Tag(Integer activityId, Integer name, Integer userId, Timestamp ctime) {
		this.activityId = activityId;
		this.name = name;
		this.userId = userId;
		this.ctime = ctime;
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

	public Integer getName() {
		return this.name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}