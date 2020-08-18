package com.jf.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StaffOpinionFeedbackCustomMapper;
import com.jf.dao.StaffOpinionFeedbackMapper;
import com.jf.dao.StaffOpinionFeedbackPicMapper;
import com.jf.entity.StaffOpinionFeedback;
import com.jf.entity.StaffOpinionFeedbackCustom;
import com.jf.entity.StaffOpinionFeedbackExample;
import com.jf.entity.StaffOpinionFeedbackPic;

@Service
@Transactional
public class StaffOpinionFeedbackService extends BaseService<StaffOpinionFeedback, StaffOpinionFeedbackExample>{
	@Resource
	StaffOpinionFeedbackMapper staffOpinionFeedbackMapper;
	
	@Resource
	StaffOpinionFeedbackCustomMapper staffOpinionFeedbackCustomMapper;
	
	@Resource
	StaffOpinionFeedbackPicMapper staffOpinionFeedbackPicMapper;
	
	@Autowired
	public void setStaffOpinionFeedbackMapper(StaffOpinionFeedbackMapper staffOpinionFeedbackMapper) {
		super.setDao(staffOpinionFeedbackMapper);
		this.staffOpinionFeedbackMapper = staffOpinionFeedbackMapper;
	}
	
	public int countBystaffOpinionFeedbackCustomExample(StaffOpinionFeedbackExample example){
		return staffOpinionFeedbackCustomMapper.staffOpinionFeedbackcountByExample(example);
	}
    public List<StaffOpinionFeedbackCustom> selectByStaffOpinionFeedbackCustomExample(StaffOpinionFeedbackExample example){
    	return staffOpinionFeedbackCustomMapper.staffOpinionFeedbackselectByExample(example);
    }
    public StaffOpinionFeedbackCustom selectByStaffOpinionFeedbackCustomPrimaryKey(Integer id){
    	return staffOpinionFeedbackCustomMapper.staffOpinionFeedbackselectByPrimaryKey(id);
    }
    
    //插入数据
  	public void insertStaffOpinionFeedback(StaffOpinionFeedback staffOpinionFeedback,String staffFeedbackContentPics){
  		//插入员工反馈表
  		staffOpinionFeedbackMapper.insertSelective(staffOpinionFeedback);
  		//插入图片表
  		JSONArray staffOpinionFeedbackPic=JSONArray.fromObject(staffFeedbackContentPics);
  		Iterator<JSONObject> it= staffOpinionFeedbackPic.iterator();
  		while (it.hasNext()) {
  			JSONObject stafffeedbackContentPic=it.next();
  			StaffOpinionFeedbackPic staffOpinionFeedbackPics=new StaffOpinionFeedbackPic();
  			staffOpinionFeedbackPics.setFeedbackContentId(staffOpinionFeedback.getId());
  			staffOpinionFeedbackPics.setFeedbackContentPic(stafffeedbackContentPic.getString("picPath"));
  			staffOpinionFeedbackPics.setCreateBy(staffOpinionFeedback.getCreateBy());
  			staffOpinionFeedbackPics.setCreateDate(staffOpinionFeedback.getCreateDate());
  			staffOpinionFeedbackPicMapper.insertSelective(staffOpinionFeedbackPics);
  			
  		}
  	}
    	
}

