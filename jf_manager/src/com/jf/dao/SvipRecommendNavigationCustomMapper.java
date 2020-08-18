package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SvipRecommendNavigationCustomMapper {

	void updateSeqNoIsNull(@Param("id") Integer id, @Param("staffID") Integer staffID, @Param("date") String date);

}