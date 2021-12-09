package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbCheckin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
@Mapper
public interface TbCheckinDao {
    //是否有签到
    public Integer haveCheckin(HashMap param);
    //插入签到
    public void insert(TbCheckin checkin);
    //查询当天签到情况
    public HashMap searchTodayCheckin(int userId);
    //查询签到天数
    public long searchCheckinDays(int userId);
    //查询本周签到天数
    public ArrayList<HashMap> searchWeekCheckin(HashMap param);
}