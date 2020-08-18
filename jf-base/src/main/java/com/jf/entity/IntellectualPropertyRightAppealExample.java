package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntellectualPropertyRightAppealExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public IntellectualPropertyRightAppealExample() {
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

        public Criteria andIntellectualPropertyRightIdIsNull() {
            addCriterion("intellectual_property_right_id is null");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdIsNotNull() {
            addCriterion("intellectual_property_right_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdEqualTo(Integer value) {
            addCriterion("intellectual_property_right_id =", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdNotEqualTo(Integer value) {
            addCriterion("intellectual_property_right_id <>", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdGreaterThan(Integer value) {
            addCriterion("intellectual_property_right_id >", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("intellectual_property_right_id >=", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdLessThan(Integer value) {
            addCriterion("intellectual_property_right_id <", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdLessThanOrEqualTo(Integer value) {
            addCriterion("intellectual_property_right_id <=", value, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdIn(List<Integer> values) {
            addCriterion("intellectual_property_right_id in", values, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdNotIn(List<Integer> values) {
            addCriterion("intellectual_property_right_id not in", values, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdBetween(Integer value1, Integer value2) {
            addCriterion("intellectual_property_right_id between", value1, value2, "intellectualPropertyRightId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightIdNotBetween(Integer value1, Integer value2) {
            addCriterion("intellectual_property_right_id not between", value1, value2, "intellectualPropertyRightId");
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

        public Criteria andAppealReasonIsNull() {
            addCriterion("appeal_reason is null");
            return (Criteria) this;
        }

        public Criteria andAppealReasonIsNotNull() {
            addCriterion("appeal_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAppealReasonEqualTo(String value) {
            addCriterion("appeal_reason =", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonNotEqualTo(String value) {
            addCriterion("appeal_reason <>", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonGreaterThan(String value) {
            addCriterion("appeal_reason >", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_reason >=", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonLessThan(String value) {
            addCriterion("appeal_reason <", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonLessThanOrEqualTo(String value) {
            addCriterion("appeal_reason <=", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonLike(String value) {
            addCriterion("appeal_reason like", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonNotLike(String value) {
            addCriterion("appeal_reason not like", value, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonIn(List<String> values) {
            addCriterion("appeal_reason in", values, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonNotIn(List<String> values) {
            addCriterion("appeal_reason not in", values, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonBetween(String value1, String value2) {
            addCriterion("appeal_reason between", value1, value2, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealReasonNotBetween(String value1, String value2) {
            addCriterion("appeal_reason not between", value1, value2, "appealReason");
            return (Criteria) this;
        }

        public Criteria andAppealTypeIsNull() {
            addCriterion("appeal_type is null");
            return (Criteria) this;
        }

        public Criteria andAppealTypeIsNotNull() {
            addCriterion("appeal_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppealTypeEqualTo(String value) {
            addCriterion("appeal_type =", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotEqualTo(String value) {
            addCriterion("appeal_type <>", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeGreaterThan(String value) {
            addCriterion("appeal_type >", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_type >=", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLessThan(String value) {
            addCriterion("appeal_type <", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLessThanOrEqualTo(String value) {
            addCriterion("appeal_type <=", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLike(String value) {
            addCriterion("appeal_type like", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotLike(String value) {
            addCriterion("appeal_type not like", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeIn(List<String> values) {
            addCriterion("appeal_type in", values, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotIn(List<String> values) {
            addCriterion("appeal_type not in", values, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeBetween(String value1, String value2) {
            addCriterion("appeal_type between", value1, value2, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotBetween(String value1, String value2) {
            addCriterion("appeal_type not between", value1, value2, "appealType");
            return (Criteria) this;
        }

        public Criteria andRelevantValueIsNull() {
            addCriterion("relevant_value is null");
            return (Criteria) this;
        }

        public Criteria andRelevantValueIsNotNull() {
            addCriterion("relevant_value is not null");
            return (Criteria) this;
        }

        public Criteria andRelevantValueEqualTo(String value) {
            addCriterion("relevant_value =", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueNotEqualTo(String value) {
            addCriterion("relevant_value <>", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueGreaterThan(String value) {
            addCriterion("relevant_value >", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueGreaterThanOrEqualTo(String value) {
            addCriterion("relevant_value >=", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueLessThan(String value) {
            addCriterion("relevant_value <", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueLessThanOrEqualTo(String value) {
            addCriterion("relevant_value <=", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueLike(String value) {
            addCriterion("relevant_value like", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueNotLike(String value) {
            addCriterion("relevant_value not like", value, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueIn(List<String> values) {
            addCriterion("relevant_value in", values, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueNotIn(List<String> values) {
            addCriterion("relevant_value not in", values, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueBetween(String value1, String value2) {
            addCriterion("relevant_value between", value1, value2, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andRelevantValueNotBetween(String value1, String value2) {
            addCriterion("relevant_value not between", value1, value2, "relevantValue");
            return (Criteria) this;
        }

        public Criteria andReasonDescIsNull() {
            addCriterion("reason_desc is null");
            return (Criteria) this;
        }

        public Criteria andReasonDescIsNotNull() {
            addCriterion("reason_desc is not null");
            return (Criteria) this;
        }

        public Criteria andReasonDescEqualTo(String value) {
            addCriterion("reason_desc =", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotEqualTo(String value) {
            addCriterion("reason_desc <>", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescGreaterThan(String value) {
            addCriterion("reason_desc >", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescGreaterThanOrEqualTo(String value) {
            addCriterion("reason_desc >=", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLessThan(String value) {
            addCriterion("reason_desc <", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLessThanOrEqualTo(String value) {
            addCriterion("reason_desc <=", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescLike(String value) {
            addCriterion("reason_desc like", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotLike(String value) {
            addCriterion("reason_desc not like", value, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescIn(List<String> values) {
            addCriterion("reason_desc in", values, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotIn(List<String> values) {
            addCriterion("reason_desc not in", values, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescBetween(String value1, String value2) {
            addCriterion("reason_desc between", value1, value2, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andReasonDescNotBetween(String value1, String value2) {
            addCriterion("reason_desc not between", value1, value2, "reasonDesc");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusIsNull() {
            addCriterion("accept_status is null");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusIsNotNull() {
            addCriterion("accept_status is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusEqualTo(String value) {
            addCriterion("accept_status =", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotEqualTo(String value) {
            addCriterion("accept_status <>", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusGreaterThan(String value) {
            addCriterion("accept_status >", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusGreaterThanOrEqualTo(String value) {
            addCriterion("accept_status >=", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLessThan(String value) {
            addCriterion("accept_status <", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLessThanOrEqualTo(String value) {
            addCriterion("accept_status <=", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLike(String value) {
            addCriterion("accept_status like", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotLike(String value) {
            addCriterion("accept_status not like", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusIn(List<String> values) {
            addCriterion("accept_status in", values, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotIn(List<String> values) {
            addCriterion("accept_status not in", values, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusBetween(String value1, String value2) {
            addCriterion("accept_status between", value1, value2, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotBetween(String value1, String value2) {
            addCriterion("accept_status not between", value1, value2, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeIsNull() {
            addCriterion("remarks_to_obligee is null");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeIsNotNull() {
            addCriterion("remarks_to_obligee is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeEqualTo(String value) {
            addCriterion("remarks_to_obligee =", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeNotEqualTo(String value) {
            addCriterion("remarks_to_obligee <>", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeGreaterThan(String value) {
            addCriterion("remarks_to_obligee >", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeGreaterThanOrEqualTo(String value) {
            addCriterion("remarks_to_obligee >=", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeLessThan(String value) {
            addCriterion("remarks_to_obligee <", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeLessThanOrEqualTo(String value) {
            addCriterion("remarks_to_obligee <=", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeLike(String value) {
            addCriterion("remarks_to_obligee like", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeNotLike(String value) {
            addCriterion("remarks_to_obligee not like", value, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeIn(List<String> values) {
            addCriterion("remarks_to_obligee in", values, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeNotIn(List<String> values) {
            addCriterion("remarks_to_obligee not in", values, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeBetween(String value1, String value2) {
            addCriterion("remarks_to_obligee between", value1, value2, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToObligeeNotBetween(String value1, String value2) {
            addCriterion("remarks_to_obligee not between", value1, value2, "remarksToObligee");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtIsNull() {
            addCriterion("remarks_to_mcht is null");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtIsNotNull() {
            addCriterion("remarks_to_mcht is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtEqualTo(String value) {
            addCriterion("remarks_to_mcht =", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtNotEqualTo(String value) {
            addCriterion("remarks_to_mcht <>", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtGreaterThan(String value) {
            addCriterion("remarks_to_mcht >", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtGreaterThanOrEqualTo(String value) {
            addCriterion("remarks_to_mcht >=", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtLessThan(String value) {
            addCriterion("remarks_to_mcht <", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtLessThanOrEqualTo(String value) {
            addCriterion("remarks_to_mcht <=", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtLike(String value) {
            addCriterion("remarks_to_mcht like", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtNotLike(String value) {
            addCriterion("remarks_to_mcht not like", value, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtIn(List<String> values) {
            addCriterion("remarks_to_mcht in", values, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtNotIn(List<String> values) {
            addCriterion("remarks_to_mcht not in", values, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtBetween(String value1, String value2) {
            addCriterion("remarks_to_mcht between", value1, value2, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andRemarksToMchtNotBetween(String value1, String value2) {
            addCriterion("remarks_to_mcht not between", value1, value2, "remarksToMcht");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIsNull() {
            addCriterion("inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIsNotNull() {
            addCriterion("inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksEqualTo(String value) {
            addCriterion("inner_remarks =", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotEqualTo(String value) {
            addCriterion("inner_remarks <>", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksGreaterThan(String value) {
            addCriterion("inner_remarks >", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("inner_remarks >=", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLessThan(String value) {
            addCriterion("inner_remarks <", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("inner_remarks <=", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksLike(String value) {
            addCriterion("inner_remarks like", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotLike(String value) {
            addCriterion("inner_remarks not like", value, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksIn(List<String> values) {
            addCriterion("inner_remarks in", values, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotIn(List<String> values) {
            addCriterion("inner_remarks not in", values, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksBetween(String value1, String value2) {
            addCriterion("inner_remarks between", value1, value2, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("inner_remarks not between", value1, value2, "innerRemarks");
            return (Criteria) this;
        }

        public Criteria andCommitDateIsNull() {
            addCriterion("commit_date is null");
            return (Criteria) this;
        }

        public Criteria andCommitDateIsNotNull() {
            addCriterion("commit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommitDateEqualTo(Date value) {
            addCriterion("commit_date =", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotEqualTo(Date value) {
            addCriterion("commit_date <>", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThan(Date value) {
            addCriterion("commit_date >", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_date >=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThan(Date value) {
            addCriterion("commit_date <", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThanOrEqualTo(Date value) {
            addCriterion("commit_date <=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateIn(List<Date> values) {
            addCriterion("commit_date in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotIn(List<Date> values) {
            addCriterion("commit_date not in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateBetween(Date value1, Date value2) {
            addCriterion("commit_date between", value1, value2, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotBetween(Date value1, Date value2) {
            addCriterion("commit_date not between", value1, value2, "commitDate");
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

        public Criteria andStatusDateIsNull() {
            addCriterion("status_date is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("status_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("status_date =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("status_date <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("status_date >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("status_date >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("status_date <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("status_date <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("status_date in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("status_date not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("status_date between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("status_date not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIsNull() {
            addCriterion("complain_end_date is null");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIsNotNull() {
            addCriterion("complain_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateEqualTo(Date value) {
            addCriterion("complain_end_date =", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotEqualTo(Date value) {
            addCriterion("complain_end_date <>", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateGreaterThan(Date value) {
            addCriterion("complain_end_date >", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complain_end_date >=", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateLessThan(Date value) {
            addCriterion("complain_end_date <", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateLessThanOrEqualTo(Date value) {
            addCriterion("complain_end_date <=", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIn(List<Date> values) {
            addCriterion("complain_end_date in", values, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotIn(List<Date> values) {
            addCriterion("complain_end_date not in", values, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateBetween(Date value1, Date value2) {
            addCriterion("complain_end_date between", value1, value2, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotBetween(Date value1, Date value2) {
            addCriterion("complain_end_date not between", value1, value2, "complainEndDate");
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