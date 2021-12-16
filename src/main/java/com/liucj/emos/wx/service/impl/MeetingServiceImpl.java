package com.liucj.emos.wx.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.liucj.emos.wx.db.dao.TbMeetingDao;
import com.liucj.emos.wx.db.dao.TbUserDao;
import com.liucj.emos.wx.db.pojo.TbMeeting;
import com.liucj.emos.wx.exception.EmosException;
import com.liucj.emos.wx.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private TbMeetingDao meetingDao;

    @Autowired
    private TbUserDao userDao;

    @Override
    public void insertMeeting(TbMeeting entity) {
        int row = meetingDao.insertMeeting(entity);
        if (row != 1) {
            throw new EmosException("会议添加失败");
        }
        //开启审批工作流
//        startMeetingWorkflow(entity.getUuid(), entity.getCreatorId().intValue(), entity.getDate(), entity.getStart());

    }

    @Override
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param) {
        ArrayList<HashMap> list = meetingDao.searchMyMeetingListByPage(param);
        String date = null;
        ArrayList resultList = new ArrayList();
        HashMap resultMap = null;
        JSONArray array = null;
        for (HashMap map : list) {
            String temp = map.get("date").toString();
            if (!temp.equals(date)) {
                date = temp;
                resultMap = new HashMap();
                resultMap.put("date", date);
                array = new JSONArray();
                resultMap.put("list", array);
                resultList.add(resultMap);
            }
            array.put(map);
        }
        return resultList;
    }

//    private void startMeetingWorkflow(String uuid, int creatorId, String date, String start) {
//        HashMap info = userDao.searchUserInfo(creatorId);
//        JSONObject json = new JSONObject();
//        json.set("url", recieveNotify);
//        json.set("uuid", uuid);
//        json.set("openId", info.get("openId"));
//        json.set("code", code);
//        json.set("date", date);
//        json.set("start", start);
//        String[] roles = info.get("roles").toString().split("，");
//        if (!ArrayUtil.contains(roles, "总经理")) {
//            Integer managerId = userDao.searchDeptManagerId(creatorId);
//            json.set("managerId", managerId);
//            Integer gmId = userDao.searchGmId();
//            json.set("gmId", gmId);
//            boolean bool = meetingDao.searchMeetingMembersInSameDept(uuid);
//            json.set("sameDept", bool);
//        }
//        String url = workflow + "/workflow/startMeetingProcess";
//        HttpResponse resp = HttpRequest.post(url).header("Content-Type", "application/json")
//                .body(json.toString()).execute();
//        if (resp.getStatus() == 200) {
//            json = JSONUtil.parseObj(resp.body());
//            String instanceId = json.getStr("instanceId");
//            HashMap param = new HashMap();
//            param.put("uuid", uuid);
//            param.put("instanceId", instanceId);
//            int row = meetingDao.updateMeetingInstanceId(param);
//            if (row != 1) {
//                throw new EmosException("保存会议工作流实例ID失败");
//            }
//        }
//    }
}
