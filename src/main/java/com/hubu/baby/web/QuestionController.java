package com.hubu.baby.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hubu.baby.constant.QuestionConstant;
import com.hubu.baby.entity.Question;
import com.hubu.baby.entity.QuestionCategory;
import com.hubu.baby.entity.QuestionReply;
import com.hubu.baby.entity.User;
import com.hubu.baby.enums.ReplyCode;
import com.hubu.baby.service.IQuestionCategoryService;
import com.hubu.baby.service.IQuestionReplyService;
import com.hubu.baby.service.IQuestionService;
import com.hubu.baby.service.IUserService;
import com.hubu.baby.solr.pojo.SearchResult;
import com.hubu.baby.solr.service.QuestionSearchService;
import com.hubu.baby.vo.QuestionReplyVo;
import com.hubu.baby.vo.ReplyDetail;
import com.hubu.baby.vo.ReplyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *     论坛功能接口
 * </p>
 *
 * @auther: finalcola-zyy
 * @date: 2018/4/23 11:37
 */
@RestController
@RequestMapping("/baby/question")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionReplyService replyService;

    @Autowired
    private IQuestionCategoryService categoryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private QuestionSearchService questionSearchService;

    /**
     * @description: 添加问题
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/addQuestion",produces = {"application/json;charset=UTF-8"})
    public Object addQuestion(Question question) {
        /*
        查询用户头像
         */
        Integer userId = question.getUserId();
        User user = userService.selectById(userId);
        String picture = user.getPicture();

        /*
        查询问题类别
         */
        String questionCategoryName = question.getQuestionCategoryName();
        QuestionCategory questionCategory = new QuestionCategory().setQuestionCategoryName(questionCategoryName);
        QuestionCategory category = categoryService.selectOne(new EntityWrapper<>(questionCategory));
        if (category == null) {
            category = new QuestionCategory();
            category.setQuestionCategoryName(questionCategoryName)
                    .setDescription(questionCategoryName)
                    .setStatus(QuestionConstant.REPLY_STATUS_OK)
                    .setCreated(new Date())
                    .setUpdated(new Date());
            categoryService.insert(category);
        }
        Integer categoryId = category.getQuestionCategoryId();

        Date date = new Date();
        question.setCreated(date)
                .setUpdated(date)
                .setPicture(picture)
                .setQuestionCategoryId(categoryId)
                .setStatus(QuestionConstant.REPLY_STATUS_OK);
        questionService.insert(question);
        return ReplyResult.successJson();
    }
    
    /**
     * @description: 添加评论
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/addReply",produces = {"application/json;charset=UTF-8"})
    public Object addReply(QuestionReply reply) {

        /*
         查询用户头像
         */
        Integer userId = reply.getUserId();
        User user = userService.selectById(userId);
        String picture = user.getPicture();

        Date now = new Date();
        reply.setCreated(now)
                .setUpdated(now)
                .setPicture(picture)
                .setStatus(QuestionConstant.REPLY_STATUS_OK)
                .setParentId(QuestionConstant.REPLY_NO_PARENT);
        replyService.insert(reply);
        return ReplyResult.successJson();
    }

    /**
     * @description: 查询问题列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getQuestionoGroupByCategory",produces = {"application/json;charset=UTF-8"})
    public Object getQuestionList(@RequestParam("page")Integer page,@RequestParam("size")Integer size) {
        Page<Question> questionPage = questionService.selectPage(new Page<>(page, size));
        List<Question> questionList = questionPage.getRecords();
        Map<String, List<Question>> result = questionList.stream().collect(Collectors.groupingBy(Question::getQuestionCategoryName));
        return result;
    }

    /**
     * @description: 查询最新的size条记录
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getLastQuestion",produces = {"application/json;charset=UTF-8"})
    public Object getLastQuestion(@RequestParam("page")Integer page
            ,@RequestParam(value = "size",defaultValue = "10")Integer size) {
        Wrapper<Question> questionWrapper = new EntityWrapper<Question>().orderBy("created", false);
        Page<Question> selectPage = questionService.selectPage(new Page<>(page, size), questionWrapper);
        List<Question> questionList = selectPage.getRecords();
        return questionList;
    }

    /**
     * @description: 获取问题列表并且分类
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getQuestionGroupByCategory",produces = {"application/json;charset=UTF-8"})
    public Object getQuestionGroupByCategory(@RequestParam(value = "size",defaultValue = "10")Integer size) {
        List<QuestionCategory> list = categoryService.selectList(new EntityWrapper<>());
        HashMap<String, List<Question>> map = new HashMap<>(32);
        Question question = new Question();
        for (QuestionCategory category : list) {
            String categoryName = category.getQuestionCategoryName();
            Integer questionCategoryId = category.getQuestionCategoryId();
            question.setQuestionCategoryId(questionCategoryId);
            List<Question> questionList = questionService.selectPage(new Page<>(1, size), new EntityWrapper<>(question)).getRecords();
            map.put(categoryName, questionList);
        }
        QuestionReplyVo questionReplyVo = new QuestionReplyVo(map);
        return questionReplyVo;
    }

    /**
     * @param
     * @return
     * @description: 根据问题类别查询
     * @auther: finalcola-zyy
     */
    @RequestMapping(value = "/getQuestionListByCategory", produces = {"application/json;charset=UTF-8"})
    public Object getQuestionListByCategory(@RequestParam("categoryName") String categoryName
            , @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Question question = new Question();
        question.setQuestionCategoryName(categoryName);
        EntityWrapper<Question> entityWrapper = new EntityWrapper<>(question);
        Page<Question> questionPage = questionService.selectPage(new Page<>(page, size), entityWrapper);
        List<Question> questionList = questionPage.getRecords();
        return questionList;
    }

    /**
     * @description: 获取问题分类列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getCategoryList",produces = {"application/json;charset=UTF-8"})
    public Object getCategoryList() {
        List<QuestionCategory> questionList = categoryService.selectList(new EntityWrapper<>());
        return questionList;
    }

    /**
     * @description: 获取回复列表
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getReply",produces = {"application/json;charset=UTF-8"})
    public Object getReply(@RequestParam("questionId")Integer questionId
            , @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        LinkedList<ReplyDetail> replyDetails = new LinkedList<>();
        QuestionReply questionReply = new QuestionReply()
                .setQuestionId(questionId)
                .setParentId(QuestionConstant.DEFAULT_PARENT_ID);
        Page<QuestionReply> replyPage = replyService.selectPage(new Page<>(page, size), new EntityWrapper<>(questionReply));
        List<QuestionReply> replyList = replyPage.getRecords();
        for (QuestionReply parentReply : replyList) {
            Integer parentId = parentReply.getReplyId();
            QuestionReply conditionReply = new QuestionReply().setQuestionId(questionId)
                    .setParentId(parentId);
            EntityWrapper<QuestionReply> replyEntityWrapper = new EntityWrapper<>(conditionReply);
            replyEntityWrapper.orderAsc(Arrays.asList("created"));
            List<QuestionReply> subReplyList = replyService.selectList(replyEntityWrapper);
            replyDetails.add(new ReplyDetail(parentReply, subReplyList));
        }

        return replyDetails;
    }

    /**
     * @description: 根据ID查询问题详情
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getQuestionById",produces = {"application/json;charset=UTF-8"})
    public Object getQuestionById(@RequestParam("questionId")Integer questionId) {
        Question question = new Question().setQuestionId(questionId);
        EntityWrapper<Question> entityWrapper = new EntityWrapper<>(question);
        Question result = questionService.selectOne(entityWrapper);
        return result;
    }


    /**
     * @description: 全文检索
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/search",produces = {"application/json;charset=UTF-8"})
    public Object search(@RequestParam("query")String queryStr
            ,@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size) {
        try {
            SearchResult searchResult = questionSearchService.query(queryStr, page, size);
            return ReplyResult.toJsonWithData(searchResult);
        } catch (Exception e) {
            log.info("查询商品出现异常",e);
            return ReplyResult.toJson(ReplyCode.REQUEST_FAILED);
        }
    }

    /**
     * @description: 查询用户发表的问题
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/getQuestionByUserId",produces = {"application/json;charset=UTF-8"})
    public Object getQuestionByUserId(@RequestParam("userId")Integer userId
            ,@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size) {
        Question question = new Question().setUserId(userId);
        EntityWrapper<Question> entityWrapper = new EntityWrapper<>(question);
        List<Question> questionList = questionService.selectPage(new Page<>(page, size), entityWrapper).getRecords();
        return ReplyResult.toJsonWithData(questionList);
    }

}
