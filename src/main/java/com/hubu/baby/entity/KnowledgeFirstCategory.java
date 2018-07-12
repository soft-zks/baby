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
 * 内容分类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@TableName("knowledge_first_category")
public class KnowledgeFirstCategory extends Model<KnowledgeFirstCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="first_category_id", type= IdType.AUTO)
	private Integer firstCategoryId;
    /**
     * 分类名称
     */
	@TableField("first_name")
	private String firstName;
	private String picture;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
	private String status;
	@TableField("first_sort_order")
	private Integer firstSortOrder;
	private Date created;
	private Date updated;


	public Integer getFirstCategoryId() {
		return firstCategoryId;
	}

	public KnowledgeFirstCategory setFirstCategoryId(Integer firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public KnowledgeFirstCategory setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public KnowledgeFirstCategory setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public KnowledgeFirstCategory setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getFirstSortOrder() {
		return firstSortOrder;
	}

	public KnowledgeFirstCategory setFirstSortOrder(Integer firstSortOrder) {
		this.firstSortOrder = firstSortOrder;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public KnowledgeFirstCategory setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public KnowledgeFirstCategory setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.firstCategoryId;
	}

	@Override
	public String toString() {
		return "KnowledgeFirstCategory{" +
			", firstCategoryId=" + firstCategoryId +
			", firstName=" + firstName +
			", picture=" + picture +
			", status=" + status +
			", firstSortOrder=" + firstSortOrder +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
