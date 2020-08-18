package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.MchtInfoChgCustom;
import com.jf.entity.MchtInfoChgExample;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgCustom;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;
import com.jf.entity.MchtTaxInvoiceInfoCustom;
import com.jf.entity.MchtTaxInvoiceInfoExample;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtTaxInvoiceInfoChgService;
import com.jf.service.MchtTaxInvoiceInfoService;

@Controller
public class MchtTaxInvoiceController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(MchtTaxInvoiceController.class);
	
	@Resource
	private MchtTaxInvoiceInfoService mchtTaxInvoiceInfoService;
	
	@Resource
	private MchtTaxInvoiceInfoChgService mchtTaxInvoiceInfoChgService;
	
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceIndex")
	public String mchtContactIndex(Model model, HttpServletRequest request) {
		int infoChgCount=0;
		MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample=new MchtTaxInvoiceInfoExample();
		mchtTaxInvoiceInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtTaxInvoiceInfoCustom> mchtTaxInvoiceInfoCustoms=mchtTaxInvoiceInfoService.selectMchtTaxInvoiceInfoCustomByExample(mchtTaxInvoiceInfoExample);
		if(mchtTaxInvoiceInfoCustoms!=null&&mchtTaxInvoiceInfoCustoms.size()>0){
			MchtTaxInvoiceInfoCustom mchtTaxInvoiceInfoCustom=mchtTaxInvoiceInfoCustoms.get(0);
			model.addAttribute("mchtTaxInvoiceInfo", mchtTaxInvoiceInfoCustom);
			//是否有申请修改
			List<String> statusList=new ArrayList<String>();
			statusList.add("0");
			statusList.add("1");
			statusList.add("2");
			MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample=new MchtTaxInvoiceInfoChgExample();
			mchtTaxInvoiceInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andAuditStatusIn(statusList);
			infoChgCount=mchtTaxInvoiceInfoChgService.countByExample(mchtTaxInvoiceInfoChgExample);
			model.addAttribute("infoChgCount", infoChgCount);
		}else{
			model.addAttribute("mchtTaxInvoiceInfo", new MchtTaxInvoiceInfoCustom());
		}
		
		return "mchtTaxInvoice/mchtTaxInvoiceIndex";
	}
	
	
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceEdit")
	public String mchtTaxInvoiceEdit(Model model, HttpServletRequest request) {
		MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample=new MchtTaxInvoiceInfoExample();
		mchtTaxInvoiceInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtTaxInvoiceInfoCustom> mchtTaxInvoiceInfoCustoms=mchtTaxInvoiceInfoService.selectMchtTaxInvoiceInfoCustomByExample(mchtTaxInvoiceInfoExample);
		if(mchtTaxInvoiceInfoCustoms!=null&&mchtTaxInvoiceInfoCustoms.size()>0){
			MchtTaxInvoiceInfoCustom mchtTaxInvoiceInfoCustom=mchtTaxInvoiceInfoCustoms.get(0);
			model.addAttribute("mchtTaxInvoiceInfo", mchtTaxInvoiceInfoCustom);
		}else{
			model.addAttribute("mchtTaxInvoiceInfo", new MchtTaxInvoiceInfoCustom());
		}
		
		model.addAttribute("taxTypeDesc", DataDicUtil.getStatusList("BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
		model.addAttribute("mchtInfo", mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId()));
		
		return "mchtTaxInvoice/mchtTaxInvoiceEdit";
	}
	
	
	/**
	 * 信息完善页保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceCommitSave")
	@ResponseBody
	public ResponseMsg mchtTaxInvoiceCommitSave(Model model, HttpServletRequest request,MchtTaxInvoiceInfo mchtTaxInvoiceInfo) {
		try {
			mchtTaxInvoiceInfo.setUpdateDate(new Date());
			mchtTaxInvoiceInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
			if(mchtTaxInvoiceInfo.getId()==null){
				mchtTaxInvoiceInfo.setMchtId(this.getSessionMchtInfo(request).getId());
				mchtTaxInvoiceInfo.setCreateBy(mchtTaxInvoiceInfo.getCreateBy());
				mchtTaxInvoiceInfo.setCreateDate(mchtTaxInvoiceInfo.getCreateDate());
			}
			mchtTaxInvoiceInfoService.mchtTaxInvoiceInfoCommitSave(mchtTaxInvoiceInfo);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 *税票信息申请更新
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/addMchtTaxInvoiceChg")
	public String addMchtTaxInvoiceChg(Model model, HttpServletRequest request) {
		
		model.addAttribute("taxTypeDescList", DataDicUtil.getStatusList("BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
		
		MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample=new MchtTaxInvoiceInfoChgExample();
		mchtTaxInvoiceInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andAuditStatusNotEqualTo("3");
		
		int count=mchtTaxInvoiceInfoChgService.countByExample(mchtTaxInvoiceInfoChgExample);
		if(count>0){
			return null;
		}
		
		
		    MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg;
			MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample=new MchtTaxInvoiceInfoExample();
			mchtTaxInvoiceInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			List<MchtTaxInvoiceInfo> mhcInvoiceInfos=mchtTaxInvoiceInfoService.selectByExample(mchtTaxInvoiceInfoExample);
			MchtTaxInvoiceInfo mchtTaxInvoiceInfo=mhcInvoiceInfos.get(0);
			mchtTaxInvoiceInfoChg=new MchtTaxInvoiceInfoChg();
			mchtTaxInvoiceInfoChg.setTaxNumber(mchtTaxInvoiceInfo.getTaxNumber());
			mchtTaxInvoiceInfoChg.setAddress(mchtTaxInvoiceInfo.getAddress());
			mchtTaxInvoiceInfoChg.setTel(mchtTaxInvoiceInfo.getTel());
			mchtTaxInvoiceInfoChg.setDepositBank(mchtTaxInvoiceInfo.getDepositBank());
			mchtTaxInvoiceInfoChg.setAccountNumber(mchtTaxInvoiceInfo.getAccountNumber());
			mchtTaxInvoiceInfoChg.setAuditStatus("0");
			mchtTaxInvoiceInfoChg.setMchtTaxInvoiceInfoId(mchtTaxInvoiceInfo.getId());
			mchtTaxInvoiceInfoChg.setTaxType(mchtTaxInvoiceInfo.getTaxType());
			mchtTaxInvoiceInfoChg.setMchtId(this.getSessionMchtInfo(request).getId());
			model.addAttribute("mchtTaxInvoiceInfoChg", mchtTaxInvoiceInfoChg);
			model.addAttribute("auditStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO_CHG", "AUDIT_STATUS", "0"));
			return "/mchtTaxInvoice/mchtTaxInvoiceChgEdit";
		
	}
	/**
	 *税务更新休息修改
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceChgEdit")
	public String mchtTaxInvoiceChgApply(Model model, HttpServletRequest request) {
		
		model.addAttribute("taxTypeDescList", DataDicUtil.getStatusList("BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE"));
			MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg=mchtTaxInvoiceInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			model.addAttribute("mchtTaxInvoiceInfoChg", mchtTaxInvoiceInfoChg);
			model.addAttribute("auditStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO_CHG", "AUDIT_STATUS", mchtTaxInvoiceInfoChg.getAuditStatus()));
			model.addAttribute("taxTypeDesc", DataDicUtil.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "TAX_TYPE", mchtTaxInvoiceInfoChg.getTaxType()));
			if(mchtTaxInvoiceInfoChg.getAuditStatus().equals("0")||mchtTaxInvoiceInfoChg.getAuditStatus().equals("4")){
				return "/mchtTaxInvoice/mchtTaxInvoiceChgEdit";
			}else{
				return "/mchtTaxInvoice/mchtTaxInvoiceChgView";
			}
		
	}
	
	
	/**
	 * 公司信息更新申请页提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceCommitAudit")
	@ResponseBody
	public ResponseMsg mchtTaxInvoiceCommitAudit(Model model, HttpServletRequest request,MchtTaxInvoiceInfo mchtTaxInvoiceInfo) {
		try {
			mchtTaxInvoiceInfo.setUpdateDate(new Date());
			mchtTaxInvoiceInfo.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtTaxInvoiceInfoService.mchtTaxInvoiceInfoCommitAudit(mchtTaxInvoiceInfo);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	
	/**
	 * 信息申请修改保存暂不提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceChgCommitSave")
	@ResponseBody
	public ResponseMsg mchtTaxInvoiceChgCommitSave(Model model, HttpServletRequest request,MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg) {
		try {
			mchtTaxInvoiceInfoChg.setUpdateDate(new Date());
			mchtTaxInvoiceInfoChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			if(mchtTaxInvoiceInfoChg.getId()==null){
				mchtTaxInvoiceInfoChg.setMchtId(this.getSessionMchtInfo(request).getId());
				mchtTaxInvoiceInfoChg.setCreateBy(mchtTaxInvoiceInfoChg.getCreateBy());
				mchtTaxInvoiceInfoChg.setCreateDate(mchtTaxInvoiceInfoChg.getCreateDate());
			}
			mchtTaxInvoiceInfoChgService.mchtTaxInvoiceInfoChgCommitSave(mchtTaxInvoiceInfoChg);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 公司信息更新申请页提审
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceChgCommitAudit")
	@ResponseBody
	public ResponseMsg mchtTaxInvoiceChgCommitAudit(Model model, HttpServletRequest request,MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg) {
		try {
			mchtTaxInvoiceInfoChg.setMchtId(this.getSessionMchtInfo(request).getId());
			mchtTaxInvoiceInfoChg.setUpdateDate(new Date());
			mchtTaxInvoiceInfoChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtTaxInvoiceInfoChgService.mchtTaxInvoiceInfoChgCommitAudit(mchtTaxInvoiceInfoChg);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 商家信息修改申请首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceChgIndex")
	public String mchtTaxInvoiceChgIndex(Model model, HttpServletRequest request) {
		
		MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample=new MchtTaxInvoiceInfoChgExample();
		mchtTaxInvoiceInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andAuditStatusNotEqualTo("3");
		int count=mchtTaxInvoiceInfoChgService.countByExample(mchtTaxInvoiceInfoChgExample);
		model.addAttribute("applyCount",count);
		return "/mchtTaxInvoice/mchtTaxInvoiceChgIndex";
	}
	

	/**
	 * 商家公司信息申请修改列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/mchtTaxInvoiceChgList")
	@ResponseBody
	public ResponseMsg mchtInfoChgApplyList( HttpServletRequest request, Page page) {
		Map<String, Object> returnData=new HashMap<String, Object>();
		MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample=new MchtTaxInvoiceInfoChgExample();
		mchtTaxInvoiceInfoChgExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andDelFlagEqualTo("0");
		int totalCount=mchtTaxInvoiceInfoChgService.countByExample(mchtTaxInvoiceInfoChgExample);
		
		mchtTaxInvoiceInfoChgExample.setLimitSize(page.getLimitSize());
		mchtTaxInvoiceInfoChgExample.setLimitStart(page.getLimitStart());
		List<MchtTaxInvoiceInfoChgCustom> MchtTaxInvoiceInfoChgCustoms=mchtTaxInvoiceInfoChgService.selectMchtTaxInvoiceInfoChgCustomByExample(mchtTaxInvoiceInfoChgExample);
		returnData.put("Rows", MchtTaxInvoiceInfoChgCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		
	}
	
	
	/**
	 * 税务更新信息删除
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtTaxInvoice/delMchtTaxInvoiceChg")
	@ResponseBody
	public ResponseMsg delMchtTaxInvoiceChg( HttpServletRequest request, Page page) {
		try {
			
			MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg=mchtTaxInvoiceInfoChgService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			
			if(mchtTaxInvoiceInfoChg==null){
				throw new ArgException("信息不存在");
			}
			
			if("1".equals(mchtTaxInvoiceInfoChg.getAuditStatus())||"2".equals(mchtTaxInvoiceInfoChg.getAuditStatus())){
				throw new ArgException("信息在审核中，不可删除");
			}
			if("3".equals(mchtTaxInvoiceInfoChg.getAuditStatus())){
				throw new ArgException("信息已审核通过，不可删除");
			}
			
			mchtTaxInvoiceInfoChg.setDelFlag("1");
			mchtTaxInvoiceInfoChg.setUpdateDate(new Date());
			mchtTaxInvoiceInfoChg.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtTaxInvoiceInfoChgService.updateByPrimaryKeySelective(mchtTaxInvoiceInfoChg);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	
}
