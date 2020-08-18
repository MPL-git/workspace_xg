package com.jf.dao;

import com.jf.entity.MemberFavoritesExt;
import com.jf.entity.MemberFavoritesExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFavoritesExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberFavoritesExt findById(int id);

    MemberFavoritesExt find(MemberFavoritesExtExample example);

    List<MemberFavoritesExt> list(MemberFavoritesExtExample example);

    List<Integer> listId(MemberFavoritesExtExample example);

    int count(MemberFavoritesExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberFavoritesExtExample example);

    int max(@Param("field") String field, @Param("example") MemberFavoritesExtExample example);

    int min(@Param("field") String field, @Param("example") MemberFavoritesExtExample example);

}

