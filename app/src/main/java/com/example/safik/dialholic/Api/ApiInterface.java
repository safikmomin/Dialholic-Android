package com.example.safik.dialholic.Api;

import com.example.safik.dialholic.apidata.LoginData;
import com.example.safik.dialholic.apidata.ProfileData;
import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by safik on 1/11/2016.
 */
public interface ApiInterface {

    //interface for login
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/users/sign_in")
    Call<LoginData> login(@Body JsonObject bean);

    //interface for retreiving user data
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @GET("users/{id}.json")
    Call<ProfileData> profile(@Path("id") String id, @Header("X-User-Token") String token, @Header("X-User-Email") String email);
}