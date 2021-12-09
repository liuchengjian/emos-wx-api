package com.liucj.emos.wx.service;

import org.apache.shiro.crypto.hash.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface CheckinService {
    //校验用户今天是否可以签到
    public  String validCanCheckIn(int userId,String date);
    //签到
    public void checkin(HashMap param);
    //创建人脸识别模型
    public void createFaceModel(int userId,String path);
    //查询当天签到情况
    public HashMap searchTodayCheckin(int userId);
    //考勤总天数
    public long searchCheckinDays(int userId);
    //查询本周签到天数
    public ArrayList<HashMap> searchWeekCheckin(HashMap param);
}
