package com.jf.service;

import com.jf.dao.ProductMapper;
import com.jf.dao.SourceNicheCustomMapper;
import com.jf.dao.SourceNicheMapper;
import com.jf.entity.Product;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import com.jf.entity.SourceNicheExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SourceNicheService extends BaseService<SourceNiche, SourceNicheExample> {

	@Autowired
	private SourceNicheMapper sourceNicheMapper;
	
	@Autowired
	private SourceNicheCustomMapper sourceNicheCustomMapper;

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	public void setOrderViewlogMapper(SourceNicheMapper sourceNicheMapper) {
		super.setDao(sourceNicheMapper);
		this.sourceNicheMapper = sourceNicheMapper;
	}
	
	public int sourceNichecountByExample(SourceNicheCustomExample example) {
		return sourceNicheCustomMapper.sourceNichecountByExample(example);
	}

	public List<SourceNicheCustom> sourceNicheCustomselectByExample(SourceNicheCustomExample example) {
		return sourceNicheCustomMapper.sourceNicheCustomselectByExample(example);
	}

	public int countIntegralLotteryCommodityPoolsList(SourceNicheCustomExample sourceNicheCustomExample) {
		return sourceNicheCustomMapper.countIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);
	}

	public List<SourceNicheCustom> selectIntegralLotteryCommodityPoolsList(SourceNicheCustomExample sourceNicheCustomExample) {
		return sourceNicheCustomMapper.selectIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);
	}
	
	public SourceNicheCustom sourceNicheCustomselectByPrimaryKey(Integer id) {
		return sourceNicheCustomMapper.sourceNicheCustomselectByPrimaryKey(id);
	}

	public HashMap<String,Object> selectWeightDetail(Integer id) {
		return sourceNicheCustomMapper.selectWeightDetail(id);
	}

	//资源位数据统计
	public   List<SourceNicheCustom>  selectDataStatisticsList(Map<String,Object> map) {
		return sourceNicheCustomMapper.selectDataStatisticsList(map);
	}
	//资源位店铺流量数据统计
	public   List<SourceNicheCustom>  selectShopDataStatisticsList(Map<String,Object> map) {
		return  sourceNicheCustomMapper.selectShopDataStatisticsList(map);
	}

	//积分商品查询
	public SourceNicheCustom selectIntegralProductCustomById(Integer id) {
		SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
		SourceNicheExample.Criteria criteria = sourceNicheCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andIdEqualTo(id);
		sourceNicheCustomExample.setLimitStart(0);
		sourceNicheCustomExample.setLimitSize(1);
		List<SourceNicheCustom> sourceNicheCustoms = this.selectIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);
		if(sourceNicheCustoms != null && sourceNicheCustoms.size() > 0){
			return sourceNicheCustoms.get(0);
		}else {
			return new SourceNicheCustom();
		}
	}

	//积分商品审核
	public void batchAuditIntegralProduct(HashMap<String, Object> paramMap) {
		sourceNicheCustomMapper.batchAuditIntegralProduct(paramMap);
	}


	public void removeIntegralProduct(Integer id, Integer staffID) {
		SourceNiche sourceNiche = this.selectByPrimaryKey(id);
		if(sourceNiche.getSeqNo() != null){
			HashMap<String, Object> paraMap = new HashMap<>();
			paraMap.put("auto_reduce","auto_reduce");
			paraMap.put("greaterThan", sourceNiche.getSeqNo());
			sourceNicheCustomMapper.updateIntegralLotterySeqNo(paraMap);
			sourceNiche.setId(id);
			sourceNiche.setStatus("1");
			sourceNiche.setSeqNo(null);
			sourceNiche.setUpdateBy(staffID);
			sourceNiche.setUpdateDate(new Date());
			this.updateByPrimaryKey(sourceNiche);
		}
	}

	public void updateIntegralLotterySeqNo(HttpServletRequest request, int countCommodityPools, Integer staffID) {
		String id = request.getParameter("id");
		Integer newSeqNo = Integer.valueOf(request.getParameter("seqNoNew"));
		//Integer oldSeqNo = Integer.valueOf(request.getParameter("seqNoOld"));
		SourceNiche sourceNicheOld = this.selectByPrimaryKey(Integer.valueOf(id));
		Integer oldSeqNo = sourceNicheOld.getSeqNo();

		if(newSeqNo > countCommodityPools){
			newSeqNo = countCommodityPools;
		}
		HashMap<String, Object> paraMap = new HashMap<>();
		if(newSeqNo.equals(oldSeqNo)){
			return;
		}else if (newSeqNo > oldSeqNo) {
			paraMap.put("auto_reduce","auto_reduce");
			paraMap.put("greaterThan", oldSeqNo);
			paraMap.put("lessThanOrEqualTo", newSeqNo);
		}else{
			paraMap.put("auto_increment","auto_increment");
			paraMap.put("greaterThanOrEqualTo", newSeqNo);
			paraMap.put("lessThan", oldSeqNo);
		}
		sourceNicheCustomMapper.updateIntegralLotterySeqNo(paraMap);
		HashMap<String, Object> paraMap2 = new HashMap<>();
		paraMap2.put("id", id);
		paraMap2.put("seqNo", newSeqNo);
		sourceNicheCustomMapper.updateIntegralLotterySeqNo(paraMap2);
	}

	public void integralProductFormattingSeqNo() {
		SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
		SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
		sourceNicheCustomCriteria.andDelFlagEqualTo("0");
		sourceNicheCustomCriteria.andAuditStatusEqualTo("1");//审核通过
		sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
		sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc");
		sourceNicheCustomCriteria.andTypeEqualTo("13");//积分抽奖
		sourceNicheCustomCriteria.andStockGreaterThan(0);
		sourceNicheCustomCriteria.andStatusEqualTo("0");
		List<SourceNicheCustom> sourceNicheCustoms = this.selectIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);
		HashMap<String, Object> paraMap = new HashMap<>();
		int seqNo = 1;
		for (SourceNicheCustom sourceNicheCustom : sourceNicheCustoms) {
			paraMap.put("id", sourceNicheCustom.getId());
			paraMap.put("seqNo", seqNo++);
			sourceNicheCustomMapper.updateIntegralLotterySeqNo(paraMap);
		}
	}

