package com.jf.dao;

import com.jf.entity.MchtLicenseChgExt;
import com.jf.entity.MchtLicenseChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtLicenseChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtLicenseChgExt findById(int id);

    MchtLicenseChgExt find(MchtLicenseChgExtExample example);

    List<MchtLicenseChgExt> list(MchtLicenseChgExtExample example);

    List<Integer> listId(MchtLicenseChgExtExample example);

    int count(MchtLicenseChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtLicenseChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtLicenseChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtLicenseChgExtExample example);

}

