package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.MobileVerificationCode;
import com.jf.entity.MobileVerificationCodeExample;

@Repository
public interface MobileVerificationCodeMapper extends BaseDao<MobileVerificationCode, MobileVerificationCodeExample> {
    int countByExample(MobileVerificationCodeExample example);

    int deleteByExample(MobileVerificationCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MobileVerificationCode record);

    int insertSelective(MobileVerificationCode record);

    List<MobileVerificationCode> selectByExample(MobileVerificationCodeExample example);

    MobileVerificationCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MobileVerificationCode record, @Param("example") MobileVerificationCodeExample example);

    int updateByExample(@Param("record") MobileVerificationCode record, @Param("example") MobileVerificationCodeExample example);

    int updateByPrimaryKeySelective(MobileVerificationCode record);

    int updateByPrimaryKey(MobileVerificationCode record);
}
