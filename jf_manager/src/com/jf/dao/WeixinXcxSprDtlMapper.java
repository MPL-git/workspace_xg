package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprDtlExample;

@Repository
public interface WeixinXcxSprDtlMapper extends BaseDao<WeixinXcxSprDtl, WeixinXcxSprDtlExample> {
    int countByExample(WeixinXcxSprDtlExample example);

    int deleteByExample(WeixinXcxSprDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinXcxSprDtl record);

    int insertSelective(WeixinXcxSprDtl record);

    List<WeixinXcxSprDtl> selectByExample(WeixinXcxSprDtlExample example);

    WeixinXcxSprDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinXcxSprDtl record, @Param("example") WeixinXcxSprDtlExample example);

    int updateByExample(@Param("record") WeixinXcxSprDtl record, @Param("example") WeixinXcxSprDtlExample example);

    int updateByPrimaryKeySelective(WeixinXcxSprDtl record);

    int updateByPrimaryKey(WeixinXcxSprDtl record);
}