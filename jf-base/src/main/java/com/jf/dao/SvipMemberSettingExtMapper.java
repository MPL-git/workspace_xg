package com.jf.dao;

import com.jf.entity.SvipMemberSettingExt;
import com.jf.entity.SvipMemberSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipMemberSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SvipMemberSettingExt findById(int id);

    SvipMemberSettingExt find(SvipMemberSettingExtExample example);

    List<SvipMemberSettingExt> list(SvipMemberSettingExtExample example);

    List<Integer> listId(SvipMemberSettingExtExample example);

    int count(SvipMemberSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") SvipMemberSettingExtExample example);

    int max(@Param("field") String field, @Param("example") SvipMemberSettingExtExample example);

    int min(@Param("field") String field, @Param("example") SvipMemberSettingExtExample example);

}

