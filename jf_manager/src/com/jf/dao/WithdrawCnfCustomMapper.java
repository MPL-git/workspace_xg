package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfCustom;
import com.jf.entity.WithdrawCnfCustomExample;

@Repository
public interface WithdrawCnfCustomMapper {
    public int countByCustomExample(WithdrawCnfCustomExample example);

    public List<WithdrawCnfCustom> selectByCustomExample(WithdrawCnfCustomExample example);

    public WithdrawCnfCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") WithdrawCnf record, @Param("example") WithdrawCnfCustomExample example);

}