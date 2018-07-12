package com.hubu.baby.vo;

import com.hubu.baby.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: finalcola-zyy
 * @date: 2018/4/28 18:25
 */
public class QuestionReplyVo {

    private List<Node> data;

    public QuestionReplyVo(Map<String,List<Question>> map) {
        data = new ArrayList<>(map.size());
        for (Map.Entry<String, List<Question>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Question> list = entry.getValue();
            Node node = new Node(key, list);
            data.add(node);
        }
    }

    public List<Node> getData() {
        return data;
    }

    public void setData(List<Node> data) {
        this.data = data;
    }

    static class Node{
        private String category;
        private List<Question> questionList;

        public Node(String category, List<Question> questionList) {
            this.category = category;
            this.questionList = questionList;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "category='" + category + '\'' +
                    ", questionList=" + questionList +
                    '}';
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<Question> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<Question> questionList) {
            this.questionList = questionList;
        }
    }

}
