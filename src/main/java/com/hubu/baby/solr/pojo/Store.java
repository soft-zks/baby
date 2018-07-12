package com.hubu.baby.solr.pojo;

import com.hubu.baby.entity.StoreContent;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * 商品信息
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 16:10
 */
public class Store {
    @Field("id")
    private String id;
    @Field("storeContentId")
    private Integer storeContentId;
    @Field("storeCategoryId")
    private Integer storeCategoryId;
    @Field("title")
    private String title;
    @Field("url")
    private String url;
    @Field("picture")
    private String picture;
    @Field("price")
    private Float price;
    @Field("description")
    private String description;
    @Field("created")
    private Date created;
    @Field("updated")
    private Date updated;
    @Field("storeCategoryName_ik")
    private String storeCategoryName;

    public Store() {
    }

    public Store(StoreContent storeContent) {
        Integer storeContentId = storeContent.getStoreContentId();
        id = String.valueOf(storeContentId);
        this.storeContentId = storeContentId;
        storeCategoryId = storeContent.getStoreCategoryId();
        title = storeContent.getTitle();
        url = storeContent.getUrl();
        picture = storeContent.getPicture();
        price = storeContent.getPrice().floatValue();
        description = storeContent.getDescription();
        created = storeContent.getCreated();
        updated = storeContent.getUpdated();
        storeCategoryName = storeContent.getStoreCategoryName();
    }

    public String getStoreCategoryName() {
        return storeCategoryName;
    }

    public void setStoreCategoryName(String storeCategoryName) {
        this.storeCategoryName = storeCategoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStoreContentId() {
        return storeContentId;
    }

    public void setStoreContentId(Integer storeContentId) {
        this.storeContentId = storeContentId;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", storeContentId=" + storeContentId +
                ", storeCategoryId=" + storeCategoryId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
