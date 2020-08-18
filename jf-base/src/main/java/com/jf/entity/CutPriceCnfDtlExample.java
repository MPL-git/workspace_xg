package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CutPriceCnfDtlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CutPriceCnfDtlExample() {
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

        public Criteria andCutPriceCnfIdIsNull() {
            addCriterion("cut_price_cnf_id is null");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdIsNotNull() {
            addCriterion("cut_price_cnf_id is not null");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdEqualTo(Integer value) {
            addCriterion("cut_price_cnf_id =", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdNotEqualTo(Integer value) {
            addCriterion("cut_price_cnf_id <>", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdGreaterThan(Integer value) {
            addCriterion("cut_price_cnf_id >", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cut_price_cnf_id >=", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdLessThan(Integer value) {
            addCriterion("cut_price_cnf_id <", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdLessThanOrEqualTo(Integer value) {
            addCriterion("cut_price_cnf_id <=", value, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdIn(List<Integer> values) {
            addCriterion("cut_price_cnf_id in", values, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdNotIn(List<Integer> values) {
            addCriterion("cut_price_cnf_id not in", values, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdBetween(Integer value1, Integer value2) {
            addCriterion("cut_price_cnf_id between", value1, value2, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andCutPriceCnfIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cut_price_cnf_id not between", value1, value2, "cutPriceCnfId");
            return (Criteria) this;
        }

        public Criteria andRateTypeIsNull() {
            addCriterion("rate_type is null");
            return (Criteria) this;
        }

        public Criteria andRateTypeIsNotNull() {
            addCriterion("rate_type is not null");
            return (Criteria) this;
        }

        public Criteria andRateTypeEqualTo(String value) {
            addCriterion("rate_type =", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotEqualTo(String value) {
            addCriterion("rate_type <>", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeGreaterThan(String value) {
            addCriterion("rate_type >", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("rate_type >=", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLessThan(String value) {
            addCriterion("rate_type <", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLessThanOrEqualTo(String value) {
            addCriterion("rate_type <=", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeLike(String value) {
            addCriterion("rate_type like", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotLike(String value) {
            addCriterion("rate_type not like", value, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeIn(List<String> values) {
            addCriterion("rate_type in", values, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotIn(List<String> values) {
            addCriterion("rate_type not in", values, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeBetween(String value1, String value2) {
            addCriterion("rate_type between", value1, value2, "rateType");
            return (Criteria) this;
        }

        public Criteria andRateTypeNotBetween(String value1, String value2) {
            addCriterion("rate_type not between", value1, value2, "rateType");
            return (Criteria) this;
        }

        public Criteria andBeginAmountIsNull() {
            addCriterion("begin_amount is null");
            return (Criteria) this;
        }

        public Criteria andBeginAmountIsNotNull() {
            addCriterion("begin_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBeginAmountEqualTo(BigDecimal value) {
            addCriterion("begin_amount =", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountNotEqualTo(BigDecimal value) {
            addCriterion("begin_amount <>", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountGreaterThan(BigDecimal value) {
            addCriterion("begin_amount >", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_amount >=", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountLessThan(BigDecimal value) {
            addCriterion("begin_amount <", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_amount <=", value, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountIn(List<BigDecimal> values) {
            addCriterion("begin_amount in", values, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountNotIn(List<BigDecimal> values) {
            addCriterion("begin_amount not in", values, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_amount between", value1, value2, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andBeginAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_amount not between", value1, value2, "beginAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountIsNull() {
            addCriterion("end_amount is null");
            return (Criteria) this;
        }

        public Criteria andEndAmountIsNotNull() {
            addCriterion("end_amount is not null");
            return (Criteria) this;
        }

        public Criteria andEndAmountEqualTo(BigDecimal value) {
            addCriterion("end_amount =", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountNotEqualTo(BigDecimal value) {
            addCriterion("end_amount <>", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountGreaterThan(BigDecimal value) {
            addCriterion("end_amount >", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_amount >=", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountLessThan(BigDecimal value) {
            addCriterion("end_amount <", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_amount <=", value, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountIn(List<BigDecimal> values) {
            addCriterion("end_amount in", values, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountNotIn(List<BigDecimal> values) {
            addCriterion("end_amount not in", values, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_amount between", value1, value2, "endAmount");
            return (Criteria) this;
        }

        public Criteria andEndAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_amount not between", value1, value2, "endAmount");
            return (Criteria) this;
        }

        public Criteria andBeginRateIsNull() {
            addCriterion("begin_rate is null");
            return (Criteria) this;
        }

        public Criteria andBeginRateIsNotNull() {
            addCriterion("begin_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBeginRateEqualTo(BigDecimal value) {
            addCriterion("begin_rate =", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateNotEqualTo(BigDecimal value) {
            addCriterion("begin_rate <>", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateGreaterThan(BigDecimal value) {
            addCriterion("begin_rate >", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_rate >=", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateLessThan(BigDecimal value) {
            addCriterion("begin_rate <", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_rate <=", value, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateIn(List<BigDecimal> values) {
            addCriterion("begin_rate in", values, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateNotIn(List<BigDecimal> values) {
            addCriterion("begin_rate not in", values, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_rate between", value1, value2, "beginRate");
            return (Criteria) this;
        }

        public Criteria andBeginRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_rate not between", value1, value2, "beginRate");
            return (Criteria) this;
        }

        public Criteria andEndRateIsNull() {
            addCriterion("end_rate is null");
            return (Criteria) this;
        }

        public Criteria andEndRateIsNotNull() {
            addCriterion("end_rate is not null");
            return (Criteria) this;
        }

        public Criteria andEndRateEqualTo(BigDecimal value) {
            addCriterion("end_rate =", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateNotEqualTo(BigDecimal value) {
            addCriterion("end_rate <>", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateGreaterThan(BigDecimal value) {
            addCriterion("end_rate >", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_rate >=", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateLessThan(BigDecimal value) {
            addCriterion("end_rate <", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_rate <=", value, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateIn(List<BigDecimal> values) {
            addCriterion("end_rate in", values, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateNotIn(List<BigDecimal> values) {
            addCriterion("end_rate not in", values, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_rate between", value1, value2, "endRate");
            return (Criteria) this;
        }

        public Criteria andEndRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_rate not between", value1, value2, "endRate");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIsNull() {
            addCriterion("fixed_amount is null");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIsNotNull() {
            addCriterion("fixed_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFixedAmountEqualTo(BigDecimal value) {
            addCriterion("fixed_amount =", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotEqualTo(BigDecimal value) {
            addCriterion("fixed_amount <>", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountGreaterThan(BigDecimal value) {
            addCriterion("fixed_amount >", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fixed_amount >=", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountLessThan(BigDecimal value) {
            addCriterion("fixed_amount <", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fixed_amount <=", value, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountIn(List<BigDecimal> values) {
            addCriterion("fixed_amount in", values, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotIn(List<BigDecimal> values) {
            addCriterion("fixed_amount not in", values, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fixed_amount between", value1, value2, "fixedAmount");
            return (Criteria) this;
        }

        public Criteria andFixedAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fixed_amount not between", value1, value2, "fixedAmount");
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