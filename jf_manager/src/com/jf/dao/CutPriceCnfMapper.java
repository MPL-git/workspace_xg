package com.jf.dao;

import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CutPriceCnfMapper extends BaseDao<CutPriceCnf, CutPriceCnfExample> {
    int countByExample(CutPriceCnfExample example);

    int deleteByExample(CutPriceCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceCnf record);

    int insertSelective(CutPriceCnf record);

    List<CutPriceCnf> selectByExample(CutPriceCnfExample example);

    CutPriceCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceCnf record, @Param("example") CutPriceCnfExample example);

    int updateByExample(@Param("record") CutPriceCnf record, @Param("example") CutPriceCnfExample example);

    int updateByPrimaryKeySelective(CutPriceCnf record);

    int updateByPrimaryKey(CutPriceCnf record);
}