package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicCustomExample;
import com.jf.entity.Product;




@Repository
public interface MchtShopDynamicCustomMapper {

	void setProductIdsNull(Integer id);
	
	int countByExample(MchtShopDynamicCustomExample example);

	List<MchtShopDynamicCustom> selectByExample(MchtShopDynamicCustomExample example);
	
	

}
