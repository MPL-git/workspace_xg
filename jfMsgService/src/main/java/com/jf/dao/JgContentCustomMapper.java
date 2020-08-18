package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.JgContent;
import com.jf.entity.JgContentCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午5:00:06 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface JgContentCustomMapper {

	List<JgContentCustom> getMemberCouponInfoForJG();

	void insertBatchJg(List<JgContent> jgContents);

	void batchUpdateJg(Map<String, Object> map);

	List<JgContentCustom> getPushPointToPointList(Map<String, Object> paramsMap);

}
