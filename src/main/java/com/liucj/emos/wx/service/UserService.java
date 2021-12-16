package com.liucj.emos.wx.service;

import com.liucj.emos.wx.db.pojo.TbUser;
import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.Set;

public interface UserService {
    //注册
    public  int registerUser(String registerCode,String code,String nickname,String photo);
    //查询用户权限
    public Set<String> searchUserPermissions(int userId);
    //登录
    public Integer login(String code);
    //查询用户
    public TbUser searchById(int userId);
    //查询用户入职日期
    public String searchUserHiredate(int userId);
    //查询用户摘要信息
    public HashMap searchUserSummary(int userId);
}
