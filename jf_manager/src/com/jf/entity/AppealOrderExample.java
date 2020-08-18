package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppealOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public AppealOrderExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andAppealTypeIsNull() {
            addCriterion("appeal_type is null");
            return (Criteria) this;
        }

        public Criteria andAppealTypeIsNotNull() {
            addCriterion("appeal_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppealTypeEqualTo(String value) {
            addCriterion("appeal_type =", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotEqualTo(String value) {
            addCriterion("appeal_type <>", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeGreaterThan(String value) {
            addCriterion("appeal_type >", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("appeal_type >=", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLessThan(String value) {
            addCriterion("appeal_type <", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLessThanOrEqualTo(String value) {
            addCriterion("appeal_type <=", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeLike(String value) {
            addCriterion("appeal_type like", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotLike(String value) {
            addCriterion("appeal_type not like", value, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeIn(List<String> values) {
            addCriterion("appeal_type in", values, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotIn(List<String> values) {
            addCriterion("appeal_type not in", values, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeBetween(String value1, String value2) {
            addCriterion("appeal_type between", value1, value2, "appealType");
            return (Criteria) this;
        }

        public Criteria andAppealTypeNotBetween(String value1, String value2) {
            addCriterion("appeal_type not between", value1, value2, "appealType");
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

        public Criteria andServiceStatusIsNull() {
            addCriterion("service_status is null");
            return (Criteria) this;
        }

        public Criteria andServiceStatusIsNotNull() {
            addCriterion("service_status is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStatusEqualTo(String value) {
            addCriterion("service_status =", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotEqualTo(String value) {
            addCriterion("service_status <>", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusGreaterThan(String value) {
            addCriterion("service_status >", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusGreaterThanOrEqualTo(String value) {
            addCriterion("service_status >=", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusLessThan(String value) {
            addCriterion("service_status <", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusLessThanOrEqualTo(String value) {
            addCriterion("service_status <=", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusLike(String value) {
            addCriterion("service_status like", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotLike(String value) {
            addCriterion("service_status not like", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusIn(List<String> values) {
            addCriterion("service_status in", values, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotIn(List<String> values) {
            addCriterion("service_status not in", values, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusBetween(String value1, String value2) {
            addCriterion("service_status between", value1, value2, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotBetween(String value1, String value2) {
            addCriterion("service_status not between", value1, value2, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorIsNull() {
            addCriterion("platform_processor is null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorIsNotNull() {
            addCriterion("platform_processor is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorEqualTo(Integer value) {
            addCriterion("platform_processor =", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotEqualTo(Integer value) {
            addCriterion("platform_processor <>", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorGreaterThan(Integer value) {
            addCriterion("platform_processor >", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_processor >=", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorLessThan(Integer value) {
            addCriterion("platform_processor <", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorLessThanOrEqualTo(Integer value) {
            addCriterion("platform_processor <=", value, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorIn(List<Integer> values) {
            addCriterion("platform_processor in", values, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotIn(List<Integer> values) {
            addCriterion("platform_processor not in", values, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorBetween(Integer value1, Integer value2) {
            addCriterion("platform_processor between", value1, value2, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andPlatformProcessorNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_processor not between", value1, value2, "platformProcessor");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeIsNull() {
            addCriterion("service_sponsor_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeIsNotNull() {
            addCriterion("service_sponsor_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeEqualTo(String value) {
            addCriterion("service_sponsor_type =", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeNotEqualTo(String value) {
            addCriterion("service_sponsor_type <>", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeGreaterThan(String value) {
            addCriterion("service_sponsor_type >", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_sponsor_type >=", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeLessThan(String value) {
            addCriterion("service_sponsor_type <", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeLessThanOrEqualTo(String value) {
            addCriterion("service_sponsor_type <=", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeLike(String value) {
            addCriterion("service_sponsor_type like", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeNotLike(String value) {
            addCriterion("service_sponsor_type not like", value, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeIn(List<String> values) {
            addCriterion("service_sponsor_type in", values, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeNotIn(List<String> values) {
            addCriterion("service_sponsor_type not in", values, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeBetween(String value1, String value2) {
            addCriterion("service_sponsor_type between", value1, value2, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andServiceSponsorTypeNotBetween(String value1, String value2) {
            addCriterion("service_sponsor_type not between", value1, value2, "serviceSponsorType");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andLiabilityIsNull() {
            addCriterion("liability is null");
            return (Criteria) this;
        }

        public Criteria andLiabilityIsNotNull() {
            addCriterion("liability is not null");
            return (Criteria) this;
        }

        public Criteria andLiabilityEqualTo(String value) {
            addCriterion("liability =", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityNotEqualTo(String value) {
            addCriterion("liability <>", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityGreaterThan(String value) {
            addCriterion("liability >", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityGreaterThanOrEqualTo(String value) {
            addCriterion("liability >=", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityLessThan(String value) {
            addCriterion("liability <", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityLessThanOrEqualTo(String value) {
            addCriterion("liability <=", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityLike(String value) {
            addCriterion("liability like", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityNotLike(String value) {
            addCriterion("liability not like", value, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityIn(List<String> values) {
            addCriterion("liability in", values, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityNotIn(List<String> values) {
            addCriterion("liability not in", values, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityBetween(String value1, String value2) {
            addCriterion("liability between", value1, value2, "liability");
            return (Criteria) this;
        }

        public Criteria andLiabilityNotBetween(String value1, String value2) {
            addCriterion("liability not between", value1, value2, "liability");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIsNull() {
            addCriterion("platform_remarks is null");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIsNotNull() {
            addCriterion("platform_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksEqualTo(String value) {
            addCriterion("platform_remarks =", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotEqualTo(String value) {
            addCriterion("platform_remarks <>", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksGreaterThan(String value) {
            addCriterion("platform_remarks >", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("platform_remarks >=", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLessThan(String value) {
            addCriterion("platform_remarks <", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLessThanOrEqualTo(String value) {
            addCriterion("platform_remarks <=", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksLike(String value) {
            addCriterion("platform_remarks like", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotLike(String value) {
            addCriterion("platform_remarks not like", value, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksIn(List<String> values) {
            addCriterion("platform_remarks in", values, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotIn(List<String> values) {
            addCriterion("platform_remarks not in", values, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksBetween(String value1, String value2) {
            addCriterion("platform_remarks between", value1, value2, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andPlatformRemarksNotBetween(String value1, String value2) {
            addCriterion("platform_remarks not between", value1, value2, "platformRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksIsNull() {
            addCriterion("mcht_remarks is null");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksIsNotNull() {
            addCriterion("mcht_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksEqualTo(String value) {
            addCriterion("mcht_remarks =", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksNotEqualTo(String value) {
            addCriterion("mcht_remarks <>", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksGreaterThan(String value) {
            addCriterion("mcht_remarks >", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_remarks >=", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksLessThan(String value) {
            addCriterion("mcht_remarks <", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksLessThanOrEqualTo(String value) {
            addCriterion("mcht_remarks <=", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksLike(String value) {
            addCriterion("mcht_remarks like", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksNotLike(String value) {
            addCriterion("mcht_remarks not like", value, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksIn(List<String> values) {
            addCriterion("mcht_remarks in", values, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksNotIn(List<String> values) {
            addCriterion("mcht_remarks not in", values, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksBetween(String value1, String value2) {
            addCriterion("mcht_remarks between", value1, value2, "mchtRemarks");
            return (Criteria) this;
        }

        public Criteria andMchtRemarksNotBetween(String value1, String value2) {
            addCriterion("mcht_remarks not between", value1, value2, "mchtRemarks");
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