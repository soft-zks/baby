package com.hubu.baby.solr.service;

import com.hubu.baby.solr.pojo.SearchResult;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 14:16
 * @Since: version 1.0
 **/
public interface SearchService {

    /**
     * 查询
     *
     * @param queryStr
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    SearchResult search(String queryStr, int page, int rows) throws Exception;
}
