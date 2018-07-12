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
@TableName("question_category")
public class QuestionCategory extends Model<QuestionCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="question_category_id", type= IdType.AUTO)
	private Integer questionCategoryId;
	@TableField("question_category_name")
	private String questionCategoryName;
	private String description;
	private String status;
	private Date created;
	private Date updated;


	public Integer getQuestionCategoryId() {
		return questionCategoryId;
	}

	public QuestionCategory setQuestionCategoryId(Integer questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
		return this;
	}

	public String getQuestionCategoryName() {
		return questionCategoryName;
	}

	public QuestionCategory setQuestionCategoryName(String questionCategoryName) {
		this.questionCategoryName = questionCategoryName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public QuestionCategory setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public QuestionCategory setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public QuestionCategory setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public QuestionCategory setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.questionCategoryId;
	}

	@Override
	public String toString() {
		return "QuestionCategory{" +
			", questionCategoryId=" + questionCategoryId +
			", questionCategoryName=" + questionCategoryName +
			", description=" + description +
			", status=" + status +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
