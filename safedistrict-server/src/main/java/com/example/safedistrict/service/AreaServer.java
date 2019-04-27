package com.example.safedistrict.service;


import com.example.safedistrict.entity.Adress;
import com.example.safedistrict.entity.Area;

import java.util.List;


public interface AreaServer {

    List<Area> getCity(int areaType);

    List<Area> getAreaBynumber(String parentNumber, int areaType);

    Area getAreaByKey(String areaNumber);

    void saveArea(Adress adress, String url, String areaId);



    void deleteByPrimaryKey(String areaNumber);
}
