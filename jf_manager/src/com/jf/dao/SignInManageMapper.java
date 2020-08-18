package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SignInManage;
import com.jf.entity.SignInManageExample;
@Repository
public interface SignInManageMapper extends BaseDao<SignInManage, SignInManageExample>{
    int countByExample(SignInManageExample example);

    int deleteByExample(SignInManageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignInManage record);

    int insertSelective(SignInManage record);

    List<SignInManage> selectByExample(SignInManageExample example);

    SignInManage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignInManage record, @Param("example") SignInManageExample example);

    int updateByExample(@Param("record") SignInManage record, @Param("example") SignInManageExample example);

    int updateByPrimaryKeySelective(SignInManage record);

    int updateByPrimaryKey(SignInManage record);
}