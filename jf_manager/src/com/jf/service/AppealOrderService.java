package com.jf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.AppealOrderCustomMapper;
import com.jf.dao.AppealOrderMapper;
import com.jf.dao.AppealPlatformRemarksPicMapper;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.AppealPlatformRemarksPic;

@Service
@Transactional
public class AppealOrderService extends BaseService<AppealOrder,AppealOrderExample> {
	@Autowired
	private AppealOrderMapper dao;
	
	@Autowired
	private AppealOrderCustomMapper appealOrderCustomMapper;
	
	@Autowired
	private AppealPlatformRemarksPicMapper appealPlatformRemarksPicMapper;
	
	@Autowired
	public void setAppealOrderMapper(AppealOrderMapper appealOrderMapper) {
		super.setDao(appealOrderMapper);
		this.dao = appealOrderMapper;
	}

	public int countAppealOrderCustomByExample(AppealOrderCustomExample example) {
		return appealOrderCustomMapper.countByExample(example);
	}

	public List<AppealOrderCustom> selectAppealOrderCustomByExample(AppealOrderCustomExample example) {
		return appealOrderCustomMapper.selectByExample(example);
	}
	
	public List<Map<String, Object>> getPlatformProcessorList() {
		return appealOrderCustomMapper.getPlatformProcessorList();
	}
	
	public void updatePlatformRemarks(HttpServletRequest request, Integer staffId) {
		Date date = new Date();
		String id = request.getParameter("id");
		String platformRemarks = request.getParameter("platformRemarks");
		String picNameA = request.getParameter("picNameA");
		String picNameB = request.getParameter("picNameB");
		String picNameC = request.getParameter("picNameC");
		
		AppealOrder appealOrder = new AppealOrder();
		appealOrder.setId(Integer.parseInt(id));
		appealOrder.setPlatformRemarks(platformRemarks);
		appealOrder.setUpdateBy(staffId);
		appealOrder.setUpdateDate(date);
		dao.updateByPrimaryKeySelective(appealOrder);
		
		if(!StringUtil.isEmpty(picNameA)) {
			AppealPlatformRemarksPic appealPlatformRemarksPicA = new AppealPlatformRemarksPic();
			appealPlatformRemarksPicA.setAppealOrderId(appealOrder.getId());
			appealPlatformRemarksPicA.setPic(picNameA);
			appealPlatformRemarksPicA.setCreateBy(staffId);
			appealPlatformRemarksPicA.setCreateDate(date);
			appealPlatformRemarksPicA.setDelFlag("0");
			appealPlatformRemarksPicMapper.insertSelective(appealPlatformRemarksPicA);
		}
		if(!StringUtil.isEmpty(picNameB)) {
			AppealPlatformRemarksPic appealPlatformRemarksPicB = new AppealPlatformRemarksPic();
			appealPlatformRemarksPicB.setAppealOrderId(appealOrder.getId());
			appealPlatformRemarksPicB.setPic(picNameB);
			appealPlatformRemarksPicB.setCreateBy(staffId);
			appealPlatformRemarksPicB.setCreateDate(date);
			appealPlatformRemarksPicB.setDelFlag("0");
			appealPlatformRemarksPicMapper.insertSelective(appealPlatformRemarksPicB);
		}
		if(!StringUtil.isEmpty(picNameC)) {
			AppealPlatformRemarksPic appealPlatformRemarksPicC = new AppealPlatformRemarksPic();
			appealPlatformRemarksPicC.setAppealOrderId(appealOrder.getId());
			appealPlatformRemarksPicC.setPic(picNameC);
			appealPlatformRemarksPicC.setCreateBy(staffId);
			appealPlatformRemarksPicC.setCreateDate(date);
			appealPlatformRemarksPicC.setDelFlag("0");
			appealPlatformRemarksPicMapper.insertSelective(appealPlatformRemarksPicC);
		}
	}
	
}
