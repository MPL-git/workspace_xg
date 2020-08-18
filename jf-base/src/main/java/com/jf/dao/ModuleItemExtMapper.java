package com.jf.dao;

import com.jf.entity.ModuleItemExt;
import com.jf.entity.ModuleItemExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleItemExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ModuleItemExt findById(int id);

    ModuleItemExt find(ModuleItemExtExample example);

    List<ModuleItemExt> list(ModuleItemExtExample example);

    List<Integer> listId(ModuleItemExtExample example);

    int count(ModuleItemExtExample example);

    long sum(@Param("field") String field, @Param("example") ModuleItemExtExample example);

    int max(@Param("field") String field, @Param("example") ModuleItemExtExample example);

    int min(@Param("field") String field, @Param("example") ModuleItemExtExample example);

}

