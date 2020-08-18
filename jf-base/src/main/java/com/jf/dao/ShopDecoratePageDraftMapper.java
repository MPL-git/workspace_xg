package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecoratePageDraft;
import com.jf.entity.ShopDecoratePageDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecoratePageDraftMapper extends BaseDao<ShopDecoratePageDraft, ShopDecoratePageDraftExample> {
    int countByExample(ShopDecoratePageDraftExample example);

    int deleteByExample(ShopDecoratePageDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecoratePageDraft record);

    int insertSelective(ShopDecoratePageDraft record);

    List<ShopDecoratePageDraft> selectByExample(ShopDecoratePageDraftExample example);

    ShopDecoratePageDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecoratePageDraft record, @Param("example") ShopDecoratePageDraftExample example);

    int updateByExample(@Param("record") ShopDecoratePageDraft record, @Param("example") ShopDecoratePageDraftExample example);

    int updateByPrimaryKeySelective(ShopDecoratePageDraft record);

    int updateByPrimaryKey(ShopDecoratePageDraft record);
}
