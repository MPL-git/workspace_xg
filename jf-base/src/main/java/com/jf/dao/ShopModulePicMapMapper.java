package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModulePicMapMapper extends BaseDao<ShopModulePicMap, ShopModulePicMapExample> {
    int countByExample(ShopModulePicMapExample example);

    int deleteByExample(ShopModulePicMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopModulePicMap record);

    int insertSelective(ShopModulePicMap record);

    List<ShopModulePicMap> selectByExample(ShopModulePicMapExample example);

    ShopModulePicMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopModulePicMap record, @Param("example") ShopModulePicMapExample example);

    int updateByExample(@Param("record") ShopModulePicMap record, @Param("example") ShopModulePicMapExample example);

    int updateByPrimaryKeySelective(ShopModulePicMap record);

    int updateByPrimaryKey(ShopModulePicMap record);
}
