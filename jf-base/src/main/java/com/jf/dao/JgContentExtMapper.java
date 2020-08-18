package com.jf.dao;

import com.jf.entity.JgContentExt;
import com.jf.entity.JgContentExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JgContentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    JgContentExt findById(int id);

    JgContentExt find(JgContentExtExample example);

    List<JgContentExt> list(JgContentExtExample example);

    List<Integer> listId(JgContentExtExample example);

    int count(JgContentExtExample example);

    long sum(@Param("field") String field, @Param("example") JgContentExtExample example);

    int max(@Param("field") String field, @Param("example") JgContentExtExample example);

    int min(@Param("field") String field, @Param("example") JgContentExtExample example);

}

