package com.jf.dao;

import com.jf.entity.MemberAccountDtlExt;
import com.jf.entity.MemberAccountDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAccountDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberAccountDtlExt findById(int id);

    MemberAccountDtlExt find(MemberAccountDtlExtExample example);

    List<MemberAccountDtlExt> list(MemberAccountDtlExtExample example);

    List<Integer> listId(MemberAccountDtlExtExample example);

    int count(MemberAccountDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberAccountDtlExtExample example);

    int max(@Param("field") String field, @Param("example") MemberAccountDtlExtExample example);

    int min(@Param("field") String field, @Param("example") MemberAccountDtlExtExample example);

}

