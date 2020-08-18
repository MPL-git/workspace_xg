package com.jf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProvinceFreight;

@Repository
public interface ProvinceFreightCustomMapper {

	ProvinceFreight getProvinceFreightTemplate(Map<String, Object> pMap);

}
