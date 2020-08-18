package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductProp;
import com.jf.entity.ProductPropExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropMapper extends BaseDao<ProductProp, ProductPropExample> {
    int countByExample(ProductPropExample example);

    int deleteByExample(ProductPropExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductProp record);

    int insertSelective(ProductProp record);

    List<ProductProp> selectByExample(ProductPropExample example);

    ProductProp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductProp record, @Param("example") ProductPropExample example);

    int updateByExample(@Param("record") ProductProp record, @Param("example") ProductPropExample example);

    int updateByPrimaryKeySelective(ProductProp record);

    int updateByPrimaryKey(ProductProp record);
}
