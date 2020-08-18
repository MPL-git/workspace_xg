package com.jf.dao;

import com.jf.entity.MchtCloseApplication;
import com.jf.entity.MchtCloseApplicationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtCloseApplicationMapper extends BaseDao<MchtCloseApplication, MchtCloseApplicationExample>{
    int countByExample(MchtCloseApplicationExample example);

    int deleteByExample(MchtCloseApplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtCloseApplication record);

    int insertSelective(MchtCloseApplication record);

    List<MchtCloseApplication> selectByExample(MchtCloseApplicationExample example);

    MchtCloseApplication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtCloseApplication record, @Param("example") MchtCloseApplicationExample example);

    int updateByExample(@Param("record") MchtCloseApplication record, @Param("example") MchtCloseApplicationExample example);

    int updateByPrimaryKeySelective(MchtCloseApplication record);

    int updateByPrimaryKey(MchtCloseApplication record);
}