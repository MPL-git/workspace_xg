package com.jf.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.BusinessCirclesAppealOrderCustomMapper;
import com.jf.dao.BusinessCirclesAppealOrderMapper;
import com.jf.dao.BusinessCirclesAppealOrderPicMapper;
import com.jf.entity.BusinessCirclesAppealOrder;
import com.jf.entity.BusinessCirclesAppealOrderCustom;
import com.jf.entity.BusinessCirclesAppealOrderCustomExample;
import com.jf.entity.BusinessCirclesAppealOrderExample;
import com.jf.entity.BusinessCirclesAppealOrderPic;

@Service
public class BusinessCirclesAppealOrderService extends BaseService<BusinessCirclesAppealOrder, BusinessCirclesAppealOrderExample> {

	@Autowired
	private BusinessCirclesAppealOrderMapper businessCirclesAppealOrderMapper;
	
	@Autowired
	private BusinessCirclesAppealOrderCustomMapper businessCirclesAppealOrderCustomMapper;
	
	@Autowired
	private BusinessCirclesAppealOrderPicMapper businessCirclesAppealOrderPicMapper;
	
	@Autowired
	public void setworkManager(BusinessCirclesAppealOrderMapper businessCirclesAppealOrderMapper) {
		super.setDao(businessCirclesAppealOrderMapper);
		this.businessCirclesAppealOrderMapper = businessCirclesAppealOrderMapper;
	}
	
	public Integer countByCustomExample(BusinessCirclesAppealOrderCustomExample example) {
		return businessCirclesAppealOrderCustomMapper.countByCustomExample(example);
	}

	public List<BusinessCirclesAppealOrderCustom> selectByCustomExample(BusinessCirclesAppealOrderCustomExample example) {
		return businessCirclesAppealOrderCustomMapper.selectByCustomExample(example);
	}
	
	public BusinessCirclesAppealOrderCustom selectByPrimaryKeyCustom(Integer id) {
		return businessCirclesAppealOrderCustomMapper.selectByPrimaryKeyCustom(id);
	}
	
	public List<Map<String, Object>> getstaffidList() {
		return businessCirclesAppealOrderCustomMapper.getstaffidList();
	}
	public List<Map<String, Object>> getcreatebyList(){
		return businessCirclesAppealOrderCustomMapper.getcreatebyList();
	}
	public int customerServiceOrdercount(String id){
		return businessCirclesAppealOrderCustomMapper.customerServiceOrdercount(id);
	}
	public int interventionOrdercount(String id){
		return businessCirclesAppealOrderCustomMapper.interventionOrdercount(id);
	}
	public int appealOrdercount(String id){
		return businessCirclesAppealOrderCustomMapper.appealOrdercount(id);
	}
	public int subOrderCustomscuont(String id){
		return businessCirclesAppealOrderCustomMapper.subOrderCustomscuont(id);
	}
	public List<Map<String, Object>> getproStatus(String id) {
		return businessCirclesAppealOrderCustomMapper.getproStatus(id);
	}
	
	public List<Map<String, Object>> getStatus(String id) {
		return businessCirclesAppealOrderCustomMapper.getStatus(id);
	}
	
	public List<Map<String, Object>> getappealOrderstatus(String id) {
		return businessCirclesAppealOrderCustomMapper.getappealOrderstatus(id);
	}
	
   
	//插入数据
  	public void insertbusinessCirclesAppealOrder(BusinessCirclesAppealOrder businessCirclesAppealOrder,String pics){

  		businessCirclesAppealOrderMapper.insertSelective(businessCirclesAppealOrder);
  		//插入图片表
  		JSONArray pic=JSONArray.fromObject(pics);
  		Iterator<JSONObject> it= pic.iterator();
  		while (it.hasNext()) {
  			   JSONObject pic1=it.next();
			   BusinessCirclesAppealOrderPic businessCirclesAppealOrderPic=new BusinessCirclesAppealOrderPic();
			   businessCirclesAppealOrderPic.setBusinessCirclesAppealOrderId(businessCirclesAppealOrder.getId());
			   businessCirclesAppealOrderPic.setPic(pic1.getString("picPath"));
			   businessCirclesAppealOrderPic.setCreateBy(businessCirclesAppealOrder.getCreateBy());
			   businessCirclesAppealOrderPic.setCreateDate(businessCirclesAppealOrder.getCreateDate());
			   businessCirclesAppealOrderPic.setDelFlag("0");
			   businessCirclesAppealOrderPicMapper.insert(businessCirclesAppealOrderPic);
  			
  		}
  	}
	
}
