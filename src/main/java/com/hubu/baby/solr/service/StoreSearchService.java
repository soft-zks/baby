package com.hubu.baby.solr.service;

import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.pojo.Store;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Solr查询Store
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 17:19
 */
public interface StoreSearchService {

    /**
     * 全文检索（title + desc）
     * @param queryStr
     * @param page
     * @param rows
     * @return
     */
    SearchResult query(String queryStr, int page, int rows) throws Exception;

    /**
     * 添加数据到Solr
     * @param store
     */
    void add(StoreContent store) throws IOException, SolrServerException;

}
