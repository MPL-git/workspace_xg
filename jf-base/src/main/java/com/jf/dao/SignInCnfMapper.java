package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInCnfMapper extends BaseDao<SignInCnf, SignInCnfExample> {
    int countByExample(SignInCnfExample example);

    int deleteByExample(SignInCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignInCnf record);

    int insertSelective(SignInCnf record);

    List<SignInCnf> selectByExample(SignInCnfExample example);

    SignInCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignInCnf record, @Param("example") SignInCnfExample example);

    int updateByExample(@Param("record") SignInCnf record, @Param("example") SignInCnfExample example);

    int updateByPrimaryKeySelective(SignInCnf record);

    int updateByPrimaryKey(SignInCnf record);
}
