package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberPv;
import com.jf.entity.MemberPvExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPvMapper extends BaseDao<MemberPv, MemberPvExample> {
    int countByExample(MemberPvExample example);

    int deleteByExample(MemberPvExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberPv record);

    int insertSelective(MemberPv record);

    List<MemberPv> selectByExample(MemberPvExample example);

    MemberPv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberPv record, @Param("example") MemberPvExample example);

    int updateByExample(@Param("record") MemberPv record, @Param("example") MemberPvExample example);

    int updateByPrimaryKeySelective(MemberPv record);

    int updateByPrimaryKey(MemberPv record);
}