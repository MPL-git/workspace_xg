package com.jf.dao;

import com.jf.entity.StaffReplyPic;
import com.jf.entity.StaffReplyPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffReplyPicMapper extends BaseDao<StaffReplyPic, StaffReplyPicExample>{
    int countByExample(StaffReplyPicExample example);

    int deleteByExample(StaffReplyPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffReplyPic record);

    int insertSelective(StaffReplyPic record);

    List<StaffReplyPic> selectByExample(StaffReplyPicExample example);

    StaffReplyPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffReplyPic record, @Param("example") StaffReplyPicExample example);

    int updateByExample(@Param("record") StaffReplyPic record, @Param("example") StaffReplyPicExample example);

    int updateByPrimaryKeySelective(StaffReplyPic record);

    int updateByPrimaryKey(StaffReplyPic record);
}