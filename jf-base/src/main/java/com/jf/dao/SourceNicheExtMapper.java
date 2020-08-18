package com.jf.dao;

import com.jf.entity.SourceNicheExt;
import com.jf.entity.SourceNicheExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceNicheExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SourceNicheExt findById(int id);

    SourceNicheExt find(SourceNicheExtExample example);

    List<SourceNicheExt> list(SourceNicheExtExample example);

    List<Integer> listId(SourceNicheExtExample example);

    int count(SourceNicheExtExample example);

    long sum(@Param("field") String field, @Param("example") SourceNicheExtExample example);

    int max(@Param("field") String field, @Param("example") SourceNicheExtExample example);

    int min(@Param("field") String field, @Param("example") SourceNicheExtExample example);

}

