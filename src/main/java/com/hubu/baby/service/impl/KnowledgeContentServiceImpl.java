package com.hubu.baby.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hubu.baby.dto.ListDataDto;
import com.hubu.baby.entity.KnowledgeContent;
import com.hubu.baby.mapper.KnowledgeContentMapper;
import com.hubu.baby.service.IKnowledgeContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hubu.baby.vo.BabyResult;
import com.hubu.baby.vo.DataWithCount;
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
public class KnowledgeContentServiceImpl extends ServiceImpl<KnowledgeContentMapper, KnowledgeContent> implements IKnowledgeContentService {

    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer DEFAULT_SIZE= 10;

    @Override
    public BabyResult getContentList(Integer secondId, Integer page, Integer size) {

        Wrapper<KnowledgeContent> wrapper = new EntityWrapper<>();
        wrapper.where("second_category_id={0}", secondId);
        this.selectList(wrapper);
        try {
            if (page == null || page <= 0) {
                page = DEFAULT_PAGE;
            }
            if (size == null || size <= 0) {
                size = DEFAULT_SIZE;
            }
            Page selectPage = this.selectPage(new Page<>(page, size), wrapper);
            int count = this.selectCount(wrapper);
            List records = selectPage.getRecords();
            return BabyResult.ok(new ListDataDto<>(records, count));
        } catch (Exception e) {
            e.printStackTrace();
            return BabyResult.build(400, "查询二级分类下的内容列表信息失败");
        }

    }

    @Override
    public BabyResult getContentById(Integer contentId) {

        KnowledgeContent knowledgeContent = this.selectById(contentId);
        if (knowledgeContent == null) {
            return BabyResult.build(400, "没查询到具体的内容");
        }
        return BabyResult.ok(knowledgeContent);

    }

    @Override
    public BabyResult getRandomContent(Integer secondId) {

        Wrapper<KnowledgeContent> wrapper = new EntityWrapper<>();
        wrapper.where("second_category_id={0}", secondId)
            .orderBy("rand()")
            .last("limit 2");
        List<KnowledgeContent> contentList = this.selectList(wrapper);
        if (contentList == null) {
            return BabyResult.build(400, "没随机查询到具体的内容");
        }
        return BabyResult.ok(contentList);
    }

    @Override
    public BabyResult getRecommend(Integer count) {

        Wrapper<KnowledgeContent> wrapper = new EntityWrapper<>();
        wrapper.orderBy("rand()")
                .last("limit " + count);
        List<KnowledgeContent> contentList = this.selectList(wrapper);
        if (contentList == null) {
            return BabyResult.build(400, "没查询到推荐的具体内容");
        }
        return BabyResult.ok(contentList);
    }
}
