package com.jf.dao;

import com.jf.entity.InformationExt;
import com.jf.entity.InformationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    InformationExt findById(int id);

    InformationExt find(InformationExtExample example);

    List<InformationExt> list(InformationExtExample example);

    List<Integer> listId(InformationExtExample example);

    int count(InformationExtExample example);

    long sum(@Param("field") String field, @Param("example") InformationExtExample example);

    int max(@Param("field") String field, @Param("example") InformationExtExample example);

    int min(@Param("field") String field, @Param("example") InformationExtExample example);

}

