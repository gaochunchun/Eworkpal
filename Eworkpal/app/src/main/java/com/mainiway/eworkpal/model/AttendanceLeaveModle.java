package com.mainiway.eworkpal.model;

import java.io.Serializable;

/**
 * Created by zhangbiao on 2016/12/15 00015.
 * 考勤-统计-请假列表-Modle
 */

public class AttendanceLeaveModle implements Serializable {
    private static final long serialVersionUID = -3132696156162935857L;
    public  String type;
    public  double duration;
    public String  startTime;
    public  String  endTime;

    public AttendanceLeaveModle(String type, double duration, String startTime, String endTime) {
        this.type = type;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
