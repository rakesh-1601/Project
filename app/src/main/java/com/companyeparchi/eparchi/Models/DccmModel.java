package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DccmModel {

    @SerializedName("data")
    private ArrayList<CNNFormModel> data;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("count")
    private Boolean count = false;

    @SerializedName("message")
    private String msg;

    @SerializedName("sequence_no")
    private String sno;

    @SerializedName("reason")
    private String reason;

    @SerializedName("turn_dish_temperature")
    private Integer tdt;

    @SerializedName("cc_size")
    private String csize;



    @SerializedName("total_heat")
    private String theat;

    public ArrayList<CNNFormModel> getData() {
        return data;
    }

    public void setData(ArrayList<CNNFormModel> data) {
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

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getTdt() {
        return tdt;
    }

    public void setTdt(Integer tdt) {
        this.tdt = tdt;
    }

    public String getCsize() {
        return csize;
    }

    public void setCsize(String csize) {
        this.csize = csize;
    }

    public String getTheat() {
        return theat;
    }

    public void setTheat(String theat) {
        this.theat = theat;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @SerializedName("average_time")
    private String atime;



    @SerializedName("total_time")
    private String ttime;


    @SerializedName("no")
    private Integer no;

}
