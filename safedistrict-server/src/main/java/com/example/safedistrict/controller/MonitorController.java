package com.example.safedistrict.controller;


import com.example.safedistrict.entity.Monitor;
import com.example.safedistrict.entity.ResultInfo;
import com.example.safedistrict.service.MonitorServer;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Monitor")
public class MonitorController {

    @Autowired
    private MonitorServer monitorServer;

    /*
    * 获取该区域的监控列表
    *
    * */
    @RequestMapping("/getMonitorList")
    public Object getMonitorList(String areaNumber){
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(areaNumber)){
            rsInfo.setMessage("区域编号不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        List<Monitor> monitorList = null;
        try {
            monitorList = monitorServer.selectMonitorByNumber(areaNumber);
        }catch (Exception e) {
            rsInfo.setMessage("查询监控信息异常！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        rsInfo.setCode("0");
        rsInfo.setTotal(monitorList.size());
        rsInfo.setResult(monitorList);
        rsInfo.setMessage("获取监控区域信息成功！");
        return rsInfo;
    }

    /*
     * 获取所有监控信息
     *
     * */
    @RequestMapping("/getallMonitor")
    public Object getallMonitor(){
        ResultInfo rsInfo = new ResultInfo();

        List<Monitor> list = null;
        try {
            list = monitorServer.selectMonitor();
        } catch (Exception e) {
            rsInfo.setMessage("查询监控信息异常！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        rsInfo.setCode("0");
        rsInfo.setTotal(list.size());
        rsInfo.setResult(list);
        rsInfo.setMessage("获取监控信息成功！");
        return rsInfo;
    }

    /*
    * 添加监控信息
    *
    * */
    @RequestMapping("/saveMonitor")
    public Object saveMonitor(Monitor monitor){
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(monitor.getAreaNumber())){
            rsInfo.setMessage("监控区域编号不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(monitor.getMonitorAddress())){
            rsInfo.setMessage("监控地址不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(monitor.getMonitorName())){
            rsInfo.setMessage("监控名称不能为空！");
            rsInfo.setCode("-1");
        }else {
            monitor.setAreaNumber("中国"+monitor.getAreaNumber());
            monitorServer.saveMonitor(monitor);
            rsInfo.setCode("0");
            rsInfo.setMessage("监控信息添加成功！");
        }
        return rsInfo;
    }


    /*
     * 修改监控信息
     *
     * */
    @RequestMapping("/updateMonitor")
    public Object updateMonitor(Monitor monitor){
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(monitor.getAreaNumber())){
            rsInfo.setMessage("监控区域编号不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(monitor.getMonitorAddress())){
            rsInfo.setMessage("监控地址不能为空！");
            rsInfo.setCode("-1");
        }else if (StringUtil.isEmpty(monitor.getMonitorName())){
            rsInfo.setMessage("监控名称不能为空！");
            rsInfo.setCode("-1");
        }else {
            monitor.setAreaNumber("中国"+monitor.getAreaNumber());
            monitorServer.updateByPrimaryKey(monitor);
            rsInfo.setCode("0");
            rsInfo.setMessage("监控信息修改成功！");
        }
        return rsInfo;
    }

    /*
     * 删除监控信息
     *
     * */
    @RequestMapping("/deleteMonitor")
    public Object deleteMonitor(int monitorId){
        ResultInfo rsInfo = new ResultInfo();
        String str = monitorId + "";
        if (StringUtil.isEmpty(str)){
            rsInfo.setMessage("监控区域ID不能为空！");
            rsInfo.setCode("-1");
        }else {
            try {
                monitorServer.deleteByPrimaryKey(monitorId);
                rsInfo.setCode("0");
                rsInfo.setMessage("监控信息删除成功！");
            }catch (Exception e){
                e.printStackTrace();
                rsInfo.setCode("-1");
                rsInfo.setMessage("监控信息删除失败！");
            }

        }
        return rsInfo;
    }
}
