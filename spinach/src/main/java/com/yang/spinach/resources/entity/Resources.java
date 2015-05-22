package com.yang.spinach.resources.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.entity.Resources
 */

public class Resources implements Serializable {

	// columns START
	/**
	 * id
	 */
	private Long id;
	/**
	 * 父节点名称
	 */
	private Integer pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型:菜单or功能
	 */
	private Integer type;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * url
	 */
	private String url;
	/**
	 * 菜单编码
	 */
	private String permission;
	/**
	 * icon
	 */
	private String icon;
	/**
	 * state
	 */
	private String state;
	/**
	 * description
	 */
	private String description;

	// columns END 数据库字段结束

	// get and set
	public void setId(Long id) {

		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setPid(Integer pid) {

		this.pid = pid;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setName(String name) {

		if (StringUtils.isNotBlank(name)) {
			name = name.trim();
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setSort(Integer sort) {

		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setUrl(String url) {

		if (StringUtils.isNotBlank(url)) {
			url = url.trim();
		}
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setPermission(String permission) {

		if (StringUtils.isNotBlank(permission)) {
			permission = permission.trim();
		}
		this.permission = permission;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setIcon(String icon) {

		if (StringUtils.isNotBlank(icon)) {
			icon = icon.trim();
		}
		this.icon = icon;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setState(String state) {

		if (StringUtils.isNotBlank(state)) {
			state = state.trim();
		}
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setDescription(String description) {

		if (StringUtils.isNotBlank(description)) {
			description = description.trim();
		}
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("pid=").append(getPid()).append(",").append("name=")
				.append(getName()).append(",").append("type=")
				.append(getType()).append(",").append("sort=")
				.append(getSort()).append(",").append("url=").append(getUrl())
				.append(",").append("permission=").append(getPermission())
				.append(",").append("icon=").append(getIcon()).append(",")
				.append("state=").append(getState()).append(",")
				.append("description=").append(getDescription()).append(",")
				.toString();
	}

}
