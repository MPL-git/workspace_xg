package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponCenterTime;
import com.jf.entity.CouponCenterTimeExample;
@Repository
public interface CouponCenterTimeMapper extends BaseDao<CouponCenterTime, CouponCenterTimeExample> {
    int countByExample(CouponCenterTimeExample example);

    int deleteByExample(CouponCenterTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponCenterTime record);

    int insertSelective(CouponCenterTime record);

    List<CouponCenterTime> selectByExample(CouponCenterTimeExample example);

    CouponCenterTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponCenterTime record, @Param("example") CouponCenterTimeExample example);

    int updateByExample(@Param("record") CouponCenterTime record, @Param("example") CouponCenterTimeExample example);

    int updateByPrimaryKeySelective(CouponCenterTime record);

    int updateByPrimaryKey(CouponCenterTime record);
}