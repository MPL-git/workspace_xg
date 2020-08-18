package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SmsWhiteMobile;
import com.jf.entity.SmsWhiteMobileExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsWhiteMobileMapper  extends BaseDao<SmsWhiteMobile,SmsWhiteMobileExample> {
    int countByExample(SmsWhiteMobileExample example);

    int deleteByExample(SmsWhiteMobileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsWhiteMobile record);

    int insertSelective(SmsWhiteMobile record);

    List<SmsWhiteMobile> selectByExample(SmsWhiteMobileExample example);

    SmsWhiteMobile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsWhiteMobile record, @Param("example") SmsWhiteMobileExample example);

    int updateByExample(@Param("record") SmsWhiteMobile record, @Param("example") SmsWhiteMobileExample example);

    int updateByPrimaryKeySelective(SmsWhiteMobile record);

    int updateByPrimaryKey(SmsWhiteMobile record);
}