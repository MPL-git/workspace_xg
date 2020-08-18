package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopDecorateInfoCustom;

@Repository
public interface ShopDecorateInfoCustomMapper {

	List<ShopDecorateInfoCustom> getShopDecorateInfo(Integer mchtId);

}