/*	public int selectTotalQuantitys(Integer id){
		return sourceNicheCustomMapper.selectTotalQuantitys(id);
	}
	
	public  Double selectTotalSales(Integer id){
		return sourceNicheCustomMapper.selectTotalSales(id);
	}*/
	
	    //添加至资源位管理表
		public void addProductlist(Integer staffID, String id,String sourceType) {
			String[] strs = id.split(",");
			Date date = new Date();
			for(String productid : strs) {
				SourceNiche sourceNiche=new SourceNiche();
				
				List<Integer> listproductid=new ArrayList<Integer>();
				listproductid.add(Integer.valueOf(productid));
				
				SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
				sourceNicheCustomExample.createCriteria().andDelFlagEqualTo("0").andLinkIdIn(listproductid).andTypeEqualTo(sourceType);
				List<SourceNicheCustom> lisNicheCustoms=sourceNicheCustomMapper.sourceNicheCustomselectByExample(sourceNicheCustomExample);
				if (lisNicheCustoms.size()>0) {
					sourceNiche.setUpdateBy(staffID);
					sourceNiche.setUpdateDate(date);
					sourceNiche.setStatus("0");
					sourceNiche.setType(sourceType);
					//直接通过审核,上线时间为当天
					sourceNiche.setAuditStatus("1");
					sourceNiche.setAuditBy(staffID);
					sourceNiche.setAuditDate(new Date());
					sourceNiche.setUpDate(new Date());
					sourceNiche.setWeightUpdateDate(new Date());
					sourceNicheMapper.updateByExampleSelective(sourceNiche, sourceNicheCustomExample);
				}else {
					sourceNiche.setLinkId(Integer.valueOf(productid));
					/*sourceNiche.setType("1");*/
					sourceNiche.setType(sourceType);
					sourceNiche.setSupportQuantity(0);
					sourceNiche.setCreateBy(staffID);
					sourceNiche.setCreateDate(date);
					sourceNiche.setStatus("0");
					sourceNiche.setDelFlag("0");
					
					//直接通过审核,上线时间为当天
					sourceNiche.setSource("0");
					sourceNiche.setAuditStatus("1");
					sourceNiche.setAuditBy(staffID);
					sourceNiche.setAuditDate(new Date());
					sourceNiche.setUpDate(new Date());
					sourceNiche.setWeightUpdateDate(new Date());
					sourceNicheMapper.insertSelective(sourceNiche);
				}
				
			}
		}
		
				public void addMchtinfoList(Integer staffID, String id) {
					String[] strs = id.split(",");
					Date date = new Date();
					for(String mhctinfoid : strs) {
						SourceNiche sourceNiche=new SourceNiche();
						List<Integer> listMchtinfoid=new ArrayList<Integer>();
						listMchtinfoid.add(Integer.valueOf(mhctinfoid));
						
						SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
						sourceNicheCustomExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andLinkIdIn(listMchtinfoid);
						List<SourceNicheCustom> lisNicheCustoms=sourceNicheCustomMapper.sourceNicheCustomselectByExample(sourceNicheCustomExample);
						if (lisNicheCustoms.size()>0) {
							sourceNiche.setUpdateBy(staffID);
							sourceNiche.setUpdateDate(date);
							sourceNiche.setStatus("0");
							sourceNicheMapper.updateByExampleSelective(sourceNiche, sourceNicheCustomExample);
						}else {
	
							sourceNiche.setLinkId(Integer.valueOf(mhctinfoid));
							sourceNiche.setType("2");
							sourceNiche.setCreateBy(staffID);
							sourceNiche.setCreateDate(date);
							sourceNiche.setStatus("0");
							sourceNiche.setDelFlag("0");
							sourceNicheMapper.insert(sourceNiche);
							
						}
						
						
					}
				}
				
				
				public void addLaxinProductlist(Integer staffID, String id) {//拉新分润推荐
					String[] strs = id.split(",");
					Date date = new Date();
					for(String productid : strs) {
						SourceNiche sourceNiche=new SourceNiche();
						
						List<Integer> listproductid=new ArrayList<Integer>();
						listproductid.add(Integer.valueOf(productid));
						
						SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
						sourceNicheCustomExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andLinkIdIn(listproductid);
						List<SourceNicheCustom> lisNicheCustoms=sourceNicheCustomMapper.sourceNicheCustomselectByExample(sourceNicheCustomExample);
						if (lisNicheCustoms.size()>0) {
							sourceNiche.setUpdateBy(staffID);
							sourceNiche.setUpdateDate(date);
							sourceNiche.setStatus("0");
							sourceNicheMapper.updateByExampleSelective(sourceNiche, sourceNicheCustomExample);
						}else {
							sourceNiche.setLinkId(Integer.valueOf(productid));
							sourceNiche.setType("3");
							sourceNiche.setSupportQuantity(0);
							sourceNiche.setCreateBy(staffID);
							sourceNiche.setCreateDate(date);
							sourceNiche.setStatus("0");
							sourceNiche.setDelFlag("0");
							sourceNicheMapper.insert(sourceNiche);
						}
						
					}
				}

	public List<HashMap<String,Object>> queryProductlist(HashMap<String, Object> paramMap){
			return sourceNicheCustomMapper.queryProductlist(paramMap);
	}
	public Integer queryProductlistCount(HashMap<String, Object> paramMap){
			return sourceNicheCustomMapper.queryProductlistCount(paramMap);
	}
	//添加至资源位管理表爆款推荐
	public void addProductlist12(Integer staffID, String id,String sourceType) {
		String[] strs = id.split(",");
		Date date = new Date();
		for(String productid : strs) {
			SourceNiche sourceNiche=new SourceNiche();

			Product product = productMapper.selectByPrimaryKey(Integer.parseInt(productid));

			SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
			sourceNicheCustomExample.createCriteria().andDelFlagEqualTo("0").andLinkIdEqualTo(Integer.parseInt(productid)).andTypeEqualTo(sourceType);
			List<SourceNicheCustom> lisNicheCustoms=sourceNicheCustomMapper.sourceNicheCustomselectByExample(sourceNicheCustomExample);
			if (lisNicheCustoms.size()>0) {
				sourceNiche.setMchtId(product.getMchtId());
				sourceNiche.setUpdateBy(staffID);
				sourceNiche.setUpdateDate(date);
				sourceNiche.setStatus("0");
				sourceNiche.setType(sourceType);
				//直接通过审核,上线时间为当天
				sourceNiche.setAuditStatus("1");
				sourceNiche.setAuditBy(staffID);
				sourceNiche.setAuditDate(new Date());
				sourceNiche.setUpDate(new Date());
				sourceNiche.setWeightUpdateDate(new Date());
				sourceNicheMapper.updateByExampleSelective(sourceNiche, sourceNicheCustomExample);
			}else {
				sourceNiche.setMchtId(product.getMchtId());
				sourceNiche.setLinkId(Integer.valueOf(productid));
				/*sourceNiche.setType("1");*/
				sourceNiche.setType(sourceType);
				sourceNiche.setSupportQuantity(0);
				sourceNiche.setCreateBy(staffID);
				sourceNiche.setCreateDate(date);
				sourceNiche.setStatus("0");
				sourceNiche.setDelFlag("0");

				//直接通过审核,上线时间为当天
				sourceNiche.setSource("0");
				sourceNiche.setAuditStatus("1");
				sourceNiche.setAuditBy(staffID);
				sourceNiche.setAuditDate(new Date());
				sourceNiche.setUpDate(new Date());
				sourceNiche.setWeightUpdateDate(new Date());
				sourceNicheMapper.insertSelective(sourceNiche);
			}

		}
	}

}
