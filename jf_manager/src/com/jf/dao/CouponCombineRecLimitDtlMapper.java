package com.jf.dao;

import com.jf.entity.CouponCombineRecLimitDtl;
import com.jf.entity.CouponCombineRecLimitDtlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CouponCombineRecLimitDtlMapper extends BaseDao<CouponCombineRecLimitDtl, CouponCombineRecLimitDtlExample>{
    int countByExample(CouponCombineRecLimitDtlExample example);

    int deleteByExample(CouponCombineRecLimitDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponCombineRecLimitDtl record);

    int insertSelective(CouponCombineRecLimitDtl record);

    List<CouponCombineRecLimitDtl> selectByExample(CouponCombineRecLimitDtlExample example);

    CouponCombineRecLimitDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponCombineRecLimitDtl record, @Param("example") CouponCombineRecLimitDtlExample example);

    int updateByExample(@Param("record") CouponCombineRecLimitDtl record, @Param("example") CouponCombineRecLimitDtlExample example);

    int updateByPrimaryKeySelective(CouponCombineRecLimitDtl record);

    int updateByPrimaryKey(CouponCombineRecLimitDtl record);
}