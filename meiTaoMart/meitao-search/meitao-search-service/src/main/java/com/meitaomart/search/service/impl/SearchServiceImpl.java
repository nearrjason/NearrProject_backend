package com.meitaomart.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.common.pojo.SearchResult;
import com.meitaomart.mapper.MeitaoItemMapper;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.search.dao.SearchDao;
import com.meitaomart.search.service.SearchService;

/**
 * 商品搜索Service
 * 
 * @author anluo
 *
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	@Autowired
	private MeitaoItemMapper itemMapper;

	@Value("${DEFAULT_SEARCH_FIELD}")
	private String DEFAULT_SEARCH_FIELD;

	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		return search(keyword, page, rows, DEFAULT_SEARCH_FIELD);
	}

	@Override
	public SearchResult search(String keyword, int page, int rows, String searchField) throws Exception {
		// 创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(keyword);
		// 设置分页条件
		if (page <= 0) {
			page = 1;
		}
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", searchField);
		query.addFilterQuery("item_status:1");
		// 开启高亮显示
		query.setHighlight(true);
		query.addHighlightField(searchField);
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 调用dao执行查询
		SearchResult searchResult = searchDao.search(query);
		// 计算总页数
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int) (recordCount / rows);
		if (recordCount % rows != 0) {
			totalPage++;
			// 添加到返回结果
		}
		searchResult.setTotalPages(totalPage);
		//filterByStatus(searchResult);
		// 返回结果
		return searchResult;
	}
	
	private void filterByStatus(SearchResult searchResult) {
		List<SearchItem> updatedList = new ArrayList<>();
		List<SearchItem> itemList = searchResult.getItemList();
		for (SearchItem searchItem : itemList) {
			Long itemId = searchItem.getId();
			if (itemId != null) {
				MeitaoItem meitaoItem = itemMapper.selectByPrimaryKey(itemId);
				if (meitaoItem != null && meitaoItem.getStatus() != null && meitaoItem.getStatus().equals((byte)1)) {
					updatedList.add(searchItem);
				}
			}
		}
		
		searchResult.setItemList(updatedList);
	}
}
