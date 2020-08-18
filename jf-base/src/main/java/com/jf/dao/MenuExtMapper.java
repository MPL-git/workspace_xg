package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.MenuExt;
import com.jf.entity.MenuExtExample;

@Repository
public interface MenuExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MenuExt findById(int id);

    MenuExt find(MenuExtExample example);

    List<MenuExt> list(MenuExtExample example);

    List<Integer> listId(MenuExtExample example);

    int count(MenuExtExample example);

    long sum(@Param("field") String field, @Param("example") MenuExtExample example);

    int max(@Param("field") String field, @Param("example") MenuExtExample example);

    int min(@Param("field") String field, @Param("example") MenuExtExample example);

}

