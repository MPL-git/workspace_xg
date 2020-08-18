package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtOptimizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtOptimizeExample() {
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

        public Criteria andAuditRiskIsNull() {
            addCriterion("audit_risk is null");
            return (Criteria) this;
        }

        public Criteria andAuditRiskIsNotNull() {
            addCriterion("audit_risk is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRiskEqualTo(String value) {
            addCriterion("audit_risk =", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskNotEqualTo(String value) {
            addCriterion("audit_risk <>", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskGreaterThan(String value) {
            addCriterion("audit_risk >", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskGreaterThanOrEqualTo(String value) {
            addCriterion("audit_risk >=", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskLessThan(String value) {
            addCriterion("audit_risk <", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskLessThanOrEqualTo(String value) {
            addCriterion("audit_risk <=", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskLike(String value) {
            addCriterion("audit_risk like", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskNotLike(String value) {
            addCriterion("audit_risk not like", value, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskIn(List<String> values) {
            addCriterion("audit_risk in", values, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskNotIn(List<String> values) {
            addCriterion("audit_risk not in", values, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskBetween(String value1, String value2) {
            addCriterion("audit_risk between", value1, value2, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andAuditRiskNotBetween(String value1, String value2) {
            addCriterion("audit_risk not between", value1, value2, "auditRisk");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityIsNull() {
            addCriterion("degree_adaptability is null");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityIsNotNull() {
            addCriterion("degree_adaptability is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityEqualTo(String value) {
            addCriterion("degree_adaptability =", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityNotEqualTo(String value) {
            addCriterion("degree_adaptability <>", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityGreaterThan(String value) {
            addCriterion("degree_adaptability >", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityGreaterThanOrEqualTo(String value) {
            addCriterion("degree_adaptability >=", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityLessThan(String value) {
            addCriterion("degree_adaptability <", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityLessThanOrEqualTo(String value) {
            addCriterion("degree_adaptability <=", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityLike(String value) {
            addCriterion("degree_adaptability like", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityNotLike(String value) {
            addCriterion("degree_adaptability not like", value, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityIn(List<String> values) {
            addCriterion("degree_adaptability in", values, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityNotIn(List<String> values) {
            addCriterion("degree_adaptability not in", values, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityBetween(String value1, String value2) {
            addCriterion("degree_adaptability between", value1, value2, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andDegreeAdaptabilityNotBetween(String value1, String value2) {
            addCriterion("degree_adaptability not between", value1, value2, "degreeAdaptability");
            return (Criteria) this;
        }

        public Criteria andResourceGradeIsNull() {
            addCriterion("resource_grade is null");
            return (Criteria) this;
        }

        public Criteria andResourceGradeIsNotNull() {
            addCriterion("resource_grade is not null");
            return (Criteria) this;
        }

        public Criteria andResourceGradeEqualTo(String value) {
            addCriterion("resource_grade =", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeNotEqualTo(String value) {
            addCriterion("resource_grade <>", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeGreaterThan(String value) {
            addCriterion("resource_grade >", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeGreaterThanOrEqualTo(String value) {
            addCriterion("resource_grade >=", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeLessThan(String value) {
            addCriterion("resource_grade <", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeLessThanOrEqualTo(String value) {
            addCriterion("resource_grade <=", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeLike(String value) {
            addCriterion("resource_grade like", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeNotLike(String value) {
            addCriterion("resource_grade not like", value, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeIn(List<String> values) {
            addCriterion("resource_grade in", values, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeNotIn(List<String> values) {
            addCriterion("resource_grade not in", values, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeBetween(String value1, String value2) {
            addCriterion("resource_grade between", value1, value2, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andResourceGradeNotBetween(String value1, String value2) {
            addCriterion("resource_grade not between", value1, value2, "resourceGrade");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIsNull() {
            addCriterion("operate_remarks is null");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIsNotNull() {
            addCriterion("operate_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksEqualTo(String value) {
            addCriterion("operate_remarks =", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotEqualTo(String value) {
            addCriterion("operate_remarks <>", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksGreaterThan(String value) {
            addCriterion("operate_remarks >", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("operate_remarks >=", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLessThan(String value) {
            addCriterion("operate_remarks <", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLessThanOrEqualTo(String value) {
            addCriterion("operate_remarks <=", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksLike(String value) {
            addCriterion("operate_remarks like", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotLike(String value) {
            addCriterion("operate_remarks not like", value, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksIn(List<String> values) {
            addCriterion("operate_remarks in", values, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotIn(List<String> values) {
            addCriterion("operate_remarks not in", values, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksBetween(String value1, String value2) {
            addCriterion("operate_remarks between", value1, value2, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andOperateRemarksNotBetween(String value1, String value2) {
            addCriterion("operate_remarks not between", value1, value2, "operateRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksIsNull() {
            addCriterion("deposit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksIsNotNull() {
            addCriterion("deposit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksEqualTo(String value) {
            addCriterion("deposit_remarks =", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksNotEqualTo(String value) {
            addCriterion("deposit_remarks <>", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksGreaterThan(String value) {
            addCriterion("deposit_remarks >", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_remarks >=", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksLessThan(String value) {
            addCriterion("deposit_remarks <", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksLessThanOrEqualTo(String value) {
            addCriterion("deposit_remarks <=", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksLike(String value) {
            addCriterion("deposit_remarks like", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksNotLike(String value) {
            addCriterion("deposit_remarks not like", value, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksIn(List<String> values) {
            addCriterion("deposit_remarks in", values, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksNotIn(List<String> values) {
            addCriterion("deposit_remarks not in", values, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksBetween(String value1, String value2) {
            addCriterion("deposit_remarks between", value1, value2, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andDepositRemarksNotBetween(String value1, String value2) {
            addCriterion("deposit_remarks not between", value1, value2, "depositRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksIsNull() {
            addCriterion("gross_profit_rate_remarks is null");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksIsNotNull() {
            addCriterion("gross_profit_rate_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksEqualTo(String value) {
            addCriterion("gross_profit_rate_remarks =", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksNotEqualTo(String value) {
            addCriterion("gross_profit_rate_remarks <>", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksGreaterThan(String value) {
            addCriterion("gross_profit_rate_remarks >", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("gross_profit_rate_remarks >=", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksLessThan(String value) {
            addCriterion("gross_profit_rate_remarks <", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksLessThanOrEqualTo(String value) {
            addCriterion("gross_profit_rate_remarks <=", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksLike(String value) {
            addCriterion("gross_profit_rate_remarks like", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksNotLike(String value) {
            addCriterion("gross_profit_rate_remarks not like", value, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksIn(List<String> values) {
            addCriterion("gross_profit_rate_remarks in", values, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksNotIn(List<String> values) {
            addCriterion("gross_profit_rate_remarks not in", values, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksBetween(String value1, String value2) {
            addCriterion("gross_profit_rate_remarks between", value1, value2, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andGrossProfitRateRemarksNotBetween(String value1, String value2) {
            addCriterion("gross_profit_rate_remarks not between", value1, value2, "grossProfitRateRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksIsNull() {
            addCriterion("product_remarks is null");
            return (Criteria) this;
        }

        public Criteria andProductRemarksIsNotNull() {
            addCriterion("product_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andProductRemarksEqualTo(String value) {
            addCriterion("product_remarks =", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksNotEqualTo(String value) {
            addCriterion("product_remarks <>", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksGreaterThan(String value) {
            addCriterion("product_remarks >", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("product_remarks >=", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksLessThan(String value) {
            addCriterion("product_remarks <", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksLessThanOrEqualTo(String value) {
            addCriterion("product_remarks <=", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksLike(String value) {
            addCriterion("product_remarks like", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksNotLike(String value) {
            addCriterion("product_remarks not like", value, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksIn(List<String> values) {
            addCriterion("product_remarks in", values, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksNotIn(List<String> values) {
            addCriterion("product_remarks not in", values, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksBetween(String value1, String value2) {
            addCriterion("product_remarks between", value1, value2, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andProductRemarksNotBetween(String value1, String value2) {
            addCriterion("product_remarks not between", value1, value2, "productRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksIsNull() {
            addCriterion("service_remarks is null");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksIsNotNull() {
            addCriterion("service_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksEqualTo(String value) {
            addCriterion("service_remarks =", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksNotEqualTo(String value) {
            addCriterion("service_remarks <>", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksGreaterThan(String value) {
            addCriterion("service_remarks >", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("service_remarks >=", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksLessThan(String value) {
            addCriterion("service_remarks <", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksLessThanOrEqualTo(String value) {
            addCriterion("service_remarks <=", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksLike(String value) {
            addCriterion("service_remarks like", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksNotLike(String value) {
            addCriterion("service_remarks not like", value, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksIn(List<String> values) {
            addCriterion("service_remarks in", values, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksNotIn(List<String> values) {
            addCriterion("service_remarks not in", values, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksBetween(String value1, String value2) {
            addCriterion("service_remarks between", value1, value2, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andServiceRemarksNotBetween(String value1, String value2) {
            addCriterion("service_remarks not between", value1, value2, "serviceRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksIsNull() {
            addCriterion("wuliu_remarks is null");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksIsNotNull() {
            addCriterion("wuliu_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksEqualTo(String value) {
            addCriterion("wuliu_remarks =", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksNotEqualTo(String value) {
            addCriterion("wuliu_remarks <>", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksGreaterThan(String value) {
            addCriterion("wuliu_remarks >", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("wuliu_remarks >=", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksLessThan(String value) {
            addCriterion("wuliu_remarks <", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksLessThanOrEqualTo(String value) {
            addCriterion("wuliu_remarks <=", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksLike(String value) {
            addCriterion("wuliu_remarks like", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksNotLike(String value) {
            addCriterion("wuliu_remarks not like", value, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksIn(List<String> values) {
            addCriterion("wuliu_remarks in", values, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksNotIn(List<String> values) {
            addCriterion("wuliu_remarks not in", values, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksBetween(String value1, String value2) {
            addCriterion("wuliu_remarks between", value1, value2, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andWuliuRemarksNotBetween(String value1, String value2) {
            addCriterion("wuliu_remarks not between", value1, value2, "wuliuRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksIsNull() {
            addCriterion("spread_remarks is null");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksIsNotNull() {
            addCriterion("spread_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksEqualTo(String value) {
            addCriterion("spread_remarks =", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksNotEqualTo(String value) {
            addCriterion("spread_remarks <>", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksGreaterThan(String value) {
            addCriterion("spread_remarks >", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("spread_remarks >=", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksLessThan(String value) {
            addCriterion("spread_remarks <", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksLessThanOrEqualTo(String value) {
            addCriterion("spread_remarks <=", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksLike(String value) {
            addCriterion("spread_remarks like", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksNotLike(String value) {
            addCriterion("spread_remarks not like", value, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksIn(List<String> values) {
            addCriterion("spread_remarks in", values, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksNotIn(List<String> values) {
            addCriterion("spread_remarks not in", values, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksBetween(String value1, String value2) {
            addCriterion("spread_remarks between", value1, value2, "spreadRemarks");
            return (Criteria) this;
        }

        public Criteria andSpreadRemarksNotBetween(String value1, String value2) {
            addCriterion("spread_remarks not between", value1, value2, "spreadRemarks");
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