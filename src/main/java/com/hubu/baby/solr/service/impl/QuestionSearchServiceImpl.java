package com.hubu.baby.solr.service.impl;

import com.hubu.baby.solr.dao.QuestionSearchDao;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.QuestionSearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:28
 */
@Service
public class QuestionSearchServiceImpl implements QuestionSearchService {

    @Value("${solr.babyQuestion.catchAllField}")
    private String CATCH_ALL_FIELD;

    @Autowired
    private QuestionSearchDao searchDao;

    public static final String HIGHLIGHT_SIMPLE_PRE = "<em class=\"highlight\">";
    public static final String HIGHLIGHT_SIMPLE_END = "</em>";

    /**
     * 全文检索（title + desc）
     *
     * @param queryStr
     * @param page
     * @param rows
     * @return
     */
    @Override
    public SearchResult query(String queryStr, int page, int rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.add(CommonParams.Q, CATCH_ALL_FIELD + ":" + queryStr);
        solrQuery.setHighlightSimplePre(HIGHLIGHT_SIMPLE_PRE);
        solrQuery.setHighlightSimplePost(HIGHLIGHT_SIMPLE_END);
        SearchResult searchResult = searchDao.search(solrQuery);
        //计算查询结果总页数
        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount / rows;
        if(recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);

        return searchResult;
    }
}
