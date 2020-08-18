package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SubOrderAttachment;
import com.jf.entity.SubOrderAttachmentExample;
@Repository
public interface SubOrderAttachmentMapper extends BaseDao<SubOrderAttachment, SubOrderAttachmentExample>{
    int countByExample(SubOrderAttachmentExample example);

    int deleteByExample(SubOrderAttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubOrderAttachment record);

    int insertSelective(SubOrderAttachment record);

    List<SubOrderAttachment> selectByExample(SubOrderAttachmentExample example);

    SubOrderAttachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubOrderAttachment record, @Param("example") SubOrderAttachmentExample example);

    int updateByExample(@Param("record") SubOrderAttachment record, @Param("example") SubOrderAttachmentExample example);

    int updateByPrimaryKeySelective(SubOrderAttachment record);

    int updateByPrimaryKey(SubOrderAttachment record);
}