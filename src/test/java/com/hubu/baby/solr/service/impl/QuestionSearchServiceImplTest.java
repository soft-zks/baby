package com.hubu.baby.solr.service.impl;

import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.QuestionSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionSearchServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(QuestionSearchServiceImplTest.class);

    @Autowired
    private QuestionSearchService questionSearchService;

    @Test
    public void query() throws Exception {
        SearchResult searchResult = questionSearchService.query("教育", 1, 10);
        log.info(searchResult.toString());
    }

}