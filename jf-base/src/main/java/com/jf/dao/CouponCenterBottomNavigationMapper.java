package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponCenterBottomNavigation;
import com.jf.entity.CouponCenterBottomNavigationExample;
@Repository
public interface CouponCenterBottomNavigationMapper extends BaseDao<CouponCenterBottomNavigation, CouponCenterBottomNavigationExample>{
    int countByExample(CouponCenterBottomNavigationExample example);

    int deleteByExample(CouponCenterBottomNavigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponCenterBottomNavigation record);

    int insertSelective(CouponCenterBottomNavigation record);

    List<CouponCenterBottomNavigation> selectByExample(CouponCenterBottomNavigationExample example);

    CouponCenterBottomNavigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponCenterBottomNavigation record, @Param("example") CouponCenterBottomNavigationExample example);

    int updateByExample(@Param("record") CouponCenterBottomNavigation record, @Param("example") CouponCenterBottomNavigationExample example);

    int updateByPrimaryKeySelective(CouponCenterBottomNavigation record);

    int updateByPrimaryKey(CouponCenterBottomNavigation record);
}