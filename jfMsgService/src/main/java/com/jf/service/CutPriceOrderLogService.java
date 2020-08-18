package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceOrderLogMapper;
import com.jf.entity.CutPriceOrderLog;
import com.jf.entity.CutPriceOrderLogExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年8月24日 上午11:15:01 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CutPriceOrderLogService extends BaseService<CutPriceOrderLog, CutPriceOrderLogExample> {
	@Autowired
	private CutPriceOrderLogMapper cutPriceOrderLogMapper;

	@Autowired
	public void setCutPriceOrderLogMapper(CutPriceOrderLogMapper cutPriceOrderLogMapper) {
		this.setDao(cutPriceOrderLogMapper);
		this.cutPriceOrderLogMapper = cutPriceOrderLogMapper;
	}
	
	public void addCutPriceOrderLog(Integer memberId,Integer cutPriceOrderId,String status,String content){
		CutPriceOrderLog log = new CutPriceOrderLog();
		log.setCutPriceOrderId(cutPriceOrderId);
		log.setStatus(status);
		log.setContent(content);
		log.setCreateBy(memberId);
		log.setCreateDate(new Date());
		log.setDelFlag("0");
		insertSelective(log);
	}
}
