package com.hubu.baby.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@TableName("ad_content")
public class AdContent extends Model<AdContent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ad_content_id", type= IdType.AUTO)
	private Integer adContentId;
    /**
     * 内容类目ID
     */
	@TableField("ad_category_id")
	private Integer adCategoryId;
    /**
     * 内容标题
     */
	private String title;
    /**
     * 子标题
     */
	@TableField("sub_title")
	private String subTitle;
    /**
     * 标题描述
     */
	@TableField("title_desc")
	private String titleDesc;
    /**
     * 链接
     */
	private String url;
    /**
     * 图片绝对路径
     */
	private String picture;
    /**
     * 内容
     */
	private String content;
	private Date created;
	private Date updated;


	public Integer getAdContentId() {
		return adContentId;
	}

	public AdContent setAdContentId(Integer adContentId) {
		this.adContentId = adContentId;
		return this;
	}

	public Integer getAdCategoryId() {
		return adCategoryId;
	}

	public AdContent setAdCategoryId(Integer adCategoryId) {
		this.adCategoryId = adCategoryId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public AdContent setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public AdContent setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public AdContent setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public AdContent setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public AdContent setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getContent() {
		return content;
	}

	public AdContent setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public AdContent setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public AdContent setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.adContentId;
	}

	@Override
	public String toString() {
		return "AdContent{" +
			", adContentId=" + adContentId +
			", adCategoryId=" + adCategoryId +
			", title=" + title +
			", subTitle=" + subTitle +
			", titleDesc=" + titleDesc +
			", url=" + url +
			", picture=" + picture +
			", content=" + content +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
