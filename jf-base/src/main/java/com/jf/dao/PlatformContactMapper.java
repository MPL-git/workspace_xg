package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformContactMapper extends BaseDao<PlatformContact, PlatformContactExample> {
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
