package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ProductWeightCustomMapper;
import com.jf.dao.ProductWeightMapper;
import com.jf.entity.GradeWeightCnf;
import com.jf.entity.GradeWeightCnfExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductWeight;
import com.jf.entity.ProductWeightExample;
import com.jf.entity.SeasonWeightCnf;
import com.jf.entity.SeasonWeightCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ProductWeightService extends BaseService<ProductWeight, ProductWeightExample> {
	
	@Autowired
	private ProductWeightMapper productWeightMapper;
	
	@Autowired
	private ProductWeightCustomMapper productWeightCustomMapper;
	
	@Autowired
	private SaleWeightCnfService saleWeightCnfService;
	
	@Autowired
	private PvWeightCnfService pvWeightCnfService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private GradeWeightCnfService gradeWeightCnfService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private SeasonWeightCnfService seasonWeightCnfService;
	
	@Autowired
	public void setProductWeightMapper(ProductWeightMapper productWeightMapper) {
		this.setDao(productWeightMapper);
		this.productWeightMapper = productWeightMapper;
	}
	
	/**
	 * 更新商品等级权重
	 * @param productId
	 */
	public void updateProductWeights(Integer productId){
		if(productId==null){
			return;
		}

		Product product=productService.selectByPrimaryKey(productId);
		MchtInfo mchtInfo=mchtInfoService.selectByPrimaryKey(product.getMchtId());
		ProductWeightExample productWeightExample=new ProductWeightExample();
		productWeightExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
		List<ProductWeight> productWeights=this.selectByExample(productWeightExample);
		ProductWeight productWeight;
		if(productWeights==null||productWeights.size()==0){
			productWeight=new ProductWeight();
			productWeight.setProductId(productId);
			productWeight.setDelFlag("0");
			productWeight.setSaleWeight(0);
			productWeight.setPvWeight(0);
			productWeight.setSaleAmountWeight(0);
			productWeight.setCommentWeight(20);//无评论的默认为20
			if("1".equals(mchtInfo.getIsManageSelf())){//如果是自营人工权重设置为400
				productWeight.setManualWeight(400);
			}else {
				productWeight.setManualWeight(0);
			}
			productWeight.setCreateDate(new Date());
		}else{
			productWeight=productWeights.get(0);
		}

		//商家等级权重
		if(mchtInfo.getGrade()!=null){
			GradeWeightCnfExample gradeWeightCnfExample=new GradeWeightCnfExample();
			gradeWeightCnfExample.createCriteria().andDelFlagEqualTo("0").andGradeTypeEqualTo("1").andGradeEqualTo(mchtInfo.getGrade());
			List<GradeWeightCnf> gradeWeightCnfs=gradeWeightCnfService.selectByExample(gradeWeightCnfExample);
			if(gradeWeightCnfs!=null&&gradeWeightCnfs.size()>0){
				productWeight.setMchtGradeWeight(gradeWeightCnfs.get(0).getWeight());
			}else{
				productWeight.setMchtGradeWeight(0);
			}
		}else{
			productWeight.setMchtGradeWeight(0);
		}
		
		//品牌等级权重
		ProductBrand productBrand=productBrandService.selectByPrimaryKey(product.getBrandId());
		if(productBrand.getGrade()!=null){
			GradeWeightCnfExample gradeWeightCnfExample=new GradeWeightCnfExample();
			gradeWeightCnfExample.createCriteria().andDelFlagEqualTo("0").andGradeTypeEqualTo("2").andGradeEqualTo(productBrand.getGrade());
			List<GradeWeightCnf> gradeWeightCnfs=gradeWeightCnfService.selectByExample(gradeWeightCnfExample);
			if(gradeWeightCnfs!=null&&gradeWeightCnfs.size()>0){
				productWeight.setBrandGradeWeight(gradeWeightCnfs.get(0).getWeight());
			}else{
				productWeight.setBrandGradeWeight(0);
			}
		}else{
			productWeight.setBrandGradeWeight(0);
		}
		
		//更新季节等级权重
		if(!StringUtil.isEmpty(product.getSeason())){
			Calendar calendar = Calendar.getInstance();
			SeasonWeightCnfExample seasonWeightCnfExample=new SeasonWeightCnfExample();
			seasonWeightCnfExample.createCriteria().andDelFlagEqualTo("0").andProductSeasonEqualTo(product.getSeason()).andBeginMonthLessThanOrEqualTo(calendar.get(Calendar.MONTH)+1).andEndMonthGreaterThanOrEqualTo(calendar.get(Calendar.MONTH)+1);
			seasonWeightCnfExample.setOrderByClause("weight desc");
			List<SeasonWeightCnf> seasonWeightCnfs=seasonWeightCnfService.selectByExample(seasonWeightCnfExample);
			if(seasonWeightCnfs!=null&&seasonWeightCnfs.size()>0){
				productWeight.setSeasonWeight(seasonWeightCnfs.get(0).getWeight());
			}else{
				productWeight.setSeasonWeight(0);
			}
		}

		//点击量和销量权重每天定时更新，这里无需处理
		
		 this.saveProductWeight(productWeight);
		 
		 int totalWeight=productWeight.getSeasonWeight()+productWeight.getSaleWeight()+productWeight.getPvWeight()+productWeight.getMchtGradeWeight()+productWeight.getBrandGradeWeight()+productWeight.getSaleAmountWeight()+productWeight.getCommentWeight()+productWeight.getManualWeight();
		 Product product4Update=new Product();
		 product4Update.setId(productId);
		 product4Update.setWeights(totalWeight);
		 productService.updateByPrimaryKeySelective(product4Update);
	}
	
	public int saveProductWeight(ProductWeight productWeight){
		if(productWeight.getId()==null){
			return this.insertSelective(productWeight);
		}else{
			return this.updateByPrimaryKeySelective(productWeight);
		}
	}
}
