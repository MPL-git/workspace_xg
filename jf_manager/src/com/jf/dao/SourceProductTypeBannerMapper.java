package com.jf.dao;

import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceProductTypeBannerMapper extends BaseDao<SourceProductTypeBanner, SourceProductTypeBannerExample>{
    int countByExample(SourceProductTypeBannerExample example);

    int deleteByExample(SourceProductTypeBannerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SourceProductTypeBanner record);

    int insertSelective(SourceProductTypeBanner record);

    List<SourceProductTypeBanner> selectByExample(SourceProductTypeBannerExample example);

    SourceProductTypeBanner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SourceProductTypeBanner record, @Param("example") SourceProductTypeBannerExample example);

    int updateByExample(@Param("record") SourceProductTypeBanner record, @Param("example") SourceProductTypeBannerExample example);

    int updateByPrimaryKeySelective(SourceProductTypeBanner record);

    int updateByPrimaryKey(SourceProductTypeBanner record);
}