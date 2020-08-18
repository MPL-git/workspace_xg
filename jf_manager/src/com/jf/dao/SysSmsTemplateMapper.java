package com.jf.dao;

import com.jf.entity.SysSmsTemplate;
import com.jf.entity.SysSmsTemplateExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysSmsTemplateMapper extends BaseDao<SysSmsTemplate, SysSmsTemplateExample> {
    int countByExample(SysSmsTemplateExample example);

    int deleteByExample(SysSmsTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysSmsTemplate record);

    int insertSelective(SysSmsTemplate record);

    List<SysSmsTemplate> selectByExample(SysSmsTemplateExample example);

    SysSmsTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysSmsTemplate record, @Param("example") SysSmsTemplateExample example);

    int updateByExample(@Param("record") SysSmsTemplate record, @Param("example") SysSmsTemplateExample example);

    int updateByPrimaryKeySelective(SysSmsTemplate record);

    int updateByPrimaryKey(SysSmsTemplate record);
}