package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Pengl
 * @create 2019-11-21 下午 4:21
 */
@Repository
public interface AppLoginDistinctLogCustomMapper {

	public void insertSelect(Map<String, Object> paramMap);

}
