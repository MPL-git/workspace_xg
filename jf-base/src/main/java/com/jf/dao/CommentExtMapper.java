package com.jf.dao;

import com.jf.entity.CommentExt;
import com.jf.entity.CommentExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CommentExt findById(int id);

    CommentExt find(CommentExtExample example);

    List<CommentExt> list(CommentExtExample example);

    List<Integer> listId(CommentExtExample example);

    int count(CommentExtExample example);

    long sum(@Param("field") String field, @Param("example") CommentExtExample example);

    int max(@Param("field") String field, @Param("example") CommentExtExample example);

    int min(@Param("field") String field, @Param("example") CommentExtExample example);

}

