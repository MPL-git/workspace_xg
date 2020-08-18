package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProductTypeCustom;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年3月2日 下午2:46:49 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface ProductTypeCustomMapper {

	List<ProductTypeCustom> getThreeIdByIds(Map<String, Object> paramsMap);

}
