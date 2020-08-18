package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CloudProductItem;
import com.jf.entity.CloudProductItemExample;

@Repository
public interface CloudProductItemMapper extends BaseDao<CloudProductItem, CloudProductItemExample> {
    int countByExample(CloudProductItemExample example);

    int deleteByExample(CloudProductItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CloudProductItem record);

    int insertSelective(CloudProductItem record);

    List<CloudProductItem> selectByExample(CloudProductItemExample example);

    CloudProductItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CloudProductItem record, @Param("example") CloudProductItemExample example);

    int updateByExample(@Param("record") CloudProductItem record, @Param("example") CloudProductItemExample example);

    int updateByPrimaryKeySelective(CloudProductItem record);

    int updateByPrimaryKey(CloudProductItem record);
}
