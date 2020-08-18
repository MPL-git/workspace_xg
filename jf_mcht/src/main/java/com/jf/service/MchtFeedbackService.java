package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtFeedbackMapper;
import com.jf.dao.MchtFeedbackPicMapper;
import com.jf.entity.MchtFeedback;
import com.jf.entity.MchtFeedbackExample;
import com.jf.entity.MchtFeedbackPic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtFeedbackService extends BaseService<MchtFeedback, MchtFeedbackExample> {

	@Autowired
	private MchtFeedbackMapper mchtFeedbackMapper;
	
	@Autowired
	private MchtFeedbackPicMapper mchtFeedbackPicMapper;
	
	@Autowired
	public void setMchtFeedbackMapper(MchtFeedbackMapper mchtFeedbackMapper) {
		super.setDao(mchtFeedbackMapper);
		this.mchtFeedbackMapper = mchtFeedbackMapper;
	}
	
	/**
	 * 
	 * @Title addMchtFeedbackAndPic   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月26日 下午2:29:06
	 */
	public void addMchtFeedbackAndPic(MchtFeedback mchtFeedback, JSONArray mchtFeedbackPics) {
		mchtFeedbackMapper.insertSelective(mchtFeedback);
		if(mchtFeedbackPics != null) {
			for(int i = 0; i < mchtFeedbackPics.size(); i++) {
				JSONObject mchtFeedbackPicObject = mchtFeedbackPics.getJSONObject(i);
				MchtFeedbackPic mchtFeedbackPic = new MchtFeedbackPic();
				mchtFeedbackPic.setFeedbackId(mchtFeedback.getId());
				mchtFeedbackPic.setPic(mchtFeedbackPicObject.getString("pic"));
				mchtFeedbackPic.setCreateBy(mchtFeedback.getCreateBy());
				mchtFeedbackPic.setCreateDate(mchtFeedback.getCreateDate());
				mchtFeedbackPic.setDelFlag("0");
				mchtFeedbackPicMapper.insertSelective(mchtFeedbackPic);
			}
		}
		
	}
	
	
	
}
