package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipRecommendNavigation;
import com.jf.entity.SvipRecommendNavigationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipRecommendNavigationMapper extends BaseDao<SvipRecommendNavigation, SvipRecommendNavigationExample> {
    int countByExample(SvipRecommendNavigationExample example);

    int deleteByExample(SvipRecommendNavigationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipRecommendNavigation record);

    int insertSelective(SvipRecommendNavigation record);

    List<SvipRecommendNavigation> selectByExample(SvipRecommendNavigationExample example);

    SvipRecommendNavigation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipRecommendNavigation record, @Param("example") SvipRecommendNavigationExample example);

    int updateByExample(@Param("record") SvipRecommendNavigation record, @Param("example") SvipRecommendNavigationExample example);

    int updateByPrimaryKeySelective(SvipRecommendNavigation record);

    int updateByPrimaryKey(SvipRecommendNavigation record);
}