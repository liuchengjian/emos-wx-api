package com.liucj.emos.wx.service;

import com.liucj.emos.wx.db.pojo.TbMeeting;

import java.util.ArrayList;
import java.util.HashMap;

public interface MeetingService {
    public void insertMeeting(TbMeeting entity);
    //查询会议列表
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);
}
