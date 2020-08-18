package com.jf.service;

import com.jf.dao.TypeColumnPvStatisticalCustomMapper;
import com.jf.dao.TypeColumnPvStatisticalMapper;
import com.jf.entity.TypeColumnPvStatistical;
import com.jf.entity.TypeColumnPvStatisticalCustom;
import com.jf.entity.TypeColumnPvStatisticalExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TypeColumnPvStatisticalService extends BaseService<TypeColumnPvStatistical, TypeColumnPvStatisticalExample>{
	@Autowired
	private TypeColumnPvStatisticalMapper typeColumnPvStatisticalMapper;
	@Autowired
    private TypeColumnPvStatisticalCustomMapper typeColumnPvStatisticalCustomMapper;
	@Autowired
	public void setActivityAuthMapper(TypeColumnPvStatisticalMapper typeColumnPvStatisticalMapper) {
		super.setDao(typeColumnPvStatisticalMapper);
		this.typeColumnPvStatisticalMapper = typeColumnPvStatisticalMapper;
	}


    public List<TypeColumnPvStatisticalCustom> getCategoryProductDataStatisticsList(HashMap<String, Object> paramMap) {
	    return typeColumnPvStatisticalCustomMapper.getCategoryProductDataStatisticsList(paramMap);
    }

	public List<TypeColumnPvStatisticalCustom> selectDataStatisticsList(Map<String, Object> paramMap) {
		return typeColumnPvStatisticalCustomMapper.selectDataStatisticsList(paramMap);
	}

    public List<TypeColumnPvStatisticalCustom> selectShopDataStatisticsList(Map<String, Object> paramMap) {
		return typeColumnPvStatisticalCustomMapper.selectShopDataStatisticsList(paramMap);
    }

	public Integer countActivityTrafficStatistics(HashMap<String, Object> paraMap) {
		return typeColumnPvStatisticalCustomMapper.countActivityTrafficStatistics(paraMap);
	}

	public List<HashMap<String,Object>> selectActivityTrafficStatistics(HashMap<String, Object> paraMap) {
		return typeColumnPvStatisticalCustomMapper.selectActivityTrafficStatistics(paraMap);
	}

	public Integer countSingleProductActivityTrafficStatistics(HashMap<String, Object> paraMap) {
		return typeColumnPvStatisticalCustomMapper.countSingleProductActivityTrafficStatistics(paraMap);
	}

	public List<HashMap<String,Object>> selectSingleProductActivityTrafficStatistics(HashMap<String, Object> paraMap) {
		return typeColumnPvStatisticalCustomMapper.selectSingleProductActivityTrafficStatistics(paraMap);
	}

	public List<TypeColumnPvStatisticalCustom> selectNowDataStatisticsList(Map<String, Object> paramMap) {
		return typeColumnPvStatisticalCustomMapper.selectNowDataStatisticsList(paramMap);
	}

	public List<TypeColumnPvStatisticalCustom> selectNowShopDataStatisticsList(Map<String, Object> paramMap) {
		return typeColumnPvStatisticalCustomMapper.selectNowShopDataStatisticsList(paramMap);
	}

	/**
	 * 商品流量报表
	 * */
	public List<TypeColumnPvStatisticalCustom> getCommodityFlowReportData(Map<String, Object> paraMap) {
				return  typeColumnPvStatisticalCustomMapper.getCommodityFlowReportData(paraMap);
	}

	/**
	 * 商家流量报表
	 * */
	public List<TypeColumnPvStatisticalCustom> getShopFlowReportData(Map<String, Object> paraMap) {
		return  typeColumnPvStatisticalCustomMapper.getShopFlowReportData(paraMap);
	}

	public List<TypeColumnPvStatisticalCustom> getBrandFlowReportData(Map<String, Object> paraMap) {
		return  typeColumnPvStatisticalCustomMapper.getBrandFlowReportData(paraMap);
	}

	public List<TypeColumnPvStatisticalCustom> getcategoryFlowReportData(Map<String, Object> paraMap) {
		return  typeColumnPvStatisticalCustomMapper.getcategoryFlowReportData(paraMap);
	}

	public TypeColumnPvStatisticalCustom getVisitorCount(Map<String, Object> paraMap) {
		return  typeColumnPvStatisticalCustomMapper.getVisitorCount(paraMap);
	}

	public TypeColumnPvStatisticalCustom getShopVisitorCount(Map<String, Object> paramMap) {
		return typeColumnPvStatisticalCustomMapper.getShopVisitorCount(paramMap);
	}
}
