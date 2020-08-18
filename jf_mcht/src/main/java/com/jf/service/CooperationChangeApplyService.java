package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CooperationChangeApplyCustomMapper;
import com.jf.dao.CooperationChangeApplyMapper;
import com.jf.dao.CooperationChangeUploadPicMapper;
import com.jf.dao.MchtBrandRateChangeMapper;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CooperationChangeApplyService extends BaseService<CooperationChangeApply,CooperationChangeApplyExample> {
	@Autowired
	private CooperationChangeApplyMapper cooperationChangeApplyMapper;

	@Autowired
	private CooperationChangeUploadPicMapper cooperationChangeUploadPicMapper;
	
	@Autowired
	private CooperationChangeApplyCustomMapper cooperationChangeApplyCustomMapper;
	
	@Autowired
	private MchtBrandRateChangeMapper mchtBrandRateChangeMapper;
	
	@Autowired
	public void setCooperationChangeApplyMapper(CooperationChangeApplyMapper cooperationChangeApplyMapper) {
		super.setDao(cooperationChangeApplyMapper);
		this.cooperationChangeApplyMapper = cooperationChangeApplyMapper;
	}
	
	public int countByCustomExample(CooperationChangeApplyCustomExample example){
		return cooperationChangeApplyCustomMapper.countByExample(example);
	}
    public List<CooperationChangeApplyCustom> selectByCustomExample(CooperationChangeApplyCustomExample example){
    	return cooperationChangeApplyCustomMapper.selectByExample(example);
    }

	public void save(CooperationChangeApply cooperationChangeApply,String mchtBrandRateChangeJsonStr) {
		if(cooperationChangeApply.getId()!=null){
			this.updateByPrimaryKeySelective(cooperationChangeApply);
		}else{
			this.insertSelective(cooperationChangeApply);
		}
		MchtBrandRateChangeExample example = new MchtBrandRateChangeExample();
		example.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId());
		MchtBrandRateChange mchtBrandRateChange = new MchtBrandRateChange();
		mchtBrandRateChange.setUpdateDate(new Date());
		mchtBrandRateChange.setDelFlag("1");
		mchtBrandRateChangeMapper.updateByExampleSelective(mchtBrandRateChange, example);
		if(!StringUtil.isEmpty(mchtBrandRateChangeJsonStr)){
			JSONArray ja = JSONArray.fromObject(mchtBrandRateChangeJsonStr);
			for(int i=0;i<ja.size();i++){
				MchtBrandRateChange mbrc = new MchtBrandRateChange();
				mbrc.setCreateDate(new Date());
				mbrc.setCreateBy(cooperationChangeApply.getUpdateBy());
				mbrc.setDelFlag("0");
				mbrc.setMchtId(cooperationChangeApply.getMchtId());
				mbrc.setCooperationChangeApplyId(cooperationChangeApply.getId());
				JSONObject jo = (JSONObject)ja.get(i);
				Integer mchtProductBrandId = jo.getInt("mchtProductBrandId");
				String popCommissionRate = jo.getString("popCommissionRate");
				mbrc.setMchtProductBrandId(mchtProductBrandId);
				mbrc.setPopCommissionRate(new BigDecimal(popCommissionRate));
				mchtBrandRateChangeMapper.insertSelective(mbrc);
			}
		}
	}

	public void toUploadPicture(CooperationChangeApply cooperationChangeApply, JSONArray cooperationChangeUploadPics){
		cooperationChangeApplyMapper.updateByPrimaryKey(cooperationChangeApply);
		if(cooperationChangeUploadPics != null){
			for(int i=0; i<cooperationChangeUploadPics.size(); i++){
				JSONObject cooperationChangeUploadPicsJSONObject = cooperationChangeUploadPics.getJSONObject(i);
				CooperationChangeUploadPic cooperationChangeUploadPic = new CooperationChangeUploadPic();
				cooperationChangeUploadPic.setCooperationChangeApplyId(cooperationChangeApply.getId());
				cooperationChangeUploadPic.setPic(cooperationChangeUploadPicsJSONObject.getString("pic"));
				cooperationChangeUploadPic.setCreateBy(cooperationChangeApply.getCreateBy());
				cooperationChangeUploadPic.setCreateDate(new Date());
				cooperationChangeUploadPic.setDelFlag("0");
				cooperationChangeUploadPicMapper.insertSelective(cooperationChangeUploadPic);
			}
		}
	}
}
