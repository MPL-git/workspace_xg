package com.jf.dao;

import com.jf.entity.CooperationChangeApplyExt;
import com.jf.entity.CooperationChangeApplyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperationChangeApplyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CooperationChangeApplyExt findById(int id);

    CooperationChangeApplyExt find(CooperationChangeApplyExtExample example);

    List<CooperationChangeApplyExt> list(CooperationChangeApplyExtExample example);

    List<Integer> listId(CooperationChangeApplyExtExample example);

    int count(CooperationChangeApplyExtExample example);

    long sum(@Param("field") String field, @Param("example") CooperationChangeApplyExtExample example);

    int max(@Param("field") String field, @Param("example") CooperationChangeApplyExtExample example);

    int min(@Param("field") String field, @Param("example") CooperationChangeApplyExtExample example);

}

