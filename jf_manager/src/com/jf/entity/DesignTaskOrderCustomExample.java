package com.jf.entity;

import java.util.List;

public class DesignTaskOrderCustomExample extends DesignTaskOrderExample {

    @Override
    public DesignTaskOrderCustomCriteria createCriteria() {
        DesignTaskOrderCustomCriteria criteria = new DesignTaskOrderCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    public class DesignTaskOrderCustomCriteria extends DesignTaskOrderExample.Criteria{

        public Criteria andMchtCodeEqualTo(String value) {
            addCriterion("mcht_code =", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andDStatusEqualTo(String value) {
            addCriterion("d.status =", value, "status");
            return (Criteria) this;
        }


        //like

        public Criteria andMchtCodeLike(String value) {
            addCriterion("mcht_code like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }


        public Criteria andMchtNameLike(String value) {
            addCriterion("concat(shop_name,company_name)like",value,"mchtName");
            return (Criteria) this;
        }

        public Criteria andDDelFlagIsNull() {
            addCriterion("d.del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDDelFlagIsNotNull() {
            addCriterion("d.del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDDelFlagEqualTo(String value) {
            addCriterion("d.del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagNotEqualTo(String value) {
            addCriterion("d.del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagGreaterThan(String value) {
            addCriterion("d.del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("d.del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagLessThan(String value) {
            addCriterion("d.del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagLessThanOrEqualTo(String value) {
            addCriterion("d.del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagLike(String value) {
            addCriterion("d.del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagNotLike(String value) {
            addCriterion("d.del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagIn(List<String> values) {
            addCriterion("d.del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagNotIn(List<String> values) {
            addCriterion("d.del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDDelFlagBetween(String value1, String value2) {
            addCriterion("d.del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria anddDDelFlagNotBetween(String value1, String value2) {
            addCriterion("d.del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
        
        public Criteria andPayStatusCustomEqualTo(String payStatus) {
            if ("1".equals(payStatus)) {
                this.addCriterion(" dto.pay_status = '1' and dto.id not in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0')");
            } else if ("2".equals(payStatus)) {
                this.addCriterion(" dto.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status != '2' and a.status != '3')");
            } else if ("3".equals(payStatus)) {
                this.addCriterion(" dto.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
            } else if ("4".equals(payStatus)) {
                this.addCriterion(" dto.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '3')");
            }
            return this;
        }

        public Criteria andDesignRefundOrderEqualTo() {
            this.addCriterion(" d.id in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
            return this;
        }

        public Criteria andDesignReceivablesEqualTo() {
            this.addCriterion(" d.id not in(select a.design_task_order_id from bu_design_task_refund_order a where a.del_flag = '0' and a.status = '2')");
            return this;
        }

        public Criteria andMchtNameLikeTo(String mchtName) {
            this.addCriterion(" d.mcht_id  in (select id from bu_mcht_info mi where mi.del_flag='0' and ( mi.shop_name like '" + mchtName + "' or mi.company_name like '" + mchtName + "' ))");
            return this;
        }

    }


}
