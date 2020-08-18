package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.AdInfo;
@Repository
public interface AdInfoCustomMapper{

	List<AdInfo> findAdInfoList(Map<String, Object> params);
}