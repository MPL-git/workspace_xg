package com.jf.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StaffReplyCustomMapper;
import com.jf.dao.StaffReplyMapper;
import com.jf.dao.StaffReplyPicMapper;
import com.jf.entity.StaffReply;
import com.jf.entity.StaffReplyCustom;
import com.jf.entity.StaffReplyExample;
import com.jf.entity.StaffReplyPic;

@Service
@Transactional
public class StaffReplyService extends BaseService<StaffReply, StaffReplyExample> {

	@Resource
	private StaffReplyMapper staffReplyMapper;
	
	@Resource
	StaffReplyCustomMapper staffReplyCustomMapper;
	
	@Resource
	StaffReplyPicMapper staffReplyPicMapper;
	
	@Autowired
	public void setStaffReplyMapperMapper(StaffReplyMapper staffReplyMapper) {
		super.setDao(staffReplyMapper);
		this.staffReplyMapper = staffReplyMapper;
	}
	
	public int countByStaffReplyCustomExample(StaffReplyExample example){
		return staffReplyCustomMapper.staffReplycountByExample(example);
	}
    public List<StaffReplyCustom> selectByStaffReplyCustomExample(StaffReplyExample example){
    	return staffReplyCustomMapper.staffReplyselectByExample(example);
    }
    public StaffReplyCustom selectByStaffReplyCustomPrimaryKey(Integer id){
    	return staffReplyCustomMapper.staffReplyCustomselectByPrimaryKey(id);
    }
	
	
	//插入数据
  	public void insertStaffReply(StaffReply staffReply,String replyPics){
  		//插入员工回复表
  		staffReplyMapper.insertSelective(staffReply);
  		//插入图片表
  		JSONArray replyPic=JSONArray.fromObject(replyPics);
  		Iterator<JSONObject> it= replyPic.iterator();
  		while (it.hasNext()) {
  			JSONObject replypic=it.next();
  			StaffReplyPic replypics=new StaffReplyPic();
  			replypics.setReplyId(staffReply.getId());
  			replypics.setReplyPic(replypic.getString("picPath"));
  			replypics.setCreateBy(staffReply.getCreateBy());
  			replypics.setCreateDate(staffReply.getCreateDate());
  			staffReplyPicMapper.insertSelective(replypics);
  			
  		}
  	}
	
}
