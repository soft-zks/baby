package com.hubu.baby.vo;

import com.hubu.baby.entity.StoreContent;
import com.hubu.baby.entity.StoreContentCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于商品展示主页中商品列表的VO
 *
 * @author: finalcola-zyy
 * @date: 2018/4/25 16:07
 */
public class StoreListVo {

    private List<Node> list;
    private Integer count;

    public StoreListVo(List<Node> data) {
        this.list = data;
        this.count = data.size();
    }

    public StoreListVo(List<StoreContentCategory> categoryList,Map<Integer,List<StoreContent>> storeContentMap) {
        list = new ArrayList<>(categoryList.size());
        for (StoreContentCategory storeContentCategory : categoryList) {
            Integer categoryId = storeContentCategory.getStoreCategoryId();
            String categoryName = storeContentCategory.getName();
            List<StoreContent> contentList = storeContentMap.get(categoryId);
            Node node = new Node(categoryName, contentList);
            list.add(node);
        }
        count = list.size();
    }

    @Override
    public String toString() {
        return "StoreListVo{" +
                "list=" + list.toString() +
                ", count=" + count +
                '}';
    }

    static class Node{
        private String name;
        private List<StoreContent> contentList;

        public Node(String name, List<StoreContent> contentList) {
            this.name = name;
            this.contentList = contentList;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", contentList=" + contentList +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<StoreContent> getContentList() {
            return contentList;
        }

        public void setContentList(List<StoreContent> contentList) {
            this.contentList = contentList;
        }
    }

}
