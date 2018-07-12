package com.hubu.baby.solr.service.impl;

import com.hubu.baby.solr.dao.SearchDao;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.HighlightParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 14:17
 * @Since: version 1.0
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    private static final String SOLR_QUERY_KEY = "know_keywords:";

    @Override
    public SearchResult search(String queryStr, int page, int rows) throws Exception {

        //创建查询条件
        SolrQuery query = new SolrQuery();
        query.setQuery(SOLR_QUERY_KEY + queryStr);
        //query.setStart((page - 1) * rows);
        //query.setRows(rows);
        query.set("df", "know_keywords");  //设置默认搜索域
        //query.add(CommonParams.Q,  SOLR_QUERY_KEY  + queryStr);
        query.add(HighlightParams.FIELDS, queryStr);
        query.setHighlight(false);          //设置高亮显示
        //query.addHighlightField("know_title");
        //query.setHighlightSimplePre("<em style=\"color:red\">");
        //query.setHighlightSimplePost("</em>");

        //执行查询
        SearchResult searchResult = searchDao.search(query);
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
