package com.meitaomart.search.mapper;

import java.util.List;

import com.meitaomart.common.pojo.SearchItem;


public interface SearchItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
