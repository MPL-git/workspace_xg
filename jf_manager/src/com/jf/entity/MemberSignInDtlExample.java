package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberSignInDtlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberSignInDtlExample() {
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

        public Criteria andMemberSignInIsNull() {
            addCriterion("member_sign_in is null");
            return (Criteria) this;
        }

        public Criteria andMemberSignInIsNotNull() {
            addCriterion("member_sign_in is not null");
            return (Criteria) this;
        }

        public Criteria andMemberSignInEqualTo(Integer value) {
            addCriterion("member_sign_in =", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInNotEqualTo(Integer value) {
            addCriterion("member_sign_in <>", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInGreaterThan(Integer value) {
            addCriterion("member_sign_in >", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_sign_in >=", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInLessThan(Integer value) {
            addCriterion("member_sign_in <", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInLessThanOrEqualTo(Integer value) {
            addCriterion("member_sign_in <=", value, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInIn(List<Integer> values) {
            addCriterion("member_sign_in in", values, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInNotIn(List<Integer> values) {
            addCriterion("member_sign_in not in", values, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInBetween(Integer value1, Integer value2) {
            addCriterion("member_sign_in between", value1, value2, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andMemberSignInNotBetween(Integer value1, Integer value2) {
            addCriterion("member_sign_in not between", value1, value2, "memberSignIn");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNull() {
            addCriterion("reward_type is null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNotNull() {
            addCriterion("reward_type is not null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeEqualTo(String value) {
            addCriterion("reward_type =", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotEqualTo(String value) {
            addCriterion("reward_type <>", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThan(String value) {
            addCriterion("reward_type >", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("reward_type >=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThan(String value) {
            addCriterion("reward_type <", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThanOrEqualTo(String value) {
            addCriterion("reward_type <=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLike(String value) {
            addCriterion("reward_type like", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotLike(String value) {
            addCriterion("reward_type not like", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIn(List<String> values) {
            addCriterion("reward_type in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotIn(List<String> values) {
            addCriterion("reward_type not in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeBetween(String value1, String value2) {
            addCriterion("reward_type between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotBetween(String value1, String value2) {
            addCriterion("reward_type not between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountIsNull() {
            addCriterion("extra_amount is null");
            return (Criteria) this;
        }

        public Criteria andExtraAmountIsNotNull() {
            addCriterion("extra_amount is not null");
            return (Criteria) this;
        }

        public Criteria andExtraAmountEqualTo(BigDecimal value) {
            addCriterion("extra_amount =", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountNotEqualTo(BigDecimal value) {
            addCriterion("extra_amount <>", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountGreaterThan(BigDecimal value) {
            addCriterion("extra_amount >", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("extra_amount >=", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountLessThan(BigDecimal value) {
            addCriterion("extra_amount <", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("extra_amount <=", value, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountIn(List<BigDecimal> values) {
            addCriterion("extra_amount in", values, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountNotIn(List<BigDecimal> values) {
            addCriterion("extra_amount not in", values, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extra_amount between", value1, value2, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andExtraAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("extra_amount not between", value1, value2, "extraAmount");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdIsNull() {
            addCriterion("sign_in_cnf_id is null");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdIsNotNull() {
            addCriterion("sign_in_cnf_id is not null");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdEqualTo(Integer value) {
            addCriterion("sign_in_cnf_id =", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdNotEqualTo(Integer value) {
            addCriterion("sign_in_cnf_id <>", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdGreaterThan(Integer value) {
            addCriterion("sign_in_cnf_id >", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_in_cnf_id >=", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdLessThan(Integer value) {
            addCriterion("sign_in_cnf_id <", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdLessThanOrEqualTo(Integer value) {
            addCriterion("sign_in_cnf_id <=", value, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdIn(List<Integer> values) {
            addCriterion("sign_in_cnf_id in", values, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdNotIn(List<Integer> values) {
            addCriterion("sign_in_cnf_id not in", values, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_cnf_id between", value1, value2, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_cnf_id not between", value1, value2, "signInCnfId");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlIsNull() {
            addCriterion("sign_in_cnf_dtl is null");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlIsNotNull() {
            addCriterion("sign_in_cnf_dtl is not null");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlEqualTo(Integer value) {
            addCriterion("sign_in_cnf_dtl =", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlNotEqualTo(Integer value) {
            addCriterion("sign_in_cnf_dtl <>", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlGreaterThan(Integer value) {
            addCriterion("sign_in_cnf_dtl >", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_in_cnf_dtl >=", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlLessThan(Integer value) {
            addCriterion("sign_in_cnf_dtl <", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlLessThanOrEqualTo(Integer value) {
            addCriterion("sign_in_cnf_dtl <=", value, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlIn(List<Integer> values) {
            addCriterion("sign_in_cnf_dtl in", values, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlNotIn(List<Integer> values) {
            addCriterion("sign_in_cnf_dtl not in", values, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_cnf_dtl between", value1, value2, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andSignInCnfDtlNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_in_cnf_dtl not between", value1, value2, "signInCnfDtl");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdIsNull() {
            addCriterion("current_source_member_id is null");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdIsNotNull() {
            addCriterion("current_source_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdEqualTo(Integer value) {
            addCriterion("current_source_member_id =", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdNotEqualTo(Integer value) {
            addCriterion("current_source_member_id <>", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdGreaterThan(Integer value) {
            addCriterion("current_source_member_id >", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_source_member_id >=", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdLessThan(Integer value) {
            addCriterion("current_source_member_id <", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("current_source_member_id <=", value, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdIn(List<Integer> values) {
            addCriterion("current_source_member_id in", values, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdNotIn(List<Integer> values) {
            addCriterion("current_source_member_id not in", values, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("current_source_member_id between", value1, value2, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andCurrentSourceMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("current_source_member_id not between", value1, value2, "currentSourceMemberId");
            return (Criteria) this;
        }

        public Criteria andIsHelpIsNull() {
            addCriterion("is_help is null");
            return (Criteria) this;
        }

        public Criteria andIsHelpIsNotNull() {
            addCriterion("is_help is not null");
            return (Criteria) this;
        }

        public Criteria andIsHelpEqualTo(String value) {
            addCriterion("is_help =", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpNotEqualTo(String value) {
            addCriterion("is_help <>", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpGreaterThan(String value) {
            addCriterion("is_help >", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpGreaterThanOrEqualTo(String value) {
            addCriterion("is_help >=", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpLessThan(String value) {
            addCriterion("is_help <", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpLessThanOrEqualTo(String value) {
            addCriterion("is_help <=", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpLike(String value) {
            addCriterion("is_help like", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpNotLike(String value) {
            addCriterion("is_help not like", value, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpIn(List<String> values) {
            addCriterion("is_help in", values, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpNotIn(List<String> values) {
            addCriterion("is_help not in", values, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpBetween(String value1, String value2) {
            addCriterion("is_help between", value1, value2, "isHelp");
            return (Criteria) this;
        }

        public Criteria andIsHelpNotBetween(String value1, String value2) {
            addCriterion("is_help not between", value1, value2, "isHelp");
            return (Criteria) this;
        }

        public Criteria andHelpAmountIsNull() {
            addCriterion("help_amount is null");
            return (Criteria) this;
        }

        public Criteria andHelpAmountIsNotNull() {
            addCriterion("help_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHelpAmountEqualTo(BigDecimal value) {
            addCriterion("help_amount =", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountNotEqualTo(BigDecimal value) {
            addCriterion("help_amount <>", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountGreaterThan(BigDecimal value) {
            addCriterion("help_amount >", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("help_amount >=", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountLessThan(BigDecimal value) {
            addCriterion("help_amount <", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("help_amount <=", value, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountIn(List<BigDecimal> values) {
            addCriterion("help_amount in", values, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountNotIn(List<BigDecimal> values) {
            addCriterion("help_amount not in", values, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("help_amount between", value1, value2, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andHelpAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("help_amount not between", value1, value2, "helpAmount");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetIsNull() {
            addCriterion("is_help_amount_get is null");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetIsNotNull() {
            addCriterion("is_help_amount_get is not null");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetEqualTo(String value) {
            addCriterion("is_help_amount_get =", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetNotEqualTo(String value) {
            addCriterion("is_help_amount_get <>", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetGreaterThan(String value) {
            addCriterion("is_help_amount_get >", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetGreaterThanOrEqualTo(String value) {
            addCriterion("is_help_amount_get >=", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetLessThan(String value) {
            addCriterion("is_help_amount_get <", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetLessThanOrEqualTo(String value) {
            addCriterion("is_help_amount_get <=", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetLike(String value) {
            addCriterion("is_help_amount_get like", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetNotLike(String value) {
            addCriterion("is_help_amount_get not like", value, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetIn(List<String> values) {
            addCriterion("is_help_amount_get in", values, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetNotIn(List<String> values) {
            addCriterion("is_help_amount_get not in", values, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetBetween(String value1, String value2) {
            addCriterion("is_help_amount_get between", value1, value2, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andIsHelpAmountGetNotBetween(String value1, String value2) {
            addCriterion("is_help_amount_get not between", value1, value2, "isHelpAmountGet");
            return (Criteria) this;
        }

        public Criteria andSignInClientIsNull() {
            addCriterion("sign_in_client is null");
            return (Criteria) this;
        }

        public Criteria andSignInClientIsNotNull() {
            addCriterion("sign_in_client is not null");
            return (Criteria) this;
        }

        public Criteria andSignInClientEqualTo(String value) {
            addCriterion("sign_in_client =", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientNotEqualTo(String value) {
            addCriterion("sign_in_client <>", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientGreaterThan(String value) {
            addCriterion("sign_in_client >", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientGreaterThanOrEqualTo(String value) {
            addCriterion("sign_in_client >=", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientLessThan(String value) {
            addCriterion("sign_in_client <", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientLessThanOrEqualTo(String value) {
            addCriterion("sign_in_client <=", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientLike(String value) {
            addCriterion("sign_in_client like", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientNotLike(String value) {
            addCriterion("sign_in_client not like", value, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientIn(List<String> values) {
            addCriterion("sign_in_client in", values, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientNotIn(List<String> values) {
            addCriterion("sign_in_client not in", values, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientBetween(String value1, String value2) {
            addCriterion("sign_in_client between", value1, value2, "signInClient");
            return (Criteria) this;
        }

        public Criteria andSignInClientNotBetween(String value1, String value2) {
            addCriterion("sign_in_client not between", value1, value2, "signInClient");
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