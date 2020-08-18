package com.jf.dao;

import com.jf.entity.ProductDescPic;
import com.jf.entity.ProductDescPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductDescPicMapper extends BaseDao<ProductDescPic, ProductDescPicExample> {
    int countByExample(ProductDescPicExample example);

    int deleteByExample(ProductDescPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductDescPic record);

    int insertSelective(ProductDescPic record);

    List<ProductDescPic> selectByExample(ProductDescPicExample example);

    ProductDescPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductDescPic record, @Param("example") ProductDescPicExample example);

    int updateByExample(@Param("record") ProductDescPic record, @Param("example") ProductDescPicExample example);

    int updateByPrimaryKeySelective(ProductDescPic record);

    int updateByPrimaryKey(ProductDescPic record);
}