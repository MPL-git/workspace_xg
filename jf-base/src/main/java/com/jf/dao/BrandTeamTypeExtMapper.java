package com.jf.dao;

import com.jf.entity.BrandTeamTypeExt;
import com.jf.entity.BrandTeamTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTeamTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BrandTeamTypeExt findById(int id);

    BrandTeamTypeExt find(BrandTeamTypeExtExample example);

    List<BrandTeamTypeExt> list(BrandTeamTypeExtExample example);

    List<Integer> listId(BrandTeamTypeExtExample example);

    int count(BrandTeamTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") BrandTeamTypeExtExample example);

    int max(@Param("field") String field, @Param("example") BrandTeamTypeExtExample example);

    int min(@Param("field") String field, @Param("example") BrandTeamTypeExtExample example);

}

