package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CustomerServiceOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize=limitSize;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNull() {
            addCriterion("sub_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("sub_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(Integer value) {
            addCriterion("sub_order_id =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(Integer value) {
            addCriterion("sub_order_id <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(Integer value) {
            addCriterion("sub_order_id >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(Integer value) {
            addCriterion("sub_order_id <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<Integer> values) {
            addCriterion("sub_order_id in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<Integer> values) {
            addCriterion("sub_order_id not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id not between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNull() {
            addCriterion("order_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNotNull() {
            addCriterion("order_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdEqualTo(Integer value) {
            addCriterion("order_dtl_id =", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotEqualTo(Integer value) {
            addCriterion("order_dtl_id <>", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThan(Integer value) {
            addCriterion("order_dtl_id >", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id >=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThan(Integer value) {
            addCriterion("order_dtl_id <", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id <=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIn(List<Integer> values) {
            addCriterion("order_dtl_id in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotIn(List<Integer> values) {
            addCriterion("order_dtl_id not in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id not between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(String value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(String value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(String value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(String value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLike(String value) {
            addCriterion("service_type like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotLike(String value) {
            addCriterion("service_type not like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<String> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<String> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(String value1, String value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(String value1, String value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andProStatusIsNull() {
            addCriterion("pro_status is null");
            return (Criteria) this;
        }

        public Criteria andProStatusIsNotNull() {
            addCriterion("pro_status is not null");
            return (Criteria) this;
        }

        public Criteria andProStatusEqualTo(String value) {
            addCriterion("pro_status =", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotEqualTo(String value) {
            addCriterion("pro_status <>", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusGreaterThan(String value) {
            addCriterion("pro_status >", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pro_status >=", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLessThan(String value) {
            addCriterion("pro_status <", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLessThanOrEqualTo(String value) {
            addCriterion("pro_status <=", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLike(String value) {
            addCriterion("pro_status like", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotLike(String value) {
            addCriterion("pro_status not like", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusIn(List<String> values) {
            addCriterion("pro_status in", values, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotIn(List<String> values) {
            addCriterion("pro_status not in", values, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusBetween(String value1, String value2) {
            addCriterion("pro_status between", value1, value2, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotBetween(String value1, String value2) {
            addCriterion("pro_status not between", value1, value2, "proStatus");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNull() {
            addCriterion("deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNotNull() {
            addCriterion("deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountEqualTo(BigDecimal value) {
            addCriterion("deposit_amount =", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotEqualTo(BigDecimal value) {
            addCriterion("deposit_amount <>", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThan(BigDecimal value) {
            addCriterion("deposit_amount >", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_amount >=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThan(BigDecimal value) {
            addCriterion("deposit_amount <", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_amount <=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIn(List<BigDecimal> values) {
            addCriterion("deposit_amount in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotIn(List<BigDecimal> values) {
            addCriterion("deposit_amount not in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_amount between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_amount not between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyIsNull() {
            addCriterion("mcht_express_company is null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyIsNotNull() {
            addCriterion("mcht_express_company is not null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyEqualTo(String value) {
            addCriterion("mcht_express_company =", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyNotEqualTo(String value) {
            addCriterion("mcht_express_company <>", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyGreaterThan(String value) {
            addCriterion("mcht_express_company >", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_express_company >=", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyLessThan(String value) {
            addCriterion("mcht_express_company <", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("mcht_express_company <=", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyLike(String value) {
            addCriterion("mcht_express_company like", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyNotLike(String value) {
            addCriterion("mcht_express_company not like", value, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyIn(List<String> values) {
            addCriterion("mcht_express_company in", values, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyNotIn(List<String> values) {
            addCriterion("mcht_express_company not in", values, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyBetween(String value1, String value2) {
            addCriterion("mcht_express_company between", value1, value2, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("mcht_express_company not between", value1, value2, "mchtExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIsNull() {
            addCriterion("mcht_express_no is null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIsNotNull() {
            addCriterion("mcht_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoEqualTo(String value) {
            addCriterion("mcht_express_no =", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotEqualTo(String value) {
            addCriterion("mcht_express_no <>", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoGreaterThan(String value) {
            addCriterion("mcht_express_no >", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_express_no >=", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLessThan(String value) {
            addCriterion("mcht_express_no <", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLessThanOrEqualTo(String value) {
            addCriterion("mcht_express_no <=", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoLike(String value) {
            addCriterion("mcht_express_no like", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotLike(String value) {
            addCriterion("mcht_express_no not like", value, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoIn(List<String> values) {
            addCriterion("mcht_express_no in", values, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotIn(List<String> values) {
            addCriterion("mcht_express_no not in", values, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoBetween(String value1, String value2) {
            addCriterion("mcht_express_no between", value1, value2, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtExpressNoNotBetween(String value1, String value2) {
            addCriterion("mcht_express_no not between", value1, value2, "mchtExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyIsNull() {
            addCriterion("member_express_company is null");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyIsNotNull() {
            addCriterion("member_express_company is not null");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyEqualTo(String value) {
            addCriterion("member_express_company =", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyNotEqualTo(String value) {
            addCriterion("member_express_company <>", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyGreaterThan(String value) {
            addCriterion("member_express_company >", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("member_express_company >=", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyLessThan(String value) {
            addCriterion("member_express_company <", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("member_express_company <=", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyLike(String value) {
            addCriterion("member_express_company like", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyNotLike(String value) {
            addCriterion("member_express_company not like", value, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyIn(List<String> values) {
            addCriterion("member_express_company in", values, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyNotIn(List<String> values) {
            addCriterion("member_express_company not in", values, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyBetween(String value1, String value2) {
            addCriterion("member_express_company between", value1, value2, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("member_express_company not between", value1, value2, "memberExpressCompany");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoIsNull() {
            addCriterion("member_express_no is null");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoIsNotNull() {
            addCriterion("member_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoEqualTo(String value) {
            addCriterion("member_express_no =", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoNotEqualTo(String value) {
            addCriterion("member_express_no <>", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoGreaterThan(String value) {
            addCriterion("member_express_no >", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("member_express_no >=", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoLessThan(String value) {
            addCriterion("member_express_no <", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoLessThanOrEqualTo(String value) {
            addCriterion("member_express_no <=", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoLike(String value) {
            addCriterion("member_express_no like", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoNotLike(String value) {
            addCriterion("member_express_no not like", value, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoIn(List<String> values) {
            addCriterion("member_express_no in", values, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoNotIn(List<String> values) {
            addCriterion("member_express_no not in", values, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoBetween(String value1, String value2) {
            addCriterion("member_express_no between", value1, value2, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMemberExpressNoNotBetween(String value1, String value2) {
            addCriterion("member_express_no not between", value1, value2, "memberExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNull() {
            addCriterion("mcht_settle_order_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNotNull() {
            addCriterion("mcht_settle_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id =", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <>", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThan(Integer value) {
            addCriterion("mcht_settle_order_id >", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id >=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThan(Integer value) {
            addCriterion("mcht_settle_order_id <", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id not in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id between", value1, value2, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id not between", value1, value2, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andInitiatorIsNull() {
            addCriterion("initiator is null");
            return (Criteria) this;
        }

        public Criteria andInitiatorIsNotNull() {
            addCriterion("initiator is not null");
            return (Criteria) this;
        }

        public Criteria andInitiatorEqualTo(String value) {
            addCriterion("initiator =", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotEqualTo(String value) {
            addCriterion("initiator <>", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorGreaterThan(String value) {
            addCriterion("initiator >", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorGreaterThanOrEqualTo(String value) {
            addCriterion("initiator >=", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLessThan(String value) {
            addCriterion("initiator <", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLessThanOrEqualTo(String value) {
            addCriterion("initiator <=", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorLike(String value) {
            addCriterion("initiator like", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotLike(String value) {
            addCriterion("initiator not like", value, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorIn(List<String> values) {
            addCriterion("initiator in", values, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotIn(List<String> values) {
            addCriterion("initiator not in", values, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorBetween(String value1, String value2) {
            addCriterion("initiator between", value1, value2, "initiator");
            return (Criteria) this;
        }

        public Criteria andInitiatorNotBetween(String value1, String value2) {
            addCriterion("initiator not between", value1, value2, "initiator");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyIsNull() {
            addCriterion("is_allow_mcht_modify is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyIsNotNull() {
            addCriterion("is_allow_mcht_modify is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyEqualTo(String value) {
            addCriterion("is_allow_mcht_modify =", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyNotEqualTo(String value) {
            addCriterion("is_allow_mcht_modify <>", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyGreaterThan(String value) {
            addCriterion("is_allow_mcht_modify >", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyGreaterThanOrEqualTo(String value) {
            addCriterion("is_allow_mcht_modify >=", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyLessThan(String value) {
            addCriterion("is_allow_mcht_modify <", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyLessThanOrEqualTo(String value) {
            addCriterion("is_allow_mcht_modify <=", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyLike(String value) {
            addCriterion("is_allow_mcht_modify like", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyNotLike(String value) {
            addCriterion("is_allow_mcht_modify not like", value, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyIn(List<String> values) {
            addCriterion("is_allow_mcht_modify in", values, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyNotIn(List<String> values) {
            addCriterion("is_allow_mcht_modify not in", values, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyBetween(String value1, String value2) {
            addCriterion("is_allow_mcht_modify between", value1, value2, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andIsAllowMchtModifyNotBetween(String value1, String value2) {
            addCriterion("is_allow_mcht_modify not between", value1, value2, "isAllowMchtModify");
            return (Criteria) this;
        }

        public Criteria andAddressTypeIsNull() {
            addCriterion("address_type is null");
            return (Criteria) this;
        }

        public Criteria andAddressTypeIsNotNull() {
            addCriterion("address_type is not null");
            return (Criteria) this;
        }

        public Criteria andAddressTypeEqualTo(String value) {
            addCriterion("address_type =", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotEqualTo(String value) {
            addCriterion("address_type <>", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeGreaterThan(String value) {
            addCriterion("address_type >", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeGreaterThanOrEqualTo(String value) {
            addCriterion("address_type >=", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeLessThan(String value) {
            addCriterion("address_type <", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeLessThanOrEqualTo(String value) {
            addCriterion("address_type <=", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeLike(String value) {
            addCriterion("address_type like", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotLike(String value) {
            addCriterion("address_type not like", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeIn(List<String> values) {
            addCriterion("address_type in", values, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotIn(List<String> values) {
            addCriterion("address_type not in", values, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeBetween(String value1, String value2) {
            addCriterion("address_type between", value1, value2, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotBetween(String value1, String value2) {
            addCriterion("address_type not between", value1, value2, "addressType");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressIsNull() {
            addCriterion("supplier_address is null");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressIsNotNull() {
            addCriterion("supplier_address is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressEqualTo(String value) {
            addCriterion("supplier_address =", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressNotEqualTo(String value) {
            addCriterion("supplier_address <>", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressGreaterThan(String value) {
            addCriterion("supplier_address >", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_address >=", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressLessThan(String value) {
            addCriterion("supplier_address <", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressLessThanOrEqualTo(String value) {
            addCriterion("supplier_address <=", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressLike(String value) {
            addCriterion("supplier_address like", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressNotLike(String value) {
            addCriterion("supplier_address not like", value, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressIn(List<String> values) {
            addCriterion("supplier_address in", values, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressNotIn(List<String> values) {
            addCriterion("supplier_address not in", values, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressBetween(String value1, String value2) {
            addCriterion("supplier_address between", value1, value2, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andSupplierAddressNotBetween(String value1, String value2) {
            addCriterion("supplier_address not between", value1, value2, "supplierAddress");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdIsNull() {
            addCriterion("cloud_product_after_templet_id is null");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdIsNotNull() {
            addCriterion("cloud_product_after_templet_id is not null");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdEqualTo(Integer value) {
            addCriterion("cloud_product_after_templet_id =", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdNotEqualTo(Integer value) {
            addCriterion("cloud_product_after_templet_id <>", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdGreaterThan(Integer value) {
            addCriterion("cloud_product_after_templet_id >", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cloud_product_after_templet_id >=", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdLessThan(Integer value) {
            addCriterion("cloud_product_after_templet_id <", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdLessThanOrEqualTo(Integer value) {
            addCriterion("cloud_product_after_templet_id <=", value, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdIn(List<Integer> values) {
            addCriterion("cloud_product_after_templet_id in", values, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdNotIn(List<Integer> values) {
            addCriterion("cloud_product_after_templet_id not in", values, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdBetween(Integer value1, Integer value2) {
            addCriterion("cloud_product_after_templet_id between", value1, value2, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andCloudProductAfterTempletIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cloud_product_after_templet_id not between", value1, value2, "cloudProductAfterTempletId");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksIsNull() {
            addCriterion("supplier_remarks is null");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksIsNotNull() {
            addCriterion("supplier_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksEqualTo(String value) {
            addCriterion("supplier_remarks =", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksNotEqualTo(String value) {
            addCriterion("supplier_remarks <>", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksGreaterThan(String value) {
            addCriterion("supplier_remarks >", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_remarks >=", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksLessThan(String value) {
            addCriterion("supplier_remarks <", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksLessThanOrEqualTo(String value) {
            addCriterion("supplier_remarks <=", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksLike(String value) {
            addCriterion("supplier_remarks like", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksNotLike(String value) {
            addCriterion("supplier_remarks not like", value, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksIn(List<String> values) {
            addCriterion("supplier_remarks in", values, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksNotIn(List<String> values) {
            addCriterion("supplier_remarks not in", values, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksBetween(String value1, String value2) {
            addCriterion("supplier_remarks between", value1, value2, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRemarksNotBetween(String value1, String value2) {
            addCriterion("supplier_remarks not between", value1, value2, "supplierRemarks");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonIsNull() {
            addCriterion("supplier_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonIsNotNull() {
            addCriterion("supplier_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonEqualTo(String value) {
            addCriterion("supplier_reject_reason =", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonNotEqualTo(String value) {
            addCriterion("supplier_reject_reason <>", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonGreaterThan(String value) {
            addCriterion("supplier_reject_reason >", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_reject_reason >=", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonLessThan(String value) {
            addCriterion("supplier_reject_reason <", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("supplier_reject_reason <=", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonLike(String value) {
            addCriterion("supplier_reject_reason like", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonNotLike(String value) {
            addCriterion("supplier_reject_reason not like", value, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonIn(List<String> values) {
            addCriterion("supplier_reject_reason in", values, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonNotIn(List<String> values) {
            addCriterion("supplier_reject_reason not in", values, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonBetween(String value1, String value2) {
            addCriterion("supplier_reject_reason between", value1, value2, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andSupplierRejectReasonNotBetween(String value1, String value2) {
            addCriterion("supplier_reject_reason not between", value1, value2, "supplierRejectReason");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeIsNull() {
            addCriterion("product_return_consignee is null");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeIsNotNull() {
            addCriterion("product_return_consignee is not null");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeEqualTo(String value) {
            addCriterion("product_return_consignee =", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeNotEqualTo(String value) {
            addCriterion("product_return_consignee <>", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeGreaterThan(String value) {
            addCriterion("product_return_consignee >", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("product_return_consignee >=", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeLessThan(String value) {
            addCriterion("product_return_consignee <", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeLessThanOrEqualTo(String value) {
            addCriterion("product_return_consignee <=", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeLike(String value) {
            addCriterion("product_return_consignee like", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeNotLike(String value) {
            addCriterion("product_return_consignee not like", value, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeIn(List<String> values) {
            addCriterion("product_return_consignee in", values, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeNotIn(List<String> values) {
            addCriterion("product_return_consignee not in", values, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeBetween(String value1, String value2) {
            addCriterion("product_return_consignee between", value1, value2, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnConsigneeNotBetween(String value1, String value2) {
            addCriterion("product_return_consignee not between", value1, value2, "productReturnConsignee");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressIsNull() {
            addCriterion("product_return_address is null");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressIsNotNull() {
            addCriterion("product_return_address is not null");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressEqualTo(String value) {
            addCriterion("product_return_address =", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressNotEqualTo(String value) {
            addCriterion("product_return_address <>", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressGreaterThan(String value) {
            addCriterion("product_return_address >", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressGreaterThanOrEqualTo(String value) {
            addCriterion("product_return_address >=", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressLessThan(String value) {
            addCriterion("product_return_address <", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressLessThanOrEqualTo(String value) {
            addCriterion("product_return_address <=", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressLike(String value) {
            addCriterion("product_return_address like", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressNotLike(String value) {
            addCriterion("product_return_address not like", value, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressIn(List<String> values) {
            addCriterion("product_return_address in", values, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressNotIn(List<String> values) {
            addCriterion("product_return_address not in", values, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressBetween(String value1, String value2) {
            addCriterion("product_return_address between", value1, value2, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnAddressNotBetween(String value1, String value2) {
            addCriterion("product_return_address not between", value1, value2, "productReturnAddress");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneIsNull() {
            addCriterion("product_return_contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneIsNotNull() {
            addCriterion("product_return_contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneEqualTo(String value) {
            addCriterion("product_return_contact_phone =", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneNotEqualTo(String value) {
            addCriterion("product_return_contact_phone <>", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneGreaterThan(String value) {
            addCriterion("product_return_contact_phone >", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("product_return_contact_phone >=", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneLessThan(String value) {
            addCriterion("product_return_contact_phone <", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("product_return_contact_phone <=", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneLike(String value) {
            addCriterion("product_return_contact_phone like", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneNotLike(String value) {
            addCriterion("product_return_contact_phone not like", value, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneIn(List<String> values) {
            addCriterion("product_return_contact_phone in", values, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneNotIn(List<String> values) {
            addCriterion("product_return_contact_phone not in", values, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneBetween(String value1, String value2) {
            addCriterion("product_return_contact_phone between", value1, value2, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnContactPhoneNotBetween(String value1, String value2) {
            addCriterion("product_return_contact_phone not between", value1, value2, "productReturnContactPhone");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksIsNull() {
            addCriterion("product_return_remarks is null");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksIsNotNull() {
            addCriterion("product_return_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksEqualTo(String value) {
            addCriterion("product_return_remarks =", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksNotEqualTo(String value) {
            addCriterion("product_return_remarks <>", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksGreaterThan(String value) {
            addCriterion("product_return_remarks >", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("product_return_remarks >=", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksLessThan(String value) {
            addCriterion("product_return_remarks <", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksLessThanOrEqualTo(String value) {
            addCriterion("product_return_remarks <=", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksLike(String value) {
            addCriterion("product_return_remarks like", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksNotLike(String value) {
            addCriterion("product_return_remarks not like", value, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksIn(List<String> values) {
            addCriterion("product_return_remarks in", values, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksNotIn(List<String> values) {
            addCriterion("product_return_remarks not in", values, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksBetween(String value1, String value2) {
            addCriterion("product_return_remarks between", value1, value2, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andProductReturnRemarksNotBetween(String value1, String value2) {
            addCriterion("product_return_remarks not between", value1, value2, "productReturnRemarks");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}