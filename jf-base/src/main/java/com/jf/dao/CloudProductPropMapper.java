package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CloudProductProp;
import com.jf.entity.CloudProductPropExample;

@Repository
public interface CloudProductPropMapper extends BaseDao<CloudProductProp, CloudProductPropExample> {
    int countByExample(CloudProductPropExample example);

    int deleteByExample(CloudProductPropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CloudProductProp record);

    int insertSelective(CloudProductProp record);

    List<CloudProductProp> selectByExample(CloudProductPropExample example);

    CloudProductProp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CloudProductProp record, @Param("example") CloudProductPropExample example);

    int updateByExample(@Param("record") CloudProductProp record, @Param("example") CloudProductPropExample example);

    int updateByPrimaryKeySelective(CloudProductProp record);

    int updateByPrimaryKey(CloudProductProp record);
}
