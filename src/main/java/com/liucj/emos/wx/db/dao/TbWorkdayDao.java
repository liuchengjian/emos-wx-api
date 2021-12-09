package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbWorkday;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Mapper
public interface TbWorkdayDao {
    public Integer searchTodayIsWorkday();
    //查询特殊工作日
    public ArrayList<String> searchWorkdayInRange(HashMap param);
}