package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.FullCutMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;
import com.jf.entity.MchtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FullCutService {

	@Autowired
	private FullCutMapper dao;

	@Autowired
	private MchtInfoMapper mchtInfoMapper;

	public FullCut save(int activityAreaId, MchtInfo currentInfo, boolean ladderFlag, boolean sumFlag, String rule){
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(currentInfo.getId());
		FullCut model = findByActivityAreaId(activityAreaId);
		if(model != null){
			model.setLadderFlag(ladderFlag ? "1" : "0");
			model.setSumFlag(sumFlag ? "1" : "0");
			model.setRule(rule.trim());
			return update(model);
		}

		model = new FullCut();
		model.setLadderFlag(ladderFlag ? "1" : "0");
		model.setSumFlag(sumFlag ? "1" : "0");
		model.setRule(rule.trim());

		model.setActivityAreaId(activityAreaId);
		model.setRang(Const.PREFERENTIAL_RANG_BRAND);
		// 自营spop的belong归属于平台，其它都归于商家
		if("1".equals(mchtInfo.getIsManageSelf())){
			model.setBelong(Const.PREFERENTIAL_BELONG_PLAT);
		}else{
			model.setBelong(Const.PREFERENTIAL_BELONG_MCHT);
		}

		return save(model);
	}


	public FullCut findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public FullCut findByActivityAreaId(int activityAreaId){
		QueryObject queryObject = new QueryObject(1,1);
		queryObject.addQuery("activityAreaId", activityAreaId);
		List<FullCut> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public FullCut save(FullCut model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public FullCut update(FullCut model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		FullCut model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public List<FullCut> list(QueryObject queryObject) {

		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<FullCut> paginate(QueryObject queryObject) {
		FullCutExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<FullCut> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private FullCutExample builderQuery(QueryObject queryObject) {
		FullCutExample example = new FullCutExample();
		FullCutExample.Criteria criteria = example.createCriteria();

		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("activityAreaId") != null){
			criteria.andActivityAreaIdEqualTo(queryObject.getQueryToInt("activityAreaId"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
