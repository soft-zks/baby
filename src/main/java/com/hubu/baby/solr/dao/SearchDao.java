package com.hubu.baby.solr.dao;

import com.hubu.baby.solr.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 13:55
 * @Since: version 1.0
 **/
public interface SearchDao {

    /**
     * 根据查询条件查询
     *
     * @param solrQuery
     * @return
     * @throws Exception
     */
    SearchResult search(SolrQuery solrQuery) throws Exception;
}
