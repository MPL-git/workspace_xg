package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TmpSubOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public TmpSubOrderExample() {
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

        public Criteria andSubOrderIdIsNull() {
            addCriterion("sub_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIsNotNull() {
            addCriterion("sub_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdEqualTo(Integer value) {
            addCriterion("sub_order_id =", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotEqualTo(Integer value) {
            addCriterion("sub_order_id <>", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThan(Integer value) {
            addCriterion("sub_order_id >", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id >=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThan(Integer value) {
            addCriterion("sub_order_id <", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_id <=", value, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdIn(List<Integer> values) {
            addCriterion("sub_order_id in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotIn(List<Integer> values) {
            addCriterion("sub_order_id not in", values, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id between", value1, value2, "subOrderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_id not between", value1, value2, "subOrderId");
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

        public Criteria andPlatAmountIsNull() {
            addCriterion("plat_amount is null");
            return (Criteria) this;
        }

        public Criteria andPlatAmountIsNotNull() {
            addCriterion("plat_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPlatAmountEqualTo(BigDecimal value) {
            addCriterion("plat_amount =", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountNotEqualTo(BigDecimal value) {
            addCriterion("plat_amount <>", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountGreaterThan(BigDecimal value) {
            addCriterion("plat_amount >", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plat_amount >=", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountLessThan(BigDecimal value) {
            addCriterion("plat_amount <", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plat_amount <=", value, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountIn(List<BigDecimal> values) {
            addCriterion("plat_amount in", values, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountNotIn(List<BigDecimal> values) {
            addCriterion("plat_amount not in", values, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plat_amount between", value1, value2, "platAmount");
            return (Criteria) this;
        }

        public Criteria andPlatAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plat_amount not between", value1, value2, "platAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountIsNull() {
            addCriterion("integral_amount is null");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountIsNotNull() {
            addCriterion("integral_amount is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountEqualTo(BigDecimal value) {
            addCriterion("integral_amount =", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountNotEqualTo(BigDecimal value) {
            addCriterion("integral_amount <>", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountGreaterThan(BigDecimal value) {
            addCriterion("integral_amount >", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_amount >=", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountLessThan(BigDecimal value) {
            addCriterion("integral_amount <", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_amount <=", value, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountIn(List<BigDecimal> values) {
            addCriterion("integral_amount in", values, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountNotIn(List<BigDecimal> values) {
            addCriterion("integral_amount not in", values, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_amount between", value1, value2, "integralAmount");
            return (Criteria) this;
        }

        public Criteria andIntegralAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_amount not between", value1, value2, "integralAmount");
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