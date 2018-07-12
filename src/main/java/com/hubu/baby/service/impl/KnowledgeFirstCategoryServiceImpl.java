package com.hubu.baby.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.Page;
import com.hubu.baby.entity.KnowledgeFirstCategory;
import com.hubu.baby.mapper.KnowledgeFirstCategoryMapper;
import com.hubu.baby.service.IKnowledgeFirstCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hubu.baby.vo.BabyResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@Service
public class KnowledgeFirstCategoryServiceImpl extends ServiceImpl<KnowledgeFirstCategoryMapper, KnowledgeFirstCategory> implements IKnowledgeFirstCategoryService {

    @Override
    public BabyResult getAllFirstCategory() {

        Wrapper<KnowledgeFirstCategory> wrapper = new EntityWrapper<>();
        try {
            List<KnowledgeFirstCategory> lists = this.selectList(wrapper);
            return BabyResult.ok(lists);
        } catch (Exception e) {
            e.printStackTrace();
            return BabyResult.build(400, "没有查询到一级分类");
        }

    }
}
