package com.example.safedistrict.service.impl;


import com.example.safedistrict.dao.MonitorMapper;
import com.example.safedistrict.entity.Monitor;
import com.example.safedistrict.service.MonitorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MonitorServer")
public class MonitorServerImpl implements MonitorServer {

    @Autowired
    private MonitorMapper monitorMapper;


    @Override
    public List<Monitor> selectMonitorByNumber(String areaNumber) throws Exception{
        return monitorMapper.selectMonitorByNumber(areaNumber);
    }

    @Override
    public List<Monitor> selectMonitor() {
        return monitorMapper.selectMonitor();
    }


    @Override
    public void saveMonitor(Monitor monitor) {
        monitorMapper.saveMonitor(monitor);
    }

    @Override
    public int updateByPrimaryKey(Monitor monitor) {
        return monitorMapper.updateByPrimaryKey(monitor);
    }


    @Override
    public void deleteByPrimaryKey(int monitorId) throws Exception{
         monitorMapper.deleteByPrimaryKey(monitorId);
    }
}
