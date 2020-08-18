package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CombineOrderCustomMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.CombineOrderCustomExample;
import com.jf.entity.CombineOrderExample;
import com.jf.vo.MemberPayDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CombineOrderService extends BaseService<CombineOrder,CombineOrderExample> {
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	@Autowired
	private CombineOrderCustomMapper combineOrderCustomMapper;
	
	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper combineOrderMapper) {
		super.setDao(combineOrderMapper);
		this.combineOrderMapper = combineOrderMapper;
	}
	
	public int countCombineOrderCustomByExample(CombineOrderExample example){
		return combineOrderCustomMapper.countByExample(example);
	}
    public List<CombineOrderCustom> selectCombineOrderCustomByExample(CombineOrderExample example){
    	return combineOrderCustomMapper.selectByExample(example);
    }
    public CombineOrderCustom selectCombineOrderCustomByPrimaryKey(Integer id){
    	return combineOrderCustomMapper.selectByPrimaryKey(id);
    }

	public List<CombineOrderCustom> receivablesCountEachDayList(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.receivablesCountEachDayList(paramMap);
	}



	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public int countMember(QueryObject queryObject) {
		return combineOrderCustomMapper.countByExampleGroupByMemberId(builderQuery(queryObject));
	}

	public List<CombineOrder> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private CombineOrderExample builderQuery(QueryObject queryObject) {
		CombineOrderCustomExample example = new CombineOrderCustomExample();
		CombineOrderCustomExample.CombineOrderCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("payStatus") != null){
			criteria.andPayStatusEqualTo(queryObject.getQueryToStr("payStatus"));
		}
		if(queryObject.getQuery("payDateAfter") != null){
			criteria.andPayDateGreaterThan(queryObject.getQueryToDate("payDateAfter"));
		}
		if(queryObject.getQuery("payDateBefore") != null){
			criteria.andPayDateLessThan(queryObject.getQueryToDate("payDateBefore"));
		}
		if(queryObject.getQuery("repeatOrder") != null){
			criteria.andRepeatOrder();
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public void updateCombineOrders(HashMap<String, Object> paramMap) {
		combineOrderCustomMapper.updateCombineOrders(paramMap);
	}

	public List<Map<String, Object>> receivablesCount(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.receivablesCount(paramMap);
	}

	public Integer countByCustomExample(CombineOrderCustomExample example) {
		return combineOrderCustomMapper.countByCustomExample(example);
	}

	public List<CombineOrderCustom> selectByCustomExample(CombineOrderCustomExample example) {
		return combineOrderCustomMapper.selectByCustomExample(example);
	}

	public List<HashMap<String, Object>> androidCount(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.androidCount(paramMap);
	}
	
	public List<HashMap<String, Object>> totalAndroidCount(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.totalAndroidCount(paramMap);
	}
	
	public List<HashMap<String, Object>> iosCount(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.iosCount(paramMap);
	}
	
	public List<HashMap<String, Object>> totalIosCount(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.totalIosCount(paramMap);
	}
	
	public List<HashMap<String, Object>> androidCountNew(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.androidCountNew(paramMap);
	}
	
	public List<HashMap<String, Object>> androidCountNewNull(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.androidCountNewNull(paramMap);
	}
	
	public List<HashMap<String, Object>> iosCountNew(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.iosCountNew(paramMap);
	}
	
	public List<HashMap<String, Object>> iosCountNewNull(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.iosCountNewNull(paramMap);
	}
	
	public List<HashMap<String, Object>> androidCountReturn(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.androidCountReturn(paramMap);
	}

	public List<HashMap<String, Object>> totalAndroidCountReturn(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.totalAndroidCountReturn(paramMap);
	}
	
	public List<HashMap<String, Object>> iosCountReturn(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.iosCountReturn(paramMap);
	}
	
	public List<HashMap<String, Object>> totalIosCountReturn(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.totalIosCountReturn(paramMap);
	}
	
	public List<CombineOrderCustom> paymentIncomeCountEachDayList(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.paymentIncomeCountEachDayList(paramMap);
	}

	public int paymentIncomeCountEachDayCount(HashMap<String, Object> paramMap) {
		return combineOrderCustomMapper.paymentIncomeCountEachDayCount(paramMap);
	}

    public List<MemberPayDataVo> statMemberPayData(QueryObject queryObject) {
        return combineOrderCustomMapper.statMemberPayData(queryObject);
    }

    public List<MemberPayDataVo> statSvipMemberPayData(HashMap<String, Object> paramMap) {
        return combineOrderCustomMapper.statSvipMemberPayData(paramMap);
    }

	public List<Map<String, Object>> androidChannelGroupList(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.androidChannelGroupList(paramMap);
	}

	public List<Map<String, Object>> androidChannelGroupNull(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.androidChannelGroupNull(paramMap);
	}

	public List<Map<String, Object>> spreadActivityGroupList(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.spreadActivityGroupList(paramMap);
	}

	public List<Map<String, Object>> spreadActivityGroupNull(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.spreadActivityGroupNull(paramMap);
	}

	public Map<String, Object> iosSpreadChannelDataTotal(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.iosSpreadChannelDataTotal(paramMap);
	}

	public Map<String, Object> androidSpreadChannelDataTotal(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.androidSpreadChannelDataTotal(paramMap);
	}

	public List<Map<String, Object>> iosSpreadChannelDataList(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.iosSpreadChannelDataList(paramMap);
	}

	public List<Map<String, Object>> androidSpreadChannelDataList(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.androidSpreadChannelDataList(paramMap);
	}

	public Map<String, Object> getTotalPayAmountSum(Map<String, Object> paramMap) {
		return combineOrderCustomMapper.getTotalPayAmountSum(paramMap);
	}

}
