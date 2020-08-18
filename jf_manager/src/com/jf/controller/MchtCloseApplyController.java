package com.jf.controller;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseController;
import com.jf.common.ext.exception.Assert;
import com.jf.controller.command.CMchtCloseApplyAuditCommit;
import com.jf.controller.command.CMchtCloseApplyList;
import com.jf.controller.command.CMchtCloseApplyRequestCommit;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtInfo;
import com.jf.entity.PlatformContact;
import com.jf.entity.StaffBean;
import com.jf.service.MchtCloseApplyService;
import com.jf.service.MchtInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MchtCloseApplyController extends ABaseController {
	private static final long serialVersionUID = 1L;

	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private MchtCloseApplyService mchtCloseApplyService;
	@Resource
	private StatusService statusService;
	@Resource
	private PlatformContactService platformContactService;



	/**
	 * 关店申请列表页
	 */
	@RequestMapping(value = "/mchtCloseApply/list.shtml")
	public ModelAndView list() {
		String page = "/mchtCloseApply/list";
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("merchantsAuditStatusList", Const.getMchtCloseApplyAuditStatusList());	//招商审核状态
		data.put("operateAuditStatusList", Const.getMchtCloseApplyAuditStatusList());	//运营审核状态
		data.put("mchtInfoStatusList", statusService.querytStatusList("BU_MCHT_INFO", "STATUS"));	//商家合作状态
		return new ModelAndView(page, data);
	}


	/**
	 * 关店申请列表数据
	 */
	@RequestMapping(value = "/mchtCloseApply/listData.shtml")
	@ResponseBody
	public String listData() {

		return json(doAction(new CMchtCloseApplyList()));
	}

	/**
	 * 查看关店信息
	 */
	@RequestMapping(value = "/mchtCloseApply/preview.shtml")
	public ModelAndView preview() {
		String page = "/mchtCloseApply/preview";
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "请填写ID");

		MchtCloseApply model = mchtCloseApplyService.findById(id);
		Assert.notNull(model, "关店申请记录不存在");
		model.put("stopBeginStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getStopBeginStatus()));	//暂停开始执行状态
		model.put("stopEndStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getStopEndStatus()));	//暂停结束执行状态
		model.put("closeBeginStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getCloseBeginStatus()));	//关店开始执行状态
		model.put("closeEndStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getCloseEndStatus()));	//关店结束执行状态

		model.put("merchantsAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(model.getMerchantsAuditStatus()));	//招商审核状态
		model.put("operateAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(model.getOperateAuditStatus()));	//运营审核状态

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		return new ModelAndView(page, data);
	}

	/**
	 * 申请关店
	 */
	@RequestMapping(value = "/mchtCloseApply/request.shtml")
	public ModelAndView request() {
		String page = "/mchtCloseApply/request";
		int mchtId = getParaToInt("mchtId");
		Assert.moreThanZero(mchtId, "请输入商家ID");

		MchtInfo mchtInfo = mchtInfoService.findById(mchtId);
		Assert.notNull(mchtInfo, "商家不存在");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("mchtInfoStatus", mchtInfo.getStatus());
		return new ModelAndView(page, data);
	}

	/**
	 * 申请关店提交
	 */
	@RequestMapping(value = "/mchtCloseApply/requestCommit.shtml")
	@ResponseBody
	public String requestCommit() {

		return json(doAction(new CMchtCloseApplyRequestCommit()));
	}

	/**
	 * 关店审核
	 */
	@RequestMapping(value = "/mchtCloseApply/audit.shtml")
	public ModelAndView audit() {
		String page = "/mchtCloseApply/audit";
		int id = getParaToInt("id");
		Assert.moreThanZero(id, "请填写ID");

		MchtCloseApply model = mchtCloseApplyService.findById(id);
		Assert.notNull(model, "关店申请记录不存在");
		model.put("stopBeginStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getStopBeginStatus()));	//暂停开始执行状态
		model.put("stopEndStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getStopEndStatus()));	//暂停结束执行状态
		model.put("closeBeginStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getCloseBeginStatus()));	//关店开始执行状态
		model.put("closeEndStatusStr", Const.getMchtCloseApplyExeStatusStr(model.getCloseEndStatus()));	//关店结束执行状态

		model.put("merchantsAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(model.getMerchantsAuditStatus()));	//招商审核状态
		model.put("operateAuditStatusStr", Const.getMchtCloseApplyAuditStatusStr(model.getOperateAuditStatus()));	//运营审核状态

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);

		StaffBean currentUser = getSessionStaffBean();
		PlatformContact merchantsContact = platformContactService.findByMchtId(model.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
		PlatformContact operateContact = platformContactService.findByMchtId(model.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
		data.put("isMyMerchants", merchantsContact!=null && merchantsContact.getStaffId().equals(Integer.valueOf(currentUser.getStaffID())));
		data.put("isMyOperate", operateContact!=null && operateContact.getStaffId().equals(Integer.valueOf(currentUser.getStaffID())));

		return new ModelAndView(page, data);
	}

	/**
	 * 关店审核提交
	 */
	@RequestMapping(value = "/mchtCloseApply/auditCommit.shtml")
	@ResponseBody
	public String auditCommit() {

		return json(doAction(new CMchtCloseApplyAuditCommit()));
	}

}
