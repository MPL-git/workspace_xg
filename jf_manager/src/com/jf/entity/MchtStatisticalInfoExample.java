package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtStatisticalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtStatisticalInfoExample() {
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

        public Criteria andActivityCount7DaysIsNull() {
            addCriterion("activity_count_7_days is null");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysIsNotNull() {
            addCriterion("activity_count_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysEqualTo(Integer value) {
            addCriterion("activity_count_7_days =", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysNotEqualTo(Integer value) {
            addCriterion("activity_count_7_days <>", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysGreaterThan(Integer value) {
            addCriterion("activity_count_7_days >", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_count_7_days >=", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysLessThan(Integer value) {
            addCriterion("activity_count_7_days <", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysLessThanOrEqualTo(Integer value) {
            addCriterion("activity_count_7_days <=", value, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysIn(List<Integer> values) {
            addCriterion("activity_count_7_days in", values, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysNotIn(List<Integer> values) {
            addCriterion("activity_count_7_days not in", values, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_7_days between", value1, value2, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount7DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_7_days not between", value1, value2, "activityCount7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysIsNull() {
            addCriterion("pay_amount_sum_7_days is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysIsNotNull() {
            addCriterion("pay_amount_sum_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days =", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days <>", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysGreaterThan(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days >", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days >=", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysLessThan(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days <", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_7_days <=", value, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_7_days in", values, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_7_days not in", values, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_7_days between", value1, value2, "payAmountSum7Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_7_days not between", value1, value2, "payAmountSum7Days");
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

        public Criteria andReturnRate7DaysIsNull() {
            addCriterion("return_rate_7_days is null");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysIsNotNull() {
            addCriterion("return_rate_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysEqualTo(BigDecimal value) {
            addCriterion("return_rate_7_days =", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysNotEqualTo(BigDecimal value) {
            addCriterion("return_rate_7_days <>", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysGreaterThan(BigDecimal value) {
            addCriterion("return_rate_7_days >", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_7_days >=", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysLessThan(BigDecimal value) {
            addCriterion("return_rate_7_days <", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_7_days <=", value, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysIn(List<BigDecimal> values) {
            addCriterion("return_rate_7_days in", values, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysNotIn(List<BigDecimal> values) {
            addCriterion("return_rate_7_days not in", values, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_7_days between", value1, value2, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_7_days not between", value1, value2, "returnRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysIsNull() {
            addCriterion("appeal_rate_7_days is null");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysIsNotNull() {
            addCriterion("appeal_rate_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_7_days =", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysNotEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_7_days <>", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysGreaterThan(BigDecimal value) {
            addCriterion("appeal_rate_7_days >", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_7_days >=", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysLessThan(BigDecimal value) {
            addCriterion("appeal_rate_7_days <", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_7_days <=", value, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_7_days in", values, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysNotIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_7_days not in", values, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_7_days between", value1, value2, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_7_days not between", value1, value2, "appealRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysIsNull() {
            addCriterion("intervention_rate_7_days is null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysIsNotNull() {
            addCriterion("intervention_rate_7_days is not null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_7_days =", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysNotEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_7_days <>", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysGreaterThan(BigDecimal value) {
            addCriterion("intervention_rate_7_days >", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_7_days >=", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysLessThan(BigDecimal value) {
            addCriterion("intervention_rate_7_days <", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_7_days <=", value, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_7_days in", values, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysNotIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_7_days not in", values, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_7_days between", value1, value2, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate7DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_7_days not between", value1, value2, "interventionRate7Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysIsNull() {
            addCriterion("activity_count_30_days is null");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysIsNotNull() {
            addCriterion("activity_count_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysEqualTo(Integer value) {
            addCriterion("activity_count_30_days =", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysNotEqualTo(Integer value) {
            addCriterion("activity_count_30_days <>", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysGreaterThan(Integer value) {
            addCriterion("activity_count_30_days >", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_count_30_days >=", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysLessThan(Integer value) {
            addCriterion("activity_count_30_days <", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysLessThanOrEqualTo(Integer value) {
            addCriterion("activity_count_30_days <=", value, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysIn(List<Integer> values) {
            addCriterion("activity_count_30_days in", values, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysNotIn(List<Integer> values) {
            addCriterion("activity_count_30_days not in", values, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_30_days between", value1, value2, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount30DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_30_days not between", value1, value2, "activityCount30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysIsNull() {
            addCriterion("pay_amount_sum_30_days is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysIsNotNull() {
            addCriterion("pay_amount_sum_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days =", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days <>", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysGreaterThan(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days >", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days >=", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysLessThan(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days <", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_30_days <=", value, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_30_days in", values, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_30_days not in", values, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_30_days between", value1, value2, "payAmountSum30Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_30_days not between", value1, value2, "payAmountSum30Days");
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

        public Criteria andReturnRate30DaysIsNull() {
            addCriterion("return_rate_30_days is null");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysIsNotNull() {
            addCriterion("return_rate_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysEqualTo(BigDecimal value) {
            addCriterion("return_rate_30_days =", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysNotEqualTo(BigDecimal value) {
            addCriterion("return_rate_30_days <>", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysGreaterThan(BigDecimal value) {
            addCriterion("return_rate_30_days >", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_30_days >=", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysLessThan(BigDecimal value) {
            addCriterion("return_rate_30_days <", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_30_days <=", value, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysIn(List<BigDecimal> values) {
            addCriterion("return_rate_30_days in", values, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysNotIn(List<BigDecimal> values) {
            addCriterion("return_rate_30_days not in", values, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_30_days between", value1, value2, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_30_days not between", value1, value2, "returnRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysIsNull() {
            addCriterion("appeal_rate_30_days is null");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysIsNotNull() {
            addCriterion("appeal_rate_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_30_days =", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysNotEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_30_days <>", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysGreaterThan(BigDecimal value) {
            addCriterion("appeal_rate_30_days >", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_30_days >=", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysLessThan(BigDecimal value) {
            addCriterion("appeal_rate_30_days <", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_30_days <=", value, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_30_days in", values, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysNotIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_30_days not in", values, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_30_days between", value1, value2, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_30_days not between", value1, value2, "appealRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysIsNull() {
            addCriterion("intervention_rate_30_days is null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysIsNotNull() {
            addCriterion("intervention_rate_30_days is not null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_30_days =", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysNotEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_30_days <>", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysGreaterThan(BigDecimal value) {
            addCriterion("intervention_rate_30_days >", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_30_days >=", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysLessThan(BigDecimal value) {
            addCriterion("intervention_rate_30_days <", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_30_days <=", value, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_30_days in", values, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysNotIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_30_days not in", values, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_30_days between", value1, value2, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate30DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_30_days not between", value1, value2, "interventionRate30Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysIsNull() {
            addCriterion("activity_count_90_days is null");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysIsNotNull() {
            addCriterion("activity_count_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysEqualTo(Integer value) {
            addCriterion("activity_count_90_days =", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysNotEqualTo(Integer value) {
            addCriterion("activity_count_90_days <>", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysGreaterThan(Integer value) {
            addCriterion("activity_count_90_days >", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_count_90_days >=", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysLessThan(Integer value) {
            addCriterion("activity_count_90_days <", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysLessThanOrEqualTo(Integer value) {
            addCriterion("activity_count_90_days <=", value, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysIn(List<Integer> values) {
            addCriterion("activity_count_90_days in", values, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysNotIn(List<Integer> values) {
            addCriterion("activity_count_90_days not in", values, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_90_days between", value1, value2, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andActivityCount90DaysNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_count_90_days not between", value1, value2, "activityCount90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysIsNull() {
            addCriterion("pay_amount_sum_90_days is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysIsNotNull() {
            addCriterion("pay_amount_sum_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days =", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days <>", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysGreaterThan(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days >", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days >=", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysLessThan(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days <", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount_sum_90_days <=", value, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_90_days in", values, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount_sum_90_days not in", values, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_90_days between", value1, value2, "payAmountSum90Days");
            return (Criteria) this;
        }

        public Criteria andPayAmountSum90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount_sum_90_days not between", value1, value2, "payAmountSum90Days");
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

        public Criteria andReturnRate90DaysIsNull() {
            addCriterion("return_rate_90_days is null");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysIsNotNull() {
            addCriterion("return_rate_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysEqualTo(BigDecimal value) {
            addCriterion("return_rate_90_days =", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysNotEqualTo(BigDecimal value) {
            addCriterion("return_rate_90_days <>", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysGreaterThan(BigDecimal value) {
            addCriterion("return_rate_90_days >", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_90_days >=", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysLessThan(BigDecimal value) {
            addCriterion("return_rate_90_days <", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_rate_90_days <=", value, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysIn(List<BigDecimal> values) {
            addCriterion("return_rate_90_days in", values, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysNotIn(List<BigDecimal> values) {
            addCriterion("return_rate_90_days not in", values, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_90_days between", value1, value2, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andReturnRate90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_rate_90_days not between", value1, value2, "returnRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysIsNull() {
            addCriterion("appeal_rate_90_days is null");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysIsNotNull() {
            addCriterion("appeal_rate_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_90_days =", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysNotEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_90_days <>", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysGreaterThan(BigDecimal value) {
            addCriterion("appeal_rate_90_days >", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_90_days >=", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysLessThan(BigDecimal value) {
            addCriterion("appeal_rate_90_days <", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_rate_90_days <=", value, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_90_days in", values, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysNotIn(List<BigDecimal> values) {
            addCriterion("appeal_rate_90_days not in", values, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_90_days between", value1, value2, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andAppealRate90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_rate_90_days not between", value1, value2, "appealRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysIsNull() {
            addCriterion("intervention_rate_90_days is null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysIsNotNull() {
            addCriterion("intervention_rate_90_days is not null");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_90_days =", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysNotEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_90_days <>", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysGreaterThan(BigDecimal value) {
            addCriterion("intervention_rate_90_days >", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_90_days >=", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysLessThan(BigDecimal value) {
            addCriterion("intervention_rate_90_days <", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysLessThanOrEqualTo(BigDecimal value) {
            addCriterion("intervention_rate_90_days <=", value, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_90_days in", values, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysNotIn(List<BigDecimal> values) {
            addCriterion("intervention_rate_90_days not in", values, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_90_days between", value1, value2, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andInterventionRate90DaysNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("intervention_rate_90_days not between", value1, value2, "interventionRate90Days");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateIsNull() {
            addCriterion("product_applause_rate is null");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateIsNotNull() {
            addCriterion("product_applause_rate is not null");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateEqualTo(BigDecimal value) {
            addCriterion("product_applause_rate =", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateNotEqualTo(BigDecimal value) {
            addCriterion("product_applause_rate <>", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateGreaterThan(BigDecimal value) {
            addCriterion("product_applause_rate >", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_applause_rate >=", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateLessThan(BigDecimal value) {
            addCriterion("product_applause_rate <", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_applause_rate <=", value, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateIn(List<BigDecimal> values) {
            addCriterion("product_applause_rate in", values, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateNotIn(List<BigDecimal> values) {
            addCriterion("product_applause_rate not in", values, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_applause_rate between", value1, value2, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductApplauseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_applause_rate not between", value1, value2, "productApplauseRate");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgIsNull() {
            addCriterion("product_score_avg is null");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgIsNotNull() {
            addCriterion("product_score_avg is not null");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgEqualTo(BigDecimal value) {
            addCriterion("product_score_avg =", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgNotEqualTo(BigDecimal value) {
            addCriterion("product_score_avg <>", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgGreaterThan(BigDecimal value) {
            addCriterion("product_score_avg >", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_score_avg >=", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgLessThan(BigDecimal value) {
            addCriterion("product_score_avg <", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_score_avg <=", value, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgIn(List<BigDecimal> values) {
            addCriterion("product_score_avg in", values, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgNotIn(List<BigDecimal> values) {
            addCriterion("product_score_avg not in", values, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_score_avg between", value1, value2, "productScoreAvg");
            return (Criteria) this;
        }

        public Criteria andProductScoreAvgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_score_avg not between", value1, value2, "productScoreAvg");
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