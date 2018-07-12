package com.hubu.baby.solr.dao.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.service.IStoreContentService;
import com.hubu.baby.solr.dao.StoreSearchDao;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.pojo.Store;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreSearchDaoImplTest {

    private static final Logger log = LoggerFactory.getLogger(StoreSearchDaoImplTest.class);

    @Autowired
    private StoreSearchDao storeSearchDao;

    @Autowired
    private IStoreContentService storeContentService;

    @Test
    public void search() throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.add(CommonParams.Q,"title:测试");
        SearchResult searchResult = storeSearchDao.search(solrQuery);
        log.info(searchResult.toString());

    }

    @Test
    public void add() {
        List<StoreContent> contentList = storeContentService.selectPage(new Page<>(1, 10)).getRecords();
        for (StoreContent storeContent : contentList) {
            Store store = new Store(storeContent);
            try {
                storeSearchDao.addBean(store,false);
            } catch (IOException|SolrServerException e) {
                log.info("添加失败:",e);
            }
        }
    }

    @Test
    public void delete() {
        ArrayList<String> list = new ArrayList<>(15);
        for (int i = 1000; i <= 2000; i++) {
            list.add(String.valueOf(i));
        }
//        list.add("null");
        try {
            storeSearchDao.delete(list);
        } catch (IOException|SolrServerException e) {
            e.printStackTrace();
        }

    }

}