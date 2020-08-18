package com.jf.entity;


public class SvipProductRecommendCustomExample extends SvipProductRecommendExample{
	
	@Override
    public SvipProductRecommendCustomCriteria createCriteria() {
		SvipProductRecommendCustomCriteria criteria = new SvipProductRecommendCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SvipProductRecommendCustomCriteria extends SvipProductRecommendExample.Criteria{
		
		public Criteria andAllDelflagNotNull(){//删除标志都为'0' 且  SVIP商品推荐表的product_id = 商品表的id
			addCriterion(" t.product_id = p.id and t.del_flag= '0' and p.del_flag= '0'");
			return this;
		}
		
		public Criteria andNameLike(String name){
			addCriterion(" p.name like '"+name+"'");
			return this;
		}
		
		public Criteria andCodeEqualto(String code){
			addCriterion(" p.code = '"+code+"'");
			return this;
		}
		
		public Criteria andProductType1IdEqualto(Integer ProductType1Id){
			addCriterion(" p.product_type1_id = "+ProductType1Id+"");
			return this;
		}
		
		public Criteria andSvipProductRecommendSourceEqualTo(String source){
			addCriterion(" t.source = "+source+"");
			return this;
		}
		
		public Criteria andMchtCodeEqualto(String mchtCode){
			addCriterion(" EXISTS(select bmi.id from bu_mcht_info bmi where p.mcht_id = bmi.id and bmi.mcht_code = '"+mchtCode+"')");
			return this;
		}
		
	}
}