package com.jf.dao;

import com.jf.entity.ProductModuleType;
import com.jf.entity.ProductModuleTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductModuleTypeMapper extends BaseDao<ProductModuleType,ProductModuleTypeExample> {
    int countByExample(ProductModuleTypeExample example);

    int deleteByExample(ProductModuleTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductModuleType record);

    int insertSelective(ProductModuleType record);

    List<ProductModuleType> selectByExample(ProductModuleTypeExample example);

    ProductModuleType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductModuleType record, @Param("example") ProductModuleTypeExample example);

    int updateByExample(@Param("record") ProductModuleType record, @Param("example") ProductModuleTypeExample example);

    int updateByPrimaryKeySelective(ProductModuleType record);

    int updateByPrimaryKey(ProductModuleType record);
}