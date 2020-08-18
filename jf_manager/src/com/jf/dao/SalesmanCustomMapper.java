package com.jf.dao;

import com.jf.entity.Salesman;
import com.jf.entity.SalesmanCustom;
import com.jf.entity.SalesmanCustomExample;
import com.jf.entity.SalesmanExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanCustomMapper{

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午3:36:03
	 */
	Integer countByCustomExample(SalesmanCustomExample salesmanCustomExample);

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午3:36:07
	 */
	List<SalesmanCustom> selectByCustomExample(
			SalesmanCustomExample salesmanCustomExample);

}