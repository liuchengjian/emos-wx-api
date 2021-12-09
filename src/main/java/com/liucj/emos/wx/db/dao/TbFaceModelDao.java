package com.liucj.emos.wx.db.dao;

import com.liucj.emos.wx.db.pojo.TbFaceModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TbFaceModelDao {
    public String searchFaceModel(int userId);

    public void insert(TbFaceModel faceModel);

    public int deleteFaceModel(int userId);
}