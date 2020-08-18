package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductModuleTypeCustomMapper;
import com.jf.dao.ProductModuleTypeMapper;
import com.jf.dao.SportCustomMapper;
import com.jf.dao.SportMapper;
import com.jf.entity.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午3:16:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ProductModuleTypeService extends BaseService<ProductModuleType, ProductModuleTypeExample> {
	@Autowired
	private ProductModuleTypeMapper productModuleTypeMapper;
	@Autowired
	private ProductModuleTypeCustomMapper productModuleTypeCustomMapper;

	@Autowired
	public void setProductModuleTypeMapper(ProductModuleTypeMapper productModuleTypeMapper) {
		this.setDao(productModuleTypeMapper);
		this.productModuleTypeMapper = productModuleTypeMapper;
	}

	public List<ProductModuleTypeCustom> getProductModuleTypeCustomList(Map<String,Object> map){
		return productModuleTypeCustomMapper.getProductModuleTypeCustomList(map);
	}
}
