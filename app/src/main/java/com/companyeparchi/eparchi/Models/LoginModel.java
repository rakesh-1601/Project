package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("case")
    private String mycase;


    public String isUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getMycase() {
        return mycase;
    }

    public LoginModel(boolean success, String msg, String token, String mycase) {
        this.success = success;
        this.msg = msg;
        this.token = token;
        this.mycase = mycase;
    }
}
