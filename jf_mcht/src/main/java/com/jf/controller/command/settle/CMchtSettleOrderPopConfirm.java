package com.jf.controller.command.settle;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jf.common.enums.InvoiceTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountExample;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.service.MchtBankAccountService;
import com.jf.service.MchtContactService;
import com.jf.service.MchtSettleOrderService;

/**
 * POP结算单商家确认
 *
 * @author huangdl
 */
public class CMchtSettleOrderPopConfirm extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderPopConfirm.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private MchtBankAccountService mchtBankAccountService;

	private int id;

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "结算ID不能为空");

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder model = mchtSettleOrderService.findById(id);
		if(model == null || !model.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}
		data.put("model", model);
		data.put("invoiceTypeList", InvoiceTypeEnum.list);	// 开票类型
		MchtContactExample example = new MchtContactExample();
		example.setOrderByClause("is_primary,id desc");
		MchtContactExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(currentUser.getMchtId());
		criteria.andContactTypeEqualTo("5");//财务对接人
		List<MchtContact> mchtContacts = mchtContactService.selectByExample(example);
		if(mchtContacts!=null && mchtContacts.size()>0){
			data.put("contactName", mchtContacts.get(0).getContactName());	
			data.put("mobile", mchtContacts.get(0).getMobile());	
			data.put("address", mchtContacts.get(0).getAddress());	
			data.put("provinceId", mchtContacts.get(0).getProvince());	
			data.put("cityId", mchtContacts.get(0).getCity());	
			data.put("countyId", mchtContacts.get(0).getCounty());	
		}else{
			data.put("provinceId", 0);	
			data.put("cityId", 0);	
			data.put("countyId", 0);
		}
		
		MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
		mchtBankAccountExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(currentUser.getMchtId());
		mchtBankAccountExample.setOrderByClause(" id desc");
		mchtBankAccountExample.setLimitStart(0);
		mchtBankAccountExample.setLimitSize(1);
		List<MchtBankAccountCustom> mchtBankAccountCustoms=mchtBankAccountService.selectMchtBankAccountCustomByExample(mchtBankAccountExample);
		if(mchtBankAccountCustoms!=null&&mchtBankAccountCustoms.size()>0){
			data.put("mchtBankAccount", mchtBankAccountCustoms.get(0));
		}else{
			data.put("mchtBankAccount", new MchtBankAccountCustom());
		}
	}

}
