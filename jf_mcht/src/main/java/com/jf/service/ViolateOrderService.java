package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ViolateOrderCustomMapper;
import com.jf.dao.ViolateOrderMapper;
import com.jf.entity.ComplainOrderPic;
import com.jf.vo.DebitRecord;
import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderCustom;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.entity.ViolateOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ViolateOrderService extends BaseService<ViolateOrder,ViolateOrderExample> {
	@Autowired
	private ViolateOrderMapper dao;
	
	@Autowired
	private ViolateOrderCustomMapper violateOrderCustomMapper;
	
	@Autowired
	private ViolateComplainOrderService violateComplainOrderService;
	
	@Autowired
	private ComplainOrderPicService complainOrderPicService;
	
	@Autowired
	public void setViolateOrderMapper(ViolateOrderMapper violateOrderMapper) {
		super.setDao(violateOrderMapper);
		this.dao = violateOrderMapper;
	}
	
	public int countViolateOrderCustomByExample(ViolateOrderCustomExample example){
		return violateOrderCustomMapper.countByExample(example);
	}
	
	public List<ViolateOrderCustom> selectViolateOrderCustomByExample(ViolateOrderCustomExample example){
		return violateOrderCustomMapper.selectByExample(example);
	}
	
	public List<String> getViolateTypesByMchtId(Integer mchtId){
		return violateOrderCustomMapper.getViolateTypesByMchtId(mchtId);
	}

	public void saveViolateComplainOrder(ViolateOrder violateOrder,ViolateComplainOrder violateComplainOrder,List<ComplainOrderPic> complainOrderPics) {
		this.updateByPrimaryKey(violateOrder);
		violateComplainOrderService.insertSelective(violateComplainOrder);
		if(complainOrderPics!=null && complainOrderPics.size()>0){
			for(ComplainOrderPic complainOrderPic:complainOrderPics){
				complainOrderPic.setComplainOrderId(violateComplainOrder.getId());
				complainOrderPicService.insertSelective(complainOrderPic);
			}
		}
	}

	public List<DebitRecord> getDebitRecordList(Map<String, Object> map) {
		return violateOrderCustomMapper.getDebitRecordList(map);
	}
}
