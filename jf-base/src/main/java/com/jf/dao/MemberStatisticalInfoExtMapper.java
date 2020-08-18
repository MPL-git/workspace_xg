package com.jf.dao;

import com.jf.entity.MemberStatisticalInfoExt;
import com.jf.entity.MemberStatisticalInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberStatisticalInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberStatisticalInfoExt findById(int id);

    MemberStatisticalInfoExt find(MemberStatisticalInfoExtExample example);

    List<MemberStatisticalInfoExt> list(MemberStatisticalInfoExtExample example);

    List<Integer> listId(MemberStatisticalInfoExtExample example);

    int count(MemberStatisticalInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberStatisticalInfoExtExample example);

    int max(@Param("field") String field, @Param("example") MemberStatisticalInfoExtExample example);

    int min(@Param("field") String field, @Param("example") MemberStatisticalInfoExtExample example);

}

