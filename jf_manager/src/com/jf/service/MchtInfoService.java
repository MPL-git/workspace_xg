package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.ext.core.CPI;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.dao.MchtBlPicMapper;
import com.jf.dao.MchtContactMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtInfoCustomMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MchtPlatformContactMapper;
import com.jf.dao.MchtTaxInvoiceInfoMapper;
import com.jf.dao.MchtUserMapper;
import com.jf.dao.ShopStatusLogMapper;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;
import com.jf.entity.ShopStatusLog;
import com.jf.vo.MchtVo;

@Service
@Transactional
public class MchtInfoService extends BaseService<MchtInfo,MchtInfoExample> {
	
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Resource
	private MchtInfoCustomMapper mchtInfoCustomMapper;	
	
	@Autowired
	private MchtContactMapper mchtContactMapper;
	
	@Autowired
	private MchtUserMapper mchtUserMapper;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private MchtBlPicMapper mchtBlPicMapper;
	
	@Resource
	private MchtDepositMapper mchtDepositMapper;
	
	@Resource
	private MchtTaxInvoiceInfoMapper mchtTaxInvoiceInfoMapper;
	
	@Resource
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Resource
	private MchtPlatformContactMapper mchtPlatformContactMapper;

	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Resource
	private MchtContractService mchtContractService;
	
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private MchtInfoChangeLogService mchtInfoChangeLogService;
	
	@Resource
	private ShopStatusLogMapper shopStatusLogMapper;

	
	@Autowired
	public void setMchtInfoMapper(MchtInfoMapper mchtInfoMapper) {
		super.setDao(mchtInfoMapper);
		this.mchtInfoMapper = mchtInfoMapper;
	}
	
	public List<MchtInfoCustom> selectByExample(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.selectByExample(example);
	}
	
	public Integer countByExample(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.countByExample(example);
	}
	
	public List<?> selectMchtInfoList(HashMap<String, Object> paramMap)
	{
		List<?> list = mchtInfoCustomMapper.selectMchtInfoList(paramMap);
		return list;
	}
	
	public String queryMchtCount(HashMap<String, Object> paramMap){
		String tatalNum=mchtInfoCustomMapper.queryMchtCount(paramMap);
		return tatalNum;
	}
	
	public List<MchtInfoCustom> mchtInfoCustom(HashMap<String, Object> paramMap) {//商家统计
		return mchtInfoCustomMapper.mchtCoum(paramMap);
	}
	
	public List<MchtInfoCustom> totalmchtInfoCustom(HashMap<String, Object> paramMap) {
		return mchtInfoCustomMapper.totalmchtCoum(paramMap);
	}
			
	public List<Map<String, Object>> selectSettlementAmount(Map<String, Object> map) {
		return mchtInfoCustomMapper.selectSettlementAmount(map);
	}
	
	public Integer selectSettlementAmountCount(Map<String, Object> map) {
		return mchtInfoCustomMapper.selectSettlementAmountCount(map);
	}
	
	
	public List<MchtInfoCustom> totalmchtinfoCoum(MchtInfoCustomExample example){
    	return mchtInfoCustomMapper.totalmchtinfoCoum(example);
    }
	
	public List<Map<String, Object>> selectMchtInfoCustomExample(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.selectMchtInfoCustomExample(example);
	}
	
	public List<Map<String, Object>> selectMchtInfoShopCustomList(Map<String, Object> paramMap) {
		return mchtInfoCustomMapper.selectMchtInfoShopCustomList(paramMap);
	}
	
	public List<MchtInfoCustom> totalmchtInfoMonthlyCustom(HashMap<String, Object> paramMap) {
		return mchtInfoCustomMapper.totalmchtInfoMonthlyCustom(paramMap);
	}
	
	
	public List<MchtInfoCustom> mchtinfoMonthlyCoum(HashMap<String, Object> paramMap) {
		return mchtInfoCustomMapper.mchtinfoMonthlyCoum(paramMap);
	}
	
