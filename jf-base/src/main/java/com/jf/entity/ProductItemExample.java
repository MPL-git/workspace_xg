package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ProductItemExample() {
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

        public Criteria andPropValIdIsNull() {
            addCriterion("prop_val_id is null");
            return (Criteria) this;
        }

        public Criteria andPropValIdIsNotNull() {
            addCriterion("prop_val_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropValIdEqualTo(String value) {
            addCriterion("prop_val_id =", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdNotEqualTo(String value) {
            addCriterion("prop_val_id <>", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdGreaterThan(String value) {
            addCriterion("prop_val_id >", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdGreaterThanOrEqualTo(String value) {
            addCriterion("prop_val_id >=", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdLessThan(String value) {
            addCriterion("prop_val_id <", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdLessThanOrEqualTo(String value) {
            addCriterion("prop_val_id <=", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdLike(String value) {
            addCriterion("prop_val_id like", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdNotLike(String value) {
            addCriterion("prop_val_id not like", value, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdIn(List<String> values) {
            addCriterion("prop_val_id in", values, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdNotIn(List<String> values) {
            addCriterion("prop_val_id not in", values, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdBetween(String value1, String value2) {
            addCriterion("prop_val_id between", value1, value2, "propValId");
            return (Criteria) this;
        }

        public Criteria andPropValIdNotBetween(String value1, String value2) {
            addCriterion("prop_val_id not between", value1, value2, "propValId");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andLockedAmountIsNull() {
            addCriterion("locked_amount is null");
            return (Criteria) this;
        }

        public Criteria andLockedAmountIsNotNull() {
            addCriterion("locked_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLockedAmountEqualTo(Integer value) {
            addCriterion("locked_amount =", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountNotEqualTo(Integer value) {
            addCriterion("locked_amount <>", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountGreaterThan(Integer value) {
            addCriterion("locked_amount >", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("locked_amount >=", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountLessThan(Integer value) {
            addCriterion("locked_amount <", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountLessThanOrEqualTo(Integer value) {
            addCriterion("locked_amount <=", value, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountIn(List<Integer> values) {
            addCriterion("locked_amount in", values, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountNotIn(List<Integer> values) {
            addCriterion("locked_amount not in", values, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountBetween(Integer value1, Integer value2) {
            addCriterion("locked_amount between", value1, value2, "lockedAmount");
            return (Criteria) this;
        }

        public Criteria andLockedAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("locked_amount not between", value1, value2, "lockedAmount");
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

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(BigDecimal value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(BigDecimal value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(BigDecimal value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(BigDecimal value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<BigDecimal> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<BigDecimal> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNull() {
            addCriterion("manage_self_freight is null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIsNotNull() {
            addCriterion("manage_self_freight is not null");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight =", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <>", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThan(BigDecimal value) {
            addCriterion("manage_self_freight >", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight >=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThan(BigDecimal value) {
            addCriterion("manage_self_freight <", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("manage_self_freight <=", value, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotIn(List<BigDecimal> values) {
            addCriterion("manage_self_freight not in", values, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight between", value1, value2, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andManageSelfFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manage_self_freight not between", value1, value2, "manageSelfFreight");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
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