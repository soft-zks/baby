package com.hubu.baby.mapper;

import com.hubu.baby.entity.Question;
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
public interface QuestionMapper extends BaseMapper<Question> {

    void updateUserPicture(@Param("userId") Integer userId, @Param("path") String path);
}