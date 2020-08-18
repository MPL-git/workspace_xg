package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolateOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ViolateOrderExample() {
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

        public Criteria andViolateTypeIsNull() {
            addCriterion("violate_type is null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIsNotNull() {
            addCriterion("violate_type is not null");
            return (Criteria) this;
        }

        public Criteria andViolateTypeEqualTo(String value) {
            addCriterion("violate_type =", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotEqualTo(String value) {
            addCriterion("violate_type <>", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThan(String value) {
            addCriterion("violate_type >", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("violate_type >=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThan(String value) {
            addCriterion("violate_type <", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLessThanOrEqualTo(String value) {
            addCriterion("violate_type <=", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeLike(String value) {
            addCriterion("violate_type like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotLike(String value) {
            addCriterion("violate_type not like", value, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeIn(List<String> values) {
            addCriterion("violate_type in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotIn(List<String> values) {
            addCriterion("violate_type not in", values, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeBetween(String value1, String value2) {
            addCriterion("violate_type between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateTypeNotBetween(String value1, String value2) {
            addCriterion("violate_type not between", value1, value2, "violateType");
            return (Criteria) this;
        }

        public Criteria andViolateActionIsNull() {
            addCriterion("violate_action is null");
            return (Criteria) this;
        }

        public Criteria andViolateActionIsNotNull() {
            addCriterion("violate_action is not null");
            return (Criteria) this;
        }

        public Criteria andViolateActionEqualTo(String value) {
            addCriterion("violate_action =", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotEqualTo(String value) {
            addCriterion("violate_action <>", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionGreaterThan(String value) {
            addCriterion("violate_action >", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionGreaterThanOrEqualTo(String value) {
            addCriterion("violate_action >=", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLessThan(String value) {
            addCriterion("violate_action <", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLessThanOrEqualTo(String value) {
            addCriterion("violate_action <=", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionLike(String value) {
            addCriterion("violate_action like", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotLike(String value) {
            addCriterion("violate_action not like", value, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionIn(List<String> values) {
            addCriterion("violate_action in", values, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotIn(List<String> values) {
            addCriterion("violate_action not in", values, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionBetween(String value1, String value2) {
            addCriterion("violate_action between", value1, value2, "violateAction");
            return (Criteria) this;
        }

        public Criteria andViolateActionNotBetween(String value1, String value2) {
            addCriterion("violate_action not between", value1, value2, "violateAction");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andPunishMeansIsNull() {
            addCriterion("punish_means is null");
            return (Criteria) this;
        }

        public Criteria andPunishMeansIsNotNull() {
            addCriterion("punish_means is not null");
            return (Criteria) this;
        }

        public Criteria andPunishMeansEqualTo(String value) {
            addCriterion("punish_means =", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansNotEqualTo(String value) {
            addCriterion("punish_means <>", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansGreaterThan(String value) {
            addCriterion("punish_means >", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansGreaterThanOrEqualTo(String value) {
            addCriterion("punish_means >=", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansLessThan(String value) {
            addCriterion("punish_means <", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansLessThanOrEqualTo(String value) {
            addCriterion("punish_means <=", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansLike(String value) {
            addCriterion("punish_means like", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansNotLike(String value) {
            addCriterion("punish_means not like", value, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansIn(List<String> values) {
            addCriterion("punish_means in", values, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansNotIn(List<String> values) {
            addCriterion("punish_means not in", values, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansBetween(String value1, String value2) {
            addCriterion("punish_means between", value1, value2, "punishMeans");
            return (Criteria) this;
        }

        public Criteria andPunishMeansNotBetween(String value1, String value2) {
            addCriterion("punish_means not between", value1, value2, "punishMeans");
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

        public Criteria andOrderSourceIsNull() {
            addCriterion("order_source is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("order_source is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(String value) {
            addCriterion("order_source =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(String value) {
            addCriterion("order_source <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(String value) {
            addCriterion("order_source >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(String value) {
            addCriterion("order_source >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(String value) {
            addCriterion("order_source <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(String value) {
            addCriterion("order_source <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLike(String value) {
            addCriterion("order_source like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotLike(String value) {
            addCriterion("order_source not like", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<String> values) {
            addCriterion("order_source in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<String> values) {
            addCriterion("order_source not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(String value1, String value2) {
            addCriterion("order_source between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(String value1, String value2) {
            addCriterion("order_source not between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andViolateDateIsNull() {
            addCriterion("violate_date is null");
            return (Criteria) this;
        }

        public Criteria andViolateDateIsNotNull() {
            addCriterion("violate_date is not null");
            return (Criteria) this;
        }

        public Criteria andViolateDateEqualTo(Date value) {
            addCriterion("violate_date =", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateNotEqualTo(Date value) {
            addCriterion("violate_date <>", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateGreaterThan(Date value) {
            addCriterion("violate_date >", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("violate_date >=", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateLessThan(Date value) {
            addCriterion("violate_date <", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateLessThanOrEqualTo(Date value) {
            addCriterion("violate_date <=", value, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateIn(List<Date> values) {
            addCriterion("violate_date in", values, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateNotIn(List<Date> values) {
            addCriterion("violate_date not in", values, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateBetween(Date value1, Date value2) {
            addCriterion("violate_date between", value1, value2, "violateDate");
            return (Criteria) this;
        }

        public Criteria andViolateDateNotBetween(Date value1, Date value2) {
            addCriterion("violate_date not between", value1, value2, "violateDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateIsNull() {
            addCriterion("complain_date is null");
            return (Criteria) this;
        }

        public Criteria andComplainDateIsNotNull() {
            addCriterion("complain_date is not null");
            return (Criteria) this;
        }

        public Criteria andComplainDateEqualTo(Date value) {
            addCriterion("complain_date =", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotEqualTo(Date value) {
            addCriterion("complain_date <>", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThan(Date value) {
            addCriterion("complain_date >", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complain_date >=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThan(Date value) {
            addCriterion("complain_date <", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateLessThanOrEqualTo(Date value) {
            addCriterion("complain_date <=", value, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateIn(List<Date> values) {
            addCriterion("complain_date in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotIn(List<Date> values) {
            addCriterion("complain_date not in", values, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateBetween(Date value1, Date value2) {
            addCriterion("complain_date between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainDateNotBetween(Date value1, Date value2) {
            addCriterion("complain_date not between", value1, value2, "complainDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIsNull() {
            addCriterion("complain_end_date is null");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIsNotNull() {
            addCriterion("complain_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateEqualTo(Date value) {
            addCriterion("complain_end_date =", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotEqualTo(Date value) {
            addCriterion("complain_end_date <>", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateGreaterThan(Date value) {
            addCriterion("complain_end_date >", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complain_end_date >=", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateLessThan(Date value) {
            addCriterion("complain_end_date <", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateLessThanOrEqualTo(Date value) {
            addCriterion("complain_end_date <=", value, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateIn(List<Date> values) {
            addCriterion("complain_end_date in", values, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotIn(List<Date> values) {
            addCriterion("complain_end_date not in", values, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateBetween(Date value1, Date value2) {
            addCriterion("complain_end_date between", value1, value2, "complainEndDate");
            return (Criteria) this;
        }

        public Criteria andComplainEndDateNotBetween(Date value1, Date value2) {
            addCriterion("complain_end_date not between", value1, value2, "complainEndDate");
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

        public Criteria andAuditRemarksIsNull() {
            addCriterion("audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIsNotNull() {
            addCriterion("audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksEqualTo(String value) {
            addCriterion("audit_remarks =", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotEqualTo(String value) {
            addCriterion("audit_remarks <>", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThan(String value) {
            addCriterion("audit_remarks >", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("audit_remarks >=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThan(String value) {
            addCriterion("audit_remarks <", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("audit_remarks <=", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksLike(String value) {
            addCriterion("audit_remarks like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotLike(String value) {
            addCriterion("audit_remarks not like", value, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksIn(List<String> values) {
            addCriterion("audit_remarks in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotIn(List<String> values) {
            addCriterion("audit_remarks not in", values, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksBetween(String value1, String value2) {
            addCriterion("audit_remarks between", value1, value2, "auditRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("audit_remarks not between", value1, value2, "auditRemarks");
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

        public Criteria andComplainInfoStatusIsNull() {
            addCriterion("complain_info_status is null");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusIsNotNull() {
            addCriterion("complain_info_status is not null");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusEqualTo(String value) {
            addCriterion("complain_info_status =", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusNotEqualTo(String value) {
            addCriterion("complain_info_status <>", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusGreaterThan(String value) {
            addCriterion("complain_info_status >", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusGreaterThanOrEqualTo(String value) {
            addCriterion("complain_info_status >=", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusLessThan(String value) {
            addCriterion("complain_info_status <", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusLessThanOrEqualTo(String value) {
            addCriterion("complain_info_status <=", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusLike(String value) {
            addCriterion("complain_info_status like", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusNotLike(String value) {
            addCriterion("complain_info_status not like", value, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusIn(List<String> values) {
            addCriterion("complain_info_status in", values, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusNotIn(List<String> values) {
            addCriterion("complain_info_status not in", values, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusBetween(String value1, String value2) {
            addCriterion("complain_info_status between", value1, value2, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andComplainInfoStatusNotBetween(String value1, String value2) {
            addCriterion("complain_info_status not between", value1, value2, "complainInfoStatus");
            return (Criteria) this;
        }

        public Criteria andEnclosureIsNull() {
            addCriterion("enclosure is null");
            return (Criteria) this;
        }

        public Criteria andEnclosureIsNotNull() {
            addCriterion("enclosure is not null");
            return (Criteria) this;
        }

        public Criteria andEnclosureEqualTo(String value) {
            addCriterion("enclosure =", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotEqualTo(String value) {
            addCriterion("enclosure <>", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureGreaterThan(String value) {
            addCriterion("enclosure >", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureGreaterThanOrEqualTo(String value) {
            addCriterion("enclosure >=", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLessThan(String value) {
            addCriterion("enclosure <", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLessThanOrEqualTo(String value) {
            addCriterion("enclosure <=", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLike(String value) {
            addCriterion("enclosure like", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotLike(String value) {
            addCriterion("enclosure not like", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureIn(List<String> values) {
            addCriterion("enclosure in", values, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotIn(List<String> values) {
            addCriterion("enclosure not in", values, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureBetween(String value1, String value2) {
            addCriterion("enclosure between", value1, value2, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotBetween(String value1, String value2) {
            addCriterion("enclosure not between", value1, value2, "enclosure");
            return (Criteria) this;
        }

        public Criteria andJifenStatusIsNull() {
            addCriterion("jifen_status is null");
            return (Criteria) this;
        }

        public Criteria andJifenStatusIsNotNull() {
            addCriterion("jifen_status is not null");
            return (Criteria) this;
        }

        public Criteria andJifenStatusEqualTo(String value) {
            addCriterion("jifen_status =", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusNotEqualTo(String value) {
            addCriterion("jifen_status <>", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusGreaterThan(String value) {
            addCriterion("jifen_status >", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusGreaterThanOrEqualTo(String value) {
            addCriterion("jifen_status >=", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusLessThan(String value) {
            addCriterion("jifen_status <", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusLessThanOrEqualTo(String value) {
            addCriterion("jifen_status <=", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusLike(String value) {
            addCriterion("jifen_status like", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusNotLike(String value) {
            addCriterion("jifen_status not like", value, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusIn(List<String> values) {
            addCriterion("jifen_status in", values, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusNotIn(List<String> values) {
            addCriterion("jifen_status not in", values, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusBetween(String value1, String value2) {
            addCriterion("jifen_status between", value1, value2, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenStatusNotBetween(String value1, String value2) {
            addCriterion("jifen_status not between", value1, value2, "jifenStatus");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIsNull() {
            addCriterion("jifen_integral is null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIsNotNull() {
            addCriterion("jifen_integral is not null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralEqualTo(Integer value) {
            addCriterion("jifen_integral =", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotEqualTo(Integer value) {
            addCriterion("jifen_integral <>", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralGreaterThan(Integer value) {
            addCriterion("jifen_integral >", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("jifen_integral >=", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralLessThan(Integer value) {
            addCriterion("jifen_integral <", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("jifen_integral <=", value, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralIn(List<Integer> values) {
            addCriterion("jifen_integral in", values, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotIn(List<Integer> values) {
            addCriterion("jifen_integral not in", values, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralBetween(Integer value1, Integer value2) {
            addCriterion("jifen_integral between", value1, value2, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("jifen_integral not between", value1, value2, "jifenIntegral");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIsNull() {
            addCriterion("enable_hours is null");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIsNotNull() {
            addCriterion("enable_hours is not null");
            return (Criteria) this;
        }

        public Criteria andEnableHoursEqualTo(String value) {
            addCriterion("enable_hours =", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotEqualTo(String value) {
            addCriterion("enable_hours <>", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursGreaterThan(String value) {
            addCriterion("enable_hours >", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursGreaterThanOrEqualTo(String value) {
            addCriterion("enable_hours >=", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLessThan(String value) {
            addCriterion("enable_hours <", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLessThanOrEqualTo(String value) {
            addCriterion("enable_hours <=", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursLike(String value) {
            addCriterion("enable_hours like", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotLike(String value) {
            addCriterion("enable_hours not like", value, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursIn(List<String> values) {
            addCriterion("enable_hours in", values, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotIn(List<String> values) {
            addCriterion("enable_hours not in", values, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursBetween(String value1, String value2) {
            addCriterion("enable_hours between", value1, value2, "enableHours");
            return (Criteria) this;
        }

        public Criteria andEnableHoursNotBetween(String value1, String value2) {
            addCriterion("enable_hours not between", value1, value2, "enableHours");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIsNull() {
            addCriterion("jifen_integral_desc is null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIsNotNull() {
            addCriterion("jifen_integral_desc is not null");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescEqualTo(String value) {
            addCriterion("jifen_integral_desc =", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotEqualTo(String value) {
            addCriterion("jifen_integral_desc <>", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescGreaterThan(String value) {
            addCriterion("jifen_integral_desc >", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescGreaterThanOrEqualTo(String value) {
            addCriterion("jifen_integral_desc >=", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLessThan(String value) {
            addCriterion("jifen_integral_desc <", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLessThanOrEqualTo(String value) {
            addCriterion("jifen_integral_desc <=", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescLike(String value) {
            addCriterion("jifen_integral_desc like", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotLike(String value) {
            addCriterion("jifen_integral_desc not like", value, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescIn(List<String> values) {
            addCriterion("jifen_integral_desc in", values, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotIn(List<String> values) {
            addCriterion("jifen_integral_desc not in", values, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescBetween(String value1, String value2) {
            addCriterion("jifen_integral_desc between", value1, value2, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andJifenIntegralDescNotBetween(String value1, String value2) {
            addCriterion("jifen_integral_desc not between", value1, value2, "jifenIntegralDesc");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusIsNull() {
            addCriterion("again_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusIsNotNull() {
            addCriterion("again_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusEqualTo(String value) {
            addCriterion("again_audit_status =", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusNotEqualTo(String value) {
            addCriterion("again_audit_status <>", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusGreaterThan(String value) {
            addCriterion("again_audit_status >", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("again_audit_status >=", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusLessThan(String value) {
            addCriterion("again_audit_status <", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("again_audit_status <=", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusLike(String value) {
            addCriterion("again_audit_status like", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusNotLike(String value) {
            addCriterion("again_audit_status not like", value, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusIn(List<String> values) {
            addCriterion("again_audit_status in", values, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusNotIn(List<String> values) {
            addCriterion("again_audit_status not in", values, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusBetween(String value1, String value2) {
            addCriterion("again_audit_status between", value1, value2, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditStatusNotBetween(String value1, String value2) {
            addCriterion("again_audit_status not between", value1, value2, "againAuditStatus");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByIsNull() {
            addCriterion("again_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByIsNotNull() {
            addCriterion("again_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByEqualTo(Integer value) {
            addCriterion("again_audit_by =", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByNotEqualTo(Integer value) {
            addCriterion("again_audit_by <>", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByGreaterThan(Integer value) {
            addCriterion("again_audit_by >", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("again_audit_by >=", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByLessThan(Integer value) {
            addCriterion("again_audit_by <", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("again_audit_by <=", value, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByIn(List<Integer> values) {
            addCriterion("again_audit_by in", values, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByNotIn(List<Integer> values) {
            addCriterion("again_audit_by not in", values, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByBetween(Integer value1, Integer value2) {
            addCriterion("again_audit_by between", value1, value2, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("again_audit_by not between", value1, value2, "againAuditBy");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateIsNull() {
            addCriterion("again_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateIsNotNull() {
            addCriterion("again_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateEqualTo(Date value) {
            addCriterion("again_audit_date =", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateNotEqualTo(Date value) {
            addCriterion("again_audit_date <>", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateGreaterThan(Date value) {
            addCriterion("again_audit_date >", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("again_audit_date >=", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateLessThan(Date value) {
            addCriterion("again_audit_date <", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("again_audit_date <=", value, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateIn(List<Date> values) {
            addCriterion("again_audit_date in", values, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateNotIn(List<Date> values) {
            addCriterion("again_audit_date not in", values, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateBetween(Date value1, Date value2) {
            addCriterion("again_audit_date between", value1, value2, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("again_audit_date not between", value1, value2, "againAuditDate");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksIsNull() {
            addCriterion("again_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksIsNotNull() {
            addCriterion("again_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksEqualTo(String value) {
            addCriterion("again_audit_remarks =", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksNotEqualTo(String value) {
            addCriterion("again_audit_remarks <>", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksGreaterThan(String value) {
            addCriterion("again_audit_remarks >", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("again_audit_remarks >=", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksLessThan(String value) {
            addCriterion("again_audit_remarks <", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("again_audit_remarks <=", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksLike(String value) {
            addCriterion("again_audit_remarks like", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksNotLike(String value) {
            addCriterion("again_audit_remarks not like", value, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksIn(List<String> values) {
            addCriterion("again_audit_remarks in", values, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksNotIn(List<String> values) {
            addCriterion("again_audit_remarks not in", values, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksBetween(String value1, String value2) {
            addCriterion("again_audit_remarks between", value1, value2, "againAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andAgainAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("again_audit_remarks not between", value1, value2, "againAuditRemarks");
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