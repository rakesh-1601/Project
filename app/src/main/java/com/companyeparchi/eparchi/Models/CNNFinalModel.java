package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class CNNFinalModel {
    @SerializedName("real_off_time")
    private String rendtime;
    @SerializedName("real_on_time")
    private String rstarttime;

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    @SerializedName("name")
    private String name;
    @SerializedName("shift")
    private String shift;
    @SerializedName("id")
    private Integer id;



    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public String getName() {
        return name;
    }

    public String getShift() {
        return shift;
    }

    public String getReason() {
        return reason;
    }

    public CNNFinalModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CNNFinalModel(Integer id,String rendtime, String rstarttime, String name, String shift, String reason) {
        this.id = id;
        this.rendtime = rendtime;
        this.rstarttime = rstarttime;
        this.name = name;
        this.shift = shift;
        this.reason = reason;
    }

    @SerializedName("reason")
    private String reason;
}
