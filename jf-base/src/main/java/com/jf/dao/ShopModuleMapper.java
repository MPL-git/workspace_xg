package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModuleMapper extends BaseDao<ShopModule, ShopModuleExample> {
    int countByExample(ShopModuleExample example);

    int deleteByExample(ShopModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopModule record);

    int insertSelective(ShopModule record);

    List<ShopModule> selectByExample(ShopModuleExample example);

    ShopModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopModule record, @Param("example") ShopModuleExample example);

    int updateByExample(@Param("record") ShopModule record, @Param("example") ShopModuleExample example);

    int updateByPrimaryKeySelective(ShopModule record);

    int updateByPrimaryKey(ShopModule record);
}
