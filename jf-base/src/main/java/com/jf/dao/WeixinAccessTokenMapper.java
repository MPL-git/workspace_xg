package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinAccessToken;
import com.jf.entity.WeixinAccessTokenExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinAccessTokenMapper extends BaseDao<WeixinAccessToken, WeixinAccessTokenExample> {
    int countByExample(WeixinAccessTokenExample example);

    int deleteByExample(WeixinAccessTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinAccessToken record);

    int insertSelective(WeixinAccessToken record);

    List<WeixinAccessToken> selectByExample(WeixinAccessTokenExample example);

    WeixinAccessToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinAccessToken record, @Param("example") WeixinAccessTokenExample example);

    int updateByExample(@Param("record") WeixinAccessToken record, @Param("example") WeixinAccessTokenExample example);

    int updateByPrimaryKeySelective(WeixinAccessToken record);

    int updateByPrimaryKey(WeixinAccessToken record);
}
