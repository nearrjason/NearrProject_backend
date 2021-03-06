package com.meitaomart.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ItemInfo implements Serializable {
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	
	public Integer getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Integer netWeight) {
		this.netWeight = netWeight;
	}

	public Integer getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}

	public Byte getDiscount() {
		return discount;
	}

	public void setDiscount(Byte discount) {
		this.discount = discount;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getDescImages() {
		return descImages;
	}

	public void setDescImages(String descImages) {
		this.descImages = descImages;
	}

	public String getOneImage() {
		if (this.images != null && this.images.length() != 0) {
			String[] imageArray = images.split(",");
			return imageArray[0];
		}
		
		return null;
	}
	
	public List<String> getImageList() {
		List<String> list = new ArrayList<>();
		if (this.images == null || this.images.length() == 0) {
			return list;
		}
		
		String[] imageArray = images.split(",");
		for (String image : imageArray) {
			list.add(image);
		}
		
		return list;
	}
	
	public List<String> getDescImageList() {
		List<String> list = new ArrayList<>();
		if (this.descImages == null || this.descImages.length() == 0) {
			return list;
		}
		
		String[] imageArray = descImages.split(",");
		for (String image : imageArray) {
			list.add(image);
		}
		
		return list;
	}
	
}
