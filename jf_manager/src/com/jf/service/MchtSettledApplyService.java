package com.jf.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSettledApplyCustomMapper;
import com.jf.dao.MchtSettledApplyMapper;
import com.jf.dao.MchtSettledApplyPicMapper;
import com.jf.entity.MchtSettledApply;
import com.jf.entity.MchtSettledApplyCustom;
import com.jf.entity.MchtSettledApplyExample;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;

@Service
@Transactional
public class MchtSettledApplyService extends BaseService<MchtSettledApply,MchtSettledApplyExample> {
	@Autowired
	private MchtSettledApplyMapper mchtSettledApplyMapper;
	@Autowired
	private MchtSettledApplyCustomMapper mchtSettledApplyCustomMapper;
	@Autowired
	private MchtSettledApplyPicMapper mchtSettledApplyPicMapper;
	
	@Autowired
	public void setMchtSettledApplyMapper(MchtSettledApplyMapper mchtSettledApplyMapper) {
		super.setDao(mchtSettledApplyMapper);
		this.mchtSettledApplyMapper = mchtSettledApplyMapper;
	}
	
	public int countMchtSettledApplyCustomByExample(MchtSettledApplyExample example){
		return mchtSettledApplyCustomMapper.countByExample(example);
	}
    public List<MchtSettledApplyCustom> selectMchtSettledApplyCustomByExample(MchtSettledApplyExample example){
    	return mchtSettledApplyCustomMapper.selectByExample(example);
    }
    public MchtSettledApplyCustom selectMchtSettledApplyCustomByPrimaryKey(Integer id){
    	return mchtSettledApplyCustomMapper.selectByPrimaryKey(id);
    }
    
	//更新数据
	public void updateMchtSettledApply(MchtSettledApply mchtSettledApply,String mchtSettledApplyPics) {
		//更新入驻信息数据
		mchtSettledApplyMapper.updateByPrimaryKeySelective(mchtSettledApply);
		//查询图片的缓存数据，并把标志位设置为1。因为用户会删除图片的操作
		if(mchtSettledApply.getId()!=null){
			MchtSettledApplyPicExample mchtSettledApplyPicExample=new MchtSettledApplyPicExample();
			mchtSettledApplyPicExample.createCriteria().andSettledApplyIdEqualTo(mchtSettledApply.getId()).andDelFlagEqualTo("0");
			MchtSettledApplyPic mchtSettledApplyPicUpdate=new MchtSettledApplyPic();
			mchtSettledApplyPicUpdate.setDelFlag("1");
			mchtSettledApplyPicMapper.updateByExampleSelective(mchtSettledApplyPicUpdate, mchtSettledApplyPicExample);
		}
		
		JSONArray mchtSettledApplyPicArray=JSONArray.fromObject(mchtSettledApplyPics);
		Iterator<JSONObject> it= mchtSettledApplyPicArray.iterator();
		while (it.hasNext()) {
			JSONObject mchtSettledApplyPic=it.next();
			
			MchtSettledApplyPicExample mchtSettledApplyPicExample=new MchtSettledApplyPicExample();
			mchtSettledApplyPicExample.createCriteria().andSettledApplyIdEqualTo(mchtSettledApply.getId()).andPicEqualTo(mchtSettledApplyPic.getString("picPath"));
			MchtSettledApplyPic mchtSettledApplyPicUpdate=new MchtSettledApplyPic();
			mchtSettledApplyPicUpdate.setDelFlag("0");
			int updateCount=mchtSettledApplyPicMapper.updateByExampleSelective(mchtSettledApplyPicUpdate, mchtSettledApplyPicExample);
			if(updateCount>0){
				continue;
			}
			MchtSettledApplyPic mchtSettledApplyPicTmp=new MchtSettledApplyPic();
			mchtSettledApplyPicTmp.setSettledApplyId(mchtSettledApply.getId());
			mchtSettledApplyPicTmp.setPic(mchtSettledApplyPic.getString("picPath"));
			mchtSettledApplyPicTmp.setType("1");
			mchtSettledApplyPicTmp.setCreateBy(mchtSettledApply.getUpdateBy());
			mchtSettledApplyPicMapper.insertSelective(mchtSettledApplyPicTmp);
		}
	}
	
	//插入数据
	public void insertMchtSettledApply(MchtSettledApply mchtSettledApply,String mchtSettledApplyPics){
		//插入品牌表
		mchtSettledApplyMapper.insertSelective(mchtSettledApply);
		//插入图片表
		JSONArray mchtSettledApplyPicArray=JSONArray.fromObject(mchtSettledApplyPics);
		Iterator<JSONObject> it= mchtSettledApplyPicArray.iterator();
		while (it.hasNext()) {
			JSONObject mchtSettledApplyPic=it.next();
			MchtSettledApplyPic mchtSettledApplyPicTmp=new MchtSettledApplyPic();
			mchtSettledApplyPicTmp.setSettledApplyId(mchtSettledApply.getId());
			mchtSettledApplyPicTmp.setPic(mchtSettledApplyPic.getString("picPath"));
			mchtSettledApplyPicTmp.setType("1");
			mchtSettledApplyPicTmp.setCreateBy(mchtSettledApply.getUpdateBy());
			mchtSettledApplyPicMapper.insertSelective(mchtSettledApplyPicTmp);
		}
	}

	public List<HashMap<String, Object>> getInfoConfirmBy() {
		return mchtSettledApplyCustomMapper.getInfoConfirmBy();
	}
	
	/*public void settledApplyUpdate(List<MchtSettledApply> MchtSettledApplyList) {
		for (MchtSettledApply mchtSettledApply : MchtSettledApplyList) {
			 this.insertSelective(mchtSettledApply);
		}	
	}*/
}
