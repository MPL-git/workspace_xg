package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CloudProductAfterTemplet;
import com.jf.entity.CloudProductAfterTempletExample;

@Repository
public interface CloudProductAfterTempletMapper extends BaseDao<CloudProductAfterTemplet, CloudProductAfterTempletExample> {
    int countByExample(CloudProductAfterTempletExample example);

    int deleteByExample(CloudProductAfterTempletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CloudProductAfterTemplet record);

    int insertSelective(CloudProductAfterTemplet record);

    List<CloudProductAfterTemplet> selectByExample(CloudProductAfterTempletExample example);

    CloudProductAfterTemplet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CloudProductAfterTemplet record, @Param("example") CloudProductAfterTempletExample example);

    int updateByExample(@Param("record") CloudProductAfterTemplet record, @Param("example") CloudProductAfterTempletExample example);

    int updateByPrimaryKeySelective(CloudProductAfterTemplet record);

    int updateByPrimaryKey(CloudProductAfterTemplet record);
}
