package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CloudProductPropValue;
import com.jf.entity.CloudProductPropValueExample;

@Repository
public interface CloudProductPropValueMapper extends BaseDao<CloudProductPropValue, CloudProductPropValueExample> {
    int countByExample(CloudProductPropValueExample example);

    int deleteByExample(CloudProductPropValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CloudProductPropValue record);

    int insertSelective(CloudProductPropValue record);

    List<CloudProductPropValue> selectByExample(CloudProductPropValueExample example);

    CloudProductPropValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CloudProductPropValue record, @Param("example") CloudProductPropValueExample example);

    int updateByExample(@Param("record") CloudProductPropValue record, @Param("example") CloudProductPropValueExample example);

    int updateByPrimaryKeySelective(CloudProductPropValue record);

    int updateByPrimaryKey(CloudProductPropValue record);
}
