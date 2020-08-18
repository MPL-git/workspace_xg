package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtContractCustomMapper;
import com.jf.dao.MchtContractMapper;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractCustom;
import com.jf.entity.MchtContractExample;
import com.jf.entity.MchtInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtContractService extends BaseService<MchtContract, MchtContractExample> {

	@Resource
	private MchtContractMapper dao;

	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private MchtContractCustomMapper mchtContractCustomMapper;
	@Resource
	public void setMchtContractMapper(MchtContractMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}


	/**
	 * 不签约
     */
	public MchtContract archiveClose(int id, String renewRemarks) {
		MchtContract model = findById(id);
		model.setArchiveStatus(Const.MCHT_CONTRACT_ARCHIVE_STATUS_CLOSE);	//归档状态
		//model.setArchiveNo(param.getArchiveNo());	//归档编号
		//model.setArchiveBy(Integer.valueOf(currentUser.getStaffID()));	//归档人员
		model.setArchiveDate(new Date());	//归档时间
		model.setRemarks(model.getRemarks()==null?renewRemarks:(model.getRemarks()+";"+renewRemarks));
		//model.setUpdateBy(Integer.valueOf(staffId));
		return update(model);
	}

	/**
	 * 获取商家合同
	 */
	public MchtContract findByMchtId(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		queryObject.setLimitSize(1);
		List<MchtContract> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public MchtContract findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtContract save(MchtContract model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtContract update(MchtContract model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtContract model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtContract> list(QueryObject queryObject) {
		MchtContractExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtContract> paginate(QueryObject queryObject) {
		MchtContractExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtContract> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtContract>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtContractExample builderQuery(QueryObject queryObject) {
		MchtContractExample example = new MchtContractExample();
		MchtContractExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("lastContractId") != null){
			criteria.andLastContractIdEqualTo(queryObject.getQueryToInt("lastContractId"));
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("contractType") != null){
			criteria.andContractTypeEqualTo(queryObject.getQueryToStr("contractType"));
		}
		if(queryObject.getQuery("auditStatus") != null){
			criteria.andAuditStatusEqualTo(queryObject.getQueryToStr("auditStatus"));
		}
		if(queryObject.getQuery("archiveStatus") != null){
			criteria.andArchiveStatusEqualTo(queryObject.getQueryToStr("archiveStatus"));
		}
		if(queryObject.getQuery("archiveStatusIn") != null){
			List<String> list = queryObject.getQuery("archiveStatusIn");
			criteria.andArchiveStatusIn(list);
		}
		if(queryObject.getQuery("contractCode") != null){
			criteria.andContractCodeLike("%" + queryObject.getQueryToStr("contractCode") + "%");
		}
		if(queryObject.getQuery("archiveNo") != null){
			criteria.andArchiveNoLike("%" + queryObject.getQueryToStr("archiveNo") + "%");
		}
		if(queryObject.getQuery("isMchtSend") != null){
			criteria.andIsMchtSendEqualTo(queryObject.getQueryToStr("isMchtSend"));
		}
		if(queryObject.getQuery("isPlatformSend") != null){
			criteria.andIsPlatformSendEqualTo(queryObject.getQueryToStr("isPlatformSend"));
		}
		if(queryObject.getQuery("renewStatus") != null){
			criteria.andRenewStatusEqualTo(queryObject.getQueryToStr("renewStatus"));
		}

		if(queryObject.getQuery("beginDateAfter") != null){
			criteria.andBeginDateGreaterThan(queryObject.getQueryToDate("beginDateAfter"));
		}
		if(queryObject.getQuery("beginDateBefore") != null){
			criteria.andBeginDateLessThan(queryObject.getQueryToDate("beginDateBefore"));
		}
		if(queryObject.getQuery("endDateAfter") != null){
			criteria.andEndDateGreaterThan(queryObject.getQueryToDate("endDateAfter"));
		}
		if(queryObject.getQuery("endDateBefore") != null){
			criteria.andEndDateLessThan(queryObject.getQueryToDate("endDateBefore"));
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
		if(queryObject.getQuery("mchtInfoCooperateBeginDateBefore") != null){
			mchtInfoQueryObject.addQuery("cooperateBeginDateBefore", queryObject.getQuery("mchtInfoCooperateBeginDateBefore"));
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



	/**
	 * 生成合同编号
     */
	private String genContractCode(){
		return "MCC" + new Date().getTime() / 1000;
	}


	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月8日 上午9:19:14
	 */
	public List<MchtContractCustom> selectByCustomExample(
			MchtContractExample mchtContractExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByCustomExample(mchtContractExample);
	}
}
