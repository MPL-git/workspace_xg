package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDtlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public OrderDtlExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIsNull() {
            addCriterion("activity_area_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIsNotNull() {
            addCriterion("activity_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdEqualTo(Integer value) {
            addCriterion("activity_area_id =", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotEqualTo(Integer value) {
            addCriterion("activity_area_id <>", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdGreaterThan(Integer value) {
            addCriterion("activity_area_id >", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_area_id >=", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdLessThan(Integer value) {
            addCriterion("activity_area_id <", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_area_id <=", value, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdIn(List<Integer> values) {
            addCriterion("activity_area_id in", values, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotIn(List<Integer> values) {
            addCriterion("activity_area_id not in", values, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_area_id between", value1, value2, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andActivityAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_area_id not between", value1, value2, "activityAreaId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdIsNull() {
            addCriterion("single_product_activity_id is null");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdIsNotNull() {
            addCriterion("single_product_activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdEqualTo(Integer value) {
            addCriterion("single_product_activity_id =", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotEqualTo(Integer value) {
            addCriterion("single_product_activity_id <>", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdGreaterThan(Integer value) {
            addCriterion("single_product_activity_id >", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_product_activity_id >=", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdLessThan(Integer value) {
            addCriterion("single_product_activity_id <", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("single_product_activity_id <=", value, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdIn(List<Integer> values) {
            addCriterion("single_product_activity_id in", values, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotIn(List<Integer> values) {
            addCriterion("single_product_activity_id not in", values, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("single_product_activity_id between", value1, value2, "singleProductActivityId");
            return (Criteria) this;
        }

        public Criteria andSingleProductActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("single_product_activity_id not between", value1, value2, "singleProductActivityId");
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

        public Criteria andCloudProductItemIdIsNull() {
            addCriterion("cloud_product_item_id is null");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdIsNotNull() {
            addCriterion("cloud_product_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdEqualTo(Integer value) {
            addCriterion("cloud_product_item_id =", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdNotEqualTo(Integer value) {
            addCriterion("cloud_product_item_id <>", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdGreaterThan(Integer value) {
            addCriterion("cloud_product_item_id >", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cloud_product_item_id >=", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdLessThan(Integer value) {
            addCriterion("cloud_product_item_id <", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("cloud_product_item_id <=", value, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdIn(List<Integer> values) {
            addCriterion("cloud_product_item_id in", values, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdNotIn(List<Integer> values) {
            addCriterion("cloud_product_item_id not in", values, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdBetween(Integer value1, Integer value2) {
            addCriterion("cloud_product_item_id between", value1, value2, "cloudProductItemId");
            return (Criteria) this;
        }

        public Criteria andCloudProductItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cloud_product_item_id not between", value1, value2, "cloudProductItemId");
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

        public Criteria andMchtPreferentialIsNull() {
            addCriterion("mcht_preferential is null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialIsNotNull() {
            addCriterion("mcht_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential =", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential <>", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialGreaterThan(BigDecimal value) {
            addCriterion("mcht_preferential >", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential >=", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialLessThan(BigDecimal value) {
            addCriterion("mcht_preferential <", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mcht_preferential <=", value, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential in", values, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("mcht_preferential not in", values, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential between", value1, value2, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andMchtPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mcht_preferential not between", value1, value2, "mchtPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNull() {
            addCriterion("integral_preferential is null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIsNotNull() {
            addCriterion("integral_preferential is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialEqualTo(BigDecimal value) {
            addCriterion("integral_preferential =", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <>", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThan(BigDecimal value) {
            addCriterion("integral_preferential >", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential >=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThan(BigDecimal value) {
            addCriterion("integral_preferential <", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_preferential <=", value, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialIn(List<BigDecimal> values) {
            addCriterion("integral_preferential in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotIn(List<BigDecimal> values) {
            addCriterion("integral_preferential not in", values, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential between", value1, value2, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andIntegralPreferentialNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_preferential not between", value1, value2, "integralPreferential");
            return (Criteria) this;
        }

        public Criteria andAllowanceIsNull() {
            addCriterion("allowance is null");
            return (Criteria) this;
        }

        public Criteria andAllowanceIsNotNull() {
            addCriterion("allowance is not null");
            return (Criteria) this;
        }

        public Criteria andAllowanceEqualTo(BigDecimal value) {
            addCriterion("allowance =", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceNotEqualTo(BigDecimal value) {
            addCriterion("allowance <>", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceGreaterThan(BigDecimal value) {
            addCriterion("allowance >", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("allowance >=", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceLessThan(BigDecimal value) {
            addCriterion("allowance <", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("allowance <=", value, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceIn(List<BigDecimal> values) {
            addCriterion("allowance in", values, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceNotIn(List<BigDecimal> values) {
            addCriterion("allowance not in", values, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allowance between", value1, value2, "allowance");
            return (Criteria) this;
        }

        public Criteria andAllowanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("allowance not between", value1, value2, "allowance");
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

        public Criteria andRefundDateIsNull() {
            addCriterion("refund_date is null");
            return (Criteria) this;
        }

        public Criteria andRefundDateIsNotNull() {
            addCriterion("refund_date is not null");
            return (Criteria) this;
        }

        public Criteria andRefundDateEqualTo(Date value) {
            addCriterion("refund_date =", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateNotEqualTo(Date value) {
            addCriterion("refund_date <>", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateGreaterThan(Date value) {
            addCriterion("refund_date >", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateGreaterThanOrEqualTo(Date value) {
            addCriterion("refund_date >=", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateLessThan(Date value) {
            addCriterion("refund_date <", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateLessThanOrEqualTo(Date value) {
            addCriterion("refund_date <=", value, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateIn(List<Date> values) {
            addCriterion("refund_date in", values, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateNotIn(List<Date> values) {
            addCriterion("refund_date not in", values, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateBetween(Date value1, Date value2) {
            addCriterion("refund_date between", value1, value2, "refundDate");
            return (Criteria) this;
        }

        public Criteria andRefundDateNotBetween(Date value1, Date value2) {
            addCriterion("refund_date not between", value1, value2, "refundDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNull() {
            addCriterion("product_status is null");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNotNull() {
            addCriterion("product_status is not null");
            return (Criteria) this;
        }

        public Criteria andProductStatusEqualTo(String value) {
            addCriterion("product_status =", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotEqualTo(String value) {
            addCriterion("product_status <>", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThan(String value) {
            addCriterion("product_status >", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThanOrEqualTo(String value) {
            addCriterion("product_status >=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThan(String value) {
            addCriterion("product_status <", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThanOrEqualTo(String value) {
            addCriterion("product_status <=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLike(String value) {
            addCriterion("product_status like", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotLike(String value) {
            addCriterion("product_status not like", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusIn(List<String> values) {
            addCriterion("product_status in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotIn(List<String> values) {
            addCriterion("product_status not in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusBetween(String value1, String value2) {
            addCriterion("product_status between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotBetween(String value1, String value2) {
            addCriterion("product_status not between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateIsNull() {
            addCriterion("product_status_date is null");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateIsNotNull() {
            addCriterion("product_status_date is not null");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateEqualTo(Date value) {
            addCriterion("product_status_date =", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateNotEqualTo(Date value) {
            addCriterion("product_status_date <>", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateGreaterThan(Date value) {
            addCriterion("product_status_date >", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("product_status_date >=", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateLessThan(Date value) {
            addCriterion("product_status_date <", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("product_status_date <=", value, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateIn(List<Date> values) {
            addCriterion("product_status_date in", values, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateNotIn(List<Date> values) {
            addCriterion("product_status_date not in", values, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateBetween(Date value1, Date value2) {
            addCriterion("product_status_date between", value1, value2, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andProductStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("product_status_date not between", value1, value2, "productStatusDate");
            return (Criteria) this;
        }

        public Criteria andIsGiveIsNull() {
            addCriterion("is_give is null");
            return (Criteria) this;
        }

        public Criteria andIsGiveIsNotNull() {
            addCriterion("is_give is not null");
            return (Criteria) this;
        }

        public Criteria andIsGiveEqualTo(String value) {
            addCriterion("is_give =", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveNotEqualTo(String value) {
            addCriterion("is_give <>", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveGreaterThan(String value) {
            addCriterion("is_give >", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveGreaterThanOrEqualTo(String value) {
            addCriterion("is_give >=", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveLessThan(String value) {
            addCriterion("is_give <", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveLessThanOrEqualTo(String value) {
            addCriterion("is_give <=", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveLike(String value) {
            addCriterion("is_give like", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveNotLike(String value) {
            addCriterion("is_give not like", value, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveIn(List<String> values) {
            addCriterion("is_give in", values, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveNotIn(List<String> values) {
            addCriterion("is_give not in", values, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveBetween(String value1, String value2) {
            addCriterion("is_give between", value1, value2, "isGive");
            return (Criteria) this;
        }

        public Criteria andIsGiveNotBetween(String value1, String value2) {
            addCriterion("is_give not between", value1, value2, "isGive");
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

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
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

        public Criteria andOriginalPriceEqualTo(BigDecimal value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(BigDecimal value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(BigDecimal value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(BigDecimal value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<BigDecimal> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<BigDecimal> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyIsNull() {
            addCriterion("is_svip_buy is null");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyIsNotNull() {
            addCriterion("is_svip_buy is not null");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyEqualTo(String value) {
            addCriterion("is_svip_buy =", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyNotEqualTo(String value) {
            addCriterion("is_svip_buy <>", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyGreaterThan(String value) {
            addCriterion("is_svip_buy >", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyGreaterThanOrEqualTo(String value) {
            addCriterion("is_svip_buy >=", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyLessThan(String value) {
            addCriterion("is_svip_buy <", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyLessThanOrEqualTo(String value) {
            addCriterion("is_svip_buy <=", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyLike(String value) {
            addCriterion("is_svip_buy like", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyNotLike(String value) {
            addCriterion("is_svip_buy not like", value, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyIn(List<String> values) {
            addCriterion("is_svip_buy in", values, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyNotIn(List<String> values) {
            addCriterion("is_svip_buy not in", values, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyBetween(String value1, String value2) {
            addCriterion("is_svip_buy between", value1, value2, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andIsSvipBuyNotBetween(String value1, String value2) {
            addCriterion("is_svip_buy not between", value1, value2, "isSvipBuy");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIsNull() {
            addCriterion("svip_discount is null");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIsNotNull() {
            addCriterion("svip_discount is not null");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountEqualTo(BigDecimal value) {
            addCriterion("svip_discount =", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotEqualTo(BigDecimal value) {
            addCriterion("svip_discount <>", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountGreaterThan(BigDecimal value) {
            addCriterion("svip_discount >", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("svip_discount >=", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountLessThan(BigDecimal value) {
            addCriterion("svip_discount <", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("svip_discount <=", value, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountIn(List<BigDecimal> values) {
            addCriterion("svip_discount in", values, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotIn(List<BigDecimal> values) {
            addCriterion("svip_discount not in", values, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("svip_discount between", value1, value2, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSvipDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("svip_discount not between", value1, value2, "svipDiscount");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIsNull() {
            addCriterion("selling_price is null");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIsNotNull() {
            addCriterion("selling_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellingPriceEqualTo(BigDecimal value) {
            addCriterion("selling_price =", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotEqualTo(BigDecimal value) {
            addCriterion("selling_price <>", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceGreaterThan(BigDecimal value) {
            addCriterion("selling_price >", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("selling_price >=", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceLessThan(BigDecimal value) {
            addCriterion("selling_price <", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("selling_price <=", value, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceIn(List<BigDecimal> values) {
            addCriterion("selling_price in", values, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotIn(List<BigDecimal> values) {
            addCriterion("selling_price not in", values, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("selling_price between", value1, value2, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andSellingPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("selling_price not between", value1, value2, "sellingPrice");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdIsNull() {
            addCriterion("dtl_express_id is null");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdIsNotNull() {
            addCriterion("dtl_express_id is not null");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdEqualTo(Integer value) {
            addCriterion("dtl_express_id =", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdNotEqualTo(Integer value) {
            addCriterion("dtl_express_id <>", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdGreaterThan(Integer value) {
            addCriterion("dtl_express_id >", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dtl_express_id >=", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdLessThan(Integer value) {
            addCriterion("dtl_express_id <", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("dtl_express_id <=", value, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdIn(List<Integer> values) {
            addCriterion("dtl_express_id in", values, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdNotIn(List<Integer> values) {
            addCriterion("dtl_express_id not in", values, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("dtl_express_id between", value1, value2, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dtl_express_id not between", value1, value2, "dtlExpressId");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoIsNull() {
            addCriterion("dtl_express_no is null");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoIsNotNull() {
            addCriterion("dtl_express_no is not null");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoEqualTo(String value) {
            addCriterion("dtl_express_no =", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoNotEqualTo(String value) {
            addCriterion("dtl_express_no <>", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoGreaterThan(String value) {
            addCriterion("dtl_express_no >", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("dtl_express_no >=", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoLessThan(String value) {
            addCriterion("dtl_express_no <", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoLessThanOrEqualTo(String value) {
            addCriterion("dtl_express_no <=", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoLike(String value) {
            addCriterion("dtl_express_no like", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoNotLike(String value) {
            addCriterion("dtl_express_no not like", value, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoIn(List<String> values) {
            addCriterion("dtl_express_no in", values, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoNotIn(List<String> values) {
            addCriterion("dtl_express_no not in", values, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoBetween(String value1, String value2) {
            addCriterion("dtl_express_no between", value1, value2, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDtlExpressNoNotBetween(String value1, String value2) {
            addCriterion("dtl_express_no not between", value1, value2, "dtlExpressNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusIsNull() {
            addCriterion("delivery_status is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusIsNotNull() {
            addCriterion("delivery_status is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusEqualTo(String value) {
            addCriterion("delivery_status =", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusNotEqualTo(String value) {
            addCriterion("delivery_status <>", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusGreaterThan(String value) {
            addCriterion("delivery_status >", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_status >=", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusLessThan(String value) {
            addCriterion("delivery_status <", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusLessThanOrEqualTo(String value) {
            addCriterion("delivery_status <=", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusLike(String value) {
            addCriterion("delivery_status like", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusNotLike(String value) {
            addCriterion("delivery_status not like", value, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusIn(List<String> values) {
            addCriterion("delivery_status in", values, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusNotIn(List<String> values) {
            addCriterion("delivery_status not in", values, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusBetween(String value1, String value2) {
            addCriterion("delivery_status between", value1, value2, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryStatusNotBetween(String value1, String value2) {
            addCriterion("delivery_status not between", value1, value2, "deliveryStatus");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNull() {
            addCriterion("delivery_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNotNull() {
            addCriterion("delivery_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateEqualTo(Date value) {
            addCriterion("delivery_date =", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotEqualTo(Date value) {
            addCriterion("delivery_date <>", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThan(Date value) {
            addCriterion("delivery_date >", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_date >=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThan(Date value) {
            addCriterion("delivery_date <", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterion("delivery_date <=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIn(List<Date> values) {
            addCriterion("delivery_date in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotIn(List<Date> values) {
            addCriterion("delivery_date not in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateBetween(Date value1, Date value2) {
            addCriterion("delivery_date between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterion("delivery_date not between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockIsNull() {
            addCriterion("marked_out_of_stock is null");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockIsNotNull() {
            addCriterion("marked_out_of_stock is not null");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockEqualTo(String value) {
            addCriterion("marked_out_of_stock =", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockNotEqualTo(String value) {
            addCriterion("marked_out_of_stock <>", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockGreaterThan(String value) {
            addCriterion("marked_out_of_stock >", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockGreaterThanOrEqualTo(String value) {
            addCriterion("marked_out_of_stock >=", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockLessThan(String value) {
            addCriterion("marked_out_of_stock <", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockLessThanOrEqualTo(String value) {
            addCriterion("marked_out_of_stock <=", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockLike(String value) {
            addCriterion("marked_out_of_stock like", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockNotLike(String value) {
            addCriterion("marked_out_of_stock not like", value, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockIn(List<String> values) {
            addCriterion("marked_out_of_stock in", values, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockNotIn(List<String> values) {
            addCriterion("marked_out_of_stock not in", values, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockBetween(String value1, String value2) {
            addCriterion("marked_out_of_stock between", value1, value2, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andMarkedOutOfStockNotBetween(String value1, String value2) {
            addCriterion("marked_out_of_stock not between", value1, value2, "markedOutOfStock");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdIsNull() {
            addCriterion("distribution_member_id is null");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdIsNotNull() {
            addCriterion("distribution_member_id is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdEqualTo(Integer value) {
            addCriterion("distribution_member_id =", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdNotEqualTo(Integer value) {
            addCriterion("distribution_member_id <>", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdGreaterThan(Integer value) {
            addCriterion("distribution_member_id >", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("distribution_member_id >=", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdLessThan(Integer value) {
            addCriterion("distribution_member_id <", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("distribution_member_id <=", value, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdIn(List<Integer> values) {
            addCriterion("distribution_member_id in", values, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdNotIn(List<Integer> values) {
            addCriterion("distribution_member_id not in", values, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("distribution_member_id between", value1, value2, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("distribution_member_id not between", value1, value2, "distributionMemberId");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNull() {
            addCriterion("distribution_amount is null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIsNotNull() {
            addCriterion("distribution_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountEqualTo(BigDecimal value) {
            addCriterion("distribution_amount =", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <>", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThan(BigDecimal value) {
            addCriterion("distribution_amount >", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount >=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThan(BigDecimal value) {
            addCriterion("distribution_amount <", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("distribution_amount <=", value, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountIn(List<BigDecimal> values) {
            addCriterion("distribution_amount in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotIn(List<BigDecimal> values) {
            addCriterion("distribution_amount not in", values, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("distribution_amount not between", value1, value2, "distributionAmount");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusIsNull() {
            addCriterion("distribution_settle_status is null");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusIsNotNull() {
            addCriterion("distribution_settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusEqualTo(String value) {
            addCriterion("distribution_settle_status =", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusNotEqualTo(String value) {
            addCriterion("distribution_settle_status <>", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusGreaterThan(String value) {
            addCriterion("distribution_settle_status >", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("distribution_settle_status >=", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusLessThan(String value) {
            addCriterion("distribution_settle_status <", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusLessThanOrEqualTo(String value) {
            addCriterion("distribution_settle_status <=", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusLike(String value) {
            addCriterion("distribution_settle_status like", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusNotLike(String value) {
            addCriterion("distribution_settle_status not like", value, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusIn(List<String> values) {
            addCriterion("distribution_settle_status in", values, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusNotIn(List<String> values) {
            addCriterion("distribution_settle_status not in", values, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusBetween(String value1, String value2) {
            addCriterion("distribution_settle_status between", value1, value2, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleStatusNotBetween(String value1, String value2) {
            addCriterion("distribution_settle_status not between", value1, value2, "distributionSettleStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateIsNull() {
            addCriterion("distribution_settle_date is null");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateIsNotNull() {
            addCriterion("distribution_settle_date is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateEqualTo(Date value) {
            addCriterion("distribution_settle_date =", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateNotEqualTo(Date value) {
            addCriterion("distribution_settle_date <>", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateGreaterThan(Date value) {
            addCriterion("distribution_settle_date >", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("distribution_settle_date >=", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateLessThan(Date value) {
            addCriterion("distribution_settle_date <", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateLessThanOrEqualTo(Date value) {
            addCriterion("distribution_settle_date <=", value, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateIn(List<Date> values) {
            addCriterion("distribution_settle_date in", values, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateNotIn(List<Date> values) {
            addCriterion("distribution_settle_date not in", values, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateBetween(Date value1, Date value2) {
            addCriterion("distribution_settle_date between", value1, value2, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andDistributionSettleDateNotBetween(Date value1, Date value2) {
            addCriterion("distribution_settle_date not between", value1, value2, "distributionSettleDate");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("merchant_code is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("merchant_code is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("merchant_code =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("merchant_code <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("merchant_code >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_code >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("merchant_code <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("merchant_code <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("merchant_code like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("merchant_code not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("merchant_code in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("merchant_code not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("merchant_code between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("merchant_code not between", value1, value2, "merchantCode");
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