package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class CaseModel
{
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    @SerializedName("name")
    private String name;
    @SerializedName("shift")
    private String shift;

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

    public CaseModel(String name, String shift) {
        this.name = name;
        this.shift = shift;
    }

    public CaseModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
