package com.hubu.baby.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


/**
 * @Description: solr配置类
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-21 21:39
 * @Since: version 1.0
 **/
@Configuration
public class SolrClientConfiguration {

    @Autowired
    private Environment environment;

    @Bean(name = "knowledgeSolrClient")
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(environment.getRequiredProperty("spring.data.solr.host")).build();
    }

    @Bean(name = "storeSolrClient")
    public SolrClient storeSolrClient() {
        return new HttpSolrClient.Builder(environment.getRequiredProperty("solr.storeContent.host")).build();
    }

    @Bean(name = "babyQuestionClient")
    public SolrClient babyQuestionClient() {
        return new HttpSolrClient.Builder(environment.getRequiredProperty("solr.babyQuestion.host")).build();
    }

}
