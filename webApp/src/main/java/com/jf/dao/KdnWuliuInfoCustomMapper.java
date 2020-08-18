package com.jf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.KdnWuliuInfoCustom;
@Repository
public interface KdnWuliuInfoCustomMapper{

	KdnWuliuInfoCustom getExpressInfo(Map<String, Object> params);
    
}