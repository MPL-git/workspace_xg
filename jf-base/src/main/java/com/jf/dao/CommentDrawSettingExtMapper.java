package com.jf.dao;

import com.jf.entity.CommentDrawSettingExt;
import com.jf.entity.CommentDrawSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDrawSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CommentDrawSettingExt findById(int id);

    CommentDrawSettingExt find(CommentDrawSettingExtExample example);

    List<CommentDrawSettingExt> list(CommentDrawSettingExtExample example);

    List<Integer> listId(CommentDrawSettingExtExample example);

    int count(CommentDrawSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") CommentDrawSettingExtExample example);

    int max(@Param("field") String field, @Param("example") CommentDrawSettingExtExample example);

    int min(@Param("field") String field, @Param("example") CommentDrawSettingExtExample example);

}

