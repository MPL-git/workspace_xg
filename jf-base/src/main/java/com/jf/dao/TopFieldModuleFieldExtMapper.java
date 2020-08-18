package com.jf.dao;

import com.jf.entity.TopFieldModuleFieldExt;
import com.jf.entity.TopFieldModuleFieldExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopFieldModuleFieldExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    TopFieldModuleFieldExt findById(int id);

    TopFieldModuleFieldExt find(TopFieldModuleFieldExtExample example);

    List<TopFieldModuleFieldExt> list(TopFieldModuleFieldExtExample example);

    List<Integer> listId(TopFieldModuleFieldExtExample example);

    int count(TopFieldModuleFieldExtExample example);

    long sum(@Param("field") String field, @Param("example") TopFieldModuleFieldExtExample example);

    int max(@Param("field") String field, @Param("example") TopFieldModuleFieldExtExample example);

    int min(@Param("field") String field, @Param("example") TopFieldModuleFieldExtExample example);

}

