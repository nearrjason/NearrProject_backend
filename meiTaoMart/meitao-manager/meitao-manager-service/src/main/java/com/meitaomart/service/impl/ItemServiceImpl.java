package com.meitaomart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meitaomart.common.builder.ItemInfoBuilder;
import com.meitaomart.common.jedis.JedisClient;
import com.meitaomart.common.pojo.EasyUIDataGridResult;
import com.meitaomart.common.pojo.ItemInfo;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.EmailUtils;
import com.meitaomart.common.utils.IDUtils;
import com.meitaomart.common.utils.JsonUtils;
import com.meitaomart.mapper.MeitaoItemCategoryMapper;
import com.meitaomart.mapper.MeitaoItemDescMapper;
import com.meitaomart.mapper.MeitaoItemMapper;
import com.meitaomart.mapper.MeitaoItemPriceMapper;
import com.meitaomart.pojo.MeitaoItem;
import com.meitaomart.pojo.MeitaoItemDesc;
import com.meitaomart.pojo.MeitaoItemDescExample;
import com.meitaomart.pojo.MeitaoItemExample;
import com.meitaomart.pojo.MeitaoItemPrice;
import com.meitaomart.pojo.MeitaoItemPriceExample;
import com.meitaomart.service.ItemService;
import com.meitaomart.pojo.MeitaoItemExample.Criteria;

