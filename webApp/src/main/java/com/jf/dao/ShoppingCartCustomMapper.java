package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
@Repository
public interface ShoppingCartCustomMapper {
    int countByExample(ShoppingCartExample example);
    List<ShoppingCartCustom> selectByExample(ShoppingCartExample example);
    ShoppingCartCustom selectByPrimaryKey(Integer id);
    List<ShoppingCartCustom> getMemberShoppingCart(Integer memberId);
    
	List<ShoppingCartCustom> getFullCutData(Map<String, Object> params);
	List<ShoppingCartCustom> findShoppingCartById(Map<String, Object> shopCarParams);
	Integer getShoppingCartNum(Integer memberId);
	List<ShoppingCartCustom> getShopCars(Map<String, Object> cartMap);
	void updateShopCartActivityInfo(Map<String, Object> activityInfoMap);

}