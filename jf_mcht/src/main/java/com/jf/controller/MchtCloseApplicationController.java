package com.jf.controller;

import com.alibaba.fastjson.JSONArray;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ExpressMapper;
import com.jf.entity.*;
import com.jf.entity.MchtContactExample.Criteria;
import com.jf.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MchtCloseApplicationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MchtCloseApplicationController.class);

	@Resource
	private MchtCloseApplicationService mchtCloseApplicationService;
	
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private ExpressMapper expressMapper;
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private MchtCloseApplicationPicService mchtCloseApplicationPicService;

	@Resource
	private MchtInfoService mchtInfoService;
	/**
	 * 关店申请首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtCloseApplication/index")
	public String mchtCloseApplicationIndex(Model model, HttpServletRequest request) {
		MchtCloseApplicationExample mcae = new MchtCloseApplicationExample();
		mcae.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtCloseApplication> mchtCloseApplications = mchtCloseApplicationService.selectByExample(mcae);
		model.addAttribute("mchtCloseApplications", mchtCloseApplications);
		if(mchtCloseApplications!=null && mchtCloseApplications.size()>0){
			model.addAttribute("mchtCloseApplication", mchtCloseApplications.get(0));
		}
		MchtDepositExample mde = new MchtDepositExample();
		mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);
		if(mchtDeposits!=null && mchtDeposits.size()>0){
			model.addAttribute("mchtDeposit", mchtDeposits.get(0));
		}
		return "mchtCloseApplication/mchtCloseApplicationIndex";
	}
	
	/**
	 * 保存商家提交的申请
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mchtCloseApplication/save")
	@ResponseBody
	public ResponseMsg save(HttpServletRequest request) {
		String applyReason = request.getParameter("applyReason");
		MchtCloseApplication mchtCloseApplication = new MchtCloseApplication();
		mchtCloseApplication.setCreateBy(this.getSessionUserInfo(request).getId());
		mchtCloseApplication.setCreateDate(new Date());
		mchtCloseApplication.setDelFlag("0");
		mchtCloseApplication.setMchtId(this.getSessionMchtInfo(request).getId());
		mchtCloseApplication.setApplySource("1");
		mchtCloseApplication.setApplyName(this.getSessionUserInfo(request).getUserCode());
		mchtCloseApplication.setApplyReason(applyReason);
		mchtCloseApplication.setProgressStatus("0");
		mchtCloseApplication.setZsConfirmStatus("0");
		mchtCloseApplicationService.insertSelective(mchtCloseApplication);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 立即寄件页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtCloseApplication/toSend")
	public String toSend(Model model,HttpServletRequest request) {
		//获取最新店铺总负责人
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		//criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andAuditStatusEqualTo("1").andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtContact contact = new MchtContact();
		if(!mchtContacts.isEmpty()){
			contact = mchtContactService.selectByExample(mchtContactExample).get(0);
		}
		model.addAttribute("mchtContact",contact);
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		ExpressExample e = new ExpressExample();
		ExpressExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		List<Express> expressList = expressMapper.selectByExample(e);
		model.addAttribute("expressList", expressList);
		//获取省市区名称
		List<Area> areas = (List<Area>) areaService.getAddress(contact);
		String address = "";
		for (Area area : areas) {
			address +=area.getAreaName();
		}
		address += contact.getAddress();
		if(StringUtils.equals(address, "null")){
			address = "";
		}
		model.addAttribute("address",address);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("mchtInfo",mchtInfo);
		return "mchtCloseApplication/toSend";
	}

	
	/**
	 * 立即寄件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtCloseApplication/send")
	@ResponseBody
	public Map<String, Object> send(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		
		MchtContactExample mchtContactExample = new MchtContactExample();
		Criteria criteria = mchtContactExample.createCriteria();
		Integer id2 = this.getSessionMchtInfo(request).getId();
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andContactTypeEqualTo("1").andDelFlagEqualTo("0");
		mchtContactExample.setOrderByClause(" id DESC");
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		if(!mchtContacts.isEmpty()){
			 MchtContact mchtContact = mchtContactService.selectByExample(mchtContactExample).get(0);
		 if("1".equals(mchtContact.getAuditStatus()) ){
				String id = request.getParameter("id");
				String expressId = request.getParameter("expressId");
				String expressNo = request.getParameter("expressNo");
				MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(id));
				mchtCloseApplication.setExpressId(Integer.parseInt(expressId));
				mchtCloseApplication.setExpressNo(expressNo.trim());
				mchtCloseApplication.setUpdateBy(this.getSessionMchtInfo(request).getId());
				mchtCloseApplication.setUpdateDate(new Date());
				mchtCloseApplication.setContractArchiveStatus("0");//设置合同归档状态为未处理
				mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);

		  }else {

				 resMap.put("returnCode", "404");
					resMap.put("returnMsg", "信息正在审核中,请稍后再试");
			 }
		}else if("1".equals(mchtInfo.getIsManageSelf())){
			String id = request.getParameter("id");
			String expressId = request.getParameter("expressId");
			String expressNo = request.getParameter("expressNo");
			MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(id));
			mchtCloseApplication.setExpressId(Integer.parseInt(expressId));
			mchtCloseApplication.setExpressNo(expressNo.trim());
			mchtCloseApplication.setUpdateBy(this.getSessionMchtInfo(request).getId());
			mchtCloseApplication.setUpdateDate(new Date());
			mchtCloseApplication.setContractArchiveStatus("0");//设置合同归档状态为未处理
			mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		}else{
			resMap.put("returnCode", "404");
			resMap.put("returnMsg", "请添加电商总负责人信息");
		}
		return resMap;
	}
	
	/**
	 * 终止合作协议预览
	 */
	@RequestMapping("/mchtCloseApplication/endAgreementPreview")
	public String endAgreementPreview(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		String id = request.getParameter("id");
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(id));
		data.put("mchtCloseApplication", mchtCloseApplication);
		return page(data, "mchtCloseApplication/endAgreementPreview");
	}
	
	/**
	 * 查看PDF
	 */
	@RequestMapping("/mchtCloseApplication/endAgreementPDF")
	public void contractPDF(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(id));
		Assert.notNull(mchtCloseApplication, "终止合作协议还没生成，不能查看协议扫描件");

		//设置响应内容类型为PDF类型
		response.setContentType("application/pdf");
		ServletOutputStream sos = response.getOutputStream();
		File file = FileUtil.getFile(mchtCloseApplication.getFilePath());
		///Assert.notNull(file, "终止合作协议还没生成，不能查看协议扫描件");
		response.setContentLength((int) file.length());
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024*1024];
		int readBytes = -1;
		while((readBytes = fis.read(buffer, 0, 1024*1024)) != -1){
			sos.write(buffer, 0, 1024*1024);
		}
		if(sos!=null){
			sos.close();
		}
		if(fis!=null){
			fis.close();
		}
	}
	
	/**
	 * 修改申请理由页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/mchtCloseApplication/toEditApplyReason")
	public String toEditApplyReason(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		return "mchtCloseApplication/toEditApplyReason";
	}
	
	/**
	 * 保存修改申请理由
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtCloseApplication/editApplyReason")
	@ResponseBody
	public ResponseMsg editApplyReason(HttpServletRequest request) {
		String id = request.getParameter("id");
		String applyReason = request.getParameter("applyReason");
		MchtCloseApplication mchtCloseApplication = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(id));
		mchtCloseApplication.setApplyReason(applyReason);
		mchtCloseApplication.setZsConfirmStatus("0");
		mchtCloseApplication.setUpdateBy(this.getSessionMchtInfo(request).getId());
		mchtCloseApplication.setUpdateDate(new Date());
		mchtCloseApplicationService.updateByPrimaryKeySelective(mchtCloseApplication);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 上传线上审核图片
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/mchtCloseApplication/toUploadProtocol")
	public String toUploadProtocol(Model model,HttpServletRequest request) {
		String mchtCloseId = request.getParameter("mchtCloseId");
		if(!StringUtils.isEmpty(mchtCloseId)){
			model.addAttribute("mchtCloseId",mchtCloseId);
			MchtCloseApplicationCustom mca = mchtCloseApplicationService.selectCustomByPrimaryKey(Integer.parseInt(mchtCloseId));
			model.addAttribute("mca",mca);
			MchtCloseApplicationPicExample mcapExample = new MchtCloseApplicationPicExample();
			mcapExample.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(Integer.parseInt(mchtCloseId));
			List<MchtCloseApplicationPic> mchtCloseApplicationPics = mchtCloseApplicationPicService.selectByExample(mcapExample );
			model.addAttribute("mchtCloseApplicationPics",mchtCloseApplicationPics);
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mca.getMchtId());
			model.addAttribute("mchtInfo",mchtInfo);

		}
		
		return "mchtCloseApplication/toUploadProtocol";
	}
	
	/**
	 * 保存上传线上审核图片
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/mchtCloseApplication/toSaveUploadProtocol")
	public String toSaveUploadProtocol(HttpServletRequest request) {
		String mchtCloseId = request.getParameter("mchtCloseId");
		String isNeedUpload = request.getParameter("isNeedUpload");
		if(!StringUtils.isEmpty(mchtCloseId)){
			MchtCloseApplicationPic mcap = new MchtCloseApplicationPic();
			MchtCloseApplication mca = mchtCloseApplicationService.selectByPrimaryKey(Integer.parseInt(mchtCloseId));
			if(!StringUtil.isBlank(isNeedUpload)){
				mca.setIsNeedUpload(isNeedUpload);
			}
			
			JSONArray imageArray = JSONArray.parseArray(getPara("images"));
			if(!"0".equals(isNeedUpload) &&( imageArray == null || imageArray.size() == 0)){
				throw new BizException("请上传合同扫描件");
			}
			if(imageArray.size() > 30){
				throw new BizException("请不要上传太多附件");
			}
			
			//提交上传图片
			mchtCloseApplicationService.commitUploadProtocol(imageArray,mca);
			
		}
			return json();
	}
	
	
	
	
}
