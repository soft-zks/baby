package com.hubu.baby.solr.dao.impl;

import com.hubu.baby.solr.dao.QuestionSearchDao;
import com.hubu.baby.solr.pojo.Question;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.pojo.Store;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:21
 */
@Component
public class QuestionSearchDaoImpl implements QuestionSearchDao {

    private static final Logger log = LoggerFactory.getLogger(QuestionSearchDaoImpl.class);

    @Resource(name = "babyQuestionClient")
    private SolrClient solrClient;

    /**
     * 根据查询条件查询
     *
     * @param solrQuery
     * @return
     * @throws Exception
     */
    @Override
    public SearchResult search(SolrQuery solrQuery) throws Exception {
        SearchResult searchResult = new SearchResult();

        //查询索引库
//        solrQuery.setHighlight(true);
//        solrQuery.addHighlightField("title");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList documentList = response.getResults();
        searchResult.setRecordCount(documentList.getNumFound());

        List<Question> questionList = response.getBeans(Question.class);

        searchResult.setKnowledgeList(questionList);
        return searchResult;
    }

    /**
     * 添加
     *
     * @param question
     * @param autoCommit
     */
    @Override
    public void add(Question question, boolean autoCommit) throws IOException, SolrServerException {
        solrClient.addBean(question);
        if (!autoCommit) {
            solrClient.commit();
        }
    }

    /**
     * 删除
     *
     * @param idList
     * @throws IOException
     * @throws SolrServerException
     */
    @Override
    public void delete(List<String> idList) throws IOException, SolrServerException {
        solrClient.deleteById(idList);
        solrClient.commit();
    }


}
