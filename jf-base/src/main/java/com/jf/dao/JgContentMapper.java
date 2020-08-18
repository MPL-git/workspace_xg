package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.JgContent;
import com.jf.entity.JgContentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JgContentMapper extends BaseDao<JgContent, JgContentExample> {
    int countByExample(JgContentExample example);

    int deleteByExample(JgContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JgContent record);

    int insertSelective(JgContent record);

    List<JgContent> selectByExample(JgContentExample example);

    JgContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JgContent record, @Param("example") JgContentExample example);

    int updateByExample(@Param("record") JgContent record, @Param("example") JgContentExample example);

    int updateByPrimaryKeySelective(JgContent record);

    int updateByPrimaryKey(JgContent record);
}
