package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class Dhdata {
    @SerializedName("blower_start_time")
    private String bstime;
    @SerializedName("blower_end_time")
    private String bendtime;
    @SerializedName("mill_start_time")
    private String mstime;
    @SerializedName("date")
    private String date;

    public Dhdata(String bstime, String bendtime, String mstime, String date) {
        this.bstime = bstime;
        this.bendtime = bendtime;
        this.mstime = mstime;
        this.date = date;
    }

    public String getBstime() {
        return bstime;
    }

    public void setBstime(String bstime) {
        this.bstime = bstime;
    }

    public String getBendtime() {
        return bendtime;
    }

    public void setBendtime(String bendtime) {
        this.bendtime = bendtime;
    }

    public String getMstime() {
        return mstime;
    }

    public void setMstime(String mstime) {
        this.mstime = mstime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
