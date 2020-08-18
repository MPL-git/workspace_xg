package com.jf.dao;

import com.jf.entity.CommentPicExt;
import com.jf.entity.CommentPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CommentPicExt findById(int id);

    CommentPicExt find(CommentPicExtExample example);

    List<CommentPicExt> list(CommentPicExtExample example);

    List<Integer> listId(CommentPicExtExample example);

    int count(CommentPicExtExample example);

    long sum(@Param("field") String field, @Param("example") CommentPicExtExample example);

    int max(@Param("field") String field, @Param("example") CommentPicExtExample example);

    int min(@Param("field") String field, @Param("example") CommentPicExtExample example);

}

