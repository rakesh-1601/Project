package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class Dnodetail {
    @SerializedName("1")
    private Integer no1;
    @SerializedName("2")
    private Integer no2;
    @SerializedName("3")
    private Integer no3;
    @SerializedName("4")
    private Integer no4;
    @SerializedName("5")
    private Integer no5;
    @SerializedName("6")
    private Integer no6;
    @SerializedName("7")
    private Integer no7;

    public Integer getNo7() {
        return no7;
    }

    public void setNo7(Integer no7) {
        this.no7 = no7;
    }

    public Integer getNo8() {
        return no8;
    }

    public void setNo8(Integer no8) {
        this.no8 = no8;
    }

    @SerializedName("8")
    private Integer no8;

    public Dnodetail(Integer no1, Integer no2, Integer no3, Integer no4, Integer no5, Integer no6,Integer no7,Integer no8) {
        this.no1 = no1;
        this.no2 = no2;
        this.no3 = no3;
        this.no4 = no4;
        this.no5 = no5;
        this.no6 = no6;
        this.no7 = no7;
        this.no8 = no8;
    }

    public Integer getNo1() {
        return no1;
    }

    public void setNo1(Integer no1) {
        this.no1 = no1;
    }

    public Integer getNo2() {
        return no2;
    }

    public void setNo2(Integer no2) {
        this.no2 = no2;
    }

    public Integer getNo3() {
        return no3;
    }

    public void setNo3(Integer no3) {
        this.no3 = no3;
    }

    public Integer getNo4() {
        return no4;
    }

    public void setNo4(Integer no4) {
        this.no4 = no4;
    }

    public Integer getNo5() {
        return no5;
    }

    public void setNo5(Integer no5) {
        this.no5 = no5;
    }

    public Integer getNo6() {
        return no6;
    }

    public void setNo6(Integer no6) {
        this.no6 = no6;
    }
}
