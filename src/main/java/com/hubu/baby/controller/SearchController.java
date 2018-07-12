package com.hubu.baby.controller;

import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.SearchService;
import com.hubu.baby.util.ExceptionUtil;
import com.hubu.baby.vo.BabyResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 查询控制层
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-22 14:23
 * @Since: version 1.0
 **/
@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger logger = Logger.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/query/{queryStr}")
    public BabyResult search(@PathVariable("queryStr") String queryStr,
                             @RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "rows", defaultValue = "20")Integer rows) {

        if (StringUtils.isBlank(queryStr)) {
            logger.debug("查询条件不能为空");
            return BabyResult.build(400, "查询条件不能为空");
        }

        SearchResult result = null;
        try {
            //解决get乱码
            //queryStr = new String(queryStr.getBytes("ISO8859-1"), "UTF-8");
            result = searchService.search(queryStr, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(ExceptionUtil.getStackTrace(e));
            return BabyResult.build(500,  ExceptionUtil.getStackTrace(e));
        }

        return BabyResult.ok(result);
    }


}
