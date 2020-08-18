package com.jf.entity;

import java.util.ArrayList;
import java.util.List;

public class SysStatusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SysStatusExample() {
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

        public Criteria andTableNameIsNull() {
            addCriterion("TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("TABLE_NAME =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("TABLE_NAME <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("TABLE_NAME >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("TABLE_NAME <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("TABLE_NAME like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("TABLE_NAME not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("TABLE_NAME in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("TABLE_NAME not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("TABLE_NAME between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("TABLE_NAME not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andColNameIsNull() {
            addCriterion("COL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andColNameIsNotNull() {
            addCriterion("COL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andColNameEqualTo(String value) {
            addCriterion("COL_NAME =", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotEqualTo(String value) {
            addCriterion("COL_NAME <>", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThan(String value) {
            addCriterion("COL_NAME >", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThanOrEqualTo(String value) {
            addCriterion("COL_NAME >=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThan(String value) {
            addCriterion("COL_NAME <", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThanOrEqualTo(String value) {
            addCriterion("COL_NAME <=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLike(String value) {
            addCriterion("COL_NAME like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotLike(String value) {
            addCriterion("COL_NAME not like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameIn(List<String> values) {
            addCriterion("COL_NAME in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotIn(List<String> values) {
            addCriterion("COL_NAME not in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameBetween(String value1, String value2) {
            addCriterion("COL_NAME between", value1, value2, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotBetween(String value1, String value2) {
            addCriterion("COL_NAME not between", value1, value2, "colName");
            return (Criteria) this;
        }

        public Criteria andStatusValueIsNull() {
            addCriterion("STATUS_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andStatusValueIsNotNull() {
            addCriterion("STATUS_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusValueEqualTo(String value) {
            addCriterion("STATUS_VALUE =", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueNotEqualTo(String value) {
            addCriterion("STATUS_VALUE <>", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueGreaterThan(String value) {
            addCriterion("STATUS_VALUE >", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_VALUE >=", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueLessThan(String value) {
            addCriterion("STATUS_VALUE <", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueLessThanOrEqualTo(String value) {
            addCriterion("STATUS_VALUE <=", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueLike(String value) {
            addCriterion("STATUS_VALUE like", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueNotLike(String value) {
            addCriterion("STATUS_VALUE not like", value, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueIn(List<String> values) {
            addCriterion("STATUS_VALUE in", values, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueNotIn(List<String> values) {
            addCriterion("STATUS_VALUE not in", values, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueBetween(String value1, String value2) {
            addCriterion("STATUS_VALUE between", value1, value2, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusValueNotBetween(String value1, String value2) {
            addCriterion("STATUS_VALUE not between", value1, value2, "statusValue");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNull() {
            addCriterion("STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStatusDescIsNotNull() {
            addCriterion("STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDescEqualTo(String value) {
            addCriterion("STATUS_DESC =", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotEqualTo(String value) {
            addCriterion("STATUS_DESC <>", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThan(String value) {
            addCriterion("STATUS_DESC >", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_DESC >=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThan(String value) {
            addCriterion("STATUS_DESC <", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLessThanOrEqualTo(String value) {
            addCriterion("STATUS_DESC <=", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescLike(String value) {
            addCriterion("STATUS_DESC like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotLike(String value) {
            addCriterion("STATUS_DESC not like", value, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescIn(List<String> values) {
            addCriterion("STATUS_DESC in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotIn(List<String> values) {
            addCriterion("STATUS_DESC not in", values, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescBetween(String value1, String value2) {
            addCriterion("STATUS_DESC between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusDescNotBetween(String value1, String value2) {
            addCriterion("STATUS_DESC not between", value1, value2, "statusDesc");
            return (Criteria) this;
        }

        public Criteria andStatusOrderIsNull() {
            addCriterion("STATUS_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andStatusOrderIsNotNull() {
            addCriterion("STATUS_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andStatusOrderEqualTo(Integer value) {
            addCriterion("STATUS_ORDER =", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderNotEqualTo(Integer value) {
            addCriterion("STATUS_ORDER <>", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderGreaterThan(Integer value) {
            addCriterion("STATUS_ORDER >", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS_ORDER >=", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderLessThan(Integer value) {
            addCriterion("STATUS_ORDER <", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS_ORDER <=", value, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderIn(List<Integer> values) {
            addCriterion("STATUS_ORDER in", values, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderNotIn(List<Integer> values) {
            addCriterion("STATUS_ORDER not in", values, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderBetween(Integer value1, Integer value2) {
            addCriterion("STATUS_ORDER between", value1, value2, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andStatusOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS_ORDER not between", value1, value2, "statusOrder");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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