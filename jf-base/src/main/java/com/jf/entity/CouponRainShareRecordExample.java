package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponRainShareRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<CouponRainShareRecordExample.Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CouponRainShareRecordExample() {
        oredCriteria = new ArrayList<CouponRainShareRecordExample.Criteria>();
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

    public List<CouponRainShareRecordExample.Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(CouponRainShareRecordExample.Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public CouponRainShareRecordExample.Criteria or() {
        CouponRainShareRecordExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public CouponRainShareRecordExample.Criteria createCriteria() {
        CouponRainShareRecordExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected CouponRainShareRecordExample.Criteria createCriteriaInternal() {
        CouponRainShareRecordExample.Criteria criteria = new CouponRainShareRecordExample.Criteria();
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
        protected List<CouponRainShareRecordExample.Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<CouponRainShareRecordExample.Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<CouponRainShareRecordExample.Criterion> getAllCriteria() {
            return criteria;
        }

        public List<CouponRainShareRecordExample.Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new CouponRainShareRecordExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new CouponRainShareRecordExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new CouponRainShareRecordExample.Criterion(condition, value1, value2));
        }

        public CouponRainShareRecordExample.Criteria andIdIsNull() {
            addCriterion("id is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdIsNull() {
            addCriterion("coupon_rain_id is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdIsNotNull() {
            addCriterion("coupon_rain_id is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdEqualTo(Integer value) {
            addCriterion("coupon_rain_id =", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdNotEqualTo(Integer value) {
            addCriterion("coupon_rain_id <>", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdGreaterThan(Integer value) {
            addCriterion("coupon_rain_id >", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_rain_id >=", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdLessThan(Integer value) {
            addCriterion("coupon_rain_id <", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_rain_id <=", value, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdIn(List<Integer> values) {
            addCriterion("coupon_rain_id in", values, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdNotIn(List<Integer> values) {
            addCriterion("coupon_rain_id not in", values, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_rain_id between", value1, value2, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCouponRainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_rain_id not between", value1, value2, "couponRainId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByEqualTo(Integer value) {
            addCriterion("update_by =", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByNotEqualTo(Integer value) {
            addCriterion("update_by <>", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByGreaterThan(Integer value) {
            addCriterion("update_by >", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_by >=", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByLessThan(Integer value) {
            addCriterion("update_by <", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByLessThanOrEqualTo(Integer value) {
            addCriterion("update_by <=", value, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByIn(List<Integer> values) {
            addCriterion("update_by in", values, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByNotIn(List<Integer> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByBetween(Integer value1, Integer value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateByNotBetween(Integer value1, Integer value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }

        public CouponRainShareRecordExample.Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (CouponRainShareRecordExample.Criteria) this;
        }
    }

    public static class Criteria extends CouponRainShareRecordExample.GeneratedCriteria {

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
