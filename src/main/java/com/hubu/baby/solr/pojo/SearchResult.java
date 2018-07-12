package com.hubu.baby.solr.pojo;

import java.util.List;

/**
 * @Description: solr查询结果
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 13:57
 * @Since: version 1.0
 **/
public class SearchResult {

    private List knowledgeList;
    private long recordCount;
    private long pageCount;
    private long curPage;

    public List getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "knowledgeList=" + knowledgeList.toString() +
                ", recordCount=" + recordCount +
                ", pageCount=" + pageCount +
                ", curPage=" + curPage +
                '}';
    }
}
