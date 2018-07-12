package com.hubu.baby.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@TableName("store_content")
public class StoreContent extends Model<StoreContent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="store_content_id", type= IdType.AUTO)
	private Integer storeContentId;
	@NotNull
	@TableField("store_category_id")
	private Integer storeCategoryId;
	@Length(min = 0,max = 100)
	private String title;
	private String url;
	private String picture;
	private BigDecimal price;
	private String description;
	private Date created;
	private Date updated;
	private String storeCategoryName;

	public String getStoreCategoryName() {
		return storeCategoryName;
	}

	public StoreContent setStoreCategoryName(String storeCategoryName) {
		this.storeCategoryName = storeCategoryName;
		return this;
	}

	public Integer getStoreContentId() {
		return storeContentId;
	}

	public StoreContent setStoreContentId(Integer storeContentId) {
		this.storeContentId = storeContentId;
		return this;
	}

	public Integer getStoreCategoryId() {
		return storeCategoryId;
	}

	public StoreContent setStoreCategoryId(Integer storeCategoryId) {
		this.storeCategoryId = storeCategoryId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public StoreContent setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public StoreContent setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public StoreContent setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public StoreContent setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public StoreContent setDescription(String description) {
		this.description = description;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public StoreContent setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public StoreContent setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.storeContentId;
	}

	@Override
	public String toString() {
		return "StoreContent{" +
				"storeContentId=" + storeContentId +
				", storeCategoryId=" + storeCategoryId +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", picture='" + picture + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", created=" + created +
				", updated=" + updated +
				", storeCategoryName='" + storeCategoryName + '\'' +
				'}';
	}
}
