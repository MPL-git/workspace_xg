package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtSettleSituationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtSettleSituationExample() {
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

        public Criteria andSettleDateIsNull() {
            addCriterion("settle_date is null");
            return (Criteria) this;
        }

        public Criteria andSettleDateIsNotNull() {
            addCriterion("settle_date is not null");
            return (Criteria) this;
        }

        public Criteria andSettleDateEqualTo(String value) {
            addCriterion("settle_date =", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotEqualTo(String value) {
            addCriterion("settle_date <>", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateGreaterThan(String value) {
            addCriterion("settle_date >", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateGreaterThanOrEqualTo(String value) {
            addCriterion("settle_date >=", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateLessThan(String value) {
            addCriterion("settle_date <", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateLessThanOrEqualTo(String value) {
            addCriterion("settle_date <=", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateLike(String value) {
            addCriterion("settle_date like", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotLike(String value) {
            addCriterion("settle_date not like", value, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateIn(List<String> values) {
            addCriterion("settle_date in", values, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotIn(List<String> values) {
            addCriterion("settle_date not in", values, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateBetween(String value1, String value2) {
            addCriterion("settle_date between", value1, value2, "settleDate");
            return (Criteria) this;
        }

        public Criteria andSettleDateNotBetween(String value1, String value2) {
            addCriterion("settle_date not between", value1, value2, "settleDate");
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

        public Criteria andProductNumIsNull() {
            addCriterion("product_num is null");
            return (Criteria) this;
        }

        public Criteria andProductNumIsNotNull() {
            addCriterion("product_num is not null");
            return (Criteria) this;
        }

        public Criteria andProductNumEqualTo(Integer value) {
            addCriterion("product_num =", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotEqualTo(Integer value) {
            addCriterion("product_num <>", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThan(Integer value) {
            addCriterion("product_num >", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_num >=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThan(Integer value) {
            addCriterion("product_num <", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThanOrEqualTo(Integer value) {
            addCriterion("product_num <=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumIn(List<Integer> values) {
            addCriterion("product_num in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotIn(List<Integer> values) {
            addCriterion("product_num not in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumBetween(Integer value1, Integer value2) {
            addCriterion("product_num between", value1, value2, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotBetween(Integer value1, Integer value2) {
            addCriterion("product_num not between", value1, value2, "productNum");
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

        public Criteria andCommissionAmountIsNull() {
            addCriterion("commission_amount is null");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountIsNotNull() {
            addCriterion("commission_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountEqualTo(BigDecimal value) {
            addCriterion("commission_amount =", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotEqualTo(BigDecimal value) {
            addCriterion("commission_amount <>", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountGreaterThan(BigDecimal value) {
            addCriterion("commission_amount >", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commission_amount >=", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountLessThan(BigDecimal value) {
            addCriterion("commission_amount <", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commission_amount <=", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountIn(List<BigDecimal> values) {
            addCriterion("commission_amount in", values, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotIn(List<BigDecimal> values) {
            addCriterion("commission_amount not in", values, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission_amount between", value1, value2, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission_amount not between", value1, value2, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalIsNull() {
            addCriterion("settle_price_total is null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalIsNotNull() {
            addCriterion("settle_price_total is not null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalEqualTo(BigDecimal value) {
            addCriterion("settle_price_total =", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalNotEqualTo(BigDecimal value) {
            addCriterion("settle_price_total <>", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalGreaterThan(BigDecimal value) {
            addCriterion("settle_price_total >", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price_total >=", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalLessThan(BigDecimal value) {
            addCriterion("settle_price_total <", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price_total <=", value, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalIn(List<BigDecimal> values) {
            addCriterion("settle_price_total in", values, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalNotIn(List<BigDecimal> values) {
            addCriterion("settle_price_total not in", values, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price_total between", value1, value2, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andSettlePriceTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price_total not between", value1, value2, "settlePriceTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalIsNull() {
            addCriterion("mcht_preferential_total is null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalIsNotNull() {
            addCriterion("mcht_preferential_total is not null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential_total =", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalNotEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential_total <>", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalGreaterThan(BigDecimal value) {
            addCriterion("mcht_preferential_total >", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential_total >=", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalLessThan(BigDecimal value) {
            addCriterion("mcht_preferential_total <", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential_total <=", value, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential_total in", values, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalNotIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential_total not in", values, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential_total between", value1, value2, "mchtPreferentialTotal");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential_total not between", value1, value2, "mchtPreferentialTotal");
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

        public Criteria andUnpayAmountIsNull() {
            addCriterion("unpay_amount is null");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountIsNotNull() {
            addCriterion("unpay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountEqualTo(BigDecimal value) {
            addCriterion("unpay_amount =", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountNotEqualTo(BigDecimal value) {
            addCriterion("unpay_amount <>", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountGreaterThan(BigDecimal value) {
            addCriterion("unpay_amount >", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unpay_amount >=", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountLessThan(BigDecimal value) {
            addCriterion("unpay_amount <", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unpay_amount <=", value, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountIn(List<BigDecimal> values) {
            addCriterion("unpay_amount in", values, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountNotIn(List<BigDecimal> values) {
            addCriterion("unpay_amount not in", values, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unpay_amount between", value1, value2, "unpayAmount");
            return (Criteria) this;
        }

        public Criteria andUnpayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unpay_amount not between", value1, value2, "unpayAmount");
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

        public Criteria andDepositTotalIsNull() {
            addCriterion("deposit_total is null");
            return (Criteria) this;
        }

        public Criteria andDepositTotalIsNotNull() {
            addCriterion("deposit_total is not null");
            return (Criteria) this;
        }

        public Criteria andDepositTotalEqualTo(BigDecimal value) {
            addCriterion("deposit_total =", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalNotEqualTo(BigDecimal value) {
            addCriterion("deposit_total <>", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalGreaterThan(BigDecimal value) {
            addCriterion("deposit_total >", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_total >=", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalLessThan(BigDecimal value) {
            addCriterion("deposit_total <", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_total <=", value, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalIn(List<BigDecimal> values) {
            addCriterion("deposit_total in", values, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalNotIn(List<BigDecimal> values) {
            addCriterion("deposit_total not in", values, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_total between", value1, value2, "depositTotal");
            return (Criteria) this;
        }

        public Criteria andDepositTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_total not between", value1, value2, "depositTotal");
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

        public Criteria andProductAmountIsNull() {
            addCriterion("product_amount is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNotNull() {
            addCriterion("product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountEqualTo(BigDecimal value) {
            addCriterion("product_amount =", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotEqualTo(BigDecimal value) {
            addCriterion("product_amount <>", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThan(BigDecimal value) {
            addCriterion("product_amount >", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_amount >=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThan(BigDecimal value) {
            addCriterion("product_amount <", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_amount <=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountIn(List<BigDecimal> values) {
            addCriterion("product_amount in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotIn(List<BigDecimal> values) {
            addCriterion("product_amount not in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_amount between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_amount not between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIsNull() {
            addCriterion("platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIsNotNull() {
            addCriterion("platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("platform_preferential =", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <>", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("platform_preferential >", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential >=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("platform_preferential <", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("platform_preferential in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("platform_preferential not in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential not between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNull() {
            addCriterion("integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNotNull() {
            addCriterion("integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("integral_preferential =", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <>", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("integral_preferential >", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential >=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("integral_preferential <", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("integral_preferential in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("integral_preferential not in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential between", value1, value2, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential not between", value1, value2, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumIsNull() {
            addCriterion("return_product_num is null");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumIsNotNull() {
            addCriterion("return_product_num is not null");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumEqualTo(Integer value) {
            addCriterion("return_product_num =", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumNotEqualTo(Integer value) {
            addCriterion("return_product_num <>", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumGreaterThan(Integer value) {
            addCriterion("return_product_num >", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_product_num >=", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumLessThan(Integer value) {
            addCriterion("return_product_num <", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumLessThanOrEqualTo(Integer value) {
            addCriterion("return_product_num <=", value, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumIn(List<Integer> values) {
            addCriterion("return_product_num in", values, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumNotIn(List<Integer> values) {
            addCriterion("return_product_num not in", values, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumBetween(Integer value1, Integer value2) {
            addCriterion("return_product_num between", value1, value2, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductNumNotBetween(Integer value1, Integer value2) {
            addCriterion("return_product_num not between", value1, value2, "returnProductNum");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountIsNull() {
            addCriterion("return_product_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountIsNotNull() {
            addCriterion("return_product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountEqualTo(BigDecimal value) {
            addCriterion("return_product_amount =", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountNotEqualTo(BigDecimal value) {
            addCriterion("return_product_amount <>", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountGreaterThan(BigDecimal value) {
            addCriterion("return_product_amount >", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_product_amount >=", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountLessThan(BigDecimal value) {
            addCriterion("return_product_amount <", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_product_amount <=", value, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountIn(List<BigDecimal> values) {
            addCriterion("return_product_amount in", values, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountNotIn(List<BigDecimal> values) {
            addCriterion("return_product_amount not in", values, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_product_amount between", value1, value2, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnProductAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_product_amount not between", value1, value2, "returnProductAmount");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialIsNull() {
            addCriterion("return_mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialIsNotNull() {
            addCriterion("return_mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("return_mcht_preferential =", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("return_mcht_preferential <>", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("return_mcht_preferential >", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_mcht_preferential >=", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("return_mcht_preferential <", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_mcht_preferential <=", value, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("return_mcht_preferential in", values, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("return_mcht_preferential not in", values, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_mcht_preferential between", value1, value2, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_mcht_preferential not between", value1, value2, "returnMchtPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialIsNull() {
            addCriterion("return_platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialIsNotNull() {
            addCriterion("return_platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("return_platform_preferential =", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("return_platform_preferential <>", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("return_platform_preferential >", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_platform_preferential >=", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("return_platform_preferential <", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_platform_preferential <=", value, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("return_platform_preferential in", values, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("return_platform_preferential not in", values, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_platform_preferential between", value1, value2, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_platform_preferential not between", value1, value2, "returnPlatformPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialIsNull() {
            addCriterion("return_integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialIsNotNull() {
            addCriterion("return_integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("return_integral_preferential =", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("return_integral_preferential <>", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("return_integral_preferential >", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_integral_preferential >=", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("return_integral_preferential <", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_integral_preferential <=", value, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("return_integral_preferential in", values, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("return_integral_preferential not in", values, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_integral_preferential between", value1, value2, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_integral_preferential not between", value1, value2, "returnIntegralPreferential");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountIsNull() {
            addCriterion("return_commission_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountIsNotNull() {
            addCriterion("return_commission_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountEqualTo(BigDecimal value) {
            addCriterion("return_commission_amount =", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountNotEqualTo(BigDecimal value) {
            addCriterion("return_commission_amount <>", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountGreaterThan(BigDecimal value) {
            addCriterion("return_commission_amount >", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_commission_amount >=", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountLessThan(BigDecimal value) {
            addCriterion("return_commission_amount <", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_commission_amount <=", value, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountIn(List<BigDecimal> values) {
            addCriterion("return_commission_amount in", values, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountNotIn(List<BigDecimal> values) {
            addCriterion("return_commission_amount not in", values, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_commission_amount between", value1, value2, "returnCommissionAmount");
            return (Criteria) this;
        }

        public Criteria andReturnCommissionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_commission_amount not between", value1, value2, "returnCommissionAmount");
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

        public Criteria andDiscountRemarksIsNull() {
            addCriterion("discount_remarks is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksIsNotNull() {
            addCriterion("discount_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksEqualTo(String value) {
            addCriterion("discount_remarks =", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksNotEqualTo(String value) {
            addCriterion("discount_remarks <>", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksGreaterThan(String value) {
            addCriterion("discount_remarks >", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("discount_remarks >=", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksLessThan(String value) {
            addCriterion("discount_remarks <", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksLessThanOrEqualTo(String value) {
            addCriterion("discount_remarks <=", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksLike(String value) {
            addCriterion("discount_remarks like", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksNotLike(String value) {
            addCriterion("discount_remarks not like", value, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksIn(List<String> values) {
            addCriterion("discount_remarks in", values, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksNotIn(List<String> values) {
            addCriterion("discount_remarks not in", values, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksBetween(String value1, String value2) {
            addCriterion("discount_remarks between", value1, value2, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountRemarksNotBetween(String value1, String value2) {
            addCriterion("discount_remarks not between", value1, value2, "discountRemarks");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayIsNull() {
            addCriterion("discount_end_unpay is null");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayIsNotNull() {
            addCriterion("discount_end_unpay is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayEqualTo(BigDecimal value) {
            addCriterion("discount_end_unpay =", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayNotEqualTo(BigDecimal value) {
            addCriterion("discount_end_unpay <>", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayGreaterThan(BigDecimal value) {
            addCriterion("discount_end_unpay >", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_end_unpay >=", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayLessThan(BigDecimal value) {
            addCriterion("discount_end_unpay <", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_end_unpay <=", value, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayIn(List<BigDecimal> values) {
            addCriterion("discount_end_unpay in", values, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayNotIn(List<BigDecimal> values) {
            addCriterion("discount_end_unpay not in", values, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_end_unpay between", value1, value2, "discountEndUnpay");
            return (Criteria) this;
        }

        public Criteria andDiscountEndUnpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_end_unpay not between", value1, value2, "discountEndUnpay");
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

        public Criteria andCurrentMonthSettleAmountIsNull() {
            addCriterion("current_month_settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountIsNotNull() {
            addCriterion("current_month_settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount =", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount <>", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("current_month_settle_amount >", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount >=", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountLessThan(BigDecimal value) {
            addCriterion("current_month_settle_amount <", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount <=", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountIn(List<BigDecimal> values) {
            addCriterion("current_month_settle_amount in", values, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("current_month_settle_amount not in", values, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_month_settle_amount between", value1, value2, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_month_settle_amount not between", value1, value2, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountIsNull() {
            addCriterion("across_month_settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountIsNotNull() {
            addCriterion("across_month_settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountEqualTo(BigDecimal value) {
            addCriterion("across_month_settle_amount =", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("across_month_settle_amount <>", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("across_month_settle_amount >", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("across_month_settle_amount >=", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountLessThan(BigDecimal value) {
            addCriterion("across_month_settle_amount <", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("across_month_settle_amount <=", value, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountIn(List<BigDecimal> values) {
            addCriterion("across_month_settle_amount in", values, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("across_month_settle_amount not in", values, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("across_month_settle_amount between", value1, value2, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("across_month_settle_amount not between", value1, value2, "acrossMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountIsNull() {
            addCriterion("across_month_return_amount is null");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountIsNotNull() {
            addCriterion("across_month_return_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountEqualTo(BigDecimal value) {
            addCriterion("across_month_return_amount =", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountNotEqualTo(BigDecimal value) {
            addCriterion("across_month_return_amount <>", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountGreaterThan(BigDecimal value) {
            addCriterion("across_month_return_amount >", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("across_month_return_amount >=", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountLessThan(BigDecimal value) {
            addCriterion("across_month_return_amount <", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("across_month_return_amount <=", value, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountIn(List<BigDecimal> values) {
            addCriterion("across_month_return_amount in", values, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountNotIn(List<BigDecimal> values) {
            addCriterion("across_month_return_amount not in", values, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("across_month_return_amount between", value1, value2, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andAcrossMonthReturnAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("across_month_return_amount not between", value1, value2, "acrossMonthReturnAmount");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutIsNull() {
            addCriterion("begin_settle_amout is null");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutIsNotNull() {
            addCriterion("begin_settle_amout is not null");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutEqualTo(BigDecimal value) {
            addCriterion("begin_settle_amout =", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutNotEqualTo(BigDecimal value) {
            addCriterion("begin_settle_amout <>", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutGreaterThan(BigDecimal value) {
            addCriterion("begin_settle_amout >", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_settle_amout >=", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutLessThan(BigDecimal value) {
            addCriterion("begin_settle_amout <", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_settle_amout <=", value, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutIn(List<BigDecimal> values) {
            addCriterion("begin_settle_amout in", values, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutNotIn(List<BigDecimal> values) {
            addCriterion("begin_settle_amout not in", values, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_settle_amout between", value1, value2, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andBeginSettleAmoutNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_settle_amout not between", value1, value2, "beginSettleAmout");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountIsNull() {
            addCriterion("end_settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountIsNotNull() {
            addCriterion("end_settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountEqualTo(BigDecimal value) {
            addCriterion("end_settle_amount =", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("end_settle_amount <>", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("end_settle_amount >", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_settle_amount >=", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountLessThan(BigDecimal value) {
            addCriterion("end_settle_amount <", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_settle_amount <=", value, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountIn(List<BigDecimal> values) {
            addCriterion("end_settle_amount in", values, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("end_settle_amount not in", values, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_settle_amount between", value1, value2, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andEndSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_settle_amount not between", value1, value2, "endSettleAmount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountIsNull() {
            addCriterion("begin_not_out_account is null");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountIsNotNull() {
            addCriterion("begin_not_out_account is not null");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountEqualTo(BigDecimal value) {
            addCriterion("begin_not_out_account =", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountNotEqualTo(BigDecimal value) {
            addCriterion("begin_not_out_account <>", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountGreaterThan(BigDecimal value) {
            addCriterion("begin_not_out_account >", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_not_out_account >=", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountLessThan(BigDecimal value) {
            addCriterion("begin_not_out_account <", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_not_out_account <=", value, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountIn(List<BigDecimal> values) {
            addCriterion("begin_not_out_account in", values, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountNotIn(List<BigDecimal> values) {
            addCriterion("begin_not_out_account not in", values, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_not_out_account between", value1, value2, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andBeginNotOutAccountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_not_out_account not between", value1, value2, "beginNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountIsNull() {
            addCriterion("end_not_out_account is null");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountIsNotNull() {
            addCriterion("end_not_out_account is not null");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountEqualTo(BigDecimal value) {
            addCriterion("end_not_out_account =", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountNotEqualTo(BigDecimal value) {
            addCriterion("end_not_out_account <>", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountGreaterThan(BigDecimal value) {
            addCriterion("end_not_out_account >", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_not_out_account >=", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountLessThan(BigDecimal value) {
            addCriterion("end_not_out_account <", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_not_out_account <=", value, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountIn(List<BigDecimal> values) {
            addCriterion("end_not_out_account in", values, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountNotIn(List<BigDecimal> values) {
            addCriterion("end_not_out_account not in", values, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_not_out_account between", value1, value2, "endNotOutAccount");
            return (Criteria) this;
        }

        public Criteria andEndNotOutAccountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_not_out_account not between", value1, value2, "endNotOutAccount");
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