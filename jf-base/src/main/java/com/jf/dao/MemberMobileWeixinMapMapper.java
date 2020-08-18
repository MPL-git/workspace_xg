package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberMobileWeixinMap;
import com.jf.entity.MemberMobileWeixinMapExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMobileWeixinMapMapper extends BaseDao<MemberMobileWeixinMap, MemberMobileWeixinMapExample> {
    int countByExample(MemberMobileWeixinMapExample example);

    int deleteByExample(MemberMobileWeixinMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberMobileWeixinMap record);

    int insertSelective(MemberMobileWeixinMap record);

    List<MemberMobileWeixinMap> selectByExample(MemberMobileWeixinMapExample example);

    MemberMobileWeixinMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberMobileWeixinMap record, @Param("example") MemberMobileWeixinMapExample example);

    int updateByExample(@Param("record") MemberMobileWeixinMap record, @Param("example") MemberMobileWeixinMapExample example);

    int updateByPrimaryKeySelective(MemberMobileWeixinMap record);

    int updateByPrimaryKey(MemberMobileWeixinMap record);
}
