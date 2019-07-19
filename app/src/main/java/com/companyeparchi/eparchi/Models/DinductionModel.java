package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DinductionModel
{
 @SerializedName("data")
    private ArrayList<InductionModel> data;

 @SerializedName("success")
    private Boolean success;

    @SerializedName("count")
    private Boolean count = false;

 @SerializedName("message")
    private String msg;


    public void setData(ArrayList<InductionModel> data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<InductionModel> getData() {
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

    public DinductionModel(ArrayList<InductionModel> data, Boolean success, Boolean count, String msg) {
        this.data = data;
        this.success = success;
        this.count = count;
        this.msg = msg;
    }
}
