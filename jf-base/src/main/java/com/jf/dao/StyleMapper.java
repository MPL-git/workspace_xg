package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleMapper extends BaseDao<Style, StyleExample> {
    int countByExample(StyleExample example);

    int deleteByExample(StyleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Style record);

    int insertSelective(Style record);

    List<Style> selectByExample(StyleExample example);

    Style selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Style record, @Param("example") StyleExample example);

    int updateByExample(@Param("record") Style record, @Param("example") StyleExample example);

    int updateByPrimaryKeySelective(Style record);

    int updateByPrimaryKey(Style record);
}
