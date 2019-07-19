package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class CNNFormModel
{
    @SerializedName("heat_no")
    private Integer heat;
    @SerializedName("cast_on_time")
    private String cot;
    @SerializedName("laddle_temperature")
    private Integer ltemp;
    @SerializedName("laddle_weight")
    private Integer lweight;
    @SerializedName("cc_size_1")
    private String csize1;

    @SerializedName("cc_size_2")
    private String csize2;

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public void setCot(String cot) {
        this.cot = cot;
    }

    public void setLtemp(Integer ltemp) {
        this.ltemp = ltemp;
    }

    public void setLweight(Integer lweight) {
        this.lweight = lweight;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setTdt(Integer tdt) {
        this.tdt = tdt;
    }

    public void setRendtime(String rendtime) {
        this.rendtime = rendtime;
    }

    public void setRstarttime(String rstarttime) {
        this.rstarttime = rstarttime;
    }

    public void setLno(String lno) {
        this.lno = lno;
    }

    public void setFurnace(Integer furnace) {
        this.furnace = furnace;
    }

    public void setShift(String shift) {
        this.shift = shift;
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

    public Integer getHeat() {
        return heat;
    }

    public String getCot() {
        return cot;
    }

    public Integer getLtemp() {
        return ltemp;
    }

    public Integer getLweight() {
        return lweight;
    }

    public void setCsize1(String csize1) {
        this.csize1 = csize1;
    }

    public String getCsize2() {
        return csize2;
    }

    public void setCsize2(String csize2) {
        this.csize2 = csize2;
    }

    public String getCsize1() {
        return csize1;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public Integer getTdt() {
        return tdt;
    }

    public String getRendtime() {
        return rendtime;
    }

    public String getRstarttime() {
        return rstarttime;
    }

    public String getLno() {
        return lno;
    }

    public Integer getFurnace() {
        return furnace;
    }

    public String getShift() {
        return shift;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @SerializedName("start_time")
    private String starttime;
    @SerializedName("end_time")
    private String endtime;
    @SerializedName("turn_dish_temperature")
    private Integer tdt;
    @SerializedName("real_end_time")
    private String rendtime;
    @SerializedName("real_start_time")
    private String rstarttime;
    @SerializedName("laddle_no")
    private String lno;

    public CNNFormModel(Integer heat, Integer ltemp, Integer lweight, String starttime, String endtime) {
        this.heat = heat;
        this.ltemp = ltemp;
        this.lweight = lweight;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @SerializedName("reason")
    private String reason;

    @SerializedName("furnace")
    private Integer furnace;

    @SerializedName("shift")
    private String shift;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private Integer id;

    public CNNFormModel(String csize1,String csize2,Integer id,Integer heat, String cot, Integer ltemp, Integer lweight,  String endtime, Integer tdt, String rendtime, String rstarttime, String lno, Integer furnace, String shift, String name) {
        this.csize1 = csize1;
        this.csize2 = csize2;
        this.id = id;
        this.heat = heat;
        this.cot = cot;
        this.ltemp = ltemp;
        this.lweight = lweight;
        this.endtime = endtime;
        this.tdt = tdt;
        this.rendtime = rendtime;
        this.rstarttime = rstarttime;
        this.lno = lno;
        this.furnace = furnace;
        this.shift = shift;
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }



    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    public CNNFormModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
