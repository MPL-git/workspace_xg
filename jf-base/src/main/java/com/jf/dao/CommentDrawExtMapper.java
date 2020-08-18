package com.jf.dao;

import com.jf.entity.CommentDrawExt;
import com.jf.entity.CommentDrawExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDrawExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CommentDrawExt findById(int id);

    CommentDrawExt find(CommentDrawExtExample example);

    List<CommentDrawExt> list(CommentDrawExtExample example);

    List<Integer> listId(CommentDrawExtExample example);

    int count(CommentDrawExtExample example);

    long sum(@Param("field") String field, @Param("example") CommentDrawExtExample example);

    int max(@Param("field") String field, @Param("example") CommentDrawExtExample example);

    int min(@Param("field") String field, @Param("example") CommentDrawExtExample example);

}

