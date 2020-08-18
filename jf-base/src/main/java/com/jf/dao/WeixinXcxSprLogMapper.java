package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinXcxSprLog;
import com.jf.entity.WeixinXcxSprLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
