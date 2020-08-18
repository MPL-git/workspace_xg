package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandTmpMapper extends BaseDao<ProductBrandTmp, ProductBrandTmpExample> {
    int countByExample(ProductBrandTmpExample example);

    int deleteByExample(ProductBrandTmpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductBrandTmp record);

    int insertSelective(ProductBrandTmp record);

    List<ProductBrandTmp> selectByExample(ProductBrandTmpExample example);

    ProductBrandTmp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductBrandTmp record, @Param("example") ProductBrandTmpExample example);

    int updateByExample(@Param("record") ProductBrandTmp record, @Param("example") ProductBrandTmpExample example);

    int updateByPrimaryKeySelective(ProductBrandTmp record);

    int updateByPrimaryKey(ProductBrandTmp record);
}
