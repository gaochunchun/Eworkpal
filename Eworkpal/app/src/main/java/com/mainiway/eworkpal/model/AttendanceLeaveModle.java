package com.mainiway.eworkpal.model;

/**
 * Created by zhangbiao on 2016/12/15 00015.
 * 考勤-统计-请假列表-Modle
 */

public class AttendanceLeaveModle {
    private String type;
    private  double duration;
    private String  startTime;
    private  String  endTime;

    public AttendanceLeaveModle(String type, double duration, String startTime, String endTime) {
        this.type = type;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
