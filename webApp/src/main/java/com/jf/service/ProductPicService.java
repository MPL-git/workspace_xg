package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年8月1日 上午9:57:07 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductPicService extends BaseService<ProductPic, ProductPicExample> {
	@Autowired
	private ProductPicMapper productPicMapper;

	@Autowired
	public void setProductPicMapper(ProductPicMapper productPicMapper) {
		this.setDao(productPicMapper);
		this.productPicMapper = productPicMapper;
	}

	public List<Map<String,Object>> getProductDefaultPic(Integer id, String activityAreaId, String isWaitermark,Boolean isSingleActivityWatermark) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		ProductPicExample productPicExample = new ProductPicExample();
		productPicExample.createCriteria().andProductIdEqualTo(id).andDelFlagEqualTo("0");
		productPicExample.setOrderByClause("is_default desc,seq_no,id");
		List<ProductPic> productPicList = selectByExample(productPicExample);
		if(productPicList != null && productPicList.size() > 0){
			for (ProductPic productPic : productPicList) {
				Map<String,Object> picMap = new HashMap<String,Object>();
				String pic = productPic.getPic();
				
//				if(isSingleActivityWatermark!=null&&isSingleActivityWatermark){
//					pic = StringUtil.getActivityMkPic(productPic.getPic(), "70Q_WM",null);
//				}else{
					if(isWaitermark.equals("0") || StringUtil.isBlank(activityAreaId)){
						pic = StringUtil.getPic(productPic.getPic(), "70Q");
					}else{
						pic = StringUtil.getActivityMkPic(productPic.getPic(), "70Q_WM",Integer.valueOf(activityAreaId));
					}
//				}
				
				picMap.put("pic", pic);
				list.add(picMap);
			}
		}
		return list;
	}
	
	
}
