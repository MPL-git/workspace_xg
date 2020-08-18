package com.jf.service;

import com.jf.dao.SvipBindProductCustomMapper;
import com.jf.dao.SvipBindProductMapper;
import com.jf.dao.SvipMarketingSettingMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipBindProductCustom;
import com.jf.entity.SvipBindProductExample;
import com.jf.entity.SvipMarketingSetting;
import com.jf.entity.SvipMarketingSettingExample;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SvipBindProductService extends BaseService<SvipBindProduct, SvipBindProductExample>{
	@Autowired
	private SvipBindProductMapper svipBindProductMapper;
	@Autowired
	private SvipBindProductCustomMapper svipBindProductCustomMapper;
	@Autowired
	private SvipMarketingSettingMapper svipMarketingSettingMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	public void setActivityAuthMapper(SvipBindProductMapper svipBindProductMapper) {
		super.setDao(svipBindProductMapper);
		this.svipBindProductMapper = svipBindProductMapper;
	}

	public Integer countSvipBindProductCustomByExample(Map<String, Object> paramMap) {
		return svipBindProductCustomMapper.countSvipBindProductCustomByExample(paramMap);
	}

	public List<SvipBindProductCustom> selectSvipBindProductCustomByExample(Map<String, Object> paramMap) {
		return svipBindProductCustomMapper.selectSvipBindProductCustomByExample(paramMap);
	}

    public Map<String, Object> batchDelProduct(HttpServletRequest request,Integer staffId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg","删除成功");
		int idSuccess = 0;
		int idError = 0;
		List<Integer> idList = Arrays.asList((Integer[]) ConvertUtils.convert(request.getParameter("ids").split(","), Integer.class));
		for (Integer id : idList){
			try {
				SvipBindProduct svipBindProduct = new SvipBindProduct();
				svipBindProduct.setId(id);
				svipBindProduct.setDelFlag("1");
				svipBindProduct.setUpdateBy(staffId);
				svipBindProduct.setUpdateDate(new Date());
				svipBindProductMapper.updateByPrimaryKeySelective(svipBindProduct);
				++idSuccess;
			} catch (Exception e) {
				++idError;
				if(idList.size() == 1){
					resMap.put("returnMsg","删除失败");
				}
			}
		}
		resMap.put("idSuccess",idSuccess);
		resMap.put("idError",idError);
		return resMap;
    }

    public Map<String, Object> toAddProductSubmit(HttpServletRequest request, int staffId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		int isSuccess = 0;
		int isError = 0;
		int unSvip = 0;

		List<String> productCodeList = Arrays.asList(request.getParameter("productIds").split("\n"));
		List<Integer> productIdList = svipBindProductCustomMapper.selectProductList(productCodeList);
		isError = productCodeList.size()-productIdList.size();

		if(!productIdList.isEmpty()){
			SvipBindProductExample svipBindProductExample = new SvipBindProductExample();
			svipBindProductExample.createCriteria().andProductIdIn(productIdList).andDelFlagEqualTo("0");
			List<SvipBindProduct> svipBindProductList = svipBindProductMapper.selectByExample(svipBindProductExample);

			List<Integer> svipProductIdList = new ArrayList<>();
			for (SvipBindProduct svipBindProduct : svipBindProductList){
				svipProductIdList.add(svipBindProduct.getProductId());
			}
			productIdList.removeAll(svipProductIdList);

			List<Product> productList = new ArrayList<>();
			if(!productIdList.isEmpty()){
				ProductExample productExample = new ProductExample();
				productExample.createCriteria().andIdIn(productIdList);
				productList = productService.selectByExample(productExample);
			}

			SvipMarketingSettingExample svipMarketingSettingExample = new SvipMarketingSettingExample();
			svipMarketingSettingExample.createCriteria().andDelFlagEqualTo("0");
			List<SvipMarketingSetting> svipMarketingSettingList = svipMarketingSettingMapper.selectByExample(svipMarketingSettingExample);

			for (Product product : productList){
				try {
					if(product.getSvipDiscount() != null){
						SvipBindProduct svipBindProduct = new SvipBindProduct();
						svipBindProduct.setProductId(product.getId());
						svipBindProduct.setSvipMarketingSettingId(svipMarketingSettingList.isEmpty()?null:svipMarketingSettingList.get(0).getId());
						svipBindProduct.setCreateBy(staffId);
						svipBindProduct.setCreateDate(new Date());
						svipBindProduct.setDelFlag("0");
						svipBindProductMapper.insertSelective(svipBindProduct);
						++isSuccess;
					}else{
						++unSvip;
					}
				}catch (Exception e){
				}
			}
		}
		resMap.put("idSuccess",isSuccess);
		resMap.put("idError",isError);
		resMap.put("unSvip",unSvip);
		return resMap;
    }
}
