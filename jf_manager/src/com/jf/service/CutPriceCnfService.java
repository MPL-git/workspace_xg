package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.constant.Const;
import com.jf.entity.ProductUpperLowerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceCnfDtlCustomMapper;
import com.jf.dao.CutPriceCnfDtlMapper;
import com.jf.dao.CutPriceCnfMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.dao.ProductMapper;
import com.jf.dao.SingleProductActivityAuditLogMapper;
import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.Product;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityAuditLog;

@Service
@Transactional
public class CutPriceCnfService extends BaseService<CutPriceCnf, CutPriceCnfExample> {

	@Autowired
	private CutPriceCnfMapper cutPriceCnfMapper;
	
	@Autowired
	private CutPriceCnfDtlCustomMapper cutPriceCnfDtlCustomMapper;
	
	@Autowired
	private SingleProductActivityMapper singleProductActivityMapper;
	
	@Autowired
	private SingleProductActivityAuditLogMapper singleProductActivityAuditLogMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductItemMapper productItemMapper;
	
	@Autowired
	private CutPriceCnfDtlMapper cutPriceCnfDtlMapper;

	@Autowired
	private ProductUpperLowerLogService productUpperLowerLogService;
	@Autowired
	public void setCutPriceCnfMapper(CutPriceCnfMapper cutPriceCnfMapper) {
		super.setDao(cutPriceCnfMapper);
		this.cutPriceCnfMapper = cutPriceCnfMapper;
	}
	
	/**
	 * 
	 * @Title addOrUpdateCutPriceCnf   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月7日 下午2:40:55
	 */
	public void addOrUpdateCutPriceCnf(CutPriceCnf cutPriceCnf, List<CutPriceCnfDtl> cutPriceCnfDtlList) {
		if(cutPriceCnf.getId() == null) {
			cutPriceCnfMapper.insertSelective(cutPriceCnf);
			for(CutPriceCnfDtl cutPriceCnfDtl : cutPriceCnfDtlList) {
				cutPriceCnfDtl.setCutPriceCnfId(cutPriceCnf.getId());
			}
			if(cutPriceCnfDtlList != null && cutPriceCnfDtlList.size() > 0) {
				cutPriceCnfDtlCustomMapper.insertCutPriceCnfDtlList(cutPriceCnfDtlList);
			}
		}else {
			cutPriceCnfMapper.updateByPrimaryKeySelective(cutPriceCnf);
			cutPriceCnfDtlCustomMapper.updateCutPriceCnfDtlIdListSelective(cutPriceCnfDtlList);
		}
	}
	
	/**
	 * 
	 * @Title updateAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月7日 下午4:09:44
	 */
	public void updateAuditStatus(SingleProductActivity singleProductActivity, SingleProductActivityAuditLog singleProductActivityAuditLog, Product product) {
		singleProductActivityMapper.updateByPrimaryKeySelective(singleProductActivity);
		singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);

		//商品上下架日志
		ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
		productUpperLowerLog.setProductId(product.getId());
		productUpperLowerLog.setStatus(product.getStatus());
		productUpperLowerLog.setType(Const.PLATFORM);
		productUpperLowerLog.setOffReason(product.getOffReason());
		productUpperLowerLog.setCreateBy(product.getUpdateBy());
		productUpperLowerLog.setCreateDate(new Date());
		productUpperLowerLogService.insertSelective(productUpperLowerLog);

		//排期通过时修改——商品-上架-审核通过
		if("3".equals(singleProductActivity.getAuditStatus())) {
			productMapper.updateByPrimaryKeySelective(product);
			// STORY#877(砍价审核、邀请享免单审核，审核不需要在显示活动价格如图. 也不需要再更新到商品SKU中)
			/*if(singleProductActivity.getActivityPrice() != null) {
				ProductItemExample productItemExample = new ProductItemExample();
				productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
				ProductItem productItem = new ProductItem();
				productItem.setSalePrice(singleProductActivity.getActivityPrice());
				productItemMapper.updateByExampleSelective(productItem, productItemExample);
			}*/
		}
	}
	
	
	public void updateAuditStatusAndCutPriceCnf(SingleProductActivity singleProductActivity, SingleProductActivityAuditLog singleProductActivityAuditLog, Product product, CutPriceCnf cutPriceCnf, CutPriceCnfDtl cutPriceCnfDtl) {
		singleProductActivityMapper.updateByPrimaryKeySelective(singleProductActivity);
		singleProductActivityAuditLogMapper.insertSelective(singleProductActivityAuditLog);
		//排期通过时修改——商品-上架-审核通过
		if("3".equals(singleProductActivity.getAuditStatus())) {
			productMapper.updateByPrimaryKeySelective(product);
		}
		if(cutPriceCnf != null) {
			cutPriceCnfMapper.updateByPrimaryKeySelective(cutPriceCnf);
		}
		if(cutPriceCnfDtl != null) {
			cutPriceCnfDtlMapper.updateByPrimaryKeySelective(cutPriceCnfDtl);
		}
	}
	
	
}
