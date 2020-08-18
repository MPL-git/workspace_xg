package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralGiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public IntegralGiveExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(String value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(String value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(String value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(String value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(String value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLike(String value) {
            addCriterion("group_id like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotLike(String value) {
            addCriterion("group_id not like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<String> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<String> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(String value1, String value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(String value1, String value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
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

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(Integer value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(Integer value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(Integer value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(Integer value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(Integer value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<Integer> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<Integer> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(Integer value1, Integer value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNull() {
            addCriterion("sub_order_code is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNotNull() {
            addCriterion("sub_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeEqualTo(String value) {
            addCriterion("sub_order_code =", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotEqualTo(String value) {
            addCriterion("sub_order_code <>", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThan(String value) {
            addCriterion("sub_order_code >", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_code >=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThan(String value) {
            addCriterion("sub_order_code <", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("sub_order_code <=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLike(String value) {
            addCriterion("sub_order_code like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotLike(String value) {
            addCriterion("sub_order_code not like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIn(List<String> values) {
            addCriterion("sub_order_code in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotIn(List<String> values) {
            addCriterion("sub_order_code not in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeBetween(String value1, String value2) {
            addCriterion("sub_order_code between", value1, value2, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotBetween(String value1, String value2) {
            addCriterion("sub_order_code not between", value1, value2, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeIsNull() {
            addCriterion("violate_order_code is null");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeIsNotNull() {
            addCriterion("violate_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeEqualTo(String value) {
            addCriterion("violate_order_code =", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeNotEqualTo(String value) {
            addCriterion("violate_order_code <>", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeGreaterThan(String value) {
            addCriterion("violate_order_code >", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("violate_order_code >=", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeLessThan(String value) {
            addCriterion("violate_order_code <", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("violate_order_code <=", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeLike(String value) {
            addCriterion("violate_order_code like", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeNotLike(String value) {
            addCriterion("violate_order_code not like", value, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeIn(List<String> values) {
            addCriterion("violate_order_code in", values, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeNotIn(List<String> values) {
            addCriterion("violate_order_code not in", values, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeBetween(String value1, String value2) {
            addCriterion("violate_order_code between", value1, value2, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andViolateOrderCodeNotBetween(String value1, String value2) {
            addCriterion("violate_order_code not between", value1, value2, "violateOrderCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeIsNull() {
            addCriterion("mcht_code is null");
            return (Criteria) this;
        }

        public Criteria andMchtCodeIsNotNull() {
            addCriterion("mcht_code is not null");
            return (Criteria) this;
        }

        public Criteria andMchtCodeEqualTo(String value) {
            addCriterion("mcht_code =", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotEqualTo(String value) {
            addCriterion("mcht_code <>", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeGreaterThan(String value) {
            addCriterion("mcht_code >", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_code >=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLessThan(String value) {
            addCriterion("mcht_code <", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLessThanOrEqualTo(String value) {
            addCriterion("mcht_code <=", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeLike(String value) {
            addCriterion("mcht_code like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotLike(String value) {
            addCriterion("mcht_code not like", value, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeIn(List<String> values) {
            addCriterion("mcht_code in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotIn(List<String> values) {
            addCriterion("mcht_code not in", values, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeBetween(String value1, String value2) {
            addCriterion("mcht_code between", value1, value2, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andMchtCodeNotBetween(String value1, String value2) {
            addCriterion("mcht_code not between", value1, value2, "mchtCode");
            return (Criteria) this;
        }

        public Criteria andCostBearIsNull() {
            addCriterion("cost_bear is null");
            return (Criteria) this;
        }

        public Criteria andCostBearIsNotNull() {
            addCriterion("cost_bear is not null");
            return (Criteria) this;
        }

        public Criteria andCostBearEqualTo(String value) {
            addCriterion("cost_bear =", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearNotEqualTo(String value) {
            addCriterion("cost_bear <>", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearGreaterThan(String value) {
            addCriterion("cost_bear >", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearGreaterThanOrEqualTo(String value) {
            addCriterion("cost_bear >=", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearLessThan(String value) {
            addCriterion("cost_bear <", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearLessThanOrEqualTo(String value) {
            addCriterion("cost_bear <=", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearLike(String value) {
            addCriterion("cost_bear like", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearNotLike(String value) {
            addCriterion("cost_bear not like", value, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearIn(List<String> values) {
            addCriterion("cost_bear in", values, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearNotIn(List<String> values) {
            addCriterion("cost_bear not in", values, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearBetween(String value1, String value2) {
            addCriterion("cost_bear between", value1, value2, "costBear");
            return (Criteria) this;
        }

        public Criteria andCostBearNotBetween(String value1, String value2) {
            addCriterion("cost_bear not between", value1, value2, "costBear");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductIsNull() {
            addCriterion("is_deposit_deduct is null");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductIsNotNull() {
            addCriterion("is_deposit_deduct is not null");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductEqualTo(String value) {
            addCriterion("is_deposit_deduct =", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductNotEqualTo(String value) {
            addCriterion("is_deposit_deduct <>", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductGreaterThan(String value) {
            addCriterion("is_deposit_deduct >", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductGreaterThanOrEqualTo(String value) {
            addCriterion("is_deposit_deduct >=", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductLessThan(String value) {
            addCriterion("is_deposit_deduct <", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductLessThanOrEqualTo(String value) {
            addCriterion("is_deposit_deduct <=", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductLike(String value) {
            addCriterion("is_deposit_deduct like", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductNotLike(String value) {
            addCriterion("is_deposit_deduct not like", value, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductIn(List<String> values) {
            addCriterion("is_deposit_deduct in", values, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductNotIn(List<String> values) {
            addCriterion("is_deposit_deduct not in", values, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductBetween(String value1, String value2) {
            addCriterion("is_deposit_deduct between", value1, value2, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andIsDepositDeductNotBetween(String value1, String value2) {
            addCriterion("is_deposit_deduct not between", value1, value2, "isDepositDeduct");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNull() {
            addCriterion("member_count is null");
            return (Criteria) this;
        }

        public Criteria andMemberCountIsNotNull() {
            addCriterion("member_count is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCountEqualTo(Integer value) {
            addCriterion("member_count =", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotEqualTo(Integer value) {
            addCriterion("member_count <>", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThan(Integer value) {
            addCriterion("member_count >", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_count >=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThan(Integer value) {
            addCriterion("member_count <", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountLessThanOrEqualTo(Integer value) {
            addCriterion("member_count <=", value, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountIn(List<Integer> values) {
            addCriterion("member_count in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotIn(List<Integer> values) {
            addCriterion("member_count not in", values, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountBetween(Integer value1, Integer value2) {
            addCriterion("member_count between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andMemberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("member_count not between", value1, value2, "memberCount");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdIsNull() {
            addCriterion("sms_template_id is null");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdIsNotNull() {
            addCriterion("sms_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdEqualTo(Integer value) {
            addCriterion("sms_template_id =", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdNotEqualTo(Integer value) {
            addCriterion("sms_template_id <>", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdGreaterThan(Integer value) {
            addCriterion("sms_template_id >", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_template_id >=", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdLessThan(Integer value) {
            addCriterion("sms_template_id <", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdLessThanOrEqualTo(Integer value) {
            addCriterion("sms_template_id <=", value, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdIn(List<Integer> values) {
            addCriterion("sms_template_id in", values, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdNotIn(List<Integer> values) {
            addCriterion("sms_template_id not in", values, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdBetween(Integer value1, Integer value2) {
            addCriterion("sms_template_id between", value1, value2, "smsTemplateId");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_template_id not between", value1, value2, "smsTemplateId");
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