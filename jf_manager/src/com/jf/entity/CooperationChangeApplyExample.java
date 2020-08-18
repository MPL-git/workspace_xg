package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CooperationChangeApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public CooperationChangeApplyExample() {
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

        public Criteria andShopTypeIsNull() {
            addCriterion("shop_type is null");
            return (Criteria) this;
        }

        public Criteria andShopTypeIsNotNull() {
            addCriterion("shop_type is not null");
            return (Criteria) this;
        }

        public Criteria andShopTypeEqualTo(String value) {
            addCriterion("shop_type =", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotEqualTo(String value) {
            addCriterion("shop_type <>", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThan(String value) {
            addCriterion("shop_type >", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_type >=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThan(String value) {
            addCriterion("shop_type <", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLessThanOrEqualTo(String value) {
            addCriterion("shop_type <=", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeLike(String value) {
            addCriterion("shop_type like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotLike(String value) {
            addCriterion("shop_type not like", value, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeIn(List<String> values) {
            addCriterion("shop_type in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotIn(List<String> values) {
            addCriterion("shop_type not in", values, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeBetween(String value1, String value2) {
            addCriterion("shop_type between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andShopTypeNotBetween(String value1, String value2) {
            addCriterion("shop_type not between", value1, value2, "shopType");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIsNull() {
            addCriterion("business_firms is null");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIsNotNull() {
            addCriterion("business_firms is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsEqualTo(String value) {
            addCriterion("business_firms =", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotEqualTo(String value) {
            addCriterion("business_firms <>", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsGreaterThan(String value) {
            addCriterion("business_firms >", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsGreaterThanOrEqualTo(String value) {
            addCriterion("business_firms >=", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLessThan(String value) {
            addCriterion("business_firms <", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLessThanOrEqualTo(String value) {
            addCriterion("business_firms <=", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsLike(String value) {
            addCriterion("business_firms like", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotLike(String value) {
            addCriterion("business_firms not like", value, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsIn(List<String> values) {
            addCriterion("business_firms in", values, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotIn(List<String> values) {
            addCriterion("business_firms not in", values, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsBetween(String value1, String value2) {
            addCriterion("business_firms between", value1, value2, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBusinessFirmsNotBetween(String value1, String value2) {
            addCriterion("business_firms not between", value1, value2, "businessFirms");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIsNull() {
            addCriterion("pre_product_type is null");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIsNotNull() {
            addCriterion("pre_product_type is not null");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeEqualTo(String value) {
            addCriterion("pre_product_type =", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeNotEqualTo(String value) {
            addCriterion("pre_product_type <>", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeGreaterThan(String value) {
            addCriterion("pre_product_type >", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pre_product_type >=", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeLessThan(String value) {
            addCriterion("pre_product_type <", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeLessThanOrEqualTo(String value) {
            addCriterion("pre_product_type <=", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeLike(String value) {
            addCriterion("pre_product_type like", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeNotLike(String value) {
            addCriterion("pre_product_type not like", value, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIn(List<String> values) {
            addCriterion("pre_product_type in", values, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeNotIn(List<String> values) {
            addCriterion("pre_product_type not in", values, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeBetween(String value1, String value2) {
            addCriterion("pre_product_type between", value1, value2, "preProductType");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeNotBetween(String value1, String value2) {
            addCriterion("pre_product_type not between", value1, value2, "preProductType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("product_type like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("product_type not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andPreShopNameIsNull() {
            addCriterion("pre_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andPreShopNameIsNotNull() {
            addCriterion("pre_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andPreShopNameEqualTo(String value) {
            addCriterion("pre_shop_name =", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameNotEqualTo(String value) {
            addCriterion("pre_shop_name <>", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameGreaterThan(String value) {
            addCriterion("pre_shop_name >", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("pre_shop_name >=", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameLessThan(String value) {
            addCriterion("pre_shop_name <", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameLessThanOrEqualTo(String value) {
            addCriterion("pre_shop_name <=", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameLike(String value) {
            addCriterion("pre_shop_name like", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameNotLike(String value) {
            addCriterion("pre_shop_name not like", value, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameIn(List<String> values) {
            addCriterion("pre_shop_name in", values, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameNotIn(List<String> values) {
            addCriterion("pre_shop_name not in", values, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameBetween(String value1, String value2) {
            addCriterion("pre_shop_name between", value1, value2, "preShopName");
            return (Criteria) this;
        }

        public Criteria andPreShopNameNotBetween(String value1, String value2) {
            addCriterion("pre_shop_name not between", value1, value2, "preShopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdIsNull() {
            addCriterion("pre_product_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdIsNotNull() {
            addCriterion("pre_product_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdEqualTo(Integer value) {
            addCriterion("pre_product_type_id =", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdNotEqualTo(Integer value) {
            addCriterion("pre_product_type_id <>", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdGreaterThan(Integer value) {
            addCriterion("pre_product_type_id >", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_product_type_id >=", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdLessThan(Integer value) {
            addCriterion("pre_product_type_id <", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("pre_product_type_id <=", value, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdIn(List<Integer> values) {
            addCriterion("pre_product_type_id in", values, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdNotIn(List<Integer> values) {
            addCriterion("pre_product_type_id not in", values, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("pre_product_type_id between", value1, value2, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andPreProductTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_product_type_id not between", value1, value2, "preProductTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNull() {
            addCriterion("product_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIsNotNull() {
            addCriterion("product_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdEqualTo(Integer value) {
            addCriterion("product_type_id =", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotEqualTo(Integer value) {
            addCriterion("product_type_id <>", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThan(Integer value) {
            addCriterion("product_type_id >", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type_id >=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThan(Integer value) {
            addCriterion("product_type_id <", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type_id <=", value, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdIn(List<Integer> values) {
            addCriterion("product_type_id in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotIn(List<Integer> values) {
            addCriterion("product_type_id not in", values, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andProductTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type_id not between", value1, value2, "productTypeId");
            return (Criteria) this;
        }

        public Criteria andPreDepositIsNull() {
            addCriterion("pre_deposit is null");
            return (Criteria) this;
        }

        public Criteria andPreDepositIsNotNull() {
            addCriterion("pre_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andPreDepositEqualTo(BigDecimal value) {
            addCriterion("pre_deposit =", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositNotEqualTo(BigDecimal value) {
            addCriterion("pre_deposit <>", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositGreaterThan(BigDecimal value) {
            addCriterion("pre_deposit >", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_deposit >=", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositLessThan(BigDecimal value) {
            addCriterion("pre_deposit <", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_deposit <=", value, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositIn(List<BigDecimal> values) {
            addCriterion("pre_deposit in", values, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositNotIn(List<BigDecimal> values) {
            addCriterion("pre_deposit not in", values, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_deposit between", value1, value2, "preDeposit");
            return (Criteria) this;
        }

        public Criteria andPreDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_deposit not between", value1, value2, "preDeposit");
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

        public Criteria andZsAuditStatusIsNull() {
            addCriterion("zs_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusIsNotNull() {
            addCriterion("zs_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusEqualTo(String value) {
            addCriterion("zs_audit_status =", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotEqualTo(String value) {
            addCriterion("zs_audit_status <>", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusGreaterThan(String value) {
            addCriterion("zs_audit_status >", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("zs_audit_status >=", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLessThan(String value) {
            addCriterion("zs_audit_status <", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("zs_audit_status <=", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusLike(String value) {
            addCriterion("zs_audit_status like", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotLike(String value) {
            addCriterion("zs_audit_status not like", value, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusIn(List<String> values) {
            addCriterion("zs_audit_status in", values, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotIn(List<String> values) {
            addCriterion("zs_audit_status not in", values, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusBetween(String value1, String value2) {
            addCriterion("zs_audit_status between", value1, value2, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditStatusNotBetween(String value1, String value2) {
            addCriterion("zs_audit_status not between", value1, value2, "zsAuditStatus");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksIsNull() {
            addCriterion("zs_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksIsNotNull() {
            addCriterion("zs_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksEqualTo(String value) {
            addCriterion("zs_audit_remarks =", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotEqualTo(String value) {
            addCriterion("zs_audit_remarks <>", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksGreaterThan(String value) {
            addCriterion("zs_audit_remarks >", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("zs_audit_remarks >=", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLessThan(String value) {
            addCriterion("zs_audit_remarks <", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("zs_audit_remarks <=", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksLike(String value) {
            addCriterion("zs_audit_remarks like", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotLike(String value) {
            addCriterion("zs_audit_remarks not like", value, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksIn(List<String> values) {
            addCriterion("zs_audit_remarks in", values, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotIn(List<String> values) {
            addCriterion("zs_audit_remarks not in", values, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksBetween(String value1, String value2) {
            addCriterion("zs_audit_remarks between", value1, value2, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("zs_audit_remarks not between", value1, value2, "zsAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIsNull() {
            addCriterion("zs_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIsNotNull() {
            addCriterion("zs_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditByEqualTo(Integer value) {
            addCriterion("zs_audit_by =", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotEqualTo(Integer value) {
            addCriterion("zs_audit_by <>", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByGreaterThan(Integer value) {
            addCriterion("zs_audit_by >", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("zs_audit_by >=", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByLessThan(Integer value) {
            addCriterion("zs_audit_by <", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("zs_audit_by <=", value, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByIn(List<Integer> values) {
            addCriterion("zs_audit_by in", values, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotIn(List<Integer> values) {
            addCriterion("zs_audit_by not in", values, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByBetween(Integer value1, Integer value2) {
            addCriterion("zs_audit_by between", value1, value2, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("zs_audit_by not between", value1, value2, "zsAuditBy");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateIsNull() {
            addCriterion("zs_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateIsNotNull() {
            addCriterion("zs_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateEqualTo(Date value) {
            addCriterion("zs_audit_date =", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotEqualTo(Date value) {
            addCriterion("zs_audit_date <>", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateGreaterThan(Date value) {
            addCriterion("zs_audit_date >", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("zs_audit_date >=", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateLessThan(Date value) {
            addCriterion("zs_audit_date <", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("zs_audit_date <=", value, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateIn(List<Date> values) {
            addCriterion("zs_audit_date in", values, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotIn(List<Date> values) {
            addCriterion("zs_audit_date not in", values, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateBetween(Date value1, Date value2) {
            addCriterion("zs_audit_date between", value1, value2, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andZsAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("zs_audit_date not between", value1, value2, "zsAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusIsNull() {
            addCriterion("fw_audit_status is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusIsNotNull() {
            addCriterion("fw_audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusEqualTo(String value) {
            addCriterion("fw_audit_status =", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusNotEqualTo(String value) {
            addCriterion("fw_audit_status <>", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusGreaterThan(String value) {
            addCriterion("fw_audit_status >", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("fw_audit_status >=", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusLessThan(String value) {
            addCriterion("fw_audit_status <", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("fw_audit_status <=", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusLike(String value) {
            addCriterion("fw_audit_status like", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusNotLike(String value) {
            addCriterion("fw_audit_status not like", value, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusIn(List<String> values) {
            addCriterion("fw_audit_status in", values, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusNotIn(List<String> values) {
            addCriterion("fw_audit_status not in", values, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusBetween(String value1, String value2) {
            addCriterion("fw_audit_status between", value1, value2, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditStatusNotBetween(String value1, String value2) {
            addCriterion("fw_audit_status not between", value1, value2, "fwAuditStatus");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksIsNull() {
            addCriterion("fw_audit_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksIsNotNull() {
            addCriterion("fw_audit_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksEqualTo(String value) {
            addCriterion("fw_audit_remarks =", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksNotEqualTo(String value) {
            addCriterion("fw_audit_remarks <>", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksGreaterThan(String value) {
            addCriterion("fw_audit_remarks >", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("fw_audit_remarks >=", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksLessThan(String value) {
            addCriterion("fw_audit_remarks <", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksLessThanOrEqualTo(String value) {
            addCriterion("fw_audit_remarks <=", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksLike(String value) {
            addCriterion("fw_audit_remarks like", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksNotLike(String value) {
            addCriterion("fw_audit_remarks not like", value, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksIn(List<String> values) {
            addCriterion("fw_audit_remarks in", values, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksNotIn(List<String> values) {
            addCriterion("fw_audit_remarks not in", values, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksBetween(String value1, String value2) {
            addCriterion("fw_audit_remarks between", value1, value2, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditRemarksNotBetween(String value1, String value2) {
            addCriterion("fw_audit_remarks not between", value1, value2, "fwAuditRemarks");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIsNull() {
            addCriterion("fw_audit_by is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIsNotNull() {
            addCriterion("fw_audit_by is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditByEqualTo(Integer value) {
            addCriterion("fw_audit_by =", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotEqualTo(Integer value) {
            addCriterion("fw_audit_by <>", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByGreaterThan(Integer value) {
            addCriterion("fw_audit_by >", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByGreaterThanOrEqualTo(Integer value) {
            addCriterion("fw_audit_by >=", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByLessThan(Integer value) {
            addCriterion("fw_audit_by <", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByLessThanOrEqualTo(Integer value) {
            addCriterion("fw_audit_by <=", value, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByIn(List<Integer> values) {
            addCriterion("fw_audit_by in", values, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotIn(List<Integer> values) {
            addCriterion("fw_audit_by not in", values, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByBetween(Integer value1, Integer value2) {
            addCriterion("fw_audit_by between", value1, value2, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditByNotBetween(Integer value1, Integer value2) {
            addCriterion("fw_audit_by not between", value1, value2, "fwAuditBy");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIsNull() {
            addCriterion("fw_audit_date is null");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIsNotNull() {
            addCriterion("fw_audit_date is not null");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateEqualTo(Date value) {
            addCriterion("fw_audit_date =", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotEqualTo(Date value) {
            addCriterion("fw_audit_date <>", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateGreaterThan(Date value) {
            addCriterion("fw_audit_date >", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("fw_audit_date >=", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateLessThan(Date value) {
            addCriterion("fw_audit_date <", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateLessThanOrEqualTo(Date value) {
            addCriterion("fw_audit_date <=", value, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateIn(List<Date> values) {
            addCriterion("fw_audit_date in", values, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotIn(List<Date> values) {
            addCriterion("fw_audit_date not in", values, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateBetween(Date value1, Date value2) {
            addCriterion("fw_audit_date between", value1, value2, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFwAuditDateNotBetween(Date value1, Date value2) {
            addCriterion("fw_audit_date not between", value1, value2, "fwAuditDate");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIsNull() {
            addCriterion("upload_status is null");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIsNotNull() {
            addCriterion("upload_status is not null");
            return (Criteria) this;
        }

        public Criteria andUploadStatusEqualTo(String value) {
            addCriterion("upload_status =", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotEqualTo(String value) {
            addCriterion("upload_status <>", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusGreaterThan(String value) {
            addCriterion("upload_status >", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusGreaterThanOrEqualTo(String value) {
            addCriterion("upload_status >=", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusLessThan(String value) {
            addCriterion("upload_status <", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusLessThanOrEqualTo(String value) {
            addCriterion("upload_status <=", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusLike(String value) {
            addCriterion("upload_status like", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotLike(String value) {
            addCriterion("upload_status not like", value, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusIn(List<String> values) {
            addCriterion("upload_status in", values, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotIn(List<String> values) {
            addCriterion("upload_status not in", values, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusBetween(String value1, String value2) {
            addCriterion("upload_status between", value1, value2, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andUploadStatusNotBetween(String value1, String value2) {
            addCriterion("upload_status not between", value1, value2, "uploadStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("send_status is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(String value) {
            addCriterion("send_status =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(String value) {
            addCriterion("send_status <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(String value) {
            addCriterion("send_status >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(String value) {
            addCriterion("send_status >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(String value) {
            addCriterion("send_status <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(String value) {
            addCriterion("send_status <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLike(String value) {
            addCriterion("send_status like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotLike(String value) {
            addCriterion("send_status not like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<String> values) {
            addCriterion("send_status in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<String> values) {
            addCriterion("send_status not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(String value1, String value2) {
            addCriterion("send_status between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(String value1, String value2) {
            addCriterion("send_status not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Integer value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Integer value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Integer value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Integer value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Integer> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Integer> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("express_no is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("express_no is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("express_no =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("express_no <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("express_no >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("express_no >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("express_no <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("express_no <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("express_no like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("express_no not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("express_no in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("express_no not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("express_no between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("express_no not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusIsNull() {
            addCriterion("archive_status is null");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusIsNotNull() {
            addCriterion("archive_status is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusEqualTo(String value) {
            addCriterion("archive_status =", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotEqualTo(String value) {
            addCriterion("archive_status <>", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusGreaterThan(String value) {
            addCriterion("archive_status >", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("archive_status >=", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLessThan(String value) {
            addCriterion("archive_status <", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLessThanOrEqualTo(String value) {
            addCriterion("archive_status <=", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusLike(String value) {
            addCriterion("archive_status like", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotLike(String value) {
            addCriterion("archive_status not like", value, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusIn(List<String> values) {
            addCriterion("archive_status in", values, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotIn(List<String> values) {
            addCriterion("archive_status not in", values, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusBetween(String value1, String value2) {
            addCriterion("archive_status between", value1, value2, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveStatusNotBetween(String value1, String value2) {
            addCriterion("archive_status not between", value1, value2, "archiveStatus");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksIsNull() {
            addCriterion("archive_remarks is null");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksIsNotNull() {
            addCriterion("archive_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksEqualTo(String value) {
            addCriterion("archive_remarks =", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksNotEqualTo(String value) {
            addCriterion("archive_remarks <>", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksGreaterThan(String value) {
            addCriterion("archive_remarks >", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("archive_remarks >=", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksLessThan(String value) {
            addCriterion("archive_remarks <", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksLessThanOrEqualTo(String value) {
            addCriterion("archive_remarks <=", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksLike(String value) {
            addCriterion("archive_remarks like", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksNotLike(String value) {
            addCriterion("archive_remarks not like", value, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksIn(List<String> values) {
            addCriterion("archive_remarks in", values, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksNotIn(List<String> values) {
            addCriterion("archive_remarks not in", values, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksBetween(String value1, String value2) {
            addCriterion("archive_remarks between", value1, value2, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveRemarksNotBetween(String value1, String value2) {
            addCriterion("archive_remarks not between", value1, value2, "archiveRemarks");
            return (Criteria) this;
        }

        public Criteria andArchiveByIsNull() {
            addCriterion("archive_by is null");
            return (Criteria) this;
        }

        public Criteria andArchiveByIsNotNull() {
            addCriterion("archive_by is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveByEqualTo(Integer value) {
            addCriterion("archive_by =", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotEqualTo(Integer value) {
            addCriterion("archive_by <>", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByGreaterThan(Integer value) {
            addCriterion("archive_by >", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByGreaterThanOrEqualTo(Integer value) {
            addCriterion("archive_by >=", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByLessThan(Integer value) {
            addCriterion("archive_by <", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByLessThanOrEqualTo(Integer value) {
            addCriterion("archive_by <=", value, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByIn(List<Integer> values) {
            addCriterion("archive_by in", values, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotIn(List<Integer> values) {
            addCriterion("archive_by not in", values, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByBetween(Integer value1, Integer value2) {
            addCriterion("archive_by between", value1, value2, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveByNotBetween(Integer value1, Integer value2) {
            addCriterion("archive_by not between", value1, value2, "archiveBy");
            return (Criteria) this;
        }

        public Criteria andArchiveDateIsNull() {
            addCriterion("archive_date is null");
            return (Criteria) this;
        }

        public Criteria andArchiveDateIsNotNull() {
            addCriterion("archive_date is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveDateEqualTo(Date value) {
            addCriterion("archive_date =", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotEqualTo(Date value) {
            addCriterion("archive_date <>", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateGreaterThan(Date value) {
            addCriterion("archive_date >", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("archive_date >=", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateLessThan(Date value) {
            addCriterion("archive_date <", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateLessThanOrEqualTo(Date value) {
            addCriterion("archive_date <=", value, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateIn(List<Date> values) {
            addCriterion("archive_date in", values, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotIn(List<Date> values) {
            addCriterion("archive_date not in", values, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateBetween(Date value1, Date value2) {
            addCriterion("archive_date between", value1, value2, "archiveDate");
            return (Criteria) this;
        }

        public Criteria andArchiveDateNotBetween(Date value1, Date value2) {
            addCriterion("archive_date not between", value1, value2, "archiveDate");
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