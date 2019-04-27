package com.example.safedistrict.service;


import com.example.safedistrict.entity.Monitor;

import java.util.List;


public interface MonitorServer {
    List<Monitor> selectMonitorByNumber(String areaNumber)  throws Exception;

    List<Monitor> selectMonitor();

    void saveMonitor(Monitor monitor);

    int updateByPrimaryKey(Monitor monitor);

    void deleteByPrimaryKey(int monitorId) throws Exception;
}
