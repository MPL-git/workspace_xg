package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeCustom;
import com.jf.entity.MchtBrandProductTypeExample;
@Repository
public interface MchtBrandProductTypeCustomMapper{
	public List<MchtBrandProductTypeCustom>selectByExample(MchtBrandProductTypeExample mchtBrandProductTypeExample);

	public void batchInsert(List<MchtBrandProductType> mchtBrandProductTypeList);
}