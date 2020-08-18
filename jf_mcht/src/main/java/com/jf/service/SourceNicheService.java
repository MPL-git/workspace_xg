package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.dao.ProductMapper;
import com.jf.dao.SourceNicheCustomMapper;
import com.jf.dao.SourceNicheMapper;
import com.jf.dao.SourceNicheProductMapper;
import com.jf.entity.Product;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import com.jf.entity.SourceNicheExample;

import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductExample;
import com.sun.org.apache.bcel.internal.generic.FDIV;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SourceNicheService extends BaseService<SourceNiche, SourceNicheExample> {

	@Autowired
	private SourceNicheMapper sourceNicheMapper;
	
	@Resource
	private SourceNicheCustomMapper sourceNicheCustomMapper;
	
	@Resource
	private SourceNicheProductMapper sourceNicheProductMapper;
	
	@Resource
	private ProductMapper productMapper;
	@Autowired
	public void setSourceNicheMapper(SourceNicheMapper sourceNicheMapper) {
		super.setDao(sourceNicheMapper);
		this.sourceNicheMapper = sourceNicheMapper;
	}
	
	public List<SourceNicheCustom> selectByCustomExample(
			SourceNicheCustomExample sourceNicheCustomExample) {
		// TODO Auto-generated method stub
		return sourceNicheCustomMapper.selectByCustomExample(sourceNicheCustomExample);
	}

	public int selectCountByCustomExample(
			SourceNicheCustomExample sourceNicheCustomExample) {
		// TODO Auto-generated method stub
		return sourceNicheCustomMapper.selectCountByCustomExample(sourceNicheCustomExample);
	}

	public ResponseMsg signUp(String paramList, String activityType, Integer mchtInfoId, String type,String upDate) throws ParseException {
		// TODO Auto-generated method stub
		JSONArray jsonArray = JSONArray.fromObject(paramList);
		upDate = upDate +" 10:00:00";		
		//批量查询该次报名商品是否已经存在
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		SourceNiche sourceNiche = new SourceNiche();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			int productId = Integer.valueOf(jsonObject.getString("productId"));
			//推荐文案
			Product product = new Product();
			product.setId(productId);
			if(!StringUtils.equals("11",activityType)){//活动类型不是积分抽奖
				String remarks = jsonObject.getString("remarks");
				product.setRecommendRemarks(remarks);
				productMapper.updateByPrimaryKeySelective(product);
			}
			//批量查询该次报名商品是否已经存在
			SourceNicheExample sourceNicheExample = new SourceNicheExample();
			sourceNicheExample.createCriteria().andLinkIdEqualTo(productId).andUpDateEqualTo(df.parse(upDate)).andTypeEqualTo(type).andDelFlagEqualTo("0");
			List<SourceNiche> sourceNiches = sourceNicheMapper.selectByExample(sourceNicheExample);			
			if(!sourceNiches.isEmpty()){
				continue;
			}
			sourceNiche.setType(type);
			sourceNiche.setLinkId(Integer.valueOf(productId));
			sourceNiche.setSupportQuantity(0);
			sourceNiche.setStatus("0");
			sourceNiche.setSource("1");
			sourceNiche.setMchtId(mchtInfoId);
			sourceNiche.setUpDate(df.parse(upDate));
			sourceNiche.setAuditStatus("0");
			sourceNiche.setDelFlag("0");
			sourceNiche.setCreateDate(new Date());
			sourceNicheMapper.insertSelective(sourceNiche);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}	
	
	
	public ResponseMsg shopSignUp(String paramList, String activityType, Integer mchtInfoId, String type) throws ParseException {
			//每日好店和大学生创业的保存 89
			SourceNicheExample sourceExample = new SourceNicheExample();
			sourceExample.createCriteria().andTypeEqualTo(type).andLinkIdEqualTo(mchtInfoId).andDelFlagEqualTo("0").andStatusEqualTo("0");;
			List<SourceNiche> mchtsource = sourceNicheMapper.selectByExample(sourceExample);
			
			if(mchtsource!=null && mchtsource.size()>0){//存在该商家
				SourceNiche mcthNiche = mchtsource.get(0);
				if("2".equals(mcthNiche.getAuditStatus())){//商家审核状态为驳回的审核,更新时将它再改为待审核并更新上线时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date upDate= new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(upDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date afterTime = c.getTime();
				String format = sdf.format(afterTime);
				mcthNiche.setUpDate(sdfs.parse(format+" 10:00:00"));//上线时间
				mcthNiche.setWeightUpdateDate(sdfs.parse(format+" 10:00:00"));//权重更新时间	
				mcthNiche.setAuditStatus("0");//审核状态
				mcthNiche.setCreateDate(new Date());//报名时间
				sourceNicheMapper.updateByPrimaryKeySelective(mcthNiche);
				}
				//将该商家报名商品批量删除,再修改
				Integer sourceId = mcthNiche.getId();
				SourceNicheProductExample sourceNicheProducExample = new SourceNicheProductExample();
				sourceNicheProducExample.createCriteria().andDelFlagEqualTo("0").andSourceNicheIdEqualTo(sourceId);
				SourceNicheProduct sourceNicheProduc4update = new SourceNicheProduct();
				sourceNicheProduc4update.setDelFlag("1");
				sourceNicheProductMapper.updateByExampleSelective(sourceNicheProduc4update, sourceNicheProducExample);
				//批量修改
				JSONArray jsonArray = JSONArray.fromObject(paramList);
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					int productId = Integer.valueOf(jsonObject.getString("productId"));
					sourceNicheProducExample = new SourceNicheProductExample();
					sourceNicheProducExample.createCriteria().andSourceNicheIdEqualTo(mchtInfoId).andProductIdEqualTo(productId);
					sourceNicheProduc4update = new SourceNicheProduct();
					sourceNicheProduc4update.setDelFlag("0");
					int count = sourceNicheProductMapper.updateByExampleSelective(sourceNicheProduc4update, sourceNicheProducExample);
					if(count>0){
						continue;
					}
					SourceNicheProduct sourceNicheProduct = new SourceNicheProduct();
					sourceNicheProduct.setSourceNicheId(mcthNiche.getId());//资源位Id
					sourceNicheProduct.setProductId(productId);//商品ID
					sourceNicheProduct.setDelFlag("0");
					sourceNicheProduct.setUpdateBy(mchtInfoId);
					sourceNicheProduct.setUpdateDate(new Date());
					sourceNicheProduct.setCreateDate(new Date());
					sourceNicheProductMapper.insertSelective(sourceNicheProduct);					
				}
				
			}else{//不存在该商家
				//将商家加入资源位表
				SourceNiche sourceNiche = new SourceNiche();
				sourceNiche.setType(type);
				sourceNiche.setLinkId(mchtInfoId);
				sourceNiche.setStatus("0");
				sourceNiche.setSource("1");
				sourceNiche.setMchtId(mchtInfoId);
				sourceNiche.setAuditStatus("0");
				sourceNiche.setDelFlag("0");
				sourceNiche.setCreateDate(new Date());//创建时间
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date upDate= new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(upDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date afterTime = c.getTime();
				String format = sdf.format(afterTime);
				sourceNiche.setUpDate(sdfs.parse(format+" 10:00:00"));//上线时间
				sourceNiche.setAuditDate(sdfs.parse(format+" 10:00:00"));//权重更新时间
				sourceNicheMapper.insertSelective(sourceNiche);
			
				//根据商家的资源位id将商品添加进资源位商品表中
				SourceNicheProduct sourceNicheProduct = new SourceNicheProduct();
				JSONArray jsonArray = JSONArray.fromObject(paramList);
				for(int i=0;i<jsonArray.size();i++){
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					int productId = Integer.valueOf(jsonObject.getString("productId"));
					
					sourceNicheProduct.setSourceNicheId(sourceNiche.getId());//资源位Id
					sourceNicheProduct.setProductId(productId);//商品ID
					sourceNicheProduct.setDelFlag("0");
					sourceNicheProduct.setCreateBy(mchtInfoId);
					sourceNicheProduct.setCreateDate(new Date());
					sourceNicheProductMapper.insertSelective(sourceNicheProduct);
				}

			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
}
