package com.hubu.baby.vo;

import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.entity.StoreContentCategory;

import java.util.List;

/**
 * 类别名称+类别下的商品
 *
 * @author: finalcola-zyy
 * @date: 2018/4/27 15:52
 */
public class CategoryWithDataVo {
    private Integer categoryId;
    private String category;
    private List<StoreContent> storeContentList;

    public CategoryWithDataVo(Integer categoryId, String category, List<StoreContent> storeContentList) {
        this.categoryId = categoryId;
        this.category = category;
        this.storeContentList = storeContentList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<StoreContent> getStoreContentList() {
        return storeContentList;
    }

    public void setStoreContentList(List<StoreContent> storeContentList) {
        this.storeContentList = storeContentList;
    }

    @Override
    public String toString() {
        return "CategoryWithDataVo{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", storeContentList=" + storeContentList +
                '}';
    }
}
