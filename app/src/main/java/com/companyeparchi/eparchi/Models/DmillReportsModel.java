package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DmillReportsModel {

    @SerializedName("head_data")
    private ArrayList<Dhdata> hdata;
    @SerializedName("mid_data")
    private ArrayList<ArrayList<MillreportsModel1>> mdata;
    @SerializedName("breakdown_data")
    private ArrayList<ArrayList<MillreportsModel1>> bdata;
    @SerializedName("no_detail")
    private ArrayList<Dnodetail> nodetail;
    @SerializedName("object_count")
    private String ocount;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("count")
    private Boolean count = false;

    public void setHdata(ArrayList<Dhdata> hdata) {
        this.hdata = hdata;
    }

    public void setMdata(ArrayList<ArrayList<MillreportsModel1>> mdata) {
        this.mdata = mdata;
    }

    public void setBdata(ArrayList<ArrayList<MillreportsModel1>> bdata) {
        this.bdata = bdata;
    }

    public void setNodetail(ArrayList<Dnodetail> nodetail) {
        this.nodetail = nodetail;
    }

    public void setOcount(String ocount) {
        this.ocount = ocount;
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

    public ArrayList<Dhdata> getHdata() {
        return hdata;
    }

    public ArrayList<ArrayList<MillreportsModel1>> getMdata() {
        return mdata;
    }

    public ArrayList<ArrayList<MillreportsModel1>> getBdata() {
        return bdata;
    }

    public ArrayList<Dnodetail> getNodetail() {
        return nodetail;
    }

    public String getOcount() {
        return ocount;
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

    public DmillReportsModel(ArrayList<Dhdata> hdata, ArrayList<ArrayList<MillreportsModel1>> mdata, ArrayList<ArrayList<MillreportsModel1>> bdata, ArrayList<Dnodetail> nodetail, String ocount, Boolean success, Boolean count, String msg) {
        this.hdata = hdata;
        this.mdata = mdata;
        this.bdata = bdata;
        this.nodetail = nodetail;
        this.ocount = ocount;
        this.success = success;
        this.count = count;
        this.msg = msg;
    }

    @SerializedName("message")
    private String msg;
}