package model;

import java.sql.Timestamp;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String content;
	private Integer type;
	
	public static final Integer TYPE_NEW_ACTIVITY = 1;
	public static final Integer TYPE_DELETE_ACTIVITY = 2;
	public static final Integer TYPE_SIGNUP_ACTIVITY = 3;
	public static final Integer TYPE_CANCEL_SIGNUP_ACTIVITY = 4;
	public static final Integer TYPE_LIKEZAN_ACTIVITY = 5;
	public static final Integer TYPE_CANCEL_LIKEZAN_ACTIVITY = 6;
	public static final Integer TYPE_COMMENT_ACTIVITY = 7;
	public static final Integer TYPE_CANCEL_COMMENT_ACTIVITY = 8;
	
	
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(Integer userId, String content, Integer type, Timestamp ctime) {
		this.userId = userId;
		this.content = content;
		this.type = type;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}