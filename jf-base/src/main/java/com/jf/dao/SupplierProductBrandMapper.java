package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SupplierProductBrand;
import com.jf.entity.SupplierProductBrandExample;

@Repository
public interface SupplierProductBrandMapper extends BaseDao<SupplierProductBrand, SupplierProductBrandExample> {
    int countByExample(SupplierProductBrandExample example);

    int deleteByExample(SupplierProductBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SupplierProductBrand record);

    int insertSelective(SupplierProductBrand record);

    List<SupplierProductBrand> selectByExample(SupplierProductBrandExample example);

    SupplierProductBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SupplierProductBrand record, @Param("example") SupplierProductBrandExample example);

    int updateByExample(@Param("record") SupplierProductBrand record, @Param("example") SupplierProductBrandExample example);

    int updateByPrimaryKeySelective(SupplierProductBrand record);

    int updateByPrimaryKey(SupplierProductBrand record);
}
