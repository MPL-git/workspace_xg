package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtInfoCustomMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MchtUserMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.Sms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtInfoService extends BaseService<MchtInfo,MchtInfoExample> {
	
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Autowired
	private MchtInfoMapper dao;
	
	@Resource
	private MchtContractService mchtContractService;
	
	@Resource
	private MchtUserMapper mchtUserMapper;
	
	@Resource
	private PlatformMsgMapper platformMsgMapper;
	
	@Resource
	private SmsService smsService;
	
	@Autowired
	private MchtInfoCustomMapper mchtInfoCustomMapper;

	@Autowired
	public void setMchtInfoMapper(MchtInfoMapper mchtInfoMapper) {
		super.setDao(mchtInfoMapper);
		this.dao = mchtInfoMapper;
	}
	
	public List<MchtInfoCustom> selectByCustomExample(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.selectByCustomExample(example);
	}
   
	/**
	 * 关店
	 */
	public void close(String staffId, int id, String remarks) {
		MchtInfo model = selectByPrimaryKey(id);
		model.setStatus(Const.MCHT_STATUS_CLOSED);
		model.setStatusDate(new Date());

		// 审核人、审核日期
		if(StrKit.notBlank(staffId)){
			model.setUpdateBy(Integer.valueOf(staffId));
		}
		model.setUpdateDate(new Date());
		model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
		updateByPrimaryKey(model);
	}

	/**
	 * 暂停业务
	 */
	public void stop(String staffId, int id, String remarks) {
		MchtInfo model = selectByPrimaryKey(id);
		model.setStatus(Const.MCHT_STATUS_STOP);
		model.setStatusDate(new Date());

		// 审核人、审核日期
		if(StrKit.notBlank(staffId)){
			model.setUpdateBy(Integer.valueOf(staffId));
		}
		model.setUpdateDate(new Date());
		model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
		updateByPrimaryKey(model);
	}

	/**
	 * 业务恢复正常
	 */
	public void statusNormal(String staffId, int id, String remarks) {
		MchtInfo model = selectByPrimaryKey(id);
		model.setStatus(Const.MCHT_STATUS_NORMAL);
		model.setStatusDate(new Date());

		// 审核人、审核日期
		if(StrKit.notBlank(staffId)){
			model.setUpdateBy(Integer.valueOf(staffId));
		}
		model.setUpdateDate(new Date());
		model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
		updateByPrimaryKey(model);
	}

	public MchtInfo findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public int countByExample(MchtInfoExample example) {
		return dao.countByExample(example);
	}

	public List<MchtInfo> list(QueryObject queryObject) {
		MchtInfoExample example = queryObject.getQuery("example");
		if(example == null){
			example = builderQuery(queryObject);
		}
		if(StrKit.notBlank(queryObject.getSortString())){
			example.setOrderByClause(queryObject.getSortString());
		}
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	private MchtInfoExample builderQuery(QueryObject queryObject) {
		MchtInfoExample example = new MchtInfoExample();
		MchtInfoExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusList = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusList);
		}
		if(queryObject.getQuery("mchtCode") != null){
			criteria.andMchtCodeEqualTo(queryObject.getQueryToStr("mchtCode"));
		}
		if(queryObject.getQuery("shortName") != null){
			criteria.andShortNameLike("%" + queryObject.getQueryToStr("shortName") + "%");
		}
		if(queryObject.getQuery("mchtType") != null){
			criteria.andMchtTypeEqualTo(queryObject.getQueryToStr("mchtType"));
		}
		if(queryObject.getQuery("agreementEndDateAfter") != null){
			criteria.andAgreementEndDateGreaterThan(queryObject.getQueryToDate("agreementEndDateAfter"));
		}
		if(queryObject.getQuery("agreementEndDateBefore") != null){
			criteria.andAgreementEndDateLessThan(queryObject.getQueryToDate("agreementEndDateBefore"));
		}
		if(queryObject.getQuery("cooperateBeginDateBefore") != null){
			criteria.andCooperateBeginDateLessThan(queryObject.getQueryToDate("cooperateBeginDateBefore"));
		}

		// 查找合同
		QueryObject contractQueryObject = new QueryObject();
		if(queryObject.getQuery("contractAuditStatus") != null){
			contractQueryObject.addQuery("auditStatus", queryObject.getQuery("contractAuditStatus"));
		}
		if(queryObject.getQuery("contractArchiveStatus") != null){
			contractQueryObject.addQuery("archiveStatus", queryObject.getQuery("contractArchiveStatus"));
		}
		if(queryObject.getQuery("contractArchiveStatusIn") != null){
			contractQueryObject.addQuery("archiveStatusIn", queryObject.getQuery("contractArchiveStatusIn"));
		}
		if(queryObject.getQuery("contractCode") != null){
			contractQueryObject.addQuery("contractCode", queryObject.getQuery("contractCode"));
		}
		if(queryObject.getQuery("contractType") != null){
			contractQueryObject.addQuery("contractType", queryObject.getQuery("contractType"));
		}
		if(queryObject.getQuery("contractMchtSendStatus") != null){
			contractQueryObject.addQuery("isMchtSend", queryObject.getQuery("contractMchtSendStatus"));
		}
		if(contractQueryObject.hasQuery()){
			List<Integer> mchtIdList = new ArrayList<Integer>();
			mchtIdList.add(0);
			List<MchtContract> list = mchtContractService.list(contractQueryObject);
			for(MchtContract mchtContract : list){
				mchtIdList.add(mchtContract.getMchtId());
			}
			criteria.andIdIn(mchtIdList);
		}


		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}



	public void sendMsg(MchtInfo mchtInfo) {
		//发送短信
		String mobile=null;
		MchtUserExample e = new MchtUserExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsPrimaryEqualTo("1").andStatusEqualTo("0");
		List<MchtUser> mchtUsers = mchtUserMapper.selectByExample(e);
		if(mchtUsers!=null && mchtUsers.size()>0){
			mobile = mchtUsers.get(0).getMobile();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isMobile(mobile)) {
			Sms sms = new Sms();
			sms.setMobile(mobile);
			sms.setSmsType("2");
			sms.setContent("您的营业执照将于{"+sdf.format(mchtInfo.getYearlyInspectionDate())+"}到期，到期后平台将暂停店铺运营");
			sms.setSendStatus("0");
			sms.setCreateDate(new Date());
			sms.setSendCount(0);
			sms.setUpdateDate(new Date());
			sms.setDelFlag("0");
			smsService.sendImmediately(sms);
		}else{
			logger.info("商家手机号不正确，无法发送短信");
		}
		//发送站内信 
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(mchtInfo.getId());
		platformMsg.setMsgType("TZ");//通知
		platformMsg.setTitle("关于营业执照即将到期的通知");
		String content = "您的营业执照将于{"+sdf.format(mchtInfo.getYearlyInspectionDate())+"}到期，到期后平台将暂停店铺运营。";
		platformMsg.setContent(content);
		platformMsg.setStatus("0");//未读
		platformMsg.setCreateDate(new Date());
		platformMsg.setDelFlag("0");
		platformMsgMapper.insertSelective(platformMsg);
	}
	
	
	//查找商家DRS
	public  List<MchtInfoCustom> findMchtDrs(List<Integer> list){
		return mchtInfoCustomMapper.findMchtDrs(list);
	}

}
