package com.jf.dao;

import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleProductActivityMapper extends BaseDao<SingleProductActivity, SingleProductActivityExample> {
    int countByExample(SingleProductActivityExample example);

    int deleteByExample(SingleProductActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleProductActivity record);

    int insertSelective(SingleProductActivity record);

    List<SingleProductActivity> selectByExample(SingleProductActivityExample example);

    SingleProductActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleProductActivity record, @Param("example") SingleProductActivityExample example);

    int updateByExample(@Param("record") SingleProductActivity record, @Param("example") SingleProductActivityExample example);

    int updateByPrimaryKeySelective(SingleProductActivity record);

    int updateByPrimaryKey(SingleProductActivity record);
}