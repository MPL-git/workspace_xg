package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WithdrawOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public WithdrawOrderExample() {
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

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
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

        public Criteria andAccIdIsNull() {
            addCriterion("acc_id is null");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNotNull() {
            addCriterion("acc_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccIdEqualTo(Integer value) {
            addCriterion("acc_id =", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotEqualTo(Integer value) {
            addCriterion("acc_id <>", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThan(Integer value) {
            addCriterion("acc_id >", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("acc_id >=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThan(Integer value) {
            addCriterion("acc_id <", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThanOrEqualTo(Integer value) {
            addCriterion("acc_id <=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdIn(List<Integer> values) {
            addCriterion("acc_id in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotIn(List<Integer> values) {
            addCriterion("acc_id not in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdBetween(Integer value1, Integer value2) {
            addCriterion("acc_id between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotBetween(Integer value1, Integer value2) {
            addCriterion("acc_id not between", value1, value2, "accId");
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

        public Criteria andWithdrawTypeIsNull() {
            addCriterion("withdraw_type is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeIsNotNull() {
            addCriterion("withdraw_type is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeEqualTo(String value) {
            addCriterion("withdraw_type =", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotEqualTo(String value) {
            addCriterion("withdraw_type <>", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeGreaterThan(String value) {
            addCriterion("withdraw_type >", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_type >=", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeLessThan(String value) {
            addCriterion("withdraw_type <", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeLessThanOrEqualTo(String value) {
            addCriterion("withdraw_type <=", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeLike(String value) {
            addCriterion("withdraw_type like", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotLike(String value) {
            addCriterion("withdraw_type not like", value, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeIn(List<String> values) {
            addCriterion("withdraw_type in", values, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotIn(List<String> values) {
            addCriterion("withdraw_type not in", values, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeBetween(String value1, String value2) {
            addCriterion("withdraw_type between", value1, value2, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andWithdrawTypeNotBetween(String value1, String value2) {
            addCriterion("withdraw_type not between", value1, value2, "withdrawType");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Integer value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Integer value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Integer value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Integer value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Integer> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Integer> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdIsNull() {
            addCriterion("member_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdIsNotNull() {
            addCriterion("member_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdEqualTo(Integer value) {
            addCriterion("member_coupon_id =", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotEqualTo(Integer value) {
            addCriterion("member_coupon_id <>", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdGreaterThan(Integer value) {
            addCriterion("member_coupon_id >", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_coupon_id >=", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdLessThan(Integer value) {
            addCriterion("member_coupon_id <", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_coupon_id <=", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdIn(List<Integer> values) {
            addCriterion("member_coupon_id in", values, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotIn(List<Integer> values) {
            addCriterion("member_coupon_id not in", values, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("member_coupon_id between", value1, value2, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_coupon_id not between", value1, value2, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdIsNull() {
            addCriterion("withdraw_cnf_id is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdIsNotNull() {
            addCriterion("withdraw_cnf_id is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdEqualTo(Integer value) {
            addCriterion("withdraw_cnf_id =", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdNotEqualTo(Integer value) {
            addCriterion("withdraw_cnf_id <>", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdGreaterThan(Integer value) {
            addCriterion("withdraw_cnf_id >", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("withdraw_cnf_id >=", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdLessThan(Integer value) {
            addCriterion("withdraw_cnf_id <", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdLessThanOrEqualTo(Integer value) {
            addCriterion("withdraw_cnf_id <=", value, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdIn(List<Integer> values) {
            addCriterion("withdraw_cnf_id in", values, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdNotIn(List<Integer> values) {
            addCriterion("withdraw_cnf_id not in", values, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_cnf_id between", value1, value2, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andWithdrawCnfIdNotBetween(Integer value1, Integer value2) {
            addCriterion("withdraw_cnf_id not between", value1, value2, "withdrawCnfId");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNull() {
            addCriterion("alipay_account is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIsNotNull() {
            addCriterion("alipay_account is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountEqualTo(String value) {
            addCriterion("alipay_account =", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotEqualTo(String value) {
            addCriterion("alipay_account <>", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThan(String value) {
            addCriterion("alipay_account >", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account >=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThan(String value) {
            addCriterion("alipay_account <", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLessThanOrEqualTo(String value) {
            addCriterion("alipay_account <=", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountLike(String value) {
            addCriterion("alipay_account like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotLike(String value) {
            addCriterion("alipay_account not like", value, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountIn(List<String> values) {
            addCriterion("alipay_account in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotIn(List<String> values) {
            addCriterion("alipay_account not in", values, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountBetween(String value1, String value2) {
            addCriterion("alipay_account between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNotBetween(String value1, String value2) {
            addCriterion("alipay_account not between", value1, value2, "alipayAccount");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIsNull() {
            addCriterion("withdraw_method is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIsNotNull() {
            addCriterion("withdraw_method is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodEqualTo(String value) {
            addCriterion("withdraw_method =", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotEqualTo(String value) {
            addCriterion("withdraw_method <>", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodGreaterThan(String value) {
            addCriterion("withdraw_method >", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodGreaterThanOrEqualTo(String value) {
            addCriterion("withdraw_method >=", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLessThan(String value) {
            addCriterion("withdraw_method <", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLessThanOrEqualTo(String value) {
            addCriterion("withdraw_method <=", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodLike(String value) {
            addCriterion("withdraw_method like", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotLike(String value) {
            addCriterion("withdraw_method not like", value, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodIn(List<String> values) {
            addCriterion("withdraw_method in", values, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotIn(List<String> values) {
            addCriterion("withdraw_method not in", values, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodBetween(String value1, String value2) {
            addCriterion("withdraw_method between", value1, value2, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andWithdrawMethodNotBetween(String value1, String value2) {
            addCriterion("withdraw_method not between", value1, value2, "withdrawMethod");
            return (Criteria) this;
        }

        public Criteria andYyAuditByIsNull() {
            addCriterion("yy_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andYyAuditByIsNotNull() {
            addCriterion("yy_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andYyAuditByEqualTo(Integer value) {
            addCriterion("yy_audit_by =", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByNotEqualTo(Integer value) {
            addCriterion("yy_audit_by <>", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByGreaterThan(Integer value) {
            addCriterion("yy_audit_by >", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("yy_audit_by >=", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByLessThan(Integer value) {
            addCriterion("yy_audit_by <", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("yy_audit_by <=", value, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByIn(List<Integer> values) {
            addCriterion("yy_audit_by in", values, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByNotIn(List<Integer> values) {
            addCriterion("yy_audit_by not in", values, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByBetween(Integer value1, Integer value2) {
            addCriterion("yy_audit_by between", value1, value2, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("yy_audit_by not between", value1, value2, "yyAuditBy");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusIsNull() {
            addCriterion("yy_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusIsNotNull() {
            addCriterion("yy_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusEqualTo(String value) {
            addCriterion("yy_audit_status =", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusNotEqualTo(String value) {
            addCriterion("yy_audit_status <>", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusGreaterThan(String value) {
            addCriterion("yy_audit_status >", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("yy_audit_status >=", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusLessThan(String value) {
            addCriterion("yy_audit_status <", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("yy_audit_status <=", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusLike(String value) {
            addCriterion("yy_audit_status like", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusNotLike(String value) {
            addCriterion("yy_audit_status not like", value, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusIn(List<String> values) {
            addCriterion("yy_audit_status in", values, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusNotIn(List<String> values) {
            addCriterion("yy_audit_status not in", values, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusBetween(String value1, String value2) {
            addCriterion("yy_audit_status between", value1, value2, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditStatusNotBetween(String value1, String value2) {
            addCriterion("yy_audit_status not between", value1, value2, "yyAuditStatus");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateIsNull() {
            addCriterion("yy_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateIsNotNull() {
            addCriterion("yy_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateEqualTo(Date value) {
            addCriterion("yy_audit_date =", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateNotEqualTo(Date value) {
            addCriterion("yy_audit_date <>", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateGreaterThan(Date value) {
            addCriterion("yy_audit_date >", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("yy_audit_date >=", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateLessThan(Date value) {
            addCriterion("yy_audit_date <", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("yy_audit_date <=", value, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateIn(List<Date> values) {
            addCriterion("yy_audit_date in", values, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateNotIn(List<Date> values) {
            addCriterion("yy_audit_date not in", values, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateBetween(Date value1, Date value2) {
            addCriterion("yy_audit_date between", value1, value2, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("yy_audit_date not between", value1, value2, "yyAuditDate");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonIsNull() {
            addCriterion("yy_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonIsNotNull() {
            addCriterion("yy_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonEqualTo(String value) {
            addCriterion("yy_reject_reason =", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonNotEqualTo(String value) {
            addCriterion("yy_reject_reason <>", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonGreaterThan(String value) {
            addCriterion("yy_reject_reason >", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("yy_reject_reason >=", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonLessThan(String value) {
            addCriterion("yy_reject_reason <", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("yy_reject_reason <=", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonLike(String value) {
            addCriterion("yy_reject_reason like", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonNotLike(String value) {
            addCriterion("yy_reject_reason not like", value, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonIn(List<String> values) {
            addCriterion("yy_reject_reason in", values, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonNotIn(List<String> values) {
            addCriterion("yy_reject_reason not in", values, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonBetween(String value1, String value2) {
            addCriterion("yy_reject_reason between", value1, value2, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyRejectReasonNotBetween(String value1, String value2) {
            addCriterion("yy_reject_reason not between", value1, value2, "yyRejectReason");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksIsNull() {
            addCriterion("yy_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksIsNotNull() {
            addCriterion("yy_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksEqualTo(String value) {
            addCriterion("yy_inner_remarks =", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksNotEqualTo(String value) {
            addCriterion("yy_inner_remarks <>", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksGreaterThan(String value) {
            addCriterion("yy_inner_remarks >", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("yy_inner_remarks >=", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksLessThan(String value) {
            addCriterion("yy_inner_remarks <", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("yy_inner_remarks <=", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksLike(String value) {
            addCriterion("yy_inner_remarks like", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksNotLike(String value) {
            addCriterion("yy_inner_remarks not like", value, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksIn(List<String> values) {
            addCriterion("yy_inner_remarks in", values, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksNotIn(List<String> values) {
            addCriterion("yy_inner_remarks not in", values, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksBetween(String value1, String value2) {
            addCriterion("yy_inner_remarks between", value1, value2, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andYyInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("yy_inner_remarks not between", value1, value2, "yyInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwAuditByIsNull() {
            addCriterion("cw_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andCwAuditByIsNotNull() {
            addCriterion("cw_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andCwAuditByEqualTo(Integer value) {
            addCriterion("cw_audit_by =", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByNotEqualTo(Integer value) {
            addCriterion("cw_audit_by <>", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByGreaterThan(Integer value) {
            addCriterion("cw_audit_by >", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("cw_audit_by >=", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByLessThan(Integer value) {
            addCriterion("cw_audit_by <", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("cw_audit_by <=", value, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByIn(List<Integer> values) {
            addCriterion("cw_audit_by in", values, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByNotIn(List<Integer> values) {
            addCriterion("cw_audit_by not in", values, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByBetween(Integer value1, Integer value2) {
            addCriterion("cw_audit_by between", value1, value2, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("cw_audit_by not between", value1, value2, "cwAuditBy");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusIsNull() {
            addCriterion("cw_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusIsNotNull() {
            addCriterion("cw_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusEqualTo(String value) {
            addCriterion("cw_audit_status =", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusNotEqualTo(String value) {
            addCriterion("cw_audit_status <>", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusGreaterThan(String value) {
            addCriterion("cw_audit_status >", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("cw_audit_status >=", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusLessThan(String value) {
            addCriterion("cw_audit_status <", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("cw_audit_status <=", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusLike(String value) {
            addCriterion("cw_audit_status like", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusNotLike(String value) {
            addCriterion("cw_audit_status not like", value, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusIn(List<String> values) {
            addCriterion("cw_audit_status in", values, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusNotIn(List<String> values) {
            addCriterion("cw_audit_status not in", values, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusBetween(String value1, String value2) {
            addCriterion("cw_audit_status between", value1, value2, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditStatusNotBetween(String value1, String value2) {
            addCriterion("cw_audit_status not between", value1, value2, "cwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateIsNull() {
            addCriterion("cw_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateIsNotNull() {
            addCriterion("cw_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateEqualTo(Date value) {
            addCriterion("cw_audit_date =", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateNotEqualTo(Date value) {
            addCriterion("cw_audit_date <>", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateGreaterThan(Date value) {
            addCriterion("cw_audit_date >", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cw_audit_date >=", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateLessThan(Date value) {
            addCriterion("cw_audit_date <", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("cw_audit_date <=", value, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateIn(List<Date> values) {
            addCriterion("cw_audit_date in", values, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateNotIn(List<Date> values) {
            addCriterion("cw_audit_date not in", values, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateBetween(Date value1, Date value2) {
            addCriterion("cw_audit_date between", value1, value2, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("cw_audit_date not between", value1, value2, "cwAuditDate");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonIsNull() {
            addCriterion("cw_reject_reason is null");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonIsNotNull() {
            addCriterion("cw_reject_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonEqualTo(String value) {
            addCriterion("cw_reject_reason =", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonNotEqualTo(String value) {
            addCriterion("cw_reject_reason <>", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonGreaterThan(String value) {
            addCriterion("cw_reject_reason >", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonGreaterThanOrEqualTo(String value) {
            addCriterion("cw_reject_reason >=", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonLessThan(String value) {
            addCriterion("cw_reject_reason <", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonLessThanOrEqualTo(String value) {
            addCriterion("cw_reject_reason <=", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonLike(String value) {
            addCriterion("cw_reject_reason like", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonNotLike(String value) {
            addCriterion("cw_reject_reason not like", value, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonIn(List<String> values) {
            addCriterion("cw_reject_reason in", values, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonNotIn(List<String> values) {
            addCriterion("cw_reject_reason not in", values, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonBetween(String value1, String value2) {
            addCriterion("cw_reject_reason between", value1, value2, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwRejectReasonNotBetween(String value1, String value2) {
            addCriterion("cw_reject_reason not between", value1, value2, "cwRejectReason");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksIsNull() {
            addCriterion("cw_inner_remarks is null");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksIsNotNull() {
            addCriterion("cw_inner_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksEqualTo(String value) {
            addCriterion("cw_inner_remarks =", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksNotEqualTo(String value) {
            addCriterion("cw_inner_remarks <>", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksGreaterThan(String value) {
            addCriterion("cw_inner_remarks >", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("cw_inner_remarks >=", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksLessThan(String value) {
            addCriterion("cw_inner_remarks <", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksLessThanOrEqualTo(String value) {
            addCriterion("cw_inner_remarks <=", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksLike(String value) {
            addCriterion("cw_inner_remarks like", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksNotLike(String value) {
            addCriterion("cw_inner_remarks not like", value, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksIn(List<String> values) {
            addCriterion("cw_inner_remarks in", values, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksNotIn(List<String> values) {
            addCriterion("cw_inner_remarks not in", values, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksBetween(String value1, String value2) {
            addCriterion("cw_inner_remarks between", value1, value2, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andCwInnerRemarksNotBetween(String value1, String value2) {
            addCriterion("cw_inner_remarks not between", value1, value2, "cwInnerRemarks");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceIsNull() {
            addCriterion("nova_balance is null");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceIsNotNull() {
            addCriterion("nova_balance is not null");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceEqualTo(BigDecimal value) {
            addCriterion("nova_balance =", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceNotEqualTo(BigDecimal value) {
            addCriterion("nova_balance <>", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceGreaterThan(BigDecimal value) {
            addCriterion("nova_balance >", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("nova_balance >=", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceLessThan(BigDecimal value) {
            addCriterion("nova_balance <", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("nova_balance <=", value, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceIn(List<BigDecimal> values) {
            addCriterion("nova_balance in", values, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceNotIn(List<BigDecimal> values) {
            addCriterion("nova_balance not in", values, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("nova_balance between", value1, value2, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andNovaBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("nova_balance not between", value1, value2, "novaBalance");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdIsNull() {
            addCriterion("bank_branch_id is null");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdIsNotNull() {
            addCriterion("bank_branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdEqualTo(Integer value) {
            addCriterion("bank_branch_id =", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdNotEqualTo(Integer value) {
            addCriterion("bank_branch_id <>", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdGreaterThan(Integer value) {
            addCriterion("bank_branch_id >", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_branch_id >=", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdLessThan(Integer value) {
            addCriterion("bank_branch_id <", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdLessThanOrEqualTo(Integer value) {
            addCriterion("bank_branch_id <=", value, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdIn(List<Integer> values) {
            addCriterion("bank_branch_id in", values, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdNotIn(List<Integer> values) {
            addCriterion("bank_branch_id not in", values, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdBetween(Integer value1, Integer value2) {
            addCriterion("bank_branch_id between", value1, value2, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankBranchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_branch_id not between", value1, value2, "bankBranchId");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNull() {
            addCriterion("bank_id is null");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNotNull() {
            addCriterion("bank_id is not null");
            return (Criteria) this;
        }

        public Criteria andBankIdEqualTo(Integer value) {
            addCriterion("bank_id =", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotEqualTo(Integer value) {
            addCriterion("bank_id <>", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThan(Integer value) {
            addCriterion("bank_id >", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_id >=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThan(Integer value) {
            addCriterion("bank_id <", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThanOrEqualTo(Integer value) {
            addCriterion("bank_id <=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdIn(List<Integer> values) {
            addCriterion("bank_id in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotIn(List<Integer> values) {
            addCriterion("bank_id not in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdBetween(Integer value1, Integer value2) {
            addCriterion("bank_id between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_id not between", value1, value2, "bankId");
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