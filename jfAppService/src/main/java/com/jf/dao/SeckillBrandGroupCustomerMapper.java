package com.jf.dao;

import com.jf.entity.SeckillBrandGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月18日 下午4:01:38 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Repository
public interface SeckillBrandGroupCustomerMapper {

	List<SeckillBrandGroup> getSeckillBrandGroup(Map<String, Object> brandGroupParams);


}
