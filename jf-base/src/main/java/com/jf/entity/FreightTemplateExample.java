package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreightTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public FreightTemplateExample() {
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

        public Criteria andFirstFreightIsNull() {
            addCriterion("first_freight is null");
            return (Criteria) this;
        }

        public Criteria andFirstFreightIsNotNull() {
            addCriterion("first_freight is not null");
            return (Criteria) this;
        }

        public Criteria andFirstFreightEqualTo(BigDecimal value) {
            addCriterion("first_freight =", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightNotEqualTo(BigDecimal value) {
            addCriterion("first_freight <>", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightGreaterThan(BigDecimal value) {
            addCriterion("first_freight >", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("first_freight >=", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightLessThan(BigDecimal value) {
            addCriterion("first_freight <", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("first_freight <=", value, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightIn(List<BigDecimal> values) {
            addCriterion("first_freight in", values, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightNotIn(List<BigDecimal> values) {
            addCriterion("first_freight not in", values, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_freight between", value1, value2, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andFirstFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("first_freight not between", value1, value2, "firstFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightIsNull() {
            addCriterion("additional_freight is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightIsNotNull() {
            addCriterion("additional_freight is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightEqualTo(BigDecimal value) {
            addCriterion("additional_freight =", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightNotEqualTo(BigDecimal value) {
            addCriterion("additional_freight <>", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightGreaterThan(BigDecimal value) {
            addCriterion("additional_freight >", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_freight >=", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightLessThan(BigDecimal value) {
            addCriterion("additional_freight <", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("additional_freight <=", value, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightIn(List<BigDecimal> values) {
            addCriterion("additional_freight in", values, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightNotIn(List<BigDecimal> values) {
            addCriterion("additional_freight not in", values, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_freight between", value1, value2, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andAdditionalFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("additional_freight not between", value1, value2, "additionalFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightIsNull() {
            addCriterion("is_full_reduction_freight is null");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightIsNotNull() {
            addCriterion("is_full_reduction_freight is not null");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightEqualTo(String value) {
            addCriterion("is_full_reduction_freight =", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightNotEqualTo(String value) {
            addCriterion("is_full_reduction_freight <>", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightGreaterThan(String value) {
            addCriterion("is_full_reduction_freight >", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightGreaterThanOrEqualTo(String value) {
            addCriterion("is_full_reduction_freight >=", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightLessThan(String value) {
            addCriterion("is_full_reduction_freight <", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightLessThanOrEqualTo(String value) {
            addCriterion("is_full_reduction_freight <=", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightLike(String value) {
            addCriterion("is_full_reduction_freight like", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightNotLike(String value) {
            addCriterion("is_full_reduction_freight not like", value, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightIn(List<String> values) {
            addCriterion("is_full_reduction_freight in", values, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightNotIn(List<String> values) {
            addCriterion("is_full_reduction_freight not in", values, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightBetween(String value1, String value2) {
            addCriterion("is_full_reduction_freight between", value1, value2, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andIsFullReductionFreightNotBetween(String value1, String value2) {
            addCriterion("is_full_reduction_freight not between", value1, value2, "isFullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullNumberIsNull() {
            addCriterion("full_number is null");
            return (Criteria) this;
        }

        public Criteria andFullNumberIsNotNull() {
            addCriterion("full_number is not null");
            return (Criteria) this;
        }

        public Criteria andFullNumberEqualTo(Integer value) {
            addCriterion("full_number =", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberNotEqualTo(Integer value) {
            addCriterion("full_number <>", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberGreaterThan(Integer value) {
            addCriterion("full_number >", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("full_number >=", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberLessThan(Integer value) {
            addCriterion("full_number <", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberLessThanOrEqualTo(Integer value) {
            addCriterion("full_number <=", value, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberIn(List<Integer> values) {
            addCriterion("full_number in", values, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberNotIn(List<Integer> values) {
            addCriterion("full_number not in", values, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberBetween(Integer value1, Integer value2) {
            addCriterion("full_number between", value1, value2, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("full_number not between", value1, value2, "fullNumber");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightIsNull() {
            addCriterion("full_reduction_freight is null");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightIsNotNull() {
            addCriterion("full_reduction_freight is not null");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightEqualTo(BigDecimal value) {
            addCriterion("full_reduction_freight =", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightNotEqualTo(BigDecimal value) {
            addCriterion("full_reduction_freight <>", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightGreaterThan(BigDecimal value) {
            addCriterion("full_reduction_freight >", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("full_reduction_freight >=", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightLessThan(BigDecimal value) {
            addCriterion("full_reduction_freight <", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("full_reduction_freight <=", value, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightIn(List<BigDecimal> values) {
            addCriterion("full_reduction_freight in", values, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightNotIn(List<BigDecimal> values) {
            addCriterion("full_reduction_freight not in", values, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("full_reduction_freight between", value1, value2, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andFullReductionFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("full_reduction_freight not between", value1, value2, "fullReductionFreight");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicIsNull() {
            addCriterion("product_notice_pic is null");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicIsNotNull() {
            addCriterion("product_notice_pic is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicEqualTo(String value) {
            addCriterion("product_notice_pic =", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicNotEqualTo(String value) {
            addCriterion("product_notice_pic <>", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicGreaterThan(String value) {
            addCriterion("product_notice_pic >", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicGreaterThanOrEqualTo(String value) {
            addCriterion("product_notice_pic >=", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicLessThan(String value) {
            addCriterion("product_notice_pic <", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicLessThanOrEqualTo(String value) {
            addCriterion("product_notice_pic <=", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicLike(String value) {
            addCriterion("product_notice_pic like", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicNotLike(String value) {
            addCriterion("product_notice_pic not like", value, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicIn(List<String> values) {
            addCriterion("product_notice_pic in", values, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicNotIn(List<String> values) {
            addCriterion("product_notice_pic not in", values, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicBetween(String value1, String value2) {
            addCriterion("product_notice_pic between", value1, value2, "productNoticePic");
            return (Criteria) this;
        }

        public Criteria andProductNoticePicNotBetween(String value1, String value2) {
            addCriterion("product_notice_pic not between", value1, value2, "productNoticePic");
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