package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayToMchtLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public PayToMchtLogExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNull() {
            addCriterion("mcht_settle_order_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNotNull() {
            addCriterion("mcht_settle_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id =", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <>", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThan(Integer value) {
            addCriterion("mcht_settle_order_id >", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id >=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThan(Integer value) {
            addCriterion("mcht_settle_order_id <", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id not in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id between", value1, value2, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id not between", value1, value2, "mchtSettleOrderId");
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

        public Criteria andPayCodeIsNull() {
            addCriterion("pay_code is null");
            return (Criteria) this;
        }

        public Criteria andPayCodeIsNotNull() {
            addCriterion("pay_code is not null");
            return (Criteria) this;
        }

        public Criteria andPayCodeEqualTo(String value) {
            addCriterion("pay_code =", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotEqualTo(String value) {
            addCriterion("pay_code <>", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeGreaterThan(String value) {
            addCriterion("pay_code >", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_code >=", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLessThan(String value) {
            addCriterion("pay_code <", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLessThanOrEqualTo(String value) {
            addCriterion("pay_code <=", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeLike(String value) {
            addCriterion("pay_code like", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotLike(String value) {
            addCriterion("pay_code not like", value, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeIn(List<String> values) {
            addCriterion("pay_code in", values, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotIn(List<String> values) {
            addCriterion("pay_code not in", values, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeBetween(String value1, String value2) {
            addCriterion("pay_code between", value1, value2, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayCodeNotBetween(String value1, String value2) {
            addCriterion("pay_code not between", value1, value2, "payCode");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIsNull() {
            addCriterion("pay_staff_id is null");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIsNotNull() {
            addCriterion("pay_staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdEqualTo(Integer value) {
            addCriterion("pay_staff_id =", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotEqualTo(Integer value) {
            addCriterion("pay_staff_id <>", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdGreaterThan(Integer value) {
            addCriterion("pay_staff_id >", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_staff_id >=", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdLessThan(Integer value) {
            addCriterion("pay_staff_id <", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdLessThanOrEqualTo(Integer value) {
            addCriterion("pay_staff_id <=", value, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdIn(List<Integer> values) {
            addCriterion("pay_staff_id in", values, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotIn(List<Integer> values) {
            addCriterion("pay_staff_id not in", values, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdBetween(Integer value1, Integer value2) {
            addCriterion("pay_staff_id between", value1, value2, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andPayStaffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_staff_id not between", value1, value2, "payStaffId");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNull() {
            addCriterion("settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNotNull() {
            addCriterion("settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountEqualTo(BigDecimal value) {
            addCriterion("settle_amount =", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("settle_amount <>", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("settle_amount >", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount >=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThan(BigDecimal value) {
            addCriterion("settle_amount <", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount <=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIn(List<BigDecimal> values) {
            addCriterion("settle_amount in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("settle_amount not in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount not between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNull() {
            addCriterion("deposit_amount is null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIsNotNull() {
            addCriterion("deposit_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDepositAmountEqualTo(BigDecimal value) {
            addCriterion("deposit_amount =", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotEqualTo(BigDecimal value) {
            addCriterion("deposit_amount <>", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThan(BigDecimal value) {
            addCriterion("deposit_amount >", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_amount >=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThan(BigDecimal value) {
            addCriterion("deposit_amount <", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit_amount <=", value, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountIn(List<BigDecimal> values) {
            addCriterion("deposit_amount in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotIn(List<BigDecimal> values) {
            addCriterion("deposit_amount not in", values, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_amount between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andDepositAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit_amount not between", value1, value2, "depositAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIsNull() {
            addCriterion("need_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIsNotNull() {
            addCriterion("need_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount =", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount <>", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountGreaterThan(BigDecimal value) {
            addCriterion("need_pay_amount >", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount >=", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountLessThan(BigDecimal value) {
            addCriterion("need_pay_amount <", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay_amount <=", value, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountIn(List<BigDecimal> values) {
            addCriterion("need_pay_amount in", values, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("need_pay_amount not in", values, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay_amount between", value1, value2, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andNeedPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay_amount not between", value1, value2, "needPayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountIsNull() {
            addCriterion("before_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountIsNotNull() {
            addCriterion("before_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountEqualTo(BigDecimal value) {
            addCriterion("before_pay_amount =", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountNotEqualTo(BigDecimal value) {
            addCriterion("before_pay_amount <>", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountGreaterThan(BigDecimal value) {
            addCriterion("before_pay_amount >", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("before_pay_amount >=", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountLessThan(BigDecimal value) {
            addCriterion("before_pay_amount <", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("before_pay_amount <=", value, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountIn(List<BigDecimal> values) {
            addCriterion("before_pay_amount in", values, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountNotIn(List<BigDecimal> values) {
            addCriterion("before_pay_amount not in", values, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_pay_amount between", value1, value2, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andBeforePayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("before_pay_amount not between", value1, value2, "beforePayAmount");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNull() {
            addCriterion("confirm_status is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNotNull() {
            addCriterion("confirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusEqualTo(String value) {
            addCriterion("confirm_status =", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotEqualTo(String value) {
            addCriterion("confirm_status <>", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThan(String value) {
            addCriterion("confirm_status >", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("confirm_status >=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThan(String value) {
            addCriterion("confirm_status <", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThanOrEqualTo(String value) {
            addCriterion("confirm_status <=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLike(String value) {
            addCriterion("confirm_status like", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotLike(String value) {
            addCriterion("confirm_status not like", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIn(List<String> values) {
            addCriterion("confirm_status in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotIn(List<String> values) {
            addCriterion("confirm_status not in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusBetween(String value1, String value2) {
            addCriterion("confirm_status between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotBetween(String value1, String value2) {
            addCriterion("confirm_status not between", value1, value2, "confirmStatus");
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