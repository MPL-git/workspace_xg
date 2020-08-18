package com.jf.dao;

import com.jf.entity.MchtBlPicChgExt;
import com.jf.entity.MchtBlPicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBlPicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBlPicChgExt findById(int id);

    MchtBlPicChgExt find(MchtBlPicChgExtExample example);

    List<MchtBlPicChgExt> list(MchtBlPicChgExtExample example);

    List<Integer> listId(MchtBlPicChgExtExample example);

    int count(MchtBlPicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBlPicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBlPicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBlPicChgExtExample example);

}

