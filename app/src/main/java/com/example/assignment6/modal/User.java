package com.example.assignment6.modal;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("username")
    String  userName;
    @SerializedName("userId")
    int userId;
    @SerializedName("title")
    String title;

    public User(int id, String name, String userName, int userId, String title) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}





