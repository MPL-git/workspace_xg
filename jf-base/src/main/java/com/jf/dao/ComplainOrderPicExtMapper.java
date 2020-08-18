package com.jf.dao;

import com.jf.entity.ComplainOrderPicExt;
import com.jf.entity.ComplainOrderPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainOrderPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ComplainOrderPicExt findById(int id);

    ComplainOrderPicExt find(ComplainOrderPicExtExample example);

    List<ComplainOrderPicExt> list(ComplainOrderPicExtExample example);

    List<Integer> listId(ComplainOrderPicExtExample example);

    int count(ComplainOrderPicExtExample example);

    long sum(@Param("field") String field, @Param("example") ComplainOrderPicExtExample example);

    int max(@Param("field") String field, @Param("example") ComplainOrderPicExtExample example);

    int min(@Param("field") String field, @Param("example") ComplainOrderPicExtExample example);

}

