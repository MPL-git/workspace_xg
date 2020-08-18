package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModuleDraftMapper extends BaseDao<ShopModuleDraft, ShopModuleDraftExample> {
    int countByExample(ShopModuleDraftExample example);

    int deleteByExample(ShopModuleDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopModuleDraft record);

    int insertSelective(ShopModuleDraft record);

    List<ShopModuleDraft> selectByExample(ShopModuleDraftExample example);

    ShopModuleDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopModuleDraft record, @Param("example") ShopModuleDraftExample example);

    int updateByExample(@Param("record") ShopModuleDraft record, @Param("example") ShopModuleDraftExample example);

    int updateByPrimaryKeySelective(ShopModuleDraft record);

    int updateByPrimaryKey(ShopModuleDraft record);
}
