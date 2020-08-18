package com.jf.dao;

import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactExample;
import com.jf.entity.MchtPlatformContactKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtPlatformContactMapper extends BaseDao<MchtPlatformContact, MchtPlatformContactExample>{
    int countByExample(MchtPlatformContactExample example);

    int deleteByExample(MchtPlatformContactExample example);

    int deleteByPrimaryKey(MchtPlatformContactKey key);

    int insert(MchtPlatformContact record);

    int insertSelective(MchtPlatformContact record);

    List<MchtPlatformContact> selectByExample(MchtPlatformContactExample example);

    MchtPlatformContact selectByPrimaryKey(MchtPlatformContactKey key);

    int updateByExampleSelective(@Param("record") MchtPlatformContact record, @Param("example") MchtPlatformContactExample example);

    int updateByExample(@Param("record") MchtPlatformContact record, @Param("example") MchtPlatformContactExample example);

    int updateByPrimaryKeySelective(MchtPlatformContact record);

    int updateByPrimaryKey(MchtPlatformContact record);
}