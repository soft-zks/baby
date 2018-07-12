package com.hubu.baby.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hubu.baby.entity.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/21 20:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IQuestionServiceTest {

    private static final Logger log = LoggerFactory.getLogger(IQuestionServiceTest.class);

    @Autowired
    private IQuestionService questionService;

    @Test
    public void selectListTest(){
        List<Question> questionList = questionService.selectList(new EntityWrapper<>());
        questionList.forEach(System.out::println);
    }

}