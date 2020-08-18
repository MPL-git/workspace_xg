package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtils;
import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.dao.ProductBrandCustomMapper;
import com.jf.dao.ProductBrandMapper;
import com.jf.dao.ProductBrandTrademarkInfoMapper;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductBrandTrademarkInfo;
import com.jf.entity.ProductBrandTrademarkInfoExample;

@Service
@Transactional
public class ProductBrandService extends BaseService<ProductBrand, ProductBrandExample>{
	@Autowired
	private ProductBrandMapper productBrandMapper;
	@Resource
	private BrandTmkPicTmpMapper brandTmkPicTmpMapper;
	@Autowired
	private ProductBrandCustomMapper productBrandCustomMapper;
	@Autowired
	private ProductBrandTrademarkInfoMapper productBrandTrademarkInfoMapper;
	
	@Autowired
	public void setProductBrandMapper(ProductBrandMapper ProductBrandMapper) {
		super.setDao(ProductBrandMapper);
		this.productBrandMapper = ProductBrandMapper;
	}
	
	public int countByCustomExample(ProductBrandCustomExample example) {
		return productBrandCustomMapper.countByCustomExample(example);
	}

	public List<ProductBrandCustom> selectByCustomExample(ProductBrandCustomExample example) {
		return productBrandCustomMapper.selectByCustomExample(example);
	}

