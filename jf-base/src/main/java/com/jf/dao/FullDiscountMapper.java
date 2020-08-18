package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullDiscountMapper extends BaseDao<FullDiscount, FullDiscountExample> {
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
