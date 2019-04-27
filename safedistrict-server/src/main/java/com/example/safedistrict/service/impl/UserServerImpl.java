package com.example.safedistrict.service.impl;

import com.example.safedistrict.dao.UserMapper;
import com.example.safedistrict.entity.User;
import com.example.safedistrict.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServer")
public class UserServerImpl implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(int userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public List<User> selectById(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public List<User> selectByPrimaryKey(String userName, String userPsd) {
        return userMapper.selectByPrimaryKey(userName,userPsd);
    }


    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
