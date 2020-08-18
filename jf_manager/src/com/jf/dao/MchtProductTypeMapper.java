package com.jf.dao;

import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtProductTypeMapper extends BaseDao<MchtProductType, MchtProductTypeExample> {
    int countByExample(MchtProductTypeExample example);

    int deleteByExample(MchtProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtProductType record);

    int insertSelective(MchtProductType record);

    List<MchtProductType> selectByExample(MchtProductTypeExample example);

    MchtProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtProductType record, @Param("example") MchtProductTypeExample example);

    int updateByExample(@Param("record") MchtProductType record, @Param("example") MchtProductTypeExample example);

    int updateByPrimaryKeySelective(MchtProductType record);

    int updateByPrimaryKey(MchtProductType record);
}