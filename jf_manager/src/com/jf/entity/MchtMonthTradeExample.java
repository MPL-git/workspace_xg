package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MchtMonthTradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public MchtMonthTradeExample() {
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

        public Criteria andTradeMonthIsNull() {
            addCriterion("trade_month is null");
            return (Criteria) this;
        }

        public Criteria andTradeMonthIsNotNull() {
            addCriterion("trade_month is not null");
            return (Criteria) this;
        }

        public Criteria andTradeMonthEqualTo(String value) {
            addCriterion("trade_month =", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthNotEqualTo(String value) {
            addCriterion("trade_month <>", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthGreaterThan(String value) {
            addCriterion("trade_month >", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthGreaterThanOrEqualTo(String value) {
            addCriterion("trade_month >=", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthLessThan(String value) {
            addCriterion("trade_month <", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthLessThanOrEqualTo(String value) {
            addCriterion("trade_month <=", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthLike(String value) {
            addCriterion("trade_month like", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthNotLike(String value) {
            addCriterion("trade_month not like", value, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthIn(List<String> values) {
            addCriterion("trade_month in", values, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthNotIn(List<String> values) {
            addCriterion("trade_month not in", values, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthBetween(String value1, String value2) {
            addCriterion("trade_month between", value1, value2, "tradeMonth");
            return (Criteria) this;
        }

        public Criteria andTradeMonthNotBetween(String value1, String value2) {
            addCriterion("trade_month not between", value1, value2, "tradeMonth");
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

        public Criteria andBeginUnpayIsNull() {
            addCriterion("begin_unpay is null");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayIsNotNull() {
            addCriterion("begin_unpay is not null");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayEqualTo(BigDecimal value) {
            addCriterion("begin_unpay =", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotEqualTo(BigDecimal value) {
            addCriterion("begin_unpay <>", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayGreaterThan(BigDecimal value) {
            addCriterion("begin_unpay >", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_unpay >=", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayLessThan(BigDecimal value) {
            addCriterion("begin_unpay <", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_unpay <=", value, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayIn(List<BigDecimal> values) {
            addCriterion("begin_unpay in", values, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotIn(List<BigDecimal> values) {
            addCriterion("begin_unpay not in", values, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_unpay between", value1, value2, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andBeginUnpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_unpay not between", value1, value2, "beginUnpay");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountIsNull() {
            addCriterion("current_month_settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountIsNotNull() {
            addCriterion("current_month_settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount =", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount <>", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("current_month_settle_amount >", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount >=", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountLessThan(BigDecimal value) {
            addCriterion("current_month_settle_amount <", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_month_settle_amount <=", value, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountIn(List<BigDecimal> values) {
            addCriterion("current_month_settle_amount in", values, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("current_month_settle_amount not in", values, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_month_settle_amount between", value1, value2, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentMonthSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_month_settle_amount not between", value1, value2, "currentMonthSettleAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountIsNull() {
            addCriterion("current_deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountIsNotNull() {
            addCriterion("current_deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountEqualTo(BigDecimal value) {
            addCriterion("current_deposit_amount =", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountNotEqualTo(BigDecimal value) {
            addCriterion("current_deposit_amount <>", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountGreaterThan(BigDecimal value) {
            addCriterion("current_deposit_amount >", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_deposit_amount >=", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountLessThan(BigDecimal value) {
            addCriterion("current_deposit_amount <", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_deposit_amount <=", value, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountIn(List<BigDecimal> values) {
            addCriterion("current_deposit_amount in", values, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountNotIn(List<BigDecimal> values) {
            addCriterion("current_deposit_amount not in", values, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_deposit_amount between", value1, value2, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentDepositAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_deposit_amount not between", value1, value2, "currentDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountIsNull() {
            addCriterion("current_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountIsNotNull() {
            addCriterion("current_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountEqualTo(BigDecimal value) {
            addCriterion("current_pay_amount =", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("current_pay_amount <>", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountGreaterThan(BigDecimal value) {
            addCriterion("current_pay_amount >", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_pay_amount >=", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountLessThan(BigDecimal value) {
            addCriterion("current_pay_amount <", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_pay_amount <=", value, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountIn(List<BigDecimal> values) {
            addCriterion("current_pay_amount in", values, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("current_pay_amount not in", values, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_pay_amount between", value1, value2, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_pay_amount not between", value1, value2, "currentPayAmount");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductIsNull() {
            addCriterion("violate_need_deduct is null");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductIsNotNull() {
            addCriterion("violate_need_deduct is not null");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductEqualTo(BigDecimal value) {
            addCriterion("violate_need_deduct =", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductNotEqualTo(BigDecimal value) {
            addCriterion("violate_need_deduct <>", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductGreaterThan(BigDecimal value) {
            addCriterion("violate_need_deduct >", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("violate_need_deduct >=", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductLessThan(BigDecimal value) {
            addCriterion("violate_need_deduct <", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductLessThanOrEqualTo(BigDecimal value) {
            addCriterion("violate_need_deduct <=", value, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductIn(List<BigDecimal> values) {
            addCriterion("violate_need_deduct in", values, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductNotIn(List<BigDecimal> values) {
            addCriterion("violate_need_deduct not in", values, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("violate_need_deduct between", value1, value2, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andViolateNeedDeductNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("violate_need_deduct not between", value1, value2, "violateNeedDeduct");
            return (Criteria) this;
        }

        public Criteria andDepositDtlIsNull() {
            addCriterion("deposit_dtl is null");
            return (Criteria) this;
        }

        public Criteria andDepositDtlIsNotNull() {
            addCriterion("deposit_dtl is not null");
            return (Criteria) this;
        }

        public Criteria andDepositDtlEqualTo(BigDecimal value) {
            addCriterion("deposit_dtl =", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlNotEqualTo(BigDecimal value) {
            addCriterion("deposit_dtl <>", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlGreaterThan(BigDecimal value) {
            addCriterion("deposit_dtl >", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_dtl >=", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlLessThan(BigDecimal value) {
            addCriterion("deposit_dtl <", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_dtl <=", value, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlIn(List<BigDecimal> values) {
            addCriterion("deposit_dtl in", values, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlNotIn(List<BigDecimal> values) {
            addCriterion("deposit_dtl not in", values, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_dtl between", value1, value2, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDepositDtlNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_dtl not between", value1, value2, "depositDtl");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIsNull() {
            addCriterion("end_unpay is null");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIsNotNull() {
            addCriterion("end_unpay is not null");
            return (Criteria) this;
        }

        public Criteria andEndUnpayEqualTo(BigDecimal value) {
            addCriterion("end_unpay =", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotEqualTo(BigDecimal value) {
            addCriterion("end_unpay <>", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayGreaterThan(BigDecimal value) {
            addCriterion("end_unpay >", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_unpay >=", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayLessThan(BigDecimal value) {
            addCriterion("end_unpay <", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_unpay <=", value, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayIn(List<BigDecimal> values) {
            addCriterion("end_unpay in", values, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotIn(List<BigDecimal> values) {
            addCriterion("end_unpay not in", values, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_unpay between", value1, value2, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andEndUnpayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_unpay not between", value1, value2, "endUnpay");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountIsNull() {
            addCriterion("total_order_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountIsNotNull() {
            addCriterion("total_order_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountEqualTo(BigDecimal value) {
            addCriterion("total_order_pay_amount =", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_order_pay_amount <>", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountGreaterThan(BigDecimal value) {
            addCriterion("total_order_pay_amount >", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_order_pay_amount >=", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountLessThan(BigDecimal value) {
            addCriterion("total_order_pay_amount <", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_order_pay_amount <=", value, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountIn(List<BigDecimal> values) {
            addCriterion("total_order_pay_amount in", values, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_order_pay_amount not in", values, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_order_pay_amount between", value1, value2, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andTotalOrderPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_order_pay_amount not between", value1, value2, "totalOrderPayAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountIsNull() {
            addCriterion("collect_deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountIsNotNull() {
            addCriterion("collect_deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountEqualTo(BigDecimal value) {
            addCriterion("collect_deposit_amount =", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountNotEqualTo(BigDecimal value) {
            addCriterion("collect_deposit_amount <>", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountGreaterThan(BigDecimal value) {
            addCriterion("collect_deposit_amount >", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("collect_deposit_amount >=", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountLessThan(BigDecimal value) {
            addCriterion("collect_deposit_amount <", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("collect_deposit_amount <=", value, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountIn(List<BigDecimal> values) {
            addCriterion("collect_deposit_amount in", values, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountNotIn(List<BigDecimal> values) {
            addCriterion("collect_deposit_amount not in", values, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collect_deposit_amount between", value1, value2, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCollectDepositAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("collect_deposit_amount not between", value1, value2, "collectDepositAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountIsNull() {
            addCriterion("current_change_amount is null");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountIsNotNull() {
            addCriterion("current_change_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountEqualTo(BigDecimal value) {
            addCriterion("current_change_amount =", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountNotEqualTo(BigDecimal value) {
            addCriterion("current_change_amount <>", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountGreaterThan(BigDecimal value) {
            addCriterion("current_change_amount >", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_change_amount >=", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountLessThan(BigDecimal value) {
            addCriterion("current_change_amount <", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_change_amount <=", value, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountIn(List<BigDecimal> values) {
            addCriterion("current_change_amount in", values, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountNotIn(List<BigDecimal> values) {
            addCriterion("current_change_amount not in", values, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_change_amount between", value1, value2, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andCurrentChangeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_change_amount not between", value1, value2, "currentChangeAmount");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceIsNull() {
            addCriterion("deposit_balance is null");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceIsNotNull() {
            addCriterion("deposit_balance is not null");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceEqualTo(BigDecimal value) {
            addCriterion("deposit_balance =", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceNotEqualTo(BigDecimal value) {
            addCriterion("deposit_balance <>", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceGreaterThan(BigDecimal value) {
            addCriterion("deposit_balance >", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_balance >=", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceLessThan(BigDecimal value) {
            addCriterion("deposit_balance <", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_balance <=", value, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceIn(List<BigDecimal> values) {
            addCriterion("deposit_balance in", values, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceNotIn(List<BigDecimal> values) {
            addCriterion("deposit_balance not in", values, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_balance between", value1, value2, "depositBalance");
            return (Criteria) this;
        }

        public Criteria andDepositBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_balance not between", value1, value2, "depositBalance");
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