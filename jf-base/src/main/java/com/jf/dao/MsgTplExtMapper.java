package com.jf.dao;

import com.jf.entity.MsgTplExt;
import com.jf.entity.MsgTplExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgTplExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MsgTplExt findById(int id);

    MsgTplExt find(MsgTplExtExample example);

    List<MsgTplExt> list(MsgTplExtExample example);

    List<Integer> listId(MsgTplExtExample example);

    int count(MsgTplExtExample example);

    long sum(@Param("field") String field, @Param("example") MsgTplExtExample example);

    int max(@Param("field") String field, @Param("example") MsgTplExtExample example);

    int min(@Param("field") String field, @Param("example") MsgTplExtExample example);

}

