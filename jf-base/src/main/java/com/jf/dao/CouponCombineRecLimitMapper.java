package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponCombineRecLimit;
import com.jf.entity.CouponCombineRecLimitExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponCombineRecLimitMapper extends BaseDao<CouponCombineRecLimit, CouponCombineRecLimitExample> {
    int countByExample(CouponCombineRecLimitExample example);

    int deleteByExample(CouponCombineRecLimitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponCombineRecLimit record);

    int insertSelective(CouponCombineRecLimit record);

    List<CouponCombineRecLimit> selectByExample(CouponCombineRecLimitExample example);

    CouponCombineRecLimit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponCombineRecLimit record, @Param("example") CouponCombineRecLimitExample example);

    int updateByExample(@Param("record") CouponCombineRecLimit record, @Param("example") CouponCombineRecLimitExample example);

    int updateByPrimaryKeySelective(CouponCombineRecLimit record);

    int updateByPrimaryKey(CouponCombineRecLimit record);
}
