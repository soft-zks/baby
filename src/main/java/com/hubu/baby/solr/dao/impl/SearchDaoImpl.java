package com.hubu.baby.solr.dao.impl;

import com.hubu.baby.solr.dao.SearchDao;
import com.hubu.baby.solr.pojo.Knowledge;
import com.hubu.baby.solr.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 13:56
 * @Since: version 1.0
 **/
@Repository
public class SearchDaoImpl implements SearchDao{

    @Resource(name = "knowledgeSolrClient")
    private SolrClient solrClient;

    @Override
    public SearchResult search(SolrQuery solrQuery) throws Exception {

        SearchResult searchResult = new SearchResult();

        //查询索引库
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList documentList = response.getResults();
        searchResult.setRecordCount(documentList.getNumFound());

        //取高亮显示
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        List<Knowledge> knowledges = new ArrayList<>();
        for (SolrDocument solrDocument : documentList) {
            Knowledge knowledge = new Knowledge();
            knowledge.setId((String) solrDocument.get("id"));

            //取高亮显示的结果
            /*List<String> list = highlighting.get(solrDocument.get("id")).get("know_title");
            String title = "";
            if(list != null && list.size() > 0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("know_title");
            }*/

            knowledge.setKnow_title((String) solrDocument.get("know_title"));
            knowledge.setKnow_picture((String) solrDocument.get("know_picture"));
            knowledge.setKnow_content((String) solrDocument.get("know_content"));
            knowledges.add(knowledge);
        }

        searchResult.setKnowledgeList(knowledges);

        return searchResult;
    }

}
