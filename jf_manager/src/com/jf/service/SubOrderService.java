package com.jf.service;

import com.jf.dao.SubOrderCustomMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SubOrderService extends BaseService<SubOrder,SubOrderExample> {
	@Autowired
	private SubOrderMapper subOrderMapper;
	@Autowired
	private SubOrderCustomMapper subOrderCustomMapper;
	@Autowired
	private RemarksLogService remarksLogService;

	@Autowired
	public void setSubOrderMapper(SubOrderMapper subOrderMapper) {
		super.setDao(subOrderMapper);
		this.subOrderMapper = subOrderMapper;
	}

	public int countSubOrderCustomByExample(SubOrderExample example){
		return subOrderCustomMapper.countByExample(example);
	}
    public List<SubOrderCustom> selectSubOrderCustomByExample(SubOrderExample example){
    	return subOrderCustomMapper.selectByExample(example);
    }
    public List<SubOrderCustom> selectSubOrderByExample(SubOrderExample example){
    	return subOrderCustomMapper.selectSubOrderByExample(example);
    }
    public List<SubOrderCustom> selectSubOrderByExampleAndDate(SubOrderCustomExample example){
    	return subOrderCustomMapper.selectSubOrderByExampleAndDate(example);
    }
    public SubOrderCustom selectSubOrderCustomByPrimaryKey(Integer id){
    	return subOrderCustomMapper.selectByPrimaryKey(id);
    }

	public void updateRemarks(SubOrder subOrder, RemarksLog remarksLog) {
		this.updateByPrimaryKeySelective(subOrder);
		remarksLogService.insertSelective(remarksLog);
	}

	public List<HashMap<String, Object>> deliveryOvertimeCount(HashMap<String, Object> map) {
		return subOrderCustomMapper.deliveryOvertimeCount(map);
	}

	public List<HashMap<String, Object>> falseOrderCount(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.falseOrderCount(paramMap);
	}

	public List<HashMap<String, Object>> outOfStockOrderCount(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.outOfStockOrderCount(paramMap);
	}

	public List<HashMap<String, Object>> otherExceptionOrderCount(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.otherExceptionOrderCount(paramMap);
	}
    public List<HashMap<String, Object>> customerserviceOrderCount(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.customerserviceOrderCount(paramMap);
	}
	/*public List<SubOrderCustom> customerserviceOrderCount(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.customerserviceOrderCount(paramMap);
	}*/

	public List<HashMap<String, Object>> getOrderStatisticsList(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.getOrderStatisticsList(paramMap);
	}

	public Integer countOrderStatisticsList(HashMap<String, Object> paramMap) {
		return subOrderCustomMapper.countOrderStatisticsList(paramMap);
	}
	
	public List<HashMap<String, Object>> selectDailyCollectionSummary(HashMap<String, Object> paramMap){
		return subOrderCustomMapper.selectDailyCollectionSummary(paramMap);
	}
	public Integer countDailyCollectionSummary(HashMap<String, Object> paramMap){
		return subOrderCustomMapper.countDailyCollectionSummary(paramMap);
	}

    public SubOrderCustom selectStudy() {
		return subOrderCustomMapper.selectStudy();
    }

    public Integer countReceiverName(HashMap<String,Object> parameMap) {
		return subOrderCustomMapper.countReceiverName(parameMap);
    }

}
