package com.jf.entity;

import java.math.BigDecimal;

public class SourceNicheCustomExample extends SourceNicheExample {

	@Override
	public SourceNicheCustomCriteria createCriteria() {
		SourceNicheCustomCriteria criteria = new SourceNicheCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
    public class SourceNicheCustomCriteria extends SourceNicheExample.Criteria {
		
    	public Criteria andpStatusEqualTo(String status) {
			addCriterion(" sn.link_id in (select p.id from bu_product p where sn.link_id =p.id  and p.del_flag='0' and p.status='"+status+"')");
			return this;
		}

    	public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" sn.link_id in (select p.id from bu_mcht_info mi,bu_product p where p.mcht_id=mi.id and sn.link_id =p.id and mi.del_flag='0' and mi.mcht_code='"+mchtCode+"')");
			return this;
		}
    	
    	public Criteria andMchtNameLikeTo(String mchtName) {
			addCriterion(" sn.link_id in (select p.id from bu_mcht_info mi,bu_product p where p.mcht_id=mi.id and sn.link_id =p.id and mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' or mi.shop_name like '"+mchtName+"'))");
	        return this;
	    }
		
    	public Criteria andProductBrandNameEqualTo(String productBrandName) {
			addCriterion(" sn.link_id in (select p.id from bu_product_brand pb,bu_product p where p.brand_id=pb.id and sn.link_id =p.id and pb.del_flag='0' and (pb.name='"+productBrandName+"' or pb.name_zh='"+productBrandName+"' or pb.name_en='"+productBrandName+"'))");
			return this;
		}
    	
    	public Criteria andArtNoEqualTo(String artNo) {
			addCriterion(" sn.link_id in (select p.id from bu_product p where sn.link_id =p.id  and p.del_flag='0' and p.art_no='"+artNo+"')");
			return this;
		}
    	
    	public Criteria andNameLike(String name) {
			addCriterion(" sn.link_id in (select p.id from bu_product p where sn.link_id =p.id  and p.del_flag='0' and p.name like '"+name+"')");
			return this;
		}

		public Criteria andMchtCodesIn(String mchtCode) {
			addCriterion(" sn.link_id in (select bp.id from bu_product bp,bu_mcht_info bmi where bp.id = sn.link_id and bmi.id = bp.mcht_id and bp.del_flag = '0' and bmi.del_flag='0' and bmi.mcht_code = '"+mchtCode+"')");
			return this;
		}

		public Criteria andMchtNameIn(String mchtName) {
			addCriterion(" sn.link_id in (select bp.id from bu_product bp,bu_mcht_info bmi where bp.id = sn.link_id and bmi.id = bp.mcht_id  and bp.del_flag = '0' and bmi.del_flag='0' and ( bmi.shop_name like '"+mchtName+"'  or bmi.company_name like '"+mchtName+"'))");
			return this;
		}

    	public Criteria andProductTypeEuqalTo(String productTypeId) {
			addCriterion(" sn.link_id in (select bp.id from bu_product bp,bu_mcht_info mi,bu_mcht_product_type mpt where bp.id = sn.link_id and bp.mcht_id = mi.id and mpt.mcht_id = mi.id and mpt.is_main = '1' and mpt.del_flag = '0' and bp.del_flag = '0' and mi.del_flag = '0' and mpt.product_type_id= "+productTypeId+")");
			return this;
		}
    	public Criteria andCodeEqualTo(String code) {
			addCriterion(" sn.link_id in (select p.id from bu_product p where sn.link_id =p.id  and p.del_flag='0' and p.code='"+code+"')");
			return this;
		}
    	
