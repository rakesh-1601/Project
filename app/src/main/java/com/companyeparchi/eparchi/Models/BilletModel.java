package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BilletModel {
    @SerializedName("start_time")
    private String stime;

    public BilletModel(String stime, String ttime, Integer blength, Integer bcount, String endtime, String remark, String shift, String rot, String roft, Integer furnace, String name, Integer id) {
        this.stime = stime;
        this.ttime = ttime;
        this.blength = blength;
        this.bcount = bcount;
        this.endtime = endtime;
        this.remark = remark;
        this.shift = shift;
        this.rot = rot;
        this.roft = roft;
        this.furnace = furnace;
        this.name = name;
        this.id = id;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getTtime() {
        return ttime;
    }

    @SerializedName("total_time")
    private String ttime;
    @SerializedName("billet_length")
    private Integer blength;
    @SerializedName("billet_count")
    private Integer bcount;
    @SerializedName("end_time")
    private String endtime;
    @SerializedName("remarks")
    private String remark;
    @SerializedName("shift")
    private String shift;
    @SerializedName("real_start_time")
    private String rot;
    @SerializedName("real_end_time")
    private String roft;
    @SerializedName("furnace")
    private Integer furnace;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private Integer id;

    @SerializedName("mill_stop")
    private ArrayList<MillStopModel> millstopdata;

    public ArrayList<MillStopModel> getMillstopdata() {
        return millstopdata;
    }

    public void setMillstopdata(ArrayList<MillStopModel> millstopdata) {
        this.millstopdata = millstopdata;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public BilletModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public String getStime() {
        return stime;
    }

    public Integer getBlength() {
        return blength;
    }

    public Integer getBcount() {
        return bcount;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getRemark() {
        return remark;
    }



    public BilletModel(Integer id,String rot,String roft,String name,String shift,Integer furnace,String stime, Integer blength, Integer bcount, String endtime, String remark) {
        this.id= id;
        this.name = name;
        this.shift = shift;
        this.rot = rot;
        this.roft = roft;
        this.furnace = furnace;
        this.stime = stime;
        this.blength = blength;
        this.bcount = bcount;
        this.endtime = endtime;
        this.remark = remark;
    }



    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;


    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setBlength(Integer blength) {
        this.blength = blength;
    }

    public void setBcount(Integer bcount) {
        this.bcount = bcount;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setRot(String rot) {
        this.rot = rot;
    }

    public void setRoft(String roft) {
        this.roft = roft;
    }

    public void setFurnace(Integer furnace) {
        this.furnace = furnace;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getShift() {
        return shift;
    }

    public String getRot() {
        return rot;
    }

    public String getRoft() {
        return roft;
    }

    public Integer getFurnace() {
        return furnace;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
