package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpOffice;
import com.jf.entity.SpOfficeExample;
@Repository
public interface SpOfficeMapper extends BaseDao<SpOffice, SpOfficeExample> {
    int countByExample(SpOfficeExample example);

    int deleteByExample(SpOfficeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpOffice record);

    int insertSelective(SpOffice record);

    List<SpOffice> selectByExample(SpOfficeExample example);

    SpOffice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpOffice record, @Param("example") SpOfficeExample example);

    int updateByExample(@Param("record") SpOffice record, @Param("example") SpOfficeExample example);

    int updateByPrimaryKeySelective(SpOffice record);

    int updateByPrimaryKey(SpOffice record);
}