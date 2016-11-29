package com.example.administrator.quanmingzhibo.bean;

/**
 * Created by imp on 2016/11/29.
 */
public class DetailsBean {


    private String  author;
    private String summary;
    private String uri;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public DetailsBean(String author, String summary, String uri) {
        this.author = author;
        this.summary = summary;
        this.uri = uri;
    }

    public DetailsBean() {
    }
}
