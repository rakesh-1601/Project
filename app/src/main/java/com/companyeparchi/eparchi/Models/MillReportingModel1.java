package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class MillReportingModel1
{
    @SerializedName("start_time")
    private String stime;
    @SerializedName("day_opening_meter")
    private Integer blength;
    @SerializedName("date")
    private String date;
    @SerializedName("material")
    private String material;
    @SerializedName("mill_type")
    private Integer milltype;
    @SerializedName("real_start_time")
    private String rot;
    @SerializedName("real_date")
    private String rdate;
    @SerializedName("name")
    private String name;
    @SerializedName("shift")
    private String shift;
    @SerializedName("id")
    private Integer id;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    public MillReportingModel1(Integer id, boolean success, String msg) {
        this.id = id;
        this.success = success;
        this.msg = msg;
    }

    public MillReportingModel1(String stime, Integer blength, String date, String material, Integer milltype, String rot, String rdate, String name, String shift) {
        this.stime = stime;
        this.blength = blength;
        this.date = date;
        this.material = material;
        this.milltype = milltype;
        this.rot = rot;
        this.rdate = rdate;
        this.name = name;
        this.shift = shift;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setBlength(Integer blength) {
        this.blength = blength;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setMilltype(Integer milltype) {
        this.milltype = milltype;
    }

    public void setRot(String rot) {
        this.rot = rot;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStime() {
        return stime;
    }

    public Integer getBlength() {
        return blength;
    }

    public String getDate() {
        return date;
    }

    public String getMaterial() {
        return material;
    }

    public Integer getMilltype() {
        return milltype;
    }

    public String getRot() {
        return rot;
    }

    public String getRdate() {
        return rdate;
    }

    public String getName() {
        return name;
    }

    public String getShift() {
        return shift;
    }

    public Integer getId() {
        return id;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }
}
