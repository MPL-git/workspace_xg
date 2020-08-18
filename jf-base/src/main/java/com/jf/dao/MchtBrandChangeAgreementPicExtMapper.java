package com.jf.dao;

import com.jf.entity.MchtBrandChangeAgreementPicExt;
import com.jf.entity.MchtBrandChangeAgreementPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandChangeAgreementPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandChangeAgreementPicExt findById(int id);

    MchtBrandChangeAgreementPicExt find(MchtBrandChangeAgreementPicExtExample example);

    List<MchtBrandChangeAgreementPicExt> list(MchtBrandChangeAgreementPicExtExample example);

    List<Integer> listId(MchtBrandChangeAgreementPicExtExample example);

    int count(MchtBrandChangeAgreementPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandChangeAgreementPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandChangeAgreementPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandChangeAgreementPicExtExample example);

}

