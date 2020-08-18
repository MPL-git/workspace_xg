package com.jf.dao;

import com.jf.entity.MchtContactExt;
import com.jf.entity.MchtContactExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContactExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtContactExt findById(int id);

    MchtContactExt find(MchtContactExtExample example);

    List<MchtContactExt> list(MchtContactExtExample example);

    List<Integer> listId(MchtContactExtExample example);

    int count(MchtContactExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtContactExtExample example);

    int max(@Param("field") String field, @Param("example") MchtContactExtExample example);

    int min(@Param("field") String field, @Param("example") MchtContactExtExample example);

}

