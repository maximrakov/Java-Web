package ru.itmo.webmail.model.domain;

import java.util.Date;

public class Article {
    private int id;
    private int userId;
    private String title;
    private String text;
    private Date creationTime;
    private boolean hide;

    public Article(int id, int userId, String title, String text, Date creationTime, boolean hide) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
