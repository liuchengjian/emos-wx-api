package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbCity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TbCityDao {
    public String searchCode(String city);
}