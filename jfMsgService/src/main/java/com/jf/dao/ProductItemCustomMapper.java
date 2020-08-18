package com.jf.dao;

import com.jf.entity.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemCustomMapper {

	List<ProductItem> getSkuByProductId(Integer productId);

}