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
@TableName("ad_content_category")
public class AdContentCategory extends Model<AdContentCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="ad_category_id", type= IdType.AUTO)
	private Integer adCategoryId;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
	private String status;
    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
	@TableField("sort_order")
	private Integer sortOrder;
	private Date created;
	private Date updated;


	public Integer getAdCategoryId() {
		return adCategoryId;
	}

	public AdContentCategory setAdCategoryId(Integer adCategoryId) {
		this.adCategoryId = adCategoryId;
		return this;
	}

	public String getName() {
		return name;
	}

	public AdContentCategory setName(String name) {
		this.name = name;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public AdContentCategory setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public AdContentCategory setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public AdContentCategory setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public AdContentCategory setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.adCategoryId;
	}

	@Override
	public String toString() {
		return "AdContentCategory{" +
			", adCategoryId=" + adCategoryId +
			", name=" + name +
			", status=" + status +
			", sortOrder=" + sortOrder +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
