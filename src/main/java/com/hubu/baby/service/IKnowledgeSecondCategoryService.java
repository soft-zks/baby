package com.hubu.baby.service;

import com.hubu.baby.entity.KnowledgeSecondCategory;
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
public interface IKnowledgeSecondCategoryService extends IService<KnowledgeSecondCategory> {

    /**
     * 根据一级分类id查询二级分类信息
     *
     * @param firstId
     * @return
     */
    BabyResult getSecondCategoryById(Integer firstId);
}
