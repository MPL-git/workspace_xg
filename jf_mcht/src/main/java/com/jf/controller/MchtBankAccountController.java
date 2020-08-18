package com.jf.controller;

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
import com.jf.common.utils.StringUtil;
import com.jf.entity.Bank;
import com.jf.entity.BankExample;
import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountExample;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisCustom;
import com.jf.entity.MchtBankAccountHisExample;
import com.jf.entity.MchtInfo;
import com.jf.service.BankService;
import com.jf.service.MchtBankAccountHisServer;
import com.jf.service.MchtBankAccountService;
import com.jf.service.MchtInfoService;

@Controller
public class MchtBankAccountController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(MchtBankAccountController.class);
	
	@Resource
	private MchtBankAccountService mchtBankAccountService;
	
	@Resource
	private MchtBankAccountHisServer mchtBankAccountHisService;
	
	@Resource
	private BankService bankService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	
	@RequestMapping("/mchtBankAccount/mchtBankAccountIndex")
	public String mchtContactIndex(Model model, HttpServletRequest request) {
		
		MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
		mchtBankAccountExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		mchtBankAccountExample.setOrderByClause(" id desc");
		mchtBankAccountExample.setLimitStart(0);
		mchtBankAccountExample.setLimitSize(1);
		List<MchtBankAccountCustom> mchtBankAccountCustoms=mchtBankAccountService.selectMchtBankAccountCustomByExample(mchtBankAccountExample);
		if(mchtBankAccountCustoms!=null&&mchtBankAccountCustoms.size()>0){
			model.addAttribute("mchtBankAccount", mchtBankAccountCustoms.get(0));
		}else{
			model.addAttribute("mchtBankAccount", new MchtBankAccountCustom());
		}
		
		return "mchtBankAccount/mchtBankAccount";
	}
	
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/getMchtBankAccountList")
	@ResponseBody
	public ResponseMsg getMchtBankAccountList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
		mchtBankAccountExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int totalCount = mchtBankAccountService.countByExample(mchtBankAccountExample);
		mchtBankAccountExample.setLimitStart(page.getLimitStart());
		mchtBankAccountExample.setLimitSize(page.getLimitSize());
		List<MchtBankAccountCustom> mchtBankAccounts = mchtBankAccountService.selectMchtBankAccountCustomByExample(mchtBankAccountExample);
		returnData.put("Rows", mchtBankAccounts);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/mchtBankAccountEdit")
	public String mchtBankAccountEdit(Model model,HttpServletRequest request) {
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		MchtBankAccount mchtBankAccount;
		if(!StringUtil.isEmpty(request.getParameter("id"))){
			
			mchtBankAccount=mchtBankAccountService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			
		}else{
			mchtBankAccount=new MchtBankAccount();
			mchtBankAccount.setMchtId(this.getSessionMchtInfo(request).getId());
			mchtBankAccount.setAccName(mchtInfo.getCompanyName());
			if(mchtInfo.getSettledType().equals("1")){
				mchtBankAccount.setAccType("2");//对公
			}else{
				mchtBankAccount.setAccType("1");//对私
			}
			
		}
		BankExample bankExample=new BankExample();
		List<Bank> banks=bankService.selectByExample(bankExample);
		model.addAttribute("mchtBankAccount", mchtBankAccount);
		model.addAttribute("mchtInfo", mchtInfo);
		model.addAttribute("banks", banks);
		model.addAttribute("isReload", request.getParameter("isReload"));
		MchtBankAccountHisExample mbahe = new MchtBankAccountHisExample();
		mbahe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andStatusEqualTo("2");
		List<MchtBankAccountHis> mchtBankAccountHisList = mchtBankAccountHisService.selectByExample(mbahe);
		model.addAttribute("mchtBankAccountHisList", mchtBankAccountHisList);
		return "mchtBankAccount/mchtBankAccountEdit";
	}
	
	
	
	/**
	 * 添加，编辑保存
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/mchtBankAccountSave")
	@ResponseBody
	public ResponseMsg mchtBankAccountSave(Model model, HttpServletRequest request,MchtBankAccount mchtBankAccount) {
		try {
			mchtBankAccount.setMchtId(this.getSessionMchtInfo(request).getId());
			
//			if("2".equals(mchtBankAccount.getAccType())){
//				mchtBankAccount.setAccName(this.getSessionMchtInfo(request).getCompanyName());
//			}
			
			if(StringUtil.isEmpty(mchtBankAccount.getAccName())){
				throw new ArgException("账户名称不能为空");
			}
			
			mchtBankAccount.setUpdateDate(new Date());
			mchtBankAccount.setUpdateBy(this.getSessionUserInfo(request).getId());
			mchtBankAccount.setStatus("0");
			mchtBankAccountService.mchtBankAccountSave(mchtBankAccount);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 添加，编辑保存
	 * 
	 * @param model
	 * @param request
	 * @param mchtProductBrand
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/setDefault")
	@ResponseBody
	public ResponseMsg setDefault(Model model, HttpServletRequest request) {
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("记录不存在");
			}
			mchtBankAccountService.setDefault(Integer.valueOf(request.getParameter("id")));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	@RequestMapping("/mchtBankAccount/mchtBankAccountHisIndex")
	public String mchtBankAccountHisIndex(Model model, HttpServletRequest request) {
		return "mchtBankAccount/mchtBankAccountHisIndex";
	}
	
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/getMchtBankAccountHisList")
	@ResponseBody
	public ResponseMsg getMchtBankAccountHisList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtBankAccountHisExample mchtBankAccountHisExample=new MchtBankAccountHisExample();
		mchtBankAccountHisExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		int totalCount = mchtBankAccountHisService.countByExample(mchtBankAccountHisExample);
		mchtBankAccountHisExample.setLimitStart(page.getLimitStart());
		mchtBankAccountHisExample.setLimitSize(page.getLimitSize());
		List<MchtBankAccountHisCustom> mchtBankAccountHiss = mchtBankAccountHisService.selectMchtBankAccountHisCustomByExample(mchtBankAccountHisExample);
		returnData.put("Rows", mchtBankAccountHiss);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtBankAccount/mchtBankAccountHisView")
	public String mchtBankAccountHisView(Model model,HttpServletRequest request) {
		MchtBankAccountHisCustom mchtBankAccountHis=mchtBankAccountHisService.selectMchtBankAccountHisCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			
		model.addAttribute("mchtBankAccountHis", mchtBankAccountHis);
		
		return "mchtBankAccount/mchtBankAccountHisView";
	}
	
	
	
	
}
