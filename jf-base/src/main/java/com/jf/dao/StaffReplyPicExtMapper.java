package com.jf.dao;

import com.jf.entity.StaffReplyPicExt;
import com.jf.entity.StaffReplyPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffReplyPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StaffReplyPicExt findById(int id);

    StaffReplyPicExt find(StaffReplyPicExtExample example);

    List<StaffReplyPicExt> list(StaffReplyPicExtExample example);

    List<Integer> listId(StaffReplyPicExtExample example);

    int count(StaffReplyPicExtExample example);

    long sum(@Param("field") String field, @Param("example") StaffReplyPicExtExample example);

    int max(@Param("field") String field, @Param("example") StaffReplyPicExtExample example);

    int min(@Param("field") String field, @Param("example") StaffReplyPicExtExample example);

}

