package com.meitaomart.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
	private long recordCount;
	private int totalPages;
	private List<SearchItem> searchItemList;

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<SearchItem> getItemList() {
		return searchItemList;
	}

	public void setItemList(List<SearchItem> searchItemList) {
		this.searchItemList = searchItemList;
	}

}
