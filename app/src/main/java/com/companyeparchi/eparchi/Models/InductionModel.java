package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class InductionModel
{
    @SerializedName("heat_no")
    private String heat;
    @SerializedName("on_time")
    private String ontime;
    @SerializedName("off_time")
    private String  offtime;
    @SerializedName("total_time")
    private String totaltime;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @SerializedName("remark")
    private String remark;

    @SerializedName("aluminium")
    private String  aluminum;

    public void setAluminum(String aluminum) {
        this.aluminum = aluminum;
    }

    public void setSilicon(String silicon) {
        this.silicon = silicon;
    }

    public String getAluminum() {
        return aluminum;
    }

    public String getSilicon() {
        return silicon;
    }

    @SerializedName("silicon")
    private String silicon;

    @SerializedName("furnace")
    private Integer furnace;

    @SerializedName("reason")
    private String reason;
    @SerializedName("pig")
    private Integer pig;
    @SerializedName("scrap")
    private Integer scrap;

    public String getReason() {
        return reason;
    }

    public String getRot() {
        return rot;
    }

    public String getRoft() {
        return roft;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public void setOntime(String ontime) {
        this.ontime = ontime;
    }

    public void setOfftime(String offtime) {
        this.offtime = offtime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public void setFurnace(Integer furnace) {
        this.furnace = furnace;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPig(Integer pig) {
        this.pig = pig;
    }

    public void setScrap(Integer scrap) {
        this.scrap = scrap;
    }

    public void setMn(Integer mn) {
        this.mn = mn;
    }

    public void setSfc(Integer sfc) {
        this.sfc = sfc;
    }

    public void setCpc(Integer cpc) {
        this.cpc = cpc;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRot(String rot) {
        this.rot = rot;
    }

    public void setRoft(String roft) {
        this.roft = roft;
    }

    public void setTaptemp(Integer taptemp) {
        this.taptemp = taptemp;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("m_n")
    private Integer mn;
    @SerializedName("sfc")
    private Integer sfc;
    @SerializedName("cpc")
    private Integer cpc;
    @SerializedName("shift")
    private String shift;


  //1.50 2.45
    @SerializedName("name")
    private String name;

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    @SerializedName("extra_time")
    private String etime;
    @SerializedName("real_on_time")
    private String rot;
    @SerializedName("real_off_time")
    private String roft;

    @SerializedName("tap_temperature")
    private Integer taptemp;
    @SerializedName("pf_factor")
    private Integer pf;

    public Integer getFurnace() {
        return furnace;
    }


    public String getHeat() {
        return heat;
    }

    public String getOntime() {
        return ontime;
    }

    public String getOfftime() {
        return offtime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public Integer getPig() {
        return pig;
    }

    public Integer getScrap() {
        return scrap;
    }

    public Integer getMn() {
        return mn;
    }

    public Integer getSfc() {
        return sfc;
    }

    public Integer getCpc() {
        return cpc;
    }


    public String getShift() {
        return shift;
    }

    public String getName() {
        return name;
    }

    public Integer getTaptemp() {
        return taptemp;
    }


    @SerializedName("red_flag")
    private boolean flag;

    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public Integer getPf() {
        return pf;
    }



    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public InductionModel(String reason, String rot, String roft, String name, Integer furnace, String heat, String ontime, String offtime, String totaltime, Integer pig, Integer scrap, Integer mn, Integer sfc, Integer cpc, String shift, Integer taptemp,String aluminium,String silicon,String remark) {
       this.reason = reason;
        this.rot = rot;
        this.roft = roft;
        this.name = name;
        this.heat = heat;
        this.ontime = ontime;
        this.offtime = offtime;
        this.totaltime = totaltime;
        this.pig = pig;
        this.scrap = scrap;
        this.mn = mn;
        this.sfc = sfc;
        this.cpc = cpc;
        this.shift = shift;
        this.furnace = furnace;
        this.taptemp = taptemp;
        this.aluminum = aluminium;
        this.silicon = silicon;
        this.remark = remark;
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


}
