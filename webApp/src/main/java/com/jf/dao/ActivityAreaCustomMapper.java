package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityAreaCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 下午2:12:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface ActivityAreaCustomMapper {

	List<ActivityAreaCustom> getActivity(Map<String, Object> paramsMap);

	Integer getActivityCount(Integer activityAreaId);

	List<ActivityAreaCustom> getIndividualActivity(Map<String, Object> paramsMap);

	List<ActivityAreaCustom> getH5ProductInfoList(List<String> sportProductsList);

}
