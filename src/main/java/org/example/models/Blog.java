package org.example.models;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class Blog {

    private int id;
    private static int nextId;
@NotBlank(message = "Title can not be blank.")
@Size
    private String title;
@NotBlank
    private String content;
    private LocalDate time;
    @NotBlank
    private String status;

    public Blog(String title, String content, String status) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.time = LocalDate.now();
        this.id = nextId;
        nextId++;

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
