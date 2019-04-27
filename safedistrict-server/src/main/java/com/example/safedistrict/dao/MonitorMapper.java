package com.example.safedistrict.dao;

import com.example.safedistrict.entity.Monitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonitorMapper {
    List<Monitor> selectMonitorByNumber(String areaNumber)  throws Exception;

    List<Monitor> selectMonitor();

    void saveMonitor(Monitor monitor);

    int updateByPrimaryKey(Monitor monitor);

    void deleteByPrimaryKey(int monitorId) throws Exception;
}