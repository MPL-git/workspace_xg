package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpTagMapper extends BaseDao<HelpTag, HelpTagExample> {
    int countByExample(HelpTagExample example);

    int deleteByExample(HelpTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelpTag record);

    int insertSelective(HelpTag record);

    List<HelpTag> selectByExample(HelpTagExample example);

    HelpTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelpTag record, @Param("example") HelpTagExample example);

    int updateByExample(@Param("record") HelpTag record, @Param("example") HelpTagExample example);

    int updateByPrimaryKeySelective(HelpTag record);

    int updateByPrimaryKey(HelpTag record);
}
