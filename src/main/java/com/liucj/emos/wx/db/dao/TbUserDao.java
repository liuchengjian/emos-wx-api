package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbUser;
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
    //查询某个用户
    public TbUser searchById(int userId);

    public HashMap searchNameAndDept(int userId);
    //查询某个用户入职时间
    public String searchUserHiredate(int userId);
}