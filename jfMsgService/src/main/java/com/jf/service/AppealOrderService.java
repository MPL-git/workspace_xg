package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.dao.AppealOrderMapper;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月6日 下午3:23:42 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AppealOrderService extends BaseService<AppealOrder, AppealOrderExample> {
	
	@Autowired
	private AppealOrderMapper appealOrderMapper;
	@Autowired
	private AppealLogService appealLogService;

	@Autowired
	public void setAppealOrderMapper(AppealOrderMapper appealOrderMapper) {
		this.setDao(appealOrderMapper);
		this.appealOrderMapper = appealOrderMapper;
	}


	public List<AppealOrder> findCloseAppealOrderList() {
		String status = "1";//状态 1待客户回复 2待商家回复 3投诉单超时关闭 4平台关闭 5用户关闭
		Date overtime = DateUtil.addDay(new Date(), -15);
		AppealOrderExample appealOrderExample = new AppealOrderExample();
		appealOrderExample.createCriteria().andStatusEqualTo(status).andUpdateDateLessThanOrEqualTo(overtime).andDelFlagEqualTo("0");
		return selectByExample(appealOrderExample);
	}


	public void closeAppealOrder(AppealOrder appealOrder) {
		String status = "1";//状态 1待客户回复 2待商家回复 3投诉单超时关闭 4平台关闭 5用户关闭
		AppealOrderExample appealOrderExample = new AppealOrderExample();
		appealOrderExample.createCriteria().andStatusEqualTo(status).andIdEqualTo(appealOrder.getId()).andDelFlagEqualTo("0");
		appealOrder.setStatus("4");
		appealOrder.setUpdateDate(new Date());
		int count = updateByExampleSelective(appealOrder, appealOrderExample);
		if(count > 0){
			appealLogService.add(appealOrder.getId(), "客户超时未回复关闭", null, new Date(), "系统", "2");
		}
	}
	
}
