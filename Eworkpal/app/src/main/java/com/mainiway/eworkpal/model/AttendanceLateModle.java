package com.mainiway.eworkpal.model;

import com.mainiway.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by zhangbiao on 2016/12/16 00016.
 * 考勤-统计-迟到列表--适配器
 * 估计以后  迟到早退  未签到未签退 外勤设备异常  都用此适配器
 * 名字暂时不改了
 */

public class AttendanceLateModle implements Serializable ,MultiItemEntity {
    private static final long serialVersionUID = 2591223968426329498L;
    public  String  date;
    public String day_of_week;
    public  String time;
    public String address;
    public int  state;//打卡状态  迟到  早退  设备异常  外勤  设备异常等
    public String  type;//打卡类型  签到 签退
    public String appeal_state;//申诉状态  未申诉 申诉处理中 申诉被驳回
    public String imag_url;
    public String field_matter;//外勤事由
    public String equipment_type;//设备型号

    public AttendanceLateModle(String date, String day_of_week, String time, String address, int state, String type, String appeal_state) {
        this.date = date;
        this.day_of_week = day_of_week;
        this.time = time;
        this.address = address;
        this.state = state;
        this.type = type;
        this.appeal_state = appeal_state;
    }


    @Override
    public int getItemType() {
        return state;
    }
}
