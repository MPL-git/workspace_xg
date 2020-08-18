package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinConfigMapper extends BaseDao<WeixinConfig, WeixinConfigExample> {
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
