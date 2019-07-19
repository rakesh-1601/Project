package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DccmnewModel {

    @SerializedName("data")
    private ArrayList<DccmModel> data;

    @SerializedName("success")
    private Boolean success;

    public ArrayList<DccmModel> getData() {
        return data;
    }

    public void setData(ArrayList<DccmModel> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("count")
    private Boolean count = false;

    @SerializedName("message")
    private String msg;
}