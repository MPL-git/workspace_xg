package com.jf.dao;

import com.jf.entity.MchtShopDynamic;
import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MchtShopDynamicCustomMapper {
    int countByCustomExample(MchtShopDynamicCustomExample example);

    List<MchtShopDynamicCustom> selectByCustomExample(MchtShopDynamicCustomExample example);

    MchtShopDynamicCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") MchtShopDynamic record, @Param("example") MchtShopDynamicCustomExample example);
    
    public int updateReadCount(Integer id);
    
    public List<Map<String, Object>> getRecommendList(Map<String, Object> paramsMap);
   
    public List<Map<String, Object>> getAttentionList(Map<String, Object> paramsMap);
    
    public List<Map<String, Object>> getMemberDynamicList(Map<String, Object> paramsMap);

}