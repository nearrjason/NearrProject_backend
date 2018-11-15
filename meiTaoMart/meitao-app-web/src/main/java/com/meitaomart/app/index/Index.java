package com.meitaomart.app.index;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meitaomart.app.pojo.ItemInfoIndex;
import com.meitaomart.cart.service.CartService;
import com.meitaomart.common.pojo.CartItem;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.content.pojo.ItemCategoryList;
import com.meitaomart.content.service.ContentService;
import com.meitaomart.pojo.MeitaoContent;
import com.meitaomart.pojo.MeitaoItemCategory;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.service.ItemCategoryService;
import com.meitaomart.service.ItemService;
import com.meitaomart.sso.service.TokenService;

@Controller
public class Index {
	@Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;
    @Value("${PARENT_CATEGORY_ID}")
    private Long PARENT_CATEGORY_ID;
    @Value("${OPTIMIZED_CATEGORY_ID}")
    private Long OPTIMIZED_CATEGORY_ID;
    @Value("${RECENT_DAYS}")
    private Integer RECENT_DAYS;

    @Autowired
    private ContentService contentService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;
    
    /**
     * 得到首页轮播图
     * @param model
     * @param request
     * @param response
     * @return 0为轮播图 1为商品分类
     */
    @RequestMapping("/index/navigation")
    @ResponseBody
    public MeitaoResult showIndexNavigation(Model model, HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<>();
    	
    	List<MeitaoContent> slideShowList = contentService.getContentListByCid(CONTENT_LUNBO_ID);
    	List<MeitaoItemCategory> itemCategoryList = itemCategoryService.getItemCategoryList(PARENT_CATEGORY_ID);
        //List<ItemCategoryList> itemCategoryList = itemCategoryService.getItemCategoryListWithChildrenList(PARENT_CATEGORY_ID);
        
        map.put("banner", slideShowList);
        map.put("itemCategoryList", itemCategoryList);
       
        return MeitaoResult.ok(map);
    }
    
    @RequestMapping("index/itemCategoryList")
    @ResponseBody
    public MeitaoResult getItemCategoryListByParentCategoryId(HttpServletRequest request, HttpServletResponse response, Long parentCategoryId) {
    	return parentCategoryId == null ? MeitaoResult.build(400, "parent category id 为空") : MeitaoResult.ok(itemCategoryService.getItemCategoryList(parentCategoryId));
    }
    
    
    
    /**
     * 主页中的商品列表，包括:
     *  优选商品, 最新上架
     * @param model
     * @param request
     * @param response
     * @return 0为最新上架  1为优选商品
     */
    @RequestMapping("/index/itemList")
    @ResponseBody
    public MeitaoResult showIndexItemList(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> result = new ArrayList<>();
    	
        List<ItemInfoIndex> newItemList = itemListConversion(itemService.getItemListByLimitNumber(10, "created_time"));
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "最新上架");
        map1.put("bannerList", newItemList);
        
        List<ItemInfoIndex> optimizedItemList = itemListConversion(itemService.getItemListByCategoryId(OPTIMIZED_CATEGORY_ID));
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "特价商品");
        map2.put("bannerList", optimizedItemList);
       
        result.add(map1);
        result.add(map2);
        return MeitaoResult.ok(result);
    }
    
    private List<ItemInfoIndex> itemListConversion(List<ItemInfo> itemList) {
    	List<ItemInfoIndex> list = new ArrayList<>();
    	for (ItemInfo itemInfo : itemList) {
    		ItemInfoIndex itemInfoIndex = new ItemInfoIndex(itemInfo.getId(), itemInfo.getName(), itemInfo.getSalePrice(), itemInfo.getSalePrice() - itemInfo.getDiscount(), itemInfo.getOneImage());
    		list.add(itemInfoIndex);
    	}
    	
    	return list;
    }
}
