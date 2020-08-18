package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityRuleConfigurationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityRuleConfigurationExample() {
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

        public Criteria andPriceRulesIsNull() {
            addCriterion("price_rules is null");
            return (Criteria) this;
        }

        public Criteria andPriceRulesIsNotNull() {
            addCriterion("price_rules is not null");
            return (Criteria) this;
        }

        public Criteria andPriceRulesEqualTo(String value) {
            addCriterion("price_rules =", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesNotEqualTo(String value) {
            addCriterion("price_rules <>", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesGreaterThan(String value) {
            addCriterion("price_rules >", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesGreaterThanOrEqualTo(String value) {
            addCriterion("price_rules >=", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesLessThan(String value) {
            addCriterion("price_rules <", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesLessThanOrEqualTo(String value) {
            addCriterion("price_rules <=", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesLike(String value) {
            addCriterion("price_rules like", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesNotLike(String value) {
            addCriterion("price_rules not like", value, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesIn(List<String> values) {
            addCriterion("price_rules in", values, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesNotIn(List<String> values) {
            addCriterion("price_rules not in", values, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesBetween(String value1, String value2) {
            addCriterion("price_rules between", value1, value2, "priceRules");
            return (Criteria) this;
        }

        public Criteria andPriceRulesNotBetween(String value1, String value2) {
            addCriterion("price_rules not between", value1, value2, "priceRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesIsNull() {
            addCriterion("sales_rules is null");
            return (Criteria) this;
        }

        public Criteria andSalesRulesIsNotNull() {
            addCriterion("sales_rules is not null");
            return (Criteria) this;
        }

        public Criteria andSalesRulesEqualTo(String value) {
            addCriterion("sales_rules =", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesNotEqualTo(String value) {
            addCriterion("sales_rules <>", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesGreaterThan(String value) {
            addCriterion("sales_rules >", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesGreaterThanOrEqualTo(String value) {
            addCriterion("sales_rules >=", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesLessThan(String value) {
            addCriterion("sales_rules <", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesLessThanOrEqualTo(String value) {
            addCriterion("sales_rules <=", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesLike(String value) {
            addCriterion("sales_rules like", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesNotLike(String value) {
            addCriterion("sales_rules not like", value, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesIn(List<String> values) {
            addCriterion("sales_rules in", values, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesNotIn(List<String> values) {
            addCriterion("sales_rules not in", values, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesBetween(String value1, String value2) {
            addCriterion("sales_rules between", value1, value2, "salesRules");
            return (Criteria) this;
        }

        public Criteria andSalesRulesNotBetween(String value1, String value2) {
            addCriterion("sales_rules not between", value1, value2, "salesRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesIsNull() {
            addCriterion("stock_rules is null");
            return (Criteria) this;
        }

        public Criteria andStockRulesIsNotNull() {
            addCriterion("stock_rules is not null");
            return (Criteria) this;
        }

        public Criteria andStockRulesEqualTo(String value) {
            addCriterion("stock_rules =", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesNotEqualTo(String value) {
            addCriterion("stock_rules <>", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesGreaterThan(String value) {
            addCriterion("stock_rules >", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesGreaterThanOrEqualTo(String value) {
            addCriterion("stock_rules >=", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesLessThan(String value) {
            addCriterion("stock_rules <", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesLessThanOrEqualTo(String value) {
            addCriterion("stock_rules <=", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesLike(String value) {
            addCriterion("stock_rules like", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesNotLike(String value) {
            addCriterion("stock_rules not like", value, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesIn(List<String> values) {
            addCriterion("stock_rules in", values, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesNotIn(List<String> values) {
            addCriterion("stock_rules not in", values, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesBetween(String value1, String value2) {
            addCriterion("stock_rules between", value1, value2, "stockRules");
            return (Criteria) this;
        }

        public Criteria andStockRulesNotBetween(String value1, String value2) {
            addCriterion("stock_rules not between", value1, value2, "stockRules");
            return (Criteria) this;
        }

        public Criteria andFavorableRateIsNull() {
            addCriterion("favorable_rate is null");
            return (Criteria) this;
        }

        public Criteria andFavorableRateIsNotNull() {
            addCriterion("favorable_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFavorableRateEqualTo(BigDecimal value) {
            addCriterion("favorable_rate =", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateNotEqualTo(BigDecimal value) {
            addCriterion("favorable_rate <>", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateGreaterThan(BigDecimal value) {
            addCriterion("favorable_rate >", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("favorable_rate >=", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateLessThan(BigDecimal value) {
            addCriterion("favorable_rate <", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("favorable_rate <=", value, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateIn(List<BigDecimal> values) {
            addCriterion("favorable_rate in", values, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateNotIn(List<BigDecimal> values) {
            addCriterion("favorable_rate not in", values, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("favorable_rate between", value1, value2, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andFavorableRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("favorable_rate not between", value1, value2, "favorableRate");
            return (Criteria) this;
        }

        public Criteria andShopCommentIsNull() {
            addCriterion("shop_comment is null");
            return (Criteria) this;
        }

        public Criteria andShopCommentIsNotNull() {
            addCriterion("shop_comment is not null");
            return (Criteria) this;
        }

        public Criteria andShopCommentEqualTo(BigDecimal value) {
            addCriterion("shop_comment =", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentNotEqualTo(BigDecimal value) {
            addCriterion("shop_comment <>", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentGreaterThan(BigDecimal value) {
            addCriterion("shop_comment >", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_comment >=", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentLessThan(BigDecimal value) {
            addCriterion("shop_comment <", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_comment <=", value, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentIn(List<BigDecimal> values) {
            addCriterion("shop_comment in", values, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentNotIn(List<BigDecimal> values) {
            addCriterion("shop_comment not in", values, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_comment between", value1, value2, "shopComment");
            return (Criteria) this;
        }

        public Criteria andShopCommentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_comment not between", value1, value2, "shopComment");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesIsNull() {
            addCriterion("sales_cycle_rules is null");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesIsNotNull() {
            addCriterion("sales_cycle_rules is not null");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesEqualTo(String value) {
            addCriterion("sales_cycle_rules =", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesNotEqualTo(String value) {
            addCriterion("sales_cycle_rules <>", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesGreaterThan(String value) {
            addCriterion("sales_cycle_rules >", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesGreaterThanOrEqualTo(String value) {
            addCriterion("sales_cycle_rules >=", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesLessThan(String value) {
            addCriterion("sales_cycle_rules <", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesLessThanOrEqualTo(String value) {
            addCriterion("sales_cycle_rules <=", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesLike(String value) {
            addCriterion("sales_cycle_rules like", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesNotLike(String value) {
            addCriterion("sales_cycle_rules not like", value, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesIn(List<String> values) {
            addCriterion("sales_cycle_rules in", values, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesNotIn(List<String> values) {
            addCriterion("sales_cycle_rules not in", values, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesBetween(String value1, String value2) {
            addCriterion("sales_cycle_rules between", value1, value2, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andSalesCycleRulesNotBetween(String value1, String value2) {
            addCriterion("sales_cycle_rules not between", value1, value2, "salesCycleRules");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsIsNull() {
            addCriterion("other_requirements is null");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsIsNotNull() {
            addCriterion("other_requirements is not null");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsEqualTo(String value) {
            addCriterion("other_requirements =", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsNotEqualTo(String value) {
            addCriterion("other_requirements <>", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsGreaterThan(String value) {
            addCriterion("other_requirements >", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsGreaterThanOrEqualTo(String value) {
            addCriterion("other_requirements >=", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsLessThan(String value) {
            addCriterion("other_requirements <", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsLessThanOrEqualTo(String value) {
            addCriterion("other_requirements <=", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsLike(String value) {
            addCriterion("other_requirements like", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsNotLike(String value) {
            addCriterion("other_requirements not like", value, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsIn(List<String> values) {
            addCriterion("other_requirements in", values, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsNotIn(List<String> values) {
            addCriterion("other_requirements not in", values, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsBetween(String value1, String value2) {
            addCriterion("other_requirements between", value1, value2, "otherRequirements");
            return (Criteria) this;
        }

        public Criteria andOtherRequirementsNotBetween(String value1, String value2) {
            addCriterion("other_requirements not between", value1, value2, "otherRequirements");
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

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
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

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
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