package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeCustom;
import com.jf.entity.MchtBrandChgProductTypeExample;
@Repository
public interface MchtBrandChgProductTypeCustomMapper{
	public List<MchtBrandChgProductTypeCustom>selectByExample(MchtBrandChgProductTypeExample mchtBrandChgProductTypeExample);

	public void batchInsert(List<MchtBrandChgProductType> mchtBrandChgProductTypeList);
}