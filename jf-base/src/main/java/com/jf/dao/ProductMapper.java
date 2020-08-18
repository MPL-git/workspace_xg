package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseDao<Product, ProductExample> {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);


    List<Product> selectByExampleWithBLOBs(ProductExample example);
    int updateByPrimaryKeyWithBLOBs(Product record);
    int updateByExampleWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);
}
