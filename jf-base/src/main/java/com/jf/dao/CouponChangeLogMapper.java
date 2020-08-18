package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponChangeLog;
import com.jf.entity.CouponChangeLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponChangeLogMapper extends BaseDao<CouponChangeLog, CouponChangeLogExample> {
    int countByExample(CouponChangeLogExample example);

    int deleteByExample(CouponChangeLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponChangeLog record);

    int insertSelective(CouponChangeLog record);

    List<CouponChangeLog> selectByExample(CouponChangeLogExample example);

    CouponChangeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponChangeLog record, @Param("example") CouponChangeLogExample example);

    int updateByExample(@Param("record") CouponChangeLog record, @Param("example") CouponChangeLogExample example);

    int updateByPrimaryKeySelective(CouponChangeLog record);

    int updateByPrimaryKey(CouponChangeLog record);
}
