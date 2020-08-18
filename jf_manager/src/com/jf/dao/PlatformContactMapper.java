package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;

@Repository
public interface PlatformContactMapper extends BaseDao<PlatformContact, PlatformContactExample>{
    int countByExample(PlatformContactExample example);

    int deleteByExample(PlatformContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformContact record);

    int insertSelective(PlatformContact record);

    List<PlatformContact> selectByExample(PlatformContactExample example);

    PlatformContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformContact record, @Param("example") PlatformContactExample example);

    int updateByExample(@Param("record") PlatformContact record, @Param("example") PlatformContactExample example);

    int updateByPrimaryKeySelective(PlatformContact record);

    int updateByPrimaryKey(PlatformContact record);
}