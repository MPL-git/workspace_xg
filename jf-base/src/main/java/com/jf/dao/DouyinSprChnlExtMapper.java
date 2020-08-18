package com.jf.dao;

import com.jf.entity.DouyinSprChnlExt;
import com.jf.entity.DouyinSprChnlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprChnlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DouyinSprChnlExt findById(int id);

    DouyinSprChnlExt find(DouyinSprChnlExtExample example);

    List<DouyinSprChnlExt> list(DouyinSprChnlExtExample example);

    List<Integer> listId(DouyinSprChnlExtExample example);

    int count(DouyinSprChnlExtExample example);

    long sum(@Param("field") String field, @Param("example") DouyinSprChnlExtExample example);

    int max(@Param("field") String field, @Param("example") DouyinSprChnlExtExample example);

    int min(@Param("field") String field, @Param("example") DouyinSprChnlExtExample example);

}

