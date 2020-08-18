package com.jf.dao;

import com.jf.entity.ToutiaoAdInfoExt;
import com.jf.entity.ToutiaoAdInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoAdInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ToutiaoAdInfoExt findById(int id);

    ToutiaoAdInfoExt find(ToutiaoAdInfoExtExample example);

    List<ToutiaoAdInfoExt> list(ToutiaoAdInfoExtExample example);

    List<Integer> listId(ToutiaoAdInfoExtExample example);

    int count(ToutiaoAdInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ToutiaoAdInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ToutiaoAdInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ToutiaoAdInfoExtExample example);

}

