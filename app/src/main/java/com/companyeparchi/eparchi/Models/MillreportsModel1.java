package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class MillreportsModel1
{
    @SerializedName("time")
    private String time;
    @SerializedName("size")
    private String size;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("section_wt")
    private String sectwt;
    @SerializedName("quality_check")
    private String qcheck;
    @SerializedName("length")
    private String length;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @SerializedName("count")
    private String count;



    public void setTime(String time) {
        this.time = time;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSectwt(String sectwt) {
        this.sectwt = sectwt;
    }

    public void setQcheck(String qcheck) {
        this.qcheck = qcheck;
    }

    public String getTime() {
        return time;
    }

    public String getSize() {
        return size;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSectwt() {
        return sectwt;
    }

    public String getQcheck() {
        return qcheck;
    }

    public MillreportsModel1(String time, String size, String quantity, String length, String qcheck) {
        this.time = time;
        this.size = size;
        this.quantity = quantity;
        this.length = length;
        this.qcheck = qcheck;
    }

    @SerializedName("range")
    private String timebig;
    @SerializedName("time_reason")
    private String treason;
    @SerializedName("reason")
    private String reason;
    @SerializedName("flag")
    private Boolean flag;

    public void setTimebig(String timebig) {
        this.timebig = timebig;
    }

    public void setTreason(String treason) {
        this.treason = treason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getTimebig() {
        return timebig;
    }

    public String getTreason() {
        return treason;
    }

    public String getReason() {
        return reason;
    }

    public Boolean getFlag() {
        return flag;
    }

    public MillreportsModel1(String timebig, String treason, String reason, Boolean flag) {
        this.timebig = timebig;
        this.treason = treason;
        this.reason = reason;
        this.flag = flag;
    }
}
