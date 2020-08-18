package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.ext.core.CPI;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.JsonKit;
import com.jf.dao.MchtCloseApplyMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtCloseApplyCustomExample;
import com.jf.entity.MchtCloseApplyExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChangeLog;

@Service
@Transactional
public class MchtCloseApplyService extends BaseService<MchtCloseApply, MchtCloseApplyExample> {

	@Autowired
	private MchtCloseApplyMapper dao;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;

	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private PlatformContactService platformContactService;

	@Autowired
	public void setMchtCloseApplyMapper(MchtCloseApplyMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	/**
	 * 暂停日期1执行
     */
	public MchtCloseApply exeStopBegin(String staffId, int id) {
		MchtCloseApply model = findById(id);
		model.setStopBeginStatus(Const.MCHT_CLOSE_APPLY_EXE_STATUS_YES);
		model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		MchtInfo mchtInfo = mchtInfoService.findById(model.getMchtId());
		if(Const.MCHT_STATUS_NORMAL.equals(mchtInfo.getStatus())){
			mchtInfoService.stop(staffId, mchtInfo.getId(), "关店申请审批通过");
		}

		return model;
	}

	/**
	 * 根据商家ID获取关店申请
     */
	public MchtCloseApply findByMchtId(Integer mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		List<MchtCloseApply> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public MchtCloseApply findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtCloseApply save(MchtCloseApply model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtCloseApply updateMchtCloseApplyStatus(MchtCloseApply model, String requestType) {
		if(!StringUtil.isEmpty(requestType) && (Const.PLAT_CONTACT_TYPE_MERCHANTS.equals(requestType) || Const.PLAT_CONTACT_TYPE_OPERATION.equals(requestType))) {
			MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setCreateBy(model.getUpdateBy());
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setDelFlag("0");
			MchtInfo mchtInfo =  mchtInfoMapper.selectByPrimaryKey(model.getMchtId());
			if(mchtInfo != null) {
				mchtInfoChangeLog.setLogName(mchtInfo.getShopName());
			}
			if(Const.PLAT_CONTACT_TYPE_MERCHANTS.equals(requestType)) {
				mchtInfoChangeLog.setLogType("招商同意关店");
				mchtInfoChangeLog.setRemarks(model.getMerchantsAuditRemarks());
			}else if(Const.PLAT_CONTACT_TYPE_OPERATION.equals(requestType)) {
				mchtInfoChangeLog.setLogType("运营同意关店");
				mchtInfoChangeLog.setRemarks(model.getOperateAuditRemarks());
			}	
			mchtInfoChangeLog.setMchtId(model.getMchtId());
			mchtInfoChangeLog.setRemarks(model.getRequestRemarks());
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		}
		return update(model);
	}
	
	public MchtCloseApply update(MchtCloseApply model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtCloseApply model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtCloseApply> list(QueryObject queryObject) {
		MchtCloseApplyExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<Map<String,Object>> paginateCustom(QueryObject queryObject) {
		Page<MchtCloseApply> page = paginate(queryObject);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(MchtCloseApply info : page.getList()){
			list.add(fillInfo(info));
		}
		return new Page<Map<String, Object>>(list, page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow());
	}

	public Page<MchtCloseApply> paginate(QueryObject queryObject) {
		MchtCloseApplyCustomExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtCloseApply> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtCloseApply>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtCloseApplyCustomExample builderQuery(QueryObject queryObject) {
		MchtCloseApplyCustomExample example = new MchtCloseApplyCustomExample();
		MchtCloseApplyCustomExample.MchtCloseApplyCustomCriteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("operateAuditStatus") != null){
			criteria.andOperateAuditStatusEqualTo(queryObject.getQueryToStr("operateAuditStatus"));
		}
		if(queryObject.getQuery("merchantsAuditStatus") != null){
			criteria.andMerchantsAuditStatusEqualTo(queryObject.getQueryToStr("merchantsAuditStatus"));
		}

		if(queryObject.getQuery("mchtInfoStatus") != null){
			criteria.andMchtStatusByEqualTo(queryObject.getQuery("mchtInfoStatus").toString());
		}
		if(queryObject.getQuery("mchtInfoStatusIn") != null && !"".equals(queryObject.getQuery("mchtInfoStatusIn"))){
			List<Object> statusList = queryObject.getQuery("mchtInfoStatusIn");
			String statusIn = "";
			for (int i = 0; i < statusList.size(); i++) {
				if(i == (statusList.size()-1)) {
					statusIn += statusList.get(i).toString();
				}else {
					statusIn += statusList.get(i).toString() + ",";
				}
			}
			criteria.andMchtStatusIn(statusIn);
		}
		if(queryObject.getQuery("mchtCode") != null){
			criteria.andMchtCodeByEqualTo(queryObject.getQuery("mchtCode").toString());
		}
		if(queryObject.getQuery("mchtShortName") != null){
			criteria.andMchtNameLike(queryObject.getQuery("mchtShortName").toString());
		}
		if(queryObject.getQuery("mchtType") != null){
			criteria.andMchtTypeByEqualTo(queryObject.getQuery("mchtType").toString());
		}

		/*// 查找商家
		QueryObject mchtInfoQueryObject = new QueryObject();
		if(queryObject.getQuery("mchtInfoStatus") != null){
			mchtInfoQueryObject.addQuery("status", queryObject.getQuery("mchtInfoStatus"));
		}
		if(queryObject.getQuery("mchtInfoStatusIn") != null){
			mchtInfoQueryObject.addQuery("statusIn", queryObject.getQuery("mchtInfoStatusIn"));
		}
		if(queryObject.getQuery("mchtCode") != null){
			mchtInfoQueryObject.addQuery("mchtCode", queryObject.getQuery("mchtCode"));
		}
		if(queryObject.getQuery("mchtShortName") != null){
			mchtInfoQueryObject.addQuery("shortName", queryObject.getQuery("mchtShortName"));
		}
		if(queryObject.getQuery("mchtType") != null){
			mchtInfoQueryObject.addQuery("mchtType", queryObject.getQuery("mchtType"));
		}
		if(mchtInfoQueryObject.hasQuery()){
			List<Integer> mchtIdList = new ArrayList<Integer>();
			mchtIdList.add(0);
			List<MchtInfo> list = mchtInfoService.list(mchtInfoQueryObject);
			for(MchtInfo mchtInfo : list){
				mchtIdList.add(mchtInfo.getId());
			}
			criteria.andMchtIdIn(mchtIdList);
		}*/

		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public Map<String, Object> fillInfo(MchtCloseApply info) {
		info.put("requestTypeStr", Const.getMchtCloseApplyRequestTypeStr(info.getRequestType()));	//申请人类型
		info.put("merchantsAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(info.getMerchantsAuditStatus()));	//招商审核状态
		info.put("operateAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(info.getOperateAuditStatus()));	//运营审核状态
		info.put("requestRemarksStr", info.getRequestRemarks().length() < 50 ? info.getRequestRemarks() : info.getRequestRemarks().substring(0, 50) + "...");	//申请理由

		info.put("platformMerchantsContact", platformContactService.findByMchtId(info.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS));	//平台招商对接人
		info.put("platformOperateContact", platformContactService.findByMchtId(info.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION));	//平台运营对接人

		// 填充商家信息
		MchtInfo mchtInfo = mchtInfoService.findById(info.getMchtId());
		mchtInfo.put("mchtTypeStr", Const.getMchtTypeStr(mchtInfo.getMchtType()));	//合作模式
		mchtInfo.put("statusStr", Const.getMchtStatusStr(mchtInfo.getStatus()));	//合作状态
		mchtInfo.put("totalAuditStatusStr", Const.getMchtTotalAuditStatusStr(mchtInfo.getTotalAuditStatus()));	//总资质状态
		info.put("mchtInfo", mchtInfo);

		Map map = JsonKit.beanToMap(info);
		map.putAll(CPI.getAttrs(info));
		return map;
	}

}
