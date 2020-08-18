package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SmsBlackIp;
import com.jf.entity.SmsBlackIpExample;

@Repository
public interface SmsBlackIpMapper extends BaseDao<SmsBlackIp, SmsBlackIpExample> {
    int countByExample(SmsBlackIpExample example);

    int deleteByExample(SmsBlackIpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsBlackIp record);

    int insertSelective(SmsBlackIp record);

    List<SmsBlackIp> selectByExample(SmsBlackIpExample example);

    SmsBlackIp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsBlackIp record, @Param("example") SmsBlackIpExample example);

    int updateByExample(@Param("record") SmsBlackIp record, @Param("example") SmsBlackIpExample example);

    int updateByPrimaryKeySelective(SmsBlackIp record);

    int updateByPrimaryKey(SmsBlackIp record);
}
