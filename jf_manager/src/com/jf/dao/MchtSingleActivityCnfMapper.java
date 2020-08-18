package com.jf.dao;

import com.jf.entity.MchtSingleActivityCnf;
import com.jf.entity.MchtSingleActivityCnfExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtSingleActivityCnfMapper extends BaseDao<MchtSingleActivityCnf, MchtSingleActivityCnfExample> {
    int countByExample(MchtSingleActivityCnfExample example);

    int deleteByExample(MchtSingleActivityCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSingleActivityCnf record);

    int insertSelective(MchtSingleActivityCnf record);

    List<MchtSingleActivityCnf> selectByExample(MchtSingleActivityCnfExample example);

    MchtSingleActivityCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSingleActivityCnf record, @Param("example") MchtSingleActivityCnfExample example);

    int updateByExample(@Param("record") MchtSingleActivityCnf record, @Param("example") MchtSingleActivityCnfExample example);

    int updateByPrimaryKeySelective(MchtSingleActivityCnf record);

    int updateByPrimaryKey(MchtSingleActivityCnf record);
}