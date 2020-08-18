package com.jf.dao;

import com.jf.entity.ClientServiceCommentExt;
import com.jf.entity.ClientServiceCommentExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientServiceCommentExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ClientServiceCommentExt findById(int id);

    ClientServiceCommentExt find(ClientServiceCommentExtExample example);

    List<ClientServiceCommentExt> list(ClientServiceCommentExtExample example);

    List<Integer> listId(ClientServiceCommentExtExample example);

    int count(ClientServiceCommentExtExample example);

    long sum(@Param("field") String field, @Param("example") ClientServiceCommentExtExample example);

    int max(@Param("field") String field, @Param("example") ClientServiceCommentExtExample example);

    int min(@Param("field") String field, @Param("example") ClientServiceCommentExtExample example);

}

