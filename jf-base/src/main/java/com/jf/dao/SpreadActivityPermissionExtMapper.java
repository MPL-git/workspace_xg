package com.jf.dao;

import com.jf.entity.SpreadActivityPermissionExt;
import com.jf.entity.SpreadActivityPermissionExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityPermissionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpreadActivityPermissionExt findById(int id);

    SpreadActivityPermissionExt find(SpreadActivityPermissionExtExample example);

    List<SpreadActivityPermissionExt> list(SpreadActivityPermissionExtExample example);

    List<Integer> listId(SpreadActivityPermissionExtExample example);

    int count(SpreadActivityPermissionExtExample example);

    long sum(@Param("field") String field, @Param("example") SpreadActivityPermissionExtExample example);

    int max(@Param("field") String field, @Param("example") SpreadActivityPermissionExtExample example);

    int min(@Param("field") String field, @Param("example") SpreadActivityPermissionExtExample example);

}

