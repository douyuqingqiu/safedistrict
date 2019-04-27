package com.example.safedistrict.controller;


import com.example.safedistrict.entity.Adress;
import com.example.safedistrict.entity.Area;
import com.example.safedistrict.entity.ResultInfo;
import com.example.safedistrict.service.AreaServer;
import com.example.safedistrict.util.ElantripConstants;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/Area")
public class AreaController {

    @Autowired
    private AreaServer areaServer;

    /*
     * 获取市信息
     *
     * */
    @RequestMapping("/getCityList")
    public Object getCityList() {

        ResultInfo rsInfo = new ResultInfo();
        List<Area> areaList = null;
        try {
            areaList = areaServer.getCity(1);
        } catch (Exception e) {
            rsInfo.setMessage("查询市信息异常！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        rsInfo.setCode("0");
        rsInfo.setTotal(areaList.size());
        rsInfo.setResult(areaList);
        rsInfo.setMessage("获取市信息成功！");
        return rsInfo;
    }

    /*
     * 获取地址信息
     *
     * */
    @RequestMapping("/getAddressList")
    public Object getAddressList() {
        ResultInfo rsInfo = new ResultInfo();
        List<Area> areaList = null;
        try {
            areaList = areaServer.getCity(6);
        } catch (Exception e) {
            rsInfo.setMessage("查询地址信息异常！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        rsInfo.setCode("0");
        rsInfo.setTotal(areaList.size());
        rsInfo.setResult(areaList);
        rsInfo.setMessage("获取地址信息成功！");
        return rsInfo;
    }

    /*
     * 获取区域信息
     *
     * */
    @RequestMapping("/getAreaList")
    public Object getAreaList(String parentNumber, int areaType) {
        ResultInfo rsInfo = new ResultInfo();
        String str = areaType+"";
        if ("".equals(str)) {
            rsInfo.setMessage("区域类型不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        if (StringUtil.isEmpty(parentNumber)) {
            rsInfo.setMessage("父级编号不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        List<Area> areaList = null;
        try {
            areaList = areaServer.getAreaBynumber(parentNumber, areaType);
        } catch (Exception e) {
            rsInfo.setMessage("查询区域信息异常！");
            rsInfo.setCode("-1");
            return rsInfo;
        }
        rsInfo.setCode("0");
        rsInfo.setTotal(areaList.size());
        rsInfo.setResult(areaList);
        rsInfo.setMessage("获取区域信息成功！");
        return rsInfo;
    }

    /*
     * 添加区域信息
     *
     * */
    @RequestMapping("/saveArea")
    public Object saveArea(Adress adress,String price, @RequestParam("file") MultipartFile file) {
        ResultInfo rsInfo = new ResultInfo();
        String path = ElantripConstants.UPLOAD_PATH;
        String url = null;
        if (file != null) {
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path,fileName);
            if (!targetFile.isDirectory()) {
                targetFile.mkdirs();
            }
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                rsInfo.setMessage("上传图片出错！!");
                rsInfo.setCode("23333");
            }
            url = ElantripConstants.UPLOAD_URL + fileName;
        }
        if (StringUtil.isEmpty(adress.getCity())) {
            rsInfo.setMessage("市不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getCounty())) {
            rsInfo.setMessage("县不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getDistrict())) {
            rsInfo.setMessage("区不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getStreet())) {
            rsInfo.setMessage("街道不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getPlot())) {
            rsInfo.setMessage("小区不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getMonitor())) {
            rsInfo.setMessage("小区监控不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else if (StringUtil.isEmpty(adress.getPrice())) {
            rsInfo.setMessage("观看价格不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else {
            try {
                System.out.println(adress.getPrice());
                areaServer.saveArea(adress,url,null);
                rsInfo.setCode("0");
                rsInfo.setMessage("上传区域信息成功！");
            }catch (Exception e){
                e.printStackTrace();
                rsInfo.setCode("-1");
                rsInfo.setMessage("上传区域信息失败！");
            }
        }
        return rsInfo;
    }


    /*
     * 删除区域信息
     *
     * */
    @RequestMapping("/deleteArea")
    public Object deleteArea(String areaNumber) {
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(areaNumber)) {
            rsInfo.setMessage("区域编号不能为空！");
            rsInfo.setCode("-1");
            return rsInfo;
        } else {
            try {
                areaServer.deleteByPrimaryKey(areaNumber);//删除
                rsInfo.setCode("0");
                rsInfo.setMessage("区域信息删除成功！");
            } catch (Exception e) {
                e.printStackTrace();
                rsInfo.setCode("-1");
                rsInfo.setMessage("区域信息删除失败！");
            }
        }
        return rsInfo;
    }

    /*
     * 修改区域信息
     *
     * */
    @RequestMapping("/updateArea")
    public Object updateArea(Adress adress, String areaNumber) {
        ResultInfo rsInfo = new ResultInfo();
        if (StringUtil.isEmpty(areaNumber)) {
                rsInfo.setMessage("区域id不能为空！");
                rsInfo.setCode("-1");
            return rsInfo;
        } else {
            try {
                Area area = areaServer.getAreaByKey(areaNumber);
                areaServer.deleteByPrimaryKey(areaNumber);
                areaServer.saveArea(adress,area.getImageDistrict(),String.valueOf(area.getAreaId()));
                rsInfo.setCode("0");
                rsInfo.setMessage("区域信息修改成功！");
            } catch (Exception e) {
                e.printStackTrace();
                rsInfo.setCode("-1");
                rsInfo.setMessage("区域信息修改失败！");
            }
        }
        return rsInfo;
    }
}

