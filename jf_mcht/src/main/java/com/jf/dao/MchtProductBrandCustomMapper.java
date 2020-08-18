package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtProductBrandCustom;
import com.jf.entity.MchtProductBrandCustomExample;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.ProductBrand;
@Repository
public interface MchtProductBrandCustomMapper{
    int countByExample(MchtProductBrandExample example);
    List<MchtProductBrandCustom> selectByExample(MchtProductBrandExample example);
    MchtProductBrandCustom selectByPrimaryKey(Integer id);
	List<ProductBrand> getMchtProductBrandList(Integer mchtId);
	List<MchtProductBrandCustom> getMchtUsebleProductBrand(Integer mchtId);

    List<ProductBrand> getActivityMchtProductBrandList(Integer mchtId);
	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月25日 下午4:29:01
	 */
	List<MchtProductBrandCustom> selectByExampleCustom(
			MchtProductBrandCustomExample mchtProductBrandCustomExample);
}