package com.jf.dao;

import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViolateWordMapper extends BaseDao<ViolateWord, ViolateWordExample>{
    int countByExample(ViolateWordExample example);

    int deleteByExample(ViolateWordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolateWord record);

    int insertSelective(ViolateWord record);

    List<ViolateWord> selectByExample(ViolateWordExample example);

    ViolateWord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolateWord record, @Param("example") ViolateWordExample example);

    int updateByExample(@Param("record") ViolateWord record, @Param("example") ViolateWordExample example);

    int updateByPrimaryKeySelective(ViolateWord record);

    int updateByPrimaryKey(ViolateWord record);
}