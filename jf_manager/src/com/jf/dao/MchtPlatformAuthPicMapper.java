package com.jf.dao;

import com.jf.entity.MchtPlatformAuthPic;
import com.jf.entity.MchtPlatformAuthPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtPlatformAuthPicMapper extends BaseDao<MchtPlatformAuthPic, MchtPlatformAuthPicExample> {
    int countByExample(MchtPlatformAuthPicExample example);

    int deleteByExample(MchtPlatformAuthPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtPlatformAuthPic record);

    int insertSelective(MchtPlatformAuthPic record);

    List<MchtPlatformAuthPic> selectByExample(MchtPlatformAuthPicExample example);

    MchtPlatformAuthPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtPlatformAuthPic record, @Param("example") MchtPlatformAuthPicExample example);

    int updateByExample(@Param("record") MchtPlatformAuthPic record, @Param("example") MchtPlatformAuthPicExample example);

    int updateByPrimaryKeySelective(MchtPlatformAuthPic record);

    int updateByPrimaryKey(MchtPlatformAuthPic record);
}