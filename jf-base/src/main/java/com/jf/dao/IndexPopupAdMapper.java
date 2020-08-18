package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IndexPopupAd;
import com.jf.entity.IndexPopupAdExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexPopupAdMapper extends BaseDao<IndexPopupAd, IndexPopupAdExample> {
    int countByExample(IndexPopupAdExample example);

    int deleteByExample(IndexPopupAdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IndexPopupAd record);

    int insertSelective(IndexPopupAd record);

    List<IndexPopupAd> selectByExample(IndexPopupAdExample example);

    IndexPopupAd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IndexPopupAd record, @Param("example") IndexPopupAdExample example);

    int updateByExample(@Param("record") IndexPopupAd record, @Param("example") IndexPopupAdExample example);

    int updateByPrimaryKeySelective(IndexPopupAd record);

    int updateByPrimaryKey(IndexPopupAd record);
}
