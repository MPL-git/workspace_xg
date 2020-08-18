package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public RefundOrderExample() {
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

        public Criteria andCombineOrderIdIsNull() {
            addCriterion("combine_order_id is null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIsNotNull() {
            addCriterion("combine_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdEqualTo(Integer value) {
            addCriterion("combine_order_id =", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotEqualTo(Integer value) {
            addCriterion("combine_order_id <>", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThan(Integer value) {
            addCriterion("combine_order_id >", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id >=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThan(Integer value) {
            addCriterion("combine_order_id <", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id <=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIn(List<Integer> values) {
            addCriterion("combine_order_id in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotIn(List<Integer> values) {
            addCriterion("combine_order_id not in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id between", value1, value2, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id not between", value1, value2, "combineOrderId");
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

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("order_type like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("order_type not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdIsNull() {
            addCriterion("service_order_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdIsNotNull() {
            addCriterion("service_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdEqualTo(Integer value) {
            addCriterion("service_order_id =", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotEqualTo(Integer value) {
            addCriterion("service_order_id <>", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdGreaterThan(Integer value) {
            addCriterion("service_order_id >", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_order_id >=", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdLessThan(Integer value) {
            addCriterion("service_order_id <", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_order_id <=", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdIn(List<Integer> values) {
            addCriterion("service_order_id in", values, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotIn(List<Integer> values) {
            addCriterion("service_order_id not in", values, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("service_order_id between", value1, value2, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_order_id not between", value1, value2, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdIsNull() {
            addCriterion("combine_deposit_order_id is null");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdIsNotNull() {
            addCriterion("combine_deposit_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id =", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id <>", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdGreaterThan(Integer value) {
            addCriterion("combine_deposit_order_id >", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id >=", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdLessThan(Integer value) {
            addCriterion("combine_deposit_order_id <", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id <=", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdIn(List<Integer> values) {
            addCriterion("combine_deposit_order_id in", values, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotIn(List<Integer> values) {
            addCriterion("combine_deposit_order_id not in", values, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("combine_deposit_order_id between", value1, value2, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("combine_deposit_order_id not between", value1, value2, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNull() {
            addCriterion("refund_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("refund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("refund_amount =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_amount >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("refund_amount <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("refund_amount in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateIsNull() {
            addCriterion("refund_agree_date is null");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateIsNotNull() {
            addCriterion("refund_agree_date is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateEqualTo(Date value) {
            addCriterion("refund_agree_date =", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateNotEqualTo(Date value) {
            addCriterion("refund_agree_date <>", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateGreaterThan(Date value) {
            addCriterion("refund_agree_date >", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_agree_date >=", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateLessThan(Date value) {
            addCriterion("refund_agree_date <", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateLessThanOrEqualTo(Date value) {
            addCriterion("refund_agree_date <=", value, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateIn(List<Date> values) {
            addCriterion("refund_agree_date in", values, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateNotIn(List<Date> values) {
            addCriterion("refund_agree_date not in", values, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateBetween(Date value1, Date value2) {
            addCriterion("refund_agree_date between", value1, value2, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundAgreeDateNotBetween(Date value1, Date value2) {
            addCriterion("refund_agree_date not between", value1, value2, "refundAgreeDate");
            return (Criteria) this;
        }

        public Criteria andRefundMethodIsNull() {
            addCriterion("refund_method is null");
            return (Criteria) this;
        }

        public Criteria andRefundMethodIsNotNull() {
            addCriterion("refund_method is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMethodEqualTo(String value) {
            addCriterion("refund_method =", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodNotEqualTo(String value) {
            addCriterion("refund_method <>", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodGreaterThan(String value) {
            addCriterion("refund_method >", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodGreaterThanOrEqualTo(String value) {
            addCriterion("refund_method >=", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodLessThan(String value) {
            addCriterion("refund_method <", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodLessThanOrEqualTo(String value) {
            addCriterion("refund_method <=", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodLike(String value) {
            addCriterion("refund_method like", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodNotLike(String value) {
            addCriterion("refund_method not like", value, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodIn(List<String> values) {
            addCriterion("refund_method in", values, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodNotIn(List<String> values) {
            addCriterion("refund_method not in", values, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodBetween(String value1, String value2) {
            addCriterion("refund_method between", value1, value2, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundMethodNotBetween(String value1, String value2) {
            addCriterion("refund_method not between", value1, value2, "refundMethod");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoIsNull() {
            addCriterion("refund_req_no is null");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoIsNotNull() {
            addCriterion("refund_req_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoEqualTo(String value) {
            addCriterion("refund_req_no =", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoNotEqualTo(String value) {
            addCriterion("refund_req_no <>", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoGreaterThan(String value) {
            addCriterion("refund_req_no >", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoGreaterThanOrEqualTo(String value) {
            addCriterion("refund_req_no >=", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoLessThan(String value) {
            addCriterion("refund_req_no <", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoLessThanOrEqualTo(String value) {
            addCriterion("refund_req_no <=", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoLike(String value) {
            addCriterion("refund_req_no like", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoNotLike(String value) {
            addCriterion("refund_req_no not like", value, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoIn(List<String> values) {
            addCriterion("refund_req_no in", values, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoNotIn(List<String> values) {
            addCriterion("refund_req_no not in", values, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoBetween(String value1, String value2) {
            addCriterion("refund_req_no between", value1, value2, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andRefundReqNoNotBetween(String value1, String value2) {
            addCriterion("refund_req_no not between", value1, value2, "refundReqNo");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNull() {
            addCriterion("failed_reason is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNotNull() {
            addCriterion("failed_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonEqualTo(String value) {
            addCriterion("failed_reason =", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotEqualTo(String value) {
            addCriterion("failed_reason <>", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThan(String value) {
            addCriterion("failed_reason >", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("failed_reason >=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThan(String value) {
            addCriterion("failed_reason <", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThanOrEqualTo(String value) {
            addCriterion("failed_reason <=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLike(String value) {
            addCriterion("failed_reason like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotLike(String value) {
            addCriterion("failed_reason not like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIn(List<String> values) {
            addCriterion("failed_reason in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotIn(List<String> values) {
            addCriterion("failed_reason not in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonBetween(String value1, String value2) {
            addCriterion("failed_reason between", value1, value2, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotBetween(String value1, String value2) {
            addCriterion("failed_reason not between", value1, value2, "failedReason");
            return (Criteria) this;
        }

        public Criteria andTryTimesIsNull() {
            addCriterion("try_times is null");
            return (Criteria) this;
        }

        public Criteria andTryTimesIsNotNull() {
            addCriterion("try_times is not null");
            return (Criteria) this;
        }

        public Criteria andTryTimesEqualTo(Integer value) {
            addCriterion("try_times =", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotEqualTo(Integer value) {
            addCriterion("try_times <>", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThan(Integer value) {
            addCriterion("try_times >", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("try_times >=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThan(Integer value) {
            addCriterion("try_times <", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesLessThanOrEqualTo(Integer value) {
            addCriterion("try_times <=", value, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesIn(List<Integer> values) {
            addCriterion("try_times in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotIn(List<Integer> values) {
            addCriterion("try_times not in", values, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesBetween(Integer value1, Integer value2) {
            addCriterion("try_times between", value1, value2, "tryTimes");
            return (Criteria) this;
        }

        public Criteria andTryTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("try_times not between", value1, value2, "tryTimes");
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

        public Criteria andSuccessDateIsNull() {
            addCriterion("success_date is null");
            return (Criteria) this;
        }

        public Criteria andSuccessDateIsNotNull() {
            addCriterion("success_date is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessDateEqualTo(Date value) {
            addCriterion("success_date =", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateNotEqualTo(Date value) {
            addCriterion("success_date <>", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateGreaterThan(Date value) {
            addCriterion("success_date >", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateGreaterThanOrEqualTo(Date value) {
            addCriterion("success_date >=", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateLessThan(Date value) {
            addCriterion("success_date <", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateLessThanOrEqualTo(Date value) {
            addCriterion("success_date <=", value, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateIn(List<Date> values) {
            addCriterion("success_date in", values, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateNotIn(List<Date> values) {
            addCriterion("success_date not in", values, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateBetween(Date value1, Date value2) {
            addCriterion("success_date between", value1, value2, "successDate");
            return (Criteria) this;
        }

        public Criteria andSuccessDateNotBetween(Date value1, Date value2) {
            addCriterion("success_date not between", value1, value2, "successDate");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIsNull() {
            addCriterion("refund_code is null");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIsNotNull() {
            addCriterion("refund_code is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCodeEqualTo(String value) {
            addCriterion("refund_code =", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotEqualTo(String value) {
            addCriterion("refund_code <>", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeGreaterThan(String value) {
            addCriterion("refund_code >", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeGreaterThanOrEqualTo(String value) {
            addCriterion("refund_code >=", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLessThan(String value) {
            addCriterion("refund_code <", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLessThanOrEqualTo(String value) {
            addCriterion("refund_code <=", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeLike(String value) {
            addCriterion("refund_code like", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotLike(String value) {
            addCriterion("refund_code not like", value, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeIn(List<String> values) {
            addCriterion("refund_code in", values, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotIn(List<String> values) {
            addCriterion("refund_code not in", values, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeBetween(String value1, String value2) {
            addCriterion("refund_code between", value1, value2, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundCodeNotBetween(String value1, String value2) {
            addCriterion("refund_code not between", value1, value2, "refundCode");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateIsNull() {
            addCriterion("refund_register_date is null");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateIsNotNull() {
            addCriterion("refund_register_date is not null");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateEqualTo(Date value) {
            addCriterion("refund_register_date =", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateNotEqualTo(Date value) {
            addCriterion("refund_register_date <>", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateGreaterThan(Date value) {
            addCriterion("refund_register_date >", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_register_date >=", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateLessThan(Date value) {
            addCriterion("refund_register_date <", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateLessThanOrEqualTo(Date value) {
            addCriterion("refund_register_date <=", value, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateIn(List<Date> values) {
            addCriterion("refund_register_date in", values, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateNotIn(List<Date> values) {
            addCriterion("refund_register_date not in", values, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateBetween(Date value1, Date value2) {
            addCriterion("refund_register_date between", value1, value2, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andRefundRegisterDateNotBetween(Date value1, Date value2) {
            addCriterion("refund_register_date not between", value1, value2, "refundRegisterDate");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdIsNull() {
            addCriterion("cashier_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdIsNotNull() {
            addCriterion("cashier_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdEqualTo(Integer value) {
            addCriterion("cashier_staff_id =", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdNotEqualTo(Integer value) {
            addCriterion("cashier_staff_id <>", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdGreaterThan(Integer value) {
            addCriterion("cashier_staff_id >", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cashier_staff_id >=", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdLessThan(Integer value) {
            addCriterion("cashier_staff_id <", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("cashier_staff_id <=", value, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdIn(List<Integer> values) {
            addCriterion("cashier_staff_id in", values, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdNotIn(List<Integer> values) {
            addCriterion("cashier_staff_id not in", values, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("cashier_staff_id between", value1, value2, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andCashierStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cashier_staff_id not between", value1, value2, "cashierStaffId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdIsNull() {
            addCriterion("wx_refund_id is null");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdIsNotNull() {
            addCriterion("wx_refund_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdEqualTo(String value) {
            addCriterion("wx_refund_id =", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdNotEqualTo(String value) {
            addCriterion("wx_refund_id <>", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdGreaterThan(String value) {
            addCriterion("wx_refund_id >", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_refund_id >=", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdLessThan(String value) {
            addCriterion("wx_refund_id <", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdLessThanOrEqualTo(String value) {
            addCriterion("wx_refund_id <=", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdLike(String value) {
            addCriterion("wx_refund_id like", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdNotLike(String value) {
            addCriterion("wx_refund_id not like", value, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdIn(List<String> values) {
            addCriterion("wx_refund_id in", values, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdNotIn(List<String> values) {
            addCriterion("wx_refund_id not in", values, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdBetween(String value1, String value2) {
            addCriterion("wx_refund_id between", value1, value2, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andWxRefundIdNotBetween(String value1, String value2) {
            addCriterion("wx_refund_id not between", value1, value2, "wxRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdIsNull() {
            addCriterion("zfb_refund_id is null");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdIsNotNull() {
            addCriterion("zfb_refund_id is not null");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdEqualTo(String value) {
            addCriterion("zfb_refund_id =", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdNotEqualTo(String value) {
            addCriterion("zfb_refund_id <>", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdGreaterThan(String value) {
            addCriterion("zfb_refund_id >", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdGreaterThanOrEqualTo(String value) {
            addCriterion("zfb_refund_id >=", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdLessThan(String value) {
            addCriterion("zfb_refund_id <", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdLessThanOrEqualTo(String value) {
            addCriterion("zfb_refund_id <=", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdLike(String value) {
            addCriterion("zfb_refund_id like", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdNotLike(String value) {
            addCriterion("zfb_refund_id not like", value, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdIn(List<String> values) {
            addCriterion("zfb_refund_id in", values, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdNotIn(List<String> values) {
            addCriterion("zfb_refund_id not in", values, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdBetween(String value1, String value2) {
            addCriterion("zfb_refund_id between", value1, value2, "zfbRefundId");
            return (Criteria) this;
        }

        public Criteria andZfbRefundIdNotBetween(String value1, String value2) {
            addCriterion("zfb_refund_id not between", value1, value2, "zfbRefundId");
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