	public MchtInfo saveMcht(MchtVo mchtVo){
		
		//插入商家主表信息
		MchtInfo mchtInfo=mchtVo.getMchtInfo();
		String mchtCode = String.format("%04d", commonService.getSequence("mchtCode"));
		SimpleDateFormat sf=new SimpleDateFormat("yy");
		mchtCode=sf.format(new Date())+mchtCode;
		
		if("1".equals(mchtInfo.getMchtType())){
			mchtCode="P"+mchtCode;
		}
		if("2".equals(mchtInfo.getMchtType())){
			if(mchtInfo.getSettledType().equals("1")){
				mchtCode="P"+mchtCode;
			}else if(mchtInfo.getSettledType().equals("2")){
				mchtCode="G"+mchtCode;
			}
		}
		mchtInfo.setMchtCode(mchtCode);
		mchtInfoMapper.insertSelective(mchtInfo);
		
		//创建商家用户
		MchtUser mchtUser=mchtVo.getMchtUser();
		mchtUser.setMchtId(mchtInfo.getId());
		mchtUser.setCreateBy(mchtInfo.getCreateBy());
		mchtUser.setCreateDate(mchtInfo.getCreateDate());
		mchtUserMapper.insertSelective(mchtUser);
		
		//创建商家平台对接人
		MchtPlatformContact mchtPlatformContact=mchtVo.getMchtPlatformContact();
		mchtPlatformContact.setMchtId(mchtInfo.getId());
		mchtPlatformContact.setCreateBy(mchtInfo.getCreateBy());
		mchtPlatformContact.setCreateDate(mchtInfo.getCreateDate());
		mchtPlatformContactMapper.insertSelective(mchtPlatformContact);
		
		//创建商家保证金
        MchtDeposit mchtDeposit=new MchtDeposit();
        mchtDeposit.setCreateBy(mchtInfo.getCreateBy());
        mchtDeposit.setCreateDate(mchtInfo.getCreateDate());
        mchtDeposit.setDelFlag("0");
        mchtDeposit.setMchtId(mchtInfo.getId());
        mchtDeposit.setPayAmt(new BigDecimal(0));
        mchtDeposit.setTotalAmt(new BigDecimal(0));
        mchtDeposit.setUnpayAmt(new BigDecimal(0));
        mchtDepositMapper.insertSelective(mchtDeposit);
        
        //创建商家财务税务信息表
        MchtTaxInvoiceInfo mchtTaxInvoiceInfo=new MchtTaxInvoiceInfo();
        mchtTaxInvoiceInfo.setMchtId(mchtInfo.getId());
        mchtTaxInvoiceInfo.setAuditStatus("0");
        mchtTaxInvoiceInfo.setCreateBy(mchtInfo.getCreateBy());
        mchtTaxInvoiceInfo.setCreateDate(mchtInfo.getCreateDate());
        mchtTaxInvoiceInfoMapper.insertSelective(mchtTaxInvoiceInfo);
		
		return mchtVo.getMchtInfo();
	}
	
	
	public MchtInfoCustom selectMchtInfoCustomById(Integer id){
		return mchtInfoCustomMapper.selectMchtInfoCustomById(id);
	}
	
	
	public void updateMchtCompanyInfo(MchtInfo mchtInfo,String mchtBlPics) {
		
		
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		
		if(mchtInfo.getId()!=null){
			MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
			mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andDelFlagEqualTo("0");
			MchtBlPic mchtBlPic4Update=new MchtBlPic();
			mchtBlPic4Update.setDelFlag("1");
			mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
		}
		
		JSONArray brandTmkPicArray=JSONArray.fromObject(mchtBlPics);
		Iterator<JSONObject> it= brandTmkPicArray.iterator();
		while (it.hasNext()) {
			JSONObject mchtBlPicObject=it.next();
			
			MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
			mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andPicEqualTo(mchtBlPicObject.getString("picPath"));
			MchtBlPic mchtBlPic4Update=new MchtBlPic();
			mchtBlPic4Update.setDelFlag("0");
			int updateCount=mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
			if(updateCount>0){
				continue;
			}
			MchtBlPic mchtBlPic=new MchtBlPic();
			mchtBlPic.setMchtId(mchtInfo.getId());
			mchtBlPic.setPic(mchtBlPicObject.getString("picPath"));
			mchtBlPic.setCreateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setCreateDate(mchtInfo.getUpdateDate());
			mchtBlPic.setUpdateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setUpdateDate(mchtInfo.getUpdateDate());
			mchtBlPicMapper.insertSelective(mchtBlPic);
		}
		
	}
	
	
	public List<Map<String, Object>> selectMchtInfoAndPicCustomById(HashMap<String, Object> paramMap){
		return mchtInfoCustomMapper.selectMchtInfoAndPicCustomById(paramMap);
	}
	public int selectCountMchtInfoAndPicCustomById(HashMap<String, Object> paramMap){
		return mchtInfoCustomMapper.selectCountMchtInfoAndPicCustomById(paramMap);
	}
	
	public List<MchtInfoCustom> selectMchtInfoAllList(Map<String, Object> map){
		return mchtInfoCustomMapper.selectMchtInfoAllList(map);
	}
	
	public Integer selectMchtInfoListCount(Map<String, Object> map){
		return mchtInfoCustomMapper.selectMchtInfoListCount(map);
	}
	
	public void isCompanyInfPerfectChange(MchtInfo mchtInfo){
		
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		
	   //插入一条变更记录
			MchtInfoChangeLog mchtInfoChangeLog=new MchtInfoChangeLog();
			mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT", mchtInfo.getIsCompanyInfPerfect()));
			mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_INFO", "IS_COMPANY_INF_PERFECT", oldMchtInfo.getIsCompanyInfPerfect()));
			mchtInfoChangeLog.setCreateBy(mchtInfo.getUpdateBy());
			mchtInfoChangeLog.setCreateDate(new Date());
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setLogName(oldMchtInfo.getCompanyName());
			mchtInfoChangeLog.setLogType("公司信息");
			mchtInfoChangeLog.setMchtId(mchtInfo.getId());
			mchtInfoChangeLog.setRemarks(mchtInfo.getCompanyInfAuditRemarks());
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
	}
	
	public void changeShopNameAuditStatusCommit(MchtInfo mchtInfo){
		
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		
		MchtInfoChangeLog mchtInfoChangeLog=new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS", mchtInfo.getShopNameAuditStatus()));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_INFO", "SHOP_NAME_AUDIT_STATUS", oldMchtInfo.getIsCompanyInfPerfect()));
		mchtInfoChangeLog.setCreateBy(mchtInfo.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(oldMchtInfo.getShopName());
		mchtInfoChangeLog.setLogType("店铺名审核");
		mchtInfoChangeLog.setMchtId(mchtInfo.getId());
		mchtInfoChangeLog.setRemarks(mchtInfo.getShopNameAuditRemarks());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
	}

	/**
	 * 关店
	 */
	public void close(String staffId, int id, String remarks) {
		MchtInfo model = selectByPrimaryKey(id);
		model.setStatus(Const.MCHT_STATUS_CLOSED);
		model.setStatusDate(new Date());

		// 审核人、审核日期
		model.setUpdateBy(Integer.valueOf(staffId));
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
		model.setUpdateBy(Integer.valueOf(staffId));
		model.setUpdateDate(new Date());
		model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
		updateByPrimaryKey(model);
	}

	public Page<Map<String, Object>> paginateCustom(QueryObject queryObject) {
		Page<MchtInfo> page = paginate(queryObject);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(MchtInfo info : page.getList()){
			list.add(fillInfo(info));
		}
		return new Page<Map<String, Object>>(list, page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow());
	}


	public MchtInfo findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public MchtInfo save(MchtInfo model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public MchtInfo update(MchtInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		MchtInfo model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtInfo> list(QueryObject queryObject) {
		MchtInfoExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtInfo> paginate(QueryObject queryObject) {
		MchtInfoExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtInfo> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtInfo>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
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

		// 查找平台对接人
		if(queryObject.getQuery("platformContactIdList") != null){
			List<Integer> platformContactIdList = queryObject.getQuery("platformContactIdList");
			if(platformContactIdList.size() > 0){
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(0);
				List<MchtPlatformContact> list = mchtPlatformContactService.listByPlatformContactId(platformContactIdList);
				for(MchtPlatformContact mchtPlatformContact : list){
					idList.add(mchtPlatformContact.getMchtId());
				}
				criteria.andIdIn(idList);
			}

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

	public Map<String, Object> fillInfo(MchtInfo info) {
		info.put("mchtTypeStr", Const.getMchtTypeStr(info.getMchtType()));	//合作模式
		info.put("statusStr", Const.getMchtStatusStr(info.getStatus()));	//合作状态
		info.put("totalAuditStatusStr", Const.getMchtTotalAuditStatusStr(info.getTotalAuditStatus()));	//总资质状态

		info.put("mchtDeposit", mchtDepositService.findByMchtId(info.getId()));	// 保证金
		info.put("platformMerchantsContact", platformContactService.findByMchtId(info.getId(), Const.PLAT_CONTACT_TYPE_MERCHANTS));	//平台招商对接人
		info.put("platformFawuContact", platformContactService.findByMchtId(info.getId(), Const.PLAT_CONTACT_TYPE_FAWU));	//平台法务对接人

		// 合同
		MchtContract mchtContract =  mchtContractService.findByMchtId(info.getId());
		if(mchtContract != null){
			mchtContract.put("archiveStatusStr", Const.getMchtContractArchiveStatusStr(mchtContract.getArchiveStatus()));	//合同归档状态
			mchtContract.put("renewStatusStr", Const.getMchtContractRenewStatusStr(mchtContract.getRenewStatus()));	//合同续签状态
			mchtContract.put("isMchtSendStr", Const.getMchtContractSendStatusStr(mchtContract.getIsMchtSend()));	//合同商家寄件状态
			info.put("mchtContract", mchtContract);
		}

		Map map = JsonKit.beanToMap(info);
		map.putAll(CPI.getAttrs(info));
		return map;
	}
	
	public void totalAudit(MchtInfo mchtInfo,BigDecimal totalAmt){
		mchtInfo.setTotalAuditDate(new Date());
		
		MchtInfoCustom oldMchtInfoCustom = mchtInfoCustomMapper.selectMchtInfoCustomById(mchtInfo.getId());
		
		if(mchtInfo.getTotalAuditStatus().equals("3")){//驳回
			mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
			//发短信通知 商家（绑定手机号）和招商
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			
			MchtUserExample mchtUserExample = new MchtUserExample();
			mchtUserExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsPrimaryEqualTo("1");
			List<MchtUser> mchtUsers=mchtUserMapper.selectByExample(mchtUserExample);
			if(mchtUsers!=null&&mchtUsers.size()>0){
				reqData.put("mobile", mchtUsers.get(0).getMobile());
				reqData.put("content", oldMchtInfoCustom.getShopName()+"店，对不起，您提交的店铺资质审核未通过，请及时登录系统修改。");
				reqData.put("smsType", "2");
				param.put("reqData", reqData);
				JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result.getString("returnCode"))){
					logger.info("商家Id为："+mchtInfo.getId()+"资质总审核未通过，短信发送失败！！！！！");
				}
			}else{
				logger.info("商家Id为："+mchtInfo.getId()+"资质总审核未通过，未找到商家手机号，短信未发送！！！");
			}
			
		}
		if(mchtInfo.getTotalAuditStatus().equals("2")){//审核通过
			//如果保证金不为空则要设置应缴保证金
			if(totalAmt!=null){
				MchtDepositExample mchtDepositExample=new MchtDepositExample();
				mchtDepositExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId());
				List<MchtDeposit> mchtDeposits=mchtDepositMapper.selectByExample(mchtDepositExample);
				if(mchtDeposits==null||mchtDeposits.size()==0){
					//创建商家保证金
			        MchtDeposit mchtDeposit=new MchtDeposit();
			        mchtDeposit.setCreateBy(mchtInfo.getCreateBy());
			        mchtDeposit.setCreateDate(mchtInfo.getCreateDate());
			        mchtDeposit.setDelFlag("0");
			        mchtDeposit.setMchtId(mchtInfo.getId());
			        mchtDeposit.setPayAmt(new BigDecimal(0));
			        mchtDeposit.setTotalAmt(totalAmt);
			        mchtDeposit.setUnpayAmt(totalAmt);
			        mchtDepositMapper.insertSelective(mchtDeposit);
				}else{
					MchtDeposit mchtDeposit=mchtDeposits.get(0);
					mchtDeposit.setTotalAmt(totalAmt);
					mchtDeposit.setUnpayAmt(totalAmt.subtract(mchtDeposit.getPayAmt()));
					mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
				}
			}
			
			//如果合作状态=入驻中，合同开始日期同时更新到“合作开始日期”
//			if(oldMchtInfoCustom.getStatus().equals("0")){
//				mchtInfo.setCooperateBeginDate(mchtInfo.getAgreementBeginDate());
//			}
			mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
			
			//发短信通知 商家（绑定手机号）和招商
			JSONObject param=new JSONObject();
			//特定参数
			JSONObject reqData=new JSONObject();
			
			MchtUserExample mchtUserExample=new MchtUserExample();
			mchtUserExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsPrimaryEqualTo("1");
			List<MchtUser> mchtUsers=mchtUserMapper.selectByExample(mchtUserExample);
			if(mchtUsers!=null&&mchtUsers.size()>0){
				reqData.put("mobile", mchtUsers.get(0).getMobile());
				reqData.put("content", oldMchtInfoCustom.getShopName()+"店，恭喜您提交的店铺资质已审核通过，请及时登录系统处理入驻合同事宜。");
				reqData.put("smsType", "2");
				param.put("reqData", reqData);
				JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result.getString("returnCode"))){
					logger.info("商家Id为："+mchtInfo.getId()+"资质总审核通过，短信发送失败！！！！！");
				}
			}else{
				logger.info("商家Id为："+mchtInfo.getId()+"资质总审核通过，未找到商家手机号，短信未发送！！！");
			}
			//生成合同
			mchtContractService.createByMchtId(mchtInfo.getUpdateBy().toString(), mchtInfo.getId());
		}
		//插入一条变更记录
		MchtInfoChangeLog mchtInfoChangeLog=new MchtInfoChangeLog();
		mchtInfoChangeLog.setCreateBy(mchtInfo.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(oldMchtInfoCustom.getCompanyName());
		mchtInfoChangeLog.setLogType("资质总审核");
		mchtInfoChangeLog.setBeforeChange(oldMchtInfoCustom.getTotalAuditStatusDesc());
		if(mchtInfo.getTotalAuditStatus().equals("3")) {//驳回
			mchtInfoChangeLog.setAfterChange("驳回");
		}else if(mchtInfo.getTotalAuditStatus().equals("2")) {//审核通过
			mchtInfoChangeLog.setAfterChange("通过");
		}
		mchtInfoChangeLog.setRemarks(mchtInfo.getTotalAuditRemarks());
		mchtInfoChangeLog.setMchtId(mchtInfo.getId());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
	}

	public void save(MchtInfo mchtInfo, MchtInfoChangeLog mchtInfoChangeLog) {
		this.updateByPrimaryKeySelective(mchtInfo);
		mchtInfoChangeLogService.insertSelective(mchtInfoChangeLog);
	}
	
	
	/**
	 * 
	 * @Title selectMchtInfoAudit   
	 * @Description TODO(招商入驻进度)   
	 * @author pengl
	 * @date 2018年5月10日 下午1:29:41
	 */
	public List<Map<String, Object>> selectMchtInfoAudit(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.selectMchtInfoAudit(example);
	}
	
	public int countMchtInfoAudit(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.countMchtInfoAudit(example);
	}

	public List<HashMap<String, Object>> selectMchtShopManagerList(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.selectMchtShopManagerList(example);
	}
	
	public Integer countMchtShopManagerList(MchtInfoCustomExample example) {
		return mchtInfoCustomMapper.countMchtShopManagerList(example);
	}

	public List<HashMap<String, Object>> getMchtInfoByMchtCode(String mchtCode) {
		return mchtInfoCustomMapper.getMchtInfoByMchtCode(mchtCode);
	}

	public void update(MchtInfo mchtInfo, ShopStatusLog ssl) {
		this.updateByPrimaryKeySelective(mchtInfo);
		shopStatusLogMapper.insertSelective(ssl);
	}
	
	/**
	 * 
	 * @Title selectMchtInfoCustomList   
	 * @Description TODO(店铺优化列表)   
	 * @author pengl
	 * @date 2018年10月18日 下午3:34:42
	 */
	public List<Map<String, Object>> selectMchtInfoCustomList(Map<String, Object> paramMap) {
		return mchtInfoCustomMapper.selectMchtInfoCustomList(paramMap);
	}
	public Integer selectMchtInfoCustomCount(Map<String, Object> paramMap) {
		return mchtInfoCustomMapper.selectMchtInfoCustomCount(paramMap);
	}
	
	/**
	 * 
	 * @Title selectExpressNames   
	 * @Description TODO(该商家的100天以内订单，且有快递名称的，这些快递有哪些？调取数量多的前三个快递名称（多的在前面），快递名称之间由顿号隔开)   
	 * @author pengl
	 * @date 2018年10月18日 下午8:14:27
	 */
	public String selectExpressNames(Map<String, Object> paramMap) {
		return mchtInfoCustomMapper.selectExpressNames(paramMap);
	}
	
	/**
	 * 通过商家序号查找
	 * 
	 * @param mchtCode
	 * @return
	 */
	public Map<String, Object> selectByMchtCode(String mchtCode) {
		return mchtInfoCustomMapper.selectByMchtCode(mchtCode);
	}
	
	/**
	 * 通过商家id查找商家开通活动信息
	 * 
	 * @param mchtId
	 * @return
	 */
	public Map<String, Object> shopActivityInfo(Integer mchtId) {
		return mchtInfoCustomMapper.shopActivityInfo(mchtId);
	}

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月22日 下午1:46:39
	 */
	public Integer countByCustomExample(
			MchtInfoCustomExample mchtInfoCustomExample) {
		// TODO Auto-generated method stub
		return mchtInfoCustomMapper.countByCustomExample(mchtInfoCustomExample);
	}

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月22日 下午1:46:43
	 */
	public List<MchtInfoCustom> selectByCustomExample(
			MchtInfoCustomExample mchtInfoCustomExample) {
		// TODO Auto-generated method stub
		return mchtInfoCustomMapper.selectByCustomExample(mchtInfoCustomExample);
	}

	/**
	 * @MethodName selectZSRenewalDetailByExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月1日 下午2:42:27
	 */
	public Map<String, Object> selectZSRenewalDetailByExample(
			MchtInfoExample mchtInfoExample) {
		// TODO Auto-generated method stub
		return mchtInfoCustomMapper.selectZSRenewalDetailByExample(mchtInfoExample);
	}

	/**
	 * @MethodName getShopFlowReportData
	 * @Description TODO
	 * @author YRD
	 * @date 2019年12月20日 下午2:42:27
	 */
	public List<MchtInfoCustom> getShopFlowReportData(Map<String,Object> map){
		return	mchtInfoCustomMapper.getShopFlowReportData(map);
	}



	/**
	 * 根据传入搜索栏的参数查询对应的法务商品(菜单:商品管理->法务商品抽查)
	 * @param map
	 * @return
	 * @date 2019年11月22日 下午4:00
	 */
	public List<Map<String, Object>> selectLegalSpotCheck(Map<String, Object> map){
		return mchtInfoCustomMapper.selectLegalSpotCheck(map);
	}
}
