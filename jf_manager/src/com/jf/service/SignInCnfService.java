package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DateUtil;
import com.jf.dao.SignInCnfCustomMapper;
import com.jf.dao.SignInCnfDtlMapper;
import com.jf.dao.SignInCnfMapper;
import com.jf.entity.SignInCnf;
import com.jf.entity.SignInCnfCustom;
import com.jf.entity.SignInCnfCustomExample;
import com.jf.entity.SignInCnfDtl;
import com.jf.entity.SignInCnfExample;

@Service
@Transactional
public class SignInCnfService extends BaseService<SignInCnf, SignInCnfExample> {

	@Autowired
	private SignInCnfMapper signInCnfMapper;
	
	@Autowired
	private SignInCnfCustomMapper signInCnfCustomMapper;
	
	@Autowired
	private SignInCnfDtlMapper signInCnfDtlMapper;
	
	@Autowired
	public void setSignInCnfMapper(SignInCnfMapper signInCnfMapper) {
		super.setDao(signInCnfMapper);
		this.signInCnfMapper = signInCnfMapper;
	}
	
	public int countByCustomExample(SignInCnfCustomExample example) {
		return signInCnfCustomMapper.countByCustomExample(example);
	}

    public List<SignInCnfCustom> selectByCustomExample(SignInCnfCustomExample example) {
    	return signInCnfCustomMapper.selectByCustomExample(example);
    }

    public SignInCnfCustom selectByPrimaryKeyCustom(Integer id) {
    	return signInCnfCustomMapper.selectByPrimaryKeyCustom(id);
    }

    public int updateByCustomExampleSelective(SignInCnf record, SignInCnfCustomExample example) {
    	return signInCnfCustomMapper.updateByCustomExampleSelective(record, example);
    }
    
    public Integer selectMemberCount() {
    	return signInCnfCustomMapper.selectMemberCount();
    }
    
    public String selectAmountSum(List<String> bizTypeList) {
    	return signInCnfCustomMapper.selectAmountSum(bizTypeList);
    }
	
    /**
     * 
     * @Title updateIsEffect   
     * @Description TODO(启用)   
     * @author pengl
     * @date 2018年5月17日 下午5:42:21
     */
    public void updateIsEffect(SignInCnf signInCnf) {
    	SignInCnfExample signInCnfExample = new SignInCnfExample();
    	SignInCnfExample.Criteria signInCnfCriteria = signInCnfExample.createCriteria();
    	signInCnfCriteria.andDelFlagEqualTo("0").andIdNotEqualTo(signInCnf.getId());
    	SignInCnf signInCnfClose = new SignInCnf();
    	signInCnfClose.setIsEffect("0");
    	signInCnfMapper.updateByExampleSelective(signInCnfClose, signInCnfExample);
    	signInCnfMapper.updateByPrimaryKeySelective(signInCnf);
    }
    
