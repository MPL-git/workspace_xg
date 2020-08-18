package com.jf.dao;

import com.jf.entity.MchtBlPicExt;
import com.jf.entity.MchtBlPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBlPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBlPicExt findById(int id);

    MchtBlPicExt find(MchtBlPicExtExample example);

    List<MchtBlPicExt> list(MchtBlPicExtExample example);

    List<Integer> listId(MchtBlPicExtExample example);

    int count(MchtBlPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBlPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBlPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBlPicExtExample example);

}

