package com.meitaomart.service;

import java.util.List;

import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemDesc;
import com.meitaomart.pojo.MeitaoItemPrice;

public interface ItemService {
	List<ItemInfo> getItemList();
	List<ItemInfo> getRecentItemList(Integer days);
	List<ItemInfo> getItemListByCategoryId(Long categoryId);
	List<ItemInfo> getItemListBySpecialCategoryId(Integer specialCategoryId);
	List<ItemInfo> getItemListByLimitNumber(Integer limitNumber, String column);
	
	EasyUIDataGridResult getItemList(int page, int rows);
	MeitaoResult deleteItems(String ids);	
	ItemInfo getItemById(long itemId);
	MeitaoResult addItem(MeitaoItem item, MeitaoItemPrice itemPrices, String desc, String descImages);
	MeitaoResult updateItem(MeitaoItem item, MeitaoItemPrice itemPrices, String desc, String descImages);
	MeitaoItemDesc getItemDescById(long itemId);
	
	void clearItemInRedis(long itemId);
	
	MeitaoResult onShelf(String ids);
	MeitaoResult offShelf(String ids);
}
