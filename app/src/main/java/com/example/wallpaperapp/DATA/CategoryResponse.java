package com.example.wallpaperapp.DATA;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    String last_update;
    List<CategoryList> data;

    public CategoryResponse(String last_update, List<CategoryList> data) {
        this.last_update = last_update;
        this.data = data;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public List<CategoryList> getData() {
        return data;
    }

    public void setData(List<CategoryList> data) {
        this.data = data;
    }
}
