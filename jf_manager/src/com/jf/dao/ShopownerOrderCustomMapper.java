package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopownerOrderCustom;
import com.jf.entity.ShopownerOrderCustomExample;


@Repository
public interface ShopownerOrderCustomMapper {
    public int countByCustomExample(ShopownerOrderCustomExample example);

    public List<ShopownerOrderCustom> selectByCustomExample(ShopownerOrderCustomExample example);
   
    public List<Map<String, Object>> statisticsShopownerOrder(Map<String, Object> paramMap);
}