package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPicMapper extends BaseDao<ProductPic, ProductPicExample> {
    int countByExample(ProductPicExample example);

    int deleteByExample(ProductPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPic record);

    int insertSelective(ProductPic record);

    List<ProductPic> selectByExample(ProductPicExample example);

    ProductPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPic record, @Param("example") ProductPicExample example);

    int updateByExample(@Param("record") ProductPic record, @Param("example") ProductPicExample example);

    int updateByPrimaryKeySelective(ProductPic record);

    int updateByPrimaryKey(ProductPic record);
}
