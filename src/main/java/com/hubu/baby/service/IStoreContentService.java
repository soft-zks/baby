package com.hubu.baby.service;

import com.hubu.baby.entity.StoreContent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface IStoreContentService extends IService<StoreContent> {

    List<StoreContent> selectSuggestStore(Integer categoryId,int limit, int size);

}
