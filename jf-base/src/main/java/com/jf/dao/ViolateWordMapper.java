package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.ViolateWord;
import com.jf.entity.ViolateWordExample;

@Repository
public interface ViolateWordMapper extends BaseDao<ViolateWord, ViolateWordExample> {
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
