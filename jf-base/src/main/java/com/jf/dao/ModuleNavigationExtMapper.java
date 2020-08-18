package com.jf.dao;

import com.jf.entity.ModuleNavigationExt;
import com.jf.entity.ModuleNavigationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleNavigationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ModuleNavigationExt findById(int id);

    ModuleNavigationExt find(ModuleNavigationExtExample example);

    List<ModuleNavigationExt> list(ModuleNavigationExtExample example);

    List<Integer> listId(ModuleNavigationExtExample example);

    int count(ModuleNavigationExtExample example);

    long sum(@Param("field") String field, @Param("example") ModuleNavigationExtExample example);

    int max(@Param("field") String field, @Param("example") ModuleNavigationExtExample example);

    int min(@Param("field") String field, @Param("example") ModuleNavigationExtExample example);

}

