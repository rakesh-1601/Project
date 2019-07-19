package com.companyeparchi.eparchi.Models;

import com.google.gson.annotations.SerializedName;

public class MillReportingModel2 {

    @SerializedName("start_time")
    private String stime;
    @SerializedName("end_time")
    private String endtime;
    @SerializedName("real_start_time")
    private String rot;
    @SerializedName("real_end_time")
    private String roft;
    @SerializedName("reason")
    private String reason;

    @SerializedName("length")
    private Integer length;
    @SerializedName("material_type")
    private String material;

    @SerializedName("no")
    private Integer no;

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getFlag() {
        return flag;
    }

    @SerializedName("flag")
    private Integer flag;

    @SerializedName("half_hour")
    private String half;
    @SerializedName("one_hour")
    private String one;

    @SerializedName("name")
    private String name;

    public MillReportingModel2(Integer count,Integer no,String name, String shift, Integer id) {
        this.count = count;
        this.no = no;
        this.name = name;
        this.shift = shift;
        this.id = id;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setRot(String rot) {
        this.rot = rot;
    }

    public void setRoft(String roft) {
        this.roft = roft;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStime() {
        return stime;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getRot() {
        return rot;
    }

    public String getRoft() {
        return roft;
    }

    public String getReason() {
        return reason;
    }

    public Integer getCount() {
        return count;
    }

    public MillReportingModel2(String stime, String endtime, String rot, String roft, String reason, String name, String shift, Integer id) {
        this.stime = stime;
        this.endtime = endtime;
        this.rot = rot;
        this.roft = roft;
        this.reason = reason;
        this.name = name;
        this.shift = shift;
        this.id = id;
    }

    @SerializedName("shift")
    private String shift;
    @SerializedName("id")
    private Integer id;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String msg;
    @SerializedName("count")
    private Integer count;

    public MillReportingModel2(Integer count,Integer length, String material, String name, String shift, Integer id) {
        this.count = count;
        this.length = length;
        this.material = material;
        this.name = name;
        this.shift = shift;
        this.id = id;
    }
    public MillReportingModel2(String value, String name, String shift, Integer id,Integer flag) {
        if(flag==1)
        {
            this.half = value;
        }
        else if(flag==2)
        {
            this.one = value;
        }
        this.name = name;
        this.shift = shift;
        this.id = id;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public void setHalf(String half) {
        this.half = half;
    }

    public void setOne(String one) {
        this.one = one;
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

    public Integer getLength() {
        return length;
    }

    public String getMaterial() {
        return material;
    }

    public Integer getNo() {
        return no;
    }

    public String getHalf() {
        return half;
    }

    public String getOne() {
        return one;
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
}
