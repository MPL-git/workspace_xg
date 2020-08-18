package com.jf.dao;

import com.jf.entity.MemberSupportSourceLogExt;
import com.jf.entity.MemberSupportSourceLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSupportSourceLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberSupportSourceLogExt findById(int id);

    MemberSupportSourceLogExt find(MemberSupportSourceLogExtExample example);

    List<MemberSupportSourceLogExt> list(MemberSupportSourceLogExtExample example);

    List<Integer> listId(MemberSupportSourceLogExtExample example);

    int count(MemberSupportSourceLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberSupportSourceLogExtExample example);

    int max(@Param("field") String field, @Param("example") MemberSupportSourceLogExtExample example);

    int min(@Param("field") String field, @Param("example") MemberSupportSourceLogExtExample example);

}

