package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Blog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static int nextId;
@NotBlank(message = "Title can not be blank.")
//@Size
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
        this.id = (long) nextId;
        nextId++;

    }

    public Blog() {

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
        return Math.toIntExact(id);
    }


}
