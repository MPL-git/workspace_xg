package com.jf.dao;

import com.jf.entity.ProductDataStatistics;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MemberPvCustomMapper {

    /**
     * 类目数据(有好货)
     * @param paramMap
     * @return
     */
    List<ProductDataStatistics> getCategoryProductDataStatisticsList(HashMap<String, Object> paramMap);

    /**
     * 小时数据(有好货)
     * @param paramMap
     * @return
     */
    List<ProductDataStatistics> getHourProductDataStatisticsList(HashMap<String, Object> paramMap);

    /**
     * 小时数据(有好货实时)
     * @param paramMap
     * @return
     */
    List<ProductDataStatistics> getNowHourProductDataStatisticsList(HashMap<String, Object> paramMap);

    /**
     * 小时数据(每日好店实时)
     * @param paramMap
     * @return
     */
    List<ProductDataStatistics> getNowHourMchtDataStatisticsList(HashMap<String, Object> paramMap);

}
