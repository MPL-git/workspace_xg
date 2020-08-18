package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.SysAppCustomVersion;
@Repository
public interface SysAppVersionCustomMapper{

	List<SysAppCustomVersion> getAppNewVersion(Map<String, String> paramsMap);
    
}