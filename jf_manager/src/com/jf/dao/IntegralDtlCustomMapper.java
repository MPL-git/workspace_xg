package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlCustom;
import com.jf.entity.IntegralDtlExample;
@Repository
public interface IntegralDtlCustomMapper{
    int countByExample(IntegralDtlExample example);
    List<IntegralDtlCustom> selectByExample(IntegralDtlExample example);
    IntegralDtlCustom selectByPrimaryKey(Integer id);
    int selectTotalIntegral();
    int sumIntegralByExample(IntegralDtlExample example);
    
    /**
     * 
     * @Title insertIntegralDtlList   
     * @Description TODO(批量插入积分明细)   
     * @author pengl
     * @date 2018年5月23日 下午1:17:43
     */
    public void insertIntegralDtlList(List<IntegralDtl> integralDtlList);
    
}