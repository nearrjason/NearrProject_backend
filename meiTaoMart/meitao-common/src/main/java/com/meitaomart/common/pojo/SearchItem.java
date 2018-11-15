package com.meitaomart.common.pojo;

import java.io.Serializable;
import java.util.Random;

public class SearchItem implements Serializable {
	private Long id;
	private String name;
	private String caption;
	private String sellPoint;
	private Integer netWeight;
	private Integer stockNumber;
	private String images;
	private Byte status;
	private Long salePrice;
	private Byte discount;
	private String categoryName;
	private String categoryNameLevelTwo;
	private Long categoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryNameLevelTwo() {
		return categoryNameLevelTwo;
	}

	public void setCategoryNameLevelTwo(String categoryNameLevelTwo) {
		this.categoryNameLevelTwo = categoryNameLevelTwo;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getOneImage() {
		if (this.images != null && this.images.length() != 0) {
			String[] imageArray = images.split(",");
			return imageArray[0];
		}
		
		return null;
	}

}
