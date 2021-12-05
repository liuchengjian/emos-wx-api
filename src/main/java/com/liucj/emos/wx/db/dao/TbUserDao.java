package com.liucj.emos.wx.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@Mapper
public interface TbUserDao {
    //是否有管理员
    public boolean haveRootUser();
    //添加员工
    public int insert(HashMap param);

    public Integer searchIdByOpenId(String openId);
    //获取权限列表
    public Set<String> searchUserPermissions(int userId);
}