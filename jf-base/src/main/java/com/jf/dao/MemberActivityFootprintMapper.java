package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberActivityFootprint;
import com.jf.entity.MemberActivityFootprintExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberActivityFootprintMapper extends BaseDao<MemberActivityFootprint, MemberActivityFootprintExample> {
    int countByExample(MemberActivityFootprintExample example);

    int deleteByExample(MemberActivityFootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberActivityFootprint record);

    int insertSelective(MemberActivityFootprint record);

    List<MemberActivityFootprint> selectByExample(MemberActivityFootprintExample example);

    MemberActivityFootprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberActivityFootprint record, @Param("example") MemberActivityFootprintExample example);

    int updateByExample(@Param("record") MemberActivityFootprint record, @Param("example") MemberActivityFootprintExample example);

    int updateByPrimaryKeySelective(MemberActivityFootprint record);

    int updateByPrimaryKey(MemberActivityFootprint record);
}
