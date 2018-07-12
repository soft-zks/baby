package com.hubu.baby.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/21 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class QuestionControllerTest {

    private static final Logger log = LoggerFactory.getLogger(QuestionControllerTest.class);

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getQuestionList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionoGroupByCategory")
                .param("page", "1")
                .param("size", "10");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void selectQuestionListByCategory() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionListByCategory")
                .param("categoryName","孕期饮食")
                .param("page", "1")
                .param("size", "10");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getCategoryList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getCategoryList");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getReply() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getReply")
                .param("questionId","1")
                .param("page","1")
                .param("size","10");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getLastQuestion() throws Exception {
        //getLastQuestion
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getLastQuestion")
                .param("page","1");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getQuestionGroupByCategory() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionGroupByCategory")
//                .param("size","5");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionGroupByCategory");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getQuestionById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionById")
                .param("questionId","1");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void addReply() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/addReply")
                .param("replyContent", "obaby~~~")
                .param("userId", "4")
                .param("questionId", "1");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void addQuestion() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/addQuestion")
                .param("questionTitle", "婴儿玩具推荐?~~~")
                .param("questionContent", "1岁的婴儿玩具有哪些值得推荐的")
                .param("questionCategoryName","玩具推荐")
                .param("userId", "3");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }

    @Test
    public void getQuestionListByUserId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/question/getQuestionByUserId")
//                .param("page", "1")
//                .param("size", "10")
                .param("userId", "4");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status, 200);
    }
}