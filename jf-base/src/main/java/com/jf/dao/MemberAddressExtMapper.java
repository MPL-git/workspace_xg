package com.jf.dao;

import com.jf.entity.MemberAddressExt;
import com.jf.entity.MemberAddressExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAddressExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberAddressExt findById(int id);

    MemberAddressExt find(MemberAddressExtExample example);

    List<MemberAddressExt> list(MemberAddressExtExample example);

    List<Integer> listId(MemberAddressExtExample example);

    int count(MemberAddressExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberAddressExtExample example);

    int max(@Param("field") String field, @Param("example") MemberAddressExtExample example);

    int min(@Param("field") String field, @Param("example") MemberAddressExtExample example);

}

