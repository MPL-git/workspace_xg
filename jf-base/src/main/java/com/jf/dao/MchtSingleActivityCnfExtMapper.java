package com.jf.dao;

import com.jf.entity.MchtSingleActivityCnfExt;
import com.jf.entity.MchtSingleActivityCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSingleActivityCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSingleActivityCnfExt findById(int id);

    MchtSingleActivityCnfExt find(MchtSingleActivityCnfExtExample example);

    List<MchtSingleActivityCnfExt> list(MchtSingleActivityCnfExtExample example);

    List<Integer> listId(MchtSingleActivityCnfExtExample example);

    int count(MchtSingleActivityCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSingleActivityCnfExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSingleActivityCnfExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSingleActivityCnfExtExample example);

}

