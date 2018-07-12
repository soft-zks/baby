package com.hubu.baby.solr.service;

import com.hubu.baby.solr.pojo.SearchResult;

/**
 * 问题检索
 *
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:26
 */
public interface QuestionSearchService {

    /**
     * 全文检索（title + desc）
     * @param queryStr
     * @param page
     * @param rows
     * @return
     */
    SearchResult query(String queryStr, int page, int rows) throws Exception;
}
