package com.jf.dao;

import com.jf.entity.ViolatePunishStandardExt;
import com.jf.entity.ViolatePunishStandardExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolatePunishStandardExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ViolatePunishStandardExt findById(int id);

    ViolatePunishStandardExt find(ViolatePunishStandardExtExample example);

    List<ViolatePunishStandardExt> list(ViolatePunishStandardExtExample example);

    List<Integer> listId(ViolatePunishStandardExtExample example);

    int count(ViolatePunishStandardExtExample example);

    long sum(@Param("field") String field, @Param("example") ViolatePunishStandardExtExample example);

    int max(@Param("field") String field, @Param("example") ViolatePunishStandardExtExample example);

    int min(@Param("field") String field, @Param("example") ViolatePunishStandardExtExample example);

}

