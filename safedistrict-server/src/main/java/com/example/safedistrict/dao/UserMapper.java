package com.example.safedistrict.dao;

import com.example.safedistrict.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectById(int userId);

    List<User> selectByPrimaryKey(@Param("userName") String userName, @Param("userPsd") String userPsd);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}