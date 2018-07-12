package com.hubu.baby.solr.dao.impl;

import com.hubu.baby.solr.dao.StoreSearchDao;
import com.hubu.baby.solr.pojo.Knowledge;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.pojo.Store;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: finalcola-zyy
 * @date: 2018/4/27 16:25
 */
@Component
public class StoreSearchDaoImpl implements StoreSearchDao {

    private static final Logger log = LoggerFactory.getLogger(StoreSearchDaoImpl.class);

    @Resource(name = "storeSolrClient")
    private SolrClient solrClient;

    @Override
    public SearchResult search(SolrQuery solrQuery) throws Exception {

        SearchResult searchResult = new SearchResult();

        //查询索引库
//        solrQuery.setHighlight(true);
//        solrQuery.addHighlightField("title");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList documentList = response.getResults();
        searchResult.setRecordCount(documentList.getNumFound());

        List<Store> storeList = response.getBeans(Store.class);

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
//        for (Map.Entry<String, Map<String, List<String>>> entry : highlighting.entrySet()) {
//            Map<String, List<String>> map = entry.getValue();
//            Set<String> keySet = map.keySet();
//            StringBuilder sb = new StringBuilder();
//            for (String s : keySet) {
//                sb.append(s).append(' ');
//            }
//            String format = String.format("key:%s -> field:%s", entry.getKey(), sb.toString());
//            log.info(format);
//        }

        /**
         * 设置高亮
         */
        if (highlighting != null) {
            for (Store store : storeList) {
                String id = store.getId();
                Map<String, List<String>> singleHighlightMap = highlighting.get(id);
                if (singleHighlightMap == null || singleHighlightMap.isEmpty()) {
                    continue;
                }
                List<String> highlightList = singleHighlightMap.get("title");
                if (highlightList == null || highlightList.isEmpty()) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                for (String s : highlightList) {
                    sb.append(s);
                }
                String highlightTitle = sb.toString();
//            log.info("title高亮:" + highlightTitle);
                store.setTitle(highlightTitle);
            }
        }

        searchResult.setKnowledgeList(storeList);
        return searchResult;
    }

    /**
     * 添加商品到Solr
     *
     * @param store
     */
    @Override
    public void addBean(Store store,boolean autoCommit) throws IOException, SolrServerException {
        solrClient.addBean(store);
        if (!autoCommit) {
            solrClient.commit();
        }
    }

    @Override
    public void delete(List<String> idList) throws IOException, SolrServerException {
        solrClient.deleteById(idList);
        solrClient.commit();
    }

    /**
     * 更新
     *
     * @param beans
     * @param isAutoSubmit 是否在solr配置文档中设置了自动提交
     */
    @Override
    public <T> void update(List<T> beans, boolean isAutoSubmit) {
        if (beans.isEmpty()) {
            return;
        }
        Class<T> klass = (Class<T>) beans.get(0).getClass();
        List<SolrDocument> documents = new ArrayList<>(beans.size());
        //--筛选出需要更新的字段，并加入map中
        Field[] fields = klass.getDeclaredFields();
        HashMap<Field, String> fieldStringHashMap = new HashMap<>();
        T tmpBean = beans.get(0);
        for (Field field : fields) {
            field.setAccessible(true);
            //获取字段上Solr的Field注解
            org.apache.solr.client.solrj.beans.Field fieldAnnotation = field.getAnnotation(org.apache.solr.client.solrj.beans.Field.class);
            if (fieldAnnotation == null) {
                continue;
            }
            try {
                Object value = field.get(beans);
                if (value == null) {
                    continue;
                }
            } catch (IllegalAccessException e) {
                continue;
            }
            //筛选完成
            String annoValue = fieldAnnotation.value();
            fieldStringHashMap.put(field, annoValue);
        }

        //--更新
        Map<String, String> map = new HashMap<>(1);//用于原子更新
        for (T bean : beans) {
            SolrInputDocument document = new SolrInputDocument();
            for (Map.Entry<Field, String> entry : fieldStringHashMap.entrySet()) {
                Field field = entry.getKey();
                String annoVal = entry.getValue();
                try {
                    Object value = field.get(bean);
                    if (annoVal.toLowerCase().indexOf("id") == -1) {
                        //不是主键
                        map.put("set", value.toString());
                        document.addField(annoVal,map);
                    } else {
                        //设置主键
                        document.addField(annoVal,value);
                    }
                } catch (IllegalAccessException e) {
                }
            }
            try {
                solrClient.add(document);
                if (!isAutoSubmit) {
                    solrClient.commit();
                }
            } catch (SolrServerException e) {
//                e.printStackTrace();
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
