package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberControl;
import com.jf.entity.MemberControlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberControlMapper extends BaseDao<MemberControl, MemberControlExample> {
    int countByExample(MemberControlExample example);

    int deleteByExample(MemberControlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberControl record);

    int insertSelective(MemberControl record);

    List<MemberControl> selectByExample(MemberControlExample example);

    MemberControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberControl record, @Param("example") MemberControlExample example);

    int updateByExample(@Param("record") MemberControl record, @Param("example") MemberControlExample example);

    int updateByPrimaryKeySelective(MemberControl record);

    int updateByPrimaryKey(MemberControl record);
}
