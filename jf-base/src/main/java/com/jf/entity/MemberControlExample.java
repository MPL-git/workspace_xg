package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MemberControlExample() {
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

        public Criteria andSignBeHelpedDateIsNull() {
            addCriterion("sign_be_helped_date is null");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateIsNotNull() {
            addCriterion("sign_be_helped_date is not null");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateEqualTo(Date value) {
            addCriterion("sign_be_helped_date =", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateNotEqualTo(Date value) {
            addCriterion("sign_be_helped_date <>", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateGreaterThan(Date value) {
            addCriterion("sign_be_helped_date >", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_be_helped_date >=", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateLessThan(Date value) {
            addCriterion("sign_be_helped_date <", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateLessThanOrEqualTo(Date value) {
            addCriterion("sign_be_helped_date <=", value, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateIn(List<Date> values) {
            addCriterion("sign_be_helped_date in", values, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateNotIn(List<Date> values) {
            addCriterion("sign_be_helped_date not in", values, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateBetween(Date value1, Date value2) {
            addCriterion("sign_be_helped_date between", value1, value2, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedDateNotBetween(Date value1, Date value2) {
            addCriterion("sign_be_helped_date not between", value1, value2, "signBeHelpedDate");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountIsNull() {
            addCriterion("sign_be_helped_count is null");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountIsNotNull() {
            addCriterion("sign_be_helped_count is not null");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountEqualTo(Integer value) {
            addCriterion("sign_be_helped_count =", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountNotEqualTo(Integer value) {
            addCriterion("sign_be_helped_count <>", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountGreaterThan(Integer value) {
            addCriterion("sign_be_helped_count >", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_be_helped_count >=", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountLessThan(Integer value) {
            addCriterion("sign_be_helped_count <", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountLessThanOrEqualTo(Integer value) {
            addCriterion("sign_be_helped_count <=", value, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountIn(List<Integer> values) {
            addCriterion("sign_be_helped_count in", values, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountNotIn(List<Integer> values) {
            addCriterion("sign_be_helped_count not in", values, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountBetween(Integer value1, Integer value2) {
            addCriterion("sign_be_helped_count between", value1, value2, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andSignBeHelpedCountNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_be_helped_count not between", value1, value2, "signBeHelpedCount");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateIsNull() {
            addCriterion("last_help_cut_price_date is null");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateIsNotNull() {
            addCriterion("last_help_cut_price_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateEqualTo(Date value) {
            addCriterion("last_help_cut_price_date =", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateNotEqualTo(Date value) {
            addCriterion("last_help_cut_price_date <>", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateGreaterThan(Date value) {
            addCriterion("last_help_cut_price_date >", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_help_cut_price_date >=", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateLessThan(Date value) {
            addCriterion("last_help_cut_price_date <", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateLessThanOrEqualTo(Date value) {
            addCriterion("last_help_cut_price_date <=", value, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateIn(List<Date> values) {
            addCriterion("last_help_cut_price_date in", values, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateNotIn(List<Date> values) {
            addCriterion("last_help_cut_price_date not in", values, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateBetween(Date value1, Date value2) {
            addCriterion("last_help_cut_price_date between", value1, value2, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastHelpCutPriceDateNotBetween(Date value1, Date value2) {
            addCriterion("last_help_cut_price_date not between", value1, value2, "lastHelpCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountIsNull() {
            addCriterion("help_cut_price_count is null");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountIsNotNull() {
            addCriterion("help_cut_price_count is not null");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountEqualTo(Integer value) {
            addCriterion("help_cut_price_count =", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountNotEqualTo(Integer value) {
            addCriterion("help_cut_price_count <>", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountGreaterThan(Integer value) {
            addCriterion("help_cut_price_count >", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("help_cut_price_count >=", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountLessThan(Integer value) {
            addCriterion("help_cut_price_count <", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountLessThanOrEqualTo(Integer value) {
            addCriterion("help_cut_price_count <=", value, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountIn(List<Integer> values) {
            addCriterion("help_cut_price_count in", values, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountNotIn(List<Integer> values) {
            addCriterion("help_cut_price_count not in", values, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountBetween(Integer value1, Integer value2) {
            addCriterion("help_cut_price_count between", value1, value2, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andHelpCutPriceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("help_cut_price_count not between", value1, value2, "helpCutPriceCount");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateIsNull() {
            addCriterion("last_cut_price_date is null");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateIsNotNull() {
            addCriterion("last_cut_price_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateEqualTo(Date value) {
            addCriterion("last_cut_price_date =", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateNotEqualTo(Date value) {
            addCriterion("last_cut_price_date <>", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateGreaterThan(Date value) {
            addCriterion("last_cut_price_date >", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_cut_price_date >=", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateLessThan(Date value) {
            addCriterion("last_cut_price_date <", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateLessThanOrEqualTo(Date value) {
            addCriterion("last_cut_price_date <=", value, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateIn(List<Date> values) {
            addCriterion("last_cut_price_date in", values, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateNotIn(List<Date> values) {
            addCriterion("last_cut_price_date not in", values, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateBetween(Date value1, Date value2) {
            addCriterion("last_cut_price_date between", value1, value2, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andLastCutPriceDateNotBetween(Date value1, Date value2) {
            addCriterion("last_cut_price_date not between", value1, value2, "lastCutPriceDate");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountIsNull() {
            addCriterion("cut_price_count is null");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountIsNotNull() {
            addCriterion("cut_price_count is not null");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountEqualTo(Integer value) {
            addCriterion("cut_price_count =", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountNotEqualTo(Integer value) {
            addCriterion("cut_price_count <>", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountGreaterThan(Integer value) {
            addCriterion("cut_price_count >", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("cut_price_count >=", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountLessThan(Integer value) {
            addCriterion("cut_price_count <", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountLessThanOrEqualTo(Integer value) {
            addCriterion("cut_price_count <=", value, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountIn(List<Integer> values) {
            addCriterion("cut_price_count in", values, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountNotIn(List<Integer> values) {
            addCriterion("cut_price_count not in", values, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountBetween(Integer value1, Integer value2) {
            addCriterion("cut_price_count between", value1, value2, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andCutPriceCountNotBetween(Integer value1, Integer value2) {
            addCriterion("cut_price_count not between", value1, value2, "cutPriceCount");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateIsNull() {
            addCriterion("last_invite_date is null");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateIsNotNull() {
            addCriterion("last_invite_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateEqualTo(Date value) {
            addCriterion("last_invite_date =", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateNotEqualTo(Date value) {
            addCriterion("last_invite_date <>", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateGreaterThan(Date value) {
            addCriterion("last_invite_date >", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_invite_date >=", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateLessThan(Date value) {
            addCriterion("last_invite_date <", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateLessThanOrEqualTo(Date value) {
            addCriterion("last_invite_date <=", value, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateIn(List<Date> values) {
            addCriterion("last_invite_date in", values, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateNotIn(List<Date> values) {
            addCriterion("last_invite_date not in", values, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateBetween(Date value1, Date value2) {
            addCriterion("last_invite_date between", value1, value2, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andLastInviteDateNotBetween(Date value1, Date value2) {
            addCriterion("last_invite_date not between", value1, value2, "lastInviteDate");
            return (Criteria) this;
        }

        public Criteria andInviteCountIsNull() {
            addCriterion("invite_count is null");
            return (Criteria) this;
        }

        public Criteria andInviteCountIsNotNull() {
            addCriterion("invite_count is not null");
            return (Criteria) this;
        }

        public Criteria andInviteCountEqualTo(Integer value) {
            addCriterion("invite_count =", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountNotEqualTo(Integer value) {
            addCriterion("invite_count <>", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountGreaterThan(Integer value) {
            addCriterion("invite_count >", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_count >=", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountLessThan(Integer value) {
            addCriterion("invite_count <", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountLessThanOrEqualTo(Integer value) {
            addCriterion("invite_count <=", value, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountIn(List<Integer> values) {
            addCriterion("invite_count in", values, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountNotIn(List<Integer> values) {
            addCriterion("invite_count not in", values, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountBetween(Integer value1, Integer value2) {
            addCriterion("invite_count between", value1, value2, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andInviteCountNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_count not between", value1, value2, "inviteCount");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateIsNull() {
            addCriterion("last_rec_svip_integral_date is null");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateIsNotNull() {
            addCriterion("last_rec_svip_integral_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateEqualTo(Date value) {
            addCriterion("last_rec_svip_integral_date =", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateNotEqualTo(Date value) {
            addCriterion("last_rec_svip_integral_date <>", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateGreaterThan(Date value) {
            addCriterion("last_rec_svip_integral_date >", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_rec_svip_integral_date >=", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateLessThan(Date value) {
            addCriterion("last_rec_svip_integral_date <", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateLessThanOrEqualTo(Date value) {
            addCriterion("last_rec_svip_integral_date <=", value, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateIn(List<Date> values) {
            addCriterion("last_rec_svip_integral_date in", values, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateNotIn(List<Date> values) {
            addCriterion("last_rec_svip_integral_date not in", values, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateBetween(Date value1, Date value2) {
            addCriterion("last_rec_svip_integral_date between", value1, value2, "lastRecSvipIntegralDate");
            return (Criteria) this;
        }

        public Criteria andLastRecSvipIntegralDateNotBetween(Date value1, Date value2) {
            addCriterion("last_rec_svip_integral_date not between", value1, value2, "lastRecSvipIntegralDate");
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