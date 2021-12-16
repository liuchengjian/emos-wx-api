package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbMeeting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Mapper
public interface TbMeetingDao {
    //创建会议
    public int insertMeeting(TbMeeting entity);
    //会议列表
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);

}