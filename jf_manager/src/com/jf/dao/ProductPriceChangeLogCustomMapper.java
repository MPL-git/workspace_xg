package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogCustom;
import com.jf.entity.ProductPriceChangeLogCustomExample;

@Repository
public interface ProductPriceChangeLogCustomMapper {
    public int countByCustomExample(ProductPriceChangeLogCustomExample example);

    public List<ProductPriceChangeLogCustom> selectByCustomExample(ProductPriceChangeLogCustomExample example);

    public ProductPriceChangeLogCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") ProductPriceChangeLog record, @Param("example") ProductPriceChangeLogCustomExample example);

}