package com.hubu.baby.mapper;

import com.hubu.baby.entity.StoreContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * ${Description}
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 15:06
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreContentMapperTest {

    @Autowired
    private StoreContentMapper storeContentMapper;

    public static final Random RANDOM = new Random();

    @Test
    public void insert() {
        int i = 3;
        StoreContent storeContent = new StoreContent();
        while (i < 100) {
            boolean nextBoolean = RANDOM.nextBoolean();
            int categoryId = (nextBoolean) ? 1 : 2;
            storeContent.setStoreCategoryId(categoryId);
            Date date = new Date();
            storeContent.setCreated(date).setUpdated(date);
            storeContent.setTitle("测试" + i);
            storeContent.setDescription("暂无更多介绍");
            storeContent.setPrice(BigDecimal.TEN);
            storeContentMapper.insert(storeContent);
            i++;
        }
    }

}