package com.jf.controller.command.settle;

import com.jf.common.enums.InvoiceTypeEnum;
import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.entity.Area;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.service.AreaService;
import com.jf.service.MchtSettleOrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * POP结算单商家确认（提交）
 *
 * @author huangdl
 */
public class CMchtSettleOrderPopConfirmCommit extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderPopConfirmCommit.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	@Resource
	private AreaService areaService;

	private int id;
	private int mchtCollectType;	// 开票类型
	private String mchtReceiverName;	// 财务收货人
	private String mchtReceiverPhone;	// 收货人电话
	private String mchtReceiverAddress;	// 收货人地址

	private int provinceId;	//省
	private int cityId;	//市
	private int countyId;	//县

	private MchtUser currentUser;

	@Override
	public void init() {
		id = getParaToInt("id");
		Assert.moreThanZero(id, "结算ID不能为空");

		mchtCollectType = getParaToInt("mchtCollectType");
		if(!InvoiceTypeEnum.contains(mchtCollectType)){
			throw new BizException("不支持的开票类型");
		}
		if(mchtCollectType!=InvoiceTypeEnum.NOT.getValue()){
			mchtReceiverName = getPara("mchtReceiverName");
			Assert.notBlank(mchtReceiverName, "财务收货人不能为空");
			
			mchtReceiverPhone = getPara("mchtReceiverPhone");
			Assert.notBlank(mchtReceiverPhone, "收货人电话不能为空");
			
			mchtReceiverAddress = getPara("mchtReceiverAddress");
			Assert.notBlank(mchtReceiverAddress, "收货人地址不能为空");
			
			provinceId = getParaToInt("province");
			Assert.moreThanZero(provinceId, "省份不能为空");
			cityId = getParaToInt("city");
			Assert.moreThanZero(cityId, "城市不能为空");
			countyId = getParaToInt("county");
			Assert.moreThanZero(countyId, "区县不能为空");
		}

		currentUser = context.getUserInfo();
	}

	@Override
	public void doCommand() {
		MchtSettleOrder model = mchtSettleOrderService.findById(id);
		if(model == null || !model.getMchtId().equals(currentUser.getMchtId())){
			throw new BizException("未找到该结算单");
		}
		model.setMchtCollectType(String.valueOf(mchtCollectType));
		if(mchtCollectType!=InvoiceTypeEnum.NOT.getValue()){
			Area province = areaService.selectByPrimaryKey(provinceId);
			Area city = areaService.selectByPrimaryKey(cityId);
			Area county = areaService.selectByPrimaryKey(countyId);
			mchtReceiverAddress = province.getAreaName() + city.getAreaName() + county.getAreaName() + mchtReceiverAddress;
			model.setMchtReceiverName(mchtReceiverName);
			model.setMchtReceiverPhone(mchtReceiverPhone);
			model.setMchtReceiverAddress(mchtReceiverAddress);
		}
		mchtSettleOrderService.update(model);
		mchtSettleOrderService.mchtConfirmed(id);
	}

}
