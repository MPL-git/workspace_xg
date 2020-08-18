package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberDynamic;
import com.jf.entity.MemberDynamicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDynamicMapper extends BaseDao<MemberDynamic, MemberDynamicExample> {
    int countByExample(MemberDynamicExample example);

    int deleteByExample(MemberDynamicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberDynamic record);

    int insertSelective(MemberDynamic record);

    List<MemberDynamic> selectByExample(MemberDynamicExample example);

    MemberDynamic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberDynamic record, @Param("example") MemberDynamicExample example);

    int updateByExample(@Param("record") MemberDynamic record, @Param("example") MemberDynamicExample example);

    int updateByPrimaryKeySelective(MemberDynamic record);

    int updateByPrimaryKey(MemberDynamic record);
}
