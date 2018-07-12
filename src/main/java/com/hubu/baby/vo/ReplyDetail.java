package com.hubu.baby.vo;

import com.hubu.baby.entity.QuestionReply;

import java.util.List;

/**
 * 显示回复列表的VO
 *
 * @author: finalcola-zyy
 * @date: 2018/4/23 16:18
 */
public class ReplyDetail {

    private QuestionReply parentReply;

    private List<QuestionReply> subReplyList;

    public ReplyDetail() {
    }

    public ReplyDetail(QuestionReply parentReply, List<QuestionReply> subReplyList) {
        this.parentReply = parentReply;
        this.subReplyList = subReplyList;
    }

    public QuestionReply getParentReply() {
        return parentReply;
    }

    public void setParentReply(QuestionReply parentReply) {
        this.parentReply = parentReply;
    }

    public List<QuestionReply> getSubReplyList() {
        return subReplyList;
    }

    public void setSubReplyList(List<QuestionReply> subReplyList) {
        this.subReplyList = subReplyList;
    }

    @Override
    public String toString() {
        return "ReplyDetail{" +
                "parentReply=" + parentReply +
                ", subReplyList=" + subReplyList +
                '}';
    }
}
