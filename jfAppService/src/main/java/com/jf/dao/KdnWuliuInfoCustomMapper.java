package com.jf.dao;

import com.jf.entity.KdnWuliuInfoCustom;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface KdnWuliuInfoCustomMapper{

	KdnWuliuInfoCustom getExpressInfo(Map<String, Object> params);
    
}