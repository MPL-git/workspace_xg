package com.jf.dao;

import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBrandAptitudePicMapper extends BaseDao<MchtBrandAptitudePic, MchtBrandAptitudePicExample>{
    int countByExample(MchtBrandAptitudePicExample example);

    int deleteByExample(MchtBrandAptitudePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandAptitudePic record);

    int insertSelective(MchtBrandAptitudePic record);

    List<MchtBrandAptitudePic> selectByExample(MchtBrandAptitudePicExample example);

    MchtBrandAptitudePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandAptitudePic record, @Param("example") MchtBrandAptitudePicExample example);

    int updateByExample(@Param("record") MchtBrandAptitudePic record, @Param("example") MchtBrandAptitudePicExample example);

    int updateByPrimaryKeySelective(MchtBrandAptitudePic record);

    int updateByPrimaryKey(MchtBrandAptitudePic record);
}