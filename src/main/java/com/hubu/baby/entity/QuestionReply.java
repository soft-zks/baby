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
@TableName("question_reply")
public class QuestionReply extends Model<QuestionReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="reply_id", type= IdType.AUTO)
	private Integer replyId;
	@TableField("reply_content")
	private String replyContent;
	@TableField("parent_id")
	private Integer parentId;
	@TableField("question_id")
	private Integer questionId;
	@TableField("user_id")
	private Integer userId;
	private String status;
	private Date created;
	private Date updated;

	/**
	 * 用户头像
	 */
	private String picture;


	public Integer getReplyId() {
		return replyId;
	}

	public QuestionReply setReplyId(Integer replyId) {
		this.replyId = replyId;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public QuestionReply setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public QuestionReply setReplyContent(String replyContent) {
		this.replyContent = replyContent;
		return this;
	}

	public Integer getParentId() {
		return parentId;
	}

	public QuestionReply setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public QuestionReply setQuestionId(Integer questionId) {
		this.questionId = questionId;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public QuestionReply setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public QuestionReply setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public QuestionReply setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public QuestionReply setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.replyId;
	}

	@Override
	public String toString() {
		return "QuestionReply{" +
			", replyId=" + replyId +
			", replyContent=" + replyContent +
			", parentId=" + parentId +
			", questionId=" + questionId +
			", userId=" + userId +
			", status=" + status +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
