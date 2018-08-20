package cn.mtmall.service;

import cn.mtmall.common.pojo.EasyUIDataGridResult;
import cn.mtmall.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
}
