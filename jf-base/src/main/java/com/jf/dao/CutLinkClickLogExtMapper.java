package com.jf.dao;

import com.jf.entity.CutLinkClickLogExt;
import com.jf.entity.CutLinkClickLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutLinkClickLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CutLinkClickLogExt findById(int id);

    CutLinkClickLogExt find(CutLinkClickLogExtExample example);

    List<CutLinkClickLogExt> list(CutLinkClickLogExtExample example);

    List<Integer> listId(CutLinkClickLogExtExample example);

    int count(CutLinkClickLogExtExample example);

    long sum(@Param("field") String field, @Param("example") CutLinkClickLogExtExample example);

    int max(@Param("field") String field, @Param("example") CutLinkClickLogExtExample example);

    int min(@Param("field") String field, @Param("example") CutLinkClickLogExtExample example);

}

