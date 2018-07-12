package com.hubu.baby.solr.dao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hubu.baby.entity.KnowledgeContent;
import com.hubu.baby.service.impl.KnowledgeContentServiceImpl;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-05-12 18:46
 * @Since: version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportAllKnowledgeTest {

    @Autowired
    private KnowledgeContentServiceImpl knowledgeContentService;

    @Resource(name = "knowledgeSolrClient")
    private SolrClient solrClient;

    @Test
    public void importAll() {

        Wrapper<KnowledgeContent> wrapper = new EntityWrapper<>();
        try {
            List<KnowledgeContent> knowledgeContentList = knowledgeContentService.selectList(wrapper);
            System.out.println(knowledgeContentList.size());

            /**
             *   private String id;
                 private String knowledge_content_id;
                 private String second_category_id;
                 private String know_title;
                 private String know_url;
                 private String know_picture;
                 private String know_content;
             */
            int i = 1;
            for(KnowledgeContent knowledgeContent : knowledgeContentList) {

                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", i++);
                document.setField("knowledge_content_id", knowledgeContent.getKnowledgeContentId());
                document.setField("second_category_id", knowledgeContent.getSecondCategoryId());
                document.setField("know_title", knowledgeContent.getTitle());
                document.setField("know_url", knowledgeContent.getUrl());
                document.setField("know_picture", knowledgeContent.getPicture());
                document.setField("know_content", knowledgeContent.getContent());
                solrClient.add(document);

                //solrClient.addBean(knowledgeContent);
            }
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
