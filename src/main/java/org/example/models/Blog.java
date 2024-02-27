package org.example.models;

import java.time.LocalDate;

public class Blog {

    private int id;
    private static int nextId;

    private String title;
    private String content;
    private LocalDate time;
    private String status;

    public Blog(String title, String content, String status) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = nextId;
        nextId++;
        this.time = LocalDate.now();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
