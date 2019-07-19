package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DqualityModel
{
    @SerializedName("data")
    private ArrayList<QualityModel> data;

    public void setCount(Boolean count) {
        this.count = count;
    }

    @SerializedName("success")
    private Boolean success;

    @SerializedName("count")
    private Boolean count = false;

    @SerializedName("message")
    private String msg;

    public void setData(ArrayList<QualityModel> data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<QualityModel> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getCount() {
        return count;
    }

    public DqualityModel(Boolean count, ArrayList<QualityModel> data, Boolean success, String msg) {
        this.count = count;
        this.data = data;
        this.success = success;
        this.msg = msg;
    }
}

