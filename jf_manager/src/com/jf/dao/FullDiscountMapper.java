package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
@Repository
public interface FullDiscountMapper extends BaseDao<FullDiscount, FullDiscountExample>{
    int countByExample(FullDiscountExample example);

    int deleteByExample(FullDiscountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FullDiscount record);

    int insertSelective(FullDiscount record);

    List<FullDiscount> selectByExample(FullDiscountExample example);

    FullDiscount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FullDiscount record, @Param("example") FullDiscountExample example);

    int updateByExample(@Param("record") FullDiscount record, @Param("example") FullDiscountExample example);

    int updateByPrimaryKeySelective(FullDiscount record);

    int updateByPrimaryKey(FullDiscount record);
}