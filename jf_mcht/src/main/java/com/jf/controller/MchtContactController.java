package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.functors.IfClosure;
import org.apache.commons.lang.StringUtils;
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
import com.jf.common.utils.StringUtil;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactCustom;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtPlatformContactCustom;
import com.jf.entity.MchtPlatformContactCustomExample;
import com.jf.service.MchtContactService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPlatformContactService;

@Controller
public class MchtContactController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtContactController.class);
	
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	

	/**
	 * 
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/mchtContact/mchtContactIndex")
	public String mchtContactIndex(Model model, HttpServletRequest request) {
		
		return "mchtContact/mchtContactIndex";
	}
	/**
	 * 商家信息页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/getMchtContactList")
	@ResponseBody
	public ResponseMsg getMchtContactList(HttpServletRequest request,Page page) {
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtContactExample mchtContactExample=new MchtContactExample();
		MchtContactExample.Criteria criteria=mchtContactExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		if (!StringUtil.isEmpty(request.getParameter("contactType"))) {
			criteria.andContactTypeEqualTo(request.getParameter("contactType"));
		}
		int totalCount = mchtContactService.countByExample(mchtContactExample);
		mchtContactExample.setLimitStart(page.getLimitStart());
		mchtContactExample.setLimitSize(page.getLimitSize());
		List<MchtContact> mchtProductBrands = mchtContactService.selectByExample(mchtContactExample);
		returnData.put("Rows", mchtProductBrands);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	
	}
	
	/**
	 * 获取商家平台对接人列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/getMchtPlatfromContactList")
	@ResponseBody
	public ResponseMsg getMchtPlatfromContactList(HttpServletRequest request,Page page) {
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		MchtPlatformContactCustomExample example = new MchtPlatformContactCustomExample();
		MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andStatusEqualTo("1");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		criteria.andPlatformContactTypeIn("2");
		int totalCount = mchtPlatformContactService.countMchtPlatformContactCustomByExample(example);
		example.setLimitStart(page.getLimitStart());
		example.setLimitSize(page.getLimitSize());
		List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(example);
		returnData.put("Rows", mchtPlatformContactCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		
	}
	
	
	/**
	 * 联系人信息查看
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/mchtContactView")
	public String mchtContactView(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		MchtContactCustom mchtContact=mchtContactService.selectMchtContactCustomByPrimaryKey(id);
		model.addAttribute("mchtContact", mchtContact);
		return "mchtContact/mchtContactView";
	}
	
	
	/**
	 * 联系人信息添加
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/mchtContactAdd")
	public String mchtContactAdd(Model model,HttpServletRequest request) {
		MchtContact mchtContact=new MchtContact();
		mchtContact.setMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtContact", mchtContact);
		model.addAttribute("contactTypeDesc", DataDicUtil.getStatusList("BU_MCHT_CONTACT", "CONTACT_TYPE"));
		if(!StringUtil.isEmpty(request.getParameter("contactType"))){
			model.addAttribute("contactType", request.getParameter("contactType"));
		}
		model.addAttribute("mchtInfo", mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId()));
		model.addAttribute("addType",request.getParameter("addType"));
		return "mchtContact/mchtContactEdit";
	}
	
	/**
	 * 联系人信息修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/mchtContactEdit")
	public String mchtContactChange(Model model,HttpServletRequest request) {
		model.addAttribute("contactType", request.getParameter("contactType"));
		MchtContactCustom mchtContact = new MchtContactCustom();
		if(request.getParameter("id") != ""){
			Integer id=Integer.valueOf(request.getParameter("id"));
			model.addAttribute("id",id);
			mchtContact=mchtContactService.selectMchtContactCustomByPrimaryKey(id);
		}else {
			//当收件人信息为空时，取最新对接人且不为空的信息
			MchtContactExample mchtContactExample = new MchtContactExample();
			mchtContactExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
			mchtContactExample.setOrderByClause(" id DESC");
			List<MchtContactCustom> mchtContacts = mchtContactService.selectCustomByExample(mchtContactExample);
			if(!mchtContacts.isEmpty()){
				mchtContact = mchtContacts.get(0);
			}
			model.addAttribute("mchtContactStatus","1");
		}
		model.addAttribute("mchtContact", mchtContact);
		String contactType = mchtContact.getContactType();
		model.addAttribute("contactTypeDesc", DataDicUtil.getStatusList("BU_MCHT_CONTACT", "CONTACT_TYPE"));
		//填写店铺总负责人信息
		if(!StringUtil.isBlank(request.getParameter("writeType"))){
			model.addAttribute("writeType",request.getParameter("writeType"));
			return "mchtContact/mchtContactPerfect";
		}else if(!StringUtil.isBlank(request.getParameter("perfectType"))){//完善店铺总负责人信息
			model.addAttribute("perfectType",request.getParameter("perfectType"));
			return "mchtContact/mchtContactPerfect";
		}else if(!StringUtil.isBlank(request.getParameter("Mailing"))){//立即寄件
			model.addAttribute("Mailing",request.getParameter("Mailing"));
			return "mchtContact/mchtContactPerfect";
		}else {//管理联系人修改
			model.addAttribute("contactType", contactType);
			return "mchtContact/mchtContactEdit";
		}
	}
	
	/**
	 * 联系人信息修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	
	@RequestMapping("/mchtContact/mchtContactSave")
	@ResponseBody
	public ResponseMsg mchtContactSave(HttpServletRequest request,MchtContact mchtContact) {
		try {
			if(StringUtils.equals(mchtContact.getContactType(), "1") || StringUtils.equals(mchtContact.getContactType(), "2") || StringUtils.equals(mchtContact.getContactType(), "3") || StringUtils.equals(mchtContact.getContactType(), "6")){
				String realValideCode = (String) request.getSession().getAttribute(request.getParameter("mobile"));
				String valideCode = request.getParameter("valideCode");
				if(!StringUtil.isEmpty(realValideCode) && !realValideCode.equals(valideCode)){
					throw new ArgException("验证码错误，无法保存。");
				}
			}

			String contactType=mchtContact.getContactType();
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtContact.getMchtId());
			if(mchtInfo.getSettledType().equals("1")){//公司企业
				MchtContactExample e=new MchtContactExample();
				e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andMobileEqualTo(mchtContact.getMobile());
				List<MchtContact> mchtContacts = mchtContactService.selectByExample(e);
				//先判断是第一次提交还是驳回提交
				if("null".equals(String.valueOf(mchtContact.getId())) || "0".equals(String.valueOf(mchtContact.getId()))){
					//如果是店铺总负责人提交
					if("1".equals(mchtContact.getContactType()) && !mchtContacts.isEmpty()){
						throw new ArgException("对不起，其他人员已使用此手机号，请不要重复填写。");
					}
					//如果不是店铺总负责人提交
					if(!"1".equals(mchtContact.getContactType()) && !mchtContacts.isEmpty()){
						for (MchtContact mchtContact2 : mchtContacts) {
							if("1".equals(mchtContact2.getContactType())){
								throw new ArgException("对不起，店铺总负责人已使用此手机号，请不要重复填写。");
							}
						}
					}
				}else{//驳回提交
					for (MchtContact mchtContact2 : mchtContacts) {
						//如果是店铺总负责人提交
						if("1".equals(mchtContact.getContactType()) && mchtContact.getId().intValue() != mchtContact2.getId().intValue()){
							throw new ArgException("对不起，其他人员已使用此手机号，请不要重复填写。");
						}
						//如果不是店铺总负责人提交
						if(!"1".equals(mchtContact.getContactType()) && mchtContact.getId().intValue() != mchtContact2.getId().intValue() && "1".equals(mchtContact2.getContactType())){
							throw new ArgException("对不起，店铺总负责人已使用此手机号，请不要重复填写。");
						}
					}
				}
				
				MchtContactExample e1=new MchtContactExample();
				e1.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactNameEqualTo(mchtContact.getContactName());
				List<MchtContact> mchtContacts1 = mchtContactService.selectByExample(e1);
				//先判断是第一次提交还是驳回提交
				if("null".equals(String.valueOf(mchtContact.getId())) || "0".equals(String.valueOf(mchtContact.getId()))){
					//如果是店铺总负责人提交
					if("1".equals(mchtContact.getContactType()) && !mchtContacts1.isEmpty()){
						throw new ArgException("对不起，其他人员已使用此姓名，请不要重复填写。");
					}
					//如果不是店铺总负责人提交
					if(!"1".equals(mchtContact.getContactType()) && !mchtContacts1.isEmpty()){
						for (MchtContact mchtContact2 : mchtContacts1) {
							if("1".equals(mchtContact2.getContactType())){
								throw new ArgException("对不起，店铺总负责人已使用此姓名，请不要重复填写。");
							}
						}
					}
				}else{//驳回提交
					for (MchtContact mchtContact2 : mchtContacts1) {
						//如果是店铺总负责人提交
						if("1".equals(mchtContact.getContactType()) && mchtContact.getId().intValue() != mchtContact2.getId().intValue()){
							throw new ArgException("对不起，其他人员已使用此姓名，请不要重复填写。");
						}
						//如果不是店铺总负责人提交
						if(!"1".equals(mchtContact.getContactType()) && mchtContact.getId().intValue() != mchtContact2.getId().intValue() && "1".equals(mchtContact2.getContactType())){
							throw new ArgException("对不起，店铺总负责人已使用此姓名，请不要重复填写。");
						}
					}
				}
			}
			
			MchtContactExample mce=new MchtContactExample();
			mce.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andQqEqualTo(mchtContact.getQq());
			if(mchtContactService.countByExample(mce)>0 && mchtContact.getId() == null){
				throw new ArgException("对不起，其他人员已使用此QQ号，请不要重复填写。");
			}
			
			MchtContactExample mchtContactExample=new MchtContactExample();
			mchtContactExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo(contactType);
			int count=mchtContactService.countByExample(mchtContactExample);
			if(count>=30){
				throw new ArgException("对接人不能超过30个");
			}
			
			mchtContact.setUpdateDate(new Date());
			mchtContact.setUpdateBy(this.getSessionUserInfo(request).getId());
			
			if(mchtContact.getId()==null){
				mchtContact.setCreateDate(mchtContact.getUpdateDate());
				mchtContact.setCreateBy(mchtContact.getUpdateBy());
			}
			
			if(StringUtils.equals(request.getParameter("Mailing"), "1") && StringUtil.isBlank(request.getParameter("writeType")) && StringUtil.isBlank(request.getParameter("perfectType"))){
				
			}else {
				mchtContact.setAuditStatus("0");
			}	
			return mchtContactService.mchtContactSave(mchtContact);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	
	/**
	 * 设置为默认联系人
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/setDefaultMchtContact")
	@ResponseBody
	public ResponseMsg setDefaultMchtContact(HttpServletRequest request) {
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("联系人不存在");
			}
			mchtContactService.setDefaultMchtContact(Integer.valueOf(request.getParameter("id")));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 删除联系人
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/delMchtContact")
	@ResponseBody
	public ResponseMsg delMchtContact(HttpServletRequest request) {
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("联系人不存在");
			}
			MchtContact mchtContact4Update=new MchtContact();
			mchtContact4Update.setDelFlag("1");
			mchtContact4Update.setId(Integer.valueOf(request.getParameter("id")));
			mchtContactService.updateByPrimaryKeySelective(mchtContact4Update);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 获取最新店铺总负责人信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtContact/showContact")
	public String showcontact(HttpServletRequest request,Model model) {
		MchtContact mchtContact=new MchtContact();
		mchtContact.setMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtContact", mchtContact);
		return "mchtContact/showContact";
	}
}
