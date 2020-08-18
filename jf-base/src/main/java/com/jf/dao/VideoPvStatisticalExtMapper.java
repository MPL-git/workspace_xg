package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.VideoPvStatisticalExt;
import com.jf.entity.VideoPvStatisticalExtExample;

@Repository
public interface VideoPvStatisticalExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    VideoPvStatisticalExt findById(int id);

    VideoPvStatisticalExt find(VideoPvStatisticalExtExample example);

    List<VideoPvStatisticalExt> list(VideoPvStatisticalExtExample example);

    List<Integer> listId(VideoPvStatisticalExtExample example);

    int count(VideoPvStatisticalExtExample example);

    long sum(@Param("field") String field, @Param("example") VideoPvStatisticalExtExample example);

    int max(@Param("field") String field, @Param("example") VideoPvStatisticalExtExample example);

    int min(@Param("field") String field, @Param("example") VideoPvStatisticalExtExample example);

}

