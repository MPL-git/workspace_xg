package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtMonthlyCollectionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtMonthlyCollectionsExample() {
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

        public Criteria andCollectionDateIsNull() {
            addCriterion("collection_date is null");
            return (Criteria) this;
        }

        public Criteria andCollectionDateIsNotNull() {
            addCriterion("collection_date is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionDateEqualTo(String value) {
            addCriterion("collection_date =", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotEqualTo(String value) {
            addCriterion("collection_date <>", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateGreaterThan(String value) {
            addCriterion("collection_date >", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateGreaterThanOrEqualTo(String value) {
            addCriterion("collection_date >=", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateLessThan(String value) {
            addCriterion("collection_date <", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateLessThanOrEqualTo(String value) {
            addCriterion("collection_date <=", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateLike(String value) {
            addCriterion("collection_date like", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotLike(String value) {
            addCriterion("collection_date not like", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateIn(List<String> values) {
            addCriterion("collection_date in", values, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotIn(List<String> values) {
            addCriterion("collection_date not in", values, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateBetween(String value1, String value2) {
            addCriterion("collection_date between", value1, value2, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotBetween(String value1, String value2) {
            addCriterion("collection_date not between", value1, value2, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNull() {
            addCriterion("mcht_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtIdIsNotNull() {
            addCriterion("mcht_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtIdEqualTo(Integer value) {
            addCriterion("mcht_id =", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotEqualTo(Integer value) {
            addCriterion("mcht_id <>", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThan(Integer value) {
            addCriterion("mcht_id >", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_id >=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThan(Integer value) {
            addCriterion("mcht_id <", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_id <=", value, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdIn(List<Integer> values) {
            addCriterion("mcht_id in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotIn(List<Integer> values) {
            addCriterion("mcht_id not in", values, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andMchtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_id not between", value1, value2, "mchtId");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayIsNull() {
            addCriterion("begin_unpay is null");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayIsNotNull() {
            addCriterion("begin_unpay is not null");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayEqualTo(BigDecimal value) {
            addCriterion("begin_unpay =", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotEqualTo(BigDecimal value) {
            addCriterion("begin_unpay <>", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayGreaterThan(BigDecimal value) {
            addCriterion("begin_unpay >", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_unpay >=", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayLessThan(BigDecimal value) {
            addCriterion("begin_unpay <", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_unpay <=", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayIn(List<BigDecimal> values) {
            addCriterion("begin_unpay in", values, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotIn(List<BigDecimal> values) {
            addCriterion("begin_unpay not in", values, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_unpay between", value1, value2, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_unpay not between", value1, value2, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(BigDecimal value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(BigDecimal value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(BigDecimal value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(BigDecimal value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<BigDecimal> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<BigDecimal> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNull() {
            addCriterion("settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNotNull() {
            addCriterion("settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountEqualTo(BigDecimal value) {
            addCriterion("settle_amount =", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("settle_amount <>", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("settle_amount >", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount >=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThan(BigDecimal value) {
            addCriterion("settle_amount <", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount <=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIn(List<BigDecimal> values) {
            addCriterion("settle_amount in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("settle_amount not in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount not between", value1, value2, "settleAmount");
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

        public Criteria andReturnAmountIsNull() {
            addCriterion("return_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNotNull() {
            addCriterion("return_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountEqualTo(BigDecimal value) {
            addCriterion("return_amount =", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotEqualTo(BigDecimal value) {
            addCriterion("return_amount <>", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThan(BigDecimal value) {
            addCriterion("return_amount >", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_amount >=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThan(BigDecimal value) {
            addCriterion("return_amount <", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_amount <=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIn(List<BigDecimal> values) {
            addCriterion("return_amount in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotIn(List<BigDecimal> values) {
            addCriterion("return_amount not in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_amount between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_amount not between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountIsNull() {
            addCriterion("return_order_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountIsNotNull() {
            addCriterion("return_order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountEqualTo(BigDecimal value) {
            addCriterion("return_order_amount =", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountNotEqualTo(BigDecimal value) {
            addCriterion("return_order_amount <>", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountGreaterThan(BigDecimal value) {
            addCriterion("return_order_amount >", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_order_amount >=", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountLessThan(BigDecimal value) {
            addCriterion("return_order_amount <", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_order_amount <=", value, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountIn(List<BigDecimal> values) {
            addCriterion("return_order_amount in", values, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountNotIn(List<BigDecimal> values) {
            addCriterion("return_order_amount not in", values, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_order_amount between", value1, value2, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andReturnOrderAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_order_amount not between", value1, value2, "returnOrderAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIsNull() {
            addCriterion("need_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIsNotNull() {
            addCriterion("need_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount =", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount <>", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountGreaterThan(BigDecimal value) {
            addCriterion("need_pay_amount >", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount >=", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountLessThan(BigDecimal value) {
            addCriterion("need_pay_amount <", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount <=", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIn(List<BigDecimal> values) {
            addCriterion("need_pay_amount in", values, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("need_pay_amount not in", values, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay_amount between", value1, value2, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay_amount not between", value1, value2, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIsNull() {
            addCriterion("end_unpay is null");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIsNotNull() {
            addCriterion("end_unpay is not null");
            return (Criteria) this;
        }

        public Criteria andEndUnpayEqualTo(BigDecimal value) {
            addCriterion("end_unpay =", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotEqualTo(BigDecimal value) {
            addCriterion("end_unpay <>", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayGreaterThan(BigDecimal value) {
            addCriterion("end_unpay >", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_unpay >=", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayLessThan(BigDecimal value) {
            addCriterion("end_unpay <", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_unpay <=", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIn(List<BigDecimal> values) {
            addCriterion("end_unpay in", values, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotIn(List<BigDecimal> values) {
            addCriterion("end_unpay not in", values, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_unpay between", value1, value2, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_unpay not between", value1, value2, "endUnpay");
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

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
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

        public Criteria andDeductionDepositTotalIsNull() {
            addCriterion("deduction_deposit_total is null");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalIsNotNull() {
            addCriterion("deduction_deposit_total is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalEqualTo(BigDecimal value) {
            addCriterion("deduction_deposit_total =", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalNotEqualTo(BigDecimal value) {
            addCriterion("deduction_deposit_total <>", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalGreaterThan(BigDecimal value) {
            addCriterion("deduction_deposit_total >", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_deposit_total >=", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalLessThan(BigDecimal value) {
            addCriterion("deduction_deposit_total <", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_deposit_total <=", value, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalIn(List<BigDecimal> values) {
            addCriterion("deduction_deposit_total in", values, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalNotIn(List<BigDecimal> values) {
            addCriterion("deduction_deposit_total not in", values, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_deposit_total between", value1, value2, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andDeductionDepositTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_deposit_total not between", value1, value2, "deductionDepositTotal");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountIsNull() {
            addCriterion("collection_product_amount is null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountIsNotNull() {
            addCriterion("collection_product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountEqualTo(BigDecimal value) {
            addCriterion("collection_product_amount =", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountNotEqualTo(BigDecimal value) {
            addCriterion("collection_product_amount <>", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountGreaterThan(BigDecimal value) {
            addCriterion("collection_product_amount >", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_product_amount >=", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountLessThan(BigDecimal value) {
            addCriterion("collection_product_amount <", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_product_amount <=", value, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountIn(List<BigDecimal> values) {
            addCriterion("collection_product_amount in", values, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountNotIn(List<BigDecimal> values) {
            addCriterion("collection_product_amount not in", values, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_product_amount between", value1, value2, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_product_amount not between", value1, value2, "collectionProductAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountIsNull() {
            addCriterion("collection_product_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountIsNotNull() {
            addCriterion("collection_product_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountEqualTo(Integer value) {
            addCriterion("collection_product_count =", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountNotEqualTo(Integer value) {
            addCriterion("collection_product_count <>", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountGreaterThan(Integer value) {
            addCriterion("collection_product_count >", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_product_count >=", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountLessThan(Integer value) {
            addCriterion("collection_product_count <", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("collection_product_count <=", value, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountIn(List<Integer> values) {
            addCriterion("collection_product_count in", values, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountNotIn(List<Integer> values) {
            addCriterion("collection_product_count not in", values, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountBetween(Integer value1, Integer value2) {
            addCriterion("collection_product_count between", value1, value2, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_product_count not between", value1, value2, "collectionProductCount");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialIsNull() {
            addCriterion("collection_mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialIsNotNull() {
            addCriterion("collection_mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("collection_mcht_preferential =", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("collection_mcht_preferential <>", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("collection_mcht_preferential >", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_mcht_preferential >=", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("collection_mcht_preferential <", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_mcht_preferential <=", value, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("collection_mcht_preferential in", values, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("collection_mcht_preferential not in", values, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_mcht_preferential between", value1, value2, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_mcht_preferential not between", value1, value2, "collectionMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialIsNull() {
            addCriterion("collection_platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialIsNotNull() {
            addCriterion("collection_platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("collection_platform_preferential =", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("collection_platform_preferential <>", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("collection_platform_preferential >", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_platform_preferential >=", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("collection_platform_preferential <", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_platform_preferential <=", value, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("collection_platform_preferential in", values, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("collection_platform_preferential not in", values, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_platform_preferential between", value1, value2, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_platform_preferential not between", value1, value2, "collectionPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialIsNull() {
            addCriterion("collection_integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialIsNotNull() {
            addCriterion("collection_integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("collection_integral_preferential =", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("collection_integral_preferential <>", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("collection_integral_preferential >", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_integral_preferential >=", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("collection_integral_preferential <", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_integral_preferential <=", value, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("collection_integral_preferential in", values, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("collection_integral_preferential not in", values, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_integral_preferential between", value1, value2, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_integral_preferential not between", value1, value2, "collectionIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountIsNull() {
            addCriterion("collection_commission_amount is null");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountIsNotNull() {
            addCriterion("collection_commission_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountEqualTo(BigDecimal value) {
            addCriterion("collection_commission_amount =", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountNotEqualTo(BigDecimal value) {
            addCriterion("collection_commission_amount <>", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountGreaterThan(BigDecimal value) {
            addCriterion("collection_commission_amount >", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_commission_amount >=", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountLessThan(BigDecimal value) {
            addCriterion("collection_commission_amount <", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collection_commission_amount <=", value, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountIn(List<BigDecimal> values) {
            addCriterion("collection_commission_amount in", values, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountNotIn(List<BigDecimal> values) {
            addCriterion("collection_commission_amount not in", values, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_commission_amount between", value1, value2, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andCollectionCommissionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collection_commission_amount not between", value1, value2, "collectionCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountIsNull() {
            addCriterion("refund_product_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountIsNotNull() {
            addCriterion("refund_product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountEqualTo(BigDecimal value) {
            addCriterion("refund_product_amount =", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_product_amount <>", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_product_amount >", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_product_amount >=", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountLessThan(BigDecimal value) {
            addCriterion("refund_product_amount <", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_product_amount <=", value, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountIn(List<BigDecimal> values) {
            addCriterion("refund_product_amount in", values, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_product_amount not in", values, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_product_amount between", value1, value2, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_product_amount not between", value1, value2, "refundProductAmount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountIsNull() {
            addCriterion("refund_product_count is null");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountIsNotNull() {
            addCriterion("refund_product_count is not null");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountEqualTo(Integer value) {
            addCriterion("refund_product_count =", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountNotEqualTo(Integer value) {
            addCriterion("refund_product_count <>", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountGreaterThan(Integer value) {
            addCriterion("refund_product_count >", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_product_count >=", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountLessThan(Integer value) {
            addCriterion("refund_product_count <", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("refund_product_count <=", value, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountIn(List<Integer> values) {
            addCriterion("refund_product_count in", values, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountNotIn(List<Integer> values) {
            addCriterion("refund_product_count not in", values, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountBetween(Integer value1, Integer value2) {
            addCriterion("refund_product_count between", value1, value2, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_product_count not between", value1, value2, "refundProductCount");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialIsNull() {
            addCriterion("refund_mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialIsNotNull() {
            addCriterion("refund_mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("refund_mcht_preferential =", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("refund_mcht_preferential <>", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("refund_mcht_preferential >", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_mcht_preferential >=", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("refund_mcht_preferential <", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_mcht_preferential <=", value, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("refund_mcht_preferential in", values, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("refund_mcht_preferential not in", values, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_mcht_preferential between", value1, value2, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_mcht_preferential not between", value1, value2, "refundMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialIsNull() {
            addCriterion("refund_platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialIsNotNull() {
            addCriterion("refund_platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("refund_platform_preferential =", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("refund_platform_preferential <>", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("refund_platform_preferential >", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_platform_preferential >=", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("refund_platform_preferential <", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_platform_preferential <=", value, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("refund_platform_preferential in", values, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("refund_platform_preferential not in", values, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_platform_preferential between", value1, value2, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_platform_preferential not between", value1, value2, "refundPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialIsNull() {
            addCriterion("refund_integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialIsNotNull() {
            addCriterion("refund_integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("refund_integral_preferential =", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("refund_integral_preferential <>", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("refund_integral_preferential >", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_integral_preferential >=", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("refund_integral_preferential <", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_integral_preferential <=", value, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("refund_integral_preferential in", values, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("refund_integral_preferential not in", values, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_integral_preferential between", value1, value2, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_integral_preferential not between", value1, value2, "refundIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountIsNull() {
            addCriterion("refund_commission_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountIsNotNull() {
            addCriterion("refund_commission_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountEqualTo(BigDecimal value) {
            addCriterion("refund_commission_amount =", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountNotEqualTo(BigDecimal value) {
            addCriterion("refund_commission_amount <>", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountGreaterThan(BigDecimal value) {
            addCriterion("refund_commission_amount >", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_commission_amount >=", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountLessThan(BigDecimal value) {
            addCriterion("refund_commission_amount <", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_commission_amount <=", value, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountIn(List<BigDecimal> values) {
            addCriterion("refund_commission_amount in", values, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountNotIn(List<BigDecimal> values) {
            addCriterion("refund_commission_amount not in", values, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_commission_amount between", value1, value2, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCommissionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_commission_amount not between", value1, value2, "refundCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountDescIsNull() {
            addCriterion("discount_desc is null");
            return (Criteria) this;
        }

        public Criteria andDiscountDescIsNotNull() {
            addCriterion("discount_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountDescEqualTo(String value) {
            addCriterion("discount_desc =", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescNotEqualTo(String value) {
            addCriterion("discount_desc <>", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescGreaterThan(String value) {
            addCriterion("discount_desc >", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescGreaterThanOrEqualTo(String value) {
            addCriterion("discount_desc >=", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescLessThan(String value) {
            addCriterion("discount_desc <", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescLessThanOrEqualTo(String value) {
            addCriterion("discount_desc <=", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescLike(String value) {
            addCriterion("discount_desc like", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescNotLike(String value) {
            addCriterion("discount_desc not like", value, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescIn(List<String> values) {
            addCriterion("discount_desc in", values, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescNotIn(List<String> values) {
            addCriterion("discount_desc not in", values, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescBetween(String value1, String value2) {
            addCriterion("discount_desc between", value1, value2, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountDescNotBetween(String value1, String value2) {
            addCriterion("discount_desc not between", value1, value2, "discountDesc");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayIsNull() {
            addCriterion("discounted_end_need_pay is null");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayIsNotNull() {
            addCriterion("discounted_end_need_pay is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayEqualTo(BigDecimal value) {
            addCriterion("discounted_end_need_pay =", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayNotEqualTo(BigDecimal value) {
            addCriterion("discounted_end_need_pay <>", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayGreaterThan(BigDecimal value) {
            addCriterion("discounted_end_need_pay >", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discounted_end_need_pay >=", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayLessThan(BigDecimal value) {
            addCriterion("discounted_end_need_pay <", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discounted_end_need_pay <=", value, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayIn(List<BigDecimal> values) {
            addCriterion("discounted_end_need_pay in", values, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayNotIn(List<BigDecimal> values) {
            addCriterion("discounted_end_need_pay not in", values, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discounted_end_need_pay between", value1, value2, "discountedEndNeedPay");
            return (Criteria) this;
        }

        public Criteria andDiscountedEndNeedPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discounted_end_need_pay not between", value1, value2, "discountedEndNeedPay");
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