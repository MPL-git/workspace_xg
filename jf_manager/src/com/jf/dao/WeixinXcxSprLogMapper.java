package com.jf.dao;

import com.jf.entity.WeixinXcxSprLog;
import com.jf.entity.WeixinXcxSprLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeixinXcxSprLogMapper extends BaseDao<WeixinXcxSprLog, WeixinXcxSprLogExample> {
    int countByExample(WeixinXcxSprLogExample example);

    int deleteByExample(WeixinXcxSprLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinXcxSprLog record);

    int insertSelective(WeixinXcxSprLog record);

    List<WeixinXcxSprLog> selectByExample(WeixinXcxSprLogExample example);

    WeixinXcxSprLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinXcxSprLog record, @Param("example") WeixinXcxSprLogExample example);

    int updateByExample(@Param("record") WeixinXcxSprLog record, @Param("example") WeixinXcxSprLogExample example);

    int updateByPrimaryKeySelective(WeixinXcxSprLog record);

    int updateByPrimaryKey(WeixinXcxSprLog record);
}