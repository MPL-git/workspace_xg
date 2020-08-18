package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.dao.MchtCloseApplyMapper;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtCloseApplyExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.PlatformContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtCloseApplyService extends BaseService<MchtCloseApply, MchtCloseApplyExample> {

	@Autowired
	private MchtCloseApplyMapper dao;

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
	 * 开店30天，合同未归档
	 * 自动生成关店申请
     */
	public MchtCloseApply autoCreate(int mchtId){
		MchtCloseApply model = new MchtCloseApply();
		model.setMchtId(mchtId);
		model.setStopBeginDate(new Date());
		//model.setStopBeginStatus(Const.FLAG_TRUE);
		model.setCloseFlag(Const.FLAG_TRUE);
		model.setCloseBeginDate(DateTimeUtil.plusDays(new Date(), 1));

		PlatformContact platformContact = platformContactService.findByMchtId(mchtId, Const.PLAT_CONTACT_TYPE_FAWU);
		if(platformContact != null){
			model.setRequestBy(platformContact.getStaffId());
			model.setRequestType(Const.PLAT_CONTACT_TYPE_FAWU);
			model.setRequestDate(new Date());
			model.setRequestRemarks("开店30天，合同未归档，申请关店");
		}

		model.setStatus(Const.MCHT_CLOSE_APPLY_STATUS_WAIT);
		return save(model);
	}

	/**
	 * 暂停开始执行
	 */
	public MchtCloseApply exeStopBegin(int id) {
		MchtCloseApply model = findById(id);
		model.setStopBeginStatus(Const.MCHT_CLOSE_APPLY_EXE_STATUS_YES);
		//model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		mchtInfoService.stop(null, model.getMchtId(), "【程序自动执行】关店申请暂停开始执行");

		return model;
	}

	/**
	 * 暂停结束执行
	 */
	public MchtCloseApply exeStopEnd(int id) {
		MchtCloseApply model = findById(id);
		model.setStopEndStatus(Const.MCHT_CLOSE_APPLY_EXE_STATUS_YES);
		//model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		mchtInfoService.statusNormal(null, model.getMchtId(), "【程序自动执行】关店申请暂停结束执行");

		return model;
	}

	/**
	 * 关店开始执行
	 */
	public MchtCloseApply exeCloseBegin(int id) {
		MchtCloseApply model = findById(id);
		model.setCloseBeginStatus(Const.MCHT_CLOSE_APPLY_EXE_STATUS_YES);
		//model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		mchtInfoService.close(null, model.getMchtId(), "【程序自动执行】关店开始执行");

		return model;
	}

	/**
	 * 关店结束执行
	 */
	public MchtCloseApply exeCloseEnd(int id) {
		MchtCloseApply model = findById(id);
		model.setCloseEndStatus(Const.MCHT_CLOSE_APPLY_EXE_STATUS_YES);
		//model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		mchtInfoService.statusNormal(null, model.getMchtId(), "【程序自动执行】关店结束执行");

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

	public Page<MchtCloseApply> paginate(QueryObject queryObject) {
		MchtCloseApplyExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtCloseApply> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtCloseApply>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtCloseApplyExample builderQuery(QueryObject queryObject) {
		MchtCloseApplyExample example = new MchtCloseApplyExample();
		MchtCloseApplyExample.Criteria criteria = example.createCriteria();
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
		if(queryObject.getQuery("stopBeginStatus") != null){
			criteria.andStopBeginStatusEqualTo(queryObject.getQueryToStr("stopBeginStatus"));
		}


		// 查找商家
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

		if(queryObject.getQuery("stopBeginDateAfter") != null){
			criteria.andStopBeginDateGreaterThanOrEqualTo(queryObject.getQueryToDate("stopBeginDateAfter"));
//			criteria.andStopBeginDateGreaterThan(queryObject.getQueryToDate("stopBeginDateAfter"));
		}
		if(queryObject.getQuery("stopBeginDateBefore") != null){
			criteria.andStopBeginDateLessThanOrEqualTo(queryObject.getQueryToDate("stopBeginDateBefore"));
//			criteria.andStopBeginDateLessThan(queryObject.getQueryToDate("stopBeginDateBefore"));
		}
		if(queryObject.getQuery("stopEndDateAfter") != null){
			criteria.andStopEndDateGreaterThanOrEqualTo(queryObject.getQueryToDate("stopEndDateAfter"));
//			criteria.andStopEndDateGreaterThan(queryObject.getQueryToDate("stopEndDateAfter"));
		}
		if(queryObject.getQuery("stopEndDateBefore") != null){
			criteria.andStopEndDateLessThanOrEqualTo(queryObject.getQueryToDate("stopEndDateBefore"));
//			criteria.andStopEndDateLessThan(queryObject.getQueryToDate("stopEndDateBefore"));
		}

		if(queryObject.getQuery("closeBeginDateAfter") != null){
			criteria.andCloseBeginDateGreaterThanOrEqualTo(queryObject.getQueryToDate("closeBeginDateAfter"));
//			criteria.andCloseBeginDateGreaterThan(queryObject.getQueryToDate("closeBeginDateAfter"));
		}
		if(queryObject.getQuery("closeBeginDateBefore") != null){
			criteria.andCloseBeginDateLessThanOrEqualTo(queryObject.getQueryToDate("closeBeginDateBefore"));
//			criteria.andCloseBeginDateLessThan(queryObject.getQueryToDate("closeBeginDateBefore"));
		}
		if(queryObject.getQuery("closeEndDateAfter") != null){
			criteria.andCloseEndDateGreaterThanOrEqualTo(queryObject.getQueryToDate("closeEndDateAfter"));
//			criteria.andCloseEndDateGreaterThan(queryObject.getQueryToDate("closeEndDateAfter"));
		}
		if(queryObject.getQuery("closeEndDateBefore") != null){
			criteria.andCloseEndDateLessThanOrEqualTo(queryObject.getQueryToDate("closeEndDateBefore"));
//			criteria.andCloseEndDateLessThan(queryObject.getQueryToDate("closeEndDateBefore"));
		}

		if(mchtInfoQueryObject.hasQuery()){
			List<Integer> mchtIdList = new ArrayList<Integer>();
			mchtIdList.add(0);
			List<MchtInfo> list = mchtInfoService.list(mchtInfoQueryObject);
			for(MchtInfo mchtInfo : list){
				mchtIdList.add(mchtInfo.getId());
			}
			criteria.andMchtIdIn(mchtIdList);
		}

		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
