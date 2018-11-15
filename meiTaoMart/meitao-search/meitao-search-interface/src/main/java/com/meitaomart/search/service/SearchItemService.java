package com.meitaomart.search.service;

import java.util.List;

import com.meitaomart.common.utils.MeitaoResult;

public interface SearchItemService {
	MeitaoResult importAllItems();
	MeitaoResult updatePartialItems(List<Long> itemIds);
	MeitaoResult updatePartialItemsByStringIds(String itemIds);
}
