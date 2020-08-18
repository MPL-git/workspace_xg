package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberAccountExample() {
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

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNull() {
            addCriterion("freeze is null");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNotNull() {
            addCriterion("freeze is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeEqualTo(BigDecimal value) {
            addCriterion("freeze =", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotEqualTo(BigDecimal value) {
            addCriterion("freeze <>", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThan(BigDecimal value) {
            addCriterion("freeze >", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze >=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThan(BigDecimal value) {
            addCriterion("freeze <", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze <=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeIn(List<BigDecimal> values) {
            addCriterion("freeze in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotIn(List<BigDecimal> values) {
            addCriterion("freeze not in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze not between", value1, value2, "freeze");
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

        public Criteria andGValueIsNull() {
            addCriterion("g_value is null");
            return (Criteria) this;
        }

        public Criteria andGValueIsNotNull() {
            addCriterion("g_value is not null");
            return (Criteria) this;
        }

        public Criteria andGValueEqualTo(Integer value) {
            addCriterion("g_value =", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueNotEqualTo(Integer value) {
            addCriterion("g_value <>", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueGreaterThan(Integer value) {
            addCriterion("g_value >", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("g_value >=", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueLessThan(Integer value) {
            addCriterion("g_value <", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueLessThanOrEqualTo(Integer value) {
            addCriterion("g_value <=", value, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueIn(List<Integer> values) {
            addCriterion("g_value in", values, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueNotIn(List<Integer> values) {
            addCriterion("g_value not in", values, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueBetween(Integer value1, Integer value2) {
            addCriterion("g_value between", value1, value2, "gValue");
            return (Criteria) this;
        }

        public Criteria andGValueNotBetween(Integer value1, Integer value2) {
            addCriterion("g_value not between", value1, value2, "gValue");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNull() {
            addCriterion("is_effect is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNotNull() {
            addCriterion("is_effect is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectEqualTo(String value) {
            addCriterion("is_effect =", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotEqualTo(String value) {
            addCriterion("is_effect <>", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThan(String value) {
            addCriterion("is_effect >", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThanOrEqualTo(String value) {
            addCriterion("is_effect >=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThan(String value) {
            addCriterion("is_effect <", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThanOrEqualTo(String value) {
            addCriterion("is_effect <=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLike(String value) {
            addCriterion("is_effect like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotLike(String value) {
            addCriterion("is_effect not like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectIn(List<String> values) {
            addCriterion("is_effect in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotIn(List<String> values) {
            addCriterion("is_effect not in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectBetween(String value1, String value2) {
            addCriterion("is_effect between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotBetween(String value1, String value2) {
            addCriterion("is_effect not between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardIsNull() {
            addCriterion("supplementary_card is null");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardIsNotNull() {
            addCriterion("supplementary_card is not null");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardEqualTo(Integer value) {
            addCriterion("supplementary_card =", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardNotEqualTo(Integer value) {
            addCriterion("supplementary_card <>", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardGreaterThan(Integer value) {
            addCriterion("supplementary_card >", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplementary_card >=", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardLessThan(Integer value) {
            addCriterion("supplementary_card <", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardLessThanOrEqualTo(Integer value) {
            addCriterion("supplementary_card <=", value, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardIn(List<Integer> values) {
            addCriterion("supplementary_card in", values, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardNotIn(List<Integer> values) {
            addCriterion("supplementary_card not in", values, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardBetween(Integer value1, Integer value2) {
            addCriterion("supplementary_card between", value1, value2, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andSupplementaryCardNotBetween(Integer value1, Integer value2) {
            addCriterion("supplementary_card not between", value1, value2, "supplementaryCard");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceIsNull() {
            addCriterion("profit_invite_balance is null");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceIsNotNull() {
            addCriterion("profit_invite_balance is not null");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceEqualTo(BigDecimal value) {
            addCriterion("profit_invite_balance =", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceNotEqualTo(BigDecimal value) {
            addCriterion("profit_invite_balance <>", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceGreaterThan(BigDecimal value) {
            addCriterion("profit_invite_balance >", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_invite_balance >=", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceLessThan(BigDecimal value) {
            addCriterion("profit_invite_balance <", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_invite_balance <=", value, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceIn(List<BigDecimal> values) {
            addCriterion("profit_invite_balance in", values, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceNotIn(List<BigDecimal> values) {
            addCriterion("profit_invite_balance not in", values, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_invite_balance between", value1, value2, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_invite_balance not between", value1, value2, "profitInviteBalance");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeIsNull() {
            addCriterion("profit_invite_freeze is null");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeIsNotNull() {
            addCriterion("profit_invite_freeze is not null");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeEqualTo(BigDecimal value) {
            addCriterion("profit_invite_freeze =", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeNotEqualTo(BigDecimal value) {
            addCriterion("profit_invite_freeze <>", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeGreaterThan(BigDecimal value) {
            addCriterion("profit_invite_freeze >", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_invite_freeze >=", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeLessThan(BigDecimal value) {
            addCriterion("profit_invite_freeze <", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit_invite_freeze <=", value, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeIn(List<BigDecimal> values) {
            addCriterion("profit_invite_freeze in", values, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeNotIn(List<BigDecimal> values) {
            addCriterion("profit_invite_freeze not in", values, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_invite_freeze between", value1, value2, "profitInviteFreeze");
            return (Criteria) this;
        }

        public Criteria andProfitInviteFreezeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit_invite_freeze not between", value1, value2, "profitInviteFreeze");
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