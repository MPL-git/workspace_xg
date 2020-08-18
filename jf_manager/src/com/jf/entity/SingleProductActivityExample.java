package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SingleProductActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SingleProductActivityExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Integer value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Integer value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Integer value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Integer value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Integer> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Integer> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(String value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(String value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(String value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(String value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(String value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(String value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLike(String value) {
            addCriterion("original_price like", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotLike(String value) {
            addCriterion("original_price not like", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<String> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<String> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(String value1, String value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(String value1, String value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIsNull() {
            addCriterion("activity_price is null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIsNotNull() {
            addCriterion("activity_price is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPriceEqualTo(BigDecimal value) {
            addCriterion("activity_price =", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotEqualTo(BigDecimal value) {
            addCriterion("activity_price <>", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThan(BigDecimal value) {
            addCriterion("activity_price >", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price >=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThan(BigDecimal value) {
            addCriterion("activity_price <", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_price <=", value, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceIn(List<BigDecimal> values) {
            addCriterion("activity_price in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotIn(List<BigDecimal> values) {
            addCriterion("activity_price not in", values, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andActivityPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_price not between", value1, value2, "activityPrice");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeIsNull() {
            addCriterion("seckill_type is null");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeIsNotNull() {
            addCriterion("seckill_type is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeEqualTo(String value) {
            addCriterion("seckill_type =", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeNotEqualTo(String value) {
            addCriterion("seckill_type <>", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeGreaterThan(String value) {
            addCriterion("seckill_type >", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("seckill_type >=", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeLessThan(String value) {
            addCriterion("seckill_type <", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeLessThanOrEqualTo(String value) {
            addCriterion("seckill_type <=", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeLike(String value) {
            addCriterion("seckill_type like", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeNotLike(String value) {
            addCriterion("seckill_type not like", value, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeIn(List<String> values) {
            addCriterion("seckill_type in", values, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeNotIn(List<String> values) {
            addCriterion("seckill_type not in", values, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeBetween(String value1, String value2) {
            addCriterion("seckill_type between", value1, value2, "seckillType");
            return (Criteria) this;
        }

        public Criteria andSeckillTypeNotBetween(String value1, String value2) {
            addCriterion("seckill_type not between", value1, value2, "seckillType");
            return (Criteria) this;
        }

        public Criteria andComparePriceIsNull() {
            addCriterion("compare_price is null");
            return (Criteria) this;
        }

        public Criteria andComparePriceIsNotNull() {
            addCriterion("compare_price is not null");
            return (Criteria) this;
        }

        public Criteria andComparePriceEqualTo(BigDecimal value) {
            addCriterion("compare_price =", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceNotEqualTo(BigDecimal value) {
            addCriterion("compare_price <>", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceGreaterThan(BigDecimal value) {
            addCriterion("compare_price >", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("compare_price >=", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceLessThan(BigDecimal value) {
            addCriterion("compare_price <", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("compare_price <=", value, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceIn(List<BigDecimal> values) {
            addCriterion("compare_price in", values, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceNotIn(List<BigDecimal> values) {
            addCriterion("compare_price not in", values, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compare_price between", value1, value2, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andComparePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("compare_price not between", value1, value2, "comparePrice");
            return (Criteria) this;
        }

        public Criteria andExpectedDateIsNull() {
            addCriterion("expected_date is null");
            return (Criteria) this;
        }

        public Criteria andExpectedDateIsNotNull() {
            addCriterion("expected_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedDateEqualTo(Date value) {
            addCriterionForJDBCDate("expected_date =", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("expected_date <>", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateGreaterThan(Date value) {
            addCriterionForJDBCDate("expected_date >", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("expected_date >=", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateLessThan(Date value) {
            addCriterionForJDBCDate("expected_date <", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("expected_date <=", value, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateIn(List<Date> values) {
            addCriterionForJDBCDate("expected_date in", values, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("expected_date not in", values, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("expected_date between", value1, value2, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andExpectedDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("expected_date not between", value1, value2, "expectedDate");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andIsCloseIsNull() {
            addCriterion("is_close is null");
            return (Criteria) this;
        }

        public Criteria andIsCloseIsNotNull() {
            addCriterion("is_close is not null");
            return (Criteria) this;
        }

        public Criteria andIsCloseEqualTo(String value) {
            addCriterion("is_close =", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotEqualTo(String value) {
            addCriterion("is_close <>", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseGreaterThan(String value) {
            addCriterion("is_close >", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseGreaterThanOrEqualTo(String value) {
            addCriterion("is_close >=", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseLessThan(String value) {
            addCriterion("is_close <", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseLessThanOrEqualTo(String value) {
            addCriterion("is_close <=", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseLike(String value) {
            addCriterion("is_close like", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotLike(String value) {
            addCriterion("is_close not like", value, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseIn(List<String> values) {
            addCriterion("is_close in", values, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotIn(List<String> values) {
            addCriterion("is_close not in", values, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseBetween(String value1, String value2) {
            addCriterion("is_close between", value1, value2, "isClose");
            return (Criteria) this;
        }

        public Criteria andIsCloseNotBetween(String value1, String value2) {
            addCriterion("is_close not between", value1, value2, "isClose");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByIsNull() {
            addCriterion("first_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByIsNotNull() {
            addCriterion("first_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByEqualTo(Integer value) {
            addCriterion("first_audit_by =", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByNotEqualTo(Integer value) {
            addCriterion("first_audit_by <>", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByGreaterThan(Integer value) {
            addCriterion("first_audit_by >", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_audit_by >=", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByLessThan(Integer value) {
            addCriterion("first_audit_by <", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("first_audit_by <=", value, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByIn(List<Integer> values) {
            addCriterion("first_audit_by in", values, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByNotIn(List<Integer> values) {
            addCriterion("first_audit_by not in", values, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByBetween(Integer value1, Integer value2) {
            addCriterion("first_audit_by between", value1, value2, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andFirstAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("first_audit_by not between", value1, value2, "firstAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByIsNull() {
            addCriterion("schedule_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByIsNotNull() {
            addCriterion("schedule_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByEqualTo(Integer value) {
            addCriterion("schedule_audit_by =", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByNotEqualTo(Integer value) {
            addCriterion("schedule_audit_by <>", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByGreaterThan(Integer value) {
            addCriterion("schedule_audit_by >", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_audit_by >=", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByLessThan(Integer value) {
            addCriterion("schedule_audit_by <", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_audit_by <=", value, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByIn(List<Integer> values) {
            addCriterion("schedule_audit_by in", values, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByNotIn(List<Integer> values) {
            addCriterion("schedule_audit_by not in", values, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByBetween(Integer value1, Integer value2) {
            addCriterion("schedule_audit_by between", value1, value2, "scheduleAuditBy");
            return (Criteria) this;
        }

        public Criteria andScheduleAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_audit_by not between", value1, value2, "scheduleAuditBy");
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

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumIsNull() {
            addCriterion("unreality_num is null");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumIsNotNull() {
            addCriterion("unreality_num is not null");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumEqualTo(Integer value) {
            addCriterion("unreality_num =", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumNotEqualTo(Integer value) {
            addCriterion("unreality_num <>", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumGreaterThan(Integer value) {
            addCriterion("unreality_num >", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("unreality_num >=", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumLessThan(Integer value) {
            addCriterion("unreality_num <", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumLessThanOrEqualTo(Integer value) {
            addCriterion("unreality_num <=", value, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumIn(List<Integer> values) {
            addCriterion("unreality_num in", values, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumNotIn(List<Integer> values) {
            addCriterion("unreality_num not in", values, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumBetween(Integer value1, Integer value2) {
            addCriterion("unreality_num between", value1, value2, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andUnrealityNumNotBetween(Integer value1, Integer value2) {
            addCriterion("unreality_num not between", value1, value2, "unrealityNum");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinIsNull() {
            addCriterion("tomorrow_increase_min is null");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinIsNotNull() {
            addCriterion("tomorrow_increase_min is not null");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinEqualTo(Integer value) {
            addCriterion("tomorrow_increase_min =", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinNotEqualTo(Integer value) {
            addCriterion("tomorrow_increase_min <>", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinGreaterThan(Integer value) {
            addCriterion("tomorrow_increase_min >", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("tomorrow_increase_min >=", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinLessThan(Integer value) {
            addCriterion("tomorrow_increase_min <", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinLessThanOrEqualTo(Integer value) {
            addCriterion("tomorrow_increase_min <=", value, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinIn(List<Integer> values) {
            addCriterion("tomorrow_increase_min in", values, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinNotIn(List<Integer> values) {
            addCriterion("tomorrow_increase_min not in", values, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinBetween(Integer value1, Integer value2) {
            addCriterion("tomorrow_increase_min between", value1, value2, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMinNotBetween(Integer value1, Integer value2) {
            addCriterion("tomorrow_increase_min not between", value1, value2, "tomorrowIncreaseMin");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxIsNull() {
            addCriterion("tomorrow_increase_max is null");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxIsNotNull() {
            addCriterion("tomorrow_increase_max is not null");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxEqualTo(Integer value) {
            addCriterion("tomorrow_increase_max =", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxNotEqualTo(Integer value) {
            addCriterion("tomorrow_increase_max <>", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxGreaterThan(Integer value) {
            addCriterion("tomorrow_increase_max >", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("tomorrow_increase_max >=", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxLessThan(Integer value) {
            addCriterion("tomorrow_increase_max <", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxLessThanOrEqualTo(Integer value) {
            addCriterion("tomorrow_increase_max <=", value, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxIn(List<Integer> values) {
            addCriterion("tomorrow_increase_max in", values, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxNotIn(List<Integer> values) {
            addCriterion("tomorrow_increase_max not in", values, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxBetween(Integer value1, Integer value2) {
            addCriterion("tomorrow_increase_max between", value1, value2, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andTomorrowIncreaseMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("tomorrow_increase_max not between", value1, value2, "tomorrowIncreaseMax");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIsNull() {
            addCriterion("is_watermark is null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIsNotNull() {
            addCriterion("is_watermark is not null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkEqualTo(String value) {
            addCriterion("is_watermark =", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotEqualTo(String value) {
            addCriterion("is_watermark <>", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThan(String value) {
            addCriterion("is_watermark >", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThanOrEqualTo(String value) {
            addCriterion("is_watermark >=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThan(String value) {
            addCriterion("is_watermark <", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThanOrEqualTo(String value) {
            addCriterion("is_watermark <=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLike(String value) {
            addCriterion("is_watermark like", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotLike(String value) {
            addCriterion("is_watermark not like", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIn(List<String> values) {
            addCriterion("is_watermark in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotIn(List<String> values) {
            addCriterion("is_watermark not in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkBetween(String value1, String value2) {
            addCriterion("is_watermark between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotBetween(String value1, String value2) {
            addCriterion("is_watermark not between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIsNull() {
            addCriterion("platform_preferential is null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIsNotNull() {
            addCriterion("platform_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialEqualTo(BigDecimal value) {
            addCriterion("platform_preferential =", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <>", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThan(BigDecimal value) {
            addCriterion("platform_preferential >", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential >=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThan(BigDecimal value) {
            addCriterion("platform_preferential <", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_preferential <=", value, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialIn(List<BigDecimal> values) {
            addCriterion("platform_preferential in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("platform_preferential not in", values, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andPlatformPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_preferential not between", value1, value2, "platformPreferential");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdIsNull() {
            addCriterion("seckill_time_id is null");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdIsNotNull() {
            addCriterion("seckill_time_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdEqualTo(Integer value) {
            addCriterion("seckill_time_id =", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdNotEqualTo(Integer value) {
            addCriterion("seckill_time_id <>", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdGreaterThan(Integer value) {
            addCriterion("seckill_time_id >", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("seckill_time_id >=", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdLessThan(Integer value) {
            addCriterion("seckill_time_id <", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdLessThanOrEqualTo(Integer value) {
            addCriterion("seckill_time_id <=", value, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdIn(List<Integer> values) {
            addCriterion("seckill_time_id in", values, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdNotIn(List<Integer> values) {
            addCriterion("seckill_time_id not in", values, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdBetween(Integer value1, Integer value2) {
            addCriterion("seckill_time_id between", value1, value2, "seckillTimeId");
            return (Criteria) this;
        }

        public Criteria andSeckillTimeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("seckill_time_id not between", value1, value2, "seckillTimeId");
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