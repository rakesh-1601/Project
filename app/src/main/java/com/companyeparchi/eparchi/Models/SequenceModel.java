package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class SequenceModel
{
    @SerializedName("sequence_no")
    private String sno;
    @SerializedName("start_kwah")
    private Integer skwah;
    @SerializedName("start_kvah")
    private Integer skvah;
    @SerializedName("start_time")
    private String starttime;
    @SerializedName("end_kwah")
    private Integer ekwah;
    @SerializedName("end_kvah")
    private Integer ekvah;
    @SerializedName("end_time")
    private String endtime;

    public String getShift() {
        return shift;
    }

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;

    @SerializedName("real_start_time")
    private String rot;
    @SerializedName("real_end_time")
    private String roft;


    @SerializedName("shift")
    private String shift;
    @SerializedName("furnace")
    private Integer furnace;
    @SerializedName("name")
    private String name;
    public Integer getFurnace() {
        return furnace;
    }

    public String getSno() {
        return sno;
    }

    public Integer getSkwah() {
        return skwah;
    }

    public Integer getSkvah() {
        return skvah;
    }

    public String getStarttime() {
        return starttime;
    }

    public Integer getEkwah() {
        return ekwah;
    }

    public Integer getEkvah() {
        return ekvah;
    }

    public String getEndtime() {
        return endtime;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public SequenceModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


    public SequenceModel(String rot,String roft,String name,String shift,Integer furnace,String sno, Integer skwah, Integer skvah, String starttime, Integer ekwah, Integer ekvah, String endtime) {
        this.name = name;
        this.shift = shift;
        this.rot = rot;
        this.roft = roft;
        this.furnace = furnace;
        this.sno = sno;
        this.skwah = skwah;
        this.skvah = skvah;
        this.starttime = starttime;
        this.ekwah = ekwah;
        this.ekvah = ekvah;
        this.endtime = endtime;
    }


}
