package com.jf.dao;

import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtProductBrandMapper extends BaseDao<MchtProductBrand, MchtProductBrandExample> {
    int countByExample(MchtProductBrandExample example);

    int deleteByExample(MchtProductBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtProductBrand record);

    int insertSelective(MchtProductBrand record);

    List<MchtProductBrand> selectByExample(MchtProductBrandExample example);

    MchtProductBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtProductBrand record, @Param("example") MchtProductBrandExample example);

    int updateByExample(@Param("record") MchtProductBrand record, @Param("example") MchtProductBrandExample example);

    int updateByPrimaryKeySelective(MchtProductBrand record);

    int updateByPrimaryKey(MchtProductBrand record);
}