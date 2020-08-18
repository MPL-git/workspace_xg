package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullGiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public FullGiveExample() {
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

        public Criteria andRangIsNull() {
            addCriterion("rang is null");
            return (Criteria) this;
        }

        public Criteria andRangIsNotNull() {
            addCriterion("rang is not null");
            return (Criteria) this;
        }

        public Criteria andRangEqualTo(String value) {
            addCriterion("rang =", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotEqualTo(String value) {
            addCriterion("rang <>", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangGreaterThan(String value) {
            addCriterion("rang >", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangGreaterThanOrEqualTo(String value) {
            addCriterion("rang >=", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLessThan(String value) {
            addCriterion("rang <", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLessThanOrEqualTo(String value) {
            addCriterion("rang <=", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLike(String value) {
            addCriterion("rang like", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotLike(String value) {
            addCriterion("rang not like", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangIn(List<String> values) {
            addCriterion("rang in", values, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotIn(List<String> values) {
            addCriterion("rang not in", values, "rang");
            return (Criteria) this;
        }

        public Criteria andRangBetween(String value1, String value2) {
            addCriterion("rang between", value1, value2, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotBetween(String value1, String value2) {
            addCriterion("rang not between", value1, value2, "rang");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("belong not between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIsNull() {
            addCriterion("activity_area_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIsNotNull() {
            addCriterion("activity_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdEqualTo(Integer value) {
            addCriterion("activity_area_id =", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotEqualTo(Integer value) {
            addCriterion("activity_area_id <>", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdGreaterThan(Integer value) {
            addCriterion("activity_area_id >", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_area_id >=", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdLessThan(Integer value) {
            addCriterion("activity_area_id <", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_area_id <=", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIn(List<Integer> values) {
            addCriterion("activity_area_id in", values, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotIn(List<Integer> values) {
            addCriterion("activity_area_id not in", values, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_area_id between", value1, value2, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_area_id not between", value1, value2, "activityAreaId");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andMinimumIsNull() {
            addCriterion("minimum is null");
            return (Criteria) this;
        }

        public Criteria andMinimumIsNotNull() {
            addCriterion("minimum is not null");
            return (Criteria) this;
        }

        public Criteria andMinimumEqualTo(BigDecimal value) {
            addCriterion("minimum =", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotEqualTo(BigDecimal value) {
            addCriterion("minimum <>", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThan(BigDecimal value) {
            addCriterion("minimum >", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("minimum >=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThan(BigDecimal value) {
            addCriterion("minimum <", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("minimum <=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumIn(List<BigDecimal> values) {
            addCriterion("minimum in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotIn(List<BigDecimal> values) {
            addCriterion("minimum not in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minimum between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minimum not between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andSumFlagIsNull() {
            addCriterion("sum_flag is null");
            return (Criteria) this;
        }

        public Criteria andSumFlagIsNotNull() {
            addCriterion("sum_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSumFlagEqualTo(String value) {
            addCriterion("sum_flag =", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagNotEqualTo(String value) {
            addCriterion("sum_flag <>", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagGreaterThan(String value) {
            addCriterion("sum_flag >", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagGreaterThanOrEqualTo(String value) {
            addCriterion("sum_flag >=", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagLessThan(String value) {
            addCriterion("sum_flag <", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagLessThanOrEqualTo(String value) {
            addCriterion("sum_flag <=", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagLike(String value) {
            addCriterion("sum_flag like", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagNotLike(String value) {
            addCriterion("sum_flag not like", value, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagIn(List<String> values) {
            addCriterion("sum_flag in", values, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagNotIn(List<String> values) {
            addCriterion("sum_flag not in", values, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagBetween(String value1, String value2) {
            addCriterion("sum_flag between", value1, value2, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andSumFlagNotBetween(String value1, String value2) {
            addCriterion("sum_flag not between", value1, value2, "sumFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagIsNull() {
            addCriterion("coupon_flag is null");
            return (Criteria) this;
        }

        public Criteria andCouponFlagIsNotNull() {
            addCriterion("coupon_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCouponFlagEqualTo(String value) {
            addCriterion("coupon_flag =", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagNotEqualTo(String value) {
            addCriterion("coupon_flag <>", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagGreaterThan(String value) {
            addCriterion("coupon_flag >", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_flag >=", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagLessThan(String value) {
            addCriterion("coupon_flag <", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagLessThanOrEqualTo(String value) {
            addCriterion("coupon_flag <=", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagLike(String value) {
            addCriterion("coupon_flag like", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagNotLike(String value) {
            addCriterion("coupon_flag not like", value, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagIn(List<String> values) {
            addCriterion("coupon_flag in", values, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagNotIn(List<String> values) {
            addCriterion("coupon_flag not in", values, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagBetween(String value1, String value2) {
            addCriterion("coupon_flag between", value1, value2, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponFlagNotBetween(String value1, String value2) {
            addCriterion("coupon_flag not between", value1, value2, "couponFlag");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupIsNull() {
            addCriterion("coupon_id_group is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupIsNotNull() {
            addCriterion("coupon_id_group is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupEqualTo(String value) {
            addCriterion("coupon_id_group =", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupNotEqualTo(String value) {
            addCriterion("coupon_id_group <>", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupGreaterThan(String value) {
            addCriterion("coupon_id_group >", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_id_group >=", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupLessThan(String value) {
            addCriterion("coupon_id_group <", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupLessThanOrEqualTo(String value) {
            addCriterion("coupon_id_group <=", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupLike(String value) {
            addCriterion("coupon_id_group like", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupNotLike(String value) {
            addCriterion("coupon_id_group not like", value, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupIn(List<String> values) {
            addCriterion("coupon_id_group in", values, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupNotIn(List<String> values) {
            addCriterion("coupon_id_group not in", values, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupBetween(String value1, String value2) {
            addCriterion("coupon_id_group between", value1, value2, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andCouponIdGroupNotBetween(String value1, String value2) {
            addCriterion("coupon_id_group not between", value1, value2, "couponIdGroup");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagIsNull() {
            addCriterion("integral_flag is null");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagIsNotNull() {
            addCriterion("integral_flag is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagEqualTo(String value) {
            addCriterion("integral_flag =", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagNotEqualTo(String value) {
            addCriterion("integral_flag <>", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagGreaterThan(String value) {
            addCriterion("integral_flag >", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagGreaterThanOrEqualTo(String value) {
            addCriterion("integral_flag >=", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagLessThan(String value) {
            addCriterion("integral_flag <", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagLessThanOrEqualTo(String value) {
            addCriterion("integral_flag <=", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagLike(String value) {
            addCriterion("integral_flag like", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagNotLike(String value) {
            addCriterion("integral_flag not like", value, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagIn(List<String> values) {
            addCriterion("integral_flag in", values, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagNotIn(List<String> values) {
            addCriterion("integral_flag not in", values, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagBetween(String value1, String value2) {
            addCriterion("integral_flag between", value1, value2, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralFlagNotBetween(String value1, String value2) {
            addCriterion("integral_flag not between", value1, value2, "integralFlag");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andProductFlagIsNull() {
            addCriterion("product_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductFlagIsNotNull() {
            addCriterion("product_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductFlagEqualTo(String value) {
            addCriterion("product_flag =", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagNotEqualTo(String value) {
            addCriterion("product_flag <>", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagGreaterThan(String value) {
            addCriterion("product_flag >", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_flag >=", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagLessThan(String value) {
            addCriterion("product_flag <", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagLessThanOrEqualTo(String value) {
            addCriterion("product_flag <=", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagLike(String value) {
            addCriterion("product_flag like", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagNotLike(String value) {
            addCriterion("product_flag not like", value, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagIn(List<String> values) {
            addCriterion("product_flag in", values, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagNotIn(List<String> values) {
            addCriterion("product_flag not in", values, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagBetween(String value1, String value2) {
            addCriterion("product_flag between", value1, value2, "productFlag");
            return (Criteria) this;
        }

        public Criteria andProductFlagNotBetween(String value1, String value2) {
            addCriterion("product_flag not between", value1, value2, "productFlag");
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