package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductPicMapper;
import com.jf.entity.ProductPic;
import com.jf.entity.ProductPicExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月28日 上午10:39:47 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductPicService extends BaseService<ProductPic, ProductPicExample> {
	private static Logger logger = LoggerFactory.getLogger(ProductPicService.class); 
	
	@Autowired
	private ProductPicMapper productPicMapper;
	
	@Autowired
	public void setProductPicMapper(ProductPicMapper productPicMapper) {
		this.setDao(productPicMapper);
		this.productPicMapper = productPicMapper;
	}
/**
 * 	
 * @param id
 * @param activityAreaId
 * @param isWaitermark
 * @param isSingleActivityWatermark 如果是单品活动且有打水印，则取打水印的图
 * @return
 */
	public List<Map<String,Object>> getProductDefaultPic(Integer id,String activityAreaId,String isWaitermark,Boolean isSingleActivityWatermark) {
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
