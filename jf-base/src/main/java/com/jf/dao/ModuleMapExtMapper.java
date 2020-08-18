package com.jf.dao;

import com.jf.entity.ModuleMapExt;
import com.jf.entity.ModuleMapExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ModuleMapExt findById(int id);

    ModuleMapExt find(ModuleMapExtExample example);

    List<ModuleMapExt> list(ModuleMapExtExample example);

    List<Integer> listId(ModuleMapExtExample example);

    int count(ModuleMapExtExample example);

    long sum(@Param("field") String field, @Param("example") ModuleMapExtExample example);

    int max(@Param("field") String field, @Param("example") ModuleMapExtExample example);

    int min(@Param("field") String field, @Param("example") ModuleMapExtExample example);

}

