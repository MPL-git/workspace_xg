package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlCustom;
@Repository
public interface IntegralDtlCustomMapper {
	
	List<IntegralDtlCustom> getIntegralDtlList(Map<String, Object> params);

	Integer getIntegralDtlCount(Map<String, Object> params);

	List<IntegralDtl> getImportAccountIntegral(Map<String, Object> params);
}