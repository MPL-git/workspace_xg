package com.jf.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProvinceFreightCustomMapper;
import com.jf.dao.ProvinceFreightMapper;
import com.jf.entity.FreightTemplate;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import com.jf.entity.ProvinceFreight;
import com.jf.entity.ProvinceFreightCustom;
import com.jf.entity.ProvinceFreightExample;

@Service
@Transactional
public class ProvinceFreightService extends BaseService<ProvinceFreight, ProvinceFreightExample> {
	@Autowired
	private ProvinceFreightMapper provinceFreightMapper;
	@Autowired
	private ProvinceFreightCustomMapper provinceFreightCustomMapper;
	@Autowired
	private FreightTemplateService freightTemplateService;
	@Autowired
	private MemberAddressService memberAddressService;

	@Autowired
	public void setProvinceFreightMapper(ProvinceFreightMapper provinceFreightMapper) {
		this.setDao(provinceFreightMapper);
		this.provinceFreightMapper = provinceFreightMapper;
	}

	public ProvinceFreight getProvinceFreightTemplate(Map<String, Object> pMap) {
		
		return provinceFreightCustomMapper.getProvinceFreightTemplate(pMap);
	}
	
	public Map<String, Object> getProductFreightAmount(List<Map<String, Object>> freights, Integer provinceId) {
		BigDecimal zero = new BigDecimal("0");
		Map<Integer, ProvinceFreightCustom> customMap = new HashMap<Integer, ProvinceFreightCustom>();
		for (Map<String, Object> map : freights) {
			Integer freightTemplateId = Integer.valueOf(map.get("freightTemplateId").toString());
			if(!customMap.containsKey(freightTemplateId)){
				ProvinceFreightCustom freightCustom = new ProvinceFreightCustom();
				BigDecimal firstFreight = zero;
				BigDecimal additionalFreight = zero;
				if(freightTemplateId != null){
					FreightTemplate freightTemplate = freightTemplateService.selectByPrimaryKey(freightTemplateId);
					if(freightTemplate != null){
						firstFreight = freightTemplate.getFirstFreight() == null ? zero : freightTemplate.getFirstFreight();
						additionalFreight = freightTemplate.getAdditionalFreight() == null ? zero : freightTemplate.getAdditionalFreight();
						//找到模板
						Map<String, Object> pMap = new HashMap<String, Object>();
						pMap.put("freightTemplateId", freightTemplateId);
						pMap.put("provinceId", provinceId);
						ProvinceFreight provinceFreight = getProvinceFreightTemplate(pMap);
						if(provinceFreight != null && provinceFreight.getId() != null){
							firstFreight = provinceFreight.getFirstFreight() == null ? zero : provinceFreight.getFirstFreight();
							additionalFreight = provinceFreight.getAdditionalFreight() == null ? zero : provinceFreight.getAdditionalFreight();
						}
					}
				}
				String freightTemplateName = "";
				if(firstFreight.compareTo(zero) > 0){
					freightTemplateName = "首件"+firstFreight+"元";
					if(additionalFreight.compareTo(zero) > 0){
						freightTemplateName = freightTemplateName+"增件"+additionalFreight+"元";
					}
				}else{
					freightTemplateName = "免运费";
				}
				freightCustom.setFreightTemplateId(freightTemplateId);
				freightCustom.setFirstFreight(firstFreight);
				freightCustom.setAdditionalFreight(additionalFreight);
				freightCustom.setFreightTemplateName(freightTemplateName);
				customMap.put(freightTemplateId, freightCustom);
			}
		}
		
		Map<String, Map<Integer, Integer>> freightMap = new HashMap<String, Map<Integer, Integer>>();
		Map<Integer, Integer> dataMap = new HashMap<Integer, Integer>();
		for (Map<String, Object> map : freights) {
			Integer productItemId = (Integer) map.get("productItemId");
			Integer quantity = (Integer) map.get("quantity");
			Integer freightTemplateId = Integer.valueOf(map.get("freightTemplateId").toString());
			if(freightTemplateId != null){
				if(freightMap.containsKey(freightTemplateId.toString())){
					dataMap = freightMap.get(freightTemplateId.toString());
					if(dataMap.containsKey(productItemId)){
						quantity = dataMap.get(productItemId)+quantity;
					}
				}else{
					dataMap = new HashMap<Integer, Integer>();
				}
				dataMap.put(productItemId, quantity);
				freightMap.put(freightTemplateId.toString(), dataMap);
			}
		}
		
		Map<Integer, BigDecimal> productItemFreightMap = new HashMap<Integer, BigDecimal>();//sku对应的运费
		Map<Integer, String> freightContentMap = new HashMap<Integer, String>();//sku对应的运费
		BigDecimal taotalFreightTemplateAmount = zero;//所有模板的总运费
		for (String key : freightMap.keySet()) {
			int i = 0;
			BigDecimal freightTemplateTotalFreight = zero;//计算每个运费模板的总运费
			Integer freightTemplateTotalQuantity = 0;//获取该模板的所有商品购买数量
			BigDecimal productItemFreight = zero;//计算每只sku的运费
			ProvinceFreightCustom freightCustom = customMap.get(Integer.valueOf(key));
			BigDecimal firstFreight = freightCustom.getFirstFreight() == null ? zero : freightCustom.getFirstFreight();
			BigDecimal additionalFreight = freightCustom.getAdditionalFreight() == null ? zero : freightCustom.getAdditionalFreight();
			String freightType = "1";//1包邮 2不包邮
			Map<Integer, Integer> m = freightMap.get(key);
			Integer skuId = null;
			for (Integer productItemId : m.keySet()) {
				skuId = productItemId;
				freightTemplateTotalQuantity = m.get(productItemId) + freightTemplateTotalQuantity;
			}
			if(freightTemplateTotalQuantity == 1){
				//一件：模板总运费 = 首件运费
				freightTemplateTotalFreight = firstFreight;
				productItemFreight = firstFreight;
				productItemFreightMap.put(skuId, productItemFreight);
				if(firstFreight.compareTo(zero) > 0){//首件有运费表示不包邮
					freightType = "2";
				}else{
					freightType = "1";
				}
				freightContentMap.put(skuId, freightType);
			}else{
				//多件：模板总运费 = 首件运费+（增件运费*（数量-1））
				BigDecimal productFreight = firstFreight.add(additionalFreight.multiply(new BigDecimal(freightTemplateTotalQuantity-1)));
				for (Integer productItemId : m.keySet()) {
					i++;
					Double productItemQuantity = m.get(productItemId).doubleValue();
					Double quantityRate = productItemQuantity / freightTemplateTotalQuantity;
					if(i == m.size()){
						productItemFreight = productFreight.subtract(freightTemplateTotalFreight);
						freightTemplateTotalFreight = freightTemplateTotalFreight.add(productItemFreight);
					}else{
						productItemFreight = productFreight.multiply(new BigDecimal(quantityRate)).setScale(1, BigDecimal.ROUND_DOWN);
						freightTemplateTotalFreight = freightTemplateTotalFreight.add(productItemFreight);
					}
					productItemFreightMap.put(productItemId, productItemFreight);
					if(firstFreight.compareTo(zero) > 0){//首件有运费表示不包邮
						freightType = "2";
					}else{
						freightType = "1";
					}
					freightContentMap.put(productItemId, freightType);
				}
			}
			//所有模板的总运费 = 所有模板的总运费+每只sku商品的运费，设置key值为-1
			taotalFreightTemplateAmount = taotalFreightTemplateAmount.add(freightTemplateTotalFreight);
			freightCustom.setFreightTemplateTotalFreight(freightTemplateTotalFreight);
			customMap.put(Integer.valueOf(key), freightCustom);
		}
		productItemFreightMap.put(-1, taotalFreightTemplateAmount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("freightCustom", customMap);//用途：获取每个模板的名称和每个模板的总运费
		map.put("productItemFreightMap", productItemFreightMap);//用途：每只sku商品所需运费，此处设置key = -1，即表示为所有模板的总运费
		map.put("freightContentMap", freightContentMap);//用途：判断是否包邮不包邮
		return map;
	}

	@SuppressWarnings("unchecked")
	public boolean getReckonFreightIsSame(List<Map<String, Object>> freights, Integer provinceId,BigDecimal differValue, Integer memberId, BigDecimal payAmount, BigDecimal totalFreight, BigDecimal totalPayAmount) {
		//再次判断默认运费地址是否一致
		//旧版本获取默认模板
		//totalFreight 是APP端传送过来省份所对应的总运费金额
		boolean isSameFreight = false;
		BigDecimal zero = new BigDecimal("0");
		MemberAddressExample memberAddressExample = new MemberAddressExample();
		memberAddressExample.createCriteria().andMemberIdEqualTo(memberId).andIsPrimaryEqualTo("Y").andDelFlagEqualTo("0");
		List<MemberAddress> defaultMemberAddrs = memberAddressService.selectByExample(memberAddressExample);
		Integer defaultProvinceId = null;
		if(CollectionUtils.isNotEmpty(defaultMemberAddrs)){
			defaultProvinceId = defaultMemberAddrs.get(0).getProvince();
		}
		if(defaultProvinceId != null && !provinceId.equals(defaultProvinceId)){
			Map<String, Object> totalDefaultFreightMap = getProductFreightAmount(freights, defaultProvinceId);
			Map<Integer, BigDecimal> productItemDefaultFreightMap = (Map<Integer, BigDecimal>) totalDefaultFreightMap.get("productItemFreightMap");//每只sku对应的运费金额
			BigDecimal totalDefaultFreight = productItemDefaultFreightMap.get(-1);//总运费设置key值为-1
			BigDecimal amount = payAmount.subtract(totalDefaultFreight).add(totalFreight);//因为前端更改省份，没有刷新页面，导致计算的运费还是默认默认模板所对应省份的运费
			differValue = totalPayAmount.subtract(amount);
			if(differValue.compareTo(zero) == 0){
				isSameFreight = true;
			}
		}
		return isSameFreight;
	}
	
}
