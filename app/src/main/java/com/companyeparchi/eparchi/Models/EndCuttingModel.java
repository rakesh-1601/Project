package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class EndCuttingModel
{
    @SerializedName("weight")
    private Integer weight;

    public EndCuttingModel(Integer weight, String start, String end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @SerializedName("time")
    private String time;
    @SerializedName("start")
    private String start;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @SerializedName("end")
    private String end;

    @SerializedName("real_time")
    private String rtime;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;
    @SerializedName("mill")
    private Integer mill;

    @SerializedName("shift")
    private String shift;
    @SerializedName("name")
    private String name;

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getWeight() {
        return weight;
    }



    public Integer getMill() {
        return mill;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMill(Integer mill) {
        this.mill = mill;
    }


    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getRtime() {
        return rtime;
    }


    public String getShift() {
        return shift;
    }

    public String getName() {
        return name;
    }

    public EndCuttingModel(String rtime, String name, String shift,Integer weight, String time, Integer mill) {
        this.rtime = rtime;
        this.name = name;
        this.shift = shift;
        this.weight = weight;
        this.time = time;
        this.mill = mill;
    }

    public EndCuttingModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
