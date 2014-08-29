package com.cloud.valueobject.vo;

import cn.egame.common.model.PageData;

public class PageDataVO {
	private int total;
    private int rows_of_page;
    private Object content;
    private int current_page;
    private int page_count;
	
	public PageDataVO(PageData pageData){
		this.total = pageData.getTotal();
		this.rows_of_page = pageData.getRowsOfPage();
		this.content = pageData.getContent();
		this.current_page = pageData.getCurrentPage();
		this.page_count = pageData.getPageCount();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows_of_page() {
		return rows_of_page;
	}

	public void setRows_of_page(int rows_of_page) {
		this.rows_of_page = rows_of_page;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getPage_count() {
		return page_count;
	}

	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}
	
	
}
