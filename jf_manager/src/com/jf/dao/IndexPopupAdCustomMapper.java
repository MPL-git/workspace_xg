package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IndexPopupAdCustom;
import com.jf.entity.IndexPopupAdCustomExample;
@Repository
public interface IndexPopupAdCustomMapper{
    int indexPopupAdcountByExample(IndexPopupAdCustomExample example);
    List<IndexPopupAdCustom> indexPopupAdselectByExample(IndexPopupAdCustomExample example);
    IndexPopupAdCustom indexPopupAdselectByPrimaryKey(Integer id);
}