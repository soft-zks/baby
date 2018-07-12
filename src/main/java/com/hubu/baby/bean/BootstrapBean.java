package com.hubu.baby.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 启动容器时的bean
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-22 20:05
 * @Since: version 1.0
 **/
@Configuration
public class BootstrapBean {

    /**
     * 保存用户信息
     *
     * @return
     */
    @Bean
    Map<String, Object> userMap() {
        return new HashMap<>();
    }

}
