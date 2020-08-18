package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.MemberCouponLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCouponLogMapper extends BaseDao<MemberCouponLog, MemberCouponLogExample> {
    int countByExample(MemberCouponLogExample example);

    int deleteByExample(MemberCouponLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCouponLog record);

    int insertSelective(MemberCouponLog record);

    List<MemberCouponLog> selectByExample(MemberCouponLogExample example);

    MemberCouponLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCouponLog record, @Param("example") MemberCouponLogExample example);

    int updateByExample(@Param("record") MemberCouponLog record, @Param("example") MemberCouponLogExample example);

    int updateByPrimaryKeySelective(MemberCouponLog record);

    int updateByPrimaryKey(MemberCouponLog record);
}
