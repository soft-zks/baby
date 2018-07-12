package com.hubu.baby.mapper;

import com.hubu.baby.entity.QuestionReply;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface QuestionReplyMapper extends BaseMapper<QuestionReply> {

    void updateUserPicture(@Param("userId") Integer userId, @Param("path") String path);
}