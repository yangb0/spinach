/**
 * @(#)Pagination.java
 *
 * Copyright 2008 jointown, Inc. All rights reserved.
 */

package com.yang.spinach.frame.pagePlugin;

import java.io.Serializable;

public class Pagination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_CURRENT_SKIP = 1;

	private int rows = DEFAULT_PAGE_SIZE; // 每页显示记录数
	private int total; // 总条目数
	// private int totalResult; //总记录数
	private int totalPage; // 总页数
	private int page = DEFAULT_CURRENT_SKIP; // 当前页

	// private int currentResult; //当前记录起始索引

	public Pagination() {

	}

	public Pagination(int rows) {
		this(rows, DEFAULT_CURRENT_SKIP);
	}

	public Pagination(int rows, int page) {
		this.rows = rows;
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		totalPage = total % rows == 0 ? total / rows : (total / rows + 1);

	}

	public int getPage() {
		if (page <= 1) {
			return 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
