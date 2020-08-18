package com.jf.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BrandTmkPicMapper;
import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.dao.ProductBrandTmpCustomMapper;
import com.jf.dao.ProductBrandTmpMapper;
import com.jf.entity.BrandTmkPic;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpExample;

@Service
@Transactional
public class ProductBrandTmpService extends BaseService<ProductBrandTmp, ProductBrandTmpExample> {
	@Autowired
	private ProductBrandTmpMapper productBrandTmpMapper;
	@Autowired
	private ProductBrandTmpCustomMapper productBrandTmpCustomMapper;
	
	@Resource
	private BrandTmkPicTmpMapper brandTmkPicTmpMapper;
	
	@Resource
	private BrandTmkPicMapper brandTmkPicMapper;
	
	@Resource
	private ProductBrandService productBrandService;

	@Autowired
	public void setProductBrandTmpMapper(ProductBrandTmpMapper productBrandTmpMapper) {
		super.setDao(productBrandTmpMapper);
		this.productBrandTmpMapper = productBrandTmpMapper;
	}

	public List<ProductBrandTmpCustom> selectByExampleCustom(ProductBrandTmpExample example) {
		return productBrandTmpCustomMapper.selectByExampleCustom(example);
	}
	
	public void updateAuditing(ProductBrandTmp productBrandTmp,String brandTmkPics) {
		
		
		ProductBrandExample productBrandExample=new ProductBrandExample();
		productBrandExample.createCriteria().andNameEqualTo(productBrandTmp.getName()).andDelFlagEqualTo("0");
		int count=productBrandService.countByExample(productBrandExample);
		if(count>0){//品牌已经存在
			return;
		}
		
		productBrandTmpMapper.updateByPrimaryKeySelective(productBrandTmp);
		
		if(productBrandTmp.getId()!=null){
			BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
			brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrandTmp.getId()).andDelFlagEqualTo("0");
			BrandTmkPicTmp brandTmkPicTmp4Update=new BrandTmkPicTmp();
			brandTmkPicTmp4Update.setDelFlag("1");
			brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
		}
		
		JSONArray brandTmkPicArray=JSONArray.fromObject(brandTmkPics);
		Iterator<JSONObject> it= brandTmkPicArray.iterator();
		while (it.hasNext()) {
			JSONObject brandTmkPicObject=it.next();
			
			BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
			brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrandTmp.getId()).andPicEqualTo(brandTmkPicObject.getString("picPath"));
			BrandTmkPicTmp brandTmkPicTmp4Update=new BrandTmkPicTmp();
			brandTmkPicTmp4Update.setDelFlag("0");
			int updateCount=brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
			if(updateCount>0){
				continue;
			}
			BrandTmkPicTmp brandTmkPicTmp=new BrandTmkPicTmp();
			brandTmkPicTmp.setBrandId(productBrandTmp.getId());
			brandTmkPicTmp.setPic(brandTmkPicObject.getString("picPath"));
			brandTmkPicTmp.setCreateBy(productBrandTmp.getUpdateBy());
			brandTmkPicTmp.setCreateDate(productBrandTmp.getUpdateDate());
			brandTmkPicTmp.setUpdateBy(productBrandTmp.getUpdateBy());
			brandTmkPicTmp.setUpdateDate(productBrandTmp.getUpdateDate());
			brandTmkPicTmpMapper.insertSelective(brandTmkPicTmp);
		}
		
		if("2".equals(productBrandTmp.getStatus())){
			ProductBrand productBrand=new ProductBrand();
			productBrand.setCatalog(productBrandTmp.getCatalog());
			productBrand.setCreateBy(productBrandTmp.getUpdateBy());
			productBrand.setCreateDate(new Date());
			productBrand.setDelFlag("0");
			productBrand.setLogo(productBrandTmp.getLogo());
			productBrand.setName(productBrandTmp.getName());
			productBrand.setNameEn(productBrandTmp.getNameEn());
			productBrand.setNameZh(productBrandTmp.getNameZh());
			productBrand.setProductTypeGroup(productBrandTmp.getProductTypeGroup());
			productBrand.setStatus("0");
			productBrand.setTmkTypeGroup(productBrandTmp.getTmkTypeGroup());
			productBrandService.insertSelective(productBrand);
			Iterator<JSONObject> iterator= brandTmkPicArray.iterator();
			while (iterator.hasNext()) {
				JSONObject brandTmkPicOjbect=iterator.next();
				BrandTmkPic brandTmkPic=new BrandTmkPic();
				brandTmkPic.setBrandId(productBrand.getId());
				brandTmkPic.setPic(brandTmkPicOjbect.getString("picPath"));
				brandTmkPic.setCreateBy(productBrandTmp.getUpdateBy());
				brandTmkPic.setCreateDate(productBrandTmp.getUpdateDate());
				brandTmkPic.setUpdateBy(productBrandTmp.getUpdateBy());
				brandTmkPic.setUpdateDate(productBrandTmp.getUpdateDate());
				brandTmkPicMapper.insertSelective(brandTmkPic);
			}
		}
		
		
	}
}
