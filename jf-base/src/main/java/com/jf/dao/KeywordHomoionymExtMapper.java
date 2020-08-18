package com.jf.dao;

import com.jf.entity.KeywordHomoionymExt;
import com.jf.entity.KeywordHomoionymExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordHomoionymExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    KeywordHomoionymExt findById(int id);

    KeywordHomoionymExt find(KeywordHomoionymExtExample example);

    List<KeywordHomoionymExt> list(KeywordHomoionymExtExample example);

    List<Integer> listId(KeywordHomoionymExtExample example);

    int count(KeywordHomoionymExtExample example);

    long sum(@Param("field") String field, @Param("example") KeywordHomoionymExtExample example);

    int max(@Param("field") String field, @Param("example") KeywordHomoionymExtExample example);

    int min(@Param("field") String field, @Param("example") KeywordHomoionymExtExample example);

}

