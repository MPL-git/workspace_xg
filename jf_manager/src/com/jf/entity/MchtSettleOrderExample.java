package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MchtSettleOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtSettleOrderExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
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

        public Criteria andMchtTypeIsNull() {
            addCriterion("mcht_type is null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIsNotNull() {
            addCriterion("mcht_type is not null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeEqualTo(String value) {
            addCriterion("mcht_type =", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotEqualTo(String value) {
            addCriterion("mcht_type <>", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThan(String value) {
            addCriterion("mcht_type >", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_type >=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThan(String value) {
            addCriterion("mcht_type <", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThanOrEqualTo(String value) {
            addCriterion("mcht_type <=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLike(String value) {
            addCriterion("mcht_type like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotLike(String value) {
            addCriterion("mcht_type not like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIn(List<String> values) {
            addCriterion("mcht_type in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotIn(List<String> values) {
            addCriterion("mcht_type not in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeBetween(String value1, String value2) {
            addCriterion("mcht_type between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotBetween(String value1, String value2) {
            addCriterion("mcht_type not between", value1, value2, "mchtType");
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

        public Criteria andEarnestMoneyIsNull() {
            addCriterion("earnest_money is null");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyIsNotNull() {
            addCriterion("earnest_money is not null");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyEqualTo(BigDecimal value) {
            addCriterion("earnest_money =", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyNotEqualTo(BigDecimal value) {
            addCriterion("earnest_money <>", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyGreaterThan(BigDecimal value) {
            addCriterion("earnest_money >", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("earnest_money >=", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyLessThan(BigDecimal value) {
            addCriterion("earnest_money <", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("earnest_money <=", value, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyIn(List<BigDecimal> values) {
            addCriterion("earnest_money in", values, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyNotIn(List<BigDecimal> values) {
            addCriterion("earnest_money not in", values, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earnest_money between", value1, value2, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andEarnestMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("earnest_money not between", value1, value2, "earnestMoney");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNull() {
            addCriterion("confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNotNull() {
            addCriterion("confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusEqualTo(String value) {
            addCriterion("confirm_status =", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotEqualTo(String value) {
            addCriterion("confirm_status <>", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThan(String value) {
            addCriterion("confirm_status >", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("confirm_status >=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThan(String value) {
            addCriterion("confirm_status <", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("confirm_status <=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLike(String value) {
            addCriterion("confirm_status like", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotLike(String value) {
            addCriterion("confirm_status not like", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIn(List<String> values) {
            addCriterion("confirm_status in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotIn(List<String> values) {
            addCriterion("confirm_status not in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusBetween(String value1, String value2) {
            addCriterion("confirm_status between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("confirm_status not between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateIsNull() {
            addCriterion("mcht_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateIsNotNull() {
            addCriterion("mcht_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateEqualTo(Date value) {
            addCriterion("mcht_confirm_date =", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateNotEqualTo(Date value) {
            addCriterion("mcht_confirm_date <>", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateGreaterThan(Date value) {
            addCriterion("mcht_confirm_date >", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_confirm_date >=", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateLessThan(Date value) {
            addCriterion("mcht_confirm_date <", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_confirm_date <=", value, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateIn(List<Date> values) {
            addCriterion("mcht_confirm_date in", values, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateNotIn(List<Date> values) {
            addCriterion("mcht_confirm_date not in", values, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateBetween(Date value1, Date value2) {
            addCriterion("mcht_confirm_date between", value1, value2, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_confirm_date not between", value1, value2, "mchtConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateIsNull() {
            addCriterion("platform_confirm_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateIsNotNull() {
            addCriterion("platform_confirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateEqualTo(Date value) {
            addCriterion("platform_confirm_date =", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateNotEqualTo(Date value) {
            addCriterion("platform_confirm_date <>", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateGreaterThan(Date value) {
            addCriterion("platform_confirm_date >", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("platform_confirm_date >=", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateLessThan(Date value) {
            addCriterion("platform_confirm_date <", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateLessThanOrEqualTo(Date value) {
            addCriterion("platform_confirm_date <=", value, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateIn(List<Date> values) {
            addCriterion("platform_confirm_date in", values, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateNotIn(List<Date> values) {
            addCriterion("platform_confirm_date not in", values, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateBetween(Date value1, Date value2) {
            addCriterion("platform_confirm_date between", value1, value2, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andPlatformConfirmDateNotBetween(Date value1, Date value2) {
            addCriterion("platform_confirm_date not between", value1, value2, "platformConfirmDate");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeIsNull() {
            addCriterion("mcht_collect_type is null");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeIsNotNull() {
            addCriterion("mcht_collect_type is not null");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeEqualTo(String value) {
            addCriterion("mcht_collect_type =", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeNotEqualTo(String value) {
            addCriterion("mcht_collect_type <>", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeGreaterThan(String value) {
            addCriterion("mcht_collect_type >", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_collect_type >=", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeLessThan(String value) {
            addCriterion("mcht_collect_type <", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeLessThanOrEqualTo(String value) {
            addCriterion("mcht_collect_type <=", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeLike(String value) {
            addCriterion("mcht_collect_type like", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeNotLike(String value) {
            addCriterion("mcht_collect_type not like", value, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeIn(List<String> values) {
            addCriterion("mcht_collect_type in", values, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeNotIn(List<String> values) {
            addCriterion("mcht_collect_type not in", values, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeBetween(String value1, String value2) {
            addCriterion("mcht_collect_type between", value1, value2, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtCollectTypeNotBetween(String value1, String value2) {
            addCriterion("mcht_collect_type not between", value1, value2, "mchtCollectType");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameIsNull() {
            addCriterion("mcht_receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameIsNotNull() {
            addCriterion("mcht_receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameEqualTo(String value) {
            addCriterion("mcht_receiver_name =", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameNotEqualTo(String value) {
            addCriterion("mcht_receiver_name <>", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameGreaterThan(String value) {
            addCriterion("mcht_receiver_name >", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_name >=", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameLessThan(String value) {
            addCriterion("mcht_receiver_name <", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_name <=", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameLike(String value) {
            addCriterion("mcht_receiver_name like", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameNotLike(String value) {
            addCriterion("mcht_receiver_name not like", value, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameIn(List<String> values) {
            addCriterion("mcht_receiver_name in", values, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameNotIn(List<String> values) {
            addCriterion("mcht_receiver_name not in", values, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameBetween(String value1, String value2) {
            addCriterion("mcht_receiver_name between", value1, value2, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverNameNotBetween(String value1, String value2) {
            addCriterion("mcht_receiver_name not between", value1, value2, "mchtReceiverName");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneIsNull() {
            addCriterion("mcht_receiver_phone is null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneIsNotNull() {
            addCriterion("mcht_receiver_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneEqualTo(String value) {
            addCriterion("mcht_receiver_phone =", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneNotEqualTo(String value) {
            addCriterion("mcht_receiver_phone <>", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneGreaterThan(String value) {
            addCriterion("mcht_receiver_phone >", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_phone >=", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneLessThan(String value) {
            addCriterion("mcht_receiver_phone <", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneLessThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_phone <=", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneLike(String value) {
            addCriterion("mcht_receiver_phone like", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneNotLike(String value) {
            addCriterion("mcht_receiver_phone not like", value, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneIn(List<String> values) {
            addCriterion("mcht_receiver_phone in", values, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneNotIn(List<String> values) {
            addCriterion("mcht_receiver_phone not in", values, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneBetween(String value1, String value2) {
            addCriterion("mcht_receiver_phone between", value1, value2, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverPhoneNotBetween(String value1, String value2) {
            addCriterion("mcht_receiver_phone not between", value1, value2, "mchtReceiverPhone");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressIsNull() {
            addCriterion("mcht_receiver_address is null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressIsNotNull() {
            addCriterion("mcht_receiver_address is not null");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressEqualTo(String value) {
            addCriterion("mcht_receiver_address =", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressNotEqualTo(String value) {
            addCriterion("mcht_receiver_address <>", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressGreaterThan(String value) {
            addCriterion("mcht_receiver_address >", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_address >=", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressLessThan(String value) {
            addCriterion("mcht_receiver_address <", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressLessThanOrEqualTo(String value) {
            addCriterion("mcht_receiver_address <=", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressLike(String value) {
            addCriterion("mcht_receiver_address like", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressNotLike(String value) {
            addCriterion("mcht_receiver_address not like", value, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressIn(List<String> values) {
            addCriterion("mcht_receiver_address in", values, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressNotIn(List<String> values) {
            addCriterion("mcht_receiver_address not in", values, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressBetween(String value1, String value2) {
            addCriterion("mcht_receiver_address between", value1, value2, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtReceiverAddressNotBetween(String value1, String value2) {
            addCriterion("mcht_receiver_address not between", value1, value2, "mchtReceiverAddress");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusIsNull() {
            addCriterion("mcht_invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusIsNotNull() {
            addCriterion("mcht_invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusEqualTo(String value) {
            addCriterion("mcht_invoice_status =", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusNotEqualTo(String value) {
            addCriterion("mcht_invoice_status <>", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusGreaterThan(String value) {
            addCriterion("mcht_invoice_status >", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_invoice_status >=", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusLessThan(String value) {
            addCriterion("mcht_invoice_status <", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusLessThanOrEqualTo(String value) {
            addCriterion("mcht_invoice_status <=", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusLike(String value) {
            addCriterion("mcht_invoice_status like", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusNotLike(String value) {
            addCriterion("mcht_invoice_status not like", value, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusIn(List<String> values) {
            addCriterion("mcht_invoice_status in", values, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusNotIn(List<String> values) {
            addCriterion("mcht_invoice_status not in", values, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusBetween(String value1, String value2) {
            addCriterion("mcht_invoice_status between", value1, value2, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceStatusNotBetween(String value1, String value2) {
            addCriterion("mcht_invoice_status not between", value1, value2, "mchtInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateIsNull() {
            addCriterion("mcht_invoice_date is null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateIsNotNull() {
            addCriterion("mcht_invoice_date is not null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateEqualTo(Date value) {
            addCriterion("mcht_invoice_date =", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateNotEqualTo(Date value) {
            addCriterion("mcht_invoice_date <>", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateGreaterThan(Date value) {
            addCriterion("mcht_invoice_date >", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("mcht_invoice_date >=", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateLessThan(Date value) {
            addCriterion("mcht_invoice_date <", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateLessThanOrEqualTo(Date value) {
            addCriterion("mcht_invoice_date <=", value, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateIn(List<Date> values) {
            addCriterion("mcht_invoice_date in", values, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateNotIn(List<Date> values) {
            addCriterion("mcht_invoice_date not in", values, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateBetween(Date value1, Date value2) {
            addCriterion("mcht_invoice_date between", value1, value2, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceDateNotBetween(Date value1, Date value2) {
            addCriterion("mcht_invoice_date not between", value1, value2, "mchtInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdIsNull() {
            addCriterion("mcht_invoice_express_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdIsNotNull() {
            addCriterion("mcht_invoice_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdEqualTo(Integer value) {
            addCriterion("mcht_invoice_express_id =", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdNotEqualTo(Integer value) {
            addCriterion("mcht_invoice_express_id <>", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdGreaterThan(Integer value) {
            addCriterion("mcht_invoice_express_id >", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_invoice_express_id >=", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdLessThan(Integer value) {
            addCriterion("mcht_invoice_express_id <", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_invoice_express_id <=", value, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdIn(List<Integer> values) {
            addCriterion("mcht_invoice_express_id in", values, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdNotIn(List<Integer> values) {
            addCriterion("mcht_invoice_express_id not in", values, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_invoice_express_id between", value1, value2, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_invoice_express_id not between", value1, value2, "mchtInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoIsNull() {
            addCriterion("mcht_invoice_express_no is null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoIsNotNull() {
            addCriterion("mcht_invoice_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoEqualTo(String value) {
            addCriterion("mcht_invoice_express_no =", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoNotEqualTo(String value) {
            addCriterion("mcht_invoice_express_no <>", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoGreaterThan(String value) {
            addCriterion("mcht_invoice_express_no >", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_invoice_express_no >=", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoLessThan(String value) {
            addCriterion("mcht_invoice_express_no <", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoLessThanOrEqualTo(String value) {
            addCriterion("mcht_invoice_express_no <=", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoLike(String value) {
            addCriterion("mcht_invoice_express_no like", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoNotLike(String value) {
            addCriterion("mcht_invoice_express_no not like", value, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoIn(List<String> values) {
            addCriterion("mcht_invoice_express_no in", values, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoNotIn(List<String> values) {
            addCriterion("mcht_invoice_express_no not in", values, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoBetween(String value1, String value2) {
            addCriterion("mcht_invoice_express_no between", value1, value2, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andMchtInvoiceExpressNoNotBetween(String value1, String value2) {
            addCriterion("mcht_invoice_express_no not between", value1, value2, "mchtInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusIsNull() {
            addCriterion("platform_collect_status is null");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusIsNotNull() {
            addCriterion("platform_collect_status is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusEqualTo(String value) {
            addCriterion("platform_collect_status =", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusNotEqualTo(String value) {
            addCriterion("platform_collect_status <>", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusGreaterThan(String value) {
            addCriterion("platform_collect_status >", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("platform_collect_status >=", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusLessThan(String value) {
            addCriterion("platform_collect_status <", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusLessThanOrEqualTo(String value) {
            addCriterion("platform_collect_status <=", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusLike(String value) {
            addCriterion("platform_collect_status like", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusNotLike(String value) {
            addCriterion("platform_collect_status not like", value, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusIn(List<String> values) {
            addCriterion("platform_collect_status in", values, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusNotIn(List<String> values) {
            addCriterion("platform_collect_status not in", values, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusBetween(String value1, String value2) {
            addCriterion("platform_collect_status between", value1, value2, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformCollectStatusNotBetween(String value1, String value2) {
            addCriterion("platform_collect_status not between", value1, value2, "platformCollectStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusIsNull() {
            addCriterion("platform_invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusIsNotNull() {
            addCriterion("platform_invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusEqualTo(String value) {
            addCriterion("platform_invoice_status =", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusNotEqualTo(String value) {
            addCriterion("platform_invoice_status <>", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusGreaterThan(String value) {
            addCriterion("platform_invoice_status >", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusGreaterThanOrEqualTo(String value) {
            addCriterion("platform_invoice_status >=", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusLessThan(String value) {
            addCriterion("platform_invoice_status <", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusLessThanOrEqualTo(String value) {
            addCriterion("platform_invoice_status <=", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusLike(String value) {
            addCriterion("platform_invoice_status like", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusNotLike(String value) {
            addCriterion("platform_invoice_status not like", value, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusIn(List<String> values) {
            addCriterion("platform_invoice_status in", values, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusNotIn(List<String> values) {
            addCriterion("platform_invoice_status not in", values, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusBetween(String value1, String value2) {
            addCriterion("platform_invoice_status between", value1, value2, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceStatusNotBetween(String value1, String value2) {
            addCriterion("platform_invoice_status not between", value1, value2, "platformInvoiceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateIsNull() {
            addCriterion("platform_invoice_date is null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateIsNotNull() {
            addCriterion("platform_invoice_date is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateEqualTo(Date value) {
            addCriterion("platform_invoice_date =", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateNotEqualTo(Date value) {
            addCriterion("platform_invoice_date <>", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateGreaterThan(Date value) {
            addCriterion("platform_invoice_date >", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("platform_invoice_date >=", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateLessThan(Date value) {
            addCriterion("platform_invoice_date <", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateLessThanOrEqualTo(Date value) {
            addCriterion("platform_invoice_date <=", value, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateIn(List<Date> values) {
            addCriterion("platform_invoice_date in", values, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateNotIn(List<Date> values) {
            addCriterion("platform_invoice_date not in", values, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateBetween(Date value1, Date value2) {
            addCriterion("platform_invoice_date between", value1, value2, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceDateNotBetween(Date value1, Date value2) {
            addCriterion("platform_invoice_date not between", value1, value2, "platformInvoiceDate");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdIsNull() {
            addCriterion("platform_invoice_express_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdIsNotNull() {
            addCriterion("platform_invoice_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdEqualTo(Integer value) {
            addCriterion("platform_invoice_express_id =", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdNotEqualTo(Integer value) {
            addCriterion("platform_invoice_express_id <>", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdGreaterThan(Integer value) {
            addCriterion("platform_invoice_express_id >", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_invoice_express_id >=", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdLessThan(Integer value) {
            addCriterion("platform_invoice_express_id <", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("platform_invoice_express_id <=", value, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdIn(List<Integer> values) {
            addCriterion("platform_invoice_express_id in", values, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdNotIn(List<Integer> values) {
            addCriterion("platform_invoice_express_id not in", values, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("platform_invoice_express_id between", value1, value2, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_invoice_express_id not between", value1, value2, "platformInvoiceExpressId");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoIsNull() {
            addCriterion("platform_invoice_express_no is null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoIsNotNull() {
            addCriterion("platform_invoice_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoEqualTo(String value) {
            addCriterion("platform_invoice_express_no =", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoNotEqualTo(String value) {
            addCriterion("platform_invoice_express_no <>", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoGreaterThan(String value) {
            addCriterion("platform_invoice_express_no >", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("platform_invoice_express_no >=", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoLessThan(String value) {
            addCriterion("platform_invoice_express_no <", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoLessThanOrEqualTo(String value) {
            addCriterion("platform_invoice_express_no <=", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoLike(String value) {
            addCriterion("platform_invoice_express_no like", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoNotLike(String value) {
            addCriterion("platform_invoice_express_no not like", value, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoIn(List<String> values) {
            addCriterion("platform_invoice_express_no in", values, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoNotIn(List<String> values) {
            addCriterion("platform_invoice_express_no not in", values, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoBetween(String value1, String value2) {
            addCriterion("platform_invoice_express_no between", value1, value2, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andPlatformInvoiceExpressNoNotBetween(String value1, String value2) {
            addCriterion("platform_invoice_express_no not between", value1, value2, "platformInvoiceExpressNo");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNull() {
            addCriterion("manage_self_freight is null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNotNull() {
            addCriterion("manage_self_freight is not null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight =", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <>", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThan(BigDecimal value) {
            addCriterion("manage_self_freight >", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight >=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThan(BigDecimal value) {
            addCriterion("manage_self_freight <", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight not in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight between", value1, value2, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight not between", value1, value2, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(String value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(String value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(String value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(String value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(String value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLike(String value) {
            addCriterion("pay_status like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotLike(String value) {
            addCriterion("pay_status not like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<String> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<String> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(String value1, String value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(String value1, String value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateIsNull() {
            addCriterion("pay_ready_date is null");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateIsNotNull() {
            addCriterion("pay_ready_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ready_date =", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ready_date <>", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("pay_ready_date >", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ready_date >=", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateLessThan(Date value) {
            addCriterionForJDBCDate("pay_ready_date <", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pay_ready_date <=", value, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateIn(List<Date> values) {
            addCriterionForJDBCDate("pay_ready_date in", values, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pay_ready_date not in", values, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pay_ready_date between", value1, value2, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayReadyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pay_ready_date not between", value1, value2, "payReadyDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayCodeIsNull() {
            addCriterion("pay_code is null");
            return (Criteria) this;
        }

        public Criteria andPayCodeIsNotNull() {
            addCriterion("pay_code is not null");
            return (Criteria) this;
        }

        public Criteria andPayCodeEqualTo(String value) {
            addCriterion("pay_code =", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotEqualTo(String value) {
            addCriterion("pay_code <>", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeGreaterThan(String value) {
            addCriterion("pay_code >", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_code >=", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLessThan(String value) {
            addCriterion("pay_code <", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLessThanOrEqualTo(String value) {
            addCriterion("pay_code <=", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLike(String value) {
            addCriterion("pay_code like", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotLike(String value) {
            addCriterion("pay_code not like", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeIn(List<String> values) {
            addCriterion("pay_code in", values, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotIn(List<String> values) {
            addCriterion("pay_code not in", values, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeBetween(String value1, String value2) {
            addCriterion("pay_code between", value1, value2, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotBetween(String value1, String value2) {
            addCriterion("pay_code not between", value1, value2, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIsNull() {
            addCriterion("pay_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIsNotNull() {
            addCriterion("pay_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdEqualTo(Integer value) {
            addCriterion("pay_staff_id =", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotEqualTo(Integer value) {
            addCriterion("pay_staff_id <>", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdGreaterThan(Integer value) {
            addCriterion("pay_staff_id >", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_staff_id >=", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdLessThan(Integer value) {
            addCriterion("pay_staff_id <", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_staff_id <=", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIn(List<Integer> values) {
            addCriterion("pay_staff_id in", values, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotIn(List<Integer> values) {
            addCriterion("pay_staff_id not in", values, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_staff_id between", value1, value2, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_staff_id not between", value1, value2, "payStaffId");
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