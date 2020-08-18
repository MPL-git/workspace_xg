package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponRainShareRecord;
import com.jf.entity.CouponRainShareRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRainShareRecordMapper extends BaseDao<CouponRainShareRecord, CouponRainShareRecordExample> {
    int countByExample(CouponRainShareRecordExample example);

    int deleteByExample(CouponRainShareRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponRainShareRecord record);

    int insertSelective(CouponRainShareRecord record);

    List<CouponRainShareRecord> selectByExample(CouponRainShareRecordExample example);

    CouponRainShareRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponRainShareRecord record, @Param("example") CouponRainShareRecordExample example);

    int updateByExample(@Param("record") CouponRainShareRecord record, @Param("example") CouponRainShareRecordExample example);

    int updateByPrimaryKeySelective(CouponRainShareRecord record);

    int updateByPrimaryKey(CouponRainShareRecord record);
}