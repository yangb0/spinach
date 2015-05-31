package com.yang.spinach.dict.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.StringUtils;



/**
 * 
 * @author <Auto generate>
 * @version  2015-05-31 15:46:09
 * @see com.yang.spinach.entity.Dict
 */

public class Dict  implements Serializable{
	
	//columns START
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 显示内容
	 */
	private String label;
	/**
	 * 值
	 */
	private Integer value;
	/**
	 * 对应字段
	 */
	private String targetColumn;
	/**
	 * 描述
	 */
	private String descrption;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否启用:0启用,1不启用
	 */
	private Integer disabled;
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Integer id) {
	    
		this.id = id;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	public void setLabel(String label) {
	    
		    if(StringUtils.isNotBlank(label)){
			 label=label.trim();
			}
		this.label = label;
	}
	
	
	public String getLabel() {
		return this.label;
	}
	public void setValue(Integer value) {
	    
		this.value = value;
	}
	
	
	public Integer getValue() {
		return this.value;
	}
	public void setTargetColumn(String targetColumn) {
	    
		    if(StringUtils.isNotBlank(targetColumn)){
			 targetColumn=targetColumn.trim();
			}
		this.targetColumn = targetColumn;
	}
	
	
	public String getTargetColumn() {
		return this.targetColumn;
	}
	public void setDescrption(String descrption) {
	    
		    if(StringUtils.isNotBlank(descrption)){
			 descrption=descrption.trim();
			}
		this.descrption = descrption;
	}
	
	
	public String getDescrption() {
		return this.descrption;
	}
	public void setSort(Integer sort) {
	    
		this.sort = sort;
	}
	
	
	public Integer getSort() {
		return this.sort;
	}
	public void setRemark(String remark) {
	    
		    if(StringUtils.isNotBlank(remark)){
			 remark=remark.trim();
			}
		this.remark = remark;
	}
	
	
	public String getRemark() {
		return this.remark;
	}
	public void setDisabled(Integer disabled) {
	    
		this.disabled = disabled;
	}
	
	
	public Integer getDisabled() {
		return this.disabled;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("label=").append(getLabel()).append(",")
			.append("value=").append(getValue()).append(",")
			.append("targetColumn=").append(getTargetColumn()).append(",")
			.append("descrption=").append(getDescrption()).append(",")
			.append("sort=").append(getSort()).append(",")
			.append("remark=").append(getRemark()).append(",")
			.append("disabled=").append(getDisabled()).append(",")
			.toString();
	}
	
	
}

	
