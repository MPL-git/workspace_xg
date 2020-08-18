package com.jf.dao;

import com.jf.entity.CouponExchangeCode;
import com.jf.entity.CouponExchangeCodeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponExchangeCodeMapper extends BaseDao<CouponExchangeCode, CouponExchangeCodeExample> {
    int countByExample(CouponExchangeCodeExample example);

    int deleteByExample(CouponExchangeCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponExchangeCode record);

    int insertSelective(CouponExchangeCode record);

    List<CouponExchangeCode> selectByExample(CouponExchangeCodeExample example);

    CouponExchangeCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponExchangeCode record, @Param("example") CouponExchangeCodeExample example);

    int updateByExample(@Param("record") CouponExchangeCode record, @Param("example") CouponExchangeCodeExample example);

    int updateByPrimaryKeySelective(CouponExchangeCode record);

    int updateByPrimaryKey(CouponExchangeCode record);
}