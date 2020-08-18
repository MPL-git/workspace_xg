package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopDecorateInfoDraftCustom;

@Repository
public interface ShopDecorateInfoDraftCustomMapper {

	List<ShopDecorateInfoDraftCustom> getShopDecorateInfo(Integer mchtId);

}
