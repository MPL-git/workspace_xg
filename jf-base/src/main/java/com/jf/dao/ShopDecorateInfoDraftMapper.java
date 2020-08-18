package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecorateInfoDraft;
import com.jf.entity.ShopDecorateInfoDraftExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateInfoDraftMapper extends BaseDao<ShopDecorateInfoDraft, ShopDecorateInfoDraftExample> {
    int countByExample(ShopDecorateInfoDraftExample example);

    int deleteByExample(ShopDecorateInfoDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecorateInfoDraft record);

    int insertSelective(ShopDecorateInfoDraft record);

    List<ShopDecorateInfoDraft> selectByExample(ShopDecorateInfoDraftExample example);

    ShopDecorateInfoDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecorateInfoDraft record, @Param("example") ShopDecorateInfoDraftExample example);

    int updateByExample(@Param("record") ShopDecorateInfoDraft record, @Param("example") ShopDecorateInfoDraftExample example);

    int updateByPrimaryKeySelective(ShopDecorateInfoDraft record);

    int updateByPrimaryKey(ShopDecorateInfoDraft record);
}
