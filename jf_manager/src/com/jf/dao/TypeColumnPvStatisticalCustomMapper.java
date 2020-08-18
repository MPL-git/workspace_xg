package com.jf.dao;

import com.jf.entity.TypeColumnPvStatistical;
import com.jf.entity.TypeColumnPvStatisticalCustom;
import com.jf.entity.TypeColumnPvStatisticalExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface TypeColumnPvStatisticalCustomMapper extends BaseDao<TypeColumnPvStatistical, TypeColumnPvStatisticalExample>{

    List<TypeColumnPvStatisticalCustom> getCategoryProductDataStatisticsList(HashMap<String, Object> paramMap);

    List<TypeColumnPvStatisticalCustom> selectDataStatisticsList(Map<String, Object> paramMap);

    List<TypeColumnPvStatisticalCustom> selectShopDataStatisticsList(Map<String, Object> paramMap);

    List<TypeColumnPvStatisticalCustom> selectNowDataStatisticsList(Map<String, Object> paramMap);

    List<TypeColumnPvStatisticalCustom> selectNowShopDataStatisticsList(Map<String, Object> paramMap);

    List<HashMap<String,Object>> selectActivityTrafficStatistics(HashMap<String, Object> paraMap);

    Integer countActivityTrafficStatistics(HashMap<String, Object> paraMap);

    List<HashMap<String,Object>> selectSingleProductActivityTrafficStatistics(HashMap<String, Object> paraMap);

    Integer countSingleProductActivityTrafficStatistics(HashMap<String, Object> paraMap);
    /**
     * 商品流量报表
     * */
    List<TypeColumnPvStatisticalCustom> getCommodityFlowReportData(Map<String, Object> paraMap);

    /**
     * 商家流量报表
     * */
    List<TypeColumnPvStatisticalCustom> getShopFlowReportData(Map<String, Object> paraMap);

    /**
     * 品牌流量报表
     * */
    List<TypeColumnPvStatisticalCustom> getBrandFlowReportData(Map<String, Object> paraMap);

    /**
     * 类目流量报表
     * */
    List<TypeColumnPvStatisticalCustom> getcategoryFlowReportData(Map<String, Object> paraMap);

    /**
     *
     * 实时获取品牌或类目会员访客数
     * */
    TypeColumnPvStatisticalCustom getVisitorCount(Map<String, Object> paraMap);

    /**
     *
     * 实时获取店铺会员访客数
     * */
    TypeColumnPvStatisticalCustom getShopVisitorCount(Map<String, Object> paramMap);
}