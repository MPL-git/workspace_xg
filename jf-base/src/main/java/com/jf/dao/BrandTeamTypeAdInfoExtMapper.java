package com.jf.dao;

import com.jf.entity.BrandTeamTypeAdInfoExt;
import com.jf.entity.BrandTeamTypeAdInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTeamTypeAdInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BrandTeamTypeAdInfoExt findById(int id);

    BrandTeamTypeAdInfoExt find(BrandTeamTypeAdInfoExtExample example);

    List<BrandTeamTypeAdInfoExt> list(BrandTeamTypeAdInfoExtExample example);

    List<Integer> listId(BrandTeamTypeAdInfoExtExample example);

    int count(BrandTeamTypeAdInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") BrandTeamTypeAdInfoExtExample example);

    int max(@Param("field") String field, @Param("example") BrandTeamTypeAdInfoExtExample example);

    int min(@Param("field") String field, @Param("example") BrandTeamTypeAdInfoExtExample example);

}

