package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SmsBlackMobile;
import com.jf.entity.SmsBlackMobileExample;

@Repository
public interface SmsBlackMobileMapper extends BaseDao<SmsBlackMobile, SmsBlackMobileExample> {
    int countByExample(SmsBlackMobileExample example);

    int deleteByExample(SmsBlackMobileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsBlackMobile record);

    int insertSelective(SmsBlackMobile record);

    List<SmsBlackMobile> selectByExample(SmsBlackMobileExample example);

    SmsBlackMobile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsBlackMobile record, @Param("example") SmsBlackMobileExample example);

    int updateByExample(@Param("record") SmsBlackMobile record, @Param("example") SmsBlackMobileExample example);

    int updateByPrimaryKeySelective(SmsBlackMobile record);

    int updateByPrimaryKey(SmsBlackMobile record);
}
