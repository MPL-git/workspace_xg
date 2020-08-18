package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinOauthRedirectUrl;
import com.jf.entity.WeixinOauthRedirectUrlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinOauthRedirectUrlMapper extends BaseDao<WeixinOauthRedirectUrl, WeixinOauthRedirectUrlExample> {
    int countByExample(WeixinOauthRedirectUrlExample example);

    int deleteByExample(WeixinOauthRedirectUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinOauthRedirectUrl record);

    int insertSelective(WeixinOauthRedirectUrl record);

    List<WeixinOauthRedirectUrl> selectByExample(WeixinOauthRedirectUrlExample example);

    WeixinOauthRedirectUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinOauthRedirectUrl record, @Param("example") WeixinOauthRedirectUrlExample example);

    int updateByExample(@Param("record") WeixinOauthRedirectUrl record, @Param("example") WeixinOauthRedirectUrlExample example);

    int updateByPrimaryKeySelective(WeixinOauthRedirectUrl record);

    int updateByPrimaryKey(WeixinOauthRedirectUrl record);
}
