package com.jf.dao;

import com.jf.entity.StyleExt;
import com.jf.entity.StyleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StyleExt findById(int id);

    StyleExt find(StyleExtExample example);

    List<StyleExt> list(StyleExtExample example);

    List<Integer> listId(StyleExtExample example);

    int count(StyleExtExample example);

    long sum(@Param("field") String field, @Param("example") StyleExtExample example);

    int max(@Param("field") String field, @Param("example") StyleExtExample example);

    int min(@Param("field") String field, @Param("example") StyleExtExample example);

}

