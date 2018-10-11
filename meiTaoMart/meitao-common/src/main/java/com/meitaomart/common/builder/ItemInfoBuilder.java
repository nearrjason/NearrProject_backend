package com.meitaomart.common.builder;

import java.util.Date;

import com.meitaomart.common.pojo.ItemInfo;

public class ItemInfoBuilder {
	private static ItemInfoBuilder itemInfoBuilder = new ItemInfoBuilder();
	
	private ItemInfoBuilder() {}
	
	public static ItemInfoBuilder getInstance() {
		return itemInfoBuilder;
	}
	
	private Long id;

	private Long categoryId;

	private String name;

	private String caption;

	private String brandName;

	private String supplier;

	private String sellPoint;

	private Integer netWeight;

	private Integer stockNumber;

	private String barcode;

	private String images;

	private Byte status;

	private Integer adminUserId;

	private Date createdTime;

	private Date updatedTime;

	private Long cost;

	private Long salePrice;

	private Byte discount;

	private String itemDesc;
	
	private String descImages;

	public ItemInfoBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public ItemInfoBuilder setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public ItemInfoBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ItemInfoBuilder setCaption(String caption) {
		this.caption = caption;
		return this;
	}

	public ItemInfoBuilder setBrandName(String brandName) {
		this.brandName = brandName;
		return this;
	}

	public ItemInfoBuilder setSupplier(String supplier) {
		this.supplier = supplier;
		return this;
	}

	public ItemInfoBuilder setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
		return this;
	}

	public ItemInfoBuilder setNetWeight(Integer netWeight) {
		this.netWeight = netWeight;
		return this;
	}

	public ItemInfoBuilder setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
		return this;
	}

	public ItemInfoBuilder setBarcode(String barcode) {
		this.barcode = barcode;
		return this;
	}

	public ItemInfoBuilder setImages(String images) {
		this.images = images;
		return this;
	}

	public ItemInfoBuilder setStatus(Byte status) {
		this.status = status;
		return this;
	}

	public ItemInfoBuilder setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
		return this;
	}

	public ItemInfoBuilder setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	public ItemInfoBuilder setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
		return this;
	}

	public ItemInfoBuilder setCost(Long cost) {
		this.cost = cost;
		return this;
	}

	public ItemInfoBuilder setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
		return this;
	}

	public ItemInfoBuilder setDiscount(Byte discount) {
		this.discount = discount;
		return this;
	}

	public ItemInfoBuilder setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
		return this;
	}
	
	public ItemInfoBuilder setDescImages(String descImages) {
		this.descImages = descImages;
		return this;
	}

	public ItemInfo build() {
		ItemInfo itemInfo = new ItemInfo();

		itemInfo.setId(this.id);
		itemInfo.setCategoryId(this.categoryId);
		itemInfo.setName(this.name);
		itemInfo.setCaption(this.caption);
		itemInfo.setBrandName(this.brandName);
		itemInfo.setSupplier(this.supplier);
		itemInfo.setSellPoint(this.sellPoint);
		itemInfo.setNetWeight(this.netWeight);
		itemInfo.setStockNumber(this.stockNumber);
		itemInfo.setBarcode(this.barcode);
		itemInfo.setImages(this.images);
		itemInfo.setStatus(this.status);
		itemInfo.setAdminUserId(this.adminUserId);
		itemInfo.setCreatedTime(this.createdTime);
		itemInfo.setUpdatedTime(this.updatedTime);
		itemInfo.setCost(this.cost);
		itemInfo.setSalePrice(this.salePrice);
		itemInfo.setDiscount(this.discount);
		itemInfo.setItemDesc(this.itemDesc);
		itemInfo.setDescImages(this.descImages);

		return itemInfo;
	}
}
