package com.yang.spinach.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;



/**
 * 
 * @author <Auto generate>
 * @version  2015-04-15 13:44:42
 * @see com.yang.spinach.demo.entity.Account
 */

public class Account  implements Serializable{
	
	//columns START
	/**
	 * id
	 */
	private Integer id;
	/**
	 * linked_id
	 */
	private Integer linkedId;
	/**
	 * username
	 */
	private String username;
	/**
	 * password
	 */
	private String password;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * email
	 */
	private String email;
	/**
	 * 会员类型 0:普通会员 1:药店会员 2:药师 3:管理员
	 */
	private Integer userType;
	/**
	 * 角色关联
	 */
	private String roleIds;
	/**
	 * login_time
	 */
	private Date loginTime;
	/**
	 * 会员是否可用(true:可用 false:不可用)
	 */
	private String usable;
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Integer id) {
	    
		this.id = id;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	public void setLinkedId(Integer linkedId) {
	    
		this.linkedId = linkedId;
	}
	
	
	public Integer getLinkedId() {
		return this.linkedId;
	}
	public void setUsername(String username) {
	    
		    if(StringUtils.isNotBlank(username)){
			 username=username.trim();
			}
		this.username = username;
	}
	
	
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String password) {
	    
		    if(StringUtils.isNotBlank(password)){
			 password=password.trim();
			}
		this.password = password;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	public void setMobile(String mobile) {
	    
		    if(StringUtils.isNotBlank(mobile)){
			 mobile=mobile.trim();
			}
		this.mobile = mobile;
	}
	
	
	public String getMobile() {
		return this.mobile;
	}
	public void setEmail(String email) {
	    
		    if(StringUtils.isNotBlank(email)){
			 email=email.trim();
			}
		this.email = email;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	public void setUserType(Integer userType) {
	    
		this.userType = userType;
	}
	
	
	public Integer getUserType() {
		return this.userType;
	}
	public void setRoleIds(String roleIds) {
	    
		    if(StringUtils.isNotBlank(roleIds)){
			 roleIds=roleIds.trim();
			}
		this.roleIds = roleIds;
	}
	
	
	public String getRoleIds() {
		return this.roleIds;
	}
		/*
	public String getlogin_timeString() {
		return DateUtils.convertDate2String(FORMAT_LOGIN_TIME, getlogin_time());
	}
	public void setlogin_timeString(String value) throws ParseException{
		setlogin_time(DateUtils.convertString2Date(FORMAT_LOGIN_TIME,value));
	}*/
	
	public void setLoginTime(Date loginTime) {
	    
		this.loginTime = loginTime;
	}
	
	
	public Date getLoginTime() {
		return this.loginTime;
	}
	public void setUsable(String usable) {
	    
		    if(StringUtils.isNotBlank(usable)){
			 usable=usable.trim();
			}
		this.usable = usable;
	}
	
	
	public String getUsable() {
		return this.usable;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("linkedId=").append(getLinkedId()).append(",")
			.append("username=").append(getUsername()).append(",")
			.append("password=").append(getPassword()).append(",")
			.append("mobile=").append(getMobile()).append(",")
			.append("email=").append(getEmail()).append(",")
			.append("userType=").append(getUserType()).append(",")
			.append("roleIds=").append(getRoleIds()).append(",")
			.append("loginTime=").append(getLoginTime()).append(",")
			.append("usable=").append(getUsable()).append(",")
			.toString();
	}
	
	
}

	
