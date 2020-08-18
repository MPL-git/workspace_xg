package com.jf.controller.command;

import com.jf.common.constant.Const;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.util.DateTimeUtil;
import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtInfo;
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
 * 提交关店申请
 *
 * @author huangdl
 */
public class CMchtCloseApplyRequestCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtCloseApplyRequestCommit.class);

	@Resource
	private MchtCloseApplyService mchtCloseApplyService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private PlatformContactService platformContactService;


	private MchtCloseApply param;

	private StaffBean currentUser;

	@Override
	public void init() {
		param = getBean(MchtCloseApply.class, "model");
		Assert.moreThanZero(param.getMchtId(), "商家ID不能为空");
		Assert.notBlank(param.getRequestRemarks(), "请填写申请理由");

		Assert.notNull(param.getStopBeginDate(), "暂停日期1不能为空");
		if(param.getStopBeginDate().before(DateTimeUtil.getStartTimeOfDate(new Date()))){
			throw new BizException("暂停日期1必须大于等于今天");
		}

		if(Const.FLAG_TRUE.equals(param.getCloseFlag())){
			// 关店时
			if(param.getStopEndDate() != null){
				throw new BizException("暂停日期2必须为空");
			}
			Assert.notNull(param.getCloseBeginDate(), "关店日期1不能为空");
			if(param.getCloseBeginDate().before(param.getStopBeginDate())){
				throw new BizException("关店日期1必须大于暂停日期1 ");
			}

			if(param.getCloseEndDate() != null && param.getCloseEndDate().before(param.getCloseBeginDate())){
				throw new BizException("关店日期2必须大于关店日期1 ");
			}
		}else{
			// 暂停时
			param.setCloseFlag(Const.FLAG_FALSE);
			if(param.getStopEndDate() != null && param.getStopEndDate().before(param.getStopBeginDate())){
				throw new BizException("暂停日期2必须大于暂停日期1 ");
			}

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
		MchtInfo mchtInfo = mchtInfoService.findById(param.getMchtId());
		Assert.notNull(mchtInfo, "商家不存在");

		boolean hasPermit = false; 	//是否有操作权限
		PlatformContact platformContact = platformContactService.findByMchtId(mchtInfo.getId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
		if(platformContact != null && currentUser.getStaffID().equals(String.valueOf(platformContact.getStaffId()))){
			hasPermit = true;
		}
		if(!hasPermit){
			platformContact = platformContactService.findByMchtId(mchtInfo.getId(), Const.PLAT_CONTACT_TYPE_OPERATION);
			if(platformContact != null && currentUser.getStaffID().equals(String.valueOf(platformContact.getStaffId()))){
				hasPermit = true;
			}
		}
		if(!hasPermit){
			platformContact = platformContactService.findByMchtId(mchtInfo.getId(), Const.PLAT_CONTACT_TYPE_FAWU);
			if(platformContact != null && currentUser.getStaffID().equals(String.valueOf(platformContact.getStaffId()))){
				hasPermit = true;
			}
		}
		if(!hasPermit){
			throw new BizException("商家的招商、运营或法务对接人才可以进行本操作");
		}



		if(mchtCloseApplyService.findByMchtId(param.getMchtId()) != null){
			throw new BizException("该商家的关店申请已存在！");
		}

		if(Const.MCHT_STATUS_CLOSED.equals(mchtInfo.getStatus())){
			throw new BizException("该商家已关店，不用重复申请");
		}

		if(Const.MCHT_STATUS_ING.equals(mchtInfo.getStatus())){
			throw new BizException("入驻中的无需申请，可直接关店");
		}

		MchtCloseApply model = new MchtCloseApply();
		model.setMchtId(mchtInfo.getId());
		model.setStopBeginDate(param.getStopBeginDate());
		model.setStopEndDate(param.getStopEndDate());
		model.setCloseFlag(param.getCloseFlag());
		if(Const.FLAG_TRUE.equals(param.getCloseFlag())){
			model.setCloseBeginDate(param.getCloseBeginDate());
			model.setCloseEndDate(param.getCloseEndDate());
		}

		model.setRequestBy(Integer.valueOf(currentUser.getStaffID()));
		model.setRequestType(Const.PLAT_CONTACT_TYPE_MERCHANTS);
		model.setRequestDate(new Date());
		model.setRequestRemarks(param.getRequestRemarks());

		model.setStatus(Const.MCHT_CLOSE_APPLY_STATUS_WAIT);
		model.setCreateBy(Integer.valueOf(currentUser.getStaffID()));
		mchtCloseApplyService.save(model);
	}

}
