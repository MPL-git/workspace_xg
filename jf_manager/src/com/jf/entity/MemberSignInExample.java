package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberSignInExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberSignInExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateIsNull() {
            addCriterion("last_sign_in_date is null");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateIsNotNull() {
            addCriterion("last_sign_in_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateEqualTo(Date value) {
            addCriterion("last_sign_in_date =", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateNotEqualTo(Date value) {
            addCriterion("last_sign_in_date <>", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateGreaterThan(Date value) {
            addCriterion("last_sign_in_date >", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_sign_in_date >=", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateLessThan(Date value) {
            addCriterion("last_sign_in_date <", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateLessThanOrEqualTo(Date value) {
            addCriterion("last_sign_in_date <=", value, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateIn(List<Date> values) {
            addCriterion("last_sign_in_date in", values, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateNotIn(List<Date> values) {
            addCriterion("last_sign_in_date not in", values, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateBetween(Date value1, Date value2) {
            addCriterion("last_sign_in_date between", value1, value2, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andLastSignInDateNotBetween(Date value1, Date value2) {
            addCriterion("last_sign_in_date not between", value1, value2, "lastSignInDate");
            return (Criteria) this;
        }

        public Criteria andMostContinuityIsNull() {
            addCriterion("most_continuity is null");
            return (Criteria) this;
        }

        public Criteria andMostContinuityIsNotNull() {
            addCriterion("most_continuity is not null");
            return (Criteria) this;
        }

        public Criteria andMostContinuityEqualTo(Integer value) {
            addCriterion("most_continuity =", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityNotEqualTo(Integer value) {
            addCriterion("most_continuity <>", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityGreaterThan(Integer value) {
            addCriterion("most_continuity >", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityGreaterThanOrEqualTo(Integer value) {
            addCriterion("most_continuity >=", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityLessThan(Integer value) {
            addCriterion("most_continuity <", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityLessThanOrEqualTo(Integer value) {
            addCriterion("most_continuity <=", value, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityIn(List<Integer> values) {
            addCriterion("most_continuity in", values, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityNotIn(List<Integer> values) {
            addCriterion("most_continuity not in", values, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityBetween(Integer value1, Integer value2) {
            addCriterion("most_continuity between", value1, value2, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andMostContinuityNotBetween(Integer value1, Integer value2) {
            addCriterion("most_continuity not between", value1, value2, "mostContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityIsNull() {
            addCriterion("current_continuity is null");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityIsNotNull() {
            addCriterion("current_continuity is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityEqualTo(Integer value) {
            addCriterion("current_continuity =", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityNotEqualTo(Integer value) {
            addCriterion("current_continuity <>", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityGreaterThan(Integer value) {
            addCriterion("current_continuity >", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_continuity >=", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityLessThan(Integer value) {
            addCriterion("current_continuity <", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityLessThanOrEqualTo(Integer value) {
            addCriterion("current_continuity <=", value, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityIn(List<Integer> values) {
            addCriterion("current_continuity in", values, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityNotIn(List<Integer> values) {
            addCriterion("current_continuity not in", values, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityBetween(Integer value1, Integer value2) {
            addCriterion("current_continuity between", value1, value2, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andCurrentContinuityNotBetween(Integer value1, Integer value2) {
            addCriterion("current_continuity not between", value1, value2, "currentContinuity");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNull() {
            addCriterion("send_count is null");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNotNull() {
            addCriterion("send_count is not null");
            return (Criteria) this;
        }

        public Criteria andSendCountEqualTo(Integer value) {
            addCriterion("send_count =", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotEqualTo(Integer value) {
            addCriterion("send_count <>", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThan(Integer value) {
            addCriterion("send_count >", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_count >=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThan(Integer value) {
            addCriterion("send_count <", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThanOrEqualTo(Integer value) {
            addCriterion("send_count <=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountIn(List<Integer> values) {
            addCriterion("send_count in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotIn(List<Integer> values) {
            addCriterion("send_count not in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountBetween(Integer value1, Integer value2) {
            addCriterion("send_count between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotBetween(Integer value1, Integer value2) {
            addCriterion("send_count not between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnIsNull() {
            addCriterion("is_send_warn is null");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnIsNotNull() {
            addCriterion("is_send_warn is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnEqualTo(String value) {
            addCriterion("is_send_warn =", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnNotEqualTo(String value) {
            addCriterion("is_send_warn <>", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnGreaterThan(String value) {
            addCriterion("is_send_warn >", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnGreaterThanOrEqualTo(String value) {
            addCriterion("is_send_warn >=", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnLessThan(String value) {
            addCriterion("is_send_warn <", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnLessThanOrEqualTo(String value) {
            addCriterion("is_send_warn <=", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnLike(String value) {
            addCriterion("is_send_warn like", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnNotLike(String value) {
            addCriterion("is_send_warn not like", value, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnIn(List<String> values) {
            addCriterion("is_send_warn in", values, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnNotIn(List<String> values) {
            addCriterion("is_send_warn not in", values, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnBetween(String value1, String value2) {
            addCriterion("is_send_warn between", value1, value2, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andIsSendWarnNotBetween(String value1, String value2) {
            addCriterion("is_send_warn not between", value1, value2, "isSendWarn");
            return (Criteria) this;
        }

        public Criteria andPopupCountIsNull() {
            addCriterion("popup_count is null");
            return (Criteria) this;
        }

        public Criteria andPopupCountIsNotNull() {
            addCriterion("popup_count is not null");
            return (Criteria) this;
        }

        public Criteria andPopupCountEqualTo(Integer value) {
            addCriterion("popup_count =", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountNotEqualTo(Integer value) {
            addCriterion("popup_count <>", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountGreaterThan(Integer value) {
            addCriterion("popup_count >", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("popup_count >=", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountLessThan(Integer value) {
            addCriterion("popup_count <", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountLessThanOrEqualTo(Integer value) {
            addCriterion("popup_count <=", value, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountIn(List<Integer> values) {
            addCriterion("popup_count in", values, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountNotIn(List<Integer> values) {
            addCriterion("popup_count not in", values, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountBetween(Integer value1, Integer value2) {
            addCriterion("popup_count between", value1, value2, "popupCount");
            return (Criteria) this;
        }

        public Criteria andPopupCountNotBetween(Integer value1, Integer value2) {
            addCriterion("popup_count not between", value1, value2, "popupCount");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdIsNull() {
            addCriterion("source_member_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdIsNotNull() {
            addCriterion("source_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdEqualTo(Integer value) {
            addCriterion("source_member_id =", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdNotEqualTo(Integer value) {
            addCriterion("source_member_id <>", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdGreaterThan(Integer value) {
            addCriterion("source_member_id >", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_member_id >=", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdLessThan(Integer value) {
            addCriterion("source_member_id <", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("source_member_id <=", value, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdIn(List<Integer> values) {
            addCriterion("source_member_id in", values, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdNotIn(List<Integer> values) {
            addCriterion("source_member_id not in", values, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("source_member_id between", value1, value2, "sourceMemberId");
            return (Criteria) this;
        }

        public Criteria andSourceMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("source_member_id not between", value1, value2, "sourceMemberId");
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