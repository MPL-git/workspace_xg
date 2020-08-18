package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.FreightTemplate;
import com.jf.entity.FreightTemplateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreightTemplateMapper extends BaseDao<FreightTemplate, FreightTemplateExample> {
    int countByExample(FreightTemplateExample example);

    int deleteByExample(FreightTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreightTemplate record);

    int insertSelective(FreightTemplate record);

    List<FreightTemplate> selectByExample(FreightTemplateExample example);

    FreightTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByExample(@Param("record") FreightTemplate record, @Param("example") FreightTemplateExample example);

    int updateByPrimaryKeySelective(FreightTemplate record);

    int updateByPrimaryKey(FreightTemplate record);
}
