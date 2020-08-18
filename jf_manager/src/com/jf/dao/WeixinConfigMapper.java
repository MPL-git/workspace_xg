package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;
@Repository
public interface WeixinConfigMapper extends BaseDao<WeixinConfig, WeixinConfigExample>{
    int countByExample(WeixinConfigExample example);

    int deleteByExample(WeixinConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinConfig record);

    int insertSelective(WeixinConfig record);

    List<WeixinConfig> selectByExample(WeixinConfigExample example);

    WeixinConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinConfig record, @Param("example") WeixinConfigExample example);

    int updateByExample(@Param("record") WeixinConfig record, @Param("example") WeixinConfigExample example);

    int updateByPrimaryKeySelective(WeixinConfig record);

    int updateByPrimaryKey(WeixinConfig record);
}