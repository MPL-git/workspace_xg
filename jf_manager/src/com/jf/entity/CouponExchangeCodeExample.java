package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponExchangeCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CouponExchangeCodeExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNull() {
            addCriterion("batch_num is null");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNotNull() {
            addCriterion("batch_num is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNumEqualTo(String value) {
            addCriterion("batch_num =", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotEqualTo(String value) {
            addCriterion("batch_num <>", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThan(String value) {
            addCriterion("batch_num >", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThanOrEqualTo(String value) {
            addCriterion("batch_num >=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThan(String value) {
            addCriterion("batch_num <", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThanOrEqualTo(String value) {
            addCriterion("batch_num <=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLike(String value) {
            addCriterion("batch_num like", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotLike(String value) {
            addCriterion("batch_num not like", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumIn(List<String> values) {
            addCriterion("batch_num in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotIn(List<String> values) {
            addCriterion("batch_num not in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumBetween(String value1, String value2) {
            addCriterion("batch_num between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotBetween(String value1, String value2) {
            addCriterion("batch_num not between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andIsExchangeIsNull() {
            addCriterion("is_exchange is null");
            return (Criteria) this;
        }

        public Criteria andIsExchangeIsNotNull() {
            addCriterion("is_exchange is not null");
            return (Criteria) this;
        }

        public Criteria andIsExchangeEqualTo(String value) {
            addCriterion("is_exchange =", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeNotEqualTo(String value) {
            addCriterion("is_exchange <>", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeGreaterThan(String value) {
            addCriterion("is_exchange >", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeGreaterThanOrEqualTo(String value) {
            addCriterion("is_exchange >=", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeLessThan(String value) {
            addCriterion("is_exchange <", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeLessThanOrEqualTo(String value) {
            addCriterion("is_exchange <=", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeLike(String value) {
            addCriterion("is_exchange like", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeNotLike(String value) {
            addCriterion("is_exchange not like", value, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeIn(List<String> values) {
            addCriterion("is_exchange in", values, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeNotIn(List<String> values) {
            addCriterion("is_exchange not in", values, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeBetween(String value1, String value2) {
            addCriterion("is_exchange between", value1, value2, "isExchange");
            return (Criteria) this;
        }

        public Criteria andIsExchangeNotBetween(String value1, String value2) {
            addCriterion("is_exchange not between", value1, value2, "isExchange");
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

        public Criteria andMemberCouponIdIsNull() {
            addCriterion("member_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdIsNotNull() {
            addCriterion("member_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdEqualTo(Integer value) {
            addCriterion("member_coupon_id =", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotEqualTo(Integer value) {
            addCriterion("member_coupon_id <>", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdGreaterThan(Integer value) {
            addCriterion("member_coupon_id >", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_coupon_id >=", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdLessThan(Integer value) {
            addCriterion("member_coupon_id <", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_coupon_id <=", value, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdIn(List<Integer> values) {
            addCriterion("member_coupon_id in", values, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotIn(List<Integer> values) {
            addCriterion("member_coupon_id not in", values, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("member_coupon_id between", value1, value2, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andMemberCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_coupon_id not between", value1, value2, "memberCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeDateIsNull() {
            addCriterion("exchange_date is null");
            return (Criteria) this;
        }

        public Criteria andExchangeDateIsNotNull() {
            addCriterion("exchange_date is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeDateEqualTo(Date value) {
            addCriterion("exchange_date =", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateNotEqualTo(Date value) {
            addCriterion("exchange_date <>", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateGreaterThan(Date value) {
            addCriterion("exchange_date >", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exchange_date >=", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateLessThan(Date value) {
            addCriterion("exchange_date <", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateLessThanOrEqualTo(Date value) {
            addCriterion("exchange_date <=", value, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateIn(List<Date> values) {
            addCriterion("exchange_date in", values, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateNotIn(List<Date> values) {
            addCriterion("exchange_date not in", values, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateBetween(Date value1, Date value2) {
            addCriterion("exchange_date between", value1, value2, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andExchangeDateNotBetween(Date value1, Date value2) {
            addCriterion("exchange_date not between", value1, value2, "exchangeDate");
            return (Criteria) this;
        }

        public Criteria andIsAllocationIsNull() {
            addCriterion("is_allocation is null");
            return (Criteria) this;
        }

        public Criteria andIsAllocationIsNotNull() {
            addCriterion("is_allocation is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllocationEqualTo(String value) {
            addCriterion("is_allocation =", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationNotEqualTo(String value) {
            addCriterion("is_allocation <>", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationGreaterThan(String value) {
            addCriterion("is_allocation >", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationGreaterThanOrEqualTo(String value) {
            addCriterion("is_allocation >=", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationLessThan(String value) {
            addCriterion("is_allocation <", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationLessThanOrEqualTo(String value) {
            addCriterion("is_allocation <=", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationLike(String value) {
            addCriterion("is_allocation like", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationNotLike(String value) {
            addCriterion("is_allocation not like", value, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationIn(List<String> values) {
            addCriterion("is_allocation in", values, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationNotIn(List<String> values) {
            addCriterion("is_allocation not in", values, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationBetween(String value1, String value2) {
            addCriterion("is_allocation between", value1, value2, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andIsAllocationNotBetween(String value1, String value2) {
            addCriterion("is_allocation not between", value1, value2, "isAllocation");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitIsNull() {
            addCriterion("exchange_limit is null");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitIsNotNull() {
            addCriterion("exchange_limit is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitEqualTo(String value) {
            addCriterion("exchange_limit =", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitNotEqualTo(String value) {
            addCriterion("exchange_limit <>", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitGreaterThan(String value) {
            addCriterion("exchange_limit >", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_limit >=", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitLessThan(String value) {
            addCriterion("exchange_limit <", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitLessThanOrEqualTo(String value) {
            addCriterion("exchange_limit <=", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitLike(String value) {
            addCriterion("exchange_limit like", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitNotLike(String value) {
            addCriterion("exchange_limit not like", value, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitIn(List<String> values) {
            addCriterion("exchange_limit in", values, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitNotIn(List<String> values) {
            addCriterion("exchange_limit not in", values, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitBetween(String value1, String value2) {
            addCriterion("exchange_limit between", value1, value2, "exchangeLimit");
            return (Criteria) this;
        }

        public Criteria andExchangeLimitNotBetween(String value1, String value2) {
            addCriterion("exchange_limit not between", value1, value2, "exchangeLimit");
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