package com.meitaomart.app.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.pojo.SearchItem;
import com.meitaomart.common.pojo.SearchResult;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.content.pojo.ItemCategoryList;
import com.meitaomart.pojo.MeitaoItemCategory;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.search.service.SearchService;
import com.meitaomart.service.ItemCategoryService;
import com.meitaomart.service.ItemService;

@Controller
public class Search {
	@Autowired
	private SearchService searchService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@Autowired
	private ItemService itemService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	@RequestMapping("/search")
	@ResponseBody
    public MeitaoResult searchItemListByKeyWord(String keyword,
                                                       @RequestParam(defaultValue = "1") Integer page, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (keyword == null || keyword.length() == 0) {
            MeitaoResult.build(400, "搜索词为空!");
        }
        keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        // 查询商品列表
        SearchResult searchResult = searchService.search(keyword, page, SEARCH_RESULT_ROWS);

        List<SearchItem> itemList = searchResult.getItemList();
        Long levelOneCategoryId = null;
        if (itemList != null && itemList.size() != 0) {
            Long categoryId = itemList.get(0).getCategoryId();
            levelOneCategoryId = getLevelOneCategoryId(categoryId);
        }

        List<ItemCategoryList> itemCategoryListWithChildrenList = new ArrayList<>();
        if (levelOneCategoryId != null) {
            itemCategoryListWithChildrenList = itemCategoryService.getItemCategoryListWithChildrenList(levelOneCategoryId);
        }


        map.put("query", keyword);
        map.put("totalPages", searchResult.getTotalPages());
        map.put("page", page);
        map.put("recordCount", searchResult.getRecordCount());
        map.put("itemList", searchResult.getItemList());
        map.put("itemCategoryList", itemCategoryListWithChildrenList);

        return MeitaoResult.ok(map);
    }
	
	@RequestMapping("/category")
    @ResponseBody
    public MeitaoResult getItemListByCategory(@RequestParam Long categoryId, @RequestParam int level, HttpServletRequest request, HttpServletResponse response) {
		if (categoryId == null) {
			return MeitaoResult.build(400, "categoryId 为 null");
		}
		
		if (level == 1) {
			List<Object> result = new ArrayList<>();
			List<MeitaoItemCategory> itemCategoryList = itemCategoryService.getItemCategoryList(categoryId);
			for (MeitaoItemCategory itemCategory : itemCategoryList) {
				Map<String, Object> map = new HashMap<>();
				if (itemCategory != null) {
					Long id = itemCategory.getId();
					String name = itemCategory.getName();
					if (id != null && name != null) {
						List<ItemInfo> list = itemService.getItemListByCategoryId(id);
						map.put("categoryName", name);
						map.put("itemList", list);
						result.add(map);
					}
				}
			}
			
			return MeitaoResult.ok(result);
		} else if (level == 2) {
			List<ItemInfo> list = itemService.getItemListByCategoryId(categoryId);
			return MeitaoResult.ok(list);
		}
		
		return MeitaoResult.build(400, "请出入正确的level级别， 目前level只能为 1, 2");
	}
	
	/*@RequestMapping("/category/{categoryId}")
    @ResponseBody
    public MeitaoResult searchItemListByCategory(Model model, @PathVariable Long categoryId, @RequestParam String cn, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String categoryName = new String(cn.getBytes("iso-8859-1"), "utf-8");
        SearchResult searchResult = searchService.search(categoryName, 1, SEARCH_RESULT_ROWS);

        Long levelOneCategoryId = getLevelOneCategoryId(categoryId);
        List<ItemCategoryList> itemCategoryListWithChildrenList = new ArrayList<>();
        if (levelOneCategoryId != null) {
            itemCategoryListWithChildrenList = itemCategoryService.getItemCategoryListWithChildrenList(levelOneCategoryId);
        }

        map.put("query", categoryName);
        map.put("totalPages", searchResult.getTotalPages());
        map.put("page", 1);
        map.put("recordCount", searchResult.getRecordCount());
        map.put("itemList", searchResult.getItemList());
        map.put("itemCategoryList", itemCategoryListWithChildrenList);
        return MeitaoResult.ok(map);
    }
    
    @RequestMapping("/category/{categoryId2}/{categoryId3}")
    @ResponseBody
    public MeitaoResult searchItemListByCategorys(Model model, @PathVariable Long categoryId2, @PathVariable Long categoryId3, @RequestParam String cn, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return searchItemListByCategory(model, categoryId3, cn, request, response);
    }
    */
    private Long getLevelOneCategoryId(Long categoryId) {
    	Long parentId1 = itemCategoryService.getParentCategoryId(categoryId);
		if (Long.valueOf(0L).equals(parentId1)) {
			return categoryId;
		}
		Long parentId2 = itemCategoryService.getParentCategoryId(parentId1);
		if (Long.valueOf(0L).equals(parentId2)) {
			return parentId1;
		}
		
		return itemCategoryService.getParentCategoryId(parentId2);
	}
}
