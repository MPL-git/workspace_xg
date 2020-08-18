package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductTypeAptitudePic;
import com.jf.entity.ProductTypeAptitudePicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeAptitudePicMapper extends BaseDao<ProductTypeAptitudePic, ProductTypeAptitudePicExample> {
    int countByExample(ProductTypeAptitudePicExample example);

    int deleteByExample(ProductTypeAptitudePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypeAptitudePic record);

    int insertSelective(ProductTypeAptitudePic record);

    List<ProductTypeAptitudePic> selectByExample(ProductTypeAptitudePicExample example);

    ProductTypeAptitudePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTypeAptitudePic record, @Param("example") ProductTypeAptitudePicExample example);

    int updateByExample(@Param("record") ProductTypeAptitudePic record, @Param("example") ProductTypeAptitudePicExample example);

    int updateByPrimaryKeySelective(ProductTypeAptitudePic record);

    int updateByPrimaryKey(ProductTypeAptitudePic record);
}
