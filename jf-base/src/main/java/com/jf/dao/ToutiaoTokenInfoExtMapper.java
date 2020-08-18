package com.jf.dao;

import com.jf.entity.ToutiaoTokenInfoExt;
import com.jf.entity.ToutiaoTokenInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoTokenInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ToutiaoTokenInfoExt findById(int id);

    ToutiaoTokenInfoExt find(ToutiaoTokenInfoExtExample example);

    List<ToutiaoTokenInfoExt> list(ToutiaoTokenInfoExtExample example);

    List<Integer> listId(ToutiaoTokenInfoExtExample example);

    int count(ToutiaoTokenInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ToutiaoTokenInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ToutiaoTokenInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ToutiaoTokenInfoExtExample example);

}

