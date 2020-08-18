package com.jf.entity;

public class MemberFootprintCustomExample extends MemberFootprintExample{
	
	@Override
    public MemberFootprintCustomCriteria createCriteria() {
		MemberFootprintCustomCriteria criteria = new MemberFootprintCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class MemberFootprintCustomCriteria extends MemberFootprintExample.Criteria{
		public Criteria andNickLike(String nick) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.nick like CONCAT('%', '"+nick+"', '%'))");
			return this;
		}
		
		public Criteria andMobileEqualTo(String mobile) {
			addCriterion(" member_id in(select mi.id from bu_member_info mi where mi.id=member_id and mi.del_flag='0' and mi.mobile='"+mobile+"')");
			return this;
		}
		
		public Criteria andProductTypeEqualTo(String productTypeId) {
			addCriterion(" EXISTS(select b.id from bu_product b, bu_product_type c, bu_product_type d where b.id = product_id and c.id = b.product_type_id and d.id = c.parent_id and d.parent_id = "+ productTypeId +" )");
			return this;
		}
		
		public Criteria andProductCodeEqualTo(String productCode) {
			addCriterion(" product_id in(select b.id from bu_product b where b.id = product_id and b.code = "+ productCode +" )");
			return this;
		}
		
	}
}