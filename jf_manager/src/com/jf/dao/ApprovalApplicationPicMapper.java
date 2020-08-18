package com.jf.dao;

import com.jf.entity.ApprovalApplicationPic;
import com.jf.entity.ApprovalApplicationPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ApprovalApplicationPicMapper extends BaseDao<ApprovalApplicationPic, ApprovalApplicationPicExample> {
    int countByExample(ApprovalApplicationPicExample example);

    int deleteByExample(ApprovalApplicationPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApprovalApplicationPic record);

    int insertSelective(ApprovalApplicationPic record);

    List<ApprovalApplicationPic> selectByExample(ApprovalApplicationPicExample example);

    ApprovalApplicationPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApprovalApplicationPic record, @Param("example") ApprovalApplicationPicExample example);

    int updateByExample(@Param("record") ApprovalApplicationPic record, @Param("example") ApprovalApplicationPicExample example);

    int updateByPrimaryKeySelective(ApprovalApplicationPic record);

    int updateByPrimaryKey(ApprovalApplicationPic record);
}