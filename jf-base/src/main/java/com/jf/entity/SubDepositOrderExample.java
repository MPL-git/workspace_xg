package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubDepositOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SubDepositOrderExample() {
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

        public Criteria andCombineDepositOrderIdIsNull() {
            addCriterion("combine_deposit_order_id is null");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdIsNotNull() {
            addCriterion("combine_deposit_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id =", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id <>", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdGreaterThan(Integer value) {
            addCriterion("combine_deposit_order_id >", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id >=", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdLessThan(Integer value) {
            addCriterion("combine_deposit_order_id <", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("combine_deposit_order_id <=", value, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdIn(List<Integer> values) {
            addCriterion("combine_deposit_order_id in", values, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotIn(List<Integer> values) {
            addCriterion("combine_deposit_order_id not in", values, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("combine_deposit_order_id between", value1, value2, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andCombineDepositOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("combine_deposit_order_id not between", value1, value2, "combineDepositOrderId");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeIsNull() {
            addCriterion("sub_deposit_order_code is null");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeIsNotNull() {
            addCriterion("sub_deposit_order_code is not null");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeEqualTo(String value) {
            addCriterion("sub_deposit_order_code =", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeNotEqualTo(String value) {
            addCriterion("sub_deposit_order_code <>", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeGreaterThan(String value) {
            addCriterion("sub_deposit_order_code >", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sub_deposit_order_code >=", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeLessThan(String value) {
            addCriterion("sub_deposit_order_code <", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("sub_deposit_order_code <=", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeLike(String value) {
            addCriterion("sub_deposit_order_code like", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeNotLike(String value) {
            addCriterion("sub_deposit_order_code not like", value, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeIn(List<String> values) {
            addCriterion("sub_deposit_order_code in", values, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeNotIn(List<String> values) {
            addCriterion("sub_deposit_order_code not in", values, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeBetween(String value1, String value2) {
            addCriterion("sub_deposit_order_code between", value1, value2, "subDepositOrderCode");
            return (Criteria) this;
        }

        public Criteria andSubDepositOrderCodeNotBetween(String value1, String value2) {
            addCriterion("sub_deposit_order_code not between", value1, value2, "subDepositOrderCode");
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

        public Criteria andMchtTypeIsNull() {
            addCriterion("mcht_type is null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIsNotNull() {
            addCriterion("mcht_type is not null");
            return (Criteria) this;
        }

        public Criteria andMchtTypeEqualTo(String value) {
            addCriterion("mcht_type =", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotEqualTo(String value) {
            addCriterion("mcht_type <>", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThan(String value) {
            addCriterion("mcht_type >", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("mcht_type >=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThan(String value) {
            addCriterion("mcht_type <", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLessThanOrEqualTo(String value) {
            addCriterion("mcht_type <=", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeLike(String value) {
            addCriterion("mcht_type like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotLike(String value) {
            addCriterion("mcht_type not like", value, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeIn(List<String> values) {
            addCriterion("mcht_type in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotIn(List<String> values) {
            addCriterion("mcht_type not in", values, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeBetween(String value1, String value2) {
            addCriterion("mcht_type between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMchtTypeNotBetween(String value1, String value2) {
            addCriterion("mcht_type not between", value1, value2, "mchtType");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdIsNull() {
            addCriterion("activity_pre_sell_product_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdIsNotNull() {
            addCriterion("activity_pre_sell_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdEqualTo(Integer value) {
            addCriterion("activity_pre_sell_product_id =", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdNotEqualTo(Integer value) {
            addCriterion("activity_pre_sell_product_id <>", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdGreaterThan(Integer value) {
            addCriterion("activity_pre_sell_product_id >", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_pre_sell_product_id >=", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdLessThan(Integer value) {
            addCriterion("activity_pre_sell_product_id <", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_pre_sell_product_id <=", value, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdIn(List<Integer> values) {
            addCriterion("activity_pre_sell_product_id in", values, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdNotIn(List<Integer> values) {
            addCriterion("activity_pre_sell_product_id not in", values, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_pre_sell_product_id between", value1, value2, "activityPreSellProductId");
            return (Criteria) this;
        }

        public Criteria andActivityPreSellProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_pre_sell_product_id not between", value1, value2, "activityPreSellProductId");
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

        public Criteria andProductItemIdIsNull() {
            addCriterion("product_item_id is null");
            return (Criteria) this;
        }

        public Criteria andProductItemIdIsNotNull() {
            addCriterion("product_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductItemIdEqualTo(Integer value) {
            addCriterion("product_item_id =", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdNotEqualTo(Integer value) {
            addCriterion("product_item_id <>", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdGreaterThan(Integer value) {
            addCriterion("product_item_id >", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_item_id >=", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdLessThan(Integer value) {
            addCriterion("product_item_id <", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_item_id <=", value, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdIn(List<Integer> values) {
            addCriterion("product_item_id in", values, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdNotIn(List<Integer> values) {
            addCriterion("product_item_id not in", values, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdBetween(Integer value1, Integer value2) {
            addCriterion("product_item_id between", value1, value2, "productItemId");
            return (Criteria) this;
        }

        public Criteria andProductItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_item_id not between", value1, value2, "productItemId");
            return (Criteria) this;
        }

        public Criteria andSkuPicIsNull() {
            addCriterion("sku_pic is null");
            return (Criteria) this;
        }

        public Criteria andSkuPicIsNotNull() {
            addCriterion("sku_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPicEqualTo(String value) {
            addCriterion("sku_pic =", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotEqualTo(String value) {
            addCriterion("sku_pic <>", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicGreaterThan(String value) {
            addCriterion("sku_pic >", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicGreaterThanOrEqualTo(String value) {
            addCriterion("sku_pic >=", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLessThan(String value) {
            addCriterion("sku_pic <", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLessThanOrEqualTo(String value) {
            addCriterion("sku_pic <=", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLike(String value) {
            addCriterion("sku_pic like", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotLike(String value) {
            addCriterion("sku_pic not like", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicIn(List<String> values) {
            addCriterion("sku_pic in", values, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotIn(List<String> values) {
            addCriterion("sku_pic not in", values, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicBetween(String value1, String value2) {
            addCriterion("sku_pic between", value1, value2, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotBetween(String value1, String value2) {
            addCriterion("sku_pic not between", value1, value2, "skuPic");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andArtNoIsNull() {
            addCriterion("art_no is null");
            return (Criteria) this;
        }

        public Criteria andArtNoIsNotNull() {
            addCriterion("art_no is not null");
            return (Criteria) this;
        }

        public Criteria andArtNoEqualTo(String value) {
            addCriterion("art_no =", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotEqualTo(String value) {
            addCriterion("art_no <>", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoGreaterThan(String value) {
            addCriterion("art_no >", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoGreaterThanOrEqualTo(String value) {
            addCriterion("art_no >=", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLessThan(String value) {
            addCriterion("art_no <", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLessThanOrEqualTo(String value) {
            addCriterion("art_no <=", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoLike(String value) {
            addCriterion("art_no like", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotLike(String value) {
            addCriterion("art_no not like", value, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoIn(List<String> values) {
            addCriterion("art_no in", values, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotIn(List<String> values) {
            addCriterion("art_no not in", values, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoBetween(String value1, String value2) {
            addCriterion("art_no between", value1, value2, "artNo");
            return (Criteria) this;
        }

        public Criteria andArtNoNotBetween(String value1, String value2) {
            addCriterion("art_no not between", value1, value2, "artNo");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNull() {
            addCriterion("brand_name is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("brand_name =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("brand_name <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("brand_name >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_name >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("brand_name <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("brand_name <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("brand_name like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("brand_name not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("brand_name in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("brand_name not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("brand_name between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("brand_name not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIsNull() {
            addCriterion("product_prop_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIsNotNull() {
            addCriterion("product_prop_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductPropDescEqualTo(String value) {
            addCriterion("product_prop_desc =", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotEqualTo(String value) {
            addCriterion("product_prop_desc <>", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescGreaterThan(String value) {
            addCriterion("product_prop_desc >", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_prop_desc >=", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLessThan(String value) {
            addCriterion("product_prop_desc <", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLessThanOrEqualTo(String value) {
            addCriterion("product_prop_desc <=", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescLike(String value) {
            addCriterion("product_prop_desc like", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotLike(String value) {
            addCriterion("product_prop_desc not like", value, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescIn(List<String> values) {
            addCriterion("product_prop_desc in", values, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotIn(List<String> values) {
            addCriterion("product_prop_desc not in", values, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescBetween(String value1, String value2) {
            addCriterion("product_prop_desc between", value1, value2, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andProductPropDescNotBetween(String value1, String value2) {
            addCriterion("product_prop_desc not between", value1, value2, "productPropDesc");
            return (Criteria) this;
        }

        public Criteria andSkuIsNull() {
            addCriterion("sku is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("sku is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(String value) {
            addCriterion("sku =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(String value) {
            addCriterion("sku <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(String value) {
            addCriterion("sku >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(String value) {
            addCriterion("sku >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(String value) {
            addCriterion("sku <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(String value) {
            addCriterion("sku <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLike(String value) {
            addCriterion("sku like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotLike(String value) {
            addCriterion("sku not like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<String> values) {
            addCriterion("sku in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<String> values) {
            addCriterion("sku not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(String value1, String value2) {
            addCriterion("sku between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(String value1, String value2) {
            addCriterion("sku not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andTagPriceIsNull() {
            addCriterion("tag_price is null");
            return (Criteria) this;
        }

        public Criteria andTagPriceIsNotNull() {
            addCriterion("tag_price is not null");
            return (Criteria) this;
        }

        public Criteria andTagPriceEqualTo(BigDecimal value) {
            addCriterion("tag_price =", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceNotEqualTo(BigDecimal value) {
            addCriterion("tag_price <>", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceGreaterThan(BigDecimal value) {
            addCriterion("tag_price >", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tag_price >=", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceLessThan(BigDecimal value) {
            addCriterion("tag_price <", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tag_price <=", value, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceIn(List<BigDecimal> values) {
            addCriterion("tag_price in", values, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceNotIn(List<BigDecimal> values) {
            addCriterion("tag_price not in", values, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tag_price between", value1, value2, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andTagPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tag_price not between", value1, value2, "tagPrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(BigDecimal value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(BigDecimal value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(BigDecimal value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<BigDecimal> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceIsNull() {
            addCriterion("mall_price is null");
            return (Criteria) this;
        }

        public Criteria andMallPriceIsNotNull() {
            addCriterion("mall_price is not null");
            return (Criteria) this;
        }

        public Criteria andMallPriceEqualTo(BigDecimal value) {
            addCriterion("mall_price =", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceNotEqualTo(BigDecimal value) {
            addCriterion("mall_price <>", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceGreaterThan(BigDecimal value) {
            addCriterion("mall_price >", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mall_price >=", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceLessThan(BigDecimal value) {
            addCriterion("mall_price <", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mall_price <=", value, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceIn(List<BigDecimal> values) {
            addCriterion("mall_price in", values, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceNotIn(List<BigDecimal> values) {
            addCriterion("mall_price not in", values, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mall_price between", value1, value2, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andMallPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mall_price not between", value1, value2, "mallPrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceIsNull() {
            addCriterion("settle_price is null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceIsNotNull() {
            addCriterion("settle_price is not null");
            return (Criteria) this;
        }

        public Criteria andSettlePriceEqualTo(BigDecimal value) {
            addCriterion("settle_price =", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotEqualTo(BigDecimal value) {
            addCriterion("settle_price <>", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceGreaterThan(BigDecimal value) {
            addCriterion("settle_price >", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price >=", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceLessThan(BigDecimal value) {
            addCriterion("settle_price <", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_price <=", value, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceIn(List<BigDecimal> values) {
            addCriterion("settle_price in", values, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotIn(List<BigDecimal> values) {
            addCriterion("settle_price not in", values, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price between", value1, value2, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andSettlePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_price not between", value1, value2, "settlePrice");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDeductAmountIsNull() {
            addCriterion("deduct_amount is null");
            return (Criteria) this;
        }

        public Criteria andDeductAmountIsNotNull() {
            addCriterion("deduct_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDeductAmountEqualTo(BigDecimal value) {
            addCriterion("deduct_amount =", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountNotEqualTo(BigDecimal value) {
            addCriterion("deduct_amount <>", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountGreaterThan(BigDecimal value) {
            addCriterion("deduct_amount >", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deduct_amount >=", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountLessThan(BigDecimal value) {
            addCriterion("deduct_amount <", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deduct_amount <=", value, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountIn(List<BigDecimal> values) {
            addCriterion("deduct_amount in", values, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountNotIn(List<BigDecimal> values) {
            addCriterion("deduct_amount not in", values, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduct_amount between", value1, value2, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andDeductAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduct_amount not between", value1, value2, "deductAmount");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
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

        public Criteria andPopCommissionRateIsNull() {
            addCriterion("pop_commission_rate is null");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateIsNotNull() {
            addCriterion("pop_commission_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate =", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate <>", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateGreaterThan(BigDecimal value) {
            addCriterion("pop_commission_rate >", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate >=", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateLessThan(BigDecimal value) {
            addCriterion("pop_commission_rate <", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pop_commission_rate <=", value, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateIn(List<BigDecimal> values) {
            addCriterion("pop_commission_rate in", values, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotIn(List<BigDecimal> values) {
            addCriterion("pop_commission_rate not in", values, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pop_commission_rate between", value1, value2, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andPopCommissionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pop_commission_rate not between", value1, value2, "popCommissionRate");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNull() {
            addCriterion("settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNotNull() {
            addCriterion("settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountEqualTo(BigDecimal value) {
            addCriterion("settle_amount =", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("settle_amount <>", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("settle_amount >", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount >=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThan(BigDecimal value) {
            addCriterion("settle_amount <", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount <=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIn(List<BigDecimal> values) {
            addCriterion("settle_amount in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("settle_amount not in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount not between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountIsNull() {
            addCriterion("commission_amount is null");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountIsNotNull() {
            addCriterion("commission_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountEqualTo(BigDecimal value) {
            addCriterion("commission_amount =", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotEqualTo(BigDecimal value) {
            addCriterion("commission_amount <>", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountGreaterThan(BigDecimal value) {
            addCriterion("commission_amount >", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commission_amount >=", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountLessThan(BigDecimal value) {
            addCriterion("commission_amount <", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commission_amount <=", value, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountIn(List<BigDecimal> values) {
            addCriterion("commission_amount in", values, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotIn(List<BigDecimal> values) {
            addCriterion("commission_amount not in", values, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission_amount between", value1, value2, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andCommissionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission_amount not between", value1, value2, "commissionAmount");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksIsNull() {
            addCriterion("member_remarks is null");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksIsNotNull() {
            addCriterion("member_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksEqualTo(String value) {
            addCriterion("member_remarks =", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksNotEqualTo(String value) {
            addCriterion("member_remarks <>", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksGreaterThan(String value) {
            addCriterion("member_remarks >", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("member_remarks >=", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksLessThan(String value) {
            addCriterion("member_remarks <", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksLessThanOrEqualTo(String value) {
            addCriterion("member_remarks <=", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksLike(String value) {
            addCriterion("member_remarks like", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksNotLike(String value) {
            addCriterion("member_remarks not like", value, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksIn(List<String> values) {
            addCriterion("member_remarks in", values, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksNotIn(List<String> values) {
            addCriterion("member_remarks not in", values, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksBetween(String value1, String value2) {
            addCriterion("member_remarks between", value1, value2, "memberRemarks");
            return (Criteria) this;
        }

        public Criteria andMemberRemarksNotBetween(String value1, String value2) {
            addCriterion("member_remarks not between", value1, value2, "memberRemarks");
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

        public Criteria andPayDateIsNull() {
            addCriterion("pay_date is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("pay_date is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Date value) {
            addCriterion("pay_date =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Date value) {
            addCriterion("pay_date <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Date value) {
            addCriterion("pay_date >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_date >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Date value) {
            addCriterion("pay_date <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Date value) {
            addCriterion("pay_date <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Date> values) {
            addCriterion("pay_date in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Date> values) {
            addCriterion("pay_date not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Date value1, Date value2) {
            addCriterion("pay_date between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Date value1, Date value2) {
            addCriterion("pay_date not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNull() {
            addCriterion("complete_date is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNotNull() {
            addCriterion("complete_date is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateEqualTo(Date value) {
            addCriterion("complete_date =", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotEqualTo(Date value) {
            addCriterion("complete_date <>", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThan(Date value) {
            addCriterion("complete_date >", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_date >=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThan(Date value) {
            addCriterion("complete_date <", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThanOrEqualTo(Date value) {
            addCriterion("complete_date <=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIn(List<Date> values) {
            addCriterion("complete_date in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotIn(List<Date> values) {
            addCriterion("complete_date not in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateBetween(Date value1, Date value2) {
            addCriterion("complete_date between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotBetween(Date value1, Date value2) {
            addCriterion("complete_date not between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNull() {
            addCriterion("mcht_settle_order_id is null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIsNotNull() {
            addCriterion("mcht_settle_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id =", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <>", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThan(Integer value) {
            addCriterion("mcht_settle_order_id >", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id >=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThan(Integer value) {
            addCriterion("mcht_settle_order_id <", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("mcht_settle_order_id <=", value, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotIn(List<Integer> values) {
            addCriterion("mcht_settle_order_id not in", values, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id between", value1, value2, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andMchtSettleOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mcht_settle_order_id not between", value1, value2, "mchtSettleOrderId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIsNull() {
            addCriterion("shopping_cart_id is null");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIsNotNull() {
            addCriterion("shopping_cart_id is not null");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdEqualTo(Integer value) {
            addCriterion("shopping_cart_id =", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotEqualTo(Integer value) {
            addCriterion("shopping_cart_id <>", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdGreaterThan(Integer value) {
            addCriterion("shopping_cart_id >", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopping_cart_id >=", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdLessThan(Integer value) {
            addCriterion("shopping_cart_id <", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdLessThanOrEqualTo(Integer value) {
            addCriterion("shopping_cart_id <=", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIn(List<Integer> values) {
            addCriterion("shopping_cart_id in", values, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotIn(List<Integer> values) {
            addCriterion("shopping_cart_id not in", values, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdBetween(Integer value1, Integer value2) {
            addCriterion("shopping_cart_id between", value1, value2, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shopping_cart_id not between", value1, value2, "shoppingCartId");
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