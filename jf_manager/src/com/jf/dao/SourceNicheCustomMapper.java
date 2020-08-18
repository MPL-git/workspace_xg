package com.jf.dao;

import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface SourceNicheCustomMapper {
    int sourceNichecountByExample(SourceNicheCustomExample example);

    List<SourceNicheCustom> sourceNicheCustomselectByExample(SourceNicheCustomExample example);

    int countIntegralLotteryCommodityPoolsList(SourceNicheCustomExample sourceNicheCustomExample);

    List<SourceNicheCustom> selectIntegralLotteryCommodityPoolsList(SourceNicheCustomExample sourceNicheCustomExample);

    SourceNicheCustom sourceNicheCustomselectByPrimaryKey(Integer id);

    HashMap<String,Object> selectWeightDetail(Integer id);

    //资源位商品流量数据统计
    List<SourceNicheCustom> selectDataStatisticsList(Map<String,Object> map);
    //资源位店铺流量数据统计
    List<SourceNicheCustom> selectShopDataStatisticsList(Map<String,Object> map);

    List<HashMap<String,Object>> queryProductlist(HashMap<String, Object> paramMap);

    Integer queryProductlistCount(HashMap<String, Object> paramMap);

    void batchAuditIntegralProduct(HashMap<String, Object> paramMap);

    void updateIntegralLotterySeqNo(HashMap<String, Object> paraMap);
}