package com.jf.entity;

import com.jf.entity.MchtContactExample.Criteria;


public class MchtContactCustomExample extends MchtContactExample {

	@Override
	public MchtContactCustomCriteria createCriteria() {
		MchtContactCustomCriteria criteria = new MchtContactCustomCriteria();
		if(oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	
	public class MchtContactCustomCriteria extends MchtContactExample.Criteria {
		
	public Criteria andMchtContactTypeEqualTo(Integer staffID) {//可查看商家联系人信息
			addCriterion(" EXISTS(select s.mcht_contact_type from bu_staff_mcht_contact_permission s where s.mcht_contact_type=t.contact_type and s.del_flag='0' and s.staff_id ="+staffID+" )");
			return this;
		}
	public Criteria andMchtDockingPersonAudit() {//对接人对应的店铺法务资质总审=通过
		addCriterion(" (select total_audit_status from bu_mcht_info m where m.id = t.mcht_id)=2");
		return this;
	}
	public Criteria andMchtCodeEqualTo(String mchtCode) {//根据商家序号进行搜索
		addCriterion(" (select mcht_code from bu_mcht_info m where m.id=t.mcht_id ) = '"+mchtCode+"' ");
		return this;
	}
	public Criteria andNameLike(String name) {//根据名称进行模糊搜索
		addCriterion(" (( SELECT shop_name FROM bu_mcht_info m WHERE m.id = t.mcht_id ) LIKE concat('%','"+name+"','%') OR ( SELECT company_name FROM bu_mcht_info m WHERE m.id = t.mcht_id ) LIKE concat('%','"+name+"','%'))");
		return this;
	}
	public Criteria andProductTypeEqualTo(Integer productType) {
		addCriterion(" mcht_id in(select mpt.mcht_id from `bu_mcht_product_type` mpt where is_main='1' and del_flag='0' and mpt.product_type_id = "+productType+")");
		return this;
	}
	public Criteria andplatformContactIn(Integer platformContact) {
		addCriterion(" ( SELECT staff_id FROM bu_platform_contact p WHERE p.contact_type = '7' AND p.id IN ( SELECT mp.platform_contact_id FROM bu_mcht_platform_contact mp WHERE mp.mcht_id = t.mcht_id AND mp.STATUS = 1 AND del_flag = 0 ) ) = "+platformContact+" ");
		return this;
	}
	
	}
}
