package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface ActivityCustomMapper {
   
	/**
     * 
     * @Title getActivityDepositOrderBeginList   
     * @Description TODO(获取购买预售活动商品会员信息)   
     * @author pengl
     * @date 2018年11月16日 上午9:37:13
     */
    public List<Map<String, Object>> getActivityDepositOrderBeginList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title getActivityDepositOrderCloseList   
     * @Description TODO(获取购买预售活动关闭商品信息)   
     * @author pengl
     * @date 2018年11月16日 下午12:57:08
     */
    public List<Map<String, Object>> getActivityDepositOrderCloseList(Map<String, Object> paramMap);
    

}