package com.hubu.baby.solr.service.impl;

import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.StoreSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 17:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreSearchServiceImplTest {
    
    private static final Logger log = LoggerFactory.getLogger(StoreSearchServiceImplTest.class);

    @Autowired
    private StoreSearchService storeSearchService;
    
    @Test
    public void query() throws Exception {
        SearchResult searchResult = storeSearchService.query("赵", 1, 10);
        log.info(searchResult.toString());
    }

    @Test
    public void add() throws Exception {
        StoreContent storeContent = new StoreContent();
        storeContent.setTitle("婴儿纸尿裤")
                .setDescription("赵昆松小时候用过的")
                .setStoreCategoryId(1)
                .setPrice(BigDecimal.TEN)
                .setUpdated(new Date())
                .setUpdated(new Date())
                .setStoreContentId(11)
                .setStoreCategoryName("育儿读物");
        storeSearchService.add(storeContent);
    }

}