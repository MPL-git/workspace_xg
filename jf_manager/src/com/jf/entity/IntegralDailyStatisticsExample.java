package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralDailyStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public IntegralDailyStatisticsExample() {
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

        public Criteria andStatisticDateIsNull() {
            addCriterion("statistic_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticDateIsNotNull() {
            addCriterion("statistic_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticDateEqualTo(String value) {
            addCriterion("statistic_date =", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotEqualTo(String value) {
            addCriterion("statistic_date <>", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateGreaterThan(String value) {
            addCriterion("statistic_date >", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateGreaterThanOrEqualTo(String value) {
            addCriterion("statistic_date >=", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateLessThan(String value) {
            addCriterion("statistic_date <", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateLessThanOrEqualTo(String value) {
            addCriterion("statistic_date <=", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateLike(String value) {
            addCriterion("statistic_date like", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotLike(String value) {
            addCriterion("statistic_date not like", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateIn(List<String> values) {
            addCriterion("statistic_date in", values, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotIn(List<String> values) {
            addCriterion("statistic_date not in", values, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateBetween(String value1, String value2) {
            addCriterion("statistic_date between", value1, value2, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotBetween(String value1, String value2) {
            addCriterion("statistic_date not between", value1, value2, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralIsNull() {
            addCriterion("begin_integral is null");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralIsNotNull() {
            addCriterion("begin_integral is not null");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralEqualTo(Integer value) {
            addCriterion("begin_integral =", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralNotEqualTo(Integer value) {
            addCriterion("begin_integral <>", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralGreaterThan(Integer value) {
            addCriterion("begin_integral >", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_integral >=", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralLessThan(Integer value) {
            addCriterion("begin_integral <", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("begin_integral <=", value, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralIn(List<Integer> values) {
            addCriterion("begin_integral in", values, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralNotIn(List<Integer> values) {
            addCriterion("begin_integral not in", values, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralBetween(Integer value1, Integer value2) {
            addCriterion("begin_integral between", value1, value2, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andBeginIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_integral not between", value1, value2, "beginIntegral");
            return (Criteria) this;
        }

        public Criteria andOrderGiveIsNull() {
            addCriterion("order_give is null");
            return (Criteria) this;
        }

        public Criteria andOrderGiveIsNotNull() {
            addCriterion("order_give is not null");
            return (Criteria) this;
        }

        public Criteria andOrderGiveEqualTo(Integer value) {
            addCriterion("order_give =", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveNotEqualTo(Integer value) {
            addCriterion("order_give <>", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveGreaterThan(Integer value) {
            addCriterion("order_give >", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_give >=", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveLessThan(Integer value) {
            addCriterion("order_give <", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveLessThanOrEqualTo(Integer value) {
            addCriterion("order_give <=", value, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveIn(List<Integer> values) {
            addCriterion("order_give in", values, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveNotIn(List<Integer> values) {
            addCriterion("order_give not in", values, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveBetween(Integer value1, Integer value2) {
            addCriterion("order_give between", value1, value2, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOrderGiveNotBetween(Integer value1, Integer value2) {
            addCriterion("order_give not between", value1, value2, "orderGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveIsNull() {
            addCriterion("other_give is null");
            return (Criteria) this;
        }

        public Criteria andOtherGiveIsNotNull() {
            addCriterion("other_give is not null");
            return (Criteria) this;
        }

        public Criteria andOtherGiveEqualTo(Integer value) {
            addCriterion("other_give =", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveNotEqualTo(Integer value) {
            addCriterion("other_give <>", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveGreaterThan(Integer value) {
            addCriterion("other_give >", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_give >=", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveLessThan(Integer value) {
            addCriterion("other_give <", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveLessThanOrEqualTo(Integer value) {
            addCriterion("other_give <=", value, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveIn(List<Integer> values) {
            addCriterion("other_give in", values, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveNotIn(List<Integer> values) {
            addCriterion("other_give not in", values, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveBetween(Integer value1, Integer value2) {
            addCriterion("other_give between", value1, value2, "otherGive");
            return (Criteria) this;
        }

        public Criteria andOtherGiveNotBetween(Integer value1, Integer value2) {
            addCriterion("other_give not between", value1, value2, "otherGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveIsNull() {
            addCriterion("violate_give is null");
            return (Criteria) this;
        }

        public Criteria andViolateGiveIsNotNull() {
            addCriterion("violate_give is not null");
            return (Criteria) this;
        }

        public Criteria andViolateGiveEqualTo(Integer value) {
            addCriterion("violate_give =", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveNotEqualTo(Integer value) {
            addCriterion("violate_give <>", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveGreaterThan(Integer value) {
            addCriterion("violate_give >", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("violate_give >=", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveLessThan(Integer value) {
            addCriterion("violate_give <", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveLessThanOrEqualTo(Integer value) {
            addCriterion("violate_give <=", value, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveIn(List<Integer> values) {
            addCriterion("violate_give in", values, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveNotIn(List<Integer> values) {
            addCriterion("violate_give not in", values, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveBetween(Integer value1, Integer value2) {
            addCriterion("violate_give between", value1, value2, "violateGive");
            return (Criteria) this;
        }

        public Criteria andViolateGiveNotBetween(Integer value1, Integer value2) {
            addCriterion("violate_give not between", value1, value2, "violateGive");
            return (Criteria) this;
        }

        public Criteria andOrderUseIsNull() {
            addCriterion("order_use is null");
            return (Criteria) this;
        }

        public Criteria andOrderUseIsNotNull() {
            addCriterion("order_use is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUseEqualTo(Integer value) {
            addCriterion("order_use =", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseNotEqualTo(Integer value) {
            addCriterion("order_use <>", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseGreaterThan(Integer value) {
            addCriterion("order_use >", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_use >=", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseLessThan(Integer value) {
            addCriterion("order_use <", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseLessThanOrEqualTo(Integer value) {
            addCriterion("order_use <=", value, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseIn(List<Integer> values) {
            addCriterion("order_use in", values, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseNotIn(List<Integer> values) {
            addCriterion("order_use not in", values, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseBetween(Integer value1, Integer value2) {
            addCriterion("order_use between", value1, value2, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOrderUseNotBetween(Integer value1, Integer value2) {
            addCriterion("order_use not between", value1, value2, "orderUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseIsNull() {
            addCriterion("other_use is null");
            return (Criteria) this;
        }

        public Criteria andOtherUseIsNotNull() {
            addCriterion("other_use is not null");
            return (Criteria) this;
        }

        public Criteria andOtherUseEqualTo(Integer value) {
            addCriterion("other_use =", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseNotEqualTo(Integer value) {
            addCriterion("other_use <>", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseGreaterThan(Integer value) {
            addCriterion("other_use >", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_use >=", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseLessThan(Integer value) {
            addCriterion("other_use <", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseLessThanOrEqualTo(Integer value) {
            addCriterion("other_use <=", value, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseIn(List<Integer> values) {
            addCriterion("other_use in", values, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseNotIn(List<Integer> values) {
            addCriterion("other_use not in", values, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseBetween(Integer value1, Integer value2) {
            addCriterion("other_use between", value1, value2, "otherUse");
            return (Criteria) this;
        }

        public Criteria andOtherUseNotBetween(Integer value1, Integer value2) {
            addCriterion("other_use not between", value1, value2, "otherUse");
            return (Criteria) this;
        }

        public Criteria andIntegralType1IsNull() {
            addCriterion("integral_type_1 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType1IsNotNull() {
            addCriterion("integral_type_1 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType1EqualTo(Integer value) {
            addCriterion("integral_type_1 =", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1NotEqualTo(Integer value) {
            addCriterion("integral_type_1 <>", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1GreaterThan(Integer value) {
            addCriterion("integral_type_1 >", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_1 >=", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1LessThan(Integer value) {
            addCriterion("integral_type_1 <", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_1 <=", value, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1In(List<Integer> values) {
            addCriterion("integral_type_1 in", values, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1NotIn(List<Integer> values) {
            addCriterion("integral_type_1 not in", values, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1Between(Integer value1, Integer value2) {
            addCriterion("integral_type_1 between", value1, value2, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType1NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_1 not between", value1, value2, "integralType1");
            return (Criteria) this;
        }

        public Criteria andIntegralType2IsNull() {
            addCriterion("integral_type_2 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType2IsNotNull() {
            addCriterion("integral_type_2 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType2EqualTo(Integer value) {
            addCriterion("integral_type_2 =", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2NotEqualTo(Integer value) {
            addCriterion("integral_type_2 <>", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2GreaterThan(Integer value) {
            addCriterion("integral_type_2 >", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_2 >=", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2LessThan(Integer value) {
            addCriterion("integral_type_2 <", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_2 <=", value, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2In(List<Integer> values) {
            addCriterion("integral_type_2 in", values, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2NotIn(List<Integer> values) {
            addCriterion("integral_type_2 not in", values, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2Between(Integer value1, Integer value2) {
            addCriterion("integral_type_2 between", value1, value2, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType2NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_2 not between", value1, value2, "integralType2");
            return (Criteria) this;
        }

        public Criteria andIntegralType3IsNull() {
            addCriterion("integral_type_3 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType3IsNotNull() {
            addCriterion("integral_type_3 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType3EqualTo(Integer value) {
            addCriterion("integral_type_3 =", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3NotEqualTo(Integer value) {
            addCriterion("integral_type_3 <>", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3GreaterThan(Integer value) {
            addCriterion("integral_type_3 >", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_3 >=", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3LessThan(Integer value) {
            addCriterion("integral_type_3 <", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_3 <=", value, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3In(List<Integer> values) {
            addCriterion("integral_type_3 in", values, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3NotIn(List<Integer> values) {
            addCriterion("integral_type_3 not in", values, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3Between(Integer value1, Integer value2) {
            addCriterion("integral_type_3 between", value1, value2, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType3NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_3 not between", value1, value2, "integralType3");
            return (Criteria) this;
        }

        public Criteria andIntegralType4IsNull() {
            addCriterion("integral_type_4 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType4IsNotNull() {
            addCriterion("integral_type_4 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType4EqualTo(Integer value) {
            addCriterion("integral_type_4 =", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4NotEqualTo(Integer value) {
            addCriterion("integral_type_4 <>", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4GreaterThan(Integer value) {
            addCriterion("integral_type_4 >", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_4 >=", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4LessThan(Integer value) {
            addCriterion("integral_type_4 <", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_4 <=", value, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4In(List<Integer> values) {
            addCriterion("integral_type_4 in", values, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4NotIn(List<Integer> values) {
            addCriterion("integral_type_4 not in", values, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4Between(Integer value1, Integer value2) {
            addCriterion("integral_type_4 between", value1, value2, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType4NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_4 not between", value1, value2, "integralType4");
            return (Criteria) this;
        }

        public Criteria andIntegralType5IsNull() {
            addCriterion("integral_type_5 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType5IsNotNull() {
            addCriterion("integral_type_5 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType5EqualTo(Integer value) {
            addCriterion("integral_type_5 =", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5NotEqualTo(Integer value) {
            addCriterion("integral_type_5 <>", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5GreaterThan(Integer value) {
            addCriterion("integral_type_5 >", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_5 >=", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5LessThan(Integer value) {
            addCriterion("integral_type_5 <", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_5 <=", value, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5In(List<Integer> values) {
            addCriterion("integral_type_5 in", values, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5NotIn(List<Integer> values) {
            addCriterion("integral_type_5 not in", values, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5Between(Integer value1, Integer value2) {
            addCriterion("integral_type_5 between", value1, value2, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType5NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_5 not between", value1, value2, "integralType5");
            return (Criteria) this;
        }

        public Criteria andIntegralType6IsNull() {
            addCriterion("integral_type_6 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType6IsNotNull() {
            addCriterion("integral_type_6 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType6EqualTo(Integer value) {
            addCriterion("integral_type_6 =", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6NotEqualTo(Integer value) {
            addCriterion("integral_type_6 <>", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6GreaterThan(Integer value) {
            addCriterion("integral_type_6 >", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_6 >=", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6LessThan(Integer value) {
            addCriterion("integral_type_6 <", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_6 <=", value, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6In(List<Integer> values) {
            addCriterion("integral_type_6 in", values, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6NotIn(List<Integer> values) {
            addCriterion("integral_type_6 not in", values, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6Between(Integer value1, Integer value2) {
            addCriterion("integral_type_6 between", value1, value2, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType6NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_6 not between", value1, value2, "integralType6");
            return (Criteria) this;
        }

        public Criteria andIntegralType7IsNull() {
            addCriterion("integral_type_7 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType7IsNotNull() {
            addCriterion("integral_type_7 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType7EqualTo(Integer value) {
            addCriterion("integral_type_7 =", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7NotEqualTo(Integer value) {
            addCriterion("integral_type_7 <>", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7GreaterThan(Integer value) {
            addCriterion("integral_type_7 >", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_7 >=", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7LessThan(Integer value) {
            addCriterion("integral_type_7 <", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_7 <=", value, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7In(List<Integer> values) {
            addCriterion("integral_type_7 in", values, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7NotIn(List<Integer> values) {
            addCriterion("integral_type_7 not in", values, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7Between(Integer value1, Integer value2) {
            addCriterion("integral_type_7 between", value1, value2, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType7NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_7 not between", value1, value2, "integralType7");
            return (Criteria) this;
        }

        public Criteria andIntegralType8IsNull() {
            addCriterion("integral_type_8 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType8IsNotNull() {
            addCriterion("integral_type_8 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType8EqualTo(Integer value) {
            addCriterion("integral_type_8 =", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8NotEqualTo(Integer value) {
            addCriterion("integral_type_8 <>", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8GreaterThan(Integer value) {
            addCriterion("integral_type_8 >", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_8 >=", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8LessThan(Integer value) {
            addCriterion("integral_type_8 <", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_8 <=", value, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8In(List<Integer> values) {
            addCriterion("integral_type_8 in", values, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8NotIn(List<Integer> values) {
            addCriterion("integral_type_8 not in", values, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8Between(Integer value1, Integer value2) {
            addCriterion("integral_type_8 between", value1, value2, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType8NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_8 not between", value1, value2, "integralType8");
            return (Criteria) this;
        }

        public Criteria andIntegralType9IsNull() {
            addCriterion("integral_type_9 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType9IsNotNull() {
            addCriterion("integral_type_9 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType9EqualTo(Integer value) {
            addCriterion("integral_type_9 =", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9NotEqualTo(Integer value) {
            addCriterion("integral_type_9 <>", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9GreaterThan(Integer value) {
            addCriterion("integral_type_9 >", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_9 >=", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9LessThan(Integer value) {
            addCriterion("integral_type_9 <", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_9 <=", value, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9In(List<Integer> values) {
            addCriterion("integral_type_9 in", values, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9NotIn(List<Integer> values) {
            addCriterion("integral_type_9 not in", values, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9Between(Integer value1, Integer value2) {
            addCriterion("integral_type_9 between", value1, value2, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType9NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_9 not between", value1, value2, "integralType9");
            return (Criteria) this;
        }

        public Criteria andIntegralType10IsNull() {
            addCriterion("integral_type_10 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType10IsNotNull() {
            addCriterion("integral_type_10 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType10EqualTo(Integer value) {
            addCriterion("integral_type_10 =", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10NotEqualTo(Integer value) {
            addCriterion("integral_type_10 <>", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10GreaterThan(Integer value) {
            addCriterion("integral_type_10 >", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_10 >=", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10LessThan(Integer value) {
            addCriterion("integral_type_10 <", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_10 <=", value, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10In(List<Integer> values) {
            addCriterion("integral_type_10 in", values, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10NotIn(List<Integer> values) {
            addCriterion("integral_type_10 not in", values, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10Between(Integer value1, Integer value2) {
            addCriterion("integral_type_10 between", value1, value2, "integralType10");
            return (Criteria) this;
        }

        public Criteria andIntegralType10NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_10 not between", value1, value2, "integralType10");
            return (Criteria) this;
        }
        public Criteria andIntegralType17IsNull() {
            addCriterion("integral_type_17 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType17IsNotNull() {
            addCriterion("integral_type_17 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType17EqualTo(Integer value) {
            addCriterion("integral_type_17 =", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17NotEqualTo(Integer value) {
            addCriterion("integral_type_17 <>", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType11GreaterThan(Integer value) {
            addCriterion("integral_type_17 >", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType11GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_17 >=", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17LessThan(Integer value) {
            addCriterion("integral_type_17 <", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_17 <=", value, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17In(List<Integer> values) {
            addCriterion("integral_type_17 in", values, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17NotIn(List<Integer> values) {
            addCriterion("integral_type_17 not in", values, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17Between(Integer value1, Integer value2) {
            addCriterion("integral_type_17 between", value1, value2, "integralType17");
            return (Criteria) this;
        }

        public Criteria andIntegralType17NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_17 not between", value1, value2, "integralType17");
            return (Criteria) this;
        }
        public Criteria andIntegralType18IsNull() {
            addCriterion("integral_type_18 is null");
            return (Criteria) this;
        }

        public Criteria andIntegralType18IsNotNull() {
            addCriterion("integral_type_18 is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralType18EqualTo(Integer value) {
            addCriterion("integral_type_18 =", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18NotEqualTo(Integer value) {
            addCriterion("integral_type_18 <>", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18GreaterThan(Integer value) {
            addCriterion("integral_type_18 >", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18GreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type_18 >=", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18LessThan(Integer value) {
            addCriterion("integral_type_18 <", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18LessThanOrEqualTo(Integer value) {
            addCriterion("integral_type_18 <=", value, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18In(List<Integer> values) {
            addCriterion("integral_type_18 in", values, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18NotIn(List<Integer> values) {
            addCriterion("integral_type_18 not in", values, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18Between(Integer value1, Integer value2) {
            addCriterion("integral_type_18 between", value1, value2, "integralType18");
            return (Criteria) this;
        }

        public Criteria andIntegralType18NotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type_18 not between", value1, value2, "integralType18");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralIsNull() {
            addCriterion("invalid_integral is null");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralIsNotNull() {
            addCriterion("invalid_integral is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralEqualTo(Integer value) {
            addCriterion("invalid_integral =", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralNotEqualTo(Integer value) {
            addCriterion("invalid_integral <>", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralGreaterThan(Integer value) {
            addCriterion("invalid_integral >", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("invalid_integral >=", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralLessThan(Integer value) {
            addCriterion("invalid_integral <", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("invalid_integral <=", value, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralIn(List<Integer> values) {
            addCriterion("invalid_integral in", values, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralNotIn(List<Integer> values) {
            addCriterion("invalid_integral not in", values, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralBetween(Integer value1, Integer value2) {
            addCriterion("invalid_integral between", value1, value2, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andInvalidIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("invalid_integral not between", value1, value2, "invalidIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralIsNull() {
            addCriterion("end_integral is null");
            return (Criteria) this;
        }

        public Criteria andEndIntegralIsNotNull() {
            addCriterion("end_integral is not null");
            return (Criteria) this;
        }

        public Criteria andEndIntegralEqualTo(Integer value) {
            addCriterion("end_integral =", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralNotEqualTo(Integer value) {
            addCriterion("end_integral <>", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralGreaterThan(Integer value) {
            addCriterion("end_integral >", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_integral >=", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralLessThan(Integer value) {
            addCriterion("end_integral <", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("end_integral <=", value, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralIn(List<Integer> values) {
            addCriterion("end_integral in", values, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralNotIn(List<Integer> values) {
            addCriterion("end_integral not in", values, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralBetween(Integer value1, Integer value2) {
            addCriterion("end_integral between", value1, value2, "endIntegral");
            return (Criteria) this;
        }

        public Criteria andEndIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("end_integral not between", value1, value2, "endIntegral");
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