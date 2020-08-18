package com.jf.dao;

import com.jf.entity.SourceNicheProductExt;
import com.jf.entity.SourceNicheProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceNicheProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SourceNicheProductExt findById(int id);

    SourceNicheProductExt find(SourceNicheProductExtExample example);

    List<SourceNicheProductExt> list(SourceNicheProductExtExample example);

    List<Integer> listId(SourceNicheProductExtExample example);

    int count(SourceNicheProductExtExample example);

    long sum(@Param("field") String field, @Param("example") SourceNicheProductExtExample example);

    int max(@Param("field") String field, @Param("example") SourceNicheProductExtExample example);

    int min(@Param("field") String field, @Param("example") SourceNicheProductExtExample example);

}