	public ProductBrandCustom selectByCustomPrimaryKey(Integer id) {
		return productBrandCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") ProductBrand record, @Param("example") ProductBrandCustomExample example) {
		return productBrandCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	public List<Map<String, Object>> selectMchtInfoBrandCustomList(Map<String, Object> paramMap) {
		return productBrandCustomMapper.selectMchtInfoBrandCustomList(paramMap);
	}
	
	//更新数据
	public void updateAuditing(ProductBrand productBrand,String brandTmkPics, String tmk) {
		//更新商品品牌数据
		productBrandMapper.updateByPrimaryKeySelective(productBrand);
		//查询图片的缓存数据，并把标志位设置为1。因为用户会删除图片的操作
		if(productBrand.getId()!=null){
			BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
			brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrand.getId()).andDelFlagEqualTo("0");
			BrandTmkPicTmp brandTmkPicTmp4Update=new BrandTmkPicTmp();
			brandTmkPicTmp4Update.setDelFlag("1");
			brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
		}
		if(!StringUtils.isEmpty(brandTmkPics)){
			JSONArray brandTmkPicArray=JSONArray.fromObject(brandTmkPics);
			Iterator<JSONObject> it= brandTmkPicArray.iterator();
			while (it.hasNext()) {
				JSONObject brandTmkPic=it.next();
				
				BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
				brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrand.getId()).andPicEqualTo(brandTmkPic.getString("picPath"));
				BrandTmkPicTmp brandTmkPicTmp4Update=new BrandTmkPicTmp();
				brandTmkPicTmp4Update.setDelFlag("0");
				int updateCount=brandTmkPicTmpMapper.updateByExampleSelective(brandTmkPicTmp4Update, brandTmkPicTmpExample);
				if(updateCount>0){
					continue;
				}
				BrandTmkPicTmp brandTmkPicTmp=new BrandTmkPicTmp();
				brandTmkPicTmp.setBrandId(productBrand.getId());
				brandTmkPicTmp.setPic(brandTmkPic.getString("picPath"));
				brandTmkPicTmp.setCreateBy(productBrand.getUpdateBy());
				brandTmkPicTmp.setCreateDate(productBrand.getUpdateDate());
				brandTmkPicTmp.setUpdateBy(productBrand.getUpdateBy());
				brandTmkPicTmp.setUpdateDate(productBrand.getUpdateDate());
				brandTmkPicTmpMapper.insertSelective(brandTmkPicTmp);
			}
		}
		ProductBrandTrademarkInfoExample productBrandTrademarkInfoExample = new ProductBrandTrademarkInfoExample();
		productBrandTrademarkInfoExample.createCriteria().andDelFlagEqualTo("0").andProductBrandIdEqualTo(productBrand.getId());
		List<ProductBrandTrademarkInfo> productBrandTrademarkInfoList = productBrandTrademarkInfoMapper.selectByExample(productBrandTrademarkInfoExample);
		String[] tmks = tmk.split(",");
		if(productBrandTrademarkInfoList != null && productBrandTrademarkInfoList.size() > 0) {
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			List<Integer> idList = new ArrayList<Integer>();
			for(String tmkStr : tmks) {
				String[] tmkCodeType = tmkStr.split("-");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("tmkCode", tmkCodeType[0]);
				map.put("tmkType", tmkCodeType[1]);
				listMap.add(map);
			}
			for (int i = 0; i < productBrandTrademarkInfoList.size(); i++) {
				boolean flag = true;
				Map<String, Object> mapObj = null;
				for(Map<String, Object> map : listMap) {
					if(productBrandTrademarkInfoList.get(i).getTmkCode().equals(map.get("tmkCode")) 
							&& productBrandTrademarkInfoList.get(i).getTmkType().equals(map.get("tmkType")) ) {
						mapObj = map;
						flag = false;
					}
				}
				if(flag) {
					idList.add(productBrandTrademarkInfoList.get(i).getId());
				}else {
					listMap.remove(mapObj);
				}
			}
			for(Integer id : idList) {
				ProductBrandTrademarkInfo productBrandTrademarkInfo = new ProductBrandTrademarkInfo();
				productBrandTrademarkInfo.setId(id);
				productBrandTrademarkInfo.setDelFlag("1");
				productBrandTrademarkInfo.setUpdateBy(productBrand.getUpdateBy());
				productBrandTrademarkInfo.setUpdateDate(productBrand.getUpdateDate());
				productBrandTrademarkInfoMapper.updateByPrimaryKeySelective(productBrandTrademarkInfo);
			}
			for(Map<String, Object> map : listMap) {
				ProductBrandTrademarkInfo productBrandTrademarkInfo = new ProductBrandTrademarkInfo();
				productBrandTrademarkInfo.setProductBrandId(productBrand.getId());
				productBrandTrademarkInfo.setTmkCode(map.get("tmkCode").toString());
				productBrandTrademarkInfo.setTmkType(map.get("tmkType").toString());
				productBrandTrademarkInfo.setCreateBy(productBrand.getUpdateBy());
				productBrandTrademarkInfo.setCreateDate(productBrand.getUpdateDate());
				productBrandTrademarkInfo.setDelFlag("0");
				productBrandTrademarkInfoMapper.insertSelective(productBrandTrademarkInfo);
			}
		}else {
			for(String tmkStr : tmks) {
				String[] tmkCodeType = tmkStr.split("-");
				ProductBrandTrademarkInfo productBrandTrademarkInfo = new ProductBrandTrademarkInfo();
				productBrandTrademarkInfo.setProductBrandId(productBrand.getId());
				productBrandTrademarkInfo.setTmkCode(tmkCodeType[0]);
				productBrandTrademarkInfo.setTmkType(tmkCodeType[1]);
				productBrandTrademarkInfo.setCreateBy(productBrand.getUpdateBy());
				productBrandTrademarkInfo.setCreateDate(productBrand.getUpdateDate());
				productBrandTrademarkInfo.setDelFlag("0");
				productBrandTrademarkInfoMapper.insertSelective(productBrandTrademarkInfo);
			}
		}
	}
	
	//插入数据
	@SuppressWarnings("unchecked")
	public void insertProductBrand(ProductBrand productBrand,String brandTmkPics, String tmk){
		//插入品牌表
		productBrandMapper.insertSelective(productBrand);
		//插入图片表
		if(!StringUtils.isEmpty(brandTmkPics)){
			JSONArray brandTmkPicArray=JSONArray.fromObject(brandTmkPics);
			Iterator<JSONObject> it= brandTmkPicArray.iterator();
			while (it.hasNext()) {
				JSONObject brandTmkPic=it.next();
				BrandTmkPicTmp brandTmkPicTmp=new BrandTmkPicTmp();
				brandTmkPicTmp.setBrandId(productBrand.getId());
				brandTmkPicTmp.setPic(brandTmkPic.getString("picPath"));
				brandTmkPicTmp.setCreateBy(productBrand.getUpdateBy());
				brandTmkPicTmp.setCreateDate(productBrand.getUpdateDate());
				brandTmkPicTmp.setUpdateBy(productBrand.getUpdateBy());
				brandTmkPicTmp.setUpdateDate(productBrand.getUpdateDate());
				brandTmkPicTmpMapper.insertSelective(brandTmkPicTmp);
			}
		}
		String[] tmks = tmk.split(",");
		for(String tmkStr : tmks) {
			String[] tmkCodeType = tmkStr.split("-");
			ProductBrandTrademarkInfo productBrandTrademarkInfo = new ProductBrandTrademarkInfo();
			productBrandTrademarkInfo.setProductBrandId(productBrand.getId());
			productBrandTrademarkInfo.setTmkCode(tmkCodeType[0]);
			productBrandTrademarkInfo.setTmkType(tmkCodeType[1]);
			productBrandTrademarkInfo.setCreateBy(productBrand.getCreateBy());
			productBrandTrademarkInfo.setCreateDate(productBrand.getCreateDate());
			productBrandTrademarkInfo.setDelFlag("0");
			productBrandTrademarkInfoMapper.insertSelective(productBrandTrademarkInfo);
		}
	}

	public String getNamesByIds(String ids) {
		return productBrandCustomMapper.getNamesByIds(ids);
	}

	public List<HashMap<String, Object>> searchBrand(
			Map<String, Object> paramMap) {
		return productBrandCustomMapper.searchBrand(paramMap);
	}

    public List<ProductBrandCustom> getBrandFlowReportData(Map<String, Object> paraMap) {
		return productBrandCustomMapper.getBrandFlowReportData(paraMap);
    }
}

