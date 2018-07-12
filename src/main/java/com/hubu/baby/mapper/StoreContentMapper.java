package com.hubu.baby.mapper;

import com.hubu.baby.entity.StoreContent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface StoreContentMapper extends BaseMapper<StoreContent> {

    List<StoreContent> listWithLimit(@Param("categoryId") Integer categoryId
            ,@Param("index") int index, @Param("size") int size);
}