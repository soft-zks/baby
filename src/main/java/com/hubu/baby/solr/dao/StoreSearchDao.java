package com.hubu.baby.solr.dao;

import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.pojo.Store;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * 商品查询
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 16:24
 */
public interface StoreSearchDao {

    /**
     * 根据查询条件查询
     *
     * @param solrQuery
     * @return
     * @throws Exception
     */
    SearchResult search(SolrQuery solrQuery) throws Exception;

    /**
     * 添加商品到Solr
     * @param store
     */
    void addBean(Store store,boolean autoCommit) throws IOException, SolrServerException;

    void delete(List<String> idList) throws IOException, SolrServerException;

    /**
     * 更新
     *
     * @param beans
     * @param isAutoSubmit 是否在solr配置文档中设置了自动提交
     * @param <T>
     */
    <T> void update(List<T> beans, boolean isAutoSubmit);

}
