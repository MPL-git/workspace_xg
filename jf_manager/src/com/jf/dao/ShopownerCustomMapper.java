package com.jf.dao;

import com.jf.entity.SalesmanCustom;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerCustom;
import com.jf.entity.ShopownerCustomExample;
import com.jf.entity.ShopownerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopownerCustomMapper{

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:03:24
	 */
	Integer countByCustomExample(ShopownerCustomExample shopownerCustomExample);

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:03:27
	 */
	List<ShopownerCustom> selectByCustomExample(
			ShopownerCustomExample shopownerCustomExample);
 
}