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
@TableName("knowledge_content")
public class KnowledgeContent extends Model<KnowledgeContent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="knowledge_content_id", type= IdType.AUTO)
	private Integer knowledgeContentId;
	@TableField("second_category_id")
	private Integer secondCategoryId;
	private String title;
	private String url;
	private String picture;
	private String content;
	private Date created;
	private Date updated;


	public Integer getKnowledgeContentId() {
		return knowledgeContentId;
	}

	public KnowledgeContent setKnowledgeContentId(Integer knowledgeContentId) {
		this.knowledgeContentId = knowledgeContentId;
		return this;
	}

	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public KnowledgeContent setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public KnowledgeContent setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public KnowledgeContent setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public KnowledgeContent setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getContent() {
		return content;
	}

	public KnowledgeContent setContent(String content) {
		this.content = content;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public KnowledgeContent setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public KnowledgeContent setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.knowledgeContentId;
	}

	@Override
	public String toString() {
		return "KnowledgeContent{" +
			", knowledgeContentId=" + knowledgeContentId +
			", secondCategoryId=" + secondCategoryId +
			", title=" + title +
			", url=" + url +
			", picture=" + picture +
			", content=" + content +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
