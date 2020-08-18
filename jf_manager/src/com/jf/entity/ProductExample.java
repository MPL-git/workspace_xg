package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    protected String productBrandName;

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public ProductExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNull() {
            addCriterion("ref_id is null");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNotNull() {
            addCriterion("ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefIdEqualTo(String value) {
            addCriterion("ref_id =", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotEqualTo(String value) {
            addCriterion("ref_id <>", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThan(String value) {
            addCriterion("ref_id >", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThanOrEqualTo(String value) {
            addCriterion("ref_id >=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThan(String value) {
            addCriterion("ref_id <", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThanOrEqualTo(String value) {
            addCriterion("ref_id <=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLike(String value) {
            addCriterion("ref_id like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotLike(String value) {
            addCriterion("ref_id not like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdIn(List<String> values) {
            addCriterion("ref_id in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotIn(List<String> values) {
            addCriterion("ref_id not in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdBetween(String value1, String value2) {
            addCriterion("ref_id between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotBetween(String value1, String value2) {
            addCriterion("ref_id not between", value1, value2, "refId");
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

        public Criteria andOffReasonIsNull() {
            addCriterion("off_reason is null");
            return (Criteria) this;
        }

        public Criteria andOffReasonIsNotNull() {
            addCriterion("off_reason is not null");
            return (Criteria) this;
        }

        public Criteria andOffReasonEqualTo(String value) {
            addCriterion("off_reason =", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonNotEqualTo(String value) {
            addCriterion("off_reason <>", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonGreaterThan(String value) {
            addCriterion("off_reason >", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonGreaterThanOrEqualTo(String value) {
            addCriterion("off_reason >=", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonLessThan(String value) {
            addCriterion("off_reason <", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonLessThanOrEqualTo(String value) {
            addCriterion("off_reason <=", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonLike(String value) {
            addCriterion("off_reason like", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonNotLike(String value) {
            addCriterion("off_reason not like", value, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonIn(List<String> values) {
            addCriterion("off_reason in", values, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonNotIn(List<String> values) {
            addCriterion("off_reason not in", values, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonBetween(String value1, String value2) {
            addCriterion("off_reason between", value1, value2, "offReason");
            return (Criteria) this;
        }

        public Criteria andOffReasonNotBetween(String value1, String value2) {
            addCriterion("off_reason not between", value1, value2, "offReason");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditByIsNull() {
            addCriterion("audit_by is null");
            return (Criteria) this;
        }

        public Criteria andAuditByIsNotNull() {
            addCriterion("audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andAuditByEqualTo(Integer value) {
            addCriterion("audit_by =", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotEqualTo(Integer value) {
            addCriterion("audit_by <>", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByGreaterThan(Integer value) {
            addCriterion("audit_by >", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_by >=", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByLessThan(Integer value) {
            addCriterion("audit_by <", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("audit_by <=", value, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByIn(List<Integer> values) {
            addCriterion("audit_by in", values, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotIn(List<Integer> values) {
            addCriterion("audit_by not in", values, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByBetween(Integer value1, Integer value2) {
            addCriterion("audit_by between", value1, value2, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_by not between", value1, value2, "auditBy");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIsNull() {
            addCriterion("audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIsNotNull() {
            addCriterion("audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksEqualTo(String value) {
            addCriterion("audit_remarks =", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotEqualTo(String value) {
            addCriterion("audit_remarks <>", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThan(String value) {
            addCriterion("audit_remarks >", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("audit_remarks >=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThan(String value) {
            addCriterion("audit_remarks <", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("audit_remarks <=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLike(String value) {
            addCriterion("audit_remarks like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotLike(String value) {
            addCriterion("audit_remarks not like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIn(List<String> values) {
            addCriterion("audit_remarks in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotIn(List<String> values) {
            addCriterion("audit_remarks not in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksBetween(String value1, String value2) {
            addCriterion("audit_remarks between", value1, value2, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("audit_remarks not between", value1, value2, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andProductType1IdIsNull() {
            addCriterion("product_type1_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdIsNotNull() {
            addCriterion("product_type1_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdEqualTo(Integer value) {
            addCriterion("product_type1_id =", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotEqualTo(Integer value) {
            addCriterion("product_type1_id <>", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdGreaterThan(Integer value) {
            addCriterion("product_type1_id >", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type1_id >=", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdLessThan(Integer value) {
            addCriterion("product_type1_id <", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type1_id <=", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdIn(List<Integer> values) {
            addCriterion("product_type1_id in", values, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotIn(List<Integer> values) {
            addCriterion("product_type1_id not in", values, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type1_id between", value1, value2, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type1_id not between", value1, value2, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNull() {
            addCriterion("product_type2_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNotNull() {
            addCriterion("product_type2_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdEqualTo(Integer value) {
            addCriterion("product_type2_id =", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotEqualTo(Integer value) {
            addCriterion("product_type2_id <>", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThan(Integer value) {
            addCriterion("product_type2_id >", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id >=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThan(Integer value) {
            addCriterion("product_type2_id <", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id <=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIn(List<Integer> values) {
            addCriterion("product_type2_id in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotIn(List<Integer> values) {
            addCriterion("product_type2_id not in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id between", value1, value2, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id not between", value1, value2, "productType2Id");
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

        public Criteria andArtNoIsNull() {
            addCriterion("art_no is null");
            return (Criteria) this;
        }

        public Criteria andArtNoIsNotNull() {
            addCriterion("art_no is not null");
            return (Criteria) this;
        }

        public Criteria andArtNoEqualTo(String value) {
            addCriterion("art_no =", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotEqualTo(String value) {
            addCriterion("art_no <>", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoGreaterThan(String value) {
            addCriterion("art_no >", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoGreaterThanOrEqualTo(String value) {
            addCriterion("art_no >=", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLessThan(String value) {
            addCriterion("art_no <", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLessThanOrEqualTo(String value) {
            addCriterion("art_no <=", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLike(String value) {
            addCriterion("art_no like", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotLike(String value) {
            addCriterion("art_no not like", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoIn(List<String> values) {
            addCriterion("art_no in", values, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotIn(List<String> values) {
            addCriterion("art_no not in", values, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoBetween(String value1, String value2) {
            addCriterion("art_no between", value1, value2, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotBetween(String value1, String value2) {
            addCriterion("art_no not between", value1, value2, "artNo");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNull() {
            addCriterion("suit_sex is null");
            return (Criteria) this;
        }

        public Criteria andSuitSexIsNotNull() {
            addCriterion("suit_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSuitSexEqualTo(String value) {
            addCriterion("suit_sex =", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotEqualTo(String value) {
            addCriterion("suit_sex <>", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThan(String value) {
            addCriterion("suit_sex >", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexGreaterThanOrEqualTo(String value) {
            addCriterion("suit_sex >=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThan(String value) {
            addCriterion("suit_sex <", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLessThanOrEqualTo(String value) {
            addCriterion("suit_sex <=", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexLike(String value) {
            addCriterion("suit_sex like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotLike(String value) {
            addCriterion("suit_sex not like", value, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexIn(List<String> values) {
            addCriterion("suit_sex in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotIn(List<String> values) {
            addCriterion("suit_sex not in", values, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexBetween(String value1, String value2) {
            addCriterion("suit_sex between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitSexNotBetween(String value1, String value2) {
            addCriterion("suit_sex not between", value1, value2, "suitSex");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNull() {
            addCriterion("suit_group is null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIsNotNull() {
            addCriterion("suit_group is not null");
            return (Criteria) this;
        }

        public Criteria andSuitGroupEqualTo(String value) {
            addCriterion("suit_group =", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotEqualTo(String value) {
            addCriterion("suit_group <>", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThan(String value) {
            addCriterion("suit_group >", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupGreaterThanOrEqualTo(String value) {
            addCriterion("suit_group >=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThan(String value) {
            addCriterion("suit_group <", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLessThanOrEqualTo(String value) {
            addCriterion("suit_group <=", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupLike(String value) {
            addCriterion("suit_group like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotLike(String value) {
            addCriterion("suit_group not like", value, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupIn(List<String> values) {
            addCriterion("suit_group in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotIn(List<String> values) {
            addCriterion("suit_group not in", values, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupBetween(String value1, String value2) {
            addCriterion("suit_group between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andSuitGroupNotBetween(String value1, String value2) {
            addCriterion("suit_group not between", value1, value2, "suitGroup");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNull() {
            addCriterion("season is null");
            return (Criteria) this;
        }

        public Criteria andSeasonIsNotNull() {
            addCriterion("season is not null");
            return (Criteria) this;
        }

        public Criteria andSeasonEqualTo(String value) {
            addCriterion("season =", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotEqualTo(String value) {
            addCriterion("season <>", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThan(String value) {
            addCriterion("season >", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonGreaterThanOrEqualTo(String value) {
            addCriterion("season >=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThan(String value) {
            addCriterion("season <", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLessThanOrEqualTo(String value) {
            addCriterion("season <=", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonLike(String value) {
            addCriterion("season like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotLike(String value) {
            addCriterion("season not like", value, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonIn(List<String> values) {
            addCriterion("season in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotIn(List<String> values) {
            addCriterion("season not in", values, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonBetween(String value1, String value2) {
            addCriterion("season between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andSeasonNotBetween(String value1, String value2) {
            addCriterion("season not between", value1, value2, "season");
            return (Criteria) this;
        }

        public Criteria andLimitBuyIsNull() {
            addCriterion("limit_buy is null");
            return (Criteria) this;
        }

        public Criteria andLimitBuyIsNotNull() {
            addCriterion("limit_buy is not null");
            return (Criteria) this;
        }

        public Criteria andLimitBuyEqualTo(Integer value) {
            addCriterion("limit_buy =", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyNotEqualTo(Integer value) {
            addCriterion("limit_buy <>", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyGreaterThan(Integer value) {
            addCriterion("limit_buy >", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_buy >=", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyLessThan(Integer value) {
            addCriterion("limit_buy <", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyLessThanOrEqualTo(Integer value) {
            addCriterion("limit_buy <=", value, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyIn(List<Integer> values) {
            addCriterion("limit_buy in", values, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyNotIn(List<Integer> values) {
            addCriterion("limit_buy not in", values, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyBetween(Integer value1, Integer value2) {
            addCriterion("limit_buy between", value1, value2, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andLimitBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_buy not between", value1, value2, "limitBuy");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdIsNull() {
            addCriterion("cs_templet_id is null");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdIsNotNull() {
            addCriterion("cs_templet_id is not null");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdEqualTo(Integer value) {
            addCriterion("cs_templet_id =", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdNotEqualTo(Integer value) {
            addCriterion("cs_templet_id <>", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdGreaterThan(Integer value) {
            addCriterion("cs_templet_id >", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cs_templet_id >=", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdLessThan(Integer value) {
            addCriterion("cs_templet_id <", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdLessThanOrEqualTo(Integer value) {
            addCriterion("cs_templet_id <=", value, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdIn(List<Integer> values) {
            addCriterion("cs_templet_id in", values, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdNotIn(List<Integer> values) {
            addCriterion("cs_templet_id not in", values, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdBetween(Integer value1, Integer value2) {
            addCriterion("cs_templet_id between", value1, value2, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andCsTempletIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cs_templet_id not between", value1, value2, "csTempletId");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIsNull() {
            addCriterion("remarks_color is null");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIsNotNull() {
            addCriterion("remarks_color is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksColorEqualTo(String value) {
            addCriterion("remarks_color =", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotEqualTo(String value) {
            addCriterion("remarks_color <>", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorGreaterThan(String value) {
            addCriterion("remarks_color >", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorGreaterThanOrEqualTo(String value) {
            addCriterion("remarks_color >=", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLessThan(String value) {
            addCriterion("remarks_color <", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLessThanOrEqualTo(String value) {
            addCriterion("remarks_color <=", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLike(String value) {
            addCriterion("remarks_color like", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotLike(String value) {
            addCriterion("remarks_color not like", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIn(List<String> values) {
            addCriterion("remarks_color in", values, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotIn(List<String> values) {
            addCriterion("remarks_color not in", values, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorBetween(String value1, String value2) {
            addCriterion("remarks_color between", value1, value2, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotBetween(String value1, String value2) {
            addCriterion("remarks_color not between", value1, value2, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNull() {
            addCriterion("sale_type is null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNotNull() {
            addCriterion("sale_type is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeEqualTo(String value) {
            addCriterion("sale_type =", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotEqualTo(String value) {
            addCriterion("sale_type <>", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThan(String value) {
            addCriterion("sale_type >", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sale_type >=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThan(String value) {
            addCriterion("sale_type <", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThanOrEqualTo(String value) {
            addCriterion("sale_type <=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLike(String value) {
            addCriterion("sale_type like", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotLike(String value) {
            addCriterion("sale_type not like", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIn(List<String> values) {
            addCriterion("sale_type in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotIn(List<String> values) {
            addCriterion("sale_type not in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeBetween(String value1, String value2) {
            addCriterion("sale_type between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotBetween(String value1, String value2) {
            addCriterion("sale_type not between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andDelDateIsNull() {
            addCriterion("del_date is null");
            return (Criteria) this;
        }

        public Criteria andDelDateIsNotNull() {
            addCriterion("del_date is not null");
            return (Criteria) this;
        }

        public Criteria andDelDateEqualTo(Date value) {
            addCriterion("del_date =", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateNotEqualTo(Date value) {
            addCriterion("del_date <>", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateGreaterThan(Date value) {
            addCriterion("del_date >", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateGreaterThanOrEqualTo(Date value) {
            addCriterion("del_date >=", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateLessThan(Date value) {
            addCriterion("del_date <", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateLessThanOrEqualTo(Date value) {
            addCriterion("del_date <=", value, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateIn(List<Date> values) {
            addCriterion("del_date in", values, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateNotIn(List<Date> values) {
            addCriterion("del_date not in", values, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateBetween(Date value1, Date value2) {
            addCriterion("del_date between", value1, value2, "delDate");
            return (Criteria) this;
        }

        public Criteria andDelDateNotBetween(Date value1, Date value2) {
            addCriterion("del_date not between", value1, value2, "delDate");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropIsNull() {
            addCriterion("is_single_prop is null");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropIsNotNull() {
            addCriterion("is_single_prop is not null");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropEqualTo(String value) {
            addCriterion("is_single_prop =", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropNotEqualTo(String value) {
            addCriterion("is_single_prop <>", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropGreaterThan(String value) {
            addCriterion("is_single_prop >", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropGreaterThanOrEqualTo(String value) {
            addCriterion("is_single_prop >=", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropLessThan(String value) {
            addCriterion("is_single_prop <", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropLessThanOrEqualTo(String value) {
            addCriterion("is_single_prop <=", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropLike(String value) {
            addCriterion("is_single_prop like", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropNotLike(String value) {
            addCriterion("is_single_prop not like", value, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropIn(List<String> values) {
            addCriterion("is_single_prop in", values, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropNotIn(List<String> values) {
            addCriterion("is_single_prop not in", values, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropBetween(String value1, String value2) {
            addCriterion("is_single_prop between", value1, value2, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andIsSinglePropNotBetween(String value1, String value2) {
            addCriterion("is_single_prop not between", value1, value2, "isSingleProp");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupIsNull() {
            addCriterion("shop_product_custom_type_id_group is null");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupIsNotNull() {
            addCriterion("shop_product_custom_type_id_group is not null");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupEqualTo(String value) {
            addCriterion("shop_product_custom_type_id_group =", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupNotEqualTo(String value) {
            addCriterion("shop_product_custom_type_id_group <>", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupGreaterThan(String value) {
            addCriterion("shop_product_custom_type_id_group >", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupGreaterThanOrEqualTo(String value) {
            addCriterion("shop_product_custom_type_id_group >=", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupLessThan(String value) {
            addCriterion("shop_product_custom_type_id_group <", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupLessThanOrEqualTo(String value) {
            addCriterion("shop_product_custom_type_id_group <=", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupLike(String value) {
            addCriterion("shop_product_custom_type_id_group like", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupNotLike(String value) {
            addCriterion("shop_product_custom_type_id_group not like", value, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupIn(List<String> values) {
            addCriterion("shop_product_custom_type_id_group in", values, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupNotIn(List<String> values) {
            addCriterion("shop_product_custom_type_id_group not in", values, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupBetween(String value1, String value2) {
            addCriterion("shop_product_custom_type_id_group between", value1, value2, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andShopProductCustomTypeIdGroupNotBetween(String value1, String value2) {
            addCriterion("shop_product_custom_type_id_group not between", value1, value2, "shopProductCustomTypeIdGroup");
            return (Criteria) this;
        }

        public Criteria andWeightsIsNull() {
            addCriterion("weights is null");
            return (Criteria) this;
        }

        public Criteria andWeightsIsNotNull() {
            addCriterion("weights is not null");
            return (Criteria) this;
        }

        public Criteria andWeightsEqualTo(Integer value) {
            addCriterion("weights =", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotEqualTo(Integer value) {
            addCriterion("weights <>", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsGreaterThan(Integer value) {
            addCriterion("weights >", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsGreaterThanOrEqualTo(Integer value) {
            addCriterion("weights >=", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsLessThan(Integer value) {
            addCriterion("weights <", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsLessThanOrEqualTo(Integer value) {
            addCriterion("weights <=", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsIn(List<Integer> values) {
            addCriterion("weights in", values, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotIn(List<Integer> values) {
            addCriterion("weights not in", values, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsBetween(Integer value1, Integer value2) {
            addCriterion("weights between", value1, value2, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotBetween(Integer value1, Integer value2) {
            addCriterion("weights not between", value1, value2, "weights");
            return (Criteria) this;
        }

        public Criteria andIsActivityIsNull() {
            addCriterion("is_activity is null");
            return (Criteria) this;
        }

        public Criteria andIsActivityIsNotNull() {
            addCriterion("is_activity is not null");
            return (Criteria) this;
        }

        public Criteria andIsActivityEqualTo(String value) {
            addCriterion("is_activity =", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotEqualTo(String value) {
            addCriterion("is_activity <>", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityGreaterThan(String value) {
            addCriterion("is_activity >", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityGreaterThanOrEqualTo(String value) {
            addCriterion("is_activity >=", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLessThan(String value) {
            addCriterion("is_activity <", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLessThanOrEqualTo(String value) {
            addCriterion("is_activity <=", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityLike(String value) {
            addCriterion("is_activity like", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotLike(String value) {
            addCriterion("is_activity not like", value, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityIn(List<String> values) {
            addCriterion("is_activity in", values, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotIn(List<String> values) {
            addCriterion("is_activity not in", values, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityBetween(String value1, String value2) {
            addCriterion("is_activity between", value1, value2, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsActivityNotBetween(String value1, String value2) {
            addCriterion("is_activity not between", value1, value2, "isActivity");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("is_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("is_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(String value) {
            addCriterion("is_show =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(String value) {
            addCriterion("is_show <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(String value) {
            addCriterion("is_show >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(String value) {
            addCriterion("is_show >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(String value) {
            addCriterion("is_show <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(String value) {
            addCriterion("is_show <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLike(String value) {
            addCriterion("is_show like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotLike(String value) {
            addCriterion("is_show not like", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<String> values) {
            addCriterion("is_show in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<String> values) {
            addCriterion("is_show not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(String value1, String value2) {
            addCriterion("is_show between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(String value1, String value2) {
            addCriterion("is_show not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdIsNull() {
            addCriterion("min_sale_price_item_id is null");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdIsNotNull() {
            addCriterion("min_sale_price_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdEqualTo(Integer value) {
            addCriterion("min_sale_price_item_id =", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdNotEqualTo(Integer value) {
            addCriterion("min_sale_price_item_id <>", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdGreaterThan(Integer value) {
            addCriterion("min_sale_price_item_id >", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_sale_price_item_id >=", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdLessThan(Integer value) {
            addCriterion("min_sale_price_item_id <", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("min_sale_price_item_id <=", value, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdIn(List<Integer> values) {
            addCriterion("min_sale_price_item_id in", values, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdNotIn(List<Integer> values) {
            addCriterion("min_sale_price_item_id not in", values, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdBetween(Integer value1, Integer value2) {
            addCriterion("min_sale_price_item_id between", value1, value2, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("min_sale_price_item_id not between", value1, value2, "minSalePriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdIsNull() {
            addCriterion("min_price_item_id is null");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdIsNotNull() {
            addCriterion("min_price_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdEqualTo(Integer value) {
            addCriterion("min_price_item_id =", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdNotEqualTo(Integer value) {
            addCriterion("min_price_item_id <>", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdGreaterThan(Integer value) {
            addCriterion("min_price_item_id >", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_price_item_id >=", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdLessThan(Integer value) {
            addCriterion("min_price_item_id <", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("min_price_item_id <=", value, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdIn(List<Integer> values) {
            addCriterion("min_price_item_id in", values, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdNotIn(List<Integer> values) {
            addCriterion("min_price_item_id not in", values, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdBetween(Integer value1, Integer value2) {
            addCriterion("min_price_item_id between", value1, value2, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinPriceItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("min_price_item_id not between", value1, value2, "minPriceItemId");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceIsNull() {
            addCriterion("min_mall_price is null");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceIsNotNull() {
            addCriterion("min_mall_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceEqualTo(BigDecimal value) {
            addCriterion("min_mall_price =", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceNotEqualTo(BigDecimal value) {
            addCriterion("min_mall_price <>", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceGreaterThan(BigDecimal value) {
            addCriterion("min_mall_price >", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_mall_price >=", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceLessThan(BigDecimal value) {
            addCriterion("min_mall_price <", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_mall_price <=", value, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceIn(List<BigDecimal> values) {
            addCriterion("min_mall_price in", values, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceNotIn(List<BigDecimal> values) {
            addCriterion("min_mall_price not in", values, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_mall_price between", value1, value2, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinMallPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_mall_price not between", value1, value2, "minMallPrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceIsNull() {
            addCriterion("min_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceIsNotNull() {
            addCriterion("min_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceEqualTo(BigDecimal value) {
            addCriterion("min_sale_price =", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("min_sale_price <>", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceGreaterThan(BigDecimal value) {
            addCriterion("min_sale_price >", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_sale_price >=", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceLessThan(BigDecimal value) {
            addCriterion("min_sale_price <", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_sale_price <=", value, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceIn(List<BigDecimal> values) {
            addCriterion("min_sale_price in", values, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("min_sale_price not in", values, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_sale_price between", value1, value2, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_sale_price not between", value1, value2, "minSalePrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceIsNull() {
            addCriterion("min_tag_price is null");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceIsNotNull() {
            addCriterion("min_tag_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceEqualTo(BigDecimal value) {
            addCriterion("min_tag_price =", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceNotEqualTo(BigDecimal value) {
            addCriterion("min_tag_price <>", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceGreaterThan(BigDecimal value) {
            addCriterion("min_tag_price >", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_tag_price >=", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceLessThan(BigDecimal value) {
            addCriterion("min_tag_price <", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_tag_price <=", value, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceIn(List<BigDecimal> values) {
            addCriterion("min_tag_price in", values, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceNotIn(List<BigDecimal> values) {
            addCriterion("min_tag_price not in", values, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_tag_price between", value1, value2, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andMinTagPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_tag_price not between", value1, value2, "minTagPrice");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNull() {
            addCriterion("sale_weight is null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIsNotNull() {
            addCriterion("sale_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSaleWeightEqualTo(Integer value) {
            addCriterion("sale_weight =", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotEqualTo(Integer value) {
            addCriterion("sale_weight <>", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThan(Integer value) {
            addCriterion("sale_weight >", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_weight >=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThan(Integer value) {
            addCriterion("sale_weight <", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightLessThanOrEqualTo(Integer value) {
            addCriterion("sale_weight <=", value, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightIn(List<Integer> values) {
            addCriterion("sale_weight in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotIn(List<Integer> values) {
            addCriterion("sale_weight not in", values, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightBetween(Integer value1, Integer value2) {
            addCriterion("sale_weight between", value1, value2, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andSaleWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_weight not between", value1, value2, "saleWeight");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIsNull() {
            addCriterion("freight_template_id is null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIsNotNull() {
            addCriterion("freight_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdEqualTo(Integer value) {
            addCriterion("freight_template_id =", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotEqualTo(Integer value) {
            addCriterion("freight_template_id <>", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThan(Integer value) {
            addCriterion("freight_template_id >", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_template_id >=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThan(Integer value) {
            addCriterion("freight_template_id <", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("freight_template_id <=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIn(List<Integer> values) {
            addCriterion("freight_template_id in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotIn(List<Integer> values) {
            addCriterion("freight_template_id not in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("freight_template_id between", value1, value2, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_template_id not between", value1, value2, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andIsShopShowIsNull() {
            addCriterion("is_shop_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShopShowIsNotNull() {
            addCriterion("is_shop_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShopShowEqualTo(String value) {
            addCriterion("is_shop_show =", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowNotEqualTo(String value) {
            addCriterion("is_shop_show <>", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowGreaterThan(String value) {
            addCriterion("is_shop_show >", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowGreaterThanOrEqualTo(String value) {
            addCriterion("is_shop_show >=", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowLessThan(String value) {
            addCriterion("is_shop_show <", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowLessThanOrEqualTo(String value) {
            addCriterion("is_shop_show <=", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowLike(String value) {
            addCriterion("is_shop_show like", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowNotLike(String value) {
            addCriterion("is_shop_show not like", value, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowIn(List<String> values) {
            addCriterion("is_shop_show in", values, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowNotIn(List<String> values) {
            addCriterion("is_shop_show not in", values, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowBetween(String value1, String value2) {
            addCriterion("is_shop_show between", value1, value2, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andIsShopShowNotBetween(String value1, String value2) {
            addCriterion("is_shop_show not between", value1, value2, "isShopShow");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIsNull() {
            addCriterion("search_fields is null");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIsNotNull() {
            addCriterion("search_fields is not null");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsEqualTo(String value) {
            addCriterion("search_fields =", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotEqualTo(String value) {
            addCriterion("search_fields <>", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsGreaterThan(String value) {
            addCriterion("search_fields >", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsGreaterThanOrEqualTo(String value) {
            addCriterion("search_fields >=", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLessThan(String value) {
            addCriterion("search_fields <", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLessThanOrEqualTo(String value) {
            addCriterion("search_fields <=", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLike(String value) {
            addCriterion("search_fields like", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotLike(String value) {
            addCriterion("search_fields not like", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIn(List<String> values) {
            addCriterion("search_fields in", values, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotIn(List<String> values) {
            addCriterion("search_fields not in", values, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsBetween(String value1, String value2) {
            addCriterion("search_fields between", value1, value2, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotBetween(String value1, String value2) {
            addCriterion("search_fields not between", value1, value2, "searchFields");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoIsNull() {
            addCriterion("third_platform_coupon_info is null");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoIsNotNull() {
            addCriterion("third_platform_coupon_info is not null");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoEqualTo(String value) {
            addCriterion("third_platform_coupon_info =", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoNotEqualTo(String value) {
            addCriterion("third_platform_coupon_info <>", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoGreaterThan(String value) {
            addCriterion("third_platform_coupon_info >", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoGreaterThanOrEqualTo(String value) {
            addCriterion("third_platform_coupon_info >=", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoLessThan(String value) {
            addCriterion("third_platform_coupon_info <", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoLessThanOrEqualTo(String value) {
            addCriterion("third_platform_coupon_info <=", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoLike(String value) {
            addCriterion("third_platform_coupon_info like", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoNotLike(String value) {
            addCriterion("third_platform_coupon_info not like", value, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoIn(List<String> values) {
            addCriterion("third_platform_coupon_info in", values, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoNotIn(List<String> values) {
            addCriterion("third_platform_coupon_info not in", values, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoBetween(String value1, String value2) {
            addCriterion("third_platform_coupon_info between", value1, value2, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andThirdPlatformCouponInfoNotBetween(String value1, String value2) {
            addCriterion("third_platform_coupon_info not between", value1, value2, "thirdPlatformCouponInfo");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNull() {
            addCriterion("comment_weight is null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIsNotNull() {
            addCriterion("comment_weight is not null");
            return (Criteria) this;
        }

        public Criteria andCommentWeightEqualTo(Integer value) {
            addCriterion("comment_weight =", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotEqualTo(Integer value) {
            addCriterion("comment_weight <>", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThan(Integer value) {
            addCriterion("comment_weight >", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_weight >=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThan(Integer value) {
            addCriterion("comment_weight <", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightLessThanOrEqualTo(Integer value) {
            addCriterion("comment_weight <=", value, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightIn(List<Integer> values) {
            addCriterion("comment_weight in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotIn(List<Integer> values) {
            addCriterion("comment_weight not in", values, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andCommentWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_weight not between", value1, value2, "commentWeight");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIsNull() {
            addCriterion("svip_discount is null");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIsNotNull() {
            addCriterion("svip_discount is not null");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountEqualTo(BigDecimal value) {
            addCriterion("svip_discount =", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotEqualTo(BigDecimal value) {
            addCriterion("svip_discount <>", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountGreaterThan(BigDecimal value) {
            addCriterion("svip_discount >", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("svip_discount >=", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountLessThan(BigDecimal value) {
            addCriterion("svip_discount <", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("svip_discount <=", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIn(List<BigDecimal> values) {
            addCriterion("svip_discount in", values, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotIn(List<BigDecimal> values) {
            addCriterion("svip_discount not in", values, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("svip_discount between", value1, value2, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("svip_discount not between", value1, value2, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIsNull() {
            addCriterion("is_return_7days is null");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIsNotNull() {
            addCriterion("is_return_7days is not null");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysEqualTo(String value) {
            addCriterion("is_return_7days =", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotEqualTo(String value) {
            addCriterion("is_return_7days <>", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysGreaterThan(String value) {
            addCriterion("is_return_7days >", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysGreaterThanOrEqualTo(String value) {
            addCriterion("is_return_7days >=", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLessThan(String value) {
            addCriterion("is_return_7days <", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLessThanOrEqualTo(String value) {
            addCriterion("is_return_7days <=", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysLike(String value) {
            addCriterion("is_return_7days like", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotLike(String value) {
            addCriterion("is_return_7days not like", value, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysIn(List<String> values) {
            addCriterion("is_return_7days in", values, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotIn(List<String> values) {
            addCriterion("is_return_7days not in", values, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysBetween(String value1, String value2) {
            addCriterion("is_return_7days between", value1, value2, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andIsReturn7daysNotBetween(String value1, String value2) {
            addCriterion("is_return_7days not between", value1, value2, "isReturn7days");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIsNull() {
            addCriterion("shelf_life is null");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIsNotNull() {
            addCriterion("shelf_life is not null");
            return (Criteria) this;
        }

        public Criteria andShelfLifeEqualTo(String value) {
            addCriterion("shelf_life =", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotEqualTo(String value) {
            addCriterion("shelf_life <>", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeGreaterThan(String value) {
            addCriterion("shelf_life >", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeGreaterThanOrEqualTo(String value) {
            addCriterion("shelf_life >=", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeLessThan(String value) {
            addCriterion("shelf_life <", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeLessThanOrEqualTo(String value) {
            addCriterion("shelf_life <=", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeLike(String value) {
            addCriterion("shelf_life like", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotLike(String value) {
            addCriterion("shelf_life not like", value, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeIn(List<String> values) {
            addCriterion("shelf_life in", values, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotIn(List<String> values) {
            addCriterion("shelf_life not in", values, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeBetween(String value1, String value2) {
            addCriterion("shelf_life between", value1, value2, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andShelfLifeNotBetween(String value1, String value2) {
            addCriterion("shelf_life not between", value1, value2, "shelfLife");
            return (Criteria) this;
        }

        public Criteria andCccNoIsNull() {
            addCriterion("ccc_no is null");
            return (Criteria) this;
        }

        public Criteria andCccNoIsNotNull() {
            addCriterion("ccc_no is not null");
            return (Criteria) this;
        }

        public Criteria andCccNoEqualTo(String value) {
            addCriterion("ccc_no =", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoNotEqualTo(String value) {
            addCriterion("ccc_no <>", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoGreaterThan(String value) {
            addCriterion("ccc_no >", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoGreaterThanOrEqualTo(String value) {
            addCriterion("ccc_no >=", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoLessThan(String value) {
            addCriterion("ccc_no <", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoLessThanOrEqualTo(String value) {
            addCriterion("ccc_no <=", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoLike(String value) {
            addCriterion("ccc_no like", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoNotLike(String value) {
            addCriterion("ccc_no not like", value, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoIn(List<String> values) {
            addCriterion("ccc_no in", values, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoNotIn(List<String> values) {
            addCriterion("ccc_no not in", values, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoBetween(String value1, String value2) {
            addCriterion("ccc_no between", value1, value2, "cccNo");
            return (Criteria) this;
        }

        public Criteria andCccNoNotBetween(String value1, String value2) {
            addCriterion("ccc_no not between", value1, value2, "cccNo");
            return (Criteria) this;
        }

        public Criteria andYearOfListingIsNull() {
            addCriterion("year_of_listing is null");
            return (Criteria) this;
        }

        public Criteria andYearOfListingIsNotNull() {
            addCriterion("year_of_listing is not null");
            return (Criteria) this;
        }

        public Criteria andYearOfListingEqualTo(String value) {
            addCriterion("year_of_listing =", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingNotEqualTo(String value) {
            addCriterion("year_of_listing <>", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingGreaterThan(String value) {
            addCriterion("year_of_listing >", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingGreaterThanOrEqualTo(String value) {
            addCriterion("year_of_listing >=", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingLessThan(String value) {
            addCriterion("year_of_listing <", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingLessThanOrEqualTo(String value) {
            addCriterion("year_of_listing <=", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingLike(String value) {
            addCriterion("year_of_listing like", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingNotLike(String value) {
            addCriterion("year_of_listing not like", value, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingIn(List<String> values) {
            addCriterion("year_of_listing in", values, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingNotIn(List<String> values) {
            addCriterion("year_of_listing not in", values, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingBetween(String value1, String value2) {
            addCriterion("year_of_listing between", value1, value2, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andYearOfListingNotBetween(String value1, String value2) {
            addCriterion("year_of_listing not between", value1, value2, "yearOfListing");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesIsNull() {
            addCriterion("virtual_sales is null");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesIsNotNull() {
            addCriterion("virtual_sales is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesEqualTo(Integer value) {
            addCriterion("virtual_sales =", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesNotEqualTo(Integer value) {
            addCriterion("virtual_sales <>", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesGreaterThan(Integer value) {
            addCriterion("virtual_sales >", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesGreaterThanOrEqualTo(Integer value) {
            addCriterion("virtual_sales >=", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesLessThan(Integer value) {
            addCriterion("virtual_sales <", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesLessThanOrEqualTo(Integer value) {
            addCriterion("virtual_sales <=", value, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesIn(List<Integer> values) {
            addCriterion("virtual_sales in", values, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesNotIn(List<Integer> values) {
            addCriterion("virtual_sales not in", values, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesBetween(Integer value1, Integer value2) {
            addCriterion("virtual_sales between", value1, value2, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualSalesNotBetween(Integer value1, Integer value2) {
            addCriterion("virtual_sales not between", value1, value2, "virtualSales");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersIsNull() {
            addCriterion("virtual_payers is null");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersIsNotNull() {
            addCriterion("virtual_payers is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersEqualTo(Integer value) {
            addCriterion("virtual_payers =", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersNotEqualTo(Integer value) {
            addCriterion("virtual_payers <>", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersGreaterThan(Integer value) {
            addCriterion("virtual_payers >", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersGreaterThanOrEqualTo(Integer value) {
            addCriterion("virtual_payers >=", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersLessThan(Integer value) {
            addCriterion("virtual_payers <", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersLessThanOrEqualTo(Integer value) {
            addCriterion("virtual_payers <=", value, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersIn(List<Integer> values) {
            addCriterion("virtual_payers in", values, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersNotIn(List<Integer> values) {
            addCriterion("virtual_payers not in", values, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersBetween(Integer value1, Integer value2) {
            addCriterion("virtual_payers between", value1, value2, "virtualPayers");
            return (Criteria) this;
        }

        public Criteria andVirtualPayersNotBetween(Integer value1, Integer value2) {
            addCriterion("virtual_payers not between", value1, value2, "virtualPayers");
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

        public Criteria andRecommendRemarksIsNull() {
            addCriterion("recommend_remarks is null");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksIsNotNull() {
            addCriterion("recommend_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksEqualTo(String value) {
            addCriterion("recommend_remarks =", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksNotEqualTo(String value) {
            addCriterion("recommend_remarks <>", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksGreaterThan(String value) {
            addCriterion("recommend_remarks >", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_remarks >=", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksLessThan(String value) {
            addCriterion("recommend_remarks <", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksLessThanOrEqualTo(String value) {
            addCriterion("recommend_remarks <=", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksLike(String value) {
            addCriterion("recommend_remarks like", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksNotLike(String value) {
            addCriterion("recommend_remarks not like", value, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksIn(List<String> values) {
            addCriterion("recommend_remarks in", values, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksNotIn(List<String> values) {
            addCriterion("recommend_remarks not in", values, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksBetween(String value1, String value2) {
            addCriterion("recommend_remarks between", value1, value2, "recommendRemarks");
            return (Criteria) this;
        }

        public Criteria andRecommendRemarksNotBetween(String value1, String value2) {
            addCriterion("recommend_remarks not between", value1, value2, "recommendRemarks");
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