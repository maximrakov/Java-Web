package ru.itmo.webmail.model.domain;

import ru.itmo.webmail.model.service.UserService;

public class News {
    private UserService userService = new UserService();
    private int userID;
    private String message;

    public News(int userID, String message) {
        this.userID = userID;
        this.message = message;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setMessage(String text) {
        this.message = text;
    }

    public int getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }

    public String getUserName(){
        return userService.getNameById(userID);
    }
}
