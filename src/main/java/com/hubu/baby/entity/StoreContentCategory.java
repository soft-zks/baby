package com.hubu.baby.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * 内容分类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@TableName("store_content_category")
public class StoreContentCategory extends Model<StoreContentCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="store_category_id", type= IdType.AUTO)
	private Integer storeCategoryId;
    /**
     * 分类名称
     */
    @NotNull
    @Length(max = 10)
	private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
	private String status;
	@TableField("sort_order")
	private Integer sortOrder;
	private Date created;
	private Date updated;


	public Integer getStoreCategoryId() {
		return storeCategoryId;
	}

	public StoreContentCategory setStoreCategoryId(Integer storeCategoryId) {
		this.storeCategoryId = storeCategoryId;
		return this;
	}

	public String getName() {
		return name;
	}

	public StoreContentCategory setName(String name) {
		this.name = name;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public StoreContentCategory setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public StoreContentCategory setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public StoreContentCategory setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public StoreContentCategory setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.storeCategoryId;
	}

	@Override
	public String toString() {
		return "StoreContentCategory{" +
			", storeCategoryId=" + storeCategoryId +
			", name=" + name +
			", status=" + status +
			", sortOrder=" + sortOrder +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
