package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.StaffReplyPic;
import com.jf.entity.StaffReplyPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffReplyPicMapper extends BaseDao<StaffReplyPic, StaffReplyPicExample> {
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
