package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductWeight;
import com.jf.entity.ProductWeightExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductWeightMapper extends BaseDao<ProductWeight, ProductWeightExample> {
    int countByExample(ProductWeightExample example);

    int deleteByExample(ProductWeightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductWeight record);

    int insertSelective(ProductWeight record);

    List<ProductWeight> selectByExample(ProductWeightExample example);

    ProductWeight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductWeight record, @Param("example") ProductWeightExample example);

    int updateByExample(@Param("record") ProductWeight record, @Param("example") ProductWeightExample example);

    int updateByPrimaryKeySelective(ProductWeight record);

    int updateByPrimaryKey(ProductWeight record);
}
