package com.jf.dao;

import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.dto.ZzySubscribeDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年10月20日 下午5:06:01 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface KdnWuliuInfoCustomMapper {

	List<KdnWuliuInfoCustom> getKdnLogisticsInfo();
	List<KdnWuliuInfoCustom> getKdnLogisticsInfoByDate(Map<String, Object> paramMap);

    List<ZzySubscribeDTO> findSelectByZZYList(Map<String, Object> paramMap);

	void updateKdnByExample(HashMap<String, Object> map);

	List<String> findZZYBatchSubscribeList(HashMap<String, Object> paramMap);

	List<Map<String,Object>> findZZyBatchQuertList(HashMap<String, Object> paramMap);
}
