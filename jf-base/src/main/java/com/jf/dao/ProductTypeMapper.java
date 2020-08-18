package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeMapper extends BaseDao<ProductType, ProductTypeExample> {
    int countByExample(ProductTypeExample example);

    int deleteByExample(ProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    List<ProductType> selectByExample(ProductTypeExample example);

    ProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByExample(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);

    List<ProductType> selectByExampleWithBLOBs(ProductTypeExample example);
    int updateByPrimaryKeyWithBLOBs(ProductType record);
    int updateByExampleWithBLOBs(@Param("record") ProductType record, @Param("example") ProductTypeExample example);
}
