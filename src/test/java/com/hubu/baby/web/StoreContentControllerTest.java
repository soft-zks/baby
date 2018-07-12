package com.hubu.baby.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/21 16:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class StoreContentControllerTest {
    private static final Logger log = LoggerFactory.getLogger(StoreContentControllerTest.class);

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getList")
                .param("page","1")
                .param("size","20");
        ResultActions resultActions = mvc.perform(requestBuilder);
        MvcResult mvcResult = resultActions.andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.info(String.valueOf(status));
        log.info(content);
        assertEquals(status,200);
    }

    @Test
    public void getCategoryList() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getCategory")
                .param("size","4");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        assertEquals(status, 200);
        log.info(content);
    }

    @Test
    public void getListByCategory() throws Exception {
        String categoryName = "育儿读物";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getListByCategory")
                .param("category", categoryName)
                .param("page", "1")
                .param("size", "20");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info(content);
        assertEquals(status,200);
    }

    @Test
    public void getStoreContentGroupByCategory() throws Exception {
        //getStoreContentGroupByCategory
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getStoreContentGroupByCategory")
                .param("size", "2");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info("返回结果:" + content);
        assertEquals(status,200);
    }

    @Test
    public void getStoreContentById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getStoreById")
                .param("id", "2");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info("返回结果:" + content);
        assertEquals(status,200);
    }

    //getRandomStore
    @Test
    public void getRandomStore() throws Exception {
        for (int i = 0; i < 5; i++) {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/getRandomSuggest")
                    .param("categoryId", "1");
            MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
            int status = response.getStatus();
            String content = response.getContentAsString();
            log.info("返回结果:" + content);
            assertEquals(status,200);
        }
    }

    @Test
    public void search() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/baby/storeContent/search")
                .param("query", "婴儿")
                .param("page","1")
                .param("size","10");
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        log.info("返回结果:" + content);
        assertEquals(status,200);
    }

}