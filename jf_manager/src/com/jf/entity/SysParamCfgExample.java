package com.jf.entity;

import java.util.ArrayList;
import java.util.List;

public class SysParamCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SysParamCfgExample() {
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

        public Criteria andParamIdIsNull() {
            addCriterion("param_id is null");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNotNull() {
            addCriterion("param_id is not null");
            return (Criteria) this;
        }

        public Criteria andParamIdEqualTo(Integer value) {
            addCriterion("param_id =", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotEqualTo(Integer value) {
            addCriterion("param_id <>", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThan(Integer value) {
            addCriterion("param_id >", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("param_id >=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThan(Integer value) {
            addCriterion("param_id <", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThanOrEqualTo(Integer value) {
            addCriterion("param_id <=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdIn(List<Integer> values) {
            addCriterion("param_id in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotIn(List<Integer> values) {
            addCriterion("param_id not in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdBetween(Integer value1, Integer value2) {
            addCriterion("param_id between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("param_id not between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamCodeIsNull() {
            addCriterion("param_code is null");
            return (Criteria) this;
        }

        public Criteria andParamCodeIsNotNull() {
            addCriterion("param_code is not null");
            return (Criteria) this;
        }

        public Criteria andParamCodeEqualTo(String value) {
            addCriterion("param_code =", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeNotEqualTo(String value) {
            addCriterion("param_code <>", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeGreaterThan(String value) {
            addCriterion("param_code >", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeGreaterThanOrEqualTo(String value) {
            addCriterion("param_code >=", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeLessThan(String value) {
            addCriterion("param_code <", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeLessThanOrEqualTo(String value) {
            addCriterion("param_code <=", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeLike(String value) {
            addCriterion("param_code like", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeNotLike(String value) {
            addCriterion("param_code not like", value, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeIn(List<String> values) {
            addCriterion("param_code in", values, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeNotIn(List<String> values) {
            addCriterion("param_code not in", values, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeBetween(String value1, String value2) {
            addCriterion("param_code between", value1, value2, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamCodeNotBetween(String value1, String value2) {
            addCriterion("param_code not between", value1, value2, "paramCode");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNull() {
            addCriterion("param_name is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("param_name is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("param_name =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("param_name <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("param_name >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("param_name >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("param_name <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("param_name <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLike(String value) {
            addCriterion("param_name like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotLike(String value) {
            addCriterion("param_name not like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("param_name in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("param_name not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("param_name between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("param_name not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNull() {
            addCriterion("param_value is null");
            return (Criteria) this;
        }

        public Criteria andParamValueIsNotNull() {
            addCriterion("param_value is not null");
            return (Criteria) this;
        }

        public Criteria andParamValueEqualTo(String value) {
            addCriterion("param_value =", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotEqualTo(String value) {
            addCriterion("param_value <>", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThan(String value) {
            addCriterion("param_value >", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueGreaterThanOrEqualTo(String value) {
            addCriterion("param_value >=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThan(String value) {
            addCriterion("param_value <", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLessThanOrEqualTo(String value) {
            addCriterion("param_value <=", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueLike(String value) {
            addCriterion("param_value like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotLike(String value) {
            addCriterion("param_value not like", value, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueIn(List<String> values) {
            addCriterion("param_value in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotIn(List<String> values) {
            addCriterion("param_value not in", values, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueBetween(String value1, String value2) {
            addCriterion("param_value between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamValueNotBetween(String value1, String value2) {
            addCriterion("param_value not between", value1, value2, "paramValue");
            return (Criteria) this;
        }

        public Criteria andParamOrderIsNull() {
            addCriterion("param_order is null");
            return (Criteria) this;
        }

        public Criteria andParamOrderIsNotNull() {
            addCriterion("param_order is not null");
            return (Criteria) this;
        }

        public Criteria andParamOrderEqualTo(String value) {
            addCriterion("param_order =", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotEqualTo(String value) {
            addCriterion("param_order <>", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThan(String value) {
            addCriterion("param_order >", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThanOrEqualTo(String value) {
            addCriterion("param_order >=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThan(String value) {
            addCriterion("param_order <", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThanOrEqualTo(String value) {
            addCriterion("param_order <=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLike(String value) {
            addCriterion("param_order like", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotLike(String value) {
            addCriterion("param_order not like", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderIn(List<String> values) {
            addCriterion("param_order in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotIn(List<String> values) {
            addCriterion("param_order not in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderBetween(String value1, String value2) {
            addCriterion("param_order between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotBetween(String value1, String value2) {
            addCriterion("param_order not between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamDescIsNull() {
            addCriterion("param_desc is null");
            return (Criteria) this;
        }

        public Criteria andParamDescIsNotNull() {
            addCriterion("param_desc is not null");
            return (Criteria) this;
        }

        public Criteria andParamDescEqualTo(String value) {
            addCriterion("param_desc =", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotEqualTo(String value) {
            addCriterion("param_desc <>", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThan(String value) {
            addCriterion("param_desc >", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThanOrEqualTo(String value) {
            addCriterion("param_desc >=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThan(String value) {
            addCriterion("param_desc <", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThanOrEqualTo(String value) {
            addCriterion("param_desc <=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLike(String value) {
            addCriterion("param_desc like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotLike(String value) {
            addCriterion("param_desc not like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescIn(List<String> values) {
            addCriterion("param_desc in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotIn(List<String> values) {
            addCriterion("param_desc not in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescBetween(String value1, String value2) {
            addCriterion("param_desc between", value1, value2, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotBetween(String value1, String value2) {
            addCriterion("param_desc not between", value1, value2, "paramDesc");
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