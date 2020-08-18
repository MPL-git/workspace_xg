package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SaleWeightCnf;
import com.jf.entity.SaleWeightCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleWeightCnfMapper extends BaseDao<SaleWeightCnf, SaleWeightCnfExample> {
    int countByExample(SaleWeightCnfExample example);

    int deleteByExample(SaleWeightCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleWeightCnf record);

    int insertSelective(SaleWeightCnf record);

    List<SaleWeightCnf> selectByExample(SaleWeightCnfExample example);

    SaleWeightCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleWeightCnf record, @Param("example") SaleWeightCnfExample example);

    int updateByExample(@Param("record") SaleWeightCnf record, @Param("example") SaleWeightCnfExample example);

    int updateByPrimaryKeySelective(SaleWeightCnf record);

    int updateByPrimaryKey(SaleWeightCnf record);
}
