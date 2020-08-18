package com.jf.entity;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MchtProductBrandCustomExample extends MchtProductBrandExample{
	
	@Override
    public MchtProductBrandCustomCriteria createCriteria() {
		MchtProductBrandCustomCriteria criteria = new MchtProductBrandCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtProductBrandCustomCriteria extends MchtProductBrandExample.Criteria{
		public Criteria andProductTypeEqualTo(Integer productTypeId) {
			addCriterion(" EXISTS(select pb.id from bu_product_brand pb where pb.id=product_brand_id and FIND_IN_SET("+productTypeId+",pb.product_type_group))");
			return this;
		}
		
		public Criteria andSearchKeyWroldLikeTo(String searchKeyWorld) {
			addCriterion(" EXISTS(select id from bu_product_brand pb where pb.id=product_brand_id and pb.del_flag='0' and (pb.`name` like '"+searchKeyWorld+"' or pb.name_en like '"+searchKeyWorld+"' or pb.name_zh like '"+searchKeyWorld+"'))");
			return this;
		}

		public Criteria andProductBrandIdIsNotEqualTo() {
			addCriterion("(product_brand_id>0 or product_brand_id is NULL)");
			return this;
		}
	}
}