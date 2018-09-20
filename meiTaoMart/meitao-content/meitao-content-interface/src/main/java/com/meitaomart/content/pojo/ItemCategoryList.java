package com.meitaomart.content.pojo;

import java.io.Serializable;
import java.util.List;

import com.meitaomart.pojo.MeitaoItemCategory;

public class ItemCategoryList extends MeitaoItemCategory implements Serializable{
	private List<ItemCategoryList> childrenList;

	public List<ItemCategoryList> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<ItemCategoryList> childrenList) {
		this.childrenList = childrenList;
	}
} 
