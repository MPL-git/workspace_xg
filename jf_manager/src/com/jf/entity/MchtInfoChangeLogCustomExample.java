package com.jf.entity;

public class MchtInfoChangeLogCustomExample extends MchtInfoChangeLogExample {

	@Override
	public MchtInfoChangeLogCustomCriteria createCriteria() {
		MchtInfoChangeLogCustomCriteria criteria = new MchtInfoChangeLogCustomCriteria();
		if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
	}
	
	public class MchtInfoChangeLogCustomCriteria extends MchtInfoChangeLogExample.Criteria {
		
		/**
		 * 
		 * @Title andCompanyNameEqualTo   
		 * @Description TODO(公司名称)   
		 * @author pengl
		 * @date 2018年3月14日 上午11:39:45
		 */
		public Criteria andCompanyNameEqualTo(String companyName) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.company_name = '"+ companyName +"' )");
	        return this;
	    }

		/**
		 * 
		 * @Title andMchtCodeEqualTo   
		 * @Description TODO(商家序号)   
		 * @author pengl
		 * @date 2018年3月14日 上午11:41:26
		 */
		public Criteria andMchtCodeEqualTo(String mchtCode) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.mcht_code = '"+ mchtCode +"' )");
			return this;
		}
		
		/**
		 * 
		 * @Title andProductTypeIdEqualTo   
		 * @Description TODO(主营类目)   
		 * @author pengl
		 * @date 2018年3月23日 下午2:19:06
		 */
		public Criteria andProductTypeIdEqualTo(String productTypeId) {
			addCriterion(" EXISTS(select mt.id from bu_mcht_product_type mt where mt.del_flag = '0' and mt.is_main = '1' and mt.status = '1' and mt.mcht_id = t.mcht_id and mt.product_type_id = " + productTypeId + ")");
			return this;
		}
		
		/**
		 * 
		 * @Title andMchtStatusEqualTo   
		 * @Description TODO(合作状态)   
		 * @author pengl
		 * @date 2018年3月23日 下午2:25:00
		 */
		public Criteria andMchtStatusEqualTo(String mchtStatus) {
			addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = t.mcht_id and mi.status = '"+ mchtStatus +"' )");
			return this;
		}
		
	}
}
