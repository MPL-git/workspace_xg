package com.jf.dao;

import com.jf.entity.SalesmanCustom;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerCustomExample;
import com.jf.entity.ShopownerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopownerMapper extends BaseDao<Shopowner, ShopownerExample>{
    int countByExample(ShopownerExample example);

    int deleteByExample(ShopownerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopowner record);

    int insertSelective(Shopowner record);

    List<Shopowner> selectByExample(ShopownerExample example);

    Shopowner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopowner record, @Param("example") ShopownerExample example);

    int updateByExample(@Param("record") Shopowner record, @Param("example") ShopownerExample example);

    int updateByPrimaryKeySelective(Shopowner record);

    int updateByPrimaryKey(Shopowner record);

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:02:17
	 */
	Integer countByCustomExample(ShopownerCustomExample shopownerCustomExample);

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:02:21
	 */
	List<SalesmanCustom> selectByCustomExample(
			ShopownerCustomExample shopownerCustomExample);
}