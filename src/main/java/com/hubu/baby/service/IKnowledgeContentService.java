package com.hubu.baby.service;

import com.hubu.baby.entity.KnowledgeContent;
import com.baomidou.mybatisplus.service.IService;
import com.hubu.baby.vo.BabyResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface IKnowledgeContentService extends IService<KnowledgeContent> {

    /**
     * 分页查询知识内容列表
     *
     * @param secondId
     * @param page
     * @param size
     * @return
     */
    BabyResult getContentList(Integer secondId, Integer page, Integer size);

    /**
     * 根据id查询文章详细内容
     *
     * @param contentId
     * @return
     */
    BabyResult getContentById(Integer contentId);

    /**
     * 随机获取两篇文章
     *
     * @param secondId
     * @return
     */
    BabyResult getRandomContent(Integer secondId);

    /**
     * 随机推荐
     *
     * @param count
     * @return
     */
    BabyResult getRecommend(Integer count);
}
