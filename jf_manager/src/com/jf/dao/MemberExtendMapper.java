package com.jf.dao;

import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberExtendMapper extends BaseDao<MemberExtend,MemberExtendExample>{
    int countByExample(MemberExtendExample example);

    int deleteByExample(MemberExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberExtend record);

    int insertSelective(MemberExtend record);

    List<MemberExtend> selectByExample(MemberExtendExample example);

    MemberExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberExtend record, @Param("example") MemberExtendExample example);

    int updateByExample(@Param("record") MemberExtend record, @Param("example") MemberExtendExample example);

    int updateByPrimaryKeySelective(MemberExtend record);

    int updateByPrimaryKey(MemberExtend record);
}