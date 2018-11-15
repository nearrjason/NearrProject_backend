package com.meitaomart.app.pojo;

public class ItemInfoIndex {
	private Long id;
	private String name;
	private Long price;
	private Long discountPrice;
	private String image;
	
	

	public ItemInfoIndex(Long id, String name, Long price, Long discountPrice, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.image = image;
	}

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Long discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
