package com.jf.dao;

import com.jf.entity.SearchLogExt;
import com.jf.entity.SearchLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SearchLogExt findById(int id);

    SearchLogExt find(SearchLogExtExample example);

    List<SearchLogExt> list(SearchLogExtExample example);

    List<Integer> listId(SearchLogExtExample example);

    int count(SearchLogExtExample example);

    long sum(@Param("field") String field, @Param("example") SearchLogExtExample example);

    int max(@Param("field") String field, @Param("example") SearchLogExtExample example);

    int min(@Param("field") String field, @Param("example") SearchLogExtExample example);

}

