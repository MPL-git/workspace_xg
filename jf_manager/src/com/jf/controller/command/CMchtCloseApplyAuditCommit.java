package com.jf.controller.command;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.PlatformContact;
import com.jf.entity.StaffBean;
import com.jf.service.MchtCloseApplyService;
import com.jf.service.MchtInfoService;
import com.jf.service.PlatformContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 关店审核
 *
 * @author huangdl
 */
public class CMchtCloseApplyAuditCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtCloseApplyAuditCommit.class);

	@Resource
	private MchtCloseApplyService mchtCloseApplyService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private PlatformContactService platformContactService;


	private MchtCloseApply param;


	private MchtCloseApply model;
	private StaffBean currentUser;

	@Override
	public void init() {
		param = getBean(MchtCloseApply.class, "model");
		Assert.moreThanZero(param.getId(), "ID不能为空");

		model = mchtCloseApplyService.findById(param.getId());
		Assert.notNull(model, "关店申请记录不存在");

		Assert.notNull(param.getStopBeginDate(), "暂停日期1不能为空");
		if(param.getStopBeginDate().before(DateTimeUtil.getStartTimeOfDate(new Date()))){
			throw new BizException("暂停日期1必须大于等于今天");
		}

		if(Const.FLAG_TRUE.equals(model.getCloseFlag())){
			// 关店时
			if(param.getStopEndDate() != null){
				throw new BizException("暂停日期2必须为空");
			}
			Assert.notNull(param.getCloseBeginDate(), "关店日期1不能为空");
			
			if(param.getCloseBeginDate().compareTo(param.getStopBeginDate()) < 1){
				throw new BizException("关店日期1必须大于暂停日期1 ");
			}
//			if(param.getCloseBeginDate().before(param.getStopBeginDate())){
//				throw new BizException("关店日期1必须大于暂停日期1 ");
//			}

			if(param.getCloseEndDate() != null && param.getCloseEndDate().compareTo(param.getCloseBeginDate()) < 1){
				throw new BizException("关店日期2必须大于关店日期1 ");
			}
//			if(param.getCloseEndDate() != null && param.getCloseEndDate().before(param.getCloseBeginDate())){
//				throw new BizException("关店日期2必须大于关店日期1 ");
//			}
		}else{
			// 暂停时
			if(param.getStopEndDate() != null && param.getStopEndDate().compareTo(param.getStopBeginDate()) < 1){
				throw new BizException("暂停日期2必须大于暂停日期1 ");
			}
//			if(param.getStopEndDate() != null && param.getStopEndDate().before(param.getStopBeginDate())){
//				throw new BizException("暂停日期2必须大于暂停日期1 ");
//			}

			if(param.getCloseBeginDate() != null){
				throw new BizException("关店日期1必须为空");
			}
			if(param.getCloseEndDate() != null){
				throw new BizException("关店日期2必须为空");
			}
		}

		currentUser = context.getSessionStaffBean();
	}

	@Override
	public void doCommand() {
		boolean commit = false;

		// 招商对接人
		PlatformContact merchantsContact = platformContactService.findByMchtId(model.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
		if(merchantsContact != null && currentUser.getStaffID().equals(String.valueOf(merchantsContact.getStaffId()))){
			commit = true;
			Assert.notBlank(param.getMerchantsAuditStatus(), "请选择招商意见");
			if(param.getMerchantsAuditStatus().equals(Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT)){
				Assert.notBlank(param.getMerchantsAuditRemarks(), "请填写招商说明");
			}

			model.setStopBeginDate(param.getStopBeginDate());
			model.setStopEndDate(param.getStopEndDate());
			if(Const.FLAG_TRUE.equals(model.getCloseFlag())){
				model.setCloseBeginDate(param.getCloseBeginDate());
				model.setCloseEndDate(param.getCloseEndDate());
			}

			model.setMerchantsAuditBy(Integer.valueOf(currentUser.getStaffID()));
			model.setMerchantsAuditStatus(param.getMerchantsAuditStatus());
			model.setMerchantsAuditDate(new Date());
			model.setMerchantsAuditRemarks(param.getMerchantsAuditRemarks());

			if(Integer.valueOf(model.getOperateAuditStatus()) > 0){
				model.setStatus(Const.MCHT_CLOSE_APPLY_STATUS_AUDITED);
			}
			model.setUpdateBy(Integer.valueOf(currentUser.getStaffID()));
			mchtCloseApplyService.updateMchtCloseApplyStatus(model, Const.PLAT_CONTACT_TYPE_MERCHANTS);


		}


		// 运营对接人
		PlatformContact operateContact = platformContactService.findByMchtId(model.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
		if(operateContact != null && currentUser.getStaffID().equals(String.valueOf(operateContact.getStaffId()))){
			commit = true;
			Assert.notBlank(param.getOperateAuditStatus(), "请选择运营意见");
			if(param.getOperateAuditStatus().equals(Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_REJECT)){
				Assert.notBlank(param.getOperateAuditRemarks(), "请填写运营说明");
			}

			model.setStopBeginDate(param.getStopBeginDate());
			model.setStopEndDate(param.getStopEndDate());
			if(Const.FLAG_TRUE.equals(model.getCloseFlag())){
				model.setCloseBeginDate(param.getCloseBeginDate());
				model.setCloseEndDate(param.getCloseEndDate());
			}

			model.setOperateAuditBy(Integer.valueOf(currentUser.getStaffID()));
			model.setOperateAuditStatus(param.getOperateAuditStatus());
			model.setOperateAuditDate(new Date());
			model.setOperateAuditRemarks(param.getOperateAuditRemarks());

			if(Integer.valueOf(model.getMerchantsAuditStatus()) > 0){
				model.setStatus(Const.MCHT_CLOSE_APPLY_STATUS_AUDITED);
			}
			model.setUpdateBy(Integer.valueOf(currentUser.getStaffID()));
			mchtCloseApplyService.updateMchtCloseApplyStatus(model, Const.PLAT_CONTACT_TYPE_OPERATION);
		}

		if(!commit){
			throw new BizException("招商本人或运营本人才可以提交");
		}

		// 如果暂停日期为今天，执行暂停
		if(Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS.equals(model.getOperateAuditStatus())
				&& Const.MCHT_CLOSE_APPLY_AUDIT_STATUS_PASS.equals(model.getMerchantsAuditStatus())
				&& DateTimeUtil.isToday(model.getStopBeginDate())){
			mchtCloseApplyService.exeStopBegin(currentUser.getStaffID(), model.getId());
		}

	}

}
