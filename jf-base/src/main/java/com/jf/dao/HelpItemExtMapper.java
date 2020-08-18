package com.jf.dao;

import com.jf.entity.HelpItemExt;
import com.jf.entity.HelpItemExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpItemExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    HelpItemExt findById(int id);

    HelpItemExt find(HelpItemExtExample example);

    List<HelpItemExt> list(HelpItemExtExample example);

    List<Integer> listId(HelpItemExtExample example);

    int count(HelpItemExtExample example);

    long sum(@Param("field") String field, @Param("example") HelpItemExtExample example);

    int max(@Param("field") String field, @Param("example") HelpItemExtExample example);

    int min(@Param("field") String field, @Param("example") HelpItemExtExample example);

}

