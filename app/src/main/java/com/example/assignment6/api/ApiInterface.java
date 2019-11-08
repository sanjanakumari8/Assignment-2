package com.example.assignment6.api;

import com.example.assignment6.modal.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users")
    Call<ArrayList<User>>getUserList();
@GET("posts?userId=1")
    Call<ArrayList<User>>getUserPost();

}
