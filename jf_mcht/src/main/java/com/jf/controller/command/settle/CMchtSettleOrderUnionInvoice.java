package com.jf.controller.command.settle;

import com.jf.common.ext.core.ABaseCommand;
import com.jf.common.ext.exception.Assert;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.QueryObject;
import com.jf.entity.MchtSettleOrder;
import com.jf.entity.MchtUser;
import com.jf.entity.SysParamCfg;
import com.jf.service.ExpressService;
import com.jf.service.MchtSettleOrderService;
import com.jf.service.SysParamCfgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SPOP结算结算单商家开票
 *
 * @author huangdl
 */
public class CMchtSettleOrderUnionInvoice extends ABaseCommand {

    private static final Log logger = LogFactory.getLog(CMchtSettleOrderUnionInvoice.class);

	@Resource
	private MchtSettleOrderService mchtSettleOrderService;
	@Resource
	private ExpressService expressService;
	@Resource
	private SysParamCfgService sysParamCfgService;

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

		// 平台开票配置
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("paramCode%", "PTKP_");
		List<SysParamCfg> ptkpList = sysParamCfgService.findList(queryObject);
		Map<String, String> ptkpMap = new HashMap<>();
		for(SysParamCfg cfg : ptkpList){
			ptkpMap.put(cfg.getParamCode(), cfg.getParamValue());
		}
		data.put("ptkpMap", ptkpMap);

		// 快递公司
		data.put("expressList", expressService.findList(new QueryObject()));
	}

}
