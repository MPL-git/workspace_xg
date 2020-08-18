package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlCustom;
import com.jf.entity.WeixinXcxSprChnlCustomExample;

@Repository
public interface WeixinXcxSprChnlCustomMapper {
    int countByCustomExample(WeixinXcxSprChnlCustomExample example);

    List<WeixinXcxSprChnlCustom> selectByCustomExample(WeixinXcxSprChnlCustomExample example);

    WeixinXcxSprChnlCustom selectByCustomPrimaryKey(Integer id);

    int updateByCustomExampleSelective(@Param("record") WeixinXcxSprChnl record, @Param("example") WeixinXcxSprChnlCustomExample example);

}