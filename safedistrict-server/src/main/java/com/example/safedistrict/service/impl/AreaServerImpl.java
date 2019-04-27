package com.example.safedistrict.service.impl;


import com.example.safedistrict.dao.AreaMapper;
import com.example.safedistrict.entity.Adress;
import com.example.safedistrict.entity.Area;
import com.example.safedistrict.service.AreaServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaServer")
public class AreaServerImpl implements AreaServer {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getCity(int areaType)  {
        return areaMapper.selectAllCity(areaType);
    }

    @Override
    public List<Area> getAreaBynumber(String parentNumber, int areaType){
        return areaMapper.getAreaBynumber(parentNumber,areaType);
    }

    @Override
    public Area getAreaByKey(String areaNumber) {
        return areaMapper.getAreaByKey(areaNumber);
    }

    @Override
    public void saveArea(Adress adress, String url, String areaId) {
        String str = "中国";
        for (int i = 1; i <= 6; i++) {
            //添加
            Area area = new Area();
            switch (i) {
                case 1:
                    area.setAreaNumber(str+adress.getCity());
                    area.setAreaName(adress.getCity());
                    break;
                case 2:
                    str += adress.getCity();
                    area.setAreaNumber(str+adress.getDistrict());
                    area.setAreaName(adress.getDistrict());
                    break;
                case 3:
                    str += adress.getDistrict();
                    area.setAreaNumber(str+adress.getCounty());
                    area.setAreaName(adress.getCounty());
                    break;
                case 4:
                    str += adress.getCounty();
                    area.setAreaNumber(str+adress.getStreet());
                    area.setAreaName(adress.getStreet());
                    break;
                case 5:
                    str += adress.getStreet();
                    area.setAreaNumber(str+adress.getPlot());
                    area.setAreaName(adress.getPlot());
                    break;
                case 6:
                    str += adress.getPlot();
                    area.setAreaNumber(str+adress.getMonitor());
                    area.setAreaName(adress.getMonitor());
                    break;
            }

            //查询是否重复
            List<Area> areaList = areaMapper.getAreaBynumber(str, i);
            boolean b = true;
            if (areaList != null && areaList.size()>0){//有同级区域
                for (int j=0;j<areaList.size();j++){
                    if (areaList.get(j).getAreaName().equals(area.getAreaName())) {//如果同级区域名不同则添加
                        b = false;
                    }
                }
                if (b){
                    area.setParentNumber(str);
                    area.setAreaType(i);
                    if (i == 6) {
                        if (areaId != null && "".equals(areaId)){
                            area.setAreaId(Integer.parseInt(areaId));
                        }
                        area.setPrice(adress.getPrice());
                        area.setImageDistrict(url);
                    }
                    areaMapper.saveArea(area);
                }
            }else {
                area.setParentNumber(str);
                area.setAreaType(i);
                if (i == 6) {
                    area.setPrice(adress.getPrice());
                    area.setImageDistrict(url);
                }
                areaMapper.saveArea(area);
            }
        }
    }


    @Override
    public void deleteByPrimaryKey(String areaNumber){
        for (int i =6;i>=1;i--) {
            Area area = getAreaByKey(areaNumber);
            areaMapper.deleteByPrimaryKey(areaNumber);//删除
            List<Area> areaList = getAreaBynumber(area.getParentNumber(), i);
            areaNumber = area.getParentNumber();
            //存在子节点
            if (areaList != null && areaList.size() > 0) {
                break;
            }
        }
    }
}
