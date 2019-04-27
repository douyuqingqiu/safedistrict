package com.example.safedistrict.service;

import com.example.safedistrict.entity.User;

import java.util.List;

public interface UserServer {
    int deleteByPrimaryKey(int userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectById(int userId);

    List<User> selectByPrimaryKey(String userName, String userPsd);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
