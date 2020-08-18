package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntellectualPropertyRightExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public IntellectualPropertyRightExample() {
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

        public Criteria andObligeeIdIsNull() {
            addCriterion("obligee_id is null");
            return (Criteria) this;
        }

        public Criteria andObligeeIdIsNotNull() {
            addCriterion("obligee_id is not null");
            return (Criteria) this;
        }

        public Criteria andObligeeIdEqualTo(Integer value) {
            addCriterion("obligee_id =", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdNotEqualTo(Integer value) {
            addCriterion("obligee_id <>", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdGreaterThan(Integer value) {
            addCriterion("obligee_id >", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("obligee_id >=", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdLessThan(Integer value) {
            addCriterion("obligee_id <", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("obligee_id <=", value, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdIn(List<Integer> values) {
            addCriterion("obligee_id in", values, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdNotIn(List<Integer> values) {
            addCriterion("obligee_id not in", values, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdBetween(Integer value1, Integer value2) {
            addCriterion("obligee_id between", value1, value2, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andObligeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("obligee_id not between", value1, value2, "obligeeId");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeIsNull() {
            addCriterion("property_right_type is null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeIsNotNull() {
            addCriterion("property_right_type is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeEqualTo(String value) {
            addCriterion("property_right_type =", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeNotEqualTo(String value) {
            addCriterion("property_right_type <>", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeGreaterThan(String value) {
            addCriterion("property_right_type >", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeGreaterThanOrEqualTo(String value) {
            addCriterion("property_right_type >=", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeLessThan(String value) {
            addCriterion("property_right_type <", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeLessThanOrEqualTo(String value) {
            addCriterion("property_right_type <=", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeLike(String value) {
            addCriterion("property_right_type like", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeNotLike(String value) {
            addCriterion("property_right_type not like", value, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeIn(List<String> values) {
            addCriterion("property_right_type in", values, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeNotIn(List<String> values) {
            addCriterion("property_right_type not in", values, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeBetween(String value1, String value2) {
            addCriterion("property_right_type between", value1, value2, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightTypeNotBetween(String value1, String value2) {
            addCriterion("property_right_type not between", value1, value2, "propertyRightType");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongIsNull() {
            addCriterion("property_right_belong is null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongIsNotNull() {
            addCriterion("property_right_belong is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongEqualTo(String value) {
            addCriterion("property_right_belong =", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongNotEqualTo(String value) {
            addCriterion("property_right_belong <>", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongGreaterThan(String value) {
            addCriterion("property_right_belong >", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongGreaterThanOrEqualTo(String value) {
            addCriterion("property_right_belong >=", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongLessThan(String value) {
            addCriterion("property_right_belong <", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongLessThanOrEqualTo(String value) {
            addCriterion("property_right_belong <=", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongLike(String value) {
            addCriterion("property_right_belong like", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongNotLike(String value) {
            addCriterion("property_right_belong not like", value, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongIn(List<String> values) {
            addCriterion("property_right_belong in", values, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongNotIn(List<String> values) {
            addCriterion("property_right_belong not in", values, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongBetween(String value1, String value2) {
            addCriterion("property_right_belong between", value1, value2, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightBelongNotBetween(String value1, String value2) {
            addCriterion("property_right_belong not between", value1, value2, "propertyRightBelong");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameIsNull() {
            addCriterion("property_right_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameIsNotNull() {
            addCriterion("property_right_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameEqualTo(String value) {
            addCriterion("property_right_name =", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameNotEqualTo(String value) {
            addCriterion("property_right_name <>", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameGreaterThan(String value) {
            addCriterion("property_right_name >", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_right_name >=", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameLessThan(String value) {
            addCriterion("property_right_name <", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameLessThanOrEqualTo(String value) {
            addCriterion("property_right_name <=", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameLike(String value) {
            addCriterion("property_right_name like", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameNotLike(String value) {
            addCriterion("property_right_name not like", value, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameIn(List<String> values) {
            addCriterion("property_right_name in", values, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameNotIn(List<String> values) {
            addCriterion("property_right_name not in", values, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameBetween(String value1, String value2) {
            addCriterion("property_right_name between", value1, value2, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andPropertyRightNameNotBetween(String value1, String value2) {
            addCriterion("property_right_name not between", value1, value2, "propertyRightName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameIsNull() {
            addCriterion("obligee_name is null");
            return (Criteria) this;
        }

        public Criteria andObligeeNameIsNotNull() {
            addCriterion("obligee_name is not null");
            return (Criteria) this;
        }

        public Criteria andObligeeNameEqualTo(String value) {
            addCriterion("obligee_name =", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameNotEqualTo(String value) {
            addCriterion("obligee_name <>", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameGreaterThan(String value) {
            addCriterion("obligee_name >", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("obligee_name >=", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameLessThan(String value) {
            addCriterion("obligee_name <", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameLessThanOrEqualTo(String value) {
            addCriterion("obligee_name <=", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameLike(String value) {
            addCriterion("obligee_name like", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameNotLike(String value) {
            addCriterion("obligee_name not like", value, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameIn(List<String> values) {
            addCriterion("obligee_name in", values, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameNotIn(List<String> values) {
            addCriterion("obligee_name not in", values, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameBetween(String value1, String value2) {
            addCriterion("obligee_name between", value1, value2, "obligeeName");
            return (Criteria) this;
        }

        public Criteria andObligeeNameNotBetween(String value1, String value2) {
            addCriterion("obligee_name not between", value1, value2, "obligeeName");
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