package com.jf.dao;

import com.jf.entity.ProductTypeCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeCustomMapper {

	public List<ProductTypeCustom> getNumberOfProductType( );
}