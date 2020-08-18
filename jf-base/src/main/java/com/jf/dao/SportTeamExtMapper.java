package com.jf.dao;

import com.jf.entity.SportTeamExt;
import com.jf.entity.SportTeamExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTeamExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportTeamExt findById(int id);

    SportTeamExt find(SportTeamExtExample example);

    List<SportTeamExt> list(SportTeamExtExample example);

    List<Integer> listId(SportTeamExtExample example);

    int count(SportTeamExtExample example);

    long sum(@Param("field") String field, @Param("example") SportTeamExtExample example);

    int max(@Param("field") String field, @Param("example") SportTeamExtExample example);

    int min(@Param("field") String field, @Param("example") SportTeamExtExample example);

}

