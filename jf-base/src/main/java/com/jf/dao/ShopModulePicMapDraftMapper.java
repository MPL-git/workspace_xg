package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModulePicMapDraftMapper extends BaseDao<ShopModulePicMapDraft, ShopModulePicMapDraftExample> {
    int countByExample(ShopModulePicMapDraftExample example);

    int deleteByExample(ShopModulePicMapDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopModulePicMapDraft record);

    int insertSelective(ShopModulePicMapDraft record);

    List<ShopModulePicMapDraft> selectByExample(ShopModulePicMapDraftExample example);

    ShopModulePicMapDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopModulePicMapDraft record, @Param("example") ShopModulePicMapDraftExample example);

    int updateByExample(@Param("record") ShopModulePicMapDraft record, @Param("example") ShopModulePicMapDraftExample example);

    int updateByPrimaryKeySelective(ShopModulePicMapDraft record);

    int updateByPrimaryKey(ShopModulePicMapDraft record);
}
