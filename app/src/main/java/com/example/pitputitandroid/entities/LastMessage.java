package com.example.pitputitandroid.entities;

public class LastMessage {
    public LastMessage(String created, String content) {
        this.created = created;
        this.content = content;
    }
    public LastMessage(Message message) {
        this.created = message.getCreated();
        this.content = message.getContent();
    }
    protected String created;
    protected String content;
    private String id;
    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }
}
