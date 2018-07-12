package com.hubu.baby.constant;

/**
 * 论坛相关的常量
 *
 * @author: finalcola-zyy
 * @date: 2018/4/23 16:03
 */
public class QuestionConstant {

    /**
     * 标志当前回复为最上层回复，parentId的默认值
     */
    public static final int DEFAULT_PARENT_ID = -1;

    /**
     * 回复状态：1：OK，0：停用
     */
    public static final String REPLY_STATUS_OK = "1";

    /**
     * 回复状态
     */
    public static final String REPLY_STATUS_LOCKED = "0";

    /**
     * 回复记录为一级回复，没有父级回复
     */
    public static final int REPLY_NO_PARENT = -1;


}
