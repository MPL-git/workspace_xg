package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;

@Repository
public interface MchtBrandAptitudePicChgMapper extends BaseDao<MchtBrandAptitudePicChg, MchtBrandAptitudePicChgExample> {
    int countByExample(MchtBrandAptitudePicChgExample example);

    int deleteByExample(MchtBrandAptitudePicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandAptitudePicChg record);

    int insertSelective(MchtBrandAptitudePicChg record);

    List<MchtBrandAptitudePicChg> selectByExample(MchtBrandAptitudePicChgExample example);

    MchtBrandAptitudePicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandAptitudePicChg record, @Param("example") MchtBrandAptitudePicChgExample example);

    int updateByExample(@Param("record") MchtBrandAptitudePicChg record, @Param("example") MchtBrandAptitudePicChgExample example);

    int updateByPrimaryKeySelective(MchtBrandAptitudePicChg record);

    int updateByPrimaryKey(MchtBrandAptitudePicChg record);
}