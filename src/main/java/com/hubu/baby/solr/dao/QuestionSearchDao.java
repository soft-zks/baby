package com.hubu.baby.solr.dao;

import com.hubu.baby.solr.pojo.Question;
import com.hubu.baby.solr.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * 问题检索DAO
 *
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:19
 */
public interface QuestionSearchDao {

    /**
     * 根据查询条件查询
     *
     * @param solrQuery
     * @return
     * @throws Exception
     */
    SearchResult search(SolrQuery solrQuery) throws Exception;

    /**
     * 添加
     *
     * @param question
     * @param autoCommit
     */
    void add(Question question, boolean autoCommit) throws IOException, SolrServerException;

    /**
     * 删除
     * @param idList
     * @throws IOException
     * @throws SolrServerException
     */
    void delete(List<String> idList) throws IOException, SolrServerException;
}
