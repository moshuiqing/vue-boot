package com.home.lh.util.po;

public class LayuiPage {

	/**
	 * 每页的条数
	 */
	private Integer limit;

	/**
	 * 页数
	 */
	private Integer page;
	
	
	
	


	public LayuiPage() {
		super();
	}

	public LayuiPage(Integer limit, Integer page) {
		super();
		this.limit = limit;
		this.page = page;
	}

	public Integer getStart() {

		return (page - 1) * limit;
	}

	public Integer getEnd() {
		return limit;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
