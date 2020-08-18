package com.jf.dao;

import com.jf.entity.AttachmentExt;
import com.jf.entity.AttachmentExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AttachmentExt findById(int id);

    AttachmentExt find(AttachmentExtExample example);

    List<AttachmentExt> list(AttachmentExtExample example);

    List<Integer> listId(AttachmentExtExample example);

    int count(AttachmentExtExample example);

    long sum(@Param("field") String field, @Param("example") AttachmentExtExample example);

    int max(@Param("field") String field, @Param("example") AttachmentExtExample example);

    int min(@Param("field") String field, @Param("example") AttachmentExtExample example);

}

