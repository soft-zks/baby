package com.hubu.baby.service;

import com.hubu.baby.entity.KnowledgeFirstCategory;
import com.baomidou.mybatisplus.service.IService;
import com.hubu.baby.vo.BabyResult;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface IKnowledgeFirstCategoryService extends IService<KnowledgeFirstCategory> {

    /**
     * 获取一级分类
     *
     * @return
     */
    BabyResult getAllFirstCategory();

}
