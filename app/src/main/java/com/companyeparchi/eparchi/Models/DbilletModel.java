package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DbilletModel {

    public void setData(ArrayList<BilletModel> data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<BilletModel> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public Boolean getCount() {
        return count;
    }

    public DbilletModel(ArrayList<BilletModel> data, Boolean success, Boolean count, String msg) {
        this.data = data;
        this.success = success;
        this.count = count;
        this.msg = msg;
    }

    @SerializedName("data")
    private ArrayList<BilletModel> data;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("count")
    private Boolean count = false;

    @SerializedName("message")
    private String msg;
}
