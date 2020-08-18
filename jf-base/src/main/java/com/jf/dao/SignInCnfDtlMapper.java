package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInCnfDtlMapper extends BaseDao<SignInCnfDtl, SignInCnfDtlExample> {
    int countByExample(SignInCnfDtlExample example);

    int deleteByExample(SignInCnfDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignInCnfDtl record);

    int insertSelective(SignInCnfDtl record);

    List<SignInCnfDtl> selectByExample(SignInCnfDtlExample example);

    SignInCnfDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignInCnfDtl record, @Param("example") SignInCnfDtlExample example);

    int updateByExample(@Param("record") SignInCnfDtl record, @Param("example") SignInCnfDtlExample example);

    int updateByPrimaryKeySelective(SignInCnfDtl record);

    int updateByPrimaryKey(SignInCnfDtl record);
}
