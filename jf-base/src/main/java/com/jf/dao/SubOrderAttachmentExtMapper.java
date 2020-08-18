package com.jf.dao;

import com.jf.entity.SubOrderAttachmentExt;
import com.jf.entity.SubOrderAttachmentExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubOrderAttachmentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SubOrderAttachmentExt findById(int id);

    SubOrderAttachmentExt find(SubOrderAttachmentExtExample example);

    List<SubOrderAttachmentExt> list(SubOrderAttachmentExtExample example);

    List<Integer> listId(SubOrderAttachmentExtExample example);

    int count(SubOrderAttachmentExtExample example);

    long sum(@Param("field") String field, @Param("example") SubOrderAttachmentExtExample example);

    int max(@Param("field") String field, @Param("example") SubOrderAttachmentExtExample example);

    int min(@Param("field") String field, @Param("example") SubOrderAttachmentExtExample example);

}

