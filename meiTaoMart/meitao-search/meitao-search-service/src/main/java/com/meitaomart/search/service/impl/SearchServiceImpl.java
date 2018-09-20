package com.meitaomart.search.service.impl;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meitaomart.common.pojo.SearchResult;
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
		// 开启高亮显示
		query.setHighlight(true);
		query.addHighlightField(searchField);
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 调用dao执行查询
		SearchResult searchResult = searchDao.search(query);
		// 计算总页数
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int) recordCount / rows;
		if (recordCount % rows != 0) {
			totalPage++;
			// 添加到返回结果
			searchResult.setTotalPages(totalPage);
		}
		// 返回结果
		return searchResult;
	}
}
