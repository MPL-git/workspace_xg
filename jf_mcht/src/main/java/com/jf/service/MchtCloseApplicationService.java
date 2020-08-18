package com.jf.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.base.BaseService;
import com.jf.dao.MchtCloseApplicationCustomMapper;
import com.jf.dao.MchtCloseApplicationMapper;
import com.jf.dao.MchtCloseApplicationPicMapper;
import com.jf.entity.MchtCloseApplication;
import com.jf.entity.MchtCloseApplicationCustom;
import com.jf.entity.MchtCloseApplicationCustomExample;
import com.jf.entity.MchtCloseApplicationExample;
import com.jf.entity.MchtCloseApplicationPic;
import com.jf.entity.MchtCloseApplicationPicExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtCloseApplicationService extends BaseService<MchtCloseApplication, MchtCloseApplicationExample> {

	@Autowired
	private MchtCloseApplicationMapper mchtCloseApplicationMapper;
	
	@Autowired
	private MchtCloseApplicationCustomMapper mchtCloseApplicationCustomMapper;
	
	@Autowired
	private MchtCloseApplicationPicMapper mchtCloseApplicationPicMapper;
	
	@Autowired
	public void setMchtCloseApplicationMapper(MchtCloseApplicationMapper mchtCloseApplicationMapper) {
		super.setDao(mchtCloseApplicationMapper);
		this.dao = mchtCloseApplicationMapper;
	}
	
	public int countByCustomExample(MchtCloseApplicationCustomExample example) {
		return mchtCloseApplicationCustomMapper.countByExample(example);
	}

	public List<MchtCloseApplicationCustom> selectByCustomExample(MchtCloseApplicationCustomExample example) {
		return mchtCloseApplicationCustomMapper.selectByExample(example);
	}

	public MchtCloseApplicationCustom selectCustomByPrimaryKey(Integer id) {
		return mchtCloseApplicationCustomMapper.selectCustomByPrimaryKey(id);
	}
	
	
	public  void commitUploadProtocol(JSONArray imageArray , MchtCloseApplication mca){
		//设置状态
		mca.setContractUploadDate(new Date());//合同上传日期
		mca.setContractAuditStatus("1");//终止合同线上审核状态 
		mchtCloseApplicationMapper.updateByPrimaryKeySelective(mca);
		
		//更新图片
		MchtCloseApplicationPicExample mcap4Example = new MchtCloseApplicationPicExample();
		mcap4Example.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(mca.getId());
		MchtCloseApplicationPic mcap4Del = new MchtCloseApplicationPic();
		mcap4Del.setDelFlag("1");
		mchtCloseApplicationPicMapper.updateByExampleSelective(mcap4Del, mcap4Example );
		
		JSONObject jsonObject;
		for (int i = 0; i < imageArray.size(); i ++) {
			jsonObject = imageArray.getJSONObject(i);
			String pic = jsonObject.getString("pic");
			MchtCloseApplicationPicExample mcap4UpdateExample = new MchtCloseApplicationPicExample();
			mcap4UpdateExample.createCriteria().andDelFlagEqualTo("1").andPicEqualTo(pic).andMchtCloseApplicationIdEqualTo(mca.getId());
			MchtCloseApplicationPic mcap4Update = new MchtCloseApplicationPic();
			mcap4Update.setDelFlag("0");
			int updateCount = mchtCloseApplicationPicMapper.updateByExampleSelective(mcap4Update, mcap4UpdateExample);
			 if(updateCount>0){
				 continue;
			 }
			
		  MchtCloseApplicationPic mcap4Add = new MchtCloseApplicationPic();
		  mcap4Add.setCreateDate(new Date());
		  mcap4Add.setDelFlag("0");
		  mcap4Add.setMchtCloseApplicationId(mca.getId());
		  mcap4Add.setPic(pic);
		  mchtCloseApplicationPicMapper.insertSelective(mcap4Add);	
			
		}
	}
	
	
}
