package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyRightComplainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public PropertyRightComplainExample() {
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

        public Criteria andIntellectualPropertyRightAppealIdIsNull() {
            addCriterion("intellectual_property_right_appeal_id is null");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdIsNotNull() {
            addCriterion("intellectual_property_right_appeal_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdEqualTo(Integer value) {
            addCriterion("intellectual_property_right_appeal_id =", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdNotEqualTo(Integer value) {
            addCriterion("intellectual_property_right_appeal_id <>", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdGreaterThan(Integer value) {
            addCriterion("intellectual_property_right_appeal_id >", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("intellectual_property_right_appeal_id >=", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdLessThan(Integer value) {
            addCriterion("intellectual_property_right_appeal_id <", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdLessThanOrEqualTo(Integer value) {
            addCriterion("intellectual_property_right_appeal_id <=", value, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdIn(List<Integer> values) {
            addCriterion("intellectual_property_right_appeal_id in", values, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdNotIn(List<Integer> values) {
            addCriterion("intellectual_property_right_appeal_id not in", values, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdBetween(Integer value1, Integer value2) {
            addCriterion("intellectual_property_right_appeal_id between", value1, value2, "intellectualPropertyRightAppealId");
            return (Criteria) this;
        }

        public Criteria andIntellectualPropertyRightAppealIdNotBetween(Integer value1, Integer value2) {
            addCriterion("intellectual_property_right_appeal_id not between", value1, value2, "intellectualPropertyRightAppealId");
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

        public Criteria andComplainReasonIsNull() {
            addCriterion("complain_reason is null");
            return (Criteria) this;
        }

        public Criteria andComplainReasonIsNotNull() {
            addCriterion("complain_reason is not null");
            return (Criteria) this;
        }

        public Criteria andComplainReasonEqualTo(String value) {
            addCriterion("complain_reason =", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonNotEqualTo(String value) {
            addCriterion("complain_reason <>", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonGreaterThan(String value) {
            addCriterion("complain_reason >", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonGreaterThanOrEqualTo(String value) {
            addCriterion("complain_reason >=", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonLessThan(String value) {
            addCriterion("complain_reason <", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonLessThanOrEqualTo(String value) {
            addCriterion("complain_reason <=", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonLike(String value) {
            addCriterion("complain_reason like", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonNotLike(String value) {
            addCriterion("complain_reason not like", value, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonIn(List<String> values) {
            addCriterion("complain_reason in", values, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonNotIn(List<String> values) {
            addCriterion("complain_reason not in", values, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonBetween(String value1, String value2) {
            addCriterion("complain_reason between", value1, value2, "complainReason");
            return (Criteria) this;
        }

        public Criteria andComplainReasonNotBetween(String value1, String value2) {
            addCriterion("complain_reason not between", value1, value2, "complainReason");
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

        public Criteria andComplainDateIsNull() {
            addCriterion("complain_date is null");
            return (Criteria) this;
        }

        public Criteria andComplainDateIsNotNull() {
            addCriterion("complain_date is not null");
            return (Criteria) this;
        }

        public Criteria andComplainDateEqualTo(Date value) {
            addCriterion("complain_date =", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotEqualTo(Date value) {
            addCriterion("complain_date <>", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThan(Date value) {
            addCriterion("complain_date >", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complain_date >=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThan(Date value) {
            addCriterion("complain_date <", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThanOrEqualTo(Date value) {
            addCriterion("complain_date <=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateIn(List<Date> values) {
            addCriterion("complain_date in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotIn(List<Date> values) {
            addCriterion("complain_date not in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateBetween(Date value1, Date value2) {
            addCriterion("complain_date between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotBetween(Date value1, Date value2) {
            addCriterion("complain_date not between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateIsNull() {
            addCriterion("prosecution_end_date is null");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateIsNotNull() {
            addCriterion("prosecution_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateEqualTo(Date value) {
            addCriterion("prosecution_end_date =", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateNotEqualTo(Date value) {
            addCriterion("prosecution_end_date <>", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateGreaterThan(Date value) {
            addCriterion("prosecution_end_date >", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("prosecution_end_date >=", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateLessThan(Date value) {
            addCriterion("prosecution_end_date <", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateLessThanOrEqualTo(Date value) {
            addCriterion("prosecution_end_date <=", value, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateIn(List<Date> values) {
            addCriterion("prosecution_end_date in", values, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateNotIn(List<Date> values) {
            addCriterion("prosecution_end_date not in", values, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateBetween(Date value1, Date value2) {
            addCriterion("prosecution_end_date between", value1, value2, "prosecutionEndDate");
            return (Criteria) this;
        }

        public Criteria andProsecutionEndDateNotBetween(Date value1, Date value2) {
            addCriterion("prosecution_end_date not between", value1, value2, "prosecutionEndDate");
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