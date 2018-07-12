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
@TableName("knowledge_second_category")
public class KnowledgeSecondCategory extends Model<KnowledgeSecondCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="second_category_id", type= IdType.AUTO)
	private Integer secondCategoryId;
    /**
     * 分类名称
     */
	@TableField("second_name")
	private String secondName;
	@TableField("first_category_id")
	private Integer firstCategoryId;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
	private String status;
	@TableField("second_sort_order")
	private Integer secondSortOrder;
	private Date created;
	private Date updated;


	public Integer getSecondCategoryId() {
		return secondCategoryId;
	}

	public KnowledgeSecondCategory setSecondCategoryId(Integer secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
		return this;
	}

	public String getSecondName() {
		return secondName;
	}

	public KnowledgeSecondCategory setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public Integer getFirstCategoryId() {
		return firstCategoryId;
	}

	public KnowledgeSecondCategory setFirstCategoryId(Integer firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public KnowledgeSecondCategory setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getSecondSortOrder() {
		return secondSortOrder;
	}

	public KnowledgeSecondCategory setSecondSortOrder(Integer secondSortOrder) {
		this.secondSortOrder = secondSortOrder;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public KnowledgeSecondCategory setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public KnowledgeSecondCategory setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.secondCategoryId;
	}

	@Override
	public String toString() {
		return "KnowledgeSecondCategory{" +
			", secondCategoryId=" + secondCategoryId +
			", secondName=" + secondName +
			", firstCategoryId=" + firstCategoryId +
			", status=" + status +
			", secondSortOrder=" + secondSortOrder +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
