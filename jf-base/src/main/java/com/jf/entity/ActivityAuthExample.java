package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityAuthExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagIsNull() {
            addCriterion("activity_name_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagIsNotNull() {
            addCriterion("activity_name_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagEqualTo(String value) {
            addCriterion("activity_name_flag =", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagNotEqualTo(String value) {
            addCriterion("activity_name_flag <>", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagGreaterThan(String value) {
            addCriterion("activity_name_flag >", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name_flag >=", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagLessThan(String value) {
            addCriterion("activity_name_flag <", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_name_flag <=", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagLike(String value) {
            addCriterion("activity_name_flag like", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagNotLike(String value) {
            addCriterion("activity_name_flag not like", value, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagIn(List<String> values) {
            addCriterion("activity_name_flag in", values, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagNotIn(List<String> values) {
            addCriterion("activity_name_flag not in", values, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagBetween(String value1, String value2) {
            addCriterion("activity_name_flag between", value1, value2, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityNameFlagNotBetween(String value1, String value2) {
            addCriterion("activity_name_flag not between", value1, value2, "activityNameFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagIsNull() {
            addCriterion("activity_type_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagIsNotNull() {
            addCriterion("activity_type_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagEqualTo(String value) {
            addCriterion("activity_type_flag =", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagNotEqualTo(String value) {
            addCriterion("activity_type_flag <>", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagGreaterThan(String value) {
            addCriterion("activity_type_flag >", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_type_flag >=", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagLessThan(String value) {
            addCriterion("activity_type_flag <", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_type_flag <=", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagLike(String value) {
            addCriterion("activity_type_flag like", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagNotLike(String value) {
            addCriterion("activity_type_flag not like", value, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagIn(List<String> values) {
            addCriterion("activity_type_flag in", values, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagNotIn(List<String> values) {
            addCriterion("activity_type_flag not in", values, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagBetween(String value1, String value2) {
            addCriterion("activity_type_flag between", value1, value2, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityTypeFlagNotBetween(String value1, String value2) {
            addCriterion("activity_type_flag not between", value1, value2, "activityTypeFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagIsNull() {
            addCriterion("activity_brand_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagIsNotNull() {
            addCriterion("activity_brand_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagEqualTo(String value) {
            addCriterion("activity_brand_flag =", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagNotEqualTo(String value) {
            addCriterion("activity_brand_flag <>", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagGreaterThan(String value) {
            addCriterion("activity_brand_flag >", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_brand_flag >=", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagLessThan(String value) {
            addCriterion("activity_brand_flag <", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_brand_flag <=", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagLike(String value) {
            addCriterion("activity_brand_flag like", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagNotLike(String value) {
            addCriterion("activity_brand_flag not like", value, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagIn(List<String> values) {
            addCriterion("activity_brand_flag in", values, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagNotIn(List<String> values) {
            addCriterion("activity_brand_flag not in", values, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagBetween(String value1, String value2) {
            addCriterion("activity_brand_flag between", value1, value2, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBrandFlagNotBetween(String value1, String value2) {
            addCriterion("activity_brand_flag not between", value1, value2, "activityBrandFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagIsNull() {
            addCriterion("activity_benefit_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagIsNotNull() {
            addCriterion("activity_benefit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagEqualTo(String value) {
            addCriterion("activity_benefit_flag =", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagNotEqualTo(String value) {
            addCriterion("activity_benefit_flag <>", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagGreaterThan(String value) {
            addCriterion("activity_benefit_flag >", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_benefit_flag >=", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagLessThan(String value) {
            addCriterion("activity_benefit_flag <", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_benefit_flag <=", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagLike(String value) {
            addCriterion("activity_benefit_flag like", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagNotLike(String value) {
            addCriterion("activity_benefit_flag not like", value, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagIn(List<String> values) {
            addCriterion("activity_benefit_flag in", values, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagNotIn(List<String> values) {
            addCriterion("activity_benefit_flag not in", values, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagBetween(String value1, String value2) {
            addCriterion("activity_benefit_flag between", value1, value2, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityBenefitFlagNotBetween(String value1, String value2) {
            addCriterion("activity_benefit_flag not between", value1, value2, "activityBenefitFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagIsNull() {
            addCriterion("activity_entry_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagIsNotNull() {
            addCriterion("activity_entry_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagEqualTo(String value) {
            addCriterion("activity_entry_flag =", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagNotEqualTo(String value) {
            addCriterion("activity_entry_flag <>", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagGreaterThan(String value) {
            addCriterion("activity_entry_flag >", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_entry_flag >=", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagLessThan(String value) {
            addCriterion("activity_entry_flag <", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_entry_flag <=", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagLike(String value) {
            addCriterion("activity_entry_flag like", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagNotLike(String value) {
            addCriterion("activity_entry_flag not like", value, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagIn(List<String> values) {
            addCriterion("activity_entry_flag in", values, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagNotIn(List<String> values) {
            addCriterion("activity_entry_flag not in", values, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagBetween(String value1, String value2) {
            addCriterion("activity_entry_flag between", value1, value2, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityEntryFlagNotBetween(String value1, String value2) {
            addCriterion("activity_entry_flag not between", value1, value2, "activityEntryFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagIsNull() {
            addCriterion("activity_poster_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagIsNotNull() {
            addCriterion("activity_poster_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagEqualTo(String value) {
            addCriterion("activity_poster_flag =", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagNotEqualTo(String value) {
            addCriterion("activity_poster_flag <>", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagGreaterThan(String value) {
            addCriterion("activity_poster_flag >", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_poster_flag >=", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagLessThan(String value) {
            addCriterion("activity_poster_flag <", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_poster_flag <=", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagLike(String value) {
            addCriterion("activity_poster_flag like", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagNotLike(String value) {
            addCriterion("activity_poster_flag not like", value, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagIn(List<String> values) {
            addCriterion("activity_poster_flag in", values, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagNotIn(List<String> values) {
            addCriterion("activity_poster_flag not in", values, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagBetween(String value1, String value2) {
            addCriterion("activity_poster_flag between", value1, value2, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPosterFlagNotBetween(String value1, String value2) {
            addCriterion("activity_poster_flag not between", value1, value2, "activityPosterFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagIsNull() {
            addCriterion("activity_preferential_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagIsNotNull() {
            addCriterion("activity_preferential_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagEqualTo(String value) {
            addCriterion("activity_preferential_flag =", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagNotEqualTo(String value) {
            addCriterion("activity_preferential_flag <>", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagGreaterThan(String value) {
            addCriterion("activity_preferential_flag >", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_preferential_flag >=", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagLessThan(String value) {
            addCriterion("activity_preferential_flag <", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_preferential_flag <=", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagLike(String value) {
            addCriterion("activity_preferential_flag like", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagNotLike(String value) {
            addCriterion("activity_preferential_flag not like", value, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagIn(List<String> values) {
            addCriterion("activity_preferential_flag in", values, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagNotIn(List<String> values) {
            addCriterion("activity_preferential_flag not in", values, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagBetween(String value1, String value2) {
            addCriterion("activity_preferential_flag between", value1, value2, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityPreferentialFlagNotBetween(String value1, String value2) {
            addCriterion("activity_preferential_flag not between", value1, value2, "activityPreferentialFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagIsNull() {
            addCriterion("activity_modify_flag is null");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagIsNotNull() {
            addCriterion("activity_modify_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagEqualTo(String value) {
            addCriterion("activity_modify_flag =", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagNotEqualTo(String value) {
            addCriterion("activity_modify_flag <>", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagGreaterThan(String value) {
            addCriterion("activity_modify_flag >", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("activity_modify_flag >=", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagLessThan(String value) {
            addCriterion("activity_modify_flag <", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagLessThanOrEqualTo(String value) {
            addCriterion("activity_modify_flag <=", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagLike(String value) {
            addCriterion("activity_modify_flag like", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagNotLike(String value) {
            addCriterion("activity_modify_flag not like", value, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagIn(List<String> values) {
            addCriterion("activity_modify_flag in", values, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagNotIn(List<String> values) {
            addCriterion("activity_modify_flag not in", values, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagBetween(String value1, String value2) {
            addCriterion("activity_modify_flag between", value1, value2, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andActivityModifyFlagNotBetween(String value1, String value2) {
            addCriterion("activity_modify_flag not between", value1, value2, "activityModifyFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagIsNull() {
            addCriterion("product_type_brand_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagIsNotNull() {
            addCriterion("product_type_brand_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagEqualTo(String value) {
            addCriterion("product_type_brand_flag =", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagNotEqualTo(String value) {
            addCriterion("product_type_brand_flag <>", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagGreaterThan(String value) {
            addCriterion("product_type_brand_flag >", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_type_brand_flag >=", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagLessThan(String value) {
            addCriterion("product_type_brand_flag <", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagLessThanOrEqualTo(String value) {
            addCriterion("product_type_brand_flag <=", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagLike(String value) {
            addCriterion("product_type_brand_flag like", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagNotLike(String value) {
            addCriterion("product_type_brand_flag not like", value, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagIn(List<String> values) {
            addCriterion("product_type_brand_flag in", values, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagNotIn(List<String> values) {
            addCriterion("product_type_brand_flag not in", values, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagBetween(String value1, String value2) {
            addCriterion("product_type_brand_flag between", value1, value2, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductTypeBrandFlagNotBetween(String value1, String value2) {
            addCriterion("product_type_brand_flag not between", value1, value2, "productTypeBrandFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagIsNull() {
            addCriterion("product_name_prop_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagIsNotNull() {
            addCriterion("product_name_prop_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagEqualTo(String value) {
            addCriterion("product_name_prop_flag =", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagNotEqualTo(String value) {
            addCriterion("product_name_prop_flag <>", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagGreaterThan(String value) {
            addCriterion("product_name_prop_flag >", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_name_prop_flag >=", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagLessThan(String value) {
            addCriterion("product_name_prop_flag <", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagLessThanOrEqualTo(String value) {
            addCriterion("product_name_prop_flag <=", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagLike(String value) {
            addCriterion("product_name_prop_flag like", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagNotLike(String value) {
            addCriterion("product_name_prop_flag not like", value, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagIn(List<String> values) {
            addCriterion("product_name_prop_flag in", values, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagNotIn(List<String> values) {
            addCriterion("product_name_prop_flag not in", values, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagBetween(String value1, String value2) {
            addCriterion("product_name_prop_flag between", value1, value2, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductNamePropFlagNotBetween(String value1, String value2) {
            addCriterion("product_name_prop_flag not between", value1, value2, "productNamePropFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagIsNull() {
            addCriterion("product_pic_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagIsNotNull() {
            addCriterion("product_pic_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagEqualTo(String value) {
            addCriterion("product_pic_flag =", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagNotEqualTo(String value) {
            addCriterion("product_pic_flag <>", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagGreaterThan(String value) {
            addCriterion("product_pic_flag >", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_pic_flag >=", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagLessThan(String value) {
            addCriterion("product_pic_flag <", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagLessThanOrEqualTo(String value) {
            addCriterion("product_pic_flag <=", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagLike(String value) {
            addCriterion("product_pic_flag like", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagNotLike(String value) {
            addCriterion("product_pic_flag not like", value, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagIn(List<String> values) {
            addCriterion("product_pic_flag in", values, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagNotIn(List<String> values) {
            addCriterion("product_pic_flag not in", values, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagBetween(String value1, String value2) {
            addCriterion("product_pic_flag between", value1, value2, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPicFlagNotBetween(String value1, String value2) {
            addCriterion("product_pic_flag not between", value1, value2, "productPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagIsNull() {
            addCriterion("product_desc_pic_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagIsNotNull() {
            addCriterion("product_desc_pic_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagEqualTo(String value) {
            addCriterion("product_desc_pic_flag =", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagNotEqualTo(String value) {
            addCriterion("product_desc_pic_flag <>", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagGreaterThan(String value) {
            addCriterion("product_desc_pic_flag >", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_desc_pic_flag >=", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagLessThan(String value) {
            addCriterion("product_desc_pic_flag <", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagLessThanOrEqualTo(String value) {
            addCriterion("product_desc_pic_flag <=", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagLike(String value) {
            addCriterion("product_desc_pic_flag like", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagNotLike(String value) {
            addCriterion("product_desc_pic_flag not like", value, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagIn(List<String> values) {
            addCriterion("product_desc_pic_flag in", values, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagNotIn(List<String> values) {
            addCriterion("product_desc_pic_flag not in", values, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagBetween(String value1, String value2) {
            addCriterion("product_desc_pic_flag between", value1, value2, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductDescPicFlagNotBetween(String value1, String value2) {
            addCriterion("product_desc_pic_flag not between", value1, value2, "productDescPicFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagIsNull() {
            addCriterion("product_prop_num_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagIsNotNull() {
            addCriterion("product_prop_num_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagEqualTo(String value) {
            addCriterion("product_prop_num_flag =", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagNotEqualTo(String value) {
            addCriterion("product_prop_num_flag <>", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagGreaterThan(String value) {
            addCriterion("product_prop_num_flag >", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_prop_num_flag >=", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagLessThan(String value) {
            addCriterion("product_prop_num_flag <", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagLessThanOrEqualTo(String value) {
            addCriterion("product_prop_num_flag <=", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagLike(String value) {
            addCriterion("product_prop_num_flag like", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagNotLike(String value) {
            addCriterion("product_prop_num_flag not like", value, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagIn(List<String> values) {
            addCriterion("product_prop_num_flag in", values, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagNotIn(List<String> values) {
            addCriterion("product_prop_num_flag not in", values, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagBetween(String value1, String value2) {
            addCriterion("product_prop_num_flag between", value1, value2, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropNumFlagNotBetween(String value1, String value2) {
            addCriterion("product_prop_num_flag not between", value1, value2, "productPropNumFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagIsNull() {
            addCriterion("product_prop_price_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagIsNotNull() {
            addCriterion("product_prop_price_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagEqualTo(String value) {
            addCriterion("product_prop_price_flag =", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagNotEqualTo(String value) {
            addCriterion("product_prop_price_flag <>", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagGreaterThan(String value) {
            addCriterion("product_prop_price_flag >", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_prop_price_flag >=", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagLessThan(String value) {
            addCriterion("product_prop_price_flag <", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagLessThanOrEqualTo(String value) {
            addCriterion("product_prop_price_flag <=", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagLike(String value) {
            addCriterion("product_prop_price_flag like", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagNotLike(String value) {
            addCriterion("product_prop_price_flag not like", value, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagIn(List<String> values) {
            addCriterion("product_prop_price_flag in", values, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagNotIn(List<String> values) {
            addCriterion("product_prop_price_flag not in", values, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagBetween(String value1, String value2) {
            addCriterion("product_prop_price_flag between", value1, value2, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropPriceFlagNotBetween(String value1, String value2) {
            addCriterion("product_prop_price_flag not between", value1, value2, "productPropPriceFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagIsNull() {
            addCriterion("product_prop_stock_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagIsNotNull() {
            addCriterion("product_prop_stock_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagEqualTo(String value) {
            addCriterion("product_prop_stock_flag =", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagNotEqualTo(String value) {
            addCriterion("product_prop_stock_flag <>", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagGreaterThan(String value) {
            addCriterion("product_prop_stock_flag >", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_prop_stock_flag >=", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagLessThan(String value) {
            addCriterion("product_prop_stock_flag <", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagLessThanOrEqualTo(String value) {
            addCriterion("product_prop_stock_flag <=", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagLike(String value) {
            addCriterion("product_prop_stock_flag like", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagNotLike(String value) {
            addCriterion("product_prop_stock_flag not like", value, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagIn(List<String> values) {
            addCriterion("product_prop_stock_flag in", values, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagNotIn(List<String> values) {
            addCriterion("product_prop_stock_flag not in", values, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagBetween(String value1, String value2) {
            addCriterion("product_prop_stock_flag between", value1, value2, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductPropStockFlagNotBetween(String value1, String value2) {
            addCriterion("product_prop_stock_flag not between", value1, value2, "productPropStockFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagIsNull() {
            addCriterion("product_other_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagIsNotNull() {
            addCriterion("product_other_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagEqualTo(String value) {
            addCriterion("product_other_flag =", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagNotEqualTo(String value) {
            addCriterion("product_other_flag <>", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagGreaterThan(String value) {
            addCriterion("product_other_flag >", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagGreaterThanOrEqualTo(String value) {
            addCriterion("product_other_flag >=", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagLessThan(String value) {
            addCriterion("product_other_flag <", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagLessThanOrEqualTo(String value) {
            addCriterion("product_other_flag <=", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagLike(String value) {
            addCriterion("product_other_flag like", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagNotLike(String value) {
            addCriterion("product_other_flag not like", value, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagIn(List<String> values) {
            addCriterion("product_other_flag in", values, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagNotIn(List<String> values) {
            addCriterion("product_other_flag not in", values, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagBetween(String value1, String value2) {
            addCriterion("product_other_flag between", value1, value2, "productOtherFlag");
            return (Criteria) this;
        }

        public Criteria andProductOtherFlagNotBetween(String value1, String value2) {
            addCriterion("product_other_flag not between", value1, value2, "productOtherFlag");
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