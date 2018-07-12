package com.hubu.baby.solr.pojo;

import com.hubu.baby.util.FieldTransfer;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/**
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:23
 */

public class Question {
    @Field("questionId")
    private Integer questionId;
    @Field("questionTitle")
    private String questionTitle;
    @Field("questionContent")
    private String questionContent;
    @Field("picture")
    private String picture;
    @Field("questionCategoryId")
    private Integer questionCategoryId;
    @Field("questionCategoryName")
    private String questionCategoryName;
    @Field("userId")
    private Integer userId;
    @Field("created")
    private Date created;
    @Field("updated")
    private Date updated;

    public Question() {
    }

    public Question(com.hubu.baby.entity.Question question) {
        try {
            FieldTransfer.fieldTrans(question, this);
        } catch (IllegalAccessException e) {

        }
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Integer questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getQuestionCategoryName() {
        return questionCategoryName;
    }

    public void setQuestionCategoryName(String questionCategoryName) {
        this.questionCategoryName = questionCategoryName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", picture='" + picture + '\'' +
                ", questionCategoryId=" + questionCategoryId +
                ", questionCategoryName='" + questionCategoryName + '\'' +
                ", userId=" + userId +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
