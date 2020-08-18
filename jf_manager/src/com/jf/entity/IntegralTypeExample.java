package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public IntegralTypeExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTallyModeIsNull() {
            addCriterion("tally_mode is null");
            return (Criteria) this;
        }

        public Criteria andTallyModeIsNotNull() {
            addCriterion("tally_mode is not null");
            return (Criteria) this;
        }

        public Criteria andTallyModeEqualTo(String value) {
            addCriterion("tally_mode =", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeNotEqualTo(String value) {
            addCriterion("tally_mode <>", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeGreaterThan(String value) {
            addCriterion("tally_mode >", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeGreaterThanOrEqualTo(String value) {
            addCriterion("tally_mode >=", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeLessThan(String value) {
            addCriterion("tally_mode <", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeLessThanOrEqualTo(String value) {
            addCriterion("tally_mode <=", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeLike(String value) {
            addCriterion("tally_mode like", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeNotLike(String value) {
            addCriterion("tally_mode not like", value, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeIn(List<String> values) {
            addCriterion("tally_mode in", values, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeNotIn(List<String> values) {
            addCriterion("tally_mode not in", values, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeBetween(String value1, String value2) {
            addCriterion("tally_mode between", value1, value2, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andTallyModeNotBetween(String value1, String value2) {
            addCriterion("tally_mode not between", value1, value2, "tallyMode");
            return (Criteria) this;
        }

        public Criteria andIntTypeIsNull() {
            addCriterion("int_type is null");
            return (Criteria) this;
        }

        public Criteria andIntTypeIsNotNull() {
            addCriterion("int_type is not null");
            return (Criteria) this;
        }

        public Criteria andIntTypeEqualTo(String value) {
            addCriterion("int_type =", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeNotEqualTo(String value) {
            addCriterion("int_type <>", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeGreaterThan(String value) {
            addCriterion("int_type >", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeGreaterThanOrEqualTo(String value) {
            addCriterion("int_type >=", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeLessThan(String value) {
            addCriterion("int_type <", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeLessThanOrEqualTo(String value) {
            addCriterion("int_type <=", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeLike(String value) {
            addCriterion("int_type like", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeNotLike(String value) {
            addCriterion("int_type not like", value, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeIn(List<String> values) {
            addCriterion("int_type in", values, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeNotIn(List<String> values) {
            addCriterion("int_type not in", values, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeBetween(String value1, String value2) {
            addCriterion("int_type between", value1, value2, "intType");
            return (Criteria) this;
        }

        public Criteria andIntTypeNotBetween(String value1, String value2) {
            addCriterion("int_type not between", value1, value2, "intType");
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

        public Criteria andIChargeIsNull() {
            addCriterion("i_charge is null");
            return (Criteria) this;
        }

        public Criteria andIChargeIsNotNull() {
            addCriterion("i_charge is not null");
            return (Criteria) this;
        }

        public Criteria andIChargeEqualTo(Integer value) {
            addCriterion("i_charge =", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeNotEqualTo(Integer value) {
            addCriterion("i_charge <>", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeGreaterThan(Integer value) {
            addCriterion("i_charge >", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeGreaterThanOrEqualTo(Integer value) {
            addCriterion("i_charge >=", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeLessThan(Integer value) {
            addCriterion("i_charge <", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeLessThanOrEqualTo(Integer value) {
            addCriterion("i_charge <=", value, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeIn(List<Integer> values) {
            addCriterion("i_charge in", values, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeNotIn(List<Integer> values) {
            addCriterion("i_charge not in", values, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeBetween(Integer value1, Integer value2) {
            addCriterion("i_charge between", value1, value2, "iCharge");
            return (Criteria) this;
        }

        public Criteria andIChargeNotBetween(Integer value1, Integer value2) {
            addCriterion("i_charge not between", value1, value2, "iCharge");
            return (Criteria) this;
        }

        public Criteria andGrowTypeIsNull() {
            addCriterion("grow_type is null");
            return (Criteria) this;
        }

        public Criteria andGrowTypeIsNotNull() {
            addCriterion("grow_type is not null");
            return (Criteria) this;
        }

        public Criteria andGrowTypeEqualTo(String value) {
            addCriterion("grow_type =", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeNotEqualTo(String value) {
            addCriterion("grow_type <>", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeGreaterThan(String value) {
            addCriterion("grow_type >", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("grow_type >=", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeLessThan(String value) {
            addCriterion("grow_type <", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeLessThanOrEqualTo(String value) {
            addCriterion("grow_type <=", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeLike(String value) {
            addCriterion("grow_type like", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeNotLike(String value) {
            addCriterion("grow_type not like", value, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeIn(List<String> values) {
            addCriterion("grow_type in", values, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeNotIn(List<String> values) {
            addCriterion("grow_type not in", values, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeBetween(String value1, String value2) {
            addCriterion("grow_type between", value1, value2, "growType");
            return (Criteria) this;
        }

        public Criteria andGrowTypeNotBetween(String value1, String value2) {
            addCriterion("grow_type not between", value1, value2, "growType");
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

        public Criteria andGChargeIsNull() {
            addCriterion("g_charge is null");
            return (Criteria) this;
        }

        public Criteria andGChargeIsNotNull() {
            addCriterion("g_charge is not null");
            return (Criteria) this;
        }

        public Criteria andGChargeEqualTo(Integer value) {
            addCriterion("g_charge =", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeNotEqualTo(Integer value) {
            addCriterion("g_charge <>", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeGreaterThan(Integer value) {
            addCriterion("g_charge >", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeGreaterThanOrEqualTo(Integer value) {
            addCriterion("g_charge >=", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeLessThan(Integer value) {
            addCriterion("g_charge <", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeLessThanOrEqualTo(Integer value) {
            addCriterion("g_charge <=", value, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeIn(List<Integer> values) {
            addCriterion("g_charge in", values, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeNotIn(List<Integer> values) {
            addCriterion("g_charge not in", values, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeBetween(Integer value1, Integer value2) {
            addCriterion("g_charge between", value1, value2, "gCharge");
            return (Criteria) this;
        }

        public Criteria andGChargeNotBetween(Integer value1, Integer value2) {
            addCriterion("g_charge not between", value1, value2, "gCharge");
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