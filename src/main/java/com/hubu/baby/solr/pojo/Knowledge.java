package com.hubu.baby.solr.pojo;


/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-26 14:05
 * @Since: version 1.0
 **/
public class Knowledge {

    private String id;
    private String knowledge_content_id;
    private String second_category_id;
    private String know_title;
    private String know_url;
    private String know_picture;
    private String know_content;

    public Knowledge() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledge_content_id() {
        return knowledge_content_id;
    }

    public void setKnowledge_content_id(String knowledge_content_id) {
        this.knowledge_content_id = knowledge_content_id;
    }

    public String getSecond_category_id() {
        return second_category_id;
    }

    public void setSecond_category_id(String second_category_id) {
        this.second_category_id = second_category_id;
    }

    public String getKnow_title() {
        return know_title;
    }

    public void setKnow_title(String know_title) {
        this.know_title = know_title;
    }

    public String getKnow_url() {
        return know_url;
    }

    public void setKnow_url(String know_url) {
        this.know_url = know_url;
    }

    public String getKnow_picture() {
        return know_picture;
    }

    public void setKnow_picture(String know_picture) {
        this.know_picture = know_picture;
    }

    public String getKnow_content() {
        return know_content;
    }

    public void setKnow_content(String know_content) {
        this.know_content = know_content;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id='" + id + '\'' +
                ", knowledge_content_id='" + knowledge_content_id + '\'' +
                ", second_category_id='" + second_category_id + '\'' +
                ", know_title='" + know_title + '\'' +
                ", know_url='" + know_url + '\'' +
                ", know_picture='" + know_picture + '\'' +
                ", know_content='" + know_content + '\'' +
                '}';
    }
}
