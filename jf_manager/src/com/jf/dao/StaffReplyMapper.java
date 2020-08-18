package com.jf.dao;

import com.jf.entity.StaffReply;
import com.jf.entity.StaffReplyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffReplyMapper extends BaseDao<StaffReply, StaffReplyExample>{
    int countByExample(StaffReplyExample example);

    int deleteByExample(StaffReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffReply record);

    int insertSelective(StaffReply record);

    List<StaffReply> selectByExample(StaffReplyExample example);

    StaffReply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffReply record, @Param("example") StaffReplyExample example);

    int updateByExample(@Param("record") StaffReply record, @Param("example") StaffReplyExample example);

    int updateByPrimaryKeySelective(StaffReply record);

    int updateByPrimaryKey(StaffReply record);
}