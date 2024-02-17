package org.example.models;


import jakarta.persistence.Entity;

import javax.validation.constraints.NotBlank;

@Entity
public class Rewards extends AbstractEntity {

    @NotBlank
    private String name;

    private String description;

    private String imageUrl; // Path or URL to the JPEG image

    // Constructors, getters, and setters

    public Rewards() {
    }

    public Rewards(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}