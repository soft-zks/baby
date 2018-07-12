package com.hubu.baby.service.impl;

import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.mapper.StoreContentMapper;
import com.hubu.baby.service.IStoreContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@Service
public class StoreContentServiceImpl extends ServiceImpl<StoreContentMapper, StoreContent> implements IStoreContentService {

    @Override
    public List<StoreContent> selectSuggestStore(Integer categoryId,int limit, int size) {
        List<StoreContent> storeContents = baseMapper.listWithLimit(categoryId, limit, size);
        return storeContents;
    }
}
