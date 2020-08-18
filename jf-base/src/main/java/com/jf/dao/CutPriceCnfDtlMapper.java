package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfDtlMapper extends BaseDao<CutPriceCnfDtl, CutPriceCnfDtlExample> {
    int countByExample(CutPriceCnfDtlExample example);

    int deleteByExample(CutPriceCnfDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceCnfDtl record);

    int insertSelective(CutPriceCnfDtl record);

    List<CutPriceCnfDtl> selectByExample(CutPriceCnfDtlExample example);

    CutPriceCnfDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceCnfDtl record, @Param("example") CutPriceCnfDtlExample example);

    int updateByExample(@Param("record") CutPriceCnfDtl record, @Param("example") CutPriceCnfDtlExample example);

    int updateByPrimaryKeySelective(CutPriceCnfDtl record);

    int updateByPrimaryKey(CutPriceCnfDtl record);
}
