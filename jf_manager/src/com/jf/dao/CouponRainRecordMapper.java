package com.jf.dao;

import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
