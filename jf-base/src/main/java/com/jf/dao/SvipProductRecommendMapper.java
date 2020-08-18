package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipProductRecommend;
import com.jf.entity.SvipProductRecommendExample;
@Repository
public interface SvipProductRecommendMapper extends BaseDao<SvipProductRecommend, SvipProductRecommendExample>{
    int countByExample(SvipProductRecommendExample example);

    int deleteByExample(SvipProductRecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipProductRecommend record);

    int insertSelective(SvipProductRecommend record);

    List<SvipProductRecommend> selectByExample(SvipProductRecommendExample example);

    SvipProductRecommend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipProductRecommend record, @Param("example") SvipProductRecommendExample example);

    int updateByExample(@Param("record") SvipProductRecommend record, @Param("example") SvipProductRecommendExample example);

    int updateByPrimaryKeySelective(SvipProductRecommend record);

    int updateByPrimaryKey(SvipProductRecommend record);
}