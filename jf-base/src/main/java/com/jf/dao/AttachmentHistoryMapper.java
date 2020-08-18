package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.AttachmentHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentHistoryMapper extends BaseDao<AttachmentHistory, AttachmentHistoryExample> {
    int countByExample(AttachmentHistoryExample example);

    int deleteByExample(AttachmentHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttachmentHistory record);

    int insertSelective(AttachmentHistory record);

    List<AttachmentHistory> selectByExample(AttachmentHistoryExample example);

    AttachmentHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttachmentHistory record, @Param("example") AttachmentHistoryExample example);

    int updateByExample(@Param("record") AttachmentHistory record, @Param("example") AttachmentHistoryExample example);

    int updateByPrimaryKeySelective(AttachmentHistory record);

    int updateByPrimaryKey(AttachmentHistory record);
}
