package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ThirdPlatformProductInfoCustom;

@Repository
public interface ThirdPlatformProductInfoCustomMapper {

	List<ThirdPlatformProductInfoCustom> getThirdProductInfoList(Map<String, Object> paramsMap);

	List<ThirdPlatformProductInfoCustom> getChannelTaoBaoProductList(Map<String, Object> paramsMap);

}
