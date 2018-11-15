package com.meitaomart.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.mapper.MeitaoItemCategoryMapper;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemCategory;
import com.meitaomart.search.mapper.SearchItemMapper;
import com.meitaomart.search.service.SearchItemService;

/**
 * 索引库维护Service
 * 
 * @author anluo
 *
 */

@Service
public class SearchItemServiceImpl implements SearchItemService {
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private MeitaoItemCategoryMapper itemCategoryMapper;

	@Override
	public MeitaoResult importAllItems() {
		// 查询商品列表
		List<SearchItem> searchItemList = searchItemMapper.getItemList();
		return updateItemList(searchItemList);
	}

	@Override
	public MeitaoResult updatePartialItems(List<Long> itemIds) {
		List<SearchItem> searchItemList = new ArrayList<>();
		for (Long itemId : itemIds) {
			SearchItem item = searchItemMapper.getItemByPrimaryKey(itemId);
			searchItemList.add(item);
		}
		return updateItemList(searchItemList);
	}
	
	@Override
	public MeitaoResult updatePartialItemsByStringIds(String itemIds) {
		String[] arrayIds = itemIds.split(",");
		List<Long> listIds = new ArrayList<>();
		
		for (String stringId : arrayIds) {
			Long id = Long.parseLong(stringId.trim());
			listIds.add(id);
		}
		
		return updatePartialItems(listIds);
	}
	
	private MeitaoResult updateItemList(List<SearchItem> searchItemList) {
		try {
			
			// 遍历商品列表
			for (SearchItem searchItem : searchItemList) {
				// 创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				// 向文档对象中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_name", searchItem.getName());
				document.addField("item_sell_point", searchItem.getSellPoint());
				document.addField("item_caption", searchItem.getCaption());
				document.addField("item_net_weight", searchItem.getNetWeight());
				document.addField("item_stock_number", searchItem.getStockNumber());
				document.addField("item_images", searchItem.getImages());
				document.addField("item_sale_price", searchItem.getSalePrice());
				document.addField("item_discount", searchItem.getDiscount());
				document.addField("item_category_name", searchItem.getCategoryName());
				document.addField("item_status", searchItem.getStatus());

				Long categoryId = searchItem.getCategoryId();
				if (categoryId != null && !Long.valueOf(-1L).equals(categoryId)) {
					document.addField("item_category_id", categoryId);
					MeitaoItemCategory itemCategory = itemCategoryMapper.selectByPrimaryKey(categoryId);
					if (itemCategory != null) {
						Long parentCategoryId = itemCategory.getParentId();
						MeitaoItemCategory parentItemCategory = itemCategoryMapper.selectByPrimaryKey(parentCategoryId);
						if (parentItemCategory != null) {
							String parentCategoryName = parentItemCategory.getName();
							document.addField("item_parent_category_name", parentCategoryName);
							document.addField("item_parent_category_id", parentCategoryId);
						}
					}
				}

				// 把文档对象写入索引库
				solrServer.add(document);
			}

			// 提交
			solrServer.commit();
			return MeitaoResult.ok();
		} catch (Exception e) {
			EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
			return MeitaoResult.build(500, "数据导入失败！");
		}
	}
}