package com.meitaomart.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.common.pojo.SearchResult;

import java.util.*;

/**
 * @author anluo
 *
 */

@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * @param query
	 * @return
	 */
	public SearchResult search(SolrQuery query) throws Exception {
		
		QueryResponse queryResponse = solrServer.query(query);
		
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		
		long numFound = solrDocumentList.getNumFound();
		SearchResult result = new SearchResult();
		result.setRecordCount(numFound);
		
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<SearchItem> searchItemList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			SearchItem searchItem = new SearchItem();
			searchItem.setId(Long.valueOf((String)solrDocument.get("id")));
			searchItem.setCaption((String)solrDocument.get("item_caption"));
			searchItem.setSellPoint((String)solrDocument.get("item_sell_point"));
			searchItem.setNetWeight((Integer)solrDocument.get("item_net_weight"));
			searchItem.setStockNumber((Integer)solrDocument.get("item_stock_number"));
			searchItem.setImages((String)solrDocument.get("item_images"));
			searchItem.setSalePrice((Long)solrDocument.get("item_sale_price"));
			searchItem.setCategoryId((Long)solrDocument.get("item_category_id"));
			searchItem.setDiscount(((Integer)solrDocument.get("item_discount")).byteValue());
			
			List<String> list = highlighting.get((solrDocument).get("id")).get("item_name");
			
			String itemName = "";
			if (list != null && list.size() > 0) {
				itemName = list.get(0);
			} else {
				itemName = (String)solrDocument.get("item_name");
			}
			
			searchItem.setName(itemName);
			
			searchItemList.add(searchItem);
		}
		
		result.setItemList(searchItemList);
		return result;
	}
}
