package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DendcuttingModel
{
    public void setData(ArrayList<EndCuttingModel> data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<EndCuttingModel> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Boolean getCount() {
        return count;
    }

    public String getMsg() {
        return msg;
    }

    public DendcuttingModel(ArrayList<EndCuttingModel> data, Boolean success, Boolean count, String msg) {
        this.data = data;
        this.success = success;
        this.count = count;
        this.msg = msg;
    }

    @SerializedName("data")
    private ArrayList<EndCuttingModel> data;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("count")
    private Boolean count = false;

    @SerializedName("message")
    private String msg;
}
