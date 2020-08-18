package com.jf.dao;

import com.jf.entity.AdInfoExt;
import com.jf.entity.AdInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AdInfoExt findById(int id);

    AdInfoExt find(AdInfoExtExample example);

    List<AdInfoExt> list(AdInfoExtExample example);

    List<Integer> listId(AdInfoExtExample example);

    int count(AdInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") AdInfoExtExample example);

    int max(@Param("field") String field, @Param("example") AdInfoExtExample example);

    int min(@Param("field") String field, @Param("example") AdInfoExtExample example);

}

