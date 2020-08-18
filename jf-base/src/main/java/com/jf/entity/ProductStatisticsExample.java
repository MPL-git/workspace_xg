package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ProductStatisticsExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysIsNull() {
            addCriterion("sold_count_7_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysIsNotNull() {
            addCriterion("sold_count_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysEqualTo(Integer value) {
            addCriterion("sold_count_7_days =", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysNotEqualTo(Integer value) {
            addCriterion("sold_count_7_days <>", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysGreaterThan(Integer value) {
            addCriterion("sold_count_7_days >", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold_count_7_days >=", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysLessThan(Integer value) {
            addCriterion("sold_count_7_days <", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sold_count_7_days <=", value, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysIn(List<Integer> values) {
            addCriterion("sold_count_7_days in", values, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysNotIn(List<Integer> values) {
            addCriterion("sold_count_7_days not in", values, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_7_days between", value1, value2, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount7DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_7_days not between", value1, value2, "soldCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysIsNull() {
            addCriterion("sold_amount_7_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysIsNotNull() {
            addCriterion("sold_amount_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysEqualTo(BigDecimal value) {
            addCriterion("sold_amount_7_days =", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysNotEqualTo(BigDecimal value) {
            addCriterion("sold_amount_7_days <>", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysGreaterThan(BigDecimal value) {
            addCriterion("sold_amount_7_days >", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_7_days >=", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysLessThan(BigDecimal value) {
            addCriterion("sold_amount_7_days <", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_7_days <=", value, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysIn(List<BigDecimal> values) {
            addCriterion("sold_amount_7_days in", values, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysNotIn(List<BigDecimal> values) {
            addCriterion("sold_amount_7_days not in", values, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_7_days between", value1, value2, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_7_days not between", value1, value2, "soldAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysIsNull() {
            addCriterion("refund_count_7_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysIsNotNull() {
            addCriterion("refund_count_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysEqualTo(Integer value) {
            addCriterion("refund_count_7_days =", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysNotEqualTo(Integer value) {
            addCriterion("refund_count_7_days <>", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysGreaterThan(Integer value) {
            addCriterion("refund_count_7_days >", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_count_7_days >=", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysLessThan(Integer value) {
            addCriterion("refund_count_7_days <", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysLessThanOrEqualTo(Integer value) {
            addCriterion("refund_count_7_days <=", value, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysIn(List<Integer> values) {
            addCriterion("refund_count_7_days in", values, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysNotIn(List<Integer> values) {
            addCriterion("refund_count_7_days not in", values, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_7_days between", value1, value2, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount7DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_7_days not between", value1, value2, "refundCount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysIsNull() {
            addCriterion("refund_amount_7_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysIsNotNull() {
            addCriterion("refund_amount_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysEqualTo(BigDecimal value) {
            addCriterion("refund_amount_7_days =", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount_7_days <>", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysGreaterThan(BigDecimal value) {
            addCriterion("refund_amount_7_days >", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_7_days >=", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysLessThan(BigDecimal value) {
            addCriterion("refund_amount_7_days <", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_7_days <=", value, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysIn(List<BigDecimal> values) {
            addCriterion("refund_amount_7_days in", values, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount_7_days not in", values, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_7_days between", value1, value2, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_7_days not between", value1, value2, "refundAmount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysIsNull() {
            addCriterion("sub_order_count_7_days is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysIsNotNull() {
            addCriterion("sub_order_count_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysEqualTo(Integer value) {
            addCriterion("sub_order_count_7_days =", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysNotEqualTo(Integer value) {
            addCriterion("sub_order_count_7_days <>", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysGreaterThan(Integer value) {
            addCriterion("sub_order_count_7_days >", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_7_days >=", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysLessThan(Integer value) {
            addCriterion("sub_order_count_7_days <", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_7_days <=", value, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysIn(List<Integer> values) {
            addCriterion("sub_order_count_7_days in", values, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysNotIn(List<Integer> values) {
            addCriterion("sub_order_count_7_days not in", values, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_7_days between", value1, value2, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount7DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_7_days not between", value1, value2, "subOrderCount7Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysIsNull() {
            addCriterion("sold_count_30_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysIsNotNull() {
            addCriterion("sold_count_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysEqualTo(Integer value) {
            addCriterion("sold_count_30_days =", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysNotEqualTo(Integer value) {
            addCriterion("sold_count_30_days <>", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysGreaterThan(Integer value) {
            addCriterion("sold_count_30_days >", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold_count_30_days >=", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysLessThan(Integer value) {
            addCriterion("sold_count_30_days <", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sold_count_30_days <=", value, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysIn(List<Integer> values) {
            addCriterion("sold_count_30_days in", values, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysNotIn(List<Integer> values) {
            addCriterion("sold_count_30_days not in", values, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_30_days between", value1, value2, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount30DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_30_days not between", value1, value2, "soldCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysIsNull() {
            addCriterion("sold_amount_30_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysIsNotNull() {
            addCriterion("sold_amount_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysEqualTo(BigDecimal value) {
            addCriterion("sold_amount_30_days =", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysNotEqualTo(BigDecimal value) {
            addCriterion("sold_amount_30_days <>", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysGreaterThan(BigDecimal value) {
            addCriterion("sold_amount_30_days >", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_30_days >=", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysLessThan(BigDecimal value) {
            addCriterion("sold_amount_30_days <", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_30_days <=", value, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysIn(List<BigDecimal> values) {
            addCriterion("sold_amount_30_days in", values, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysNotIn(List<BigDecimal> values) {
            addCriterion("sold_amount_30_days not in", values, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_30_days between", value1, value2, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_30_days not between", value1, value2, "soldAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysIsNull() {
            addCriterion("refund_count_30_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysIsNotNull() {
            addCriterion("refund_count_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysEqualTo(Integer value) {
            addCriterion("refund_count_30_days =", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysNotEqualTo(Integer value) {
            addCriterion("refund_count_30_days <>", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysGreaterThan(Integer value) {
            addCriterion("refund_count_30_days >", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_count_30_days >=", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysLessThan(Integer value) {
            addCriterion("refund_count_30_days <", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysLessThanOrEqualTo(Integer value) {
            addCriterion("refund_count_30_days <=", value, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysIn(List<Integer> values) {
            addCriterion("refund_count_30_days in", values, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysNotIn(List<Integer> values) {
            addCriterion("refund_count_30_days not in", values, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_30_days between", value1, value2, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount30DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_30_days not between", value1, value2, "refundCount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysIsNull() {
            addCriterion("refund_amount_30_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysIsNotNull() {
            addCriterion("refund_amount_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysEqualTo(BigDecimal value) {
            addCriterion("refund_amount_30_days =", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount_30_days <>", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysGreaterThan(BigDecimal value) {
            addCriterion("refund_amount_30_days >", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_30_days >=", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysLessThan(BigDecimal value) {
            addCriterion("refund_amount_30_days <", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_30_days <=", value, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysIn(List<BigDecimal> values) {
            addCriterion("refund_amount_30_days in", values, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount_30_days not in", values, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_30_days between", value1, value2, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_30_days not between", value1, value2, "refundAmount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysIsNull() {
            addCriterion("sub_order_count_30_days is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysIsNotNull() {
            addCriterion("sub_order_count_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysEqualTo(Integer value) {
            addCriterion("sub_order_count_30_days =", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysNotEqualTo(Integer value) {
            addCriterion("sub_order_count_30_days <>", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysGreaterThan(Integer value) {
            addCriterion("sub_order_count_30_days >", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_30_days >=", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysLessThan(Integer value) {
            addCriterion("sub_order_count_30_days <", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_30_days <=", value, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysIn(List<Integer> values) {
            addCriterion("sub_order_count_30_days in", values, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysNotIn(List<Integer> values) {
            addCriterion("sub_order_count_30_days not in", values, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_30_days between", value1, value2, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount30DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_30_days not between", value1, value2, "subOrderCount30Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysIsNull() {
            addCriterion("sold_count_90_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysIsNotNull() {
            addCriterion("sold_count_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysEqualTo(Integer value) {
            addCriterion("sold_count_90_days =", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysNotEqualTo(Integer value) {
            addCriterion("sold_count_90_days <>", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysGreaterThan(Integer value) {
            addCriterion("sold_count_90_days >", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold_count_90_days >=", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysLessThan(Integer value) {
            addCriterion("sold_count_90_days <", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sold_count_90_days <=", value, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysIn(List<Integer> values) {
            addCriterion("sold_count_90_days in", values, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysNotIn(List<Integer> values) {
            addCriterion("sold_count_90_days not in", values, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_90_days between", value1, value2, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldCount90DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sold_count_90_days not between", value1, value2, "soldCount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysIsNull() {
            addCriterion("sold_amount_90_days is null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysIsNotNull() {
            addCriterion("sold_amount_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysEqualTo(BigDecimal value) {
            addCriterion("sold_amount_90_days =", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysNotEqualTo(BigDecimal value) {
            addCriterion("sold_amount_90_days <>", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysGreaterThan(BigDecimal value) {
            addCriterion("sold_amount_90_days >", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_90_days >=", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysLessThan(BigDecimal value) {
            addCriterion("sold_amount_90_days <", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_amount_90_days <=", value, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysIn(List<BigDecimal> values) {
            addCriterion("sold_amount_90_days in", values, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysNotIn(List<BigDecimal> values) {
            addCriterion("sold_amount_90_days not in", values, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_90_days between", value1, value2, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSoldAmount90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_amount_90_days not between", value1, value2, "soldAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysIsNull() {
            addCriterion("refund_count_90_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysIsNotNull() {
            addCriterion("refund_count_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysEqualTo(Integer value) {
            addCriterion("refund_count_90_days =", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysNotEqualTo(Integer value) {
            addCriterion("refund_count_90_days <>", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysGreaterThan(Integer value) {
            addCriterion("refund_count_90_days >", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_count_90_days >=", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysLessThan(Integer value) {
            addCriterion("refund_count_90_days <", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysLessThanOrEqualTo(Integer value) {
            addCriterion("refund_count_90_days <=", value, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysIn(List<Integer> values) {
            addCriterion("refund_count_90_days in", values, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysNotIn(List<Integer> values) {
            addCriterion("refund_count_90_days not in", values, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_90_days between", value1, value2, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundCount90DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_count_90_days not between", value1, value2, "refundCount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysIsNull() {
            addCriterion("refund_amount_90_days is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysIsNotNull() {
            addCriterion("refund_amount_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysEqualTo(BigDecimal value) {
            addCriterion("refund_amount_90_days =", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysNotEqualTo(BigDecimal value) {
            addCriterion("refund_amount_90_days <>", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysGreaterThan(BigDecimal value) {
            addCriterion("refund_amount_90_days >", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_90_days >=", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysLessThan(BigDecimal value) {
            addCriterion("refund_amount_90_days <", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_amount_90_days <=", value, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysIn(List<BigDecimal> values) {
            addCriterion("refund_amount_90_days in", values, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysNotIn(List<BigDecimal> values) {
            addCriterion("refund_amount_90_days not in", values, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_90_days between", value1, value2, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andRefundAmount90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_amount_90_days not between", value1, value2, "refundAmount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysIsNull() {
            addCriterion("sub_order_count_90_days is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysIsNotNull() {
            addCriterion("sub_order_count_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysEqualTo(Integer value) {
            addCriterion("sub_order_count_90_days =", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysNotEqualTo(Integer value) {
            addCriterion("sub_order_count_90_days <>", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysGreaterThan(Integer value) {
            addCriterion("sub_order_count_90_days >", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_90_days >=", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysLessThan(Integer value) {
            addCriterion("sub_order_count_90_days <", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_count_90_days <=", value, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysIn(List<Integer> values) {
            addCriterion("sub_order_count_90_days in", values, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysNotIn(List<Integer> values) {
            addCriterion("sub_order_count_90_days not in", values, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_90_days between", value1, value2, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andSubOrderCount90DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_count_90_days not between", value1, value2, "subOrderCount90Days");
            return (Criteria) this;
        }

        public Criteria andApplauseRateIsNull() {
            addCriterion("applause_rate is null");
            return (Criteria) this;
        }

        public Criteria andApplauseRateIsNotNull() {
            addCriterion("applause_rate is not null");
            return (Criteria) this;
        }

        public Criteria andApplauseRateEqualTo(BigDecimal value) {
            addCriterion("applause_rate =", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateNotEqualTo(BigDecimal value) {
            addCriterion("applause_rate <>", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateGreaterThan(BigDecimal value) {
            addCriterion("applause_rate >", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("applause_rate >=", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateLessThan(BigDecimal value) {
            addCriterion("applause_rate <", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("applause_rate <=", value, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateIn(List<BigDecimal> values) {
            addCriterion("applause_rate in", values, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateNotIn(List<BigDecimal> values) {
            addCriterion("applause_rate not in", values, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applause_rate between", value1, value2, "applauseRate");
            return (Criteria) this;
        }

        public Criteria andApplauseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("applause_rate not between", value1, value2, "applauseRate");
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