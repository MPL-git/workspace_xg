package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.FreightTemplateMapper;
import com.jf.entity.FreightTemplate;
import com.jf.entity.FreightTemplateExample;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.ProvinceFreight;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FreightTemplateService extends BaseService<FreightTemplate, FreightTemplateExample> {
	@Autowired
	private FreightTemplateMapper freightTemplateMapper;
	@Autowired
	private ProvinceFreightService provinceFreightService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	public void setFreightTemplateMapper(FreightTemplateMapper freightTemplateMapper) {
		this.setDao(freightTemplateMapper);
		this.freightTemplateMapper = freightTemplateMapper;
	}
	
	public Map<String, Object> getProductFreightTemplate(JSONObject reqDataJson) {
		Integer productId = null;
		String productCode = "";
		Integer provinceId = null;
		Product p = null;
		ProductExample productExample = new ProductExample();
		ProductExample.Criteria criteria = productExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if(reqDataJson.containsKey("provinceId") && !StringUtil.isBlank(reqDataJson.getString("provinceId"))){
			provinceId = reqDataJson.getInt("provinceId");
		}
		if(reqDataJson.containsKey("productId") && !StringUtil.isBlank(reqDataJson.getString("productId"))){
			productId = reqDataJson.getInt("productId");
			criteria.andIdEqualTo(productId);
		}else if(reqDataJson.containsKey("productCode") && !StringUtil.isBlank(reqDataJson.getString("productCode"))){
			productCode = reqDataJson.getString("productCode");
			criteria.andCodeEqualTo(productCode);
		}else{
			throw new ArgException("商品id不能为空");
		}
		List<Product> products = productService.selectByExample(productExample);
		BigDecimal firstFreight = new BigDecimal("0");
		BigDecimal additionalFreight = new BigDecimal("0");
		BigDecimal zero = new BigDecimal("0");
		String provinceName = "";
		if(CollectionUtils.isNotEmpty(products)){
			p = products.get(0);
			if(provinceId == null){//前端没有默认省份的发送地
				provinceId = 110000;
				provinceName = "北京市";
			}
			Integer freightTemplateId = p.getFreightTemplateId();//运费模板id
			if(freightTemplateId != null){
				FreightTemplate freightTemplate = findModelById(freightTemplateId);
				if(freightTemplate != null){
					//找到模板
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("freightTemplateId", freightTemplateId);
					pMap.put("provinceId", provinceId);
					ProvinceFreight provinceFreight = provinceFreightService.getProvinceFreightTemplate(pMap);
					if(provinceFreight != null && provinceFreight.getId() != null){
						firstFreight = provinceFreight.getFirstFreight();
						additionalFreight = provinceFreight.getAdditionalFreight();
					}else{
						firstFreight = freightTemplate.getFirstFreight();
						additionalFreight = freightTemplate.getAdditionalFreight();
					}
				}
			}
		}
		String freight = "";
		if(firstFreight.compareTo(zero) > 0){
			//首件M元增件N元
			freight = firstFreight+"元";
			if(additionalFreight.compareTo(zero) > 0){
				freight = "首件"+firstFreight+"元增件"+additionalFreight+"元";
			}
		}else{
			freight = "免运费";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		map.put("provinceName", provinceName);
		map.put("freight", freight);
		return map;
	}

	public FreightTemplate findModelById(Integer freightTemplateId) {
		FreightTemplate freightTemplate = null;
		FreightTemplateExample freightTemplateExample = new FreightTemplateExample();
		freightTemplateExample.createCriteria().andIdEqualTo(freightTemplateId).andDelFlagEqualTo("0");
		List<FreightTemplate> list = selectByExample(freightTemplateExample);
		if(CollectionUtils.isNotEmpty(list)){
			freightTemplate = list.get(0);
		}
		return freightTemplate;
	}
	
	
}
