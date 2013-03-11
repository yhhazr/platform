package com.sz7road.web.model.pagination;

public class PageInfo {
	
	private int startRow = 1;

	private int pageSize = 99999;
	
	public PageInfo(int startRow, int pageSize) {
		super();
		this.startRow = startRow;
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
