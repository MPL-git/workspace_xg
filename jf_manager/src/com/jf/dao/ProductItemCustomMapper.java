package com.jf.dao;

import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Repository
public interface ProductItemCustomMapper  {
    List<ProductItemCustom> selectByExample(ProductItemExample example);
    ProductItemCustom selectByPrimaryKey(Integer id);
    void updateByProductId(@Param("sale_price") BigDecimal seckillPrice,@Param("product_id") Integer productId);
    ProductItem getMinPriceItem(Map<String, Object> params);

    ProductItem getMaxPriceItem(Map<String, Object> params);
}