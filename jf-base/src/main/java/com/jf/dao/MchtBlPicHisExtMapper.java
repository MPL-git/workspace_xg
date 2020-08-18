package com.jf.dao;

import com.jf.entity.MchtBlPicHisExt;
import com.jf.entity.MchtBlPicHisExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBlPicHisExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBlPicHisExt findById(int id);

    MchtBlPicHisExt find(MchtBlPicHisExtExample example);

    List<MchtBlPicHisExt> list(MchtBlPicHisExtExample example);

    List<Integer> listId(MchtBlPicHisExtExample example);

    int count(MchtBlPicHisExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBlPicHisExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBlPicHisExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBlPicHisExtExample example);

}

