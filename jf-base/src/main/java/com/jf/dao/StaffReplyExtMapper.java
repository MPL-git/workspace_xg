package com.jf.dao;

import com.jf.entity.StaffReplyExt;
import com.jf.entity.StaffReplyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffReplyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StaffReplyExt findById(int id);

    StaffReplyExt find(StaffReplyExtExample example);

    List<StaffReplyExt> list(StaffReplyExtExample example);

    List<Integer> listId(StaffReplyExtExample example);

    int count(StaffReplyExtExample example);

    long sum(@Param("field") String field, @Param("example") StaffReplyExtExample example);

    int max(@Param("field") String field, @Param("example") StaffReplyExtExample example);

    int min(@Param("field") String field, @Param("example") StaffReplyExtExample example);

}

