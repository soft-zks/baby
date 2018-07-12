package com.hubu.baby.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public class Question extends Model<Question> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="question_id", type= IdType.AUTO)
	private Integer questionId;
	@TableField("question_title")
	private String questionTitle;
	@TableField("question_content")
	private String questionContent;
	private String picture;
	@TableField("question_category_id")
	private Integer questionCategoryId;
    /**
     * 分类名称
     */
	@TableField("question_category_name")
	private String questionCategoryName;
	@TableField("user_id")
	private Integer userId;
	private String status;
	private Date created;
	private Date updated;


	public Integer getQuestionId() {
		return questionId;
	}

	public Question setQuestionId(Integer questionId) {
		this.questionId = questionId;
		return this;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public Question setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
		return this;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public Question setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public Question setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public Integer getQuestionCategoryId() {
		return questionCategoryId;
	}

	public Question setQuestionCategoryId(Integer questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
		return this;
	}

	public String getQuestionCategoryName() {
		return questionCategoryName;
	}

	public Question setQuestionCategoryName(String questionCategoryName) {
		this.questionCategoryName = questionCategoryName;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public Question setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Question setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public Question setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public Question setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.questionId;
	}

	@Override
	public String toString() {
		return "Question{" +
			", questionId=" + questionId +
			", questionTitle=" + questionTitle +
			", questionContent=" + questionContent +
			", picture=" + picture +
			", questionCategoryId=" + questionCategoryId +
			", questionCategoryName=" + questionCategoryName +
			", userId=" + userId +
			", status=" + status +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
