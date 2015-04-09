package model;

import java.sql.Timestamp;

/**
 * Attachment entity. @author MyEclipse Persistence Tools
 */

public class Attachment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityId;
	private Integer userId;
	private String fileType;
	private String fileName;
	private String fileUrl;
	private Integer fileSize;
	private Timestamp ctime;
	private Integer state;
	
	public static Integer ATTACH_FILE = 2;
	public static Integer ATTACH_IMAGE = 1;

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** full constructor */
	public Attachment(Integer activityId, Integer userId, String fileType,
			String fileName, String fileUrl, Integer fileSize, Timestamp ctime,
			Integer state) {
		this.activityId = activityId;
		this.userId = userId;
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.fileSize = fileSize;
		this.ctime = ctime;
		this.state = state;
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

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
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

}