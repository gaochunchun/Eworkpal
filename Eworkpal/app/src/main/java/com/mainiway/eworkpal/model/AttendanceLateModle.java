package com.mainiway.eworkpal.model;

/**
 * Created by zhangbiao on 2016/12/16 00016.
 * 考勤-统计-迟到列表--适配器
 * 估计以后  迟到早退  未签到未签退 外勤设备异常  都用此适配器
 * 名字暂时不改了
 */

public class AttendanceLateModle {
    private  String  date;
    private String day_of_week;
    private  String time;
    private String address;
    private String  state;//打卡状态  迟到  早退  设备异常  外勤  设备异常等
    private String  type;//打卡类型  签到 签退
    private String appeal_state;//申诉状态  未申诉 申诉处理中 申诉被驳回

    public AttendanceLateModle(String date, String day_of_week, String time, String address, String state, String type, String appeal_state) {
        this.date = date;
        this.day_of_week = day_of_week;
        this.time = time;
        this.address = address;
        this.state = state;
        this.type = type;
        this.appeal_state = appeal_state;
    }

    public String getAppeal_state() {
        return appeal_state;
    }

    public void setAppeal_state(String appeal_state) {
        this.appeal_state = appeal_state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
