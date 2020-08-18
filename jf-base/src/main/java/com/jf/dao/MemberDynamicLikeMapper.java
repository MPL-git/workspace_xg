package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberDynamicLike;
import com.jf.entity.MemberDynamicLikeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDynamicLikeMapper extends BaseDao<MemberDynamicLike, MemberDynamicLikeExample> {
    int countByExample(MemberDynamicLikeExample example);

    int deleteByExample(MemberDynamicLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberDynamicLike record);

    int insertSelective(MemberDynamicLike record);

    List<MemberDynamicLike> selectByExample(MemberDynamicLikeExample example);

    MemberDynamicLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberDynamicLike record, @Param("example") MemberDynamicLikeExample example);

    int updateByExample(@Param("record") MemberDynamicLike record, @Param("example") MemberDynamicLikeExample example);

    int updateByPrimaryKeySelective(MemberDynamicLike record);

    int updateByPrimaryKey(MemberDynamicLike record);
}
