package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.CutPriceOrderMapper;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CutPriceOrderService extends BaseService<CutPriceOrder, CutPriceOrderExample> {
	@Autowired
	private CutPriceOrderMapper cutPriceOrderMapper;
	@Autowired
	private CutPriceOrderLogService cutPriceOrderLogService;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private ProductService productService;
	@Autowired
	public void setCutPriceOrderMapper(CutPriceOrderMapper cutPriceOrderMapper) {
		this.setDao(cutPriceOrderMapper);
		this.cutPriceOrderMapper = cutPriceOrderMapper;
	}
	public void updateActivationCutOrder(Integer cutOrderId, String activityType) {
		CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
		cutPriceOrderExample.createCriteria().andStatusEqualTo("0").andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
		List<CutPriceOrder> orders = selectByExample(cutPriceOrderExample);
		if(CollectionUtils.isNotEmpty(orders)){
			Date currentDate = new Date();
			CutPriceOrder order = orders.get(0);
			com.jf.entity.Product p = productService.selectByPrimaryKey(order.getProductId());
			productItemService.updateSkuLockedAmount(order.getProductItemId(),1,p.getMchtId());
			String status = "1";
			order.setStatus(status);
			order.setUpdateBy(order.getMemberId());
			order.setUpdateDate(currentDate);
			int count = updateByExampleSelective(order, cutPriceOrderExample);
			if(count <= 0){
				throw new ArgException("系统异常，请稍后再试");
			}
			cutPriceOrderLogService.addCutPriceOrderLog(order.getMemberId(), cutOrderId, status, "助力大减价激活成功");
		}else{
			throw new ArgException("请先领取商品减价任务");
		}
	}
	private List<CutPriceOrder> findModelByStatus(List<String> statusList, Integer cutOrderId) {
		CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
		cutPriceOrderExample.createCriteria().andStatusIn(statusList).andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
		return selectByExample(cutPriceOrderExample);
	}
	
}
