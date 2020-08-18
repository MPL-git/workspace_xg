package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WoRkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public WoRkExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("staff_id is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Integer value) {
            addCriterion("staff_id =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Integer value) {
            addCriterion("staff_id <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Integer value) {
            addCriterion("staff_id >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("staff_id >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Integer value) {
            addCriterion("staff_id <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("staff_id <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Integer> values) {
            addCriterion("staff_id in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Integer> values) {
            addCriterion("staff_id not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("staff_id between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("staff_id not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNull() {
            addCriterion("work_type is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNotNull() {
            addCriterion("work_type is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeEqualTo(String value) {
            addCriterion("work_type =", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotEqualTo(String value) {
            addCriterion("work_type <>", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThan(String value) {
            addCriterion("work_type >", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("work_type >=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThan(String value) {
            addCriterion("work_type <", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThanOrEqualTo(String value) {
            addCriterion("work_type <=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLike(String value) {
            addCriterion("work_type like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotLike(String value) {
            addCriterion("work_type not like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIn(List<String> values) {
            addCriterion("work_type in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotIn(List<String> values) {
            addCriterion("work_type not in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeBetween(String value1, String value2) {
            addCriterion("work_type between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotBetween(String value1, String value2) {
            addCriterion("work_type not between", value1, value2, "workType");
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

        public Criteria andStatusBehaviorIsNull() {
            addCriterion("status_behavior is null");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorIsNotNull() {
            addCriterion("status_behavior is not null");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorEqualTo(String value) {
            addCriterion("status_behavior =", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorNotEqualTo(String value) {
            addCriterion("status_behavior <>", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorGreaterThan(String value) {
            addCriterion("status_behavior >", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorGreaterThanOrEqualTo(String value) {
            addCriterion("status_behavior >=", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorLessThan(String value) {
            addCriterion("status_behavior <", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorLessThanOrEqualTo(String value) {
            addCriterion("status_behavior <=", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorLike(String value) {
            addCriterion("status_behavior like", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorNotLike(String value) {
            addCriterion("status_behavior not like", value, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorIn(List<String> values) {
            addCriterion("status_behavior in", values, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorNotIn(List<String> values) {
            addCriterion("status_behavior not in", values, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorBetween(String value1, String value2) {
            addCriterion("status_behavior between", value1, value2, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andStatusBehaviorNotBetween(String value1, String value2) {
            addCriterion("status_behavior not between", value1, value2, "statusBehavior");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeIsNull() {
            addCriterion("urgent_degree is null");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeIsNotNull() {
            addCriterion("urgent_degree is not null");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeEqualTo(String value) {
            addCriterion("urgent_degree =", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeNotEqualTo(String value) {
            addCriterion("urgent_degree <>", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeGreaterThan(String value) {
            addCriterion("urgent_degree >", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("urgent_degree >=", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeLessThan(String value) {
            addCriterion("urgent_degree <", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeLessThanOrEqualTo(String value) {
            addCriterion("urgent_degree <=", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeLike(String value) {
            addCriterion("urgent_degree like", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeNotLike(String value) {
            addCriterion("urgent_degree not like", value, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeIn(List<String> values) {
            addCriterion("urgent_degree in", values, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeNotIn(List<String> values) {
            addCriterion("urgent_degree not in", values, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeBetween(String value1, String value2) {
            addCriterion("urgent_degree between", value1, value2, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andUrgentDegreeNotBetween(String value1, String value2) {
            addCriterion("urgent_degree not between", value1, value2, "urgentDegree");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNull() {
            addCriterion("close_reason is null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNotNull() {
            addCriterion("close_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonEqualTo(String value) {
            addCriterion("close_reason =", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotEqualTo(String value) {
            addCriterion("close_reason <>", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThan(String value) {
            addCriterion("close_reason >", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("close_reason >=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThan(String value) {
            addCriterion("close_reason <", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThanOrEqualTo(String value) {
            addCriterion("close_reason <=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLike(String value) {
            addCriterion("close_reason like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotLike(String value) {
            addCriterion("close_reason not like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIn(List<String> values) {
            addCriterion("close_reason in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotIn(List<String> values) {
            addCriterion("close_reason not in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonBetween(String value1, String value2) {
            addCriterion("close_reason between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotBetween(String value1, String value2) {
            addCriterion("close_reason not between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andTitleContentIsNull() {
            addCriterion("title_content is null");
            return (Criteria) this;
        }

        public Criteria andTitleContentIsNotNull() {
            addCriterion("title_content is not null");
            return (Criteria) this;
        }

        public Criteria andTitleContentEqualTo(String value) {
            addCriterion("title_content =", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentNotEqualTo(String value) {
            addCriterion("title_content <>", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentGreaterThan(String value) {
            addCriterion("title_content >", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentGreaterThanOrEqualTo(String value) {
            addCriterion("title_content >=", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentLessThan(String value) {
            addCriterion("title_content <", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentLessThanOrEqualTo(String value) {
            addCriterion("title_content <=", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentLike(String value) {
            addCriterion("title_content like", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentNotLike(String value) {
            addCriterion("title_content not like", value, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentIn(List<String> values) {
            addCriterion("title_content in", values, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentNotIn(List<String> values) {
            addCriterion("title_content not in", values, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentBetween(String value1, String value2) {
            addCriterion("title_content between", value1, value2, "titleContent");
            return (Criteria) this;
        }

        public Criteria andTitleContentNotBetween(String value1, String value2) {
            addCriterion("title_content not between", value1, value2, "titleContent");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeIsNull() {
            addCriterion("relevant_type is null");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeIsNotNull() {
            addCriterion("relevant_type is not null");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeEqualTo(String value) {
            addCriterion("relevant_type =", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeNotEqualTo(String value) {
            addCriterion("relevant_type <>", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeGreaterThan(String value) {
            addCriterion("relevant_type >", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("relevant_type >=", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeLessThan(String value) {
            addCriterion("relevant_type <", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeLessThanOrEqualTo(String value) {
            addCriterion("relevant_type <=", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeLike(String value) {
            addCriterion("relevant_type like", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeNotLike(String value) {
            addCriterion("relevant_type not like", value, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeIn(List<String> values) {
            addCriterion("relevant_type in", values, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeNotIn(List<String> values) {
            addCriterion("relevant_type not in", values, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeBetween(String value1, String value2) {
            addCriterion("relevant_type between", value1, value2, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantTypeNotBetween(String value1, String value2) {
            addCriterion("relevant_type not between", value1, value2, "relevantType");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeIsNull() {
            addCriterion("relevant_code is null");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeIsNotNull() {
            addCriterion("relevant_code is not null");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeEqualTo(String value) {
            addCriterion("relevant_code =", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeNotEqualTo(String value) {
            addCriterion("relevant_code <>", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeGreaterThan(String value) {
            addCriterion("relevant_code >", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("relevant_code >=", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeLessThan(String value) {
            addCriterion("relevant_code <", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeLessThanOrEqualTo(String value) {
            addCriterion("relevant_code <=", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeLike(String value) {
            addCriterion("relevant_code like", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeNotLike(String value) {
            addCriterion("relevant_code not like", value, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeIn(List<String> values) {
            addCriterion("relevant_code in", values, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeNotIn(List<String> values) {
            addCriterion("relevant_code not in", values, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeBetween(String value1, String value2) {
            addCriterion("relevant_code between", value1, value2, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantCodeNotBetween(String value1, String value2) {
            addCriterion("relevant_code not between", value1, value2, "relevantCode");
            return (Criteria) this;
        }

        public Criteria andRelevantIdIsNull() {
            addCriterion("relevant_id is null");
            return (Criteria) this;
        }

        public Criteria andRelevantIdIsNotNull() {
            addCriterion("relevant_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelevantIdEqualTo(Integer value) {
            addCriterion("relevant_id =", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdNotEqualTo(Integer value) {
            addCriterion("relevant_id <>", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdGreaterThan(Integer value) {
            addCriterion("relevant_id >", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relevant_id >=", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdLessThan(Integer value) {
            addCriterion("relevant_id <", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdLessThanOrEqualTo(Integer value) {
            addCriterion("relevant_id <=", value, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdIn(List<Integer> values) {
            addCriterion("relevant_id in", values, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdNotIn(List<Integer> values) {
            addCriterion("relevant_id not in", values, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdBetween(Integer value1, Integer value2) {
            addCriterion("relevant_id between", value1, value2, "relevantId");
            return (Criteria) this;
        }

        public Criteria andRelevantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relevant_id not between", value1, value2, "relevantId");
            return (Criteria) this;
        }

        public Criteria andDescribeContentIsNull() {
            addCriterion("describe_content is null");
            return (Criteria) this;
        }

        public Criteria andDescribeContentIsNotNull() {
            addCriterion("describe_content is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeContentEqualTo(String value) {
            addCriterion("describe_content =", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentNotEqualTo(String value) {
            addCriterion("describe_content <>", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentGreaterThan(String value) {
            addCriterion("describe_content >", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentGreaterThanOrEqualTo(String value) {
            addCriterion("describe_content >=", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentLessThan(String value) {
            addCriterion("describe_content <", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentLessThanOrEqualTo(String value) {
            addCriterion("describe_content <=", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentLike(String value) {
            addCriterion("describe_content like", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentNotLike(String value) {
            addCriterion("describe_content not like", value, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentIn(List<String> values) {
            addCriterion("describe_content in", values, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentNotIn(List<String> values) {
            addCriterion("describe_content not in", values, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentBetween(String value1, String value2) {
            addCriterion("describe_content between", value1, value2, "describeContent");
            return (Criteria) this;
        }

        public Criteria andDescribeContentNotBetween(String value1, String value2) {
            addCriterion("describe_content not between", value1, value2, "describeContent");
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