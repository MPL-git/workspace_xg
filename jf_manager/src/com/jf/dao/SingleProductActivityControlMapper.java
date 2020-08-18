package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SingleProductActivityControl;
import com.jf.entity.SingleProductActivityControlExample;
@Repository
public interface SingleProductActivityControlMapper extends BaseDao<SingleProductActivityControl, SingleProductActivityControlExample> {
    int countByExample(SingleProductActivityControlExample example);

    int deleteByExample(SingleProductActivityControlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleProductActivityControl record);

    int insertSelective(SingleProductActivityControl record);

    List<SingleProductActivityControl> selectByExample(SingleProductActivityControlExample example);

    SingleProductActivityControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleProductActivityControl record, @Param("example") SingleProductActivityControlExample example);

    int updateByExample(@Param("record") SingleProductActivityControl record, @Param("example") SingleProductActivityControlExample example);

    int updateByPrimaryKeySelective(SingleProductActivityControl record);

    int updateByPrimaryKey(SingleProductActivityControl record);
}