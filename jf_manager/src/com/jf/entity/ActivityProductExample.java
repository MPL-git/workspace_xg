package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityProductExample() {
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

        public Criteria andActivityPriceIsNull() {
            addCriterion("activity_price is null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIsNotNull() {
            addCriterion("activity_price is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceEqualTo(BigDecimal value) {
            addCriterion("activity_price =", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotEqualTo(BigDecimal value) {
            addCriterion("activity_price <>", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThan(BigDecimal value) {
            addCriterion("activity_price >", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price >=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThan(BigDecimal value) {
            addCriterion("activity_price <", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price <=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIn(List<BigDecimal> values) {
            addCriterion("activity_price in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotIn(List<BigDecimal> values) {
            addCriterion("activity_price not in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price not between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagIsNull() {
            addCriterion("refuse_flag is null");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagIsNotNull() {
            addCriterion("refuse_flag is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagEqualTo(String value) {
            addCriterion("refuse_flag =", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagNotEqualTo(String value) {
            addCriterion("refuse_flag <>", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagGreaterThan(String value) {
            addCriterion("refuse_flag >", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_flag >=", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagLessThan(String value) {
            addCriterion("refuse_flag <", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagLessThanOrEqualTo(String value) {
            addCriterion("refuse_flag <=", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagLike(String value) {
            addCriterion("refuse_flag like", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagNotLike(String value) {
            addCriterion("refuse_flag not like", value, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagIn(List<String> values) {
            addCriterion("refuse_flag in", values, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagNotIn(List<String> values) {
            addCriterion("refuse_flag not in", values, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagBetween(String value1, String value2) {
            addCriterion("refuse_flag between", value1, value2, "refuseFlag");
            return (Criteria) this;
        }

        public Criteria andRefuseFlagNotBetween(String value1, String value2) {
            addCriterion("refuse_flag not between", value1, value2, "refuseFlag");
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

        public Criteria andIsWatermarkIsNull() {
            addCriterion("is_watermark is null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIsNotNull() {
            addCriterion("is_watermark is not null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkEqualTo(String value) {
            addCriterion("is_watermark =", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotEqualTo(String value) {
            addCriterion("is_watermark <>", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThan(String value) {
            addCriterion("is_watermark >", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThanOrEqualTo(String value) {
            addCriterion("is_watermark >=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThan(String value) {
            addCriterion("is_watermark <", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThanOrEqualTo(String value) {
            addCriterion("is_watermark <=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLike(String value) {
            addCriterion("is_watermark like", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotLike(String value) {
            addCriterion("is_watermark not like", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIn(List<String> values) {
            addCriterion("is_watermark in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotIn(List<String> values) {
            addCriterion("is_watermark not in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkBetween(String value1, String value2) {
            addCriterion("is_watermark between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotBetween(String value1, String value2) {
            addCriterion("is_watermark not between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNull() {
            addCriterion("is_gift is null");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNotNull() {
            addCriterion("is_gift is not null");
            return (Criteria) this;
        }

        public Criteria andIsGiftEqualTo(String value) {
            addCriterion("is_gift =", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotEqualTo(String value) {
            addCriterion("is_gift <>", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThan(String value) {
            addCriterion("is_gift >", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThanOrEqualTo(String value) {
            addCriterion("is_gift >=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThan(String value) {
            addCriterion("is_gift <", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThanOrEqualTo(String value) {
            addCriterion("is_gift <=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLike(String value) {
            addCriterion("is_gift like", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotLike(String value) {
            addCriterion("is_gift not like", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftIn(List<String> values) {
            addCriterion("is_gift in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotIn(List<String> values) {
            addCriterion("is_gift not in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftBetween(String value1, String value2) {
            addCriterion("is_gift between", value1, value2, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotBetween(String value1, String value2) {
            addCriterion("is_gift not between", value1, value2, "isGift");
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