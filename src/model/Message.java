package model;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer fromUserId;
	private Integer toUserId;
	private String title;
	private String content;
	private Timestamp ctime;
	private Integer state;
	
	public static int STATE_NO_READ = 1;
	public static int STATE_HAS_READ = 2;
	
	private String fromUsername;
	private String fromUserheadimg;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** full constructor */
	public Message(Integer fromUserId, Integer toUserId, String title,
			String content, Timestamp ctime, Integer state,
			String fromUsername, String fromUserheadimg) {
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.title = title;
		this.content = content;
		this.ctime = ctime;
		this.state = state;
		this.fromUsername = fromUsername;
		this.fromUserheadimg = fromUserheadimg;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
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

	public String getFromUsername() {
		return this.fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getFromUserheadimg() {
		return this.fromUserheadimg;
	}

	public void setFromUserheadimg(String fromUserheadimg) {
		this.fromUserheadimg = fromUserheadimg;
	}

}