package com.jf.entity;


import java.util.Date;

public class DesignTaskRefundOrderCustomExample extends DesignTaskRefundOrderExample{
	
	@Override
    public DesignTaskRefundOrderCustomCriteria createCriteria() {
		DesignTaskRefundOrderCustomCriteria criteria = new DesignTaskRefundOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class DesignTaskRefundOrderCustomCriteria extends DesignTaskRefundOrderExample.Criteria{


        public Criteria andTDelFlagEqualTo(String value) {
            addCriterion("t.del_flag =", value, "delFlag");
            return  this;
        }

        public Criteria andTStatusEqualTo(String value) {
            addCriterion("t.status =", value, "status");
            return  this;
        }

        public Criteria andTCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("t.create_date >=", value, "createDate");
            return  this;
        }

        public Criteria andTCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("t.create_date <=", value, "createDate");
            return  this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andMchtCodeEqualTo(String value) {
            addCriterion("mcht_code =", value, "mchtCode");
            return  this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return  this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andDStatusEqualTo(String value) {
            addCriterion("d.status =", value, "status");
            return this;
        }




        //like


        public DesignTaskRefundOrderCustomExample.Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return  this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andMchtCodeLike(String value) {
            addCriterion("mcht_code like", value, "mchtCode");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andPaymentNoLike(String value) {
            addCriterion("payment_no like", value, "paymentNo");
            return this;
        }


        public DesignTaskRefundOrderCustomExample.Criteria andMchtNameLike(String value) {
            addCriterion("concat(shop_name,company_name)like",value,"mchtName");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andTaskTypeEqualTo(String value) {
            addCriterion("d.task_type =", value, "taskType");
            return (Criteria) this;
        }


        public DesignTaskRefundOrderCustomExample.Criteria andDesignRefundOrderEqualTo() {
            this.addCriterion(" d.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andDesignReceivablesEqualTo() {
            this.addCriterion(" d.id not in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
            return this;
        }

        public DesignTaskRefundOrderCustomExample.Criteria andMchtNameLikeTo(String mchtName) {
            this.addCriterion(" d.mcht_id  in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.shop_name like '" + mchtName + "' or mi.company_name like '" + mchtName + "' ))");
            return this;
        }
		
	}
}