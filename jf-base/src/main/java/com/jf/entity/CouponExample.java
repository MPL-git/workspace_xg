package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CouponExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andCouponTypeIsNull() {
            addCriterion("coupon_type is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("coupon_type is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(String value) {
            addCriterion("coupon_type =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(String value) {
            addCriterion("coupon_type <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(String value) {
            addCriterion("coupon_type >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_type >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(String value) {
            addCriterion("coupon_type <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(String value) {
            addCriterion("coupon_type <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLike(String value) {
            addCriterion("coupon_type like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotLike(String value) {
            addCriterion("coupon_type not like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<String> values) {
            addCriterion("coupon_type in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<String> values) {
            addCriterion("coupon_type not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(String value1, String value2) {
            addCriterion("coupon_type between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(String value1, String value2) {
            addCriterion("coupon_type not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andTypeIdsIsNull() {
            addCriterion("type_ids is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdsIsNotNull() {
            addCriterion("type_ids is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdsEqualTo(String value) {
            addCriterion("type_ids =", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsNotEqualTo(String value) {
            addCriterion("type_ids <>", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsGreaterThan(String value) {
            addCriterion("type_ids >", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsGreaterThanOrEqualTo(String value) {
            addCriterion("type_ids >=", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsLessThan(String value) {
            addCriterion("type_ids <", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsLessThanOrEqualTo(String value) {
            addCriterion("type_ids <=", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsLike(String value) {
            addCriterion("type_ids like", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsNotLike(String value) {
            addCriterion("type_ids not like", value, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsIn(List<String> values) {
            addCriterion("type_ids in", values, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsNotIn(List<String> values) {
            addCriterion("type_ids not in", values, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsBetween(String value1, String value2) {
            addCriterion("type_ids between", value1, value2, "typeIds");
            return (Criteria) this;
        }

        public Criteria andTypeIdsNotBetween(String value1, String value2) {
            addCriterion("type_ids not between", value1, value2, "typeIds");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeIsNull() {
            addCriterion("can_superpose is null");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeIsNotNull() {
            addCriterion("can_superpose is not null");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeEqualTo(String value) {
            addCriterion("can_superpose =", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeNotEqualTo(String value) {
            addCriterion("can_superpose <>", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeGreaterThan(String value) {
            addCriterion("can_superpose >", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeGreaterThanOrEqualTo(String value) {
            addCriterion("can_superpose >=", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeLessThan(String value) {
            addCriterion("can_superpose <", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeLessThanOrEqualTo(String value) {
            addCriterion("can_superpose <=", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeLike(String value) {
            addCriterion("can_superpose like", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeNotLike(String value) {
            addCriterion("can_superpose not like", value, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeIn(List<String> values) {
            addCriterion("can_superpose in", values, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeNotIn(List<String> values) {
            addCriterion("can_superpose not in", values, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeBetween(String value1, String value2) {
            addCriterion("can_superpose between", value1, value2, "canSuperpose");
            return (Criteria) this;
        }

        public Criteria andCanSuperposeNotBetween(String value1, String value2) {
            addCriterion("can_superpose not between", value1, value2, "canSuperpose");
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

        public Criteria andPreferentialTypeIsNull() {
            addCriterion("preferential_type is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIsNotNull() {
            addCriterion("preferential_type is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeEqualTo(String value) {
            addCriterion("preferential_type =", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotEqualTo(String value) {
            addCriterion("preferential_type <>", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThan(String value) {
            addCriterion("preferential_type >", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThanOrEqualTo(String value) {
            addCriterion("preferential_type >=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThan(String value) {
            addCriterion("preferential_type <", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThanOrEqualTo(String value) {
            addCriterion("preferential_type <=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLike(String value) {
            addCriterion("preferential_type like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotLike(String value) {
            addCriterion("preferential_type not like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIn(List<String> values) {
            addCriterion("preferential_type in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotIn(List<String> values) {
            addCriterion("preferential_type not in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeBetween(String value1, String value2) {
            addCriterion("preferential_type between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotBetween(String value1, String value2) {
            addCriterion("preferential_type not between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andMaxDiscountMoneyIsNull() {
            addCriterion("max_discount_money is null");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyIsNotNull() {
            addCriterion("max_discount_money is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyEqualTo(BigDecimal value) {
            addCriterion("max_discount_money =", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyNotEqualTo(BigDecimal value) {
            addCriterion("max_discount_money <>", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyGreaterThan(BigDecimal value) {
            addCriterion("max_discount_money >", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_discount_money >=", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyLessThan(BigDecimal value) {
            addCriterion("max_discount_money <", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_discount_money <=", value, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyIn(List<BigDecimal> values) {
            addCriterion("max_discount_money in", values, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyNotIn(List<BigDecimal> values) {
            addCriterion("max_discount_money not in", values, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_discount_money between", value1, value2, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andMaxDiscountMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_discount_money not between", value1, value2, "maxDiscountMoney");
            return (Criteria) this;
        }

        public Criteria andConditionTypeIsNull() {
            addCriterion("condition_type is null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeIsNotNull() {
            addCriterion("condition_type is not null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeEqualTo(String value) {
            addCriterion("condition_type =", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNotEqualTo(String value) {
            addCriterion("condition_type <>", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeGreaterThan(String value) {
            addCriterion("condition_type >", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("condition_type >=", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeLessThan(String value) {
            addCriterion("condition_type <", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeLessThanOrEqualTo(String value) {
            addCriterion("condition_type <=", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeLike(String value) {
            addCriterion("condition_type like", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNotLike(String value) {
            addCriterion("condition_type not like", value, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeIn(List<String> values) {
            addCriterion("condition_type in", values, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNotIn(List<String> values) {
            addCriterion("condition_type not in", values, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeBetween(String value1, String value2) {
            addCriterion("condition_type between", value1, value2, "conditionType");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNotBetween(String value1, String value2) {
            addCriterion("condition_type not between", value1, value2, "conditionType");
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

        public Criteria andMinicountIsNull() {
            addCriterion("minicount is null");
            return (Criteria) this;
        }

        public Criteria andMinicountIsNotNull() {
            addCriterion("minicount is not null");
            return (Criteria) this;
        }

        public Criteria andMinicountEqualTo(Integer value) {
            addCriterion("minicount =", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountNotEqualTo(Integer value) {
            addCriterion("minicount <>", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountGreaterThan(Integer value) {
            addCriterion("minicount >", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountGreaterThanOrEqualTo(Integer value) {
            addCriterion("minicount >=", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountLessThan(Integer value) {
            addCriterion("minicount <", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountLessThanOrEqualTo(Integer value) {
            addCriterion("minicount <=", value, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountIn(List<Integer> values) {
            addCriterion("minicount in", values, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountNotIn(List<Integer> values) {
            addCriterion("minicount not in", values, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountBetween(Integer value1, Integer value2) {
            addCriterion("minicount between", value1, value2, "minicount");
            return (Criteria) this;
        }

        public Criteria andMinicountNotBetween(Integer value1, Integer value2) {
            addCriterion("minicount not between", value1, value2, "minicount");
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

        public Criteria andRecTypeIsNull() {
            addCriterion("rec_type is null");
            return (Criteria) this;
        }

        public Criteria andRecTypeIsNotNull() {
            addCriterion("rec_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecTypeEqualTo(String value) {
            addCriterion("rec_type =", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeNotEqualTo(String value) {
            addCriterion("rec_type <>", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeGreaterThan(String value) {
            addCriterion("rec_type >", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rec_type >=", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeLessThan(String value) {
            addCriterion("rec_type <", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeLessThanOrEqualTo(String value) {
            addCriterion("rec_type <=", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeLike(String value) {
            addCriterion("rec_type like", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeNotLike(String value) {
            addCriterion("rec_type not like", value, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeIn(List<String> values) {
            addCriterion("rec_type in", values, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeNotIn(List<String> values) {
            addCriterion("rec_type not in", values, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeBetween(String value1, String value2) {
            addCriterion("rec_type between", value1, value2, "recType");
            return (Criteria) this;
        }

        public Criteria andRecTypeNotBetween(String value1, String value2) {
            addCriterion("rec_type not between", value1, value2, "recType");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecIsNull() {
            addCriterion("care_shop_can_rec is null");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecIsNotNull() {
            addCriterion("care_shop_can_rec is not null");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecEqualTo(String value) {
            addCriterion("care_shop_can_rec =", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecNotEqualTo(String value) {
            addCriterion("care_shop_can_rec <>", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecGreaterThan(String value) {
            addCriterion("care_shop_can_rec >", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecGreaterThanOrEqualTo(String value) {
            addCriterion("care_shop_can_rec >=", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecLessThan(String value) {
            addCriterion("care_shop_can_rec <", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecLessThanOrEqualTo(String value) {
            addCriterion("care_shop_can_rec <=", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecLike(String value) {
            addCriterion("care_shop_can_rec like", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecNotLike(String value) {
            addCriterion("care_shop_can_rec not like", value, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecIn(List<String> values) {
            addCriterion("care_shop_can_rec in", values, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecNotIn(List<String> values) {
            addCriterion("care_shop_can_rec not in", values, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecBetween(String value1, String value2) {
            addCriterion("care_shop_can_rec between", value1, value2, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andCareShopCanRecNotBetween(String value1, String value2) {
            addCriterion("care_shop_can_rec not between", value1, value2, "careShopCanRec");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralIsNull() {
            addCriterion("need_integral is null");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralIsNotNull() {
            addCriterion("need_integral is not null");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralEqualTo(Integer value) {
            addCriterion("need_integral =", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralNotEqualTo(Integer value) {
            addCriterion("need_integral <>", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralGreaterThan(Integer value) {
            addCriterion("need_integral >", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_integral >=", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralLessThan(Integer value) {
            addCriterion("need_integral <", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("need_integral <=", value, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralIn(List<Integer> values) {
            addCriterion("need_integral in", values, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralNotIn(List<Integer> values) {
            addCriterion("need_integral not in", values, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralBetween(Integer value1, Integer value2) {
            addCriterion("need_integral between", value1, value2, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andNeedIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("need_integral not between", value1, value2, "needIntegral");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIsNull() {
            addCriterion("rec_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIsNotNull() {
            addCriterion("rec_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateEqualTo(Date value) {
            addCriterion("rec_begin_date =", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotEqualTo(Date value) {
            addCriterion("rec_begin_date <>", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateGreaterThan(Date value) {
            addCriterion("rec_begin_date >", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_begin_date >=", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateLessThan(Date value) {
            addCriterion("rec_begin_date <", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("rec_begin_date <=", value, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateIn(List<Date> values) {
            addCriterion("rec_begin_date in", values, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotIn(List<Date> values) {
            addCriterion("rec_begin_date not in", values, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateBetween(Date value1, Date value2) {
            addCriterion("rec_begin_date between", value1, value2, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("rec_begin_date not between", value1, value2, "recBeginDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIsNull() {
            addCriterion("rec_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIsNotNull() {
            addCriterion("rec_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecEndDateEqualTo(Date value) {
            addCriterion("rec_end_date =", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotEqualTo(Date value) {
            addCriterion("rec_end_date <>", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateGreaterThan(Date value) {
            addCriterion("rec_end_date >", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("rec_end_date >=", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateLessThan(Date value) {
            addCriterion("rec_end_date <", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateLessThanOrEqualTo(Date value) {
            addCriterion("rec_end_date <=", value, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateIn(List<Date> values) {
            addCriterion("rec_end_date in", values, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotIn(List<Date> values) {
            addCriterion("rec_end_date not in", values, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateBetween(Date value1, Date value2) {
            addCriterion("rec_end_date between", value1, value2, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andRecEndDateNotBetween(Date value1, Date value2) {
            addCriterion("rec_end_date not between", value1, value2, "recEndDate");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIsNull() {
            addCriterion("min_member_group is null");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIsNotNull() {
            addCriterion("min_member_group is not null");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupEqualTo(Integer value) {
            addCriterion("min_member_group =", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotEqualTo(Integer value) {
            addCriterion("min_member_group <>", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupGreaterThan(Integer value) {
            addCriterion("min_member_group >", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_member_group >=", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupLessThan(Integer value) {
            addCriterion("min_member_group <", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupLessThanOrEqualTo(Integer value) {
            addCriterion("min_member_group <=", value, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupIn(List<Integer> values) {
            addCriterion("min_member_group in", values, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotIn(List<Integer> values) {
            addCriterion("min_member_group not in", values, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupBetween(Integer value1, Integer value2) {
            addCriterion("min_member_group between", value1, value2, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andMinMemberGroupNotBetween(Integer value1, Integer value2) {
            addCriterion("min_member_group not between", value1, value2, "minMemberGroup");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNull() {
            addCriterion("expiry_type is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNotNull() {
            addCriterion("expiry_type is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeEqualTo(String value) {
            addCriterion("expiry_type =", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotEqualTo(String value) {
            addCriterion("expiry_type <>", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThan(String value) {
            addCriterion("expiry_type >", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("expiry_type >=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThan(String value) {
            addCriterion("expiry_type <", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThanOrEqualTo(String value) {
            addCriterion("expiry_type <=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLike(String value) {
            addCriterion("expiry_type like", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotLike(String value) {
            addCriterion("expiry_type not like", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIn(List<String> values) {
            addCriterion("expiry_type in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotIn(List<String> values) {
            addCriterion("expiry_type not in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeBetween(String value1, String value2) {
            addCriterion("expiry_type between", value1, value2, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotBetween(String value1, String value2) {
            addCriterion("expiry_type not between", value1, value2, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateIsNull() {
            addCriterion("expiry_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateIsNotNull() {
            addCriterion("expiry_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateEqualTo(Date value) {
            addCriterion("expiry_begin_date =", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateNotEqualTo(Date value) {
            addCriterion("expiry_begin_date <>", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateGreaterThan(Date value) {
            addCriterion("expiry_begin_date >", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("expiry_begin_date >=", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateLessThan(Date value) {
            addCriterion("expiry_begin_date <", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("expiry_begin_date <=", value, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateIn(List<Date> values) {
            addCriterion("expiry_begin_date in", values, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateNotIn(List<Date> values) {
            addCriterion("expiry_begin_date not in", values, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateBetween(Date value1, Date value2) {
            addCriterion("expiry_begin_date between", value1, value2, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("expiry_begin_date not between", value1, value2, "expiryBeginDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateIsNull() {
            addCriterion("expiry_end_date is null");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateIsNotNull() {
            addCriterion("expiry_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateEqualTo(Date value) {
            addCriterion("expiry_end_date =", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateNotEqualTo(Date value) {
            addCriterion("expiry_end_date <>", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateGreaterThan(Date value) {
            addCriterion("expiry_end_date >", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("expiry_end_date >=", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateLessThan(Date value) {
            addCriterion("expiry_end_date <", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateLessThanOrEqualTo(Date value) {
            addCriterion("expiry_end_date <=", value, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateIn(List<Date> values) {
            addCriterion("expiry_end_date in", values, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateNotIn(List<Date> values) {
            addCriterion("expiry_end_date not in", values, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateBetween(Date value1, Date value2) {
            addCriterion("expiry_end_date between", value1, value2, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryEndDateNotBetween(Date value1, Date value2) {
            addCriterion("expiry_end_date not between", value1, value2, "expiryEndDate");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysIsNull() {
            addCriterion("expiry_days is null");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysIsNotNull() {
            addCriterion("expiry_days is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysEqualTo(Integer value) {
            addCriterion("expiry_days =", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysNotEqualTo(Integer value) {
            addCriterion("expiry_days <>", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysGreaterThan(Integer value) {
            addCriterion("expiry_days >", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("expiry_days >=", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysLessThan(Integer value) {
            addCriterion("expiry_days <", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysLessThanOrEqualTo(Integer value) {
            addCriterion("expiry_days <=", value, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysIn(List<Integer> values) {
            addCriterion("expiry_days in", values, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysNotIn(List<Integer> values) {
            addCriterion("expiry_days not in", values, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysBetween(Integer value1, Integer value2) {
            addCriterion("expiry_days between", value1, value2, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andExpiryDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("expiry_days not between", value1, value2, "expiryDays");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityIsNull() {
            addCriterion("grant_quantity is null");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityIsNotNull() {
            addCriterion("grant_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityEqualTo(Integer value) {
            addCriterion("grant_quantity =", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityNotEqualTo(Integer value) {
            addCriterion("grant_quantity <>", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityGreaterThan(Integer value) {
            addCriterion("grant_quantity >", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("grant_quantity >=", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityLessThan(Integer value) {
            addCriterion("grant_quantity <", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("grant_quantity <=", value, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityIn(List<Integer> values) {
            addCriterion("grant_quantity in", values, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityNotIn(List<Integer> values) {
            addCriterion("grant_quantity not in", values, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityBetween(Integer value1, Integer value2) {
            addCriterion("grant_quantity between", value1, value2, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andGrantQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("grant_quantity not between", value1, value2, "grantQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityIsNull() {
            addCriterion("rec_quantity is null");
            return (Criteria) this;
        }

        public Criteria andRecQuantityIsNotNull() {
            addCriterion("rec_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andRecQuantityEqualTo(Integer value) {
            addCriterion("rec_quantity =", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityNotEqualTo(Integer value) {
            addCriterion("rec_quantity <>", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityGreaterThan(Integer value) {
            addCriterion("rec_quantity >", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("rec_quantity >=", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityLessThan(Integer value) {
            addCriterion("rec_quantity <", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("rec_quantity <=", value, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityIn(List<Integer> values) {
            addCriterion("rec_quantity in", values, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityNotIn(List<Integer> values) {
            addCriterion("rec_quantity not in", values, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityBetween(Integer value1, Integer value2) {
            addCriterion("rec_quantity between", value1, value2, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("rec_quantity not between", value1, value2, "recQuantity");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeIsNull() {
            addCriterion("rec_limit_type is null");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeIsNotNull() {
            addCriterion("rec_limit_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeEqualTo(String value) {
            addCriterion("rec_limit_type =", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeNotEqualTo(String value) {
            addCriterion("rec_limit_type <>", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeGreaterThan(String value) {
            addCriterion("rec_limit_type >", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rec_limit_type >=", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeLessThan(String value) {
            addCriterion("rec_limit_type <", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeLessThanOrEqualTo(String value) {
            addCriterion("rec_limit_type <=", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeLike(String value) {
            addCriterion("rec_limit_type like", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeNotLike(String value) {
            addCriterion("rec_limit_type not like", value, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeIn(List<String> values) {
            addCriterion("rec_limit_type in", values, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeNotIn(List<String> values) {
            addCriterion("rec_limit_type not in", values, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeBetween(String value1, String value2) {
            addCriterion("rec_limit_type between", value1, value2, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecLimitTypeNotBetween(String value1, String value2) {
            addCriterion("rec_limit_type not between", value1, value2, "recLimitType");
            return (Criteria) this;
        }

        public Criteria andRecEachIsNull() {
            addCriterion("rec_each is null");
            return (Criteria) this;
        }

        public Criteria andRecEachIsNotNull() {
            addCriterion("rec_each is not null");
            return (Criteria) this;
        }

        public Criteria andRecEachEqualTo(Integer value) {
            addCriterion("rec_each =", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachNotEqualTo(Integer value) {
            addCriterion("rec_each <>", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachGreaterThan(Integer value) {
            addCriterion("rec_each >", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachGreaterThanOrEqualTo(Integer value) {
            addCriterion("rec_each >=", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachLessThan(Integer value) {
            addCriterion("rec_each <", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachLessThanOrEqualTo(Integer value) {
            addCriterion("rec_each <=", value, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachIn(List<Integer> values) {
            addCriterion("rec_each in", values, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachNotIn(List<Integer> values) {
            addCriterion("rec_each not in", values, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachBetween(Integer value1, Integer value2) {
            addCriterion("rec_each between", value1, value2, "recEach");
            return (Criteria) this;
        }

        public Criteria andRecEachNotBetween(Integer value1, Integer value2) {
            addCriterion("rec_each not between", value1, value2, "recEach");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNull() {
            addCriterion("link_type is null");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNotNull() {
            addCriterion("link_type is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTypeEqualTo(String value) {
            addCriterion("link_type =", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotEqualTo(String value) {
            addCriterion("link_type <>", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThan(String value) {
            addCriterion("link_type >", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("link_type >=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThan(String value) {
            addCriterion("link_type <", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThanOrEqualTo(String value) {
            addCriterion("link_type <=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLike(String value) {
            addCriterion("link_type like", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotLike(String value) {
            addCriterion("link_type not like", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIn(List<String> values) {
            addCriterion("link_type in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotIn(List<String> values) {
            addCriterion("link_type not in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeBetween(String value1, String value2) {
            addCriterion("link_type between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotBetween(String value1, String value2) {
            addCriterion("link_type not between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNull() {
            addCriterion("link_value is null");
            return (Criteria) this;
        }

        public Criteria andLinkValueIsNotNull() {
            addCriterion("link_value is not null");
            return (Criteria) this;
        }

        public Criteria andLinkValueEqualTo(String value) {
            addCriterion("link_value =", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotEqualTo(String value) {
            addCriterion("link_value <>", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThan(String value) {
            addCriterion("link_value >", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueGreaterThanOrEqualTo(String value) {
            addCriterion("link_value >=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThan(String value) {
            addCriterion("link_value <", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLessThanOrEqualTo(String value) {
            addCriterion("link_value <=", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueLike(String value) {
            addCriterion("link_value like", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotLike(String value) {
            addCriterion("link_value not like", value, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueIn(List<String> values) {
            addCriterion("link_value in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotIn(List<String> values) {
            addCriterion("link_value not in", values, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueBetween(String value1, String value2) {
            addCriterion("link_value between", value1, value2, "linkValue");
            return (Criteria) this;
        }

        public Criteria andLinkValueNotBetween(String value1, String value2) {
            addCriterion("link_value not between", value1, value2, "linkValue");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixIsNull() {
            addCriterion("definition_prefix is null");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixIsNotNull() {
            addCriterion("definition_prefix is not null");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixEqualTo(String value) {
            addCriterion("definition_prefix =", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixNotEqualTo(String value) {
            addCriterion("definition_prefix <>", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixGreaterThan(String value) {
            addCriterion("definition_prefix >", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("definition_prefix >=", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixLessThan(String value) {
            addCriterion("definition_prefix <", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixLessThanOrEqualTo(String value) {
            addCriterion("definition_prefix <=", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixLike(String value) {
            addCriterion("definition_prefix like", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixNotLike(String value) {
            addCriterion("definition_prefix not like", value, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixIn(List<String> values) {
            addCriterion("definition_prefix in", values, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixNotIn(List<String> values) {
            addCriterion("definition_prefix not in", values, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixBetween(String value1, String value2) {
            addCriterion("definition_prefix between", value1, value2, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andDefinitionPrefixNotBetween(String value1, String value2) {
            addCriterion("definition_prefix not between", value1, value2, "definitionPrefix");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainIsNull() {
            addCriterion("is_support_coupon_rain is null");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainIsNotNull() {
            addCriterion("is_support_coupon_rain is not null");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainEqualTo(String value) {
            addCriterion("is_support_coupon_rain =", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainNotEqualTo(String value) {
            addCriterion("is_support_coupon_rain <>", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainGreaterThan(String value) {
            addCriterion("is_support_coupon_rain >", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainGreaterThanOrEqualTo(String value) {
            addCriterion("is_support_coupon_rain >=", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainLessThan(String value) {
            addCriterion("is_support_coupon_rain <", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainLessThanOrEqualTo(String value) {
            addCriterion("is_support_coupon_rain <=", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainLike(String value) {
            addCriterion("is_support_coupon_rain like", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainNotLike(String value) {
            addCriterion("is_support_coupon_rain not like", value, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainIn(List<String> values) {
            addCriterion("is_support_coupon_rain in", values, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainNotIn(List<String> values) {
            addCriterion("is_support_coupon_rain not in", values, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainBetween(String value1, String value2) {
            addCriterion("is_support_coupon_rain between", value1, value2, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andIsSupportCouponRainNotBetween(String value1, String value2) {
            addCriterion("is_support_coupon_rain not between", value1, value2, "isSupportCouponRain");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreIsNull() {
            addCriterion("limit_coupon_rain_score is null");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreIsNotNull() {
            addCriterion("limit_coupon_rain_score is not null");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreEqualTo(Integer value) {
            addCriterion("limit_coupon_rain_score =", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreNotEqualTo(Integer value) {
            addCriterion("limit_coupon_rain_score <>", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreGreaterThan(Integer value) {
            addCriterion("limit_coupon_rain_score >", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_coupon_rain_score >=", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreLessThan(Integer value) {
            addCriterion("limit_coupon_rain_score <", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreLessThanOrEqualTo(Integer value) {
            addCriterion("limit_coupon_rain_score <=", value, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreIn(List<Integer> values) {
            addCriterion("limit_coupon_rain_score in", values, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreNotIn(List<Integer> values) {
            addCriterion("limit_coupon_rain_score not in", values, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreBetween(Integer value1, Integer value2) {
            addCriterion("limit_coupon_rain_score between", value1, value2, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andLimitCouponRainScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_coupon_rain_score not between", value1, value2, "limitCouponRainScore");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNull() {
            addCriterion("activity_type is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNotNull() {
            addCriterion("activity_type is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeEqualTo(String value) {
            addCriterion("activity_type =", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotEqualTo(String value) {
            addCriterion("activity_type <>", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThan(String value) {
            addCriterion("activity_type >", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThanOrEqualTo(String value) {
            addCriterion("activity_type >=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThan(String value) {
            addCriterion("activity_type <", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThanOrEqualTo(String value) {
            addCriterion("activity_type <=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLike(String value) {
            addCriterion("activity_type like", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotLike(String value) {
            addCriterion("activity_type not like", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIn(List<String> values) {
            addCriterion("activity_type in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotIn(List<String> values) {
            addCriterion("activity_type not in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeBetween(String value1, String value2) {
            addCriterion("activity_type between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotBetween(String value1, String value2) {
            addCriterion("activity_type not between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableIsNull() {
            addCriterion("is_integral_turntable is null");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableIsNotNull() {
            addCriterion("is_integral_turntable is not null");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableEqualTo(String value) {
            addCriterion("is_integral_turntable =", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableNotEqualTo(String value) {
            addCriterion("is_integral_turntable <>", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableGreaterThan(String value) {
            addCriterion("is_integral_turntable >", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableGreaterThanOrEqualTo(String value) {
            addCriterion("is_integral_turntable >=", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableLessThan(String value) {
            addCriterion("is_integral_turntable <", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableLessThanOrEqualTo(String value) {
            addCriterion("is_integral_turntable <=", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableLike(String value) {
            addCriterion("is_integral_turntable like", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableNotLike(String value) {
            addCriterion("is_integral_turntable not like", value, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableIn(List<String> values) {
            addCriterion("is_integral_turntable in", values, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableNotIn(List<String> values) {
            addCriterion("is_integral_turntable not in", values, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableBetween(String value1, String value2) {
            addCriterion("is_integral_turntable between", value1, value2, "isIntegralTurntable");
            return (Criteria) this;
        }

        public Criteria andIsIntegralTurntableNotBetween(String value1, String value2) {
            addCriterion("is_integral_turntable not between", value1, value2, "isIntegralTurntable");
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