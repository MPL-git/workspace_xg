package com.jf.service;

import com.jf.biz.CombineOrderExtendBiz;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.CombineOrderExtendExample;
import com.jf.entity.TrackData;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CombineOrderExtendService extends CombineOrderExtendBiz {

	/**
	 * 保存推广相关信息
	 * 
	 * @param combineOrderId
	 * @param trackData
	 * @return: com.jf.entity.CombineOrderExtend
	 */
	public CombineOrderExtend saveTrackData(Integer combineOrderId, TrackData trackData) {
		CombineOrderExtend model = findByCombineOrderId(combineOrderId);
		if(model == null){
			model = new CombineOrderExtend();
			model.setCombineOrderId(combineOrderId);
		}

		model.setChannel(trackData.getChannel());
		model.setActivityname(trackData.getActivityname());
		model.setSpreadname(trackData.getSpreadname());
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			insertSelective(model);
		}
		return model;
	}

	/**
	 * 保存上报热云信息
	 *
	 * @param combineOrderId	combineOrderId
	 * @param trackingioCommitStatus trackingioCommitStatus
	 */
	public void saveTrackingio(Integer combineOrderId, String trackingioCommitStatus) {
		CombineOrderExtend model = findByCombineOrderId(combineOrderId);
		if(model == null){
			return;
		}

		model.setTrackingioCommitStatus(trackingioCommitStatus);
		model.setTrackingioCommitTime(new Date());
		//model.setUpdateDate(new Date());
		updateByPrimaryKeySelective(model);
	}

	public CombineOrderExtend findByCombineOrderId(int combineOrderId){
		CombineOrderExtendExample example = new CombineOrderExtendExample();
		CombineOrderExtendExample.Criteria criteria = example.createCriteria();
		criteria.andCombineOrderIdEqualTo(combineOrderId);
		example.setLimitStart(0);
		example.setLimitSize(1);
		List<CombineOrderExtend> list = selectByExample(example);
		if(list.size()>0)	return list.get(0);
		return null;
	}




}
