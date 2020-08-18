package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.KeyValue;
import com.jf.entity.KeyValueExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueMapper extends BaseDao<KeyValue, KeyValueExample> {
    int countByExample(KeyValueExample example);

    int deleteByExample(KeyValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(KeyValue record);

    int insertSelective(KeyValue record);

    List<KeyValue> selectByExample(KeyValueExample example);

    KeyValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") KeyValue record, @Param("example") KeyValueExample example);

    int updateByExample(@Param("record") KeyValue record, @Param("example") KeyValueExample example);

    int updateByPrimaryKeySelective(KeyValue record);

    int updateByPrimaryKey(KeyValue record);
}