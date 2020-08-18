package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateAreaDraftMapper extends BaseDao<ShopDecorateAreaDraft, ShopDecorateAreaDraftExample> {
    int countByExample(ShopDecorateAreaDraftExample example);

    int deleteByExample(ShopDecorateAreaDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecorateAreaDraft record);

    int insertSelective(ShopDecorateAreaDraft record);

    List<ShopDecorateAreaDraft> selectByExample(ShopDecorateAreaDraftExample example);

    ShopDecorateAreaDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecorateAreaDraft record, @Param("example") ShopDecorateAreaDraftExample example);

    int updateByExample(@Param("record") ShopDecorateAreaDraft record, @Param("example") ShopDecorateAreaDraftExample example);

    int updateByPrimaryKeySelective(ShopDecorateAreaDraft record);

    int updateByPrimaryKey(ShopDecorateAreaDraft record);
}
