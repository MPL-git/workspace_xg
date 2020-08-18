package com.jf.dao;

import com.jf.entity.CutPriceCnfTplDtl;
import com.jf.entity.CutPriceCnfTplDtlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CutPriceCnfTplDtlMapper extends BaseDao<CutPriceCnfTplDtl, CutPriceCnfTplDtlExample> {
    int countByExample(CutPriceCnfTplDtlExample example);

    int deleteByExample(CutPriceCnfTplDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceCnfTplDtl record);

    int insertSelective(CutPriceCnfTplDtl record);

    List<CutPriceCnfTplDtl> selectByExample(CutPriceCnfTplDtlExample example);

    CutPriceCnfTplDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceCnfTplDtl record, @Param("example") CutPriceCnfTplDtlExample example);

    int updateByExample(@Param("record") CutPriceCnfTplDtl record, @Param("example") CutPriceCnfTplDtlExample example);

    int updateByPrimaryKeySelective(CutPriceCnfTplDtl record);

    int updateByPrimaryKey(CutPriceCnfTplDtl record);
}