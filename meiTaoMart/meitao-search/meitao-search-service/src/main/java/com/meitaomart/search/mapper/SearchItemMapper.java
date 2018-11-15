package com.meitaomart.search.mapper;

import java.util.List;

import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.pojo.MeitaoItem;


public interface SearchItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemByPrimaryKey(Long id);
}
