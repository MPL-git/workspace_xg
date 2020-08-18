package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityExample() {
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

        public Criteria andProductTypeIdIsNull() {
            addCriterion("product_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNotNull() {
            addCriterion("product_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdEqualTo(Integer value) {
            addCriterion("product_type_id =", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotEqualTo(Integer value) {
            addCriterion("product_type_id <>", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThan(Integer value) {
            addCriterion("product_type_id >", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type_id >=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThan(Integer value) {
            addCriterion("product_type_id <", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type_id <=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIn(List<Integer> values) {
            addCriterion("product_type_id in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotIn(List<Integer> values) {
            addCriterion("product_type_id not in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id not between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdIsNull() {
            addCriterion("product_type_second_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdIsNotNull() {
            addCriterion("product_type_second_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdEqualTo(Integer value) {
            addCriterion("product_type_second_id =", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdNotEqualTo(Integer value) {
            addCriterion("product_type_second_id <>", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdGreaterThan(Integer value) {
            addCriterion("product_type_second_id >", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type_second_id >=", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdLessThan(Integer value) {
            addCriterion("product_type_second_id <", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type_second_id <=", value, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdIn(List<Integer> values) {
            addCriterion("product_type_second_id in", values, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdNotIn(List<Integer> values) {
            addCriterion("product_type_second_id not in", values, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdBetween(Integer value1, Integer value2) {
            addCriterion("product_type_second_id between", value1, value2, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andProductTypeSecondIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type_second_id not between", value1, value2, "productTypeSecondId");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeIsNull() {
            addCriterion("brand_limit_type is null");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeIsNotNull() {
            addCriterion("brand_limit_type is not null");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeEqualTo(String value) {
            addCriterion("brand_limit_type =", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeNotEqualTo(String value) {
            addCriterion("brand_limit_type <>", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeGreaterThan(String value) {
            addCriterion("brand_limit_type >", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("brand_limit_type >=", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeLessThan(String value) {
            addCriterion("brand_limit_type <", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeLessThanOrEqualTo(String value) {
            addCriterion("brand_limit_type <=", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeLike(String value) {
            addCriterion("brand_limit_type like", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeNotLike(String value) {
            addCriterion("brand_limit_type not like", value, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeIn(List<String> values) {
            addCriterion("brand_limit_type in", values, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeNotIn(List<String> values) {
            addCriterion("brand_limit_type not in", values, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeBetween(String value1, String value2) {
            addCriterion("brand_limit_type between", value1, value2, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andBrandLimitTypeNotBetween(String value1, String value2) {
            addCriterion("brand_limit_type not between", value1, value2, "brandLimitType");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdIsNull() {
            addCriterion("product_brand_id is null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdIsNotNull() {
            addCriterion("product_brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdEqualTo(Integer value) {
            addCriterion("product_brand_id =", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotEqualTo(Integer value) {
            addCriterion("product_brand_id <>", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdGreaterThan(Integer value) {
            addCriterion("product_brand_id >", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_brand_id >=", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdLessThan(Integer value) {
            addCriterion("product_brand_id <", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_brand_id <=", value, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdIn(List<Integer> values) {
            addCriterion("product_brand_id in", values, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotIn(List<Integer> values) {
            addCriterion("product_brand_id not in", values, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("product_brand_id between", value1, value2, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andProductBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_brand_id not between", value1, value2, "productBrandId");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNull() {
            addCriterion("fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andFeeRateIsNotNull() {
            addCriterion("fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFeeRateEqualTo(BigDecimal value) {
            addCriterion("fee_rate =", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("fee_rate <>", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThan(BigDecimal value) {
            addCriterion("fee_rate >", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_rate >=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThan(BigDecimal value) {
            addCriterion("fee_rate <", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee_rate <=", value, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateIn(List<BigDecimal> values) {
            addCriterion("fee_rate in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("fee_rate not in", values, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_rate between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee_rate not between", value1, value2, "feeRate");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIsNull() {
            addCriterion("expected_start_time is null");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIsNotNull() {
            addCriterion("expected_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeEqualTo(Date value) {
            addCriterion("expected_start_time =", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotEqualTo(Date value) {
            addCriterion("expected_start_time <>", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeGreaterThan(Date value) {
            addCriterion("expected_start_time >", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expected_start_time >=", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeLessThan(Date value) {
            addCriterion("expected_start_time <", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("expected_start_time <=", value, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeIn(List<Date> values) {
            addCriterion("expected_start_time in", values, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotIn(List<Date> values) {
            addCriterion("expected_start_time not in", values, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeBetween(Date value1, Date value2) {
            addCriterion("expected_start_time between", value1, value2, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andExpectedStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("expected_start_time not between", value1, value2, "expectedStartTime");
            return (Criteria) this;
        }

        public Criteria andEntryPicIsNull() {
            addCriterion("entry_pic is null");
            return (Criteria) this;
        }

        public Criteria andEntryPicIsNotNull() {
            addCriterion("entry_pic is not null");
            return (Criteria) this;
        }

        public Criteria andEntryPicEqualTo(String value) {
            addCriterion("entry_pic =", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotEqualTo(String value) {
            addCriterion("entry_pic <>", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicGreaterThan(String value) {
            addCriterion("entry_pic >", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicGreaterThanOrEqualTo(String value) {
            addCriterion("entry_pic >=", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLessThan(String value) {
            addCriterion("entry_pic <", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLessThanOrEqualTo(String value) {
            addCriterion("entry_pic <=", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicLike(String value) {
            addCriterion("entry_pic like", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotLike(String value) {
            addCriterion("entry_pic not like", value, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicIn(List<String> values) {
            addCriterion("entry_pic in", values, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotIn(List<String> values) {
            addCriterion("entry_pic not in", values, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicBetween(String value1, String value2) {
            addCriterion("entry_pic between", value1, value2, "entryPic");
            return (Criteria) this;
        }

        public Criteria andEntryPicNotBetween(String value1, String value2) {
            addCriterion("entry_pic not between", value1, value2, "entryPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicIsNull() {
            addCriterion("brand_team_pic is null");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicIsNotNull() {
            addCriterion("brand_team_pic is not null");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicEqualTo(String value) {
            addCriterion("brand_team_pic =", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicNotEqualTo(String value) {
            addCriterion("brand_team_pic <>", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicGreaterThan(String value) {
            addCriterion("brand_team_pic >", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicGreaterThanOrEqualTo(String value) {
            addCriterion("brand_team_pic >=", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicLessThan(String value) {
            addCriterion("brand_team_pic <", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicLessThanOrEqualTo(String value) {
            addCriterion("brand_team_pic <=", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicLike(String value) {
            addCriterion("brand_team_pic like", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicNotLike(String value) {
            addCriterion("brand_team_pic not like", value, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicIn(List<String> values) {
            addCriterion("brand_team_pic in", values, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicNotIn(List<String> values) {
            addCriterion("brand_team_pic not in", values, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicBetween(String value1, String value2) {
            addCriterion("brand_team_pic between", value1, value2, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andBrandTeamPicNotBetween(String value1, String value2) {
            addCriterion("brand_team_pic not between", value1, value2, "brandTeamPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicIsNull() {
            addCriterion("poster_pic is null");
            return (Criteria) this;
        }

        public Criteria andPosterPicIsNotNull() {
            addCriterion("poster_pic is not null");
            return (Criteria) this;
        }

        public Criteria andPosterPicEqualTo(String value) {
            addCriterion("poster_pic =", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicNotEqualTo(String value) {
            addCriterion("poster_pic <>", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicGreaterThan(String value) {
            addCriterion("poster_pic >", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicGreaterThanOrEqualTo(String value) {
            addCriterion("poster_pic >=", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicLessThan(String value) {
            addCriterion("poster_pic <", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicLessThanOrEqualTo(String value) {
            addCriterion("poster_pic <=", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicLike(String value) {
            addCriterion("poster_pic like", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicNotLike(String value) {
            addCriterion("poster_pic not like", value, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicIn(List<String> values) {
            addCriterion("poster_pic in", values, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicNotIn(List<String> values) {
            addCriterion("poster_pic not in", values, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicBetween(String value1, String value2) {
            addCriterion("poster_pic between", value1, value2, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterPicNotBetween(String value1, String value2) {
            addCriterion("poster_pic not between", value1, value2, "posterPic");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdIsNull() {
            addCriterion("poster_link_id is null");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdIsNotNull() {
            addCriterion("poster_link_id is not null");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdEqualTo(Integer value) {
            addCriterion("poster_link_id =", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdNotEqualTo(Integer value) {
            addCriterion("poster_link_id <>", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdGreaterThan(Integer value) {
            addCriterion("poster_link_id >", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("poster_link_id >=", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdLessThan(Integer value) {
            addCriterion("poster_link_id <", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("poster_link_id <=", value, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdIn(List<Integer> values) {
            addCriterion("poster_link_id in", values, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdNotIn(List<Integer> values) {
            addCriterion("poster_link_id not in", values, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("poster_link_id between", value1, value2, "posterLinkId");
            return (Criteria) this;
        }

        public Criteria andPosterLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("poster_link_id not between", value1, value2, "posterLinkId");
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

        public Criteria andOperateAuditByIsNull() {
            addCriterion("operate_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByIsNotNull() {
            addCriterion("operate_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByEqualTo(Integer value) {
            addCriterion("operate_audit_by =", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotEqualTo(Integer value) {
            addCriterion("operate_audit_by <>", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByGreaterThan(Integer value) {
            addCriterion("operate_audit_by >", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_audit_by >=", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByLessThan(Integer value) {
            addCriterion("operate_audit_by <", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("operate_audit_by <=", value, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByIn(List<Integer> values) {
            addCriterion("operate_audit_by in", values, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotIn(List<Integer> values) {
            addCriterion("operate_audit_by not in", values, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByBetween(Integer value1, Integer value2) {
            addCriterion("operate_audit_by between", value1, value2, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_audit_by not between", value1, value2, "operateAuditBy");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIsNull() {
            addCriterion("operate_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIsNotNull() {
            addCriterion("operate_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusEqualTo(String value) {
            addCriterion("operate_audit_status =", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotEqualTo(String value) {
            addCriterion("operate_audit_status <>", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusGreaterThan(String value) {
            addCriterion("operate_audit_status >", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("operate_audit_status >=", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLessThan(String value) {
            addCriterion("operate_audit_status <", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("operate_audit_status <=", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusLike(String value) {
            addCriterion("operate_audit_status like", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotLike(String value) {
            addCriterion("operate_audit_status not like", value, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusIn(List<String> values) {
            addCriterion("operate_audit_status in", values, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotIn(List<String> values) {
            addCriterion("operate_audit_status not in", values, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusBetween(String value1, String value2) {
            addCriterion("operate_audit_status between", value1, value2, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditStatusNotBetween(String value1, String value2) {
            addCriterion("operate_audit_status not between", value1, value2, "operateAuditStatus");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIsNull() {
            addCriterion("operate_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIsNotNull() {
            addCriterion("operate_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksEqualTo(String value) {
            addCriterion("operate_audit_remarks =", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotEqualTo(String value) {
            addCriterion("operate_audit_remarks <>", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksGreaterThan(String value) {
            addCriterion("operate_audit_remarks >", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("operate_audit_remarks >=", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLessThan(String value) {
            addCriterion("operate_audit_remarks <", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("operate_audit_remarks <=", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksLike(String value) {
            addCriterion("operate_audit_remarks like", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotLike(String value) {
            addCriterion("operate_audit_remarks not like", value, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksIn(List<String> values) {
            addCriterion("operate_audit_remarks in", values, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotIn(List<String> values) {
            addCriterion("operate_audit_remarks not in", values, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksBetween(String value1, String value2) {
            addCriterion("operate_audit_remarks between", value1, value2, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("operate_audit_remarks not between", value1, value2, "operateAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByIsNull() {
            addCriterion("design_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByIsNotNull() {
            addCriterion("design_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByEqualTo(Integer value) {
            addCriterion("design_audit_by =", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByNotEqualTo(Integer value) {
            addCriterion("design_audit_by <>", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByGreaterThan(Integer value) {
            addCriterion("design_audit_by >", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("design_audit_by >=", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByLessThan(Integer value) {
            addCriterion("design_audit_by <", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("design_audit_by <=", value, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByIn(List<Integer> values) {
            addCriterion("design_audit_by in", values, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByNotIn(List<Integer> values) {
            addCriterion("design_audit_by not in", values, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByBetween(Integer value1, Integer value2) {
            addCriterion("design_audit_by between", value1, value2, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("design_audit_by not between", value1, value2, "designAuditBy");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusIsNull() {
            addCriterion("design_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusIsNotNull() {
            addCriterion("design_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusEqualTo(String value) {
            addCriterion("design_audit_status =", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusNotEqualTo(String value) {
            addCriterion("design_audit_status <>", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusGreaterThan(String value) {
            addCriterion("design_audit_status >", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("design_audit_status >=", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusLessThan(String value) {
            addCriterion("design_audit_status <", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("design_audit_status <=", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusLike(String value) {
            addCriterion("design_audit_status like", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusNotLike(String value) {
            addCriterion("design_audit_status not like", value, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusIn(List<String> values) {
            addCriterion("design_audit_status in", values, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusNotIn(List<String> values) {
            addCriterion("design_audit_status not in", values, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusBetween(String value1, String value2) {
            addCriterion("design_audit_status between", value1, value2, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditStatusNotBetween(String value1, String value2) {
            addCriterion("design_audit_status not between", value1, value2, "designAuditStatus");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksIsNull() {
            addCriterion("design_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksIsNotNull() {
            addCriterion("design_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksEqualTo(String value) {
            addCriterion("design_audit_remarks =", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksNotEqualTo(String value) {
            addCriterion("design_audit_remarks <>", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksGreaterThan(String value) {
            addCriterion("design_audit_remarks >", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("design_audit_remarks >=", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksLessThan(String value) {
            addCriterion("design_audit_remarks <", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("design_audit_remarks <=", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksLike(String value) {
            addCriterion("design_audit_remarks like", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksNotLike(String value) {
            addCriterion("design_audit_remarks not like", value, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksIn(List<String> values) {
            addCriterion("design_audit_remarks in", values, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksNotIn(List<String> values) {
            addCriterion("design_audit_remarks not in", values, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksBetween(String value1, String value2) {
            addCriterion("design_audit_remarks between", value1, value2, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andDesignAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("design_audit_remarks not between", value1, value2, "designAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditByIsNull() {
            addCriterion("law_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andLawAuditByIsNotNull() {
            addCriterion("law_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andLawAuditByEqualTo(Integer value) {
            addCriterion("law_audit_by =", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByNotEqualTo(Integer value) {
            addCriterion("law_audit_by <>", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByGreaterThan(Integer value) {
            addCriterion("law_audit_by >", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("law_audit_by >=", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByLessThan(Integer value) {
            addCriterion("law_audit_by <", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("law_audit_by <=", value, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByIn(List<Integer> values) {
            addCriterion("law_audit_by in", values, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByNotIn(List<Integer> values) {
            addCriterion("law_audit_by not in", values, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByBetween(Integer value1, Integer value2) {
            addCriterion("law_audit_by between", value1, value2, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("law_audit_by not between", value1, value2, "lawAuditBy");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusIsNull() {
            addCriterion("law_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusIsNotNull() {
            addCriterion("law_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusEqualTo(String value) {
            addCriterion("law_audit_status =", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusNotEqualTo(String value) {
            addCriterion("law_audit_status <>", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusGreaterThan(String value) {
            addCriterion("law_audit_status >", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("law_audit_status >=", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusLessThan(String value) {
            addCriterion("law_audit_status <", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("law_audit_status <=", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusLike(String value) {
            addCriterion("law_audit_status like", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusNotLike(String value) {
            addCriterion("law_audit_status not like", value, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusIn(List<String> values) {
            addCriterion("law_audit_status in", values, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusNotIn(List<String> values) {
            addCriterion("law_audit_status not in", values, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusBetween(String value1, String value2) {
            addCriterion("law_audit_status between", value1, value2, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditStatusNotBetween(String value1, String value2) {
            addCriterion("law_audit_status not between", value1, value2, "lawAuditStatus");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksIsNull() {
            addCriterion("law_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksIsNotNull() {
            addCriterion("law_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksEqualTo(String value) {
            addCriterion("law_audit_remarks =", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksNotEqualTo(String value) {
            addCriterion("law_audit_remarks <>", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksGreaterThan(String value) {
            addCriterion("law_audit_remarks >", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("law_audit_remarks >=", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksLessThan(String value) {
            addCriterion("law_audit_remarks <", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("law_audit_remarks <=", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksLike(String value) {
            addCriterion("law_audit_remarks like", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksNotLike(String value) {
            addCriterion("law_audit_remarks not like", value, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksIn(List<String> values) {
            addCriterion("law_audit_remarks in", values, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksNotIn(List<String> values) {
            addCriterion("law_audit_remarks not in", values, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksBetween(String value1, String value2) {
            addCriterion("law_audit_remarks between", value1, value2, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andLawAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("law_audit_remarks not between", value1, value2, "lawAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditByIsNull() {
            addCriterion("coo_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andCooAuditByIsNotNull() {
            addCriterion("coo_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andCooAuditByEqualTo(Integer value) {
            addCriterion("coo_audit_by =", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByNotEqualTo(Integer value) {
            addCriterion("coo_audit_by <>", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByGreaterThan(Integer value) {
            addCriterion("coo_audit_by >", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("coo_audit_by >=", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByLessThan(Integer value) {
            addCriterion("coo_audit_by <", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("coo_audit_by <=", value, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByIn(List<Integer> values) {
            addCriterion("coo_audit_by in", values, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByNotIn(List<Integer> values) {
            addCriterion("coo_audit_by not in", values, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByBetween(Integer value1, Integer value2) {
            addCriterion("coo_audit_by between", value1, value2, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("coo_audit_by not between", value1, value2, "cooAuditBy");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusIsNull() {
            addCriterion("coo_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusIsNotNull() {
            addCriterion("coo_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusEqualTo(String value) {
            addCriterion("coo_audit_status =", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusNotEqualTo(String value) {
            addCriterion("coo_audit_status <>", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusGreaterThan(String value) {
            addCriterion("coo_audit_status >", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("coo_audit_status >=", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusLessThan(String value) {
            addCriterion("coo_audit_status <", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("coo_audit_status <=", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusLike(String value) {
            addCriterion("coo_audit_status like", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusNotLike(String value) {
            addCriterion("coo_audit_status not like", value, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusIn(List<String> values) {
            addCriterion("coo_audit_status in", values, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusNotIn(List<String> values) {
            addCriterion("coo_audit_status not in", values, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusBetween(String value1, String value2) {
            addCriterion("coo_audit_status between", value1, value2, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditStatusNotBetween(String value1, String value2) {
            addCriterion("coo_audit_status not between", value1, value2, "cooAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksIsNull() {
            addCriterion("coo_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksIsNotNull() {
            addCriterion("coo_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksEqualTo(String value) {
            addCriterion("coo_audit_remarks =", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksNotEqualTo(String value) {
            addCriterion("coo_audit_remarks <>", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksGreaterThan(String value) {
            addCriterion("coo_audit_remarks >", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("coo_audit_remarks >=", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksLessThan(String value) {
            addCriterion("coo_audit_remarks <", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("coo_audit_remarks <=", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksLike(String value) {
            addCriterion("coo_audit_remarks like", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksNotLike(String value) {
            addCriterion("coo_audit_remarks not like", value, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksIn(List<String> values) {
            addCriterion("coo_audit_remarks in", values, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksNotIn(List<String> values) {
            addCriterion("coo_audit_remarks not in", values, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksBetween(String value1, String value2) {
            addCriterion("coo_audit_remarks between", value1, value2, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andCooAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("coo_audit_remarks not between", value1, value2, "cooAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIsNull() {
            addCriterion("activity_group_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIsNotNull() {
            addCriterion("activity_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdEqualTo(Integer value) {
            addCriterion("activity_group_id =", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotEqualTo(Integer value) {
            addCriterion("activity_group_id <>", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdGreaterThan(Integer value) {
            addCriterion("activity_group_id >", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_group_id >=", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdLessThan(Integer value) {
            addCriterion("activity_group_id <", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_group_id <=", value, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdIn(List<Integer> values) {
            addCriterion("activity_group_id in", values, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotIn(List<Integer> values) {
            addCriterion("activity_group_id not in", values, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_group_id between", value1, value2, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andActivityGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_group_id not between", value1, value2, "activityGroupId");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNull() {
            addCriterion("is_sign is null");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNotNull() {
            addCriterion("is_sign is not null");
            return (Criteria) this;
        }

        public Criteria andIsSignEqualTo(String value) {
            addCriterion("is_sign =", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotEqualTo(String value) {
            addCriterion("is_sign <>", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThan(String value) {
            addCriterion("is_sign >", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThanOrEqualTo(String value) {
            addCriterion("is_sign >=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThan(String value) {
            addCriterion("is_sign <", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThanOrEqualTo(String value) {
            addCriterion("is_sign <=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLike(String value) {
            addCriterion("is_sign like", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotLike(String value) {
            addCriterion("is_sign not like", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignIn(List<String> values) {
            addCriterion("is_sign in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotIn(List<String> values) {
            addCriterion("is_sign not in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignBetween(String value1, String value2) {
            addCriterion("is_sign between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotBetween(String value1, String value2) {
            addCriterion("is_sign not between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusIsNull() {
            addCriterion("pre_sell_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusIsNotNull() {
            addCriterion("pre_sell_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusEqualTo(String value) {
            addCriterion("pre_sell_audit_status =", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusNotEqualTo(String value) {
            addCriterion("pre_sell_audit_status <>", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusGreaterThan(String value) {
            addCriterion("pre_sell_audit_status >", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sell_audit_status >=", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusLessThan(String value) {
            addCriterion("pre_sell_audit_status <", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("pre_sell_audit_status <=", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusLike(String value) {
            addCriterion("pre_sell_audit_status like", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusNotLike(String value) {
            addCriterion("pre_sell_audit_status not like", value, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusIn(List<String> values) {
            addCriterion("pre_sell_audit_status in", values, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusNotIn(List<String> values) {
            addCriterion("pre_sell_audit_status not in", values, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusBetween(String value1, String value2) {
            addCriterion("pre_sell_audit_status between", value1, value2, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditStatusNotBetween(String value1, String value2) {
            addCriterion("pre_sell_audit_status not between", value1, value2, "preSellAuditStatus");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksIsNull() {
            addCriterion("pre_sell_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksIsNotNull() {
            addCriterion("pre_sell_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksEqualTo(String value) {
            addCriterion("pre_sell_audit_remarks =", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksNotEqualTo(String value) {
            addCriterion("pre_sell_audit_remarks <>", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksGreaterThan(String value) {
            addCriterion("pre_sell_audit_remarks >", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sell_audit_remarks >=", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksLessThan(String value) {
            addCriterion("pre_sell_audit_remarks <", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("pre_sell_audit_remarks <=", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksLike(String value) {
            addCriterion("pre_sell_audit_remarks like", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksNotLike(String value) {
            addCriterion("pre_sell_audit_remarks not like", value, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksIn(List<String> values) {
            addCriterion("pre_sell_audit_remarks in", values, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksNotIn(List<String> values) {
            addCriterion("pre_sell_audit_remarks not in", values, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksBetween(String value1, String value2) {
            addCriterion("pre_sell_audit_remarks between", value1, value2, "preSellAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andPreSellAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("pre_sell_audit_remarks not between", value1, value2, "preSellAuditRemarks");
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