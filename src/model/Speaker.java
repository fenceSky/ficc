package model;

/**
 * Speaker entity. @author MyEclipse Persistence Tools
 */

public class Speaker implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activityId;
	private String name;
	private String introduction;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Speaker() {
	}

	/** full constructor */
	public Speaker(Integer activityId, String name, String introduction,
			Integer userId) {
		this.activityId = activityId;
		this.name = name;
		this.introduction = introduction;
		this.userId = userId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}