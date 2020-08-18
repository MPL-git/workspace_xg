package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.CloudProduct;
import com.jf.entity.CloudProductExample;

@Repository
public interface CloudProductMapper extends BaseDao<CloudProduct, CloudProductExample> {
    int countByExample(CloudProductExample example);

    int deleteByExample(CloudProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CloudProduct record);

    int insertSelective(CloudProduct record);

    List<CloudProduct> selectByExample(CloudProductExample example);

    CloudProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CloudProduct record, @Param("example") CloudProductExample example);

    int updateByExample(@Param("record") CloudProduct record, @Param("example") CloudProductExample example);

    int updateByPrimaryKeySelective(CloudProduct record);

    int updateByPrimaryKey(CloudProduct record);
}
