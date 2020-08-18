package com.jf.dao;

import com.jf.entity.ProvinceFreight;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ProvinceFreightCustomMapper {

	ProvinceFreight getProvinceFreightTemplate(Map<String, Object> pMap);

}
