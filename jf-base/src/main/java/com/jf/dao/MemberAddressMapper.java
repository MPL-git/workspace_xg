package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAddressMapper extends BaseDao<MemberAddress, MemberAddressExample> {
    int countByExample(MemberAddressExample example);

    int deleteByExample(MemberAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    List<MemberAddress> selectByExample(MemberAddressExample example);

    MemberAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAddress record, @Param("example") MemberAddressExample example);

    int updateByExample(@Param("record") MemberAddress record, @Param("example") MemberAddressExample example);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);
}
