package com.meitaomart.search.service;

import com.meitaomart.common.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String keyword, int page, int rows) throws Exception;
	SearchResult search(String keyword, int page, int rows, String searchField) throws Exception;
}
