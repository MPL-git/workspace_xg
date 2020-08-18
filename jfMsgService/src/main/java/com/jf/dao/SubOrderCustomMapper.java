package com.jf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jf.entity.SubOrderCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SubOrder;

@Repository
public interface SubOrderCustomMapper{

	/**
	 * 根据配置查出超时未发货的订单
	 * @return
	 */
    List<SubOrder> selectOverTimeDeliveryListByCnf();
    
	/**
	 * 查出超时未发货的订单（默认48小时）
	 * @return
	 */
    List<SubOrder> selectOverTimeDeliveryListByDefault();
    
    
    /**
     * 查出超时未发货的订单（默认48小时）
     * @return
     */
    List<SubOrder> selectOverTimeDeliveryList();
    
    
    /**
     * 查询虚假发货的子订单
     * @return
     */
    List<SubOrder> selectShamDeliveryList(@Param("bgnDeliveryDate") Date bgnDeliveryDate);
    
    
    /**
     * 批量插入虚假发货
     * @return
     */
    int batchInsertShamDelivery(@Param("bgnDeliveryDate") Date bgnDeliveryDate);
    
    /**
     * 查询未生成违规单的虚假发货订单
     * @return
     */
    List<SubOrder> selectShamDeliveryOrderWithNoViolateOrder();
    
    
    
    /**
     * <!--  付款后72小时未发货标记为缺货，只处理5天内的订单 -->
     * @return
     */
    int batchInsertOutOfStock();
    
    
    /**
     * 查询未生成违规单的缺货订单
     * @return
     */
    List<SubOrder> selectOutOfStockOrderWithNoViolateOrder();


	public List<SubOrderCustom> selectCollegeStudentOrderList(@Param("auditStatus") String auditStatus);

    void bulkUpdateSubOrderAuditStatus(Map<String, Object> params);

}
