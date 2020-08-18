package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtPlatformContactMapper extends BaseDao<MchtPlatformContact, MchtPlatformContactExample> {
    int countByExample(MchtPlatformContactExample example);

    int deleteByExample(MchtPlatformContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtPlatformContact record);

    int insertSelective(MchtPlatformContact record);

    List<MchtPlatformContact> selectByExample(MchtPlatformContactExample example);

    MchtPlatformContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtPlatformContact record, @Param("example") MchtPlatformContactExample example);

    int updateByExample(@Param("record") MchtPlatformContact record, @Param("example") MchtPlatformContactExample example);

    int updateByPrimaryKeySelective(MchtPlatformContact record);

    int updateByPrimaryKey(MchtPlatformContact record);
}
