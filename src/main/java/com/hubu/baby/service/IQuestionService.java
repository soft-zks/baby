package com.hubu.baby.service;

import com.hubu.baby.entity.Question;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 更新用户头像地址(并同时更新该用户回复的头像)
     * @param userId
     * @param path
     */
    void updateUserPicture(Integer userId, String path);
}
