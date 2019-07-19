package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class QualityModel
{
    @SerializedName("heat_no")
    private String heat;
    @SerializedName("batch_per_c")
    private String bpc;
    @SerializedName("per_c")
    private String perc;
    @SerializedName("per_si")
    private String persi;
    @SerializedName("per_mn")
    private String permn;
    @SerializedName("per_s")
    private String pers;
    @SerializedName("per_p")
    private String perp;
    @SerializedName("sample_type")
    private String sampletype;
    @SerializedName("furnace")
    private String furnace;

    public String getTmaterial() {
        return tmaterial;
    }

    public void setTmaterial(String tmaterial) {
        this.tmaterial = tmaterial;
    }

    @SerializedName("type_material")
    private String tmaterial;

    @SerializedName("shift")
    private String shift;
    @SerializedName("name")
    private String name;

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public void setBpc(String bpc) {
        this.bpc = bpc;
    }

    public void setPerc(String perc) {
        this.perc = perc;
    }

    public void setPersi(String persi) {
        this.persi = persi;
    }

    public void setPermn(String permn) {
        this.permn = permn;
    }

    public void setPers(String pers) {
        this.pers = pers;
    }

    public void setPerp(String perp) {
        this.perp = perp;
    }

    public void setSampletype(String sampletype) {
        this.sampletype = sampletype;
    }

    public void setFurnace(String furnace) {
        this.furnace = furnace;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPerp() {
        return perp;
    }

    public String getShift() {
        return shift;
    }

    public String getName() {
        return name;
    }

    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;




    public String getHeat() {
        return heat;
    }

    public String getBpc() {
        return bpc;
    }

    public String getPerc() {
        return perc;
    }

    public String getPersi() {
        return persi;
    }

    public String getPermn() {
        return permn;
    }

    public String getPers() {
        return pers;
    }

    public String getSampletype() {
        return sampletype;
    }

    public String getFurnace() {
        return furnace;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public QualityModel(String name,String shift,String perp,String heat, String bpc, String perc, String persi, String permn, String pers, String sampletype, String furnace) {
        this.shift = shift;
        this.name = name;
        this.perp = perp;
        this.heat = heat;
        this.bpc = bpc;
        this.perc = perc;
        this.persi = persi;
        this.permn = permn;
        this.pers = pers;
        this.sampletype = sampletype;
        this.furnace = furnace;
    }

    public QualityModel(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


}
