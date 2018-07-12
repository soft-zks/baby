package com.hubu.baby.constant;

/**
 * @Description: 微信常量
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-31 09:42
 * @Since: version 1.0
 **/
public final class WxConstant {

    public static final String APPID = "your appid";
    public static final String APPSECRET = "your appsecret";

    //获取token
    public static final String ACCESS_TOKEN_BASE_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    //允许的范围
    public static final String SCOPE = "snsapi_userinfo";
    //token
    public static final String TOKEN = "your token";

    private WxConstant(){}


}
