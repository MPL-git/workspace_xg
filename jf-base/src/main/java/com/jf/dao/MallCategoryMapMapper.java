package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MallCategoryMap;
import com.jf.entity.MallCategoryMapExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallCategoryMapMapper extends BaseDao<MallCategoryMap, MallCategoryMapExample> {
    int countByExample(MallCategoryMapExample example);

    int deleteByExample(MallCategoryMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCategoryMap record);

    int insertSelective(MallCategoryMap record);

    List<MallCategoryMap> selectByExample(MallCategoryMapExample example);

    MallCategoryMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCategoryMap record, @Param("example") MallCategoryMapExample example);

    int updateByExample(@Param("record") MallCategoryMap record, @Param("example") MallCategoryMapExample example);

    int updateByPrimaryKeySelective(MallCategoryMap record);

    int updateByPrimaryKey(MallCategoryMap record);
}
