package com.jf.entity;

import java.util.Date;





public class CommentCustomExample extends CommentExample{
	
	@Override
    public CommentCustomCriteria createCriteria() {
		CommentCustomCriteria criteria = new CommentCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class CommentCustomCriteria extends CommentExample.Criteria{
		public Criteria andProductCodeEqualTo(String code) {
			addCriterion("t.product_id = (select p.id FROM bu_product p WHERE p.del_flag = '0' and p.code = '"+code+"')");
	        return this;
	    }
		
		public Criteria andBrandIdEqualTo(Integer brandId) {
			addCriterion("EXISTS(select id FROM bu_product p WHERE t.product_id = p.id and p.brand_id = "+brandId+")");
			return this;
		}
		
		public Criteria andHasPic() {
			addCriterion("EXISTS(select cp.id from bu_comment_pic cp where cp.del_flag='0' and cp.comment_id = t.id and cp.pic_type = '1')");
			return this;
		}

		public Criteria andIsAllowModifyCommentEqualTo(String isAllowModifyComment) {
			addCriterion("EXISTS(select so.id from bu_sub_order so where so.del_flag='0' and so.id = t.sub_order_id and so.is_allow_modify_comment = "+isAllowModifyComment+")");
			return this;
		}
		
		public Criteria andSubOrderCodeEqualTo(String subOrderCode) {
			addCriterion("EXISTS(select so.id from bu_sub_order so where so.del_flag='0' and so.id = t.sub_order_id and so.sub_order_code = '"+subOrderCode+"')");
			return this;
		}
		
		public Criteria andOrderCreateDateGreaterThanOrEqualTo(String dateBegin) {
			addCriterion("EXISTS(select so.id from bu_sub_order so where so.del_flag='0' and so.id = t.sub_order_id and so.create_date >= '"+dateBegin+"')");
			return this;
		}

		public Criteria andOrderCreateDateLessThanOrEqualTo(String dateEnd) {
			addCriterion("EXISTS(select so.id from bu_sub_order so where so.del_flag='0' and so.id = t.sub_order_id and so.create_date <= '"+dateEnd+"')");
			return this;
		}
	}
}