    /**
     * 
     * @Title addOrUpdateSignInCnf   
     * @Description TODO(新增或修改签到方案)   
     * @author pengl
     * @date 2018年5月18日 下午4:37:36
     */
    public void addOrUpdateSignInCnf(SignInCnf signInCnf, SignInCnfDtl sqSignInCnfDtl, SignInCnfDtl ljSignInCnfDtl, SignInCnfDtl oneSignInCnfDtl, SignInCnfDtl twoSignInCnfDtl, 
    		SignInCnfDtl threeSignInCnfDtl, SignInCnfDtl fourSignInCnfDtl, SignInCnfDtl qtSignInCnfDtl, SignInCnfDtl syqSignInCnfDtl, SignInCnfDtl xyqSignInCnfDtl, SignInCnfDtl lyqSignInCnfDtl) {
    	if(signInCnf.getId() == null) { //新增
    		signInCnfMapper.insertSelective(signInCnf);
    		sqSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(sqSignInCnfDtl);
    		ljSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(ljSignInCnfDtl);
    		oneSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(oneSignInCnfDtl);
    		twoSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(twoSignInCnfDtl);
    		threeSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(threeSignInCnfDtl);
    		fourSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(fourSignInCnfDtl);
    		qtSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(qtSignInCnfDtl);
    		syqSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(syqSignInCnfDtl);
    		xyqSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(xyqSignInCnfDtl);
    		lyqSignInCnfDtl.setSignInCnfId(signInCnf.getId());
    		signInCnfDtlMapper.insertSelective(lyqSignInCnfDtl);
    	}else { //修改
    		Integer updateById = signInCnf.getCreateBy();
    		Date date = signInCnf.getCreateDate();
    		signInCnf.setIsEffect(null); //是否启用
    		signInCnf.setCreateBy(null);
    		signInCnf.setCreateDate(null);
    		signInCnf.setUpdateBy(updateById);
    		signInCnf.setUpdateDate(date);
    		signInCnfMapper.updateByPrimaryKeySelective(signInCnf);
    		sqSignInCnfDtl.setCreateBy(null);
    		sqSignInCnfDtl.setCreateDate(null);
    		sqSignInCnfDtl.setUpdateBy(updateById);
    		sqSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(sqSignInCnfDtl);
    		ljSignInCnfDtl.setCreateBy(null);
    		ljSignInCnfDtl.setCreateDate(null);
    		ljSignInCnfDtl.setUpdateBy(updateById);
    		ljSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(ljSignInCnfDtl);
    		oneSignInCnfDtl.setCreateBy(null);
    		oneSignInCnfDtl.setCreateDate(null);
    		oneSignInCnfDtl.setUpdateBy(updateById);
    		oneSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(oneSignInCnfDtl);
    		twoSignInCnfDtl.setCreateBy(null);
    		twoSignInCnfDtl.setCreateDate(null);
    		twoSignInCnfDtl.setUpdateBy(updateById);
    		twoSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(twoSignInCnfDtl);
    		threeSignInCnfDtl.setCreateBy(null);
    		threeSignInCnfDtl.setCreateDate(null);
    		threeSignInCnfDtl.setUpdateBy(updateById);
    		threeSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(threeSignInCnfDtl);
    		fourSignInCnfDtl.setCreateBy(null);
    		fourSignInCnfDtl.setCreateDate(null);
    		fourSignInCnfDtl.setUpdateBy(updateById);
    		fourSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(fourSignInCnfDtl);
    		qtSignInCnfDtl.setCreateBy(null);
    		qtSignInCnfDtl.setCreateDate(null);
    		qtSignInCnfDtl.setUpdateBy(updateById);
    		qtSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(qtSignInCnfDtl);
    		syqSignInCnfDtl.setCreateBy(null);
    		syqSignInCnfDtl.setCreateDate(null);
    		syqSignInCnfDtl.setUpdateBy(updateById);
    		syqSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(syqSignInCnfDtl);
    		xyqSignInCnfDtl.setCreateBy(null);
    		xyqSignInCnfDtl.setCreateDate(null);
    		xyqSignInCnfDtl.setUpdateBy(updateById);
    		xyqSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(xyqSignInCnfDtl);
    		lyqSignInCnfDtl.setCreateBy(null);
    		lyqSignInCnfDtl.setCreateDate(null);
    		lyqSignInCnfDtl.setUpdateBy(updateById);
    		lyqSignInCnfDtl.setUpdateDate(date);
    		signInCnfDtlMapper.updateByPrimaryKeySelective(lyqSignInCnfDtl);
    	}
    }
    
    /**
     * 
     * @Title selectSignInCnfStatisticsList   
     * @Description TODO(现金签到数据统计)   
     * @author pengl
     * @date 2018年6月13日 上午11:07:05
     */
    public List<Map<String, Object>> selectSignInCnfStatisticsList(List<String> dates, String dateFlag) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String beginDate = "";
		String endDate = "";
		Map<String, String> mapParam = new HashMap<String, String>();
		if("day".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + " 00:00:00";
				endDate = str + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				Map<String, Object> map = signInCnfCustomMapper.selectSignInCnfStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("tx_amount_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("tx_amount_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}else if("month".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01 00:00:00";
				String[] ss = str.split("-");
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				Map<String, Object> map = signInCnfCustomMapper.selectSignInCnfStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("tx_amount_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("tx_amount_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}else if("year".equals(dateFlag)) {
			for(String str : dates) {
				beginDate = str + "-01-01 00:00:00";
				endDate = DateUtil.getLastDayOfMonth(Integer.parseInt(str), 12) + " 23:59:59";
				mapParam.put("beginDate", beginDate);
				mapParam.put("endDate", endDate);
				Map<String, Object> map = signInCnfCustomMapper.selectSignInCnfStatisticsList(mapParam);
				map.put("date", str);
				if("0.00".equals(map.get("yg_commission_amount_sum").toString()) || "0.00".equals(map.get("tx_amount_sum").toString())) {
					map.put("con_percent", "0.00");
				}else {
					map.put("con_percent", new BigDecimal(map.get("yg_commission_amount_sum").toString())
						.divide(new BigDecimal(map.get("tx_amount_sum").toString()), 4, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
				}
				listMap.add(map);
			}
		}
		return listMap;
	}
   
}
