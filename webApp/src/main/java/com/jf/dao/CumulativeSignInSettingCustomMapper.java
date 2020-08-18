package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CumulativeSignInSettingCustom;

@Repository
public interface CumulativeSignInSettingCustomMapper {

	List<CumulativeSignInSettingCustom> getMonthCumulativeAward(Map<String, Object> paramsMap);

}
