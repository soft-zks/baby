package com.hubu.baby.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hubu.baby.entity.KnowledgeSecondCategory;
import com.hubu.baby.mapper.KnowledgeSecondCategoryMapper;
import com.hubu.baby.service.IKnowledgeSecondCategoryService;
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
public class KnowledgeSecondCategoryServiceImpl extends ServiceImpl<KnowledgeSecondCategoryMapper, KnowledgeSecondCategory> implements IKnowledgeSecondCategoryService {

    @Override
    public BabyResult getSecondCategoryById(Integer firstId) {

        Wrapper<KnowledgeSecondCategory> wrapper = new EntityWrapper<>();

        try {
            wrapper.where("first_category_id={0}", firstId);
            List<KnowledgeSecondCategory> lists = this.selectList(wrapper);
            return BabyResult.ok(lists);
        } catch (Exception e) {
            e.printStackTrace();
            return BabyResult.build(400, "查询二级分类信息失败");
        }

    }
}
