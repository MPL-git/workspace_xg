package com.jf.dao;

import com.jf.entity.ProvinceFreightExt;
import com.jf.entity.ProvinceFreightExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceFreightExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProvinceFreightExt findById(int id);

    ProvinceFreightExt find(ProvinceFreightExtExample example);

    List<ProvinceFreightExt> list(ProvinceFreightExtExample example);

    List<Integer> listId(ProvinceFreightExtExample example);

    int count(ProvinceFreightExtExample example);

    long sum(@Param("field") String field, @Param("example") ProvinceFreightExtExample example);

    int max(@Param("field") String field, @Param("example") ProvinceFreightExtExample example);

    int min(@Param("field") String field, @Param("example") ProvinceFreightExtExample example);

}

