package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.dao.ProductMapper;
import com.jf.dao.ProductPicMapper;
import com.jf.dao.ThirdPlatformProductInfoCustomMapper;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ThirdPlatformProductInfoService extends BaseService<ThirdPlatformProductInfo,ThirdPlatformProductInfoExample> {
	@Autowired
	private ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper;
	
	@Resource
	private ThirdPlatformProductInfoCustomMapper thirdPlatformProductInfoCustomMapper;
	
	@Resource
	private ProductMapper productMapper;
	
	@Resource
	private ProductPicMapper productPicMapper;

	@Resource
	private ProductUpperLowerLogService productUpperLowerLogService;
	@Autowired
	public void setThirdPlatformProductInfoMapper(ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper) {
		super.setDao(thirdPlatformProductInfoMapper);
		this.thirdPlatformProductInfoMapper = thirdPlatformProductInfoMapper;
	}
	
	public List<ThirdPlatformProductInfoCustom> selectByCustomExample(ThirdPlatformProductInfoCustomExample example){
		return thirdPlatformProductInfoCustomMapper.selectByExample(example);
	}
	
	public int countByCustomExample(ThirdPlatformProductInfoCustomExample example){
		return thirdPlatformProductInfoCustomMapper.countByExample(example);
	}

	public void update(Product product, ProductExample example,
			ThirdPlatformProductInfo thirdPlatformProductInfo,
			ThirdPlatformProductInfoExample e,List<Integer> productIdList) {
		productMapper.updateByExampleSelective(product, example);
		this.updateByExampleSelective(thirdPlatformProductInfo, e);

		//商品上下架日志
		for (Integer productId : productIdList) {
			ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
			productUpperLowerLog.setProductId(productId);
			productUpperLowerLog.setStatus(product.getStatus());
			productUpperLowerLog.setType(Const.PLATFORM);
			productUpperLowerLog.setOffReason(product.getOffReason());
			productUpperLowerLog.setCreateBy(product.getUpdateBy());
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);
		}
	}

	public void update(Product product,
			ThirdPlatformProductInfo thirdPlatformProductInfo) {
		productMapper.updateByPrimaryKeySelective(product);
		this.updateByPrimaryKeySelective(thirdPlatformProductInfo);

		//商品上下架日志
		ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
		productUpperLowerLog.setProductId(product.getId());
		productUpperLowerLog.setStatus(product.getStatus());
		productUpperLowerLog.setType(Const.PLATFORM);
		productUpperLowerLog.setOffReason(product.getOffReason());
		productUpperLowerLog.setCreateBy(product.getUpdateBy());
		productUpperLowerLog.setCreateDate(new Date());
		productUpperLowerLogService.insertSelective(productUpperLowerLog);
	}

	public void save(ThirdPlatformProductInfo thirdPlatformProductInfo,String imgs) {
		this.updateByPrimaryKeySelective(thirdPlatformProductInfo);
		Product p = new Product();
		p.setUpdateDate(new Date());
		p.setName(thirdPlatformProductInfo.getTitle());
		p.setProductType1Id(thirdPlatformProductInfo.getProductType1Id());
		p.setProductType2Id(thirdPlatformProductInfo.getProductType2Id());
		p.setProductTypeId(thirdPlatformProductInfo.getProductType3Id());
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andIdEqualTo(thirdPlatformProductInfo.getProductId()).andDelFlagEqualTo("0");
		productMapper.updateByExampleSelective(p, productExample);
		
		//TODO 先删除全部旧的
		ProductPic delProductPic = new ProductPic();
		delProductPic.setDelFlag("1");
		delProductPic.setUpdateDate(new Date());
		ProductPicExample productPicExample = new ProductPicExample();
		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(thirdPlatformProductInfo.getProductId());
		productPicMapper.updateByExampleSelective(delProductPic, productPicExample);
		
		JSONArray imgsArray = JSONArray.fromObject(imgs);
		String defaultImg = imgsArray.get(0).toString();
		ProductPicExample e = new ProductPicExample();
		ProductPicExample.Criteria c = e.createCriteria();
		c.andPicEqualTo(defaultImg).andProductIdEqualTo(thirdPlatformProductInfo.getProductId());
		int count = productPicMapper.countByExample(e);
		if(count == 0){
			ProductPic productPic = new ProductPic();
			productPic.setDelFlag("0");
			productPic.setCreateDate(new Date());
			productPic.setProductId(thirdPlatformProductInfo.getProductId());
			productPic.setPic(defaultImg);
			productPic.setIsDefault("1");
			productPicMapper.insertSelective(productPic);
		}else{
			ProductPic pp = new ProductPic();
			pp.setDelFlag("0");
			pp.setIsDefault("1");
			pp.setUpdateDate(new Date());
			productPicMapper.updateByExampleSelective(pp, e);
		}
		for(int i=1;i<imgsArray.size();i++){
			String img = imgsArray.get(i).toString();
			e = new ProductPicExample();
			ProductPicExample.Criteria criteria = e.createCriteria();
			criteria.andPicEqualTo(img).andProductIdEqualTo(thirdPlatformProductInfo.getProductId());
			int otherCount = productPicMapper.countByExample(e);
			if(otherCount == 0){
				ProductPic productPic = new ProductPic();
				productPic.setDelFlag("0");
				productPic.setCreateDate(new Date());
				productPic.setProductId(thirdPlatformProductInfo.getProductId());
				productPic.setPic(img);
				productPic.setIsDefault("0");
				productPicMapper.insertSelective(productPic);
			}else{
				ProductPic pp = new ProductPic();
				pp.setDelFlag("0");
				pp.setIsDefault("0");
				pp.setUpdateDate(new Date());
				productPicMapper.updateByExampleSelective(pp, e);
			}
		}
	}
	
	public void updateNullSeqNo(Integer id){
		thirdPlatformProductInfoCustomMapper.updateNullSeqNo(id);
	} 
}
