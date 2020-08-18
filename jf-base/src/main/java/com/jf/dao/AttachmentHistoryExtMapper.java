package com.jf.dao;

import com.jf.entity.AttachmentHistoryExt;
import com.jf.entity.AttachmentHistoryExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentHistoryExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AttachmentHistoryExt findById(int id);

    AttachmentHistoryExt find(AttachmentHistoryExtExample example);

    List<AttachmentHistoryExt> list(AttachmentHistoryExtExample example);

    List<Integer> listId(AttachmentHistoryExtExample example);

    int count(AttachmentHistoryExtExample example);

    long sum(@Param("field") String field, @Param("example") AttachmentHistoryExtExample example);

    int max(@Param("field") String field, @Param("example") AttachmentHistoryExtExample example);

    int min(@Param("field") String field, @Param("example") AttachmentHistoryExtExample example);

}

