package ru.itmo.webmail.model.domain;

public class MessageView {
    public String name;
    public String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageView(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
