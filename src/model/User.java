package model;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String weixinOpenid;
	private String weixinUnionid;
	private String nickname;
	private Integer sex;
	private String province;
	private String city;
	private String country;
	private String weixinHeadimg;
	private Timestamp regTime;
	private Timestamp lastLoginTime;
	private String email;
	private String weixinId;
	private String realname;
	private String corp;
	private String cellphone;
	private String workphone;
	private String position;
	private String address;
	
	private Integer verified;
	public static int REALNAME_VERIFIED = 1;
	public static int NO_VERIFIED = 0;
	
	private Integer permission;
	
	public static int PERMISSION_NORMAL = 1;
	public static int PERMISSION_START_ACTIVIY = 2;
	
	
	private String qq;
	private String pemail;
	private String corpdetail;
	private String department;
	private String remark1;
	private String remark2;
	private String remark3;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String weixinOpenid, String weixinUnionid, String nickname,
			Integer sex, String province, String city, String country,
			String weixinHeadimg, Timestamp regTime, Timestamp lastLoginTime,
			String email, String weixinId, String realname, String corp,
			String cellphone, String workphone, String position,
			String address, Integer verified, Integer permission, String qq,
			String pemail, String corpdetail, String department,
			String remark1, String remark2, String remark3) {
		this.weixinOpenid = weixinOpenid;
		this.weixinUnionid = weixinUnionid;
		this.nickname = nickname;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.country = country;
		this.weixinHeadimg = weixinHeadimg;
		this.regTime = regTime;
		this.lastLoginTime = lastLoginTime;
		this.email = email;
		this.weixinId = weixinId;
		this.realname = realname;
		this.corp = corp;
		this.cellphone = cellphone;
		this.workphone = workphone;
		this.position = position;
		this.address = address;
		this.verified = verified;
		this.permission = permission;
		this.qq = qq;
		this.pemail = pemail;
		this.corpdetail = corpdetail;
		this.department = department;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeixinOpenid() {
		return this.weixinOpenid;
	}

	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}

	public String getWeixinUnionid() {
		return this.weixinUnionid;
	}

	public void setWeixinUnionid(String weixinUnionid) {
		this.weixinUnionid = weixinUnionid;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWeixinHeadimg() {
		return this.weixinHeadimg;
	}

	public void setWeixinHeadimg(String weixinHeadimg) {
		this.weixinHeadimg = weixinHeadimg;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinId() {
		return this.weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCorp() {
		return this.corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getVerified() {
		return this.verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

	public Integer getPermission() {
		return this.permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPemail() {
		return this.pemail;
	}

	public void setPemail(String pemail) {
		this.pemail = pemail;
	}

	public String getCorpdetail() {
		return this.corpdetail;
	}

	public void setCorpdetail(String corpdetail) {
		this.corpdetail = corpdetail;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

}