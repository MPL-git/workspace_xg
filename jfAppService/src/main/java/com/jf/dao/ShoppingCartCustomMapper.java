package com.jf.dao;

import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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