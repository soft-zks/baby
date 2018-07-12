package com.hubu.baby.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hubu.baby.entity.Question;
import com.hubu.baby.entity.QuestionReply;
import com.hubu.baby.mapper.QuestionMapper;
import com.hubu.baby.mapper.QuestionReplyMapper;
import com.hubu.baby.service.IQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionReplyMapper replyMapper;

    /**
     * 更新用户头像地址(并同时更新该用户回复的头像)
     *
     * @param userId
     * @param path
     */
    @Override
    public void updateUserPicture(Integer userId, String path) {
        baseMapper.updateUserPicture(userId,path);

        replyMapper.updateUserPicture(userId, path);
    }
}
