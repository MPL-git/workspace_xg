package com.jf.dao;

import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawCnfMapper extends BaseDao<WithdrawCnf, WithdrawCnfExample> {
    int countByExample(WithdrawCnfExample example);

    int deleteByExample(WithdrawCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawCnf record);

    int insertSelective(WithdrawCnf record);

    List<WithdrawCnf> selectByExample(WithdrawCnfExample example);

    WithdrawCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawCnf record, @Param("example") WithdrawCnfExample example);

    int updateByExample(@Param("record") WithdrawCnf record, @Param("example") WithdrawCnfExample example);

    int updateByPrimaryKeySelective(WithdrawCnf record);

    int updateByPrimaryKey(WithdrawCnf record);
}