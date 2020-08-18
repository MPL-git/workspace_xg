package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jf.entity.AdPvStatisticalCustomExample.AdPvStatisticalCustomCriteria;

public class MchtPvStatisticalCustomExample extends MchtPvStatisticalExample{
	@Override
    public MchtPvStatisticalCustomCriteria createCriteria() {
		MchtPvStatisticalCustomCriteria criteria = new MchtPvStatisticalCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MchtPvStatisticalCustomCriteria extends MchtPvStatisticalExample.Criteria{

		//商家序号
		public void andMchtCodeEqualTo(String mchtCode) {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select b.mcht_code from bu_mcht_info b where b.id = a.mcht_id and b.del_flag = '0' and b.mcht_code = '"+mchtCode+"')");
		}

		//商家名称
		public void andShortNameEqualTo(String shortName) {
			// TODO Auto-generated method stub
			addCriterion("EXISTS(select b.shop_name from bu_mcht_info b where b.id = a.mcht_id and b.del_flag = '0' and (b.shop_name like '%"+shortName+"%' or b.company_name like '%"+shortName+"%'))");
		}

		//主营类目
		public void andProductTypeIdEqualTo(String productTypeId) {
			// TODO Auto-generated method stub
			addCriterion("EXISTS(select b.name from bu_product_type b,bu_mcht_product_type c where c.mcht_id = a.mcht_id and c.product_type_id = b.id and c.is_main = '1' and c.status = '1' and b.del_flag='0' and c.del_flag='0' and  c.product_type_id = "+productTypeId+")");
		}
		
	}
}