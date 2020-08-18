package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CutPriceCnfTpl;
import com.jf.entity.CutPriceCnfTplExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceCnfTplMapper extends BaseDao<CutPriceCnfTpl, CutPriceCnfTplExample> {
    int countByExample(CutPriceCnfTplExample example);

    int deleteByExample(CutPriceCnfTplExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceCnfTpl record);

    int insertSelective(CutPriceCnfTpl record);

    List<CutPriceCnfTpl> selectByExample(CutPriceCnfTplExample example);

    CutPriceCnfTpl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceCnfTpl record, @Param("example") CutPriceCnfTplExample example);

    int updateByExample(@Param("record") CutPriceCnfTpl record, @Param("example") CutPriceCnfTplExample example);

    int updateByPrimaryKeySelective(CutPriceCnfTpl record);

    int updateByPrimaryKey(CutPriceCnfTpl record);
}
