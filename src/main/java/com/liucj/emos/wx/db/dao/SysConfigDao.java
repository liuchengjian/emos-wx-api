package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SysConfigDao {
    public List<SysConfig> selectAllParam();

}