package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SpreadDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SpreadDataExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("account_id like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("account_id not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityIsNull() {
            addCriterion("display_quantity is null");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityIsNotNull() {
            addCriterion("display_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityEqualTo(String value) {
            addCriterion("display_quantity =", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityNotEqualTo(String value) {
            addCriterion("display_quantity <>", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityGreaterThan(String value) {
            addCriterion("display_quantity >", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityGreaterThanOrEqualTo(String value) {
            addCriterion("display_quantity >=", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityLessThan(String value) {
            addCriterion("display_quantity <", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityLessThanOrEqualTo(String value) {
            addCriterion("display_quantity <=", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityLike(String value) {
            addCriterion("display_quantity like", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityNotLike(String value) {
            addCriterion("display_quantity not like", value, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityIn(List<String> values) {
            addCriterion("display_quantity in", values, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityNotIn(List<String> values) {
            addCriterion("display_quantity not in", values, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityBetween(String value1, String value2) {
            addCriterion("display_quantity between", value1, value2, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andDisplayQuantityNotBetween(String value1, String value2) {
            addCriterion("display_quantity not between", value1, value2, "displayQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIsNull() {
            addCriterion("click_quantity is null");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIsNotNull() {
            addCriterion("click_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andClickQuantityEqualTo(String value) {
            addCriterion("click_quantity =", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotEqualTo(String value) {
            addCriterion("click_quantity <>", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityGreaterThan(String value) {
            addCriterion("click_quantity >", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityGreaterThanOrEqualTo(String value) {
            addCriterion("click_quantity >=", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityLessThan(String value) {
            addCriterion("click_quantity <", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityLessThanOrEqualTo(String value) {
            addCriterion("click_quantity <=", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityLike(String value) {
            addCriterion("click_quantity like", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotLike(String value) {
            addCriterion("click_quantity not like", value, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityIn(List<String> values) {
            addCriterion("click_quantity in", values, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotIn(List<String> values) {
            addCriterion("click_quantity not in", values, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityBetween(String value1, String value2) {
            addCriterion("click_quantity between", value1, value2, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickQuantityNotBetween(String value1, String value2) {
            addCriterion("click_quantity not between", value1, value2, "clickQuantity");
            return (Criteria) this;
        }

        public Criteria andClickRateIsNull() {
            addCriterion("click_rate is null");
            return (Criteria) this;
        }

        public Criteria andClickRateIsNotNull() {
            addCriterion("click_rate is not null");
            return (Criteria) this;
        }

        public Criteria andClickRateEqualTo(String value) {
            addCriterion("click_rate =", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotEqualTo(String value) {
            addCriterion("click_rate <>", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThan(String value) {
            addCriterion("click_rate >", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateGreaterThanOrEqualTo(String value) {
            addCriterion("click_rate >=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThan(String value) {
            addCriterion("click_rate <", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLessThanOrEqualTo(String value) {
            addCriterion("click_rate <=", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateLike(String value) {
            addCriterion("click_rate like", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotLike(String value) {
            addCriterion("click_rate not like", value, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateIn(List<String> values) {
            addCriterion("click_rate in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotIn(List<String> values) {
            addCriterion("click_rate not in", values, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateBetween(String value1, String value2) {
            addCriterion("click_rate between", value1, value2, "clickRate");
            return (Criteria) this;
        }

        public Criteria andClickRateNotBetween(String value1, String value2) {
            addCriterion("click_rate not between", value1, value2, "clickRate");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityIsNull() {
            addCriterion("conversion_quantity is null");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityIsNotNull() {
            addCriterion("conversion_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityEqualTo(String value) {
            addCriterion("conversion_quantity =", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityNotEqualTo(String value) {
            addCriterion("conversion_quantity <>", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityGreaterThan(String value) {
            addCriterion("conversion_quantity >", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityGreaterThanOrEqualTo(String value) {
            addCriterion("conversion_quantity >=", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityLessThan(String value) {
            addCriterion("conversion_quantity <", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityLessThanOrEqualTo(String value) {
            addCriterion("conversion_quantity <=", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityLike(String value) {
            addCriterion("conversion_quantity like", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityNotLike(String value) {
            addCriterion("conversion_quantity not like", value, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityIn(List<String> values) {
            addCriterion("conversion_quantity in", values, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityNotIn(List<String> values) {
            addCriterion("conversion_quantity not in", values, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityBetween(String value1, String value2) {
            addCriterion("conversion_quantity between", value1, value2, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionQuantityNotBetween(String value1, String value2) {
            addCriterion("conversion_quantity not between", value1, value2, "conversionQuantity");
            return (Criteria) this;
        }

        public Criteria andConversionCostIsNull() {
            addCriterion("conversion_cost is null");
            return (Criteria) this;
        }

        public Criteria andConversionCostIsNotNull() {
            addCriterion("conversion_cost is not null");
            return (Criteria) this;
        }

        public Criteria andConversionCostEqualTo(String value) {
            addCriterion("conversion_cost =", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostNotEqualTo(String value) {
            addCriterion("conversion_cost <>", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostGreaterThan(String value) {
            addCriterion("conversion_cost >", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostGreaterThanOrEqualTo(String value) {
            addCriterion("conversion_cost >=", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostLessThan(String value) {
            addCriterion("conversion_cost <", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostLessThanOrEqualTo(String value) {
            addCriterion("conversion_cost <=", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostLike(String value) {
            addCriterion("conversion_cost like", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostNotLike(String value) {
            addCriterion("conversion_cost not like", value, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostIn(List<String> values) {
            addCriterion("conversion_cost in", values, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostNotIn(List<String> values) {
            addCriterion("conversion_cost not in", values, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostBetween(String value1, String value2) {
            addCriterion("conversion_cost between", value1, value2, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionCostNotBetween(String value1, String value2) {
            addCriterion("conversion_cost not between", value1, value2, "conversionCost");
            return (Criteria) this;
        }

        public Criteria andConversionRateIsNull() {
            addCriterion("conversion_rate is null");
            return (Criteria) this;
        }

        public Criteria andConversionRateIsNotNull() {
            addCriterion("conversion_rate is not null");
            return (Criteria) this;
        }

        public Criteria andConversionRateEqualTo(String value) {
            addCriterion("conversion_rate =", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateNotEqualTo(String value) {
            addCriterion("conversion_rate <>", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateGreaterThan(String value) {
            addCriterion("conversion_rate >", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateGreaterThanOrEqualTo(String value) {
            addCriterion("conversion_rate >=", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateLessThan(String value) {
            addCriterion("conversion_rate <", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateLessThanOrEqualTo(String value) {
            addCriterion("conversion_rate <=", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateLike(String value) {
            addCriterion("conversion_rate like", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateNotLike(String value) {
            addCriterion("conversion_rate not like", value, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateIn(List<String> values) {
            addCriterion("conversion_rate in", values, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateNotIn(List<String> values) {
            addCriterion("conversion_rate not in", values, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateBetween(String value1, String value2) {
            addCriterion("conversion_rate between", value1, value2, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andConversionRateNotBetween(String value1, String value2) {
            addCriterion("conversion_rate not between", value1, value2, "conversionRate");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionIsNull() {
            addCriterion("total_consumption is null");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionIsNotNull() {
            addCriterion("total_consumption is not null");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionEqualTo(String value) {
            addCriterion("total_consumption =", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionNotEqualTo(String value) {
            addCriterion("total_consumption <>", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionGreaterThan(String value) {
            addCriterion("total_consumption >", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionGreaterThanOrEqualTo(String value) {
            addCriterion("total_consumption >=", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionLessThan(String value) {
            addCriterion("total_consumption <", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionLessThanOrEqualTo(String value) {
            addCriterion("total_consumption <=", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionLike(String value) {
            addCriterion("total_consumption like", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionNotLike(String value) {
            addCriterion("total_consumption not like", value, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionIn(List<String> values) {
            addCriterion("total_consumption in", values, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionNotIn(List<String> values) {
            addCriterion("total_consumption not in", values, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionBetween(String value1, String value2) {
            addCriterion("total_consumption between", value1, value2, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andTotalConsumptionNotBetween(String value1, String value2) {
            addCriterion("total_consumption not between", value1, value2, "totalConsumption");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioIsNull() {
            addCriterion("consumption_ring_ratio is null");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioIsNotNull() {
            addCriterion("consumption_ring_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioEqualTo(String value) {
            addCriterion("consumption_ring_ratio =", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioNotEqualTo(String value) {
            addCriterion("consumption_ring_ratio <>", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioGreaterThan(String value) {
            addCriterion("consumption_ring_ratio >", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioGreaterThanOrEqualTo(String value) {
            addCriterion("consumption_ring_ratio >=", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioLessThan(String value) {
            addCriterion("consumption_ring_ratio <", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioLessThanOrEqualTo(String value) {
            addCriterion("consumption_ring_ratio <=", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioLike(String value) {
            addCriterion("consumption_ring_ratio like", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioNotLike(String value) {
            addCriterion("consumption_ring_ratio not like", value, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioIn(List<String> values) {
            addCriterion("consumption_ring_ratio in", values, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioNotIn(List<String> values) {
            addCriterion("consumption_ring_ratio not in", values, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioBetween(String value1, String value2) {
            addCriterion("consumption_ring_ratio between", value1, value2, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andConsumptionRingRatioNotBetween(String value1, String value2) {
            addCriterion("consumption_ring_ratio not between", value1, value2, "consumptionRingRatio");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(String value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(String value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(String value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(String value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(String value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLike(String value) {
            addCriterion("balance like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotLike(String value) {
            addCriterion("balance not like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<String> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<String> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(String value1, String value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(String value1, String value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceIsNull() {
            addCriterion("available_balance is null");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceIsNotNull() {
            addCriterion("available_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceEqualTo(String value) {
            addCriterion("available_balance =", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceNotEqualTo(String value) {
            addCriterion("available_balance <>", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceGreaterThan(String value) {
            addCriterion("available_balance >", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("available_balance >=", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceLessThan(String value) {
            addCriterion("available_balance <", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceLessThanOrEqualTo(String value) {
            addCriterion("available_balance <=", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceLike(String value) {
            addCriterion("available_balance like", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceNotLike(String value) {
            addCriterion("available_balance not like", value, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceIn(List<String> values) {
            addCriterion("available_balance in", values, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceNotIn(List<String> values) {
            addCriterion("available_balance not in", values, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceBetween(String value1, String value2) {
            addCriterion("available_balance between", value1, value2, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andAvailableBalanceNotBetween(String value1, String value2) {
            addCriterion("available_balance not between", value1, value2, "availableBalance");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
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