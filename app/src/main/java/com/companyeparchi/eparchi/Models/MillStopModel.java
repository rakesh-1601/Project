package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class MillStopModel
{
    @SerializedName("m_real_start_time")
    private String mrot;
    @SerializedName("m_real_end_time")
    private String mroft;
    @SerializedName("m_start_time")
    private String mstime;
    @SerializedName("m_billet_length")
    private Integer mblength;
    @SerializedName("m_billet_count")
    private Integer mbcount;
    @SerializedName("m_end_time")
    private String mendtime;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;
    @SerializedName("id")
    private Integer id;

    public void setMrot(String mrot) {
        this.mrot = mrot;
    }

    public void setMroft(String mroft) {
        this.mroft = mroft;
    }

    public void setMstime(String mstime) {
        this.mstime = mstime;
    }

    public void setMblength(Integer mblength) {
        this.mblength = mblength;
    }

    public void setMbcount(Integer mbcount) {
        this.mbcount = mbcount;
    }

    public void setMendtime(String mendtime) {
        this.mendtime = mendtime;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMcuttype(String mcuttype) {
        this.mcuttype = mcuttype;
    }

    public Integer getId() {
        return id;
    }

    public MillStopModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public MillStopModel(Integer id,String mrot, String mroft, String mstime, Integer mblength, Integer mbcount, String mendtime, String mcuttype) {
        this.id = id;
        this.mrot = mrot;
        this.mroft = mroft;
        this.mstime = mstime;
        this.mblength = mblength;
        this.mbcount = mbcount;
        this.mendtime = mendtime;
        this.mcuttype = mcuttype;
    }

    public String getMrot() {
        return mrot;
    }

    public String getMroft() {
        return mroft;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public String getMstime() {
        return mstime;
    }

    public Integer getMblength() {
        return mblength;
    }

    public Integer getMbcount() {
        return mbcount;
    }

    public String getMendtime() {
        return mendtime;
    }

    public String getMcuttype() {
        return mcuttype;
    }


    @SerializedName("m_cut_type")
    private String mcuttype;
}
