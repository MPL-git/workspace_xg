package com.jf.dao;

import com.jf.entity.CouponAddtaskConfig;
import com.jf.entity.CouponAddtaskConfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CouponAddtaskConfigMapper extends BaseDao<CouponAddtaskConfig, CouponAddtaskConfigExample>{
    int countByExample(CouponAddtaskConfigExample example);

    int deleteByExample(CouponAddtaskConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponAddtaskConfig record);

    int insertSelective(CouponAddtaskConfig record);

    List<CouponAddtaskConfig> selectByExample(CouponAddtaskConfigExample example);

    CouponAddtaskConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponAddtaskConfig record, @Param("example") CouponAddtaskConfigExample example);

    int updateByExample(@Param("record") CouponAddtaskConfig record, @Param("example") CouponAddtaskConfigExample example);

    int updateByPrimaryKeySelective(CouponAddtaskConfig record);

    int updateByPrimaryKey(CouponAddtaskConfig record);
}