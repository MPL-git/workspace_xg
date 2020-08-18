package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceProductTypeMapper extends BaseDao<SourceProductType, SourceProductTypeExample> {
    int countByExample(SourceProductTypeExample example);

    int deleteByExample(SourceProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SourceProductType record);

    int insertSelective(SourceProductType record);

    List<SourceProductType> selectByExample(SourceProductTypeExample example);

    SourceProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SourceProductType record, @Param("example") SourceProductTypeExample example);

    int updateByExample(@Param("record") SourceProductType record, @Param("example") SourceProductTypeExample example);

    int updateByPrimaryKeySelective(SourceProductType record);

    int updateByPrimaryKey(SourceProductType record);
}
