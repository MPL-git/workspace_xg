package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
@Repository
public interface MsgTplMapper extends BaseDao<MsgTpl, MsgTplExample>{
    int countByExample(MsgTplExample example);

    int deleteByExample(MsgTplExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MsgTpl record);

    int insertSelective(MsgTpl record);

    List<MsgTpl> selectByExample(MsgTplExample example);

    MsgTpl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MsgTpl record, @Param("example") MsgTplExample example);

    int updateByExample(@Param("record") MsgTpl record, @Param("example") MsgTplExample example);

    int updateByPrimaryKeySelective(MsgTpl record);

    int updateByPrimaryKey(MsgTpl record);
}