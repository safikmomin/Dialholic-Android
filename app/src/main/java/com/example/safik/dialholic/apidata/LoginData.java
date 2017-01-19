package com.example.safik.dialholic.apidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("authentication_token")
    @Expose
    private String authenticationToken;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginData() {
    }

    /**
     *
     * @param status
     * @param authenticationToken
     * @param user
     */
    public LoginData(User user, String status, String authenticationToken) {
        this.user = user;
        this.status = status;
        this.authenticationToken = authenticationToken;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The authenticationToken
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     *
     * @param authenticationToken
     * The authentication_token
     */
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

}