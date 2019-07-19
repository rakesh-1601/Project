package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class MillReportingModel4 {
    @SerializedName("raw_material")
    private Integer srm;

    @SerializedName("end_cutting")
    private Integer sec;

    @SerializedName("use_missroll")
    private Integer suse;

    @SerializedName("sale_missroll")
    private Integer ssm;

    @SerializedName("coal")
    private Integer soc;

    @SerializedName("day_closing_meter")
    private Integer dop;


    @SerializedName("start_time")
    private String stime;

    @SerializedName("real_start_time")
    private String rtime;

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

    public void setSrm(Integer srm) {
        this.srm = srm;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public void setSuse(Integer suse) {
        this.suse = suse;
    }

    public void setSsm(Integer ssm) {
        this.ssm = ssm;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public void setDop(Integer dop) {
        this.dop = dop;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
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

    public Integer getSrm() {
        return srm;
    }

    public Integer getSec() {
        return sec;
    }

    public Integer getSuse() {
        return suse;
    }

    public Integer getSsm() {
        return ssm;
    }

    public Integer getSoc() {
        return soc;
    }

    public Integer getDop() {
        return dop;
    }

    public String getStime() {
        return stime;
    }

    public String getRtime() {
        return rtime;
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

    public MillReportingModel4(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public MillReportingModel4(Integer srm, Integer sec, Integer suse, Integer ssm, Integer soc, Integer dop, String stime, String rtime, String name, String shift, Integer id) {
        this.srm = srm;
        this.sec = sec;
        this.suse = suse;
        this.ssm = ssm;
        this.soc = soc;
        this.dop = dop;
        this.stime = stime;
        this.rtime = rtime;
        this.name = name;
        this.shift = shift;
        this.id = id;
    }
}
