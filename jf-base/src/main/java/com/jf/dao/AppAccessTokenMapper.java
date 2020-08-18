package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.AppAccessToken;
import com.jf.entity.AppAccessTokenExample;

@Repository
public interface AppAccessTokenMapper extends BaseDao<AppAccessToken, AppAccessTokenExample> {
    int countByExample(AppAccessTokenExample example);

    int deleteByExample(AppAccessTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppAccessToken record);

    int insertSelective(AppAccessToken record);

    List<AppAccessToken> selectByExample(AppAccessTokenExample example);

    AppAccessToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppAccessToken record, @Param("example") AppAccessTokenExample example);

    int updateByExample(@Param("record") AppAccessToken record, @Param("example") AppAccessTokenExample example);

    int updateByPrimaryKeySelective(AppAccessToken record);

    int updateByPrimaryKey(AppAccessToken record);
}
