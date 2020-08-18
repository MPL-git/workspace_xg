package com.jf.dao;

import com.jf.entity.AdInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdInfoCustomMapper{

	List<AdInfo> findAdInfoList(Map<String, Object> params);
}