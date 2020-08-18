package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SubOrderExample() {
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

        public Criteria andCombineOrderIdIsNull() {
            addCriterion("combine_order_id is null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIsNotNull() {
            addCriterion("combine_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdEqualTo(Integer value) {
            addCriterion("combine_order_id =", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotEqualTo(Integer value) {
            addCriterion("combine_order_id <>", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThan(Integer value) {
            addCriterion("combine_order_id >", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id >=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThan(Integer value) {
            addCriterion("combine_order_id <", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("combine_order_id <=", value, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdIn(List<Integer> values) {
            addCriterion("combine_order_id in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotIn(List<Integer> values) {
            addCriterion("combine_order_id not in", values, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id between", value1, value2, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("combine_order_id not between", value1, value2, "combineOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNull() {
            addCriterion("sub_order_code is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIsNotNull() {
            addCriterion("sub_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeEqualTo(String value) {
            addCriterion("sub_order_code =", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotEqualTo(String value) {
            addCriterion("sub_order_code <>", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThan(String value) {
            addCriterion("sub_order_code >", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_code >=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThan(String value) {
            addCriterion("sub_order_code <", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("sub_order_code <=", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeLike(String value) {
            addCriterion("sub_order_code like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotLike(String value) {
            addCriterion("sub_order_code not like", value, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeIn(List<String> values) {
            addCriterion("sub_order_code in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotIn(List<String> values) {
            addCriterion("sub_order_code not in", values, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeBetween(String value1, String value2) {
            addCriterion("sub_order_code between", value1, value2, "subOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubOrderCodeNotBetween(String value1, String value2) {
            addCriterion("sub_order_code not between", value1, value2, "subOrderCode");
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

        public Criteria andMchtTypeIsNull() {
            addCriterion("mcht_type is null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIsNotNull() {
            addCriterion("mcht_type is not null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeEqualTo(String value) {
            addCriterion("mcht_type =", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotEqualTo(String value) {
            addCriterion("mcht_type <>", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThan(String value) {
            addCriterion("mcht_type >", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_type >=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThan(String value) {
            addCriterion("mcht_type <", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThanOrEqualTo(String value) {
            addCriterion("mcht_type <=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLike(String value) {
            addCriterion("mcht_type like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotLike(String value) {
            addCriterion("mcht_type not like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIn(List<String> values) {
            addCriterion("mcht_type in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotIn(List<String> values) {
            addCriterion("mcht_type not in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeBetween(String value1, String value2) {
            addCriterion("mcht_type between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotBetween(String value1, String value2) {
            addCriterion("mcht_type not between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIsNull() {
            addCriterion("is_manage_self is null");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIsNotNull() {
            addCriterion("is_manage_self is not null");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfEqualTo(String value) {
            addCriterion("is_manage_self =", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotEqualTo(String value) {
            addCriterion("is_manage_self <>", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfGreaterThan(String value) {
            addCriterion("is_manage_self >", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfGreaterThanOrEqualTo(String value) {
            addCriterion("is_manage_self >=", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLessThan(String value) {
            addCriterion("is_manage_self <", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLessThanOrEqualTo(String value) {
            addCriterion("is_manage_self <=", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfLike(String value) {
            addCriterion("is_manage_self like", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotLike(String value) {
            addCriterion("is_manage_self not like", value, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfIn(List<String> values) {
            addCriterion("is_manage_self in", values, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotIn(List<String> values) {
            addCriterion("is_manage_self not in", values, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfBetween(String value1, String value2) {
            addCriterion("is_manage_self between", value1, value2, "isManageSelf");
            return (Criteria) this;
        }

        public Criteria andIsManageSelfNotBetween(String value1, String value2) {
            addCriterion("is_manage_self not between", value1, value2, "isManageSelf");
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

        public Criteria andPlatformPreferentialIsNull() {
            addCriterion("platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIsNotNull() {
            addCriterion("platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("platform_preferential =", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <>", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("platform_preferential >", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential >=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("platform_preferential <", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("platform_preferential in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("platform_preferential not in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential not between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialIsNull() {
            addCriterion("mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialIsNotNull() {
            addCriterion("mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential =", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential <>", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("mcht_preferential >", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential >=", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("mcht_preferential <", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential <=", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential in", values, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential not in", values, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential between", value1, value2, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential not between", value1, value2, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNull() {
            addCriterion("integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNotNull() {
            addCriterion("integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("integral_preferential =", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <>", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("integral_preferential >", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential >=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("integral_preferential <", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("integral_preferential in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("integral_preferential not in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential between", value1, value2, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential not between", value1, value2, "integralPreferential");
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

        public Criteria andReceiptDateIsNull() {
            addCriterion("receipt_date is null");
            return (Criteria) this;
        }

        public Criteria andReceiptDateIsNotNull() {
            addCriterion("receipt_date is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptDateEqualTo(Date value) {
            addCriterion("receipt_date =", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotEqualTo(Date value) {
            addCriterion("receipt_date <>", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateGreaterThan(Date value) {
            addCriterion("receipt_date >", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateGreaterThanOrEqualTo(Date value) {
            addCriterion("receipt_date >=", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateLessThan(Date value) {
            addCriterion("receipt_date <", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateLessThanOrEqualTo(Date value) {
            addCriterion("receipt_date <=", value, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateIn(List<Date> values) {
            addCriterion("receipt_date in", values, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotIn(List<Date> values) {
            addCriterion("receipt_date not in", values, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateBetween(Date value1, Date value2) {
            addCriterion("receipt_date between", value1, value2, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiptDateNotBetween(Date value1, Date value2) {
            addCriterion("receipt_date not between", value1, value2, "receiptDate");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeIsNull() {
            addCriterion("receiver_type is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeIsNotNull() {
            addCriterion("receiver_type is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeEqualTo(String value) {
            addCriterion("receiver_type =", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotEqualTo(String value) {
            addCriterion("receiver_type <>", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeGreaterThan(String value) {
            addCriterion("receiver_type >", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_type >=", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLessThan(String value) {
            addCriterion("receiver_type <", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLessThanOrEqualTo(String value) {
            addCriterion("receiver_type <=", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLike(String value) {
            addCriterion("receiver_type like", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotLike(String value) {
            addCriterion("receiver_type not like", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeIn(List<String> values) {
            addCriterion("receiver_type in", values, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotIn(List<String> values) {
            addCriterion("receiver_type not in", values, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeBetween(String value1, String value2) {
            addCriterion("receiver_type between", value1, value2, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotBetween(String value1, String value2) {
            addCriterion("receiver_type not between", value1, value2, "receiverType");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIsNull() {
            addCriterion("is_user_del is null");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIsNotNull() {
            addCriterion("is_user_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsUserDelEqualTo(String value) {
            addCriterion("is_user_del =", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotEqualTo(String value) {
            addCriterion("is_user_del <>", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelGreaterThan(String value) {
            addCriterion("is_user_del >", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelGreaterThanOrEqualTo(String value) {
            addCriterion("is_user_del >=", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLessThan(String value) {
            addCriterion("is_user_del <", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLessThanOrEqualTo(String value) {
            addCriterion("is_user_del <=", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelLike(String value) {
            addCriterion("is_user_del like", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotLike(String value) {
            addCriterion("is_user_del not like", value, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelIn(List<String> values) {
            addCriterion("is_user_del in", values, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotIn(List<String> values) {
            addCriterion("is_user_del not in", values, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelBetween(String value1, String value2) {
            addCriterion("is_user_del between", value1, value2, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andIsUserDelNotBetween(String value1, String value2) {
            addCriterion("is_user_del not between", value1, value2, "isUserDel");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIsNull() {
            addCriterion("remarks_color is null");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIsNotNull() {
            addCriterion("remarks_color is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksColorEqualTo(String value) {
            addCriterion("remarks_color =", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotEqualTo(String value) {
            addCriterion("remarks_color <>", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorGreaterThan(String value) {
            addCriterion("remarks_color >", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorGreaterThanOrEqualTo(String value) {
            addCriterion("remarks_color >=", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLessThan(String value) {
            addCriterion("remarks_color <", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLessThanOrEqualTo(String value) {
            addCriterion("remarks_color <=", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorLike(String value) {
            addCriterion("remarks_color like", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotLike(String value) {
            addCriterion("remarks_color not like", value, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorIn(List<String> values) {
            addCriterion("remarks_color in", values, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotIn(List<String> values) {
            addCriterion("remarks_color not in", values, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorBetween(String value1, String value2) {
            addCriterion("remarks_color between", value1, value2, "remarksColor");
            return (Criteria) this;
        }

        public Criteria andRemarksColorNotBetween(String value1, String value2) {
            addCriterion("remarks_color not between", value1, value2, "remarksColor");
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

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(String value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(String value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(String value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(String value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(String value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(String value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLike(String value) {
            addCriterion("express_id like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotLike(String value) {
            addCriterion("express_id not like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<String> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<String> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(String value1, String value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(String value1, String value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("express_no is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("express_no is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("express_no =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("express_no <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("express_no >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("express_no >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("express_no <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("express_no <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("express_no like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("express_no not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("express_no in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("express_no not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("express_no between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("express_no not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNull() {
            addCriterion("delivery_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNotNull() {
            addCriterion("delivery_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateEqualTo(Date value) {
            addCriterion("delivery_date =", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotEqualTo(Date value) {
            addCriterion("delivery_date <>", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThan(Date value) {
            addCriterion("delivery_date >", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_date >=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThan(Date value) {
            addCriterion("delivery_date <", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterion("delivery_date <=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIn(List<Date> values) {
            addCriterion("delivery_date in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotIn(List<Date> values) {
            addCriterion("delivery_date not in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateBetween(Date value1, Date value2) {
            addCriterion("delivery_date between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterion("delivery_date not between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNull() {
            addCriterion("complete_date is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNotNull() {
            addCriterion("complete_date is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateEqualTo(Date value) {
            addCriterion("complete_date =", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotEqualTo(Date value) {
            addCriterion("complete_date <>", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThan(Date value) {
            addCriterion("complete_date >", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date >=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThan(Date value) {
            addCriterion("complete_date <", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThanOrEqualTo(Date value) {
            addCriterion("complete_date <=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIn(List<Date> values) {
            addCriterion("complete_date in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotIn(List<Date> values) {
            addCriterion("complete_date not in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateBetween(Date value1, Date value2) {
            addCriterion("complete_date between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotBetween(Date value1, Date value2) {
            addCriterion("complete_date not between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNull() {
            addCriterion("close_date is null");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNotNull() {
            addCriterion("close_date is not null");
            return (Criteria) this;
        }

        public Criteria andCloseDateEqualTo(Date value) {
            addCriterion("close_date =", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotEqualTo(Date value) {
            addCriterion("close_date <>", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThan(Date value) {
            addCriterion("close_date >", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("close_date >=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThan(Date value) {
            addCriterion("close_date <", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThanOrEqualTo(Date value) {
            addCriterion("close_date <=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateIn(List<Date> values) {
            addCriterion("close_date in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotIn(List<Date> values) {
            addCriterion("close_date not in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateBetween(Date value1, Date value2) {
            addCriterion("close_date between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotBetween(Date value1, Date value2) {
            addCriterion("close_date not between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateIsNull() {
            addCriterion("delivery_last_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateIsNotNull() {
            addCriterion("delivery_last_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateEqualTo(Date value) {
            addCriterion("delivery_last_date =", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateNotEqualTo(Date value) {
            addCriterion("delivery_last_date <>", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateGreaterThan(Date value) {
            addCriterion("delivery_last_date >", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_last_date >=", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateLessThan(Date value) {
            addCriterion("delivery_last_date <", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateLessThanOrEqualTo(Date value) {
            addCriterion("delivery_last_date <=", value, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateIn(List<Date> values) {
            addCriterion("delivery_last_date in", values, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateNotIn(List<Date> values) {
            addCriterion("delivery_last_date not in", values, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateBetween(Date value1, Date value2) {
            addCriterion("delivery_last_date between", value1, value2, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryLastDateNotBetween(Date value1, Date value2) {
            addCriterion("delivery_last_date not between", value1, value2, "deliveryLastDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeIsNull() {
            addCriterion("delivery_overtime is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeIsNotNull() {
            addCriterion("delivery_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeEqualTo(Integer value) {
            addCriterion("delivery_overtime =", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeNotEqualTo(Integer value) {
            addCriterion("delivery_overtime <>", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeGreaterThan(Integer value) {
            addCriterion("delivery_overtime >", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_overtime >=", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeLessThan(Integer value) {
            addCriterion("delivery_overtime <", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_overtime <=", value, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeIn(List<Integer> values) {
            addCriterion("delivery_overtime in", values, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeNotIn(List<Integer> values) {
            addCriterion("delivery_overtime not in", values, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("delivery_overtime between", value1, value2, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andDeliveryOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_overtime not between", value1, value2, "deliveryOvertime");
            return (Criteria) this;
        }

        public Criteria andFollowByIsNull() {
            addCriterion("follow_by is null");
            return (Criteria) this;
        }

        public Criteria andFollowByIsNotNull() {
            addCriterion("follow_by is not null");
            return (Criteria) this;
        }

        public Criteria andFollowByEqualTo(Integer value) {
            addCriterion("follow_by =", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotEqualTo(Integer value) {
            addCriterion("follow_by <>", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByGreaterThan(Integer value) {
            addCriterion("follow_by >", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByGreaterThanOrEqualTo(Integer value) {
            addCriterion("follow_by >=", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByLessThan(Integer value) {
            addCriterion("follow_by <", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByLessThanOrEqualTo(Integer value) {
            addCriterion("follow_by <=", value, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByIn(List<Integer> values) {
            addCriterion("follow_by in", values, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotIn(List<Integer> values) {
            addCriterion("follow_by not in", values, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByBetween(Integer value1, Integer value2) {
            addCriterion("follow_by between", value1, value2, "followBy");
            return (Criteria) this;
        }

        public Criteria andFollowByNotBetween(Integer value1, Integer value2) {
            addCriterion("follow_by not between", value1, value2, "followBy");
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

        public Criteria andIsAllowModifyCommentIsNull() {
            addCriterion("is_allow_modify_comment is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentIsNotNull() {
            addCriterion("is_allow_modify_comment is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentEqualTo(String value) {
            addCriterion("is_allow_modify_comment =", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentNotEqualTo(String value) {
            addCriterion("is_allow_modify_comment <>", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentGreaterThan(String value) {
            addCriterion("is_allow_modify_comment >", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentGreaterThanOrEqualTo(String value) {
            addCriterion("is_allow_modify_comment >=", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentLessThan(String value) {
            addCriterion("is_allow_modify_comment <", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentLessThanOrEqualTo(String value) {
            addCriterion("is_allow_modify_comment <=", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentLike(String value) {
            addCriterion("is_allow_modify_comment like", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentNotLike(String value) {
            addCriterion("is_allow_modify_comment not like", value, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentIn(List<String> values) {
            addCriterion("is_allow_modify_comment in", values, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentNotIn(List<String> values) {
            addCriterion("is_allow_modify_comment not in", values, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentBetween(String value1, String value2) {
            addCriterion("is_allow_modify_comment between", value1, value2, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andIsAllowModifyCommentNotBetween(String value1, String value2) {
            addCriterion("is_allow_modify_comment not between", value1, value2, "isAllowModifyComment");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountIsNull() {
            addCriterion("member_remind_count is null");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountIsNotNull() {
            addCriterion("member_remind_count is not null");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountEqualTo(Integer value) {
            addCriterion("member_remind_count =", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountNotEqualTo(Integer value) {
            addCriterion("member_remind_count <>", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountGreaterThan(Integer value) {
            addCriterion("member_remind_count >", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_remind_count >=", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountLessThan(Integer value) {
            addCriterion("member_remind_count <", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountLessThanOrEqualTo(Integer value) {
            addCriterion("member_remind_count <=", value, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountIn(List<Integer> values) {
            addCriterion("member_remind_count in", values, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountNotIn(List<Integer> values) {
            addCriterion("member_remind_count not in", values, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountBetween(Integer value1, Integer value2) {
            addCriterion("member_remind_count between", value1, value2, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindCountNotBetween(Integer value1, Integer value2) {
            addCriterion("member_remind_count not between", value1, value2, "memberRemindCount");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateIsNull() {
            addCriterion("member_remind_date is null");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateIsNotNull() {
            addCriterion("member_remind_date is not null");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateEqualTo(Date value) {
            addCriterion("member_remind_date =", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateNotEqualTo(Date value) {
            addCriterion("member_remind_date <>", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateGreaterThan(Date value) {
            addCriterion("member_remind_date >", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateGreaterThanOrEqualTo(Date value) {
            addCriterion("member_remind_date >=", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateLessThan(Date value) {
            addCriterion("member_remind_date <", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateLessThanOrEqualTo(Date value) {
            addCriterion("member_remind_date <=", value, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateIn(List<Date> values) {
            addCriterion("member_remind_date in", values, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateNotIn(List<Date> values) {
            addCriterion("member_remind_date not in", values, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateBetween(Date value1, Date value2) {
            addCriterion("member_remind_date between", value1, value2, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andMemberRemindDateNotBetween(Date value1, Date value2) {
            addCriterion("member_remind_date not between", value1, value2, "memberRemindDate");
            return (Criteria) this;
        }

        public Criteria andIsSpecialIsNull() {
            addCriterion("is_special is null");
            return (Criteria) this;
        }

        public Criteria andIsSpecialIsNotNull() {
            addCriterion("is_special is not null");
            return (Criteria) this;
        }

        public Criteria andIsSpecialEqualTo(String value) {
            addCriterion("is_special =", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialNotEqualTo(String value) {
            addCriterion("is_special <>", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialGreaterThan(String value) {
            addCriterion("is_special >", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialGreaterThanOrEqualTo(String value) {
            addCriterion("is_special >=", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialLessThan(String value) {
            addCriterion("is_special <", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialLessThanOrEqualTo(String value) {
            addCriterion("is_special <=", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialLike(String value) {
            addCriterion("is_special like", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialNotLike(String value) {
            addCriterion("is_special not like", value, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialIn(List<String> values) {
            addCriterion("is_special in", values, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialNotIn(List<String> values) {
            addCriterion("is_special not in", values, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialBetween(String value1, String value2) {
            addCriterion("is_special between", value1, value2, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andIsSpecialNotBetween(String value1, String value2) {
            addCriterion("is_special not between", value1, value2, "isSpecial");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNull() {
            addCriterion("distribution_amount is null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNotNull() {
            addCriterion("distribution_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountEqualTo(BigDecimal value) {
            addCriterion("distribution_amount =", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <>", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThan(BigDecimal value) {
            addCriterion("distribution_amount >", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount >=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThan(BigDecimal value) {
            addCriterion("distribution_amount <", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIn(List<BigDecimal> values) {
            addCriterion("distribution_amount in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotIn(List<BigDecimal> values) {
            addCriterion("distribution_amount not in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount not between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("audit_status like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("audit_status not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNull() {
            addCriterion("audit_date is null");
            return (Criteria) this;
        }

        public Criteria andAuditDateIsNotNull() {
            addCriterion("audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDateEqualTo(Date value) {
            addCriterion("audit_date =", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotEqualTo(Date value) {
            addCriterion("audit_date <>", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThan(Date value) {
            addCriterion("audit_date >", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_date >=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThan(Date value) {
            addCriterion("audit_date <", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("audit_date <=", value, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateIn(List<Date> values) {
            addCriterion("audit_date in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotIn(List<Date> values) {
            addCriterion("audit_date not in", values, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateBetween(Date value1, Date value2) {
            addCriterion("audit_date between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("audit_date not between", value1, value2, "auditDate");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusIsNull() {
            addCriterion("audit_reason_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusIsNotNull() {
            addCriterion("audit_reason_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusEqualTo(String value) {
            addCriterion("audit_reason_status =", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusNotEqualTo(String value) {
            addCriterion("audit_reason_status <>", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusGreaterThan(String value) {
            addCriterion("audit_reason_status >", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusGreaterThanOrEqualTo(String value) {
            addCriterion("audit_reason_status >=", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusLessThan(String value) {
            addCriterion("audit_reason_status <", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusLessThanOrEqualTo(String value) {
            addCriterion("audit_reason_status <=", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusLike(String value) {
            addCriterion("audit_reason_status like", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusNotLike(String value) {
            addCriterion("audit_reason_status not like", value, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusIn(List<String> values) {
            addCriterion("audit_reason_status in", values, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusNotIn(List<String> values) {
            addCriterion("audit_reason_status not in", values, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusBetween(String value1, String value2) {
            addCriterion("audit_reason_status between", value1, value2, "auditReasonStatus");
            return (Criteria) this;
        }

        public Criteria andAuditReasonStatusNotBetween(String value1, String value2) {
            addCriterion("audit_reason_status not between", value1, value2, "auditReasonStatus");
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