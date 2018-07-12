package com.hubu.baby.solr.dao.impl;

import com.hubu.baby.entity.Question;
import com.hubu.baby.service.IQuestionService;
import com.hubu.baby.solr.dao.QuestionSearchDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/5/11 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionSearchDaoImplTest {

    @Autowired
    private QuestionSearchDao questionSearchDao;

    @Autowired
    private IQuestionService questionService;

    @Test
    public void add() throws Exception {
        List<Question> questions = questionService.selectList(null);
        for (Question question : questions) {
            com.hubu.baby.solr.pojo.Question toSolr = new com.hubu.baby.solr.pojo.Question(question);
            System.out.println(toSolr);
            questionSearchDao.add(toSolr, false);
        }
    }

}