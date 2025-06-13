package com.pluralsight.models;

public class Category {
    private int id;
    private String CategoryName;

    public Category(int id, String CategoryName) {
        this.id = id;
        this.CategoryName = CategoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }
}
