package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Activity entity. @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Timestamp startTime;
	private Integer lastTime;
	private String province;
	private String city;
	private String shortAddr;
	private String detailAddr;
	private Integer publisherId;
	private Integer totalPeople;
	private Integer fee;
	private String content;
	private Integer state;
	
	public static Integer NO_VERIFIED = 1;
	public static Integer PASS_VERIFIED = 2;
	public static Integer CLOSE_SIGNUP = 3;
	//public static Integer CLOSE = 4;
	
	private Timestamp publishTime;
	
	
	//extra data
	int likezan_count;
	User publisher;
	ArrayList<Comment> comments;
	ArrayList<Attachment> attachments;
	ArrayList<Speaker> speakers;
	ArrayList<Signup> signup_users;
	

	// Constructors

	/** default constructor */
	public Activity() {
		initExtraData();
	}

	/** full constructor */
	public Activity(String title, Timestamp startTime, Integer lastTime,
			String province, String city, String shortAddr, String detailAddr,
			Integer publisherId, Integer totalPeople, Integer fee,
			String content, Integer state, Timestamp publishTime) {
		this.title = title;
		this.startTime = startTime;
		this.lastTime = lastTime;
		this.province = province;
		this.city = city;
		this.shortAddr = shortAddr;
		this.detailAddr = detailAddr;
		this.publisherId = publisherId;
		this.totalPeople = totalPeople;
		this.fee = fee;
		this.content = content;
		this.state = state;
		this.publishTime = publishTime;
		initExtraData();
	}
	
	private void initExtraData(){
		likezan_count = 0;
		publisher = null;
		comments = new ArrayList<Comment>();
		attachments = new ArrayList<Attachment>();
		speakers = new ArrayList<Speaker>();
		signup_users = new ArrayList<Signup>();
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Integer getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Integer lastTime) {
		this.lastTime = lastTime;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getShortAddr() {
		return this.shortAddr;
	}

	public void setShortAddr(String shortAddr) {
		this.shortAddr = shortAddr;
	}

	public String getDetailAddr() {
		return this.detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public Integer getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getTotalPeople() {
		return this.totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}

	public Integer getFee() {
		return this.fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public int getLikezan_count() {
		return likezan_count;
	}

	public void setLikezan_count(int likezan_count) {
		this.likezan_count = likezan_count;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}

	public ArrayList<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(ArrayList<Speaker> speakers) {
		this.speakers = speakers;
	}

	public ArrayList<Signup> getSignup_users() {
		return signup_users;
	}

	public void setSignup_users(ArrayList<Signup> signup_users) {
		this.signup_users = signup_users;
	}
	
	public String getImageUrl(){
		for(int i = 0 ; i < attachments.size(); i++){
			if(attachments.get(i).getState().equals(Attachment.ATTACH_IMAGE)){
				return attachments.get(i).getFileUrl();
			}
		}
		return "images/timthumb.jpeg";
	}
	
	public String getFile(){
		for(int i = 0 ; i < attachments.size(); i++){
			if(attachments.get(i).getState().equals(Attachment.ATTACH_FILE)){
				return attachments.get(i).getFileUrl();
			}
		}
		return null;
	}
	
	public String getIntro(){
		int introLength = 50;
		if(content.length() < introLength){
			return content;
		}else{
			return content.substring(0, introLength) + "...";
		}
	}

}