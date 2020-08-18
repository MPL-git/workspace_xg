package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceProductTypeBannerMapper extends BaseDao<SourceProductTypeBanner, SourceProductTypeBannerExample> {
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
