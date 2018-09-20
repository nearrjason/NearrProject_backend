package com.meitaomart.service;

import java.util.List;

import com.meitaomart.common.pojo.EasyUITreeNode;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.content.pojo.ItemCategoryList;
import com.meitaomart.pojo.MeitaoItemCategory;

public interface ItemCategoryService {
	List<EasyUITreeNode> getItemCategoryListEasyUI(long parentId);
	MeitaoResult addItemCategory(long parentId, String name);
	MeitaoResult updateItemCategory(long id, String name);
	MeitaoResult deleteItemCategory(long id);
	
	List<MeitaoItemCategory> getItemCategoryList(long parentId);
	List<ItemCategoryList> getItemCategoryListWithChildrenList(long parentId);
}
