package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpOffice;
import com.jf.entity.SpOfficeExample;

@Repository
public interface SpOfficeMapper extends BaseDao<SpOffice, SpOfficeExample> {
    int countByExample(SpOfficeExample example);

    int deleteByExample(SpOfficeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpOffice record);

    int insertSelective(SpOffice record);

    List<SpOffice> selectByExample(SpOfficeExample example);

    SpOffice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpOffice record, @Param("example") SpOfficeExample example);

    int updateByExample(@Param("record") SpOffice record, @Param("example") SpOfficeExample example);

    int updateByPrimaryKeySelective(SpOffice record);

    int updateByPrimaryKey(SpOffice record);
}
