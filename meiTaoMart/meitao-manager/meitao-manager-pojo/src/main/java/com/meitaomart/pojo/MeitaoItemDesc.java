package com.meitaomart.pojo;

import java.io.Serializable;
import java.util.Date;

public class MeitaoItemDesc implements Serializable{
    private Long itemId;

    private Date createdTime;

    private Date updatedTime;

    private String descImages;

    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public String getDescImages() {
        return descImages;
    }

    public void setDescImages(String descImages) {
        this.descImages = descImages == null ? null : descImages.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}