package com.jf.dao;

import com.jf.entity.SportTeamLogExt;
import com.jf.entity.SportTeamLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTeamLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportTeamLogExt findById(int id);

    SportTeamLogExt find(SportTeamLogExtExample example);

    List<SportTeamLogExt> list(SportTeamLogExtExample example);

    List<Integer> listId(SportTeamLogExtExample example);

    int count(SportTeamLogExtExample example);

    long sum(@Param("field") String field, @Param("example") SportTeamLogExtExample example);

    int max(@Param("field") String field, @Param("example") SportTeamLogExtExample example);

    int min(@Param("field") String field, @Param("example") SportTeamLogExtExample example);

}

