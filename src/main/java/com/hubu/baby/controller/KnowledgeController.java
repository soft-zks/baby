package com.hubu.baby.controller;

import com.hubu.baby.service.IKnowledgeContentService;
import com.hubu.baby.service.IKnowledgeFirstCategoryService;
import com.hubu.baby.service.IKnowledgeSecondCategoryService;
import com.hubu.baby.vo.BabyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 知识版面
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-20 19:45
 * @Since: version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private IKnowledgeFirstCategoryService knowledgeFirstCategoryService;
    @Autowired
    private IKnowledgeSecondCategoryService knowledgeSecondCategoryService;
    @Autowired
    private IKnowledgeContentService knowledgeContentService;

    /**
     * 获取一级分类
     *
     * @return
     */
    @GetMapping("/getAllFirstCategory")
    public BabyResult getAllFirstCategory() {

        return knowledgeFirstCategoryService.getAllFirstCategory();
    }

    /**
     * 根据id获取二级分类信息
     *
     * @param firstId
     * @return
     */
    @GetMapping("/getSecondCategory/{firstId}")
    public BabyResult getSecondCategory(@PathVariable("firstId") Integer firstId) {

        return knowledgeSecondCategoryService.getSecondCategoryById(firstId);
    }

    /**
     * 查询具体二级分类下的文章信息列表
     *
     * @param secondId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getContentList/{secondId}")
    public BabyResult getContentList(@PathVariable("secondId") Integer secondId,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {

        return knowledgeContentService.getContentList(secondId, page, size);
    }

    /**
     * 根据id查询文章详细内容
     *
     * @param contentId
     * @return
     */
    @GetMapping("/getContent/{contentId}")
    public BabyResult getContent(@PathVariable("contentId") Integer contentId) {

        return knowledgeContentService.getContentById(contentId);
    }

    /**
     * 随机推荐两篇文章
     * 
     * @param secondId
     * @return
     */
    @GetMapping("/getRandomContent/{secondId}")
    public BabyResult getRandomContent(@PathVariable("secondId") Integer secondId) {
        
        return knowledgeContentService.getRandomContent(secondId);
    }

    /**
     * 随机推荐
     *
     * @param count
     * @return
     */
    @GetMapping("/getRecommend/{count}")
    public BabyResult getRecommend(@PathVariable("count") Integer count) {

        return knowledgeContentService.getRecommend(count);
    }
}
