package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterventionOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public InterventionOrderExample() {
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

        public Criteria andServiceOrderIdIsNull() {
            addCriterion("service_order_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdIsNotNull() {
            addCriterion("service_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdEqualTo(Integer value) {
            addCriterion("service_order_id =", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotEqualTo(Integer value) {
            addCriterion("service_order_id <>", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdGreaterThan(Integer value) {
            addCriterion("service_order_id >", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_order_id >=", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdLessThan(Integer value) {
            addCriterion("service_order_id <", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_order_id <=", value, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdIn(List<Integer> values) {
            addCriterion("service_order_id in", values, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotIn(List<Integer> values) {
            addCriterion("service_order_id not in", values, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("service_order_id between", value1, value2, "serviceOrderId");
            return (Criteria) this;
        }

        public Criteria andServiceOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_order_id not between", value1, value2, "serviceOrderId");
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

        public Criteria andInterventionCodeIsNull() {
            addCriterion("intervention_code is null");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeIsNotNull() {
            addCriterion("intervention_code is not null");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeEqualTo(String value) {
            addCriterion("intervention_code =", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeNotEqualTo(String value) {
            addCriterion("intervention_code <>", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeGreaterThan(String value) {
            addCriterion("intervention_code >", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("intervention_code >=", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeLessThan(String value) {
            addCriterion("intervention_code <", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeLessThanOrEqualTo(String value) {
            addCriterion("intervention_code <=", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeLike(String value) {
            addCriterion("intervention_code like", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeNotLike(String value) {
            addCriterion("intervention_code not like", value, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeIn(List<String> values) {
            addCriterion("intervention_code in", values, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeNotIn(List<String> values) {
            addCriterion("intervention_code not in", values, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeBetween(String value1, String value2) {
            addCriterion("intervention_code between", value1, value2, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andInterventionCodeNotBetween(String value1, String value2) {
            addCriterion("intervention_code not between", value1, value2, "interventionCode");
            return (Criteria) this;
        }

        public Criteria andProStatusIsNull() {
            addCriterion("pro_status is null");
            return (Criteria) this;
        }

        public Criteria andProStatusIsNotNull() {
            addCriterion("pro_status is not null");
            return (Criteria) this;
        }

        public Criteria andProStatusEqualTo(String value) {
            addCriterion("pro_status =", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotEqualTo(String value) {
            addCriterion("pro_status <>", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusGreaterThan(String value) {
            addCriterion("pro_status >", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pro_status >=", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLessThan(String value) {
            addCriterion("pro_status <", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLessThanOrEqualTo(String value) {
            addCriterion("pro_status <=", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusLike(String value) {
            addCriterion("pro_status like", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotLike(String value) {
            addCriterion("pro_status not like", value, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusIn(List<String> values) {
            addCriterion("pro_status in", values, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotIn(List<String> values) {
            addCriterion("pro_status not in", values, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusBetween(String value1, String value2) {
            addCriterion("pro_status between", value1, value2, "proStatus");
            return (Criteria) this;
        }

        public Criteria andProStatusNotBetween(String value1, String value2) {
            addCriterion("pro_status not between", value1, value2, "proStatus");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("contacts is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("contacts is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("contacts =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("contacts <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("contacts >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("contacts >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("contacts <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("contacts <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("contacts like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("contacts not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("contacts in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("contacts not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("contacts between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("contacts not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
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

        public Criteria andPlatformProcessorIsNull() {
            addCriterion("platform_processor is null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorIsNotNull() {
            addCriterion("platform_processor is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorEqualTo(Integer value) {
            addCriterion("platform_processor =", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotEqualTo(Integer value) {
            addCriterion("platform_processor <>", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorGreaterThan(Integer value) {
            addCriterion("platform_processor >", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_processor >=", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorLessThan(Integer value) {
            addCriterion("platform_processor <", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorLessThanOrEqualTo(Integer value) {
            addCriterion("platform_processor <=", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorIn(List<Integer> values) {
            addCriterion("platform_processor in", values, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotIn(List<Integer> values) {
            addCriterion("platform_processor not in", values, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorBetween(Integer value1, Integer value2) {
            addCriterion("platform_processor between", value1, value2, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_processor not between", value1, value2, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNull() {
            addCriterion("reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIsNotNull() {
            addCriterion("reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRejectReasonEqualTo(String value) {
            addCriterion("reject_reason =", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotEqualTo(String value) {
            addCriterion("reject_reason <>", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThan(String value) {
            addCriterion("reject_reason >", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reject_reason >=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThan(String value) {
            addCriterion("reject_reason <", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("reject_reason <=", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonLike(String value) {
            addCriterion("reject_reason like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotLike(String value) {
            addCriterion("reject_reason not like", value, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonIn(List<String> values) {
            addCriterion("reject_reason in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotIn(List<String> values) {
            addCriterion("reject_reason not in", values, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonBetween(String value1, String value2) {
            addCriterion("reject_reason between", value1, value2, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andRejectReasonNotBetween(String value1, String value2) {
            addCriterion("reject_reason not between", value1, value2, "rejectReason");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIsNull() {
            addCriterion("platform_remarks is null");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIsNotNull() {
            addCriterion("platform_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksEqualTo(String value) {
            addCriterion("platform_remarks =", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotEqualTo(String value) {
            addCriterion("platform_remarks <>", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksGreaterThan(String value) {
            addCriterion("platform_remarks >", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("platform_remarks >=", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLessThan(String value) {
            addCriterion("platform_remarks <", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLessThanOrEqualTo(String value) {
            addCriterion("platform_remarks <=", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLike(String value) {
            addCriterion("platform_remarks like", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotLike(String value) {
            addCriterion("platform_remarks not like", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIn(List<String> values) {
            addCriterion("platform_remarks in", values, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotIn(List<String> values) {
            addCriterion("platform_remarks not in", values, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksBetween(String value1, String value2) {
            addCriterion("platform_remarks between", value1, value2, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotBetween(String value1, String value2) {
            addCriterion("platform_remarks not between", value1, value2, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonIsNull() {
            addCriterion("director_reason is null");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonIsNotNull() {
            addCriterion("director_reason is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonEqualTo(String value) {
            addCriterion("director_reason =", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonNotEqualTo(String value) {
            addCriterion("director_reason <>", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonGreaterThan(String value) {
            addCriterion("director_reason >", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonGreaterThanOrEqualTo(String value) {
            addCriterion("director_reason >=", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonLessThan(String value) {
            addCriterion("director_reason <", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonLessThanOrEqualTo(String value) {
            addCriterion("director_reason <=", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonLike(String value) {
            addCriterion("director_reason like", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonNotLike(String value) {
            addCriterion("director_reason not like", value, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonIn(List<String> values) {
            addCriterion("director_reason in", values, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonNotIn(List<String> values) {
            addCriterion("director_reason not in", values, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonBetween(String value1, String value2) {
            addCriterion("director_reason between", value1, value2, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorReasonNotBetween(String value1, String value2) {
            addCriterion("director_reason not between", value1, value2, "directorReason");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksIsNull() {
            addCriterion("director_remarks is null");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksIsNotNull() {
            addCriterion("director_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksEqualTo(String value) {
            addCriterion("director_remarks =", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksNotEqualTo(String value) {
            addCriterion("director_remarks <>", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksGreaterThan(String value) {
            addCriterion("director_remarks >", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("director_remarks >=", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksLessThan(String value) {
            addCriterion("director_remarks <", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksLessThanOrEqualTo(String value) {
            addCriterion("director_remarks <=", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksLike(String value) {
            addCriterion("director_remarks like", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksNotLike(String value) {
            addCriterion("director_remarks not like", value, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksIn(List<String> values) {
            addCriterion("director_remarks in", values, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksNotIn(List<String> values) {
            addCriterion("director_remarks not in", values, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksBetween(String value1, String value2) {
            addCriterion("director_remarks between", value1, value2, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andDirectorRemarksNotBetween(String value1, String value2) {
            addCriterion("director_remarks not between", value1, value2, "directorRemarks");
            return (Criteria) this;
        }

        public Criteria andWinTypeIsNull() {
            addCriterion("win_type is null");
            return (Criteria) this;
        }

        public Criteria andWinTypeIsNotNull() {
            addCriterion("win_type is not null");
            return (Criteria) this;
        }

        public Criteria andWinTypeEqualTo(String value) {
            addCriterion("win_type =", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeNotEqualTo(String value) {
            addCriterion("win_type <>", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeGreaterThan(String value) {
            addCriterion("win_type >", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeGreaterThanOrEqualTo(String value) {
            addCriterion("win_type >=", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeLessThan(String value) {
            addCriterion("win_type <", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeLessThanOrEqualTo(String value) {
            addCriterion("win_type <=", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeLike(String value) {
            addCriterion("win_type like", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeNotLike(String value) {
            addCriterion("win_type not like", value, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeIn(List<String> values) {
            addCriterion("win_type in", values, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeNotIn(List<String> values) {
            addCriterion("win_type not in", values, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeBetween(String value1, String value2) {
            addCriterion("win_type between", value1, value2, "winType");
            return (Criteria) this;
        }

        public Criteria andWinTypeNotBetween(String value1, String value2) {
            addCriterion("win_type not between", value1, value2, "winType");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonIsNull() {
            addCriterion("client_result_reason is null");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonIsNotNull() {
            addCriterion("client_result_reason is not null");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonEqualTo(String value) {
            addCriterion("client_result_reason =", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonNotEqualTo(String value) {
            addCriterion("client_result_reason <>", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonGreaterThan(String value) {
            addCriterion("client_result_reason >", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonGreaterThanOrEqualTo(String value) {
            addCriterion("client_result_reason >=", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonLessThan(String value) {
            addCriterion("client_result_reason <", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonLessThanOrEqualTo(String value) {
            addCriterion("client_result_reason <=", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonLike(String value) {
            addCriterion("client_result_reason like", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonNotLike(String value) {
            addCriterion("client_result_reason not like", value, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonIn(List<String> values) {
            addCriterion("client_result_reason in", values, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonNotIn(List<String> values) {
            addCriterion("client_result_reason not in", values, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonBetween(String value1, String value2) {
            addCriterion("client_result_reason between", value1, value2, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andClientResultReasonNotBetween(String value1, String value2) {
            addCriterion("client_result_reason not between", value1, value2, "clientResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonIsNull() {
            addCriterion("mcht_result_reason is null");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonIsNotNull() {
            addCriterion("mcht_result_reason is not null");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonEqualTo(String value) {
            addCriterion("mcht_result_reason =", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonNotEqualTo(String value) {
            addCriterion("mcht_result_reason <>", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonGreaterThan(String value) {
            addCriterion("mcht_result_reason >", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_result_reason >=", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonLessThan(String value) {
            addCriterion("mcht_result_reason <", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonLessThanOrEqualTo(String value) {
            addCriterion("mcht_result_reason <=", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonLike(String value) {
            addCriterion("mcht_result_reason like", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonNotLike(String value) {
            addCriterion("mcht_result_reason not like", value, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonIn(List<String> values) {
            addCriterion("mcht_result_reason in", values, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonNotIn(List<String> values) {
            addCriterion("mcht_result_reason not in", values, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonBetween(String value1, String value2) {
            addCriterion("mcht_result_reason between", value1, value2, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andMchtResultReasonNotBetween(String value1, String value2) {
            addCriterion("mcht_result_reason not between", value1, value2, "mchtResultReason");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesIsNull() {
            addCriterion("record_of_cases is null");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesIsNotNull() {
            addCriterion("record_of_cases is not null");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesEqualTo(String value) {
            addCriterion("record_of_cases =", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesNotEqualTo(String value) {
            addCriterion("record_of_cases <>", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesGreaterThan(String value) {
            addCriterion("record_of_cases >", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesGreaterThanOrEqualTo(String value) {
            addCriterion("record_of_cases >=", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesLessThan(String value) {
            addCriterion("record_of_cases <", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesLessThanOrEqualTo(String value) {
            addCriterion("record_of_cases <=", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesLike(String value) {
            addCriterion("record_of_cases like", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesNotLike(String value) {
            addCriterion("record_of_cases not like", value, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesIn(List<String> values) {
            addCriterion("record_of_cases in", values, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesNotIn(List<String> values) {
            addCriterion("record_of_cases not in", values, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesBetween(String value1, String value2) {
            addCriterion("record_of_cases between", value1, value2, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andRecordOfCasesNotBetween(String value1, String value2) {
            addCriterion("record_of_cases not between", value1, value2, "recordOfCases");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateIsNull() {
            addCriterion("is_initiate_violate is null");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateIsNotNull() {
            addCriterion("is_initiate_violate is not null");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateEqualTo(String value) {
            addCriterion("is_initiate_violate =", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateNotEqualTo(String value) {
            addCriterion("is_initiate_violate <>", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateGreaterThan(String value) {
            addCriterion("is_initiate_violate >", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateGreaterThanOrEqualTo(String value) {
            addCriterion("is_initiate_violate >=", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateLessThan(String value) {
            addCriterion("is_initiate_violate <", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateLessThanOrEqualTo(String value) {
            addCriterion("is_initiate_violate <=", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateLike(String value) {
            addCriterion("is_initiate_violate like", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateNotLike(String value) {
            addCriterion("is_initiate_violate not like", value, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateIn(List<String> values) {
            addCriterion("is_initiate_violate in", values, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateNotIn(List<String> values) {
            addCriterion("is_initiate_violate not in", values, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateBetween(String value1, String value2) {
            addCriterion("is_initiate_violate between", value1, value2, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsInitiateViolateNotBetween(String value1, String value2) {
            addCriterion("is_initiate_violate not between", value1, value2, "isInitiateViolate");
            return (Criteria) this;
        }

        public Criteria andIsCommentIsNull() {
            addCriterion("is_comment is null");
            return (Criteria) this;
        }

        public Criteria andIsCommentIsNotNull() {
            addCriterion("is_comment is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommentEqualTo(String value) {
            addCriterion("is_comment =", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotEqualTo(String value) {
            addCriterion("is_comment <>", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThan(String value) {
            addCriterion("is_comment >", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThanOrEqualTo(String value) {
            addCriterion("is_comment >=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThan(String value) {
            addCriterion("is_comment <", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThanOrEqualTo(String value) {
            addCriterion("is_comment <=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLike(String value) {
            addCriterion("is_comment like", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotLike(String value) {
            addCriterion("is_comment not like", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentIn(List<String> values) {
            addCriterion("is_comment in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotIn(List<String> values) {
            addCriterion("is_comment not in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentBetween(String value1, String value2) {
            addCriterion("is_comment between", value1, value2, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotBetween(String value1, String value2) {
            addCriterion("is_comment not between", value1, value2, "isComment");
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