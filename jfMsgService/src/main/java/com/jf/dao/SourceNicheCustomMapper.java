package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface SourceNicheCustomMapper {

    /**
     *
     * @MethodName: getColumnPvHourStatistics
     * @Description: (资源位流量统计,每小时一次)
     * @author chengh
     * @date 2019年7月3日 下午2:46:00
     */
    public List<Map<String, Object>> getColumnPvHourStatistics(Map<String, String> map);


    List<Map<String, Object>> queryWeightsProduct(@Param("productTypeId") Integer productTypeId);

    List<Map<String, Object>> turntableProductList();

    void recycleTurntableProduct(@Param("recycleList") ArrayList recycleList);
}