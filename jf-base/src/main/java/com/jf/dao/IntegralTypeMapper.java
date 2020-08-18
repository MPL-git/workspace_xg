package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralTypeMapper extends BaseDao<IntegralType, IntegralTypeExample> {
    int countByExample(IntegralTypeExample example);

    int deleteByExample(IntegralTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralType record);

    int insertSelective(IntegralType record);

    List<IntegralType> selectByExample(IntegralTypeExample example);

    IntegralType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralType record, @Param("example") IntegralTypeExample example);

    int updateByExample(@Param("record") IntegralType record, @Param("example") IntegralTypeExample example);

    int updateByPrimaryKeySelective(IntegralType record);

    int updateByPrimaryKey(IntegralType record);
}
