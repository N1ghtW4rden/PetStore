package com.example.petshop;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private String status;

    public long getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(final List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}