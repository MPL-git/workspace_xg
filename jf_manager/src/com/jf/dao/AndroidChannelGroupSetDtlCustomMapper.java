package com.jf.dao;

import com.jf.entity.AndroidChannelGroupSetDtl;
import com.jf.entity.AndroidChannelGroupSetDtlCustom;
import com.jf.entity.AndroidChannelGroupSetDtlCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndroidChannelGroupSetDtlCustomMapper {
    int countByCustomExample(AndroidChannelGroupSetDtlCustomExample example);

    List<AndroidChannelGroupSetDtlCustom> selectByCustomExample(AndroidChannelGroupSetDtlCustomExample example);

    AndroidChannelGroupSetDtlCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") AndroidChannelGroupSetDtl record, @Param("example") AndroidChannelGroupSetDtlCustomExample example);

}