package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.PDFCreaterIndividualContract;
import com.jf.bean.PDFCreaterMchtContract;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.ext.core.CPI;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class MchtContractService extends BaseService<MchtContract, MchtContractExample> {

	private static final Log logger = LogFactory.getLog(MchtContractService.class);

	@Autowired
	private MchtContractMapper dao;
	@Autowired
	private MchtContractCustomMapper mchtContractCustomMapper;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	private MchtUserMapper mchtUserMapper;
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	@Resource
	private PlatformContactService platformContactService;
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private MchtContractService mchtContractService;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	private ExpressMapper expressMapper;
   
	@Resource
	private ExpressService expressService;
	
	@Resource
	private MchtContractPicService mchtContractPicService;
	
	@Resource
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;
	
	@Resource
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	
	@Resource
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Resource
	private MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper;
	
	@Resource
	private MchtBrandOtherPicMapper mchtBrandOtherPicMapper;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private MchtBlPicService mchtBlPicService;
	
	@Resource
	private MchtInfoChgService mchtInfoChgService;
	
	@Resource
	private MchtBlPicChgService mchtBlPicChgService;

	@Resource
	private ContractRenewLogMapper contractRenewLogMapper;
	@Autowired
	public void setMchtContractMapper(MchtContractMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	/**
	 * 生成商家合同
	 */
	public MchtContract createByMchtId(String staffId, int mchtId) {
		MchtInfo mchtInfo = mchtInfoService.findById(mchtId);

		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		queryObject.setLimitSize(1);
		List<MchtContract> list = list(queryObject);
		MchtContract model = list.size() > 0 ? list.get(0) : null;
		if(model == null){
			model = new MchtContract();
			model.setMchtId(mchtId);
			model.setContractCode(genContractCode(mchtInfo.getId()));	//合同编号
			model.setBeginDate(mchtInfo.getAgreementBeginDate());
			model.setEndDate(mchtInfo.getAgreementEndDate());

			model.setContractType(Const.MCHT_CONTRACT_TYPE_NEW);	//新签合同
			model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);
			model.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
			model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
			model.setCreateBy(Integer.valueOf(staffId));
			save(model);
		}

		//生成PDF
		createPDF(model.getId());

		return model;
	}



	/**
	 * 审核通过
	 */
	public MchtContract auditPass(String staffId, int id, String remarks) {
		MchtContract model = findById(id);
		model.setAuditDate(new Date());
		model.setUpdateBy(Integer.valueOf(staffId));
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "AUDIT_STATUS", Const.MCHT_CONTRACT_AUDIT_STATUS_PASS));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "AUDIT_STATUS", model.getAuditStatus()));
		mchtInfoChangeLog.setCreateBy(Integer.valueOf(staffId));
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(model.getContractCode());
		mchtInfoChangeLog.setLogType("线上合同状态");
		mchtInfoChangeLog.setMchtId(model.getMchtId());
		mchtInfoChangeLog.setRemarks(remarks);
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_PASS);
		if(StrKit.notBlank(remarks)){
			//model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
			model.setRemarks(remarks);
		}

		//生成PDF
		createPDF(model.getId());

		//发短信通知商家
		JSONObject param=new JSONObject();
		JSONObject reqData=new JSONObject();
		MchtInfo mchtInfo = mchtInfoService.findById(model.getMchtId());
		MchtUserExample mchtUserExample=new MchtUserExample();
		mchtUserExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsPrimaryEqualTo("1");
		List<MchtUser> mchtUsers=mchtUserMapper.selectByExample(mchtUserExample);
		if(mchtUsers!=null&&mchtUsers.size()>0){
			if(!StringUtils.isEmpty(mchtUsers.get(0).getMobile())){
				reqData.put("mobile", mchtUsers.get(0).getMobile());
				reqData.put("content", "您提交的合同初审通过，请及时登录平台，按平台要求将入驻资料及合同寄往平台");
				reqData.put("smsType", "2");
				param.put("reqData", reqData);
				JSONObject result= JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result.getString("returnCode"))){
//				if (true){
					logger.info("商家合同审核通过，短信发送失败！！！！！");
				}
			}else{
				logger.info("商家合同审核通过，未找到商家手机号，短信未发送！！！");
			}
		}else{
			logger.info("商家合同审核通过，未找到商家手机号，短信未发送！！！");
		}

		return update(model);
	}

	
	/**
	 * 续签审核通过
	 */
	public MchtContract renewAuditPass(String staffId, int id, String remarks) {
		MchtContract model = findById(id);
		model.setAuditDate(new Date());
		model.setUpdateBy(Integer.valueOf(staffId));
		
		//插入记录表
		ContractRenewLog contractRenewLog = new ContractRenewLog();
		contractRenewLog.setContractId(id);
		contractRenewLog.setOperator(Integer.valueOf(staffId));
		contractRenewLog.setOperatorType("1");
		contractRenewLog.setStatus("8");
		contractRenewLog.setStatusDate(new Date());
		contractRenewLog.setContent(remarks);
		contractRenewLog.setCreateBy(Integer.valueOf(staffId));
		contractRenewLog.setCreateDate(new Date());
		contractRenewLog.setUpdateBy(Integer.valueOf(staffId));
		contractRenewLog.setUpdateDate(new Date());
		contractRenewLog.setDelFlag("0");
		contractRenewLogMapper.insertSelective(contractRenewLog);
		
		model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_PASS);
		if(StrKit.notBlank(remarks)){
			//model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
			model.setAuditRemarks(remarks);
			model.setRemarks("");
		}
		return update(model);
	}
	
	/**
	 * 审核驳回
	 */
	public MchtContract auditReject(String staffId, int id, String remarks) {
		MchtContract model = findById(id);
		model.setAuditDate(new Date());
		model.setUpdateBy(Integer.valueOf(staffId));
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "AUDIT_STATUS", Const.MCHT_CONTRACT_AUDIT_STATUS_REJECT));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "AUDIT_STATUS", model.getAuditStatus()));
		mchtInfoChangeLog.setCreateBy(Integer.valueOf(staffId));
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(model.getContractCode());
		mchtInfoChangeLog.setLogType("线上合同状态");
		mchtInfoChangeLog.setMchtId(model.getMchtId());
		mchtInfoChangeLog.setRemarks(remarks);
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_REJECT);
		if(StrKit.notBlank(remarks)){
			//model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
			model.setRemarks(remarks);
		}

		//发短信通知商家
		JSONObject param=new JSONObject();
		JSONObject reqData=new JSONObject();
		MchtInfo mchtInfo = mchtInfoService.findById(model.getMchtId());
		MchtUserExample mchtUserExample=new MchtUserExample();
		mchtUserExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId()).andIsPrimaryEqualTo("1");
		List<MchtUser> mchtUsers=mchtUserMapper.selectByExample(mchtUserExample);
		if(mchtUsers!=null&&mchtUsers.size()>0){
			if(!StringUtils.isEmpty(mchtUsers.get(0).getMobile())){
				reqData.put("mobile", mchtUsers.get(0).getMobile());
				reqData.put("content", "您提交的合同初审驳回，请及时登录平台，按平台要求修改。");
				reqData.put("smsType", "2");
				param.put("reqData", reqData);
				JSONObject result= JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				if(!"0000".equals(result.getString("returnCode"))){
					logger.info("商家合同审核驳回，短信发送失败！！！！！");
				}
			}else{
				logger.info("商家合同审核驳回，未找到商家手机号，短信未发送！！！");
			}
		}else{
			logger.info("商家合同审核驳回，未找到商家手机号，短信未发送！！！");
		}

		return update(model);
	}

	/**
	 * 续签审核驳回
	 */
	public MchtContract renewAuditReject(String staffId, int id, String remarks) {
		MchtContract model = findById(id);
		model.setAuditDate(new Date());
		model.setUpdateBy(Integer.valueOf(staffId));
		
		//插入记录表
		ContractRenewLog contractRenewLog = new ContractRenewLog();
		contractRenewLog.setContractId(id);
		contractRenewLog.setOperator(Integer.valueOf(staffId));
		contractRenewLog.setOperatorType("1");
		contractRenewLog.setStatus("9");
		contractRenewLog.setStatusDate(new Date());
		contractRenewLog.setContent(remarks);
		contractRenewLog.setCreateBy(Integer.valueOf(staffId));
		contractRenewLog.setCreateDate(new Date());
		contractRenewLog.setUpdateBy(Integer.valueOf(staffId));
		contractRenewLog.setUpdateDate(new Date());
		contractRenewLog.setDelFlag("0");
		contractRenewLogMapper.insertSelective(contractRenewLog);
				
		model.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_PASS);
		if(StrKit.notBlank(remarks)){
			//model.setRemarks(model.getRemarks()==null?remarks:(model.getRemarks()+";"+remarks));
			model.setAuditRemarks(remarks);
			model.setRemarks("");
		}
		return update(model);
	}
	
	/**
	 * 续签
	 */
	public MchtContract renewYes(String staffId, int id, String renewRemarks, Date beginDate, Date endDate) {
		MchtContract model = findById(id);
		model.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_YES);
		model.setRenewRemarks(renewRemarks);
		model.setUpdateBy(Integer.valueOf(staffId));
		update(model);

		// 生成新合同
		MchtContract newContract = new MchtContract();
		newContract.setMchtId(model.getMchtId());
		newContract.setUploadDate(model.getUploadDate()); //上传时间
		newContract.setAuditStatus(model.getAuditStatus());	//线上审核状态
		newContract.setAuditDate(model.getAuditDate());	//线上审核时间
		newContract.setIsMchtSend("0");// 0 未寄出
		newContract.setMchtSendDate(model.getMchtSendDate());
		newContract.setMchtExpressId(model.getMchtExpressId());
		newContract.setMchtExpressNo(model.getMchtExpressNo());

		newContract.setLastContractId(model.getId());
		newContract.setContractType(Const.MCHT_CONTRACT_TYPE_RENEW);
		newContract.setBeginDate(beginDate);
		newContract.setEndDate(endDate);
		newContract.setContractCode(genContractCode(model.getMchtId()));	//合同编号

		//续签时不继承以下属性
//		newContract.setArchiveStatus(model.getArchiveStatus());
//		newContract.setArchiveDate(model.getArchiveDate());
//		newContract.setArchiveNo(model.getArchiveNo());
//		newContract.setArchiveBy(model.getArchiveBy());
//		newContract.setIsPlatformSend(model.getIsPlatformSend());
//		newContract.setPlatformSendDate(model.getPlatformSendDate());
//		newContract.setPlatformExpressId(model.getPlatformExpressId());
//		newContract.setPlatformExpressNo(model.getPlatformExpressNo());
//		newContract.setFilePath(model.getFilePath());

		newContract.setCreateBy(Integer.valueOf(staffId));
		save(newContract);

		//生成PDF
		createPDF(newContract.getId());

		return model;
	}

	/**
	 * 不再续签
     */
	public MchtContract renewNo(String staffId, int id, String renewRemarks) {
		MchtContract model = findById(id);
		model.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_NO);
		model.setRenewRemarks(renewRemarks);
		model.setUpdateBy(Integer.valueOf(staffId));
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

	/**
	 * 获取商家合同
	 */
	public MchtContract findByLastContractId(int lastContractId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("lastContractId", lastContractId);
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

	public MchtContract updateArchiveStatus(MchtContract model) {
		MchtContract mchtContract = dao.selectByPrimaryKey(model.getId());
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		if(!StringUtil.isEmpty(model.getArchiveStatus())) {
			mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "ARCHIVE_STATUS", model.getArchiveStatus()));
			mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_CONTRACT", "ARCHIVE_STATUS", mchtContract.getArchiveStatus()));
			mchtInfoChangeLog.setRemarks(model.getRemarks());
		}
		mchtInfoChangeLog.setCreateBy(model.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(mchtContract.getContractCode());
		mchtInfoChangeLog.setLogType("合同归档状态");
		mchtInfoChangeLog.setMchtId(mchtContract.getMchtId());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		return update(model);
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
	
	public List<MchtContract> listCustom(QueryObject queryObject) {
		List<MchtContract> list = list(queryObject);
		for(MchtContract info : list){
			fillInfo(info);
		}
		return list;
	}

	public Page<Map<String,Object>> paginateCustom(QueryObject queryObject) {
		Page<MchtContract> page = paginate(queryObject);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(MchtContract info : page.getList()){
			list.add(fillInfo(info));
		}
		return new Page<Map<String, Object>>(list, page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow());
	}

	public Page<MchtContract> paginate(QueryObject queryObject) {
		MchtContractCustomExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtContract> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtContract>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtContractCustomExample builderQuery(QueryObject queryObject) {
		MchtContractCustomExample example = new MchtContractCustomExample();
		MchtContractCustomExample.MchtContractCustomCriteria criteria = example.createCriteria();
		criteria.andMchtDelFlagEqualTo("0");
		
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
		if(queryObject.getQuery("platformExpressNo") != null){
			criteria.andPlatformExpressNoEqualTo(queryObject.getQueryToStr("platformExpressNo"));
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
		if(queryObject.getQuery("contractarchivedateBegin")!=null){
			criteria.andArchiveDateGreaterThanOrEqualTo(queryObject.getQueryToDate("contractarchivedateBegin"));	
		}
		if(queryObject.getQuery("contractarchivedateEnd")!=null){
			criteria.andArchiveDateLessThanOrEqualTo(queryObject.getQueryToDate("contractarchivedateEnd"));
		}
		if(queryObject.getQuery("mchtInfoArchiveStatus")!=null){
			criteria.andMchtArchiveStatusEqualTo(queryObject.getQueryToStr("mchtInfoArchiveStatus"));
		}
		if(queryObject.getQuery("archiveStatusNotEqual")!=null){
			criteria.andArchiveStatusNotEqualTo(queryObject.getQueryToStr("archiveStatusNotEqual"));
		}
		if(queryObject.getQuery("settledType")!=null){
			criteria.andSettledTypeEqualTo(queryObject.getQueryToStr("settledType"));
		}

		//线上合同审核是否自营
		if(queryObject.getQuery("isManageSelf")!=null){
			criteria.andIsManageSelfEqualTo(queryObject.getQueryToStr("isManageSelf"));
		}

		// 查找平台用户对接人
		if(queryObject.getQuery("platContactStaffId") != null){
			criteria.andPlatContactStaffIdEqualTo(queryObject.getQueryToInt("platContactStaffId"));
		}
		
		// 查找商品类目
		if(queryObject.getQuery("productTypeId") != null){
			criteria.andProductTypeIdTo(queryObject.getQueryToInt("productTypeId"));
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
				criteria.andMchtIdIn(idList);
			}

		}

		if(queryObject.getQuery("mchtInfoStatus") != null){
			criteria.andMchtStatusByEqualTo(queryObject.getQuery("mchtInfoStatus").toString());
		}
		if(queryObject.getQuery("notEqualMchtInfoStatus") != null){
			criteria.andMchtStatusNotEqualTo(queryObject.getQuery("notEqualMchtInfoStatus").toString());
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
		
		if(queryObject.getQuery("mchtDelFlag") != null){
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

	public Map<String, Object> fillInfo(MchtContract info) {
		info.put("auditStatusStr", Const.getMchtContractAuditStatusStr(info.getAuditStatus()));	//合同审核状态
		info.put("archiveStatusStr", Const.getMchtContractArchiveStatusStr(info.getArchiveStatus()));	//合同归档状态
		info.put("renewStatusStr", Const.getMchtContractRenewStatusStr(info.getRenewStatus()));	//合同续签状态
		info.put("isMchtSendStr", Const.getMchtContractSendStatusStr(info.getIsMchtSend()));	//合同商家寄件状态

		info.put("mchtDeposit", mchtDepositService.findByMchtId(info.getMchtId()));	// 保证金
		info.put("platformMerchantsContact", platformContactService.findByMchtId(info.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS));	//平台招商对接人
		info.put("platformFawuContact", platformContactService.findByMchtId(info.getMchtId(), Const.PLAT_CONTACT_TYPE_FAWU));	//平台法务对接人
		
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

	/**
	 * 生成合同编号
     */
	public String genContractCode(Integer mchtIId){
		MchtInfo mchtInfo=mchtInfoService.selectByPrimaryKey(mchtIId);
		String mchtCode=mchtInfo.getMchtCode();
		String contractCode="";
		MchtContractExample mchtContractExample=new MchtContractExample();
		mchtContractExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtInfo.getId());
		mchtContractExample.setOrderByClause("id desc");
		List<MchtContract> mchtContracts=mchtContractService.selectByExample(mchtContractExample);
		if(mchtContracts==null||mchtContracts.size()==0){
			contractCode=mchtCode+"_1";
		}else{
			MchtContract mchtContract=mchtContracts.get(0);
			String currentContractCode=mchtContract.getContractCode();
			Integer seqNo=Integer.valueOf(currentContractCode.substring(currentContractCode.lastIndexOf("_")+1));
			seqNo=seqNo+1;
			contractCode=mchtCode+"_"+seqNo;
		}
//		return "MCC" + new Date().getTime() / 1000;
		return  contractCode;
	}

	/**
	 * 生成PDF
     */
	public MchtContract createPDF(int id){
		MchtContract model = findById(id);
		// 生成PDF
		try {
			String filePath = PDFCreaterMchtContract.createPDF(model);
			model.setFilePath(filePath);
			update(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 生成个体PDF
	 */
	public MchtContract createIndividualPDF(int id){
		MchtContract model = findById(id);
		// 生成PDF
		try {
			String filePath = PDFCreaterIndividualContract.createPDF(model);
			model.setFilePath(filePath);
			update(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public static void main(String[] args) {
		String a="sfsfsfs20";
		System.out.println(a.lastIndexOf("_")+1);
		System.out.println(a.substring(a.lastIndexOf("_")+1));
	}
	
	public void rebuildMchtContract(Integer staffId, Integer mchtContractId, Integer mchtId) {
		// 逻辑删除合同
		MchtContractExample mchtContractExample = new MchtContractExample();
		mchtContractExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtContractId);
		MchtContract mchtContract = new MchtContract();
		mchtContract.setUpdateBy(staffId);
		mchtContract.setUpdateDate(new Date());
		mchtContract.setDelFlag("1");
		dao.updateByExampleSelective(mchtContract, mchtContractExample);
		
		// 修改商家资质总审核状态
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		mchtInfo.setUpdateBy(staffId);
		mchtInfo.setUpdateDate(new Date());
		mchtInfo.setTotalAuditStatus("0");
		mchtInfoService.updateByPrimaryKey(mchtInfo);
		
	}

	public List<HashMap<String, Object>> getMchtContacts(HashMap<String, Object> paramMap) {
		return mchtContractCustomMapper.getMchtContacts(paramMap);
	}
	
	public int sendSms(List<JSONObject> sendList) {
		int count = 0;
		for(JSONObject jo:sendList){
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(jo).toString()));
			if("0000".equals(result.getString("returnCode"))){
				count++;
			}else{
				break;
			}
		}
		return count;
	}

	public List<HashMap<String, Object>> countMchtContract(HashMap<String, Object> paramMap) {
		return mchtContractCustomMapper.countMchtContract(paramMap);
	}

	public List<HashMap<String, Object>> countArchiveStatusMchtContract(HashMap<String, Object> paramMap) {
		return mchtContractCustomMapper.countArchiveStatusMchtContract(paramMap);
	}

	public void contractPicUpload(MchtContract mchtContract, String contractPics, String isSpecial, String isNeedUpload) {
		if ("1".equals(isNeedUpload)){
		//先删除合同下的所有图片，再更新添加回来
		MchtContractPicExample mcpe = new MchtContractPicExample();
		mcpe.createCriteria().andDelFlagEqualTo("0").andContractIdEqualTo(mchtContract.getId());
		MchtContractPic mcp = new MchtContractPic();
		mcp.setDelFlag("1");
		mcp.setUpdateDate(new Date());
		mchtContractPicService.updateByExampleSelective(mcp, mcpe);
		JSONArray picsJa = JSONArray.fromObject(contractPics);
		Iterator<JSONObject> picIt = picsJa.iterator();
		while (picIt.hasNext()) {
			JSONObject picObject = picIt.next();
			MchtContractPicExample mchtContractPicExample = new MchtContractPicExample();
			mchtContractPicExample.createCriteria().andPicEqualTo(picObject.getString("picPath")).andContractIdEqualTo(mchtContract.getId());
			MchtContractPic mchtContractPicUpdate = new MchtContractPic();
			mchtContractPicUpdate.setDelFlag("0");
			int updateCount = mchtContractPicService.updateByExampleSelective(mchtContractPicUpdate, mchtContractPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtContractPic mchtContractPic = new MchtContractPic();
			mchtContractPic.setMchtId(mchtContract.getMchtId());
			mchtContractPic.setContractId(mchtContract.getId());
			mchtContractPic.setPic(picObject.getString("picPath"));
			mchtContractPic.setCreateDate(new Date());
			mchtContractPic.setUpdateDate(new Date());
			mchtContractPic.setDelFlag("0");
			mchtContractPicService.insertSelective(mchtContractPic);
		}
		}
		//合同提交审核
		if(StringUtils.isEmpty(isSpecial)){
			mchtContract.setIsNeedUpload(isNeedUpload);
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_WAIT);
			mchtContract.setUploadDate(new Date());
			this.updateByPrimaryKeySelective(mchtContract);
		}
	}

	public void mchtContractPicsArchive(Integer mchtId,Integer mchtProductBrandId,Integer id, Integer staffID,String picType) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		int totalCount=3;
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mchtBlPicExample);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			totalCount+=mchtBlPics.size();
		}
		if(!StringUtils.isEmpty(mchtInfo.getTrcPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getOccPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getAtqPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("7")){
			MchtBrandAptitudePicExample mbapce = new MchtBrandAptitudePicExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(id);
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setUpdateBy(staffID);
			mchtBrandAptitudePic.setUpdateDate(new Date());
			mchtBrandAptitudePic.setArchiveBy(staffID);
			mchtBrandAptitudePic.setArchiveStatus("1");//已归档
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("8")){
			MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(id);
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setUpdateBy(staffID);
			mchtPlatformAuthPic.setUpdateDate(new Date());
			mchtPlatformAuthPic.setArchiveBy(staffID);
			mchtPlatformAuthPic.setArchiveStatus("1");//已归档
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic, mpapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("9")){
			MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(id);
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setUpdateBy(staffID);
			mchtBrandInvoicePic.setUpdateDate(new Date());
			mchtBrandInvoicePic.setArchiveBy(staffID);
			mchtBrandInvoicePic.setArchiveStatus("1");//已归档
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("10")){
			MchtBrandInspectionPicExample mbipce = new MchtBrandInspectionPicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(id);
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setUpdateBy(staffID);
			mchtBrandInspectionPic.setUpdateDate(new Date());
			mchtBrandInspectionPic.setArchiveBy(staffID);
			mchtBrandInspectionPic.setArchiveStatus("1");//已归档
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("11")){
			MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(id);
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setUpdateBy(staffID);
			mchtBrandOtherPic.setUpdateDate(new Date());
			mchtBrandOtherPic.setArchiveBy(staffID);
			mchtBrandOtherPic.setArchiveStatus("1");//已归档
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic,mbopce);
		}
		//TODO 判断是不是全部已齐全了
		Integer count = mchtProductBrandService.updateMchtProductBrandArchiveStatus(mchtProductBrandId, staffID);
		if(count!=0){
			MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mchtProductBrandId);
			mchtProductBrand.setArchiveStatus("2");//未齐全
			mchtProductBrand.setUpdateBy(staffID);
			mchtProductBrand.setUpdateDate(new Date());
			mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			mchtInfo.setArchiveStatus("0");//未归档
			mchtInfo.setUpdateBy(staffID);
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		}else{
			int mchtCount=0;
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg1ArchiveStatus()) && mchtInfo.getIdcardImg1ArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg2ArchiveStatus()) && mchtInfo.getIdcardImg2ArchiveStatus().equals("1")){
				mchtCount++;
			}
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andBlPicArchiveStatusEqualTo("1");
			List<MchtBlPic> mchtBlPicList = mchtBlPicService.selectByExample(mbpe);
			if(mchtBlPicList!=null && mchtBlPicList.size()>0){
				mchtCount+=mchtBlPicList.size();
			}
			if(!StringUtils.isEmpty(mchtInfo.getOccPicArchiveStatus()) && mchtInfo.getOccPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getTrcPicArchiveStatus()) && mchtInfo.getTrcPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getAtqPicArchiveStatus()) && mchtInfo.getAtqPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getBoaalPicArchiveStatus()) && mchtInfo.getBoaalPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(mchtCount == totalCount){
				mchtInfo.setArchiveStatus("1");//已归档
				mchtInfo.setUpdateBy(staffID);
				mchtInfo.setUpdateDate(new Date());
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}else{
				mchtInfo.setArchiveStatus("0");//未归档
				mchtInfo.setUpdateBy(staffID);
				mchtInfo.setUpdateDate(new Date());
				mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			}
		}
	}

	public void picArchive(Integer mchtId,Integer id, Integer staffID, String picType,String archiveStatus) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		int totalCount=3;
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mchtBlPicExample);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			totalCount+=mchtBlPics.size();
		}
		if(!StringUtils.isEmpty(mchtInfo.getTrcPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getOccPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getAtqPic())){
			totalCount++;
		}
		
		if(picType.equals("1")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setIdcardImg1ArchiveStatus(archiveStatus);
		}else if(picType.equals("2")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setIdcardImg2ArchiveStatus(archiveStatus);
		}else if(picType.equals("3")){
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBlPic mbp = new MchtBlPic();
			mbp.setUpdateDate(new Date());
			mbp.setBlPicArchiveStatus(archiveStatus);
			mchtBlPicService.updateByExampleSelective(mbp, mbpe);
		}else if(picType.equals("4")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setOccPicArchiveStatus(archiveStatus);
		}else if(picType.equals("5")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setAtqPicArchiveStatus(archiveStatus);
		}else if(picType.equals("6")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setBoaalPicArchiveStatus(archiveStatus);
		}else if(picType.equals("7")){
			MchtBrandAptitudePicExample mbapce = new MchtBrandAptitudePicExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setUpdateBy(staffID);
			mchtBrandAptitudePic.setUpdateDate(new Date());
			mchtBrandAptitudePic.setArchiveBy(staffID);
			mchtBrandAptitudePic.setArchiveStatus(archiveStatus);
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbapce);
			MchtBrandAptitudePic mbap = mchtBrandAptitudePicMapper.selectByPrimaryKey(id);
			if(archiveStatus.equals("0")){//未归档
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mbap.getMchtProductBrandId());
				mchtProductBrand.setArchiveStatus("2");//未齐
				mchtProductBrand.setUpdateBy(staffID);
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else{
				mchtProductBrandService.updateMchtProductBrandArchiveStatus(mbap.getMchtProductBrandId(), staffID);
			}
		}else if(picType.equals("8")){
			MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setUpdateBy(staffID);
			mchtPlatformAuthPic.setUpdateDate(new Date());
			mchtPlatformAuthPic.setArchiveBy(staffID);
			mchtPlatformAuthPic.setArchiveStatus(archiveStatus);
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic, mpapce);
			MchtPlatformAuthPic mpap = mchtPlatformAuthPicMapper.selectByPrimaryKey(id);
			if(archiveStatus.equals("0")){//未归档
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mpap.getMchtProductBrandId());
				mchtProductBrand.setArchiveStatus("2");//未齐
				mchtProductBrand.setUpdateBy(staffID);
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else{
				mchtProductBrandService.updateMchtProductBrandArchiveStatus(mpap.getMchtProductBrandId(), staffID);
			}
		}else if(picType.equals("9")){
			MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setUpdateBy(staffID);
			mchtBrandInvoicePic.setUpdateDate(new Date());
			mchtBrandInvoicePic.setArchiveBy(staffID);
			mchtBrandInvoicePic.setArchiveStatus(archiveStatus);
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic, mbipce);
			MchtBrandInvoicePic mbip = mchtBrandInvoicePicMapper.selectByPrimaryKey(id);
			if(archiveStatus.equals("0")){//未归档
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mbip.getMchtProductBrandId());
				mchtProductBrand.setArchiveStatus("2");//未齐
				mchtProductBrand.setUpdateBy(staffID);
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else{
				mchtProductBrandService.updateMchtProductBrandArchiveStatus(mbip.getMchtProductBrandId(), staffID);
			}
		}else if(picType.equals("10")){
			MchtBrandInspectionPicExample mbipce = new MchtBrandInspectionPicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setUpdateBy(staffID);
			mchtBrandInspectionPic.setUpdateDate(new Date());
			mchtBrandInspectionPic.setArchiveBy(staffID);
			mchtBrandInspectionPic.setArchiveStatus(archiveStatus);
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic, mbipce);
			MchtBrandInspectionPic mbip = mchtBrandInspectionPicMapper.selectByPrimaryKey(id);
			if(archiveStatus.equals("0")){//未归档
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mbip.getMchtProductBrandId());
				mchtProductBrand.setArchiveStatus("2");//未齐
				mchtProductBrand.setUpdateBy(staffID);
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else{
				mchtProductBrandService.updateMchtProductBrandArchiveStatus(mbip.getMchtProductBrandId(), staffID);
			}
		}else if(picType.equals("11")){
			MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setUpdateBy(staffID);
			mchtBrandOtherPic.setUpdateDate(new Date());
			mchtBrandOtherPic.setArchiveBy(staffID);
			mchtBrandOtherPic.setArchiveStatus(archiveStatus);
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic,mbopce);
			MchtBrandOtherPic mbop = mchtBrandOtherPicMapper.selectByPrimaryKey(id);
			if(archiveStatus.equals("0")){//未归档
				MchtProductBrand mchtProductBrand = mchtProductBrandService.selectByPrimaryKey(mbop.getMchtProductBrandId());
				mchtProductBrand.setArchiveStatus("2");//未齐
				mchtProductBrand.setUpdateBy(staffID);
				mchtProductBrand.setUpdateDate(new Date());
				mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
			}else{
				mchtProductBrandService.updateMchtProductBrandArchiveStatus(mbop.getMchtProductBrandId(), staffID);
			}
		}else if(picType.equals("12")){//税务登记证
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setTrcPicArchiveStatus(archiveStatus);
		}
		if(archiveStatus.equals("0")){//未归档
			mchtInfo.setArchiveStatus("0");
			mchtInfo.setUpdateBy(staffID);
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		}else{//已归档
			int mchtCount=0;
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg1ArchiveStatus()) && mchtInfo.getIdcardImg1ArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg2ArchiveStatus()) && mchtInfo.getIdcardImg2ArchiveStatus().equals("1")){
				mchtCount++;
			}
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andBlPicArchiveStatusEqualTo("1");
			List<MchtBlPic> mchtBlPicList = mchtBlPicService.selectByExample(mbpe);
			if(mchtBlPicList!=null && mchtBlPicList.size()>0){
				mchtCount+=mchtBlPicList.size();
			}
			if(!StringUtils.isEmpty(mchtInfo.getOccPicArchiveStatus()) && mchtInfo.getOccPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getTrcPicArchiveStatus()) && mchtInfo.getTrcPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getAtqPicArchiveStatus()) && mchtInfo.getAtqPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getBoaalPicArchiveStatus()) && mchtInfo.getBoaalPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(mchtCount == totalCount){
				MchtProductBrandExample mpbe = new MchtProductBrandExample();
				mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andStatusEqualTo("1");
				List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
				Integer total = 0; 
				for(MchtProductBrand mchtProductBrand:mchtProductBrands){
					Integer diff = mchtProductBrandService.updateMchtProductBrandArchiveStatus(mchtProductBrand.getId(), staffID);
					total+=diff;
				}
				if(total == 0){
					mchtInfo.setArchiveStatus("1");
				}else{
					mchtInfo.setArchiveStatus("0");
				}
			}else{
				mchtInfo.setArchiveStatus("0");
			}
		}
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
	}
	
	public int countCustomByExample(MchtContractCustomExample example) {
		return mchtContractCustomMapper.countByExample(example);
	}
	
	public List<MchtContractCustom> selectCustomByExample(MchtContractCustomExample example) {
		return mchtContractCustomMapper.selectByExample(example);
	}

	public void companyInfoPicArchive(Integer mchtId, Integer id,Integer staffID,String picType, String archiveStatus) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		int totalCount=3;
		MchtBlPicExample mchtBlPicExample = new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<MchtBlPic> mchtBlPics = mchtBlPicService.selectByExample(mchtBlPicExample);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			totalCount+=mchtBlPics.size();
		}
		if(!StringUtils.isEmpty(mchtInfo.getTrcPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getOccPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfo.getAtqPic())){
			totalCount++;
		}
		
		if(picType.equals("1")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setIdcardImg1ArchiveStatus(archiveStatus);
		}else if(picType.equals("2")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setIdcardImg2ArchiveStatus(archiveStatus);
		}else if(picType.equals("3")){
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
			MchtBlPic mbp = new MchtBlPic();
			mbp.setUpdateDate(new Date());
			mbp.setBlPicArchiveStatus(archiveStatus);
			mchtBlPicService.updateByExampleSelective(mbp, mbpe);
		}else if(picType.equals("4")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setOccPicArchiveStatus(archiveStatus);
		}else if(picType.equals("5")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setAtqPicArchiveStatus(archiveStatus);
		}else if(picType.equals("6")){
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setBoaalPicArchiveStatus(archiveStatus);
		}else if(picType.equals("12")){//税务登记证
			mchtInfo.setUpdateDate(new Date());
			mchtInfo.setTrcPicArchiveStatus(archiveStatus);
		}
		if(archiveStatus.equals("0")){//未归档
			mchtInfo.setCompanyInfoArchiveStatus("0");
			mchtInfo.setUpdateBy(staffID);
			mchtInfo.setUpdateDate(new Date());
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		}else{//已归档
			int mchtCount=0;
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg1ArchiveStatus()) && mchtInfo.getIdcardImg1ArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getIdcardImg2ArchiveStatus()) && mchtInfo.getIdcardImg2ArchiveStatus().equals("1")){
				mchtCount++;
			}
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId).andBlPicArchiveStatusEqualTo("1");
			List<MchtBlPic> mchtBlPicList = mchtBlPicService.selectByExample(mbpe);
			if(mchtBlPicList!=null && mchtBlPicList.size()>0){
				mchtCount+=mchtBlPicList.size();
			}
			if(!StringUtils.isEmpty(mchtInfo.getOccPicArchiveStatus()) && mchtInfo.getOccPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getTrcPicArchiveStatus()) && mchtInfo.getTrcPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getAtqPicArchiveStatus()) && mchtInfo.getAtqPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfo.getBoaalPicArchiveStatus()) && mchtInfo.getBoaalPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(mchtCount == totalCount){
				mchtInfo.setCompanyInfoArchiveStatus("1");
			}else{
				mchtInfo.setCompanyInfoArchiveStatus("0");
			}
			mchtInfo.setUpdateBy(staffID);
			mchtInfo.setUpdateDate(new Date());
		}
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
	}
	
	public void companyInfoPicArchiveChg(Integer mchtContractId, Integer id,Integer staffID,String picType, String archiveStatus) {
		MchtInfoChg mchtInfoChg = mchtInfoChgService.selectByPrimaryKey(mchtContractId);
		int totalCount=3;
		if(!StringUtils.isEmpty(mchtInfoChg.getBlPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfoChg.getTrcPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfoChg.getOccPic())){
			totalCount++;
		}
		if(!StringUtils.isEmpty(mchtInfoChg.getAtqPic())){
			totalCount++;
		}
		
		//更新到主表
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtInfoChg.getMchtId());
		if(picType.equals("1")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setIdcardImg1ArchiveStatus(archiveStatus);
			mchtInfo.setIdcardImg1ArchiveStatus("1");
		}else if(picType.equals("2")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setIdcardImg2ArchiveStatus(archiveStatus);
			mchtInfo.setIdcardImg2ArchiveStatus("1");
		}else if(picType.equals("3")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setBlPicArchiveStatus(archiveStatus);
			
			MchtBlPicExample mbpe = new MchtBlPicExample();
			mbpe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtInfoChg.getMchtId());
			MchtBlPic mbp = new MchtBlPic();
			mbp.setUpdateDate(new Date());
			mbp.setBlPicArchiveStatus(archiveStatus);
			mchtBlPicService.updateByExampleSelective(mbp, mbpe);

		}else if(picType.equals("4")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setOccPicArchiveStatus(archiveStatus);
			mchtInfo.setOccPicArchiveStatus("1");
		}else if(picType.equals("5")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setAtqPicArchiveStatus(archiveStatus);
			mchtInfo.setAtqPicArchiveStatus("1");
		}else if(picType.equals("6")){
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setBoaalPicArchiveStatus(archiveStatus);
			mchtInfo.setBoaalPicArchiveStatus("1");
		}else if(picType.equals("12")){//税务登记证
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChg.setTrcPicArchiveStatus(archiveStatus);
			mchtInfo.setTrcPicArchiveStatus("1");
		}
		
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		
		if(archiveStatus.equals("0")){//未归档
			mchtInfoChg.setCompanyInfoArchiveStatus("0");
			mchtInfoChg.setUpdateBy(staffID);
			mchtInfoChg.setUpdateDate(new Date());
			mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
		}else{//已归档
			int mchtCount=0;
			if(!StringUtils.isEmpty(mchtInfoChg.getIdcardImg1ArchiveStatus()) && mchtInfoChg.getIdcardImg1ArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getIdcardImg2ArchiveStatus()) && mchtInfoChg.getIdcardImg2ArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getBlPicArchiveStatus()) && mchtInfoChg.getBlPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getOccPicArchiveStatus()) && mchtInfoChg.getOccPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getTrcPicArchiveStatus()) && mchtInfoChg.getTrcPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getAtqPicArchiveStatus()) && mchtInfoChg.getAtqPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(!StringUtils.isEmpty(mchtInfoChg.getBoaalPicArchiveStatus()) && mchtInfoChg.getBoaalPicArchiveStatus().equals("1")){
				mchtCount++;
			}
			if(mchtCount == totalCount){
				mchtInfoChg.setCompanyInfoArchiveStatus("3");		
				mchtInfo.setCompanyInfAuditInnerRemarks("1");
			}else{
				mchtInfoChg.setCompanyInfoArchiveStatus("0");			
				mchtInfo.setCompanyInfAuditInnerRemarks("0");
			}
			mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
			mchtInfoChg.setUpdateBy(staffID);
			mchtInfoChg.setUpdateDate(new Date());
		}
		mchtInfoChgService.updateByPrimaryKeySelective(mchtInfoChg);
	}

	//平台合同回寄确认列表
	public List<PlatformContractReturnLis> contractReturnList(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.contractReturnList(map);
	}
	
	//平台合同回寄确认列表数量
	public int countByContractReturnList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByContractReturnList(map);
	}

	/**
	 * @MethodName countByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月31日 下午9:00:53
	 */
	public int countByExampleCustom(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByExampleCustom(mchtContractCustomExample);
	}

	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月31日 下午9:00:57
	 */
	public List<MchtContractCustom> selectByExampleCustom(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByExampleCustom(mchtContractCustomExample);
	}

	/**
	 * @MethodName audit
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月1日 下午8:06:02
	 */
	public Map<String, Object> audit(HttpServletRequest request,Integer staffId,MchtContract mchtContract) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		String renewProStatus= request.getParameter("renewProStatus");
		String zsNotRenewRemarks= request.getParameter("zsNotRenewRemarks");
		String zsRenewInnerRemarks= request.getParameter("zsRenewInnerRemarks");
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtContract.setRenewProStatus(renewProStatus);
			if(org.apache.commons.lang.StringUtils.equals("4", renewProStatus)){
				mchtContract.setZsNotRenewRemarks(zsNotRenewRemarks);
				mchtContract.setRenewStatus("3");//店铺暂停
			}
			mchtContract.setZsRenewInnerRemarks(zsRenewInnerRemarks);
			mchtContract.setUpdateBy(staffId);
			mchtContract.setUpdateDate(new Date());			
			mchtContractService.updateByPrimaryKeySelective(mchtContract);
			
			//插入记录表
			ContractRenewLog contractRenewLog = new ContractRenewLog();
			contractRenewLog.setContractId(mchtContract.getId());
			contractRenewLog.setOperator(staffId);
			contractRenewLog.setOperatorType("1");
			contractRenewLog.setStatus(renewProStatus);
			contractRenewLog.setStatusDate(new Date());
			contractRenewLog.setContent(zsRenewInnerRemarks);
			contractRenewLog.setCreateBy(staffId);
			contractRenewLog.setCreateDate(new Date());
			contractRenewLog.setUpdateBy(staffId);
			contractRenewLog.setUpdateDate(new Date());
			contractRenewLog.setDelFlag("0");
			contractRenewLogMapper.insertSelective(contractRenewLog);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * @MethodName fwAudit
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 下午3:27:45
	 */
	public Map<String, Object> fwAudit(HttpServletRequest request,
			Integer staffId, MchtContract mchtContract1) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap=new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String renewRemarks= request.getParameter("renewRemarks");
		String beginDate= request.getParameter("beginDate");
		String endDate= request.getParameter("endDate");
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			//原先的合同状态修改
			mchtContract1.setRenewProStatus("7");
			mchtContract1.setRenewStatus("1");
			mchtContract1.setUpdateBy(staffId);
			mchtContract1.setUpdateDate(new Date());
			mchtContract1.setRenewRemarks(renewRemarks);
			mchtContractService.updateByPrimaryKeySelective(mchtContract1);
			
			//生成新合同
			MchtContract mchtContract = new MchtContract();
			mchtContract.setMchtId(mchtContract1.getMchtId());
			mchtContract.setContractCode(mchtContractService.genContractCode(mchtContract1.getMchtId()));	//合同编号
			mchtContract.setBeginDate(format.parse(beginDate));
			mchtContract.setEndDate(format.parse(endDate));
			mchtContract.setRenewRemarks(renewRemarks);
			mchtContract.setLastContractId(mchtContract1.getId());
			mchtContract.setContractType(Const.MCHT_CONTRACT_TYPE_RENEW);	//续签
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);
			mchtContract.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
			mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
			mchtContract.setRenewProStatus(Const.MCHT_CONTRACT_RENEW_STATUS_ZEOR);//续签进度
			mchtContract.setCreateBy(Integer.valueOf(staffId));
			mchtContractService.save(mchtContract);
			//生成PDF
			mchtContractService.createPDF(mchtContract.getId());

			//插入记录表
			ContractRenewLog contractRenewLog = new ContractRenewLog();
			contractRenewLog.setContractId(mchtContract1.getId());
			contractRenewLog.setOperator(staffId);
			contractRenewLog.setOperatorType("1");
			contractRenewLog.setStatus("7");
			contractRenewLog.setStatusDate(new Date());
			contractRenewLog.setContent(renewRemarks);
			contractRenewLog.setCreateBy(staffId);
			contractRenewLog.setCreateDate(new Date());
			contractRenewLog.setUpdateBy(staffId);
			contractRenewLog.setUpdateDate(new Date());
			contractRenewLog.setDelFlag("0");
			contractRenewLogMapper.insertSelective(contractRenewLog);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName yyAudit
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月6日 上午9:25:27
	 */
	public Map<String, Object> yyAudit(HttpServletRequest request,
			Integer staffId, MchtContract mchtContract1) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap=new HashMap<String, Object>();
		String renewProStatus= request.getParameter("renewProStatus");
		String shopCloseReason= request.getParameter("shopCloseReason");
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			mchtContract1.setRenewProStatus(renewProStatus);
			if(org.apache.commons.lang.StringUtils.equals("5", renewProStatus)){
				mchtContract1.setShopCloseReason(shopCloseReason);
				mchtContract1.setRenewProStatus("5");
				
				//店铺暂停，需要上份合同的续签状态改为店铺暂停
				MchtContract mchtContract2 = mchtContractService.selectByPrimaryKey(mchtContract1.getLastContractId());
				if(mchtContract2 != null){
					mchtContract2.setRenewStatus("3");
					mchtContract2.setUpdateBy(staffId);
					mchtContract2.setUpdateDate(new Date());
					mchtContractService.updateByPrimaryKeySelective(mchtContract2);
				}		
			}
			
			mchtContract1.setUpdateBy(staffId);
			mchtContract1.setUpdateDate(new Date());
			mchtContractService.updateByPrimaryKeySelective(mchtContract1);
			
			//插入记录表
			ContractRenewLog contractRenewLog = new ContractRenewLog();
			contractRenewLog.setContractId(mchtContract1.getId());
			contractRenewLog.setOperator(staffId);
			contractRenewLog.setOperatorType("1");
			contractRenewLog.setStatus(renewProStatus);
			contractRenewLog.setStatusDate(new Date());
			contractRenewLog.setCreateBy(staffId);
			contractRenewLog.setCreateDate(new Date());
			contractRenewLog.setUpdateBy(staffId);
			contractRenewLog.setUpdateDate(new Date());
			contractRenewLog.setDelFlag("0");
			contractRenewLogMapper.insertSelective(contractRenewLog);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * @MethodName countByExampleCustomFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午9:49:54
	 */
	public int countByExampleCustomFW(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByExampleCustomFW(mchtContractCustomExample);
	}

	/**
	 * @MethodName selectByExampleCustomFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午9:50:01
	 */
	public List<MchtContractCustom> selectByExampleCustomFW(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return  mchtContractCustomMapper.selectByExampleCustomFW(mchtContractCustomExample);
	}

	/**
	 * @MethodName rebuildMchtContract
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月3日 上午10:01:52
	 */
	public void rebuildMchtContract(HttpServletRequest request,int staffId, MchtContract mchtContract1) {
		// TODO Auto-generated method stub
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String renewRemarks= request.getParameter("renewRemarks");
		String beginDate= request.getParameter("beginDate");
		String endDate= request.getParameter("endDate");
		// 逻辑删除合同
		mchtContract1.setUpdateBy(staffId);
		mchtContract1.setUpdateDate(new Date());
		mchtContract1.setDelFlag("1");
		dao.updateByPrimaryKeySelective(mchtContract1);
		
		//生成新续签合同
		MchtContract mchtContract = new MchtContract();
		mchtContract.setMchtId(mchtContract1.getMchtId());
		mchtContract.setContractCode(mchtContract1.getContractCode());	//合同编号
		mchtContract.setBeginDate(format.parse(beginDate));
		mchtContract.setEndDate(format.parse(endDate));
		mchtContract.setRenewRemarks(renewRemarks);
		mchtContract.setLastContractId(mchtContract1.getLastContractId());
		mchtContract.setContractType(Const.MCHT_CONTRACT_TYPE_RENEW);	//续签
		mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);
		mchtContract.setRenewStatus(Const.MCHT_CONTRACT_RENEW_STATUS_WAIT);
		mchtContract.setAuditStatus(Const.MCHT_CONTRACT_AUDIT_STATUS_NONE);	//审核状态：未上传
		mchtContract.setRenewProStatus(Const.MCHT_CONTRACT_RENEW_STATUS_ZEOR);//续签进度
		mchtContract.setCreateBy(Integer.valueOf(staffId));
		mchtContractService.save(mchtContract);
		//生成PDF
		mchtContractService.createPDF(mchtContract.getId());

		//插入记录表
		ContractRenewLog contractRenewLog = new ContractRenewLog();
		contractRenewLog.setContractId(mchtContract1.getLastContractId());
		contractRenewLog.setOperator(staffId);
		contractRenewLog.setOperatorType("1");
		contractRenewLog.setStatus("12");
		contractRenewLog.setStatusDate(new Date());
		contractRenewLog.setContent(renewRemarks);
		contractRenewLog.setCreateBy(staffId);
		contractRenewLog.setCreateDate(new Date());
		contractRenewLog.setUpdateBy(staffId);
		contractRenewLog.setUpdateDate(new Date());
		contractRenewLog.setDelFlag("0");
		contractRenewLogMapper.insertSelective(contractRenewLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName countByExampleCustomOnlineFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月3日 下午4:15:00
	 */
	public int countByExampleCustomOnlineFW(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByExampleCustomOnlineFW(mchtContractCustomExample);
	}

	/**
	 * @MethodName selectByExampleCustomOnlineFW
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月3日 下午4:15:04
	 */
	public List<MchtContractCustom> selectByExampleCustomOnlineFW(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByExampleCustomOnlineFW(mchtContractCustomExample);
	}

	/**
	 * @MethodName countByExampleCustomNotRenew
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月5日 下午7:35:28
	 */
	public int countByExampleCustomNotRenew(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByExampleCustomNotRenew(mchtContractCustomExample);
	}

	/**
	 * @MethodName selectByExampleCustomNotRenew
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月5日 下午7:35:34
	 */
	public List<MchtContractCustom> selectByExampleCustomNotRenew(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByExampleCustomNotRenew(mchtContractCustomExample);
	}

	/**
	 * @MethodName countByExampleCustomSuspended
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月6日 下午2:57:37
	 */
	public int countByExampleCustomSuspended(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.countByExampleCustomSuspended(mchtContractCustomExample);
	}

	/**
	 * @MethodName selectByExampleCustomSuspended
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月6日 下午2:57:42
	 */
	public List<MchtContractCustom> selectByExampleCustomSuspended(
			MchtContractCustomExample mchtContractCustomExample) {
		// TODO Auto-generated method stub
		return mchtContractCustomMapper.selectByExampleCustomSuspended(mchtContractCustomExample);
	}

	public List<Map<String, Object>>  selectExpireListByExample(Map<String, Object> map) {
		return mchtContractCustomMapper.selectExpireListByExample(map);
	}

	public int countExpireListByExample(Map<String, Object> map) {
		return mchtContractCustomMapper.countExpireListByExample(map);
	}
}
