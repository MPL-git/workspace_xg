package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.dao.ProductBrandMapper;
import com.jf.dao.ProductBrandTmpCustomMapper;
import com.jf.dao.ProductBrandTmpMapper;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpCustomExample;
import com.jf.entity.ProductBrandTmpExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ProductBrandTmpService extends BaseService<ProductBrandTmp, ProductBrandTmpExample> {
	@Autowired
	private ProductBrandTmpMapper productBrandTmpMapper;
	@Autowired
	private ProductBrandTmpCustomMapper productBrandTmpCustomMapper;
	
	@Autowired
	private ProductBrandMapper productBrandMapper;
	
	@Autowired
	private BrandTmkPicTmpMapper brandTmkPicTmpMapper;

	@Autowired
	public void setProductBrandTmpMapper(ProductBrandTmpMapper productBrandTmpMapper) {
		super.setDao(productBrandTmpMapper);
		this.productBrandTmpMapper = productBrandTmpMapper;
	}

	public List<ProductBrandTmpCustom> selectProductBrandTmpCustomByExample(ProductBrandTmpCustomExample example) {
		return productBrandTmpCustomMapper.selectByExample(example);
	}
	
	public ProductBrandTmpCustom selectProductBrandTmpCustomByPrimaryKey(Integer id){
		return productBrandTmpCustomMapper.selectByPrimaryKey(id);
	}
	
	public void applyCommitSave(ProductBrandTmp productBrandTmp,String brandTmkPics) throws ArgException{
		
		ProductBrandTmpExample productBrandTmpExample;
		
		ProductBrandExample productBrandExample=new ProductBrandExample();
		productBrandExample.createCriteria().andNameEqualTo(productBrandTmp.getName());
		int count=productBrandMapper.countByExample(productBrandExample);
		if(count>0){
			throw new ArgException("品牌已存在。无需申请。");
		}
		
		if(productBrandTmp.getId()==null){
			productBrandTmpExample=new ProductBrandTmpExample();
			productBrandTmpExample.createCriteria().andNameEqualTo(productBrandTmp.getName());
			int productBrandTmpCount=productBrandTmpMapper.countByExample(productBrandTmpExample);
			if(productBrandTmpCount>0){
				throw new ArgException("品牌已在申请中。");
			}
			
			productBrandTmpMapper.insertSelective(productBrandTmp);
		}else{
			productBrandTmpExample=new ProductBrandTmpExample();
			productBrandTmpExample.createCriteria().andNameEqualTo(productBrandTmp.getName()).andIdNotEqualTo(productBrandTmp.getId());
			int productBrandTmpCount=productBrandTmpMapper.countByExample(productBrandTmpExample);
			if(productBrandTmpCount>0){
				throw new ArgException("品牌已在申请中。");
			}
			ProductBrandTmp oldProductBrandTmp=productBrandTmpMapper.selectByPrimaryKey(productBrandTmp.getId());
			if(oldProductBrandTmp.getStatus().equals("1")||oldProductBrandTmp.getStatus().equals("2")){
				throw new ArgException("品牌正在审核当中或已审核通过，不可修改。");
			}
			productBrandTmpMapper.updateByPrimaryKeySelective(productBrandTmp);
		}
		
		
				// 更新商标图
				BrandTmkPicTmpExample brandTmkPicTmpExample = new BrandTmkPicTmpExample();
				brandTmkPicTmpExample.createCriteria().andDelFlagEqualTo("0").andBrandIdEqualTo(productBrandTmp.getId());
				BrandTmkPicTmp brandTmkPicTmp4Update = new BrandTmkPicTmp();
				brandTmkPicTmp4Update.setDelFlag("1");
				brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);

				JSONArray brandTmkPicArray = JSONArray.fromObject(brandTmkPics);
				Iterator<JSONObject> it = brandTmkPicArray.iterator();
				while (it.hasNext()) {
					JSONObject brandTmkPicObject = it.next();

					brandTmkPicTmpExample = new BrandTmkPicTmpExample();
					brandTmkPicTmpExample.createCriteria().andPicEqualTo(brandTmkPicObject.getString("pic")).andBrandIdEqualTo(productBrandTmp.getId());
					brandTmkPicTmp4Update = new BrandTmkPicTmp();
					brandTmkPicTmp4Update.setDelFlag("0");
					int updateCount = brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
					if (updateCount > 0) {
						continue;
					}
					BrandTmkPicTmp brandTmkPicTmp = new BrandTmkPicTmp();
					brandTmkPicTmp.setBrandId(productBrandTmp.getId());
					brandTmkPicTmp.setPic(brandTmkPicObject.getString("pic"));
					brandTmkPicTmp.setCreateBy(productBrandTmp.getUpdateBy());
					brandTmkPicTmp.setCreateDate(productBrandTmp.getUpdateDate());
					brandTmkPicTmp.setUpdateBy(productBrandTmp.getUpdateBy());
					brandTmkPicTmp.setUpdateDate(productBrandTmp.getUpdateDate());
					brandTmkPicTmpMapper.insertSelective(brandTmkPicTmp);
				}

		
		
		
	}
	

	public void applyCommitAudit(ProductBrandTmp productBrandTmp,String brandTmkPics) throws ArgException{
		
		ProductBrandTmpExample productBrandTmpExample;
		
		ProductBrandExample productBrandExample=new ProductBrandExample();
		productBrandExample.createCriteria().andNameEqualTo(productBrandTmp.getName());
		int count=productBrandMapper.countByExample(productBrandExample);
		if(count>0){
			throw new ArgException("品牌已存在。无需申请。");
		}
		
			productBrandTmpExample=new ProductBrandTmpExample();
			productBrandTmpExample.createCriteria().andNameEqualTo(productBrandTmp.getName()).andIdNotEqualTo(productBrandTmp.getId());
			int productBrandTmpCount=productBrandTmpMapper.countByExample(productBrandTmpExample);
			if(productBrandTmpCount>0){
				throw new ArgException("品牌已在申请中。");
			}
			ProductBrandTmp oldProductBrandTmp=productBrandTmpMapper.selectByPrimaryKey(productBrandTmp.getId());
			if(oldProductBrandTmp.getStatus().equals("1")||oldProductBrandTmp.getStatus().equals("2")){
				throw new ArgException("品牌正在审核当中或已审核通过，不可修改。");
			}
			productBrandTmp.setStatus("1");
			productBrandTmpMapper.updateByPrimaryKeySelective(productBrandTmp);
			
			// 更新商标图
			BrandTmkPicTmpExample brandTmkPicTmpExample = new BrandTmkPicTmpExample();
			brandTmkPicTmpExample.createCriteria().andDelFlagEqualTo("0").andBrandIdEqualTo(productBrandTmp.getId());
			BrandTmkPicTmp brandTmkPicTmp4Update = new BrandTmkPicTmp();
			brandTmkPicTmp4Update.setDelFlag("1");
			brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);

			JSONArray brandTmkPicArray = JSONArray.fromObject(brandTmkPics);
			Iterator<JSONObject> it = brandTmkPicArray.iterator();
			while (it.hasNext()) {
				JSONObject brandTmkPicObject = it.next();

				brandTmkPicTmpExample = new BrandTmkPicTmpExample();
				brandTmkPicTmpExample.createCriteria().andPicEqualTo(brandTmkPicObject.getString("pic")).andBrandIdEqualTo(productBrandTmp.getId());
				brandTmkPicTmp4Update = new BrandTmkPicTmp();
				brandTmkPicTmp4Update.setDelFlag("0");
				int updateCount = brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
				if (updateCount > 0) {
					continue;
				}
				BrandTmkPicTmp brandTmkPicTmp = new BrandTmkPicTmp();
				brandTmkPicTmp.setBrandId(productBrandTmp.getId());
				brandTmkPicTmp.setPic(brandTmkPicObject.getString("pic"));
				brandTmkPicTmp.setCreateBy(productBrandTmp.getUpdateBy());
				brandTmkPicTmp.setCreateDate(productBrandTmp.getUpdateDate());
				brandTmkPicTmp.setUpdateBy(productBrandTmp.getUpdateBy());
				brandTmkPicTmp.setUpdateDate(productBrandTmp.getUpdateDate());
				brandTmkPicTmpMapper.insertSelective(brandTmkPicTmp);
			}

	}
	
	
}