    	public Criteria andSuitSexEqualToManOrWomen(String suitSex) {
			addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id  and p.del_flag='0' and (p.suit_sex = '"+suitSex+"' or p.suit_sex = '11'))");
			return this;
		}
    	
    	public Criteria andSuitSexEqualTo(String suitSex) {
			addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and p.del_flag='0' and p.suit_sex = '"+suitSex+"')");
			return this;
		}
    	
    	public Criteria andSuitGroupLikeTo(String suitGroup) {
			if(suitGroup.equals("010")){
				addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and (p.del_flag='0' p.suit_group = '010' or p.suit_group = '011' or p.suit_group = '110' or p.suit_group = '111'))");
			}else if(suitGroup.equals("001")){
				addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and (p.suit_group = '001' or p.suit_group = '011' or p.suit_group = '101' or p.suit_group = '111'))");
			}
	        return this;
	    }
    	
    	public Criteria andSuitGroupLike(String suitGroup) {
			addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and p.del_flag='0' and p.suit_group like '"+suitGroup+"')");
			return this;
		}
    	
    	public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal salePrice) {
			addCriterion(" (select min(pi.sale_price) from bu_product_item pi,bu_product p where pi.del_flag='0' and pi.product_id=p.id and sn.link_id =p.id)>="+salePrice);
			return this;
		}
    	
    	public Criteria andSalePriceLessThanOrEqualTo(BigDecimal salePriceEnd) {
			addCriterion(" (select max(pi.sale_price) from bu_product_item pi,bu_product p where pi.del_flag='0' and pi.product_id=p.id and sn.link_id =p.id)<="+salePriceEnd);
			return this;
		}
    	  	
    	public Criteria andProductTypeAll(String productTypeIds) {
			addCriterion( "sn.link_id in(select p.id from bu_product p where sn.link_id =p.id and p.del_flag='0' and p.product_type_id in ("+productTypeIds+"))" );
			return this;
		}
    	
    	public Criteria andmchtCodeEqualTo(String mchtCode) {
			addCriterion( "sn.link_id in(select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.mcht_code = '"+mchtCode+"')" );
			return this;
		}
    	
    	public Criteria andmchtNameLikeTo(String mchtName) {
			addCriterion(" sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and ( mi.short_name like '"+mchtName+"' or mi.company_name like '"+mchtName+"' or mi.shop_name like '"+mchtName+"'))");
	        return this;
	    }

    	public Criteria andproductBrandNameEqualTo(String productBrandName) {
			addCriterion(" sn.link_id in (select mpb.mcht_id from bu_product_brand pb,bu_mcht_product_brand mpb where mpb.product_brand_id=pb.id and sn.link_id =mpb.mcht_id and mpb.del_flag='0' and pb.del_flag='0' and (pb.name='"+productBrandName+"' or pb.name_zh='"+productBrandName+"' or pb.name_en='"+productBrandName+"'))");
			return this;
		}
    	
    	public Criteria andproductTypeIdEqualTo(String productTypeId) {
			addCriterion(" sn.link_id in (select mpt.mcht_id from bu_mcht_product_type mpt,bu_product_type bpt where mpt.mcht_id = sn.link_id and mpt.is_main = '1' and mpt.del_flag = '0' and bpt.id = mpt.product_type_id and bpt.status = '1' and bpt.del_flag = '0' and mpt.product_type_id='"+productTypeId+"')");
	        return this;
	    }
    	
    	public Criteria andmchtStatusDescEqualTo(String mchtStatusDesc) {
			if(mchtStatusDesc.equals("1")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.status = '"+1+"')");
			}else if(mchtStatusDesc.equals("2")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.status = '"+2+"')");
			}else if(mchtStatusDesc.equals("3")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.status = '"+3+"')");
			}
	        return this;
	    }
		
    	public Criteria andgradeDescEqualTo(String gradeDesc) {
			if(gradeDesc.equals("1")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.grade = '"+1+"')");
			}else if(gradeDesc.equals("2")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.grade = '"+2+"')");
			}else if(gradeDesc.equals("3")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.grade = '"+3+"')");
			}else if(gradeDesc.equals("4")){
				addCriterion("sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id and mi.del_flag='0' and mi.grade = '"+4+"')");
			}
	        return this;
	    }
    	
    	public Criteria andDegreeAuditriskDescEqualTo(String degreeAuditriskDesc) {
			if(degreeAuditriskDesc.equals("1")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.audit_risk = '"+1+"')");
			}else if(degreeAuditriskDesc.equals("2")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.audit_risk = '"+2+"')");
			}else if(degreeAuditriskDesc.equals("3")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.audit_risk = '"+3+"')");
			}
	        return this;
	    }
    	
    	
    	public Criteria anddegreeAdaptabilityDescEqualTo(String degreeAdaptabilityDesc) {
			if(degreeAdaptabilityDesc.equals("1")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.degree_adaptability = '"+1+"')");
			}else if(degreeAdaptabilityDesc.equals("2")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.degree_adaptability = '"+2+"')");
			}else if(degreeAdaptabilityDesc.equals("3")){
				addCriterion("sn.link_id in (select mo.mcht_id from bu_mcht_optimize mo where sn.link_id =mo.mcht_id and mo.del_flag='0' and mo.degree_adaptability = '"+3+"')");
			}
	        return this;
	    }
    	
    	public Criteria andshopStatusEqualTo(String shopStatusDesc) {
			addCriterion(" sn.link_id in (select mi.id from bu_mcht_info mi where sn.link_id =mi.id  and mi.del_flag='0' and mi.shop_status='"+shopStatusDesc+"')");
			return this;
		}
    	
    	//更新和创建时间
 	   public Criteria andStartUpdatedateBeginDate(String value) {
 			 addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and sn.type='3' and p.del_flag='0' and (IFNULL(p.update_date,p.create_date) >='"+value+"'))");
 			 return this;
 	    }
 				
 	  public Criteria andEndUpdatedateBeginDate(String value) {
 		 addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id =p.id and sn.type='3' and p.del_flag='0' and (IFNULL(p.update_date,p.create_date) <='"+value+"'))");
 			 return this;
 	   }
 	  
 	  //=====
 	  
 	 public Criteria andproductbrandNameLike(String productBrandName) {
			addCriterion(" sn.link_id in (select p.id from bu_product_brand pb,bu_product p where p.brand_id=pb.id and sn.link_id =p.id and pb.del_flag='0' and (pb.name like'"+productBrandName+"' or pb.name_zh like'"+productBrandName+"' or pb.name_en like'"+productBrandName+"'))");
			return this;
		}
 
 	 public Criteria andsourceProductTypeIdEqualTo(Integer productTypeId) {//商品一级类目ID
			addCriterion(" sn.link_id in (select p.id from bu_product p where p.id = sn.link_id and p.product_type1_id = '"+productTypeId+"'  )");
	        return this;
	    }
 	  

 	 public Criteria andCodesIn(String codes) {
			addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id=p.id and p.del_flag='0' and p.code in ("+codes+"))");
	        return this;
	    }

 	 public Criteria andProductTypeIdEuqalTo(Integer productTypeId) {
			addCriterion(" EXISTS(select p.id from bu_product p where sn.link_id=p.id and p.del_flag='0' and p.product_type1_id = "+productTypeId+")");
	        return this;
	    }
 	 
 	 public Criteria andArtNosIn(String artNos) {
 		addCriterion("sn.link_id in (select p.id from bu_product p where sn.link_id=p.id and p.del_flag='0' and p.art_no in ('"+artNos+"'))");
	        return this;
	    }
 	 
 	 public Criteria andBannerStatusEuqalTo(Integer bannerId) {//banner中已经上线
			addCriterion("sn.id in (select brp.source_niche_id  from bu_banner_recommend_product brp where  sn.id = brp.source_niche_id  and  brp.del_flag='0' and  brp.`status` = '1' and brp.banner_id="+bannerId+" )");
	        return this;
	    }
 	 
 	 public Criteria andProductTypeTwoLike(String twoType) {
			addCriterion(" sn.link_id in (select p.id from bu_product p,bu_product_type pt where p.product_type2_id=pt.id and p.id = sn.link_id and pt.t_level='2' and pt.del_flag='0' and pt.name like '"+twoType+"') ");
	        return this;
	    }
 	 
 	 public Criteria andBannerStatusNotIn(Integer bannerId) {
			addCriterion("sn.id not in (select brp.source_niche_id  from bu_banner_recommend_product brp where  sn.id = brp.source_niche_id  and  brp.del_flag='0' and   brp.`status` = '1' and brp.banner_id="+bannerId+" )");
	        return this;
	    }

 	 public Criteria andLotteryCountMinLessThen(Integer lotteryCountMin) {
			addCriterion(" (SELECT count( id ) FROM bu_member_lottery ml WHERE sn.link_id = ml.product_id AND ml.type = 3 AND ml.del_flag = '0' ) > "+lotteryCountMin);
	        return this;
	    }

 	 public Criteria andLotteryCountMinGreaterThen(Integer lotteryCountMax) {
			addCriterion(" (SELECT count( id ) FROM bu_member_lottery ml WHERE sn.link_id = ml.product_id AND ml.type = 3 AND ml.del_flag = '0' ) < "+lotteryCountMax);
	        return this;
	    }

 	 
	}
	
}
