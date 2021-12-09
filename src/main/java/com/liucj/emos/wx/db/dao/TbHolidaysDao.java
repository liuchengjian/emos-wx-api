package com.liucj.emos.wx.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Mapper
public interface TbHolidaysDao {
    public Integer searchTodayIsHolidays();
    //查询特殊节假日
    public ArrayList<String> searchHolidaysInRange(HashMap param);

}