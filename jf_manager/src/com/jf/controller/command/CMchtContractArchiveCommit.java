package com.jf.controller.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ContractRenewLog;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractPic;
import com.jf.entity.MchtInfo;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.StaffBean;
import com.jf.service.ContractRenewLogService;
import com.jf.service.MchtContactService;
import com.jf.service.MchtContractPicService;
import com.jf.service.MchtContractService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPlatformContactService;
import com.jf.service.MchtUserService;
import com.jf.service.MsgTplService;
import com.jf.service.PlatformMsgService;

/**
 * 商家合同归档
 *
 * @author huangdl
 */
public class CMchtContractArchiveCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtContractArchiveCommit.class);

	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private MchtContactService mchtContactService;
	@Resource
	private MchtContractPicService mchtContractPicService;
	@Resource
	private MsgTplService msgTplService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MchtUserService mchtUserService;
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	@Resource
	private ContractRenewLogService contractRenewLogService;
	
	private MchtContract param;

	private StaffBean currentUser;
	
	private String mchtContractPics;

	@Override
	public void init() {
		param = getBean(MchtContract.class, "model");
		Assert.moreThanZero(param.getId(), "合同ID不能为空");
		Assert.notBlank(param.getContractCode(), "请填写合同编号");
		Assert.notBlank(param.getArchiveStatus(), "请选择归档状态");

		Assert.notNull(param.getBeginDate(), "合同开始时间不能为空");
		Assert.notNull(param.getEndDate(), "合同到期时间不能为空");

		if(Const.MCHT_CONTRACT_SEND_STATUS_YES.equals(param.getIsPlatformSend())){
			Assert.moreThanZero(param.getPlatformExpressId(), "寄回快递ID不能为空");
			Assert.notBlank(param.getPlatformExpressNo(), "寄回快递单号不能为空");
		}
		mchtContractPics = getPara("mchtContractPics");
		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		MchtContract model = mchtContractService.findById(param.getId());
		Assert.notNull(model, "合同不存在");

		MchtInfo mchtInfo = mchtInfoService.findById(model.getMchtId());
		Assert.notNull(mchtInfo, "商家不存在");

		if(!Const.MCHT_TOTAL_AUDIT_STATUS_PASS.equals(mchtInfo.getTotalAuditStatus()) && !Const.MCHT_TOTAL_AUDIT_STATUS_REJECT.equals(mchtInfo.getTotalAuditStatus())){
			throw new BizException("总资质审核状态为通过或驳回的商家才能进行归档操作");
		}

		if(Const.MCHT_CONTRACT_ARCHIVE_STATUS_PASS.equals(model.getArchiveStatus())){
			throw new BizException("该合同已归档，不能操作");
		}

		model.setContractCode(param.getContractCode());	//合同编号
		model.setBeginDate(param.getBeginDate());	//合同开始时间
		model.setEndDate(param.getEndDate());	//合同到期时间
		model.setFwInnerRemarks(param.getRemarks());//法务归档备注
		if(Const.MCHT_CONTRACT_SEND_STATUS_YES.equals(param.getIsPlatformSend())){
			model.setIsPlatformSend(Const.MCHT_CONTRACT_SEND_STATUS_YES);
			model.setPlatformExpressId(param.getPlatformExpressId());
			model.setPlatformExpressNo(param.getPlatformExpressNo());
			model.setPlatformSendDate(new Date());
		}else{
			model.setIsPlatformSend(Const.MCHT_CONTRACT_SEND_STATUS_NO);
		}
		model.setUpdateBy(Integer.valueOf(currentUser.getStaffID()));
		model.setArchiveStatus(param.getArchiveStatus());	//归档状态
		if(param.getArchiveStatus().equals("1")){//归档通过
			mchtInfo.setAgreementBeginDate(param.getBeginDate());
			mchtInfo.setAgreementEndDate(param.getEndDate());
			mchtInfoService.update(mchtInfo);
			
			//归档通过插入记录表
			ContractRenewLog contractRenewLog = new ContractRenewLog();
			contractRenewLog.setContractId(model.getId());
			contractRenewLog.setOperator(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setOperatorType("1");
			contractRenewLog.setStatus("10");
			contractRenewLog.setStatusDate(new Date());
			contractRenewLog.setContent(model.getRemarks());
			contractRenewLog.setCreateBy(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setCreateDate(new Date());
			contractRenewLog.setUpdateBy(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setUpdateDate(new Date());
			contractRenewLog.setDelFlag("0");
			contractRenewLogService.insertSelective(contractRenewLog);
		}
		model.setArchiveNo(param.getArchiveNo());	//归档编号
		model.setArchiveBy(Integer.valueOf(currentUser.getStaffID()));	//归档人员
		model.setArchiveDate(new Date());	//归档时间
		model.setRemarks(param.getRemarks());	//备注，驳回原因
		model.setFwInnerRemarks(param.getFwInnerRemarks());	// 法务内部备注
		if(param.getArchiveStatus().equals("2")){
			model.setIsMchtSend("0");
			//归档驳回插入记录表
			ContractRenewLog contractRenewLog = new ContractRenewLog();
			contractRenewLog.setContractId(model.getId());
			contractRenewLog.setOperator(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setOperatorType("1");
			contractRenewLog.setStatus("11");
			contractRenewLog.setStatusDate(new Date());
			contractRenewLog.setContent(model.getRemarks());
			contractRenewLog.setCreateBy(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setCreateDate(new Date());
			contractRenewLog.setUpdateBy(Integer.valueOf(Integer.valueOf(currentUser.getStaffID())));
			contractRenewLog.setUpdateDate(new Date());
			contractRenewLog.setDelFlag("0");
			contractRenewLogService.insertSelective(contractRenewLog);
		}
		mchtContractService.updateArchiveStatus(model);
		//TODO 图片处理
		List<MchtContractPic> mchtContractPicList = new ArrayList<MchtContractPic>();
		if(!StringUtils.isEmpty(mchtContractPics)){
			JSONArray picArray = JSONArray.fromObject(mchtContractPics);
			Iterator<JSONObject> it= picArray.iterator();
			while (it.hasNext()) {
				JSONObject pic=it.next();
				MchtContractPic mchtContractPic = new MchtContractPic();
				mchtContractPic.setMchtId(model.getMchtId());
				mchtContractPic.setContractId(model.getId());
				mchtContractPic.setOperatorType("2");//平台
				mchtContractPic.setPic(pic.getString("picPath"));
				mchtContractPic.setCreateBy(Integer.parseInt(currentUser.getStaffID()));
				mchtContractPic.setCreateDate(new Date());
				mchtContractPic.setDelFlag("0");
				mchtContractPicList.add(mchtContractPic);
			}
		}
		if(mchtContractPicList!=null && mchtContractPicList.size()>0){
			mchtContractPicService.saveMchtContractPicList(mchtContractPicList);
		}
		if(param.getArchiveStatus().equals("2")){//驳回
			// 发送站内信
			String title = "关于合同归档通知";
			String content = "您的合同归档被驳回，请在前往工商主体信息管理中查看驳回原因。";
	    	platformMsgService.save(model.getMchtId(), title, content, "TZ");
	    	
	    	// 发送短信给商家端主账号
	    	String mobile = mchtUserService.selectMobileByMchtId(model.getMchtId());
	    	content = "您的店铺【" + mchtInfo.getMchtCode() + "】的合同归档驳回，请登录平台查看驳回原因。";
	    	if(StrKit.notBlank(mobile)){
		    	SmsUtil.send(mobile, content, "4");
	    	}
	        // 发送短信给商家店铺负责人
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 1);
	        if(StrKit.notBlank(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	        // 发送短信给商家运营专员
	        mobile = mchtUserService.selectMchtContactMobileByMchtId(model.getMchtId(), 2);
	        if(StrKit.notBlank(mobile)){
	        	SmsUtil.send(mobile, content, "4");
	        }
	    	
			// 发送短信给平台招商员
	    	mobile = mchtPlatformContactService.findMobile(model.getMchtId(), "1");
	    	if(StrKit.notBlank(mobile)){
	    		content = "您对接的【" + mchtInfo.getMchtCode() + "】的合同归档驳回，请尽快联系商家将资料寄回平台";
		    	SmsUtil.send(mobile, content, "4");
	    	}
			
	    	sendOldSms(model, mchtInfo);
		}
	}
	
	// 旧发送短信
	public void sendOldSms(MchtContract model, MchtInfo mchtInfo){
		String toSendMobile="";
		List<String> contactTypes = new ArrayList<String>();
		contactTypes.add("1");//电商
		contactTypes.add("2");//运营
		for(String contactType:contactTypes){
			MchtContactExample example = new MchtContactExample();
			MchtContactExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(model.getMchtId());
			c.andContactTypeEqualTo(contactType);
			List<MchtContact> mchtContacts = mchtContactService.selectByExample(example);
			if(mchtContacts!=null && mchtContacts.size()>0){
				toSendMobile = mchtContacts.get(0).getMobile();
				break;
			}
		}
		if(StringUtils.isEmpty(toSendMobile)){
			toSendMobile = mchtInfo.getCorporationMobile();
		}
		if(!StringUtils.isEmpty(toSendMobile)){
			try {
				JSONObject param=new JSONObject();
				JSONObject reqData=new JSONObject();
				reqData.put("mobile", toSendMobile);
				reqData.put("content", "您寄往平台的资料及合同有部分不齐全，请登录平台查看具体原因，并按要求补齐寄回。");
				reqData.put("smsType", "4");
				param.put("reqData", reqData);
				JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