/**
 * 商品管理Service
 * 
 * @version 1.0
 */

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private MeitaoItemMapper itemMapper;
	@Autowired
	private MeitaoItemDescMapper itemDescMapper;
	@Autowired
	private MeitaoItemPriceMapper itemPriceMapper;
	@Autowired
	private MeitaoItemCategoryMapper itemCategoryMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;
	@Value("${OPTIMIZED_CATEGORY_ID}")
	private Long OPTIMIZED_CATEGORY_ID;

	@Resource
	private Destination topicDestination;

	/**
	 * 得到所有的item信息 信息来源表: MeitaoItem, MeitaoItemPrice, MeitaoItemDesc
	 */
	@Override
	public List<ItemInfo> getItemList() {
		MeitaoItemExample itemExample = new MeitaoItemExample();
		itemExample.setOrderByClause("updated_time DESC");
		List<MeitaoItem> itemList = itemMapper.selectByExample(itemExample);
		List<ItemInfo> resultList = new ArrayList<>();

		for (MeitaoItem item : itemList) {
			MeitaoItemPrice itemPrice = itemPriceMapper.selectByPrimaryKey(item.getId());
			MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
			if (itemPrice != null && itemDesc != null) {
				ItemInfo itemInfo = createItemInfo(item, itemPrice, itemDesc);
				resultList.add(itemInfo);
			}
			
		}
		return resultList;
	}

	private ItemInfo createItemInfo(MeitaoItem meitaoItem, MeitaoItemPrice meitaoItemPrice,
			MeitaoItemDesc meitaoItemDesc) {
		if (meitaoItem == null || meitaoItemPrice == null || meitaoItemDesc == null) {
			return null;
		}
		
		String itemCategoryName = itemCategoryMapper.selectItemCategoryNameByPrimaryKey(meitaoItem.getCategoryId());
		
		String specialCategoryName = null;
		Integer specialCategoryId = meitaoItem.getSpecialCategoryId();
		if (specialCategoryId != null) {
			if (specialCategoryId.equals(-1)) {
				specialCategoryName = "优选商品";
			} else if (specialCategoryId.equals(-2)) {
				specialCategoryName = "最新上架";
			}
		}
		
		String statusName = null;
		Byte status = meitaoItem.getStatus();
		if (status != null) {
			if (status == 1) {
				statusName = "正常";
			} else if (status == 2) {
				statusName = "已删除";
			} else if (status == 0) {
				statusName = "已下架";
			}
		}
		
		ItemInfo itemInfo = ItemInfoBuilder.getInstance().setId(meitaoItem.getId())
				.setCategoryId(meitaoItem.getCategoryId()).setCategoryName(itemCategoryName).setSpecialCategoryId(specialCategoryId).setSpecialCategoryName(specialCategoryName).setName(meitaoItem.getName())
				.setCaption(meitaoItem.getCaption()).setBrandName(meitaoItem.getBrandName())
				.setSupplier(meitaoItem.getSupplier()).setSellPoint(meitaoItem.getSellPoint())
				.setNetWeight(meitaoItem.getNetWeight()).setStockNumber(meitaoItem.getStockNumber())
				.setBarcode(meitaoItem.getBarcode()).setImages(meitaoItem.getImages()).setStatus(meitaoItem.getStatus())
				.setStatusName(statusName)
				.setAdminUserId(meitaoItem.getAdminUserId()).setCreatedTime(meitaoItem.getCreatedTime())
				.setUpdatedTime(meitaoItem.getUpdatedTime()).setCost(meitaoItemPrice.getCost())
				.setSalePrice(meitaoItemPrice.getSalePrice()).setDiscount(meitaoItemPrice.getDiscount())
				.setItemDesc(meitaoItemDesc.getItemDesc()).setDescImages(meitaoItemDesc.getDescImages()).build();

		return itemInfo;
	}

	@Override
	public ItemInfo getItemById(long itemId) {
		/*try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
			if (StringUtils.isNotBlank(json)) {
				ItemInfo itemInfo = JsonUtils.jsonToPojo(json, ItemInfo.class);
				return itemInfo;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			EmailUtils.groupSendEmailForJavaException(e.getMessage());
		}*/
		
		MeitaoItem meitaoItem = itemMapper.selectByPrimaryKey(itemId);
		MeitaoItemPrice meitaoItemPrice = itemPriceMapper.selectByPrimaryKey(itemId);
		MeitaoItemDesc meitaoItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		ItemInfo itemInfo = createItemInfo(meitaoItem, meitaoItemPrice, meitaoItemDesc);
		
		/*try {
			// 把数据库查询结果添加到缓存
			jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(itemInfo));
			// 设置过期时间
			jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", ITEM_CACHE_EXPIRE); // 3600s
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return itemInfo;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		/*
		 * MeitaoItemExample example = new MeitaoItemExample(); List<MeitaoItem>
		 * list = itemMapper.selectByExample(example);
		 */
		List<ItemInfo> list = getItemList();
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		// 取分页结果
		PageInfo<ItemInfo> pageInfo = new PageInfo<>(list);
		// 取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public MeitaoResult deleteItems(String ids) {
		String[] arrayIds = ids.split(",");
		for (String stringId : arrayIds) {
			Long id = Long.parseLong(stringId.trim());
			MeitaoItem item = new MeitaoItem();
			item.setId(id);
			item.setStatus((byte)2);
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return MeitaoResult.ok();
	} 

	@Override
	public MeitaoResult addItem(MeitaoItem item, MeitaoItemPrice itemPrices, String desc, String descImages) {
		/**
		 * 商品表插入
		 */
		String barcode = item.getBarcode();
		if (barcode == null || barcode.length() == 0) {
			return MeitaoResult.build(400, "barcode为空!");
		}
		
		//检查barcode是否存在:
		MeitaoItemExample example = new MeitaoItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andBarcodeEqualTo(barcode);
		List<MeitaoItem> list = itemMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			// 无此条形码商品
			// 生成商品id
			final long itemId = IDUtils.genItemId();

			// 补全item的属性
			item.setId(itemId);
			// 1-正常 0-下架 3-删除
			item.setStatus((byte) 1);
			item.setCreatedTime(new Date());
			item.setUpdatedTime(new Date());

			// 向商品表插入数据
			itemMapper.insert(item);

			/**
			 * 商品价格表插入
			 */
			itemPrices.setItemId(itemId);
			itemPriceMapper.insert(itemPrices);

			/**
			 * 商品描述表插入
			 */
			// 创建一个商品描述表对应的pojo对象
			MeitaoItemDesc itemDesc = new MeitaoItemDesc();

			// 补全属性
			itemDesc.setItemId(itemId);
			itemDesc.setItemDesc(desc);
			itemDesc.setDescImages(descImages);
			itemDesc.setCreatedTime(new Date());
			itemDesc.setUpdatedTime(new Date());

			// 向商品描述表插入数据
			itemDescMapper.insert(itemDesc);
			// 发送一个商品添加消息
			/*
			 * jmsTemplate.send(topicDestination, new MessageCreator() {
			 * 
			 * @Override public Message createMessage(Session session) throws
			 * JMSException { TextMessage textMessage =
			 * session.createTextMessage(itemId + ""); return textMessage; }
			 * 
			 * });
			 */
			return MeitaoResult.ok();
		} else {
			return MeitaoResult.build(400, "此条形码已存在，请去更新商品页面更新!");
			/*MeitaoItem currentItem = list.get(0);
			Integer currentStockNumber = currentItem.getStockNumber();
			String currentImages = currentItem.getImages();
			
			Integer newStockNumber = (item.getStockNumber() != null ? item.getStockNumber() : 0) + (currentStockNumber != null ? currentStockNumber : 0);
			String addedImages = item.getImages();
			String newImages = null;
			if (StringUtils.isNotEmpty(currentImages) && StringUtils.isNotEmpty(addedImages)) {
				newImages = addedImages + "," + currentImages;
			} else if (StringUtils.isNotEmpty(currentImages)) {
				newImages = currentImages;
			} else {
				newImages = addedImages;
			}
			
			item.setStockNumber(newStockNumber);
			item.setImages(newImages);
			
			itemMapper.updateByPrimaryKeySelective(item);*/
		}
		
	}
	
	@Override
	public MeitaoResult updateItem(MeitaoItem item, MeitaoItemPrice itemPrices, String desc, String descImages) {
		item.setUpdatedTime(new Date());
		itemPrices.setItemId(item.getId());
		
		MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
		if (descImages != null && descImages.length() != 0) {
			itemDesc.setDescImages(descImages);
			itemDesc.setUpdatedTime(new Date());
			itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		}
		
		itemMapper.updateByPrimaryKeySelective(item);
		itemPriceMapper.updateByPrimaryKeySelective(itemPrices);
		
		//deleteRedis(REDIS_ITEM_PRE + ":" + item.getId() + ":BASE");
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoItemDesc getItemDescById(long itemId) {
		MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return itemDesc;
	}

	@Override
	public List<ItemInfo> getRecentItemList(Integer days) {
		List<ItemInfo> resultList = new ArrayList<>();
		// TODO Auto-generated method stub
		DateTime dateTime = new DateTime();
		Date daysAgodate = dateTime.minusDays(days).toDate();

		MeitaoItemExample itemExample = new MeitaoItemExample();
		Criteria itemCriteria = itemExample.createCriteria();

		itemCriteria.andCreatedTimeGreaterThan(daysAgodate).andCategoryIdNotEqualTo(OPTIMIZED_CATEGORY_ID);;

		List<MeitaoItem> itemList = itemMapper.selectByExample(itemExample);
		if (itemList != null && itemList.size() == 0) {
			return resultList;
		}

		for (MeitaoItem item : itemList) {
			MeitaoItemPrice itemPrice = itemPriceMapper.selectByPrimaryKey(item.getId());
			MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
			ItemInfo itemInfo = createItemInfo(item, itemPrice, itemDesc);
			resultList.add(itemInfo);
		}
		return resultList;
	}

	@Override
	public List<ItemInfo> getItemListByCategoryId(Long categoryId) {
		List<ItemInfo> resultList = new ArrayList<>();

		MeitaoItemExample itemExample = new MeitaoItemExample();
		Criteria itemCriteria = itemExample.createCriteria();
		itemCriteria.andCategoryIdEqualTo(categoryId).andStatusEqualTo((byte)1);

		List<MeitaoItem> itemList = itemMapper.selectByExample(itemExample);
		if (itemList != null && itemList.size() == 0) {
			return resultList;
		}

		for (MeitaoItem item : itemList) {
			MeitaoItemPrice itemPrice = itemPriceMapper.selectByPrimaryKey(item.getId());
			MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
			ItemInfo itemInfo = createItemInfo(item, itemPrice, itemDesc);
			resultList.add(itemInfo);
		}
		return resultList;
	}
	
	@Override
	public List<ItemInfo> getItemListBySpecialCategoryId(Integer specialCategoryId) {
		List<ItemInfo> resultList = new ArrayList<>();

		MeitaoItemExample itemExample = new MeitaoItemExample();
		Criteria itemCriteria = itemExample.createCriteria();
		itemCriteria.andSpecialCategoryIdEqualTo(specialCategoryId).andStatusEqualTo((byte)1);

		List<MeitaoItem> itemList = itemMapper.selectByExample(itemExample);
		if (itemList != null && itemList.size() == 0) {
			return resultList;
		}

		for (MeitaoItem item : itemList) {
			MeitaoItemPrice itemPrice = itemPriceMapper.selectByPrimaryKey(item.getId());
			MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
			ItemInfo itemInfo = createItemInfo(item, itemPrice, itemDesc);
			resultList.add(itemInfo);
		}
		return resultList;
	}

	@Override
	public List<ItemInfo> getItemListByLimitNumber(Integer limitNumber, String column) {
		List<ItemInfo> resultList = new ArrayList<>();

		MeitaoItemExample itemExample = new MeitaoItemExample();
		Criteria itemCriteria = itemExample.createCriteria();
		itemCriteria.andCategoryIdNotEqualTo(OPTIMIZED_CATEGORY_ID);
		itemExample.setLimitNumber(limitNumber);
		itemExample.setOrderByClause(column + " DESC");

		List<MeitaoItem> itemList = itemMapper.selectByExample(itemExample);
		if (itemList != null && itemList.size() == 0) {
			return resultList;
		}

		for (MeitaoItem item : itemList) {
			MeitaoItemPrice itemPrice = itemPriceMapper.selectByPrimaryKey(item.getId());
			MeitaoItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
			ItemInfo itemInfo = createItemInfo(item, itemPrice, itemDesc);
			resultList.add(itemInfo);
		}
		return resultList;
	} 
	
	private void deleteRedis(String key) {
		if (jedisClient.exists(key)) {
			try {
				jedisClient.del(key);
			} catch (Exception e) {
				EmailUtils.groupSendEmailForJavaException(e.getStackTrace().toString());
			}
		}
	}

	@Override
	public void clearItemInRedis(long itemId) {
		deleteRedis(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
	}

	@Override
	public MeitaoResult onShelf(String ids) {
		String[] arrayIds = ids.split(",");
		for (String stringId : arrayIds) {
			Long id = Long.parseLong(stringId.trim());
			MeitaoItem item = new MeitaoItem();
			item.setId(id);
			item.setStatus((byte)1);
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoResult offShelf(String ids) {
		String[] arrayIds = ids.split(",");
		for (String stringId : arrayIds) {
			Long id = Long.parseLong(stringId.trim());
			MeitaoItem item = new MeitaoItem();
			item.setId(id);
			item.setStatus((byte)0);
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return MeitaoResult.ok();
	}
}