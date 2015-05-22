package com.yang.spinach.role.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.entity.Role
 */

public class Role implements Serializable {

	// columns START
	/**
	 * id
	 */
	private Long id;
	/**
	 * name
	 */
	private String name;
	/**
	 * role_code
	 */
	private String roleCode;
	/**
	 * description
	 */
	private String description;
	/**
	 * sort
	 */
	private Integer sort;
	/**
	 * disabled
	 */
	private String disabled;

	// columns END 数据库字段结束

	// get and set
	public void setId(Long id) {

		this.id = id;
	}

	public Long getId() {
		return this.id;
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

	public void setRoleCode(String roleCode) {

		if (StringUtils.isNotBlank(roleCode)) {
			roleCode = roleCode.trim();
		}
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return this.roleCode;
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

	public void setSort(Integer sort) {

		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setDisabled(String disabled) {

		if (StringUtils.isNotBlank(disabled)) {
			disabled = disabled.trim();
		}
		this.disabled = disabled;
	}

	public String getDisabled() {
		return this.disabled;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("name=").append(getName()).append(",")
				.append("roleCode=").append(getRoleCode()).append(",")
				.append("description=").append(getDescription()).append(",")
				.append("sort=").append(getSort()).append(",")
				.append("disabled=").append(getDisabled()).append(",")
				.toString();
	}

}
