package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpItemMapper extends BaseDao<HelpItem, HelpItemExample> {
    int countByExample(HelpItemExample example);

    int deleteByExample(HelpItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelpItem record);

    int insertSelective(HelpItem record);

    List<HelpItem> selectByExample(HelpItemExample example);

    HelpItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelpItem record, @Param("example") HelpItemExample example);

    int updateByExample(@Param("record") HelpItem record, @Param("example") HelpItemExample example);

    int updateByPrimaryKeySelective(HelpItem record);

    int updateByPrimaryKey(HelpItem record);
}
