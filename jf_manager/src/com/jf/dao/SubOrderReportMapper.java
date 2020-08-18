package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单排行Dao
 */
@Repository
public interface SubOrderReportMapper {

	/**
	 * 
	* @Title selectSubOrder   
	* @Description TODO(获取订单报表统计)   
	* @author 彭立
	* @date 2017年9月8日 上午9:01:23
	 */
	public List<Map<String, Object>> selectSubOrder(Map<String, Object> map);
	
	/**
	 * 
	 * @Title selectReQuantityRate   
	 * @Description TODO(获取退货率)   
	 * @author pengl
	 * @date 2018年10月25日 下午5:52:15
	 */
	public String selectReQuantityRate(Map<String, Object> map);

	/**
	 * 
	 * @Title selectGoodCommentRate   
	 * @Description TODO(获取好评率)   
	 * @author pengl
	 * @date 2018年10月25日 下午5:52:41
	 */
	public String selectGoodCommentRate(Map<String, Object> map);

    List<Map<String, Object>> selectProductSubOrder(Map<String, Object> map);

	public List<Map<String, Object>> ManageSelfSubOrder(Map<String, Object> map);
}
