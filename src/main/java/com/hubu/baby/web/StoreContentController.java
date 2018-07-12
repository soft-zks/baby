package com.hubu.baby.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hubu.baby.constant.StoreConstant;
import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.entity.StoreContentCategory;
import com.hubu.baby.enums.ReplyCode;
import com.hubu.baby.service.IStoreContentCategoryService;
import com.hubu.baby.service.IStoreContentService;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.StoreSearchService;
import com.hubu.baby.vo.CategoryWithDataVo;
import com.hubu.baby.vo.ReplyResult;
import com.hubu.baby.vo.StoreListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@RestController
@RequestMapping("/baby/storeContent")
public class StoreContentController {

    private static final Logger log = LoggerFactory.getLogger(StoreContentController.class);

    @Autowired
    private IStoreContentService storeContentService;

    @Autowired
    private IStoreContentCategoryService storeContentCategoryService;

    @Autowired
    private StoreSearchService searchService;

    private static final Random RANDOM = new Random();

    public static int getRandomNum(int start ,int end){
        int result = RANDOM.nextInt(end - start);
        return result + start;
    }


    /**
     * 默认获取随机同类别商品的数量
     */
    public static final int DEFAULT_SUGGEST_STORE_NUM = 5;

    /**
     * @description: 获取同类别的随机商品
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getRandomSuggest",produces = {"application/json;charset=UTF-8"})
    public Object getRandomStore(@RequestParam("categoryId")Integer categoryId) {
        StoreContent storeContent = new StoreContent().setStoreCategoryId(categoryId);
        int count = storeContentService.selectCount(new EntityWrapper<>(storeContent));
        int index = getRandomNum(0, count - DEFAULT_SUGGEST_STORE_NUM);
        //index=(page-1)*size
        //page=(index/size)+1
        List<StoreContent> storeContents = storeContentService.selectSuggestStore(categoryId, index, DEFAULT_SUGGEST_STORE_NUM);
        return ReplyResult.toJsonWithData(storeContents);
    }

    /**
     * @description: 添加推荐商品
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @PostMapping(value = "/addStore",produces = {"application/json;charset=UTF-8"})
    public String addStoreContent(@Validated StoreContent storeContent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ReplyResult.toJson(ReplyCode.ATTR_WRONG);
        }
        storeContentService.insert(storeContent);
        return ReplyResult.successJson();
    }

    /**
     * @description: 通过商品ID查询商品详情
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getStoreById",produces = {"application/json;charset=UTF-8"})
    public Object getStoreById(@RequestParam("id")Integer id) {
        StoreContent storeContent = new StoreContent();
        storeContent.setStoreContentId(id);
        EntityWrapper<StoreContent> entityWrapper = new EntityWrapper<>(storeContent);
        StoreContent result = storeContentService.selectOne(entityWrapper);
        return result;
    }

    /**
     * @param
     * @return
     * @description: 查询商城内容
     * @auther: finalcola-zyy
     */
    @RequestMapping(value = "/getList", produces = {"application/json;charset=UTF-8"})
    public Object getList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<StoreContent> contentPage = new Page<>(page, size);
        Page<StoreContent> result = storeContentService.selectPage(contentPage);
        List<StoreContent> records = result.getRecords();
        return records;
    }

    /**
     * @param
     * @return
     * @description: 获取商品列表，并且将结果通过类别分类
     * @auther: finalcola-zyy
     */
    @RequestMapping(value = "/getStoreContentGroupByCategory", produces = {"application/json;charset=UTF-8"})
    public String getStoreContentGroupByCategory(@RequestParam(value = "size", required = false) Integer size) {
        EntityWrapper<StoreContentCategory> wrapper = new EntityWrapper<>();
        wrapper.where("status", "1")
                .orderDesc(Arrays.asList("created"));
        List<StoreContentCategory> categories = storeContentCategoryService.selectList(wrapper);
        List<Integer> categoryIdList = categories.stream().map(StoreContentCategory::getStoreCategoryId).collect(Collectors.toList());

//        log.info("商品类别ID："+categories.toString());

        Map<Integer, List<StoreContent>> map = new HashMap<>(categories.size() * 2);
        StoreContent storeContent = new StoreContent();
        for (StoreContentCategory category : categories) {
            Integer storeCategoryId = category.getStoreCategoryId();

            storeContent.setStoreCategoryId(storeCategoryId);
            EntityWrapper<StoreContent> entityWrapper = new EntityWrapper<>(storeContent);
            List<StoreContent> storeContentList = storeContentService.selectPage(new Page<>(1, size), entityWrapper).getRecords();
            map.put(storeCategoryId, storeContentList);
        }

        StoreListVo storeListVo = new StoreListVo(categories, map);
        log.info("vo" + storeListVo.toString());
        return ReplyResult.toJsonWithData(storeListVo);
    }

    /**
     * @description: 获取指定类别的商品
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getListByCategory",produces = {"application/json;charset=UTF-8"})
    public Object getListByCategory(@RequestParam("category")String categoryName
            ,@RequestParam("page")Integer page,@RequestParam("size")Integer size) {
        StoreContentCategory storeContentCategory = new StoreContentCategory();
        storeContentCategory.setStatus(StoreConstant.CATEGORY_STATUS_SIMPLE)
                .setName(categoryName);
        EntityWrapper<StoreContentCategory> categoryEntityWrapper = new EntityWrapper<>();
        categoryEntityWrapper.setEntity(storeContentCategory);
        StoreContentCategory contentCategory = storeContentCategoryService.selectOne(categoryEntityWrapper);
        Integer categoryId = contentCategory.getStoreCategoryId();

        EntityWrapper<StoreContent> contentEntityWrapper = new EntityWrapper<>();
        StoreContent storeContent = new StoreContent().setStoreCategoryId(categoryId);
        contentEntityWrapper.setEntity(storeContent);
        Page<StoreContent> selectPage = storeContentService.selectPage(new Page<>(page, size), contentEntityWrapper);
        return selectPage.getRecords();
    }

    /**
     * @description: 获取商品类别列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getCategory",produces = {"application/json;charset=UTF-8"})
    public Object getCategory(@RequestParam(value = "size",required = false)Integer size) {
        EntityWrapper<StoreContentCategory> wrapper = new EntityWrapper<>();
        wrapper.where("status", "1")
                .orderDesc(Arrays.asList("created"));
        List<StoreContentCategory> categories = storeContentCategoryService.selectList(wrapper);
        categories.stream().sorted(Comparator.comparing(StoreContentCategory::getSortOrder));
        if (categories.size() > 30) {
            log.info("商城商品类别查询数量过多:" + categories.size());
        }

        LinkedList<CategoryWithDataVo> result = new LinkedList<>();
        StoreContent storeContentEntity = new StoreContent();
        size = (size == null) ? 5 : size;
        for (StoreContentCategory category : categories) {
            String categoryName = category.getName();
            Integer categoryId = category.getStoreCategoryId();
            storeContentEntity.setStoreCategoryId(categoryId);
            List<StoreContent> contentList =
                    storeContentService.selectPage(new Page<>(1, size), new EntityWrapper<>(storeContentEntity)).getRecords();
            result.add(new CategoryWithDataVo(categoryId, categoryName, contentList));
        }

        return ReplyResult.toJsonWithData(result);
    }

    /**
     * @description: 添加上传类别
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/addCategory",produces = {"application/json;charset=UTF-8"})
    public String addCategory(StoreContentCategory category) {
        Date now = new Date();
        category.setCreated(now);
        category.setUpdated(now);
        if (category.getSortOrder() == null) {
            category.setStatus(StoreConstant.CATEGORY_STATUS_SIMPLE);
        }
        storeContentCategoryService.insert(category);
        return ReplyResult.successJson();
    }


    /**
     * @description: 商品全文查询
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/search",produces = {"application/json;charset=UTF-8"})
    public Object search(@RequestParam("query")String query
            ,@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size) {
        try {
            SearchResult result = searchService.query(query, page, size);
            return ReplyResult.toJsonWithData(result);
        } catch (Exception e) {
            log.info("查询商品出现异常",e);
            return ReplyResult.toJson(ReplyCode.REQUEST_FAILED);
        }
    }

}
