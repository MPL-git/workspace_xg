package com.jf.dao;

import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeixinXcxSprChnlMapper extends BaseDao<WeixinXcxSprChnl, WeixinXcxSprChnlExample> {
    int countByExample(WeixinXcxSprChnlExample example);

    int deleteByExample(WeixinXcxSprChnlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinXcxSprChnl record);

    int insertSelective(WeixinXcxSprChnl record);

    List<WeixinXcxSprChnl> selectByExample(WeixinXcxSprChnlExample example);

    WeixinXcxSprChnl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinXcxSprChnl record, @Param("example") WeixinXcxSprChnlExample example);

    int updateByExample(@Param("record") WeixinXcxSprChnl record, @Param("example") WeixinXcxSprChnlExample example);

    int updateByPrimaryKeySelective(WeixinXcxSprChnl record);

    int updateByPrimaryKey(WeixinXcxSprChnl record);
}