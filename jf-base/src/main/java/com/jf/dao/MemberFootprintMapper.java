package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberFootprint;
import com.jf.entity.MemberFootprintExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFootprintMapper extends BaseDao<MemberFootprint, MemberFootprintExample> {
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
