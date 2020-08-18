package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
@Repository
public interface MchtPlatformAuthPicChgMapper extends BaseDao<MchtPlatformAuthPicChg, MchtPlatformAuthPicChgExample>{
    int countByExample(MchtPlatformAuthPicChgExample example);

    int deleteByExample(MchtPlatformAuthPicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtPlatformAuthPicChg record);

    int insertSelective(MchtPlatformAuthPicChg record);

    List<MchtPlatformAuthPicChg> selectByExample(MchtPlatformAuthPicChgExample example);

    MchtPlatformAuthPicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtPlatformAuthPicChg record, @Param("example") MchtPlatformAuthPicChgExample example);

    int updateByExample(@Param("record") MchtPlatformAuthPicChg record, @Param("example") MchtPlatformAuthPicChgExample example);

    int updateByPrimaryKeySelective(MchtPlatformAuthPicChg record);

    int updateByPrimaryKey(MchtPlatformAuthPicChg record);
}