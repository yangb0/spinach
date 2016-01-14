package com.yang.spinach.bussiness.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yang.spinach.bussiness.system.entity.Account
 */
@Table(name = "t_account")
public class Account implements Serializable {

	/**
	 * id
	 */
	@Id
	private Long id;
	/**
	 * username
	 */
	@NotBlank(message = "用户名不能为空")
	private String username;
	/**
	 * 昵称
	 */
	@NotBlank(message = "昵称不能为空")
	private String nickName;
	/**
	 * password
	 */
	@JsonIgnore
	private String password;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * email
	 */
	@Email
	private String email;
	/**
	 * 用户类型 0:普通会员 1:管理员
	 */
	private Integer userType;
	/**
	 * login_time
	 */
	private Date loginTime;
	/**
	 * 是否可用(0:可用 1:不可用)
	 */
	private Integer disabled;
	@Transient
	private String keyWords;

	public void setId(Long id) {

		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setUsername(String username) {

		if (StringUtils.isNotBlank(username)) {
			username = username.trim();
		}
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setNickName(String nickName) {

		if (StringUtils.isNotBlank(nickName)) {
			nickName = nickName.trim();
		}
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setPassword(String password) {

		if (StringUtils.isNotBlank(password)) {
			password = password.trim();
		}
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setMobile(String mobile) {

		if (StringUtils.isNotBlank(mobile)) {
			mobile = mobile.trim();
		}
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {

		if (StringUtils.isNotBlank(email)) {
			email = email.trim();
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

	/*
	 * public String getlogin_timeString() { return
	 * DateUtils.convertDate2String(FORMAT_LOGIN_TIME, getlogin_time()); }
	 * public void setlogin_timeString(String value) throws ParseException{
	 * setlogin_time(DateUtils.convertString2Date(FORMAT_LOGIN_TIME,value)); }
	 */

	public void setLoginTime(Date loginTime) {

		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setDisabled(Integer disabled) {

		this.disabled = disabled;
	}

	public Integer getDisabled() {
		return this.disabled;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("username=").append(getUsername()).append(",")
				.append("nickName=").append(getNickName()).append(",")
				.append("password=").append(getPassword()).append(",")
				.append("mobile=").append(getMobile()).append(",")
				.append("email=").append(getEmail()).append(",")
				.append("userType=").append(getUserType()).append(",")
				.append("loginTime=").append(getLoginTime()).append(",")
				.append("disabled=").append(getDisabled()).append(",")
				.toString();
	}
}
