package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPreferentialInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public OrderPreferentialInfoExample() {
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

        public Criteria andPreferentialTypeIsNull() {
            addCriterion("preferential_type is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIsNotNull() {
            addCriterion("preferential_type is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeEqualTo(String value) {
            addCriterion("preferential_type =", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotEqualTo(String value) {
            addCriterion("preferential_type <>", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThan(String value) {
            addCriterion("preferential_type >", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeGreaterThanOrEqualTo(String value) {
            addCriterion("preferential_type >=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThan(String value) {
            addCriterion("preferential_type <", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLessThanOrEqualTo(String value) {
            addCriterion("preferential_type <=", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeLike(String value) {
            addCriterion("preferential_type like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotLike(String value) {
            addCriterion("preferential_type not like", value, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeIn(List<String> values) {
            addCriterion("preferential_type in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotIn(List<String> values) {
            addCriterion("preferential_type not in", values, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeBetween(String value1, String value2) {
            addCriterion("preferential_type between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialTypeNotBetween(String value1, String value2) {
            addCriterion("preferential_type not between", value1, value2, "preferentialType");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdIsNull() {
            addCriterion("preferential_id is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdIsNotNull() {
            addCriterion("preferential_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdEqualTo(Integer value) {
            addCriterion("preferential_id =", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdNotEqualTo(Integer value) {
            addCriterion("preferential_id <>", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGreaterThan(Integer value) {
            addCriterion("preferential_id >", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("preferential_id >=", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdLessThan(Integer value) {
            addCriterion("preferential_id <", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdLessThanOrEqualTo(Integer value) {
            addCriterion("preferential_id <=", value, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdIn(List<Integer> values) {
            addCriterion("preferential_id in", values, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdNotIn(List<Integer> values) {
            addCriterion("preferential_id not in", values, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdBetween(Integer value1, Integer value2) {
            addCriterion("preferential_id between", value1, value2, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andPreferentialIdNotBetween(Integer value1, Integer value2) {
            addCriterion("preferential_id not between", value1, value2, "preferentialId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNull() {
            addCriterion("order_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNotNull() {
            addCriterion("order_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdEqualTo(Integer value) {
            addCriterion("order_dtl_id =", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotEqualTo(Integer value) {
            addCriterion("order_dtl_id <>", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThan(Integer value) {
            addCriterion("order_dtl_id >", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id >=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThan(Integer value) {
            addCriterion("order_dtl_id <", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id <=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIn(List<Integer> values) {
            addCriterion("order_dtl_id in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotIn(List<Integer> values) {
            addCriterion("order_dtl_id not in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id not between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andRangIsNull() {
            addCriterion("rang is null");
            return (Criteria) this;
        }

        public Criteria andRangIsNotNull() {
            addCriterion("rang is not null");
            return (Criteria) this;
        }

        public Criteria andRangEqualTo(String value) {
            addCriterion("rang =", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotEqualTo(String value) {
            addCriterion("rang <>", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangGreaterThan(String value) {
            addCriterion("rang >", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangGreaterThanOrEqualTo(String value) {
            addCriterion("rang >=", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLessThan(String value) {
            addCriterion("rang <", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLessThanOrEqualTo(String value) {
            addCriterion("rang <=", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangLike(String value) {
            addCriterion("rang like", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotLike(String value) {
            addCriterion("rang not like", value, "rang");
            return (Criteria) this;
        }

        public Criteria andRangIn(List<String> values) {
            addCriterion("rang in", values, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotIn(List<String> values) {
            addCriterion("rang not in", values, "rang");
            return (Criteria) this;
        }

        public Criteria andRangBetween(String value1, String value2) {
            addCriterion("rang between", value1, value2, "rang");
            return (Criteria) this;
        }

        public Criteria andRangNotBetween(String value1, String value2) {
            addCriterion("rang not between", value1, value2, "rang");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("belong not between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentIsNull() {
            addCriterion("preferential_content is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentIsNotNull() {
            addCriterion("preferential_content is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentEqualTo(String value) {
            addCriterion("preferential_content =", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentNotEqualTo(String value) {
            addCriterion("preferential_content <>", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentGreaterThan(String value) {
            addCriterion("preferential_content >", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentGreaterThanOrEqualTo(String value) {
            addCriterion("preferential_content >=", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentLessThan(String value) {
            addCriterion("preferential_content <", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentLessThanOrEqualTo(String value) {
            addCriterion("preferential_content <=", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentLike(String value) {
            addCriterion("preferential_content like", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentNotLike(String value) {
            addCriterion("preferential_content not like", value, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentIn(List<String> values) {
            addCriterion("preferential_content in", values, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentNotIn(List<String> values) {
            addCriterion("preferential_content not in", values, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentBetween(String value1, String value2) {
            addCriterion("preferential_content between", value1, value2, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialContentNotBetween(String value1, String value2) {
            addCriterion("preferential_content not between", value1, value2, "preferentialContent");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountIsNull() {
            addCriterion("preferential_amount is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountIsNotNull() {
            addCriterion("preferential_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountEqualTo(BigDecimal value) {
            addCriterion("preferential_amount =", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountNotEqualTo(BigDecimal value) {
            addCriterion("preferential_amount <>", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountGreaterThan(BigDecimal value) {
            addCriterion("preferential_amount >", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("preferential_amount >=", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountLessThan(BigDecimal value) {
            addCriterion("preferential_amount <", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("preferential_amount <=", value, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountIn(List<BigDecimal> values) {
            addCriterion("preferential_amount in", values, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountNotIn(List<BigDecimal> values) {
            addCriterion("preferential_amount not in", values, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("preferential_amount between", value1, value2, "preferentialAmount");
            return (Criteria) this;
        }

        public Criteria andPreferentialAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("preferential_amount not between", value1, value2, "preferentialAmount");
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