package com.jf.dao;

import com.jf.entity.SvipProductRecommend;
import com.jf.entity.SvipProductRecommendCustom;
import com.jf.entity.SvipProductRecommendCustomExample;
import com.jf.vo.SvipProductAutomaticGrab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public interface SvipProductRecommendCustomMapper {

    ArrayList<SvipProductRecommendCustom> selectSvipProductRecommendCustomByExample(SvipProductRecommendCustomExample example);
   
    int countSvipProductRecommendCustomByExample(SvipProductRecommendCustomExample example);
    
    int batchInsertSvipProductRecommend(@Param("productIds")ArrayList<Integer> productIds, @Param("svipProductRecommend")SvipProductRecommend svipProductRecommend);
	
	List<SvipProductAutomaticGrab> getAutomaticGrabProductIds();

	ArrayList<SvipProductRecommendCustom> selectAutomaticGrabList(@Param("ids")List<Integer> ids);

	void updateSeqNoIsNull(@Param("id")Integer id,@Param("staffID")Integer staffID,@Param("date")String date);
	
	ArrayList<HashMap<String,Object>> selectProductByCodeList(@Param("productCodesSet")HashSet<String> productCodesSet);

}