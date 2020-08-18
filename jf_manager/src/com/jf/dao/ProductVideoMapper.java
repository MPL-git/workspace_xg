package com.jf.dao;

import com.jf.entity.ProductVideo;
import com.jf.entity.ProductVideoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVideoMapper extends BaseDao<ProductVideo, ProductVideoExample>{
    int countByExample(ProductVideoExample example);

    int deleteByExample(ProductVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductVideo record);

    int insertSelective(ProductVideo record);

    List<ProductVideo> selectByExample(ProductVideoExample example);

    ProductVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductVideo record, @Param("example") ProductVideoExample example);

    int updateByExample(@Param("record") ProductVideo record, @Param("example") ProductVideoExample example);

    int updateByPrimaryKeySelective(ProductVideo record);

    int updateByPrimaryKey(ProductVideo record);
}