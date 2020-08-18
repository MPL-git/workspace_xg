package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFootprint;
import com.jf.entity.MemberFootprintExample;
@Repository
public interface MemberFootprintMapper extends BaseDao<MemberFootprint, MemberFootprintExample>{
    int countByExample(MemberFootprintExample example);

    int deleteByExample(MemberFootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFootprint record);

    int insertSelective(MemberFootprint record);

    List<MemberFootprint> selectByExample(MemberFootprintExample example);

    MemberFootprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFootprint record, @Param("example") MemberFootprintExample example);

    int updateByExample(@Param("record") MemberFootprint record, @Param("example") MemberFootprintExample example);

    int updateByPrimaryKeySelective(MemberFootprint record);

    int updateByPrimaryKey(MemberFootprint record);
}