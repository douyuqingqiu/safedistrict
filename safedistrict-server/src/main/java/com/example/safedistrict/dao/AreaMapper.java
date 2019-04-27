package com.example.safedistrict.dao;

import com.example.safedistrict.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {
    List<Area> selectAllCity(int areaType);

    List<Area> getAreaBynumber(@Param("parentNumber") String parentNumber, @Param("areaType")int areaType);

    Area getAreaByKey(String areaNumber);

    void saveArea(Area area);

    void deleteByPrimaryKey(String areaNumber);
}