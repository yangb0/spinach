package com.yang.spinach.bussiness.system.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yang.spinach.bussiness.system.entity.entity.Role
 */
@Table(name="t_role")
public class Role implements Serializable {

	// columns START
	/**
	 * id
	 */
	@Id
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
	private Integer disabled;

	// columns END 数据库字段结束
	@Transient
	private String checked;// 是否被选中

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

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
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

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
