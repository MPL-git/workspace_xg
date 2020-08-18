package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainRecordExample;

@Repository
public interface CouponRainRecordMapper extends BaseDao<CouponRainRecord, CouponRainRecordExample> {
    int countByExample(CouponRainRecordExample example);

    int deleteByExample(CouponRainRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponRainRecord record);

    int insertSelective(CouponRainRecord record);

    List<CouponRainRecord> selectByExample(CouponRainRecordExample example);

    CouponRainRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponRainRecord record, @Param("example") CouponRainRecordExample example);

    int updateByExample(@Param("record") CouponRainRecord record, @Param("example") CouponRainRecordExample example);

    int updateByPrimaryKeySelective(CouponRainRecord record);

    int updateByPrimaryKey(CouponRainRecord record);
}
