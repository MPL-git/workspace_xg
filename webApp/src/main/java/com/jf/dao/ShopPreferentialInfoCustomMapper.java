package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopPreferentialInfoCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月19日 下午5:14:01 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface ShopPreferentialInfoCustomMapper {

	List<ShopPreferentialInfoCustom> getShopPreferentialInfo(List<Integer> mchtIds);

}
