package com.jf.dao;

import com.jf.entity.CouponModuleTime;
import com.jf.entity.CouponModuleTimeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponModuleTimeMapper extends BaseDao<CouponModuleTime,CouponModuleTimeExample> {
    int countByExample(CouponModuleTimeExample example);

    int deleteByExample(CouponModuleTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponModuleTime record);

    int insertSelective(CouponModuleTime record);

    List<CouponModuleTime> selectByExample(CouponModuleTimeExample example);

    CouponModuleTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponModuleTime record, @Param("example") CouponModuleTimeExample example);

    int updateByExample(@Param("record") CouponModuleTime record, @Param("example") CouponModuleTimeExample example);

    int updateByPrimaryKeySelective(CouponModuleTime record);

    int updateByPrimaryKey(CouponModuleTime record);
}