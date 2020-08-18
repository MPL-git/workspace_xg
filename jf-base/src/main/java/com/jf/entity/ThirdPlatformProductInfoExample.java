package com.jf.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThirdPlatformProductInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ThirdPlatformProductInfoExample() {
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

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNull() {
            addCriterion("ref_id is null");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNotNull() {
            addCriterion("ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefIdEqualTo(String value) {
            addCriterion("ref_id =", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotEqualTo(String value) {
            addCriterion("ref_id <>", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThan(String value) {
            addCriterion("ref_id >", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThanOrEqualTo(String value) {
            addCriterion("ref_id >=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThan(String value) {
            addCriterion("ref_id <", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThanOrEqualTo(String value) {
            addCriterion("ref_id <=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLike(String value) {
            addCriterion("ref_id like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotLike(String value) {
            addCriterion("ref_id not like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdIn(List<String> values) {
            addCriterion("ref_id in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotIn(List<String> values) {
            addCriterion("ref_id not in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdBetween(String value1, String value2) {
            addCriterion("ref_id between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotBetween(String value1, String value2) {
            addCriterion("ref_id not between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andIsCouponIsNull() {
            addCriterion("is_coupon is null");
            return (Criteria) this;
        }

        public Criteria andIsCouponIsNotNull() {
            addCriterion("is_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andIsCouponEqualTo(String value) {
            addCriterion("is_coupon =", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponNotEqualTo(String value) {
            addCriterion("is_coupon <>", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponGreaterThan(String value) {
            addCriterion("is_coupon >", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponGreaterThanOrEqualTo(String value) {
            addCriterion("is_coupon >=", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponLessThan(String value) {
            addCriterion("is_coupon <", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponLessThanOrEqualTo(String value) {
            addCriterion("is_coupon <=", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponLike(String value) {
            addCriterion("is_coupon like", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponNotLike(String value) {
            addCriterion("is_coupon not like", value, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponIn(List<String> values) {
            addCriterion("is_coupon in", values, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponNotIn(List<String> values) {
            addCriterion("is_coupon not in", values, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponBetween(String value1, String value2) {
            addCriterion("is_coupon between", value1, value2, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andIsCouponNotBetween(String value1, String value2) {
            addCriterion("is_coupon not between", value1, value2, "isCoupon");
            return (Criteria) this;
        }

        public Criteria andShopTitleIsNull() {
            addCriterion("shop_title is null");
            return (Criteria) this;
        }

        public Criteria andShopTitleIsNotNull() {
            addCriterion("shop_title is not null");
            return (Criteria) this;
        }

        public Criteria andShopTitleEqualTo(String value) {
            addCriterion("shop_title =", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleNotEqualTo(String value) {
            addCriterion("shop_title <>", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleGreaterThan(String value) {
            addCriterion("shop_title >", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleGreaterThanOrEqualTo(String value) {
            addCriterion("shop_title >=", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleLessThan(String value) {
            addCriterion("shop_title <", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleLessThanOrEqualTo(String value) {
            addCriterion("shop_title <=", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleLike(String value) {
            addCriterion("shop_title like", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleNotLike(String value) {
            addCriterion("shop_title not like", value, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleIn(List<String> values) {
            addCriterion("shop_title in", values, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleNotIn(List<String> values) {
            addCriterion("shop_title not in", values, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleBetween(String value1, String value2) {
            addCriterion("shop_title between", value1, value2, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andShopTitleNotBetween(String value1, String value2) {
            addCriterion("shop_title not between", value1, value2, "shopTitle");
            return (Criteria) this;
        }

        public Criteria andReservePriceIsNull() {
            addCriterion("reserve_price is null");
            return (Criteria) this;
        }

        public Criteria andReservePriceIsNotNull() {
            addCriterion("reserve_price is not null");
            return (Criteria) this;
        }

        public Criteria andReservePriceEqualTo(BigDecimal value) {
            addCriterion("reserve_price =", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotEqualTo(BigDecimal value) {
            addCriterion("reserve_price <>", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceGreaterThan(BigDecimal value) {
            addCriterion("reserve_price >", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("reserve_price >=", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceLessThan(BigDecimal value) {
            addCriterion("reserve_price <", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("reserve_price <=", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceIn(List<BigDecimal> values) {
            addCriterion("reserve_price in", values, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotIn(List<BigDecimal> values) {
            addCriterion("reserve_price not in", values, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reserve_price between", value1, value2, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("reserve_price not between", value1, value2, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceIsNull() {
            addCriterion("zk_final_price is null");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceIsNotNull() {
            addCriterion("zk_final_price is not null");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceEqualTo(BigDecimal value) {
            addCriterion("zk_final_price =", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceNotEqualTo(BigDecimal value) {
            addCriterion("zk_final_price <>", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceGreaterThan(BigDecimal value) {
            addCriterion("zk_final_price >", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("zk_final_price >=", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceLessThan(BigDecimal value) {
            addCriterion("zk_final_price <", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("zk_final_price <=", value, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceIn(List<BigDecimal> values) {
            addCriterion("zk_final_price in", values, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceNotIn(List<BigDecimal> values) {
            addCriterion("zk_final_price not in", values, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zk_final_price between", value1, value2, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andZkFinalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("zk_final_price not between", value1, value2, "zkFinalPrice");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(String value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(String value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(String value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(String value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(String value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(String value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLike(String value) {
            addCriterion("seller_id like", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotLike(String value) {
            addCriterion("seller_id not like", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<String> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<String> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(String value1, String value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(String value1, String value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Integer value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Integer value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Integer value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Integer value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Integer> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Integer> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Integer value1, Integer value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andItemUrlIsNull() {
            addCriterion("item_url is null");
            return (Criteria) this;
        }

        public Criteria andItemUrlIsNotNull() {
            addCriterion("item_url is not null");
            return (Criteria) this;
        }

        public Criteria andItemUrlEqualTo(String value) {
            addCriterion("item_url =", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlNotEqualTo(String value) {
            addCriterion("item_url <>", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlGreaterThan(String value) {
            addCriterion("item_url >", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlGreaterThanOrEqualTo(String value) {
            addCriterion("item_url >=", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlLessThan(String value) {
            addCriterion("item_url <", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlLessThanOrEqualTo(String value) {
            addCriterion("item_url <=", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlLike(String value) {
            addCriterion("item_url like", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlNotLike(String value) {
            addCriterion("item_url not like", value, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlIn(List<String> values) {
            addCriterion("item_url in", values, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlNotIn(List<String> values) {
            addCriterion("item_url not in", values, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlBetween(String value1, String value2) {
            addCriterion("item_url between", value1, value2, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andItemUrlNotBetween(String value1, String value2) {
            addCriterion("item_url not between", value1, value2, "itemUrl");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountIsNull() {
            addCriterion("coupon_total_count is null");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountIsNotNull() {
            addCriterion("coupon_total_count is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountEqualTo(Integer value) {
            addCriterion("coupon_total_count =", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountNotEqualTo(Integer value) {
            addCriterion("coupon_total_count <>", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountGreaterThan(Integer value) {
            addCriterion("coupon_total_count >", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_total_count >=", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountLessThan(Integer value) {
            addCriterion("coupon_total_count <", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_total_count <=", value, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountIn(List<Integer> values) {
            addCriterion("coupon_total_count in", values, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountNotIn(List<Integer> values) {
            addCriterion("coupon_total_count not in", values, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountBetween(Integer value1, Integer value2) {
            addCriterion("coupon_total_count between", value1, value2, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCouponTotalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_total_count not between", value1, value2, "couponTotalCount");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNull() {
            addCriterion("commission_rate is null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNotNull() {
            addCriterion("commission_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateEqualTo(String value) {
            addCriterion("commission_rate =", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotEqualTo(String value) {
            addCriterion("commission_rate <>", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThan(String value) {
            addCriterion("commission_rate >", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThanOrEqualTo(String value) {
            addCriterion("commission_rate >=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThan(String value) {
            addCriterion("commission_rate <", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThanOrEqualTo(String value) {
            addCriterion("commission_rate <=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLike(String value) {
            addCriterion("commission_rate like", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotLike(String value) {
            addCriterion("commission_rate not like", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIn(List<String> values) {
            addCriterion("commission_rate in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotIn(List<String> values) {
            addCriterion("commission_rate not in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateBetween(String value1, String value2) {
            addCriterion("commission_rate between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotBetween(String value1, String value2) {
            addCriterion("commission_rate not between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCouponInfoIsNull() {
            addCriterion("coupon_info is null");
            return (Criteria) this;
        }

        public Criteria andCouponInfoIsNotNull() {
            addCriterion("coupon_info is not null");
            return (Criteria) this;
        }

        public Criteria andCouponInfoEqualTo(String value) {
            addCriterion("coupon_info =", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoNotEqualTo(String value) {
            addCriterion("coupon_info <>", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoGreaterThan(String value) {
            addCriterion("coupon_info >", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_info >=", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoLessThan(String value) {
            addCriterion("coupon_info <", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoLessThanOrEqualTo(String value) {
            addCriterion("coupon_info <=", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoLike(String value) {
            addCriterion("coupon_info like", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoNotLike(String value) {
            addCriterion("coupon_info not like", value, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoIn(List<String> values) {
            addCriterion("coupon_info in", values, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoNotIn(List<String> values) {
            addCriterion("coupon_info not in", values, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoBetween(String value1, String value2) {
            addCriterion("coupon_info between", value1, value2, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCouponInfoNotBetween(String value1, String value2) {
            addCriterion("coupon_info not between", value1, value2, "couponInfo");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Integer value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Integer value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Integer value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Integer value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Integer> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Integer> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Integer value1, Integer value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountIsNull() {
            addCriterion("coupon_remain_count is null");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountIsNotNull() {
            addCriterion("coupon_remain_count is not null");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountEqualTo(Integer value) {
            addCriterion("coupon_remain_count =", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountNotEqualTo(Integer value) {
            addCriterion("coupon_remain_count <>", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountGreaterThan(Integer value) {
            addCriterion("coupon_remain_count >", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_remain_count >=", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountLessThan(Integer value) {
            addCriterion("coupon_remain_count <", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_remain_count <=", value, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountIn(List<Integer> values) {
            addCriterion("coupon_remain_count in", values, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountNotIn(List<Integer> values) {
            addCriterion("coupon_remain_count not in", values, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountBetween(Integer value1, Integer value2) {
            addCriterion("coupon_remain_count between", value1, value2, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRemainCountNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_remain_count not between", value1, value2, "couponRemainCount");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeIsNull() {
            addCriterion("coupon_start_time is null");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeIsNotNull() {
            addCriterion("coupon_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeEqualTo(String value) {
            addCriterion("coupon_start_time =", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeNotEqualTo(String value) {
            addCriterion("coupon_start_time <>", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeGreaterThan(String value) {
            addCriterion("coupon_start_time >", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_start_time >=", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeLessThan(String value) {
            addCriterion("coupon_start_time <", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeLessThanOrEqualTo(String value) {
            addCriterion("coupon_start_time <=", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeLike(String value) {
            addCriterion("coupon_start_time like", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeNotLike(String value) {
            addCriterion("coupon_start_time not like", value, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeIn(List<String> values) {
            addCriterion("coupon_start_time in", values, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeNotIn(List<String> values) {
            addCriterion("coupon_start_time not in", values, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeBetween(String value1, String value2) {
            addCriterion("coupon_start_time between", value1, value2, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponStartTimeNotBetween(String value1, String value2) {
            addCriterion("coupon_start_time not between", value1, value2, "couponStartTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeIsNull() {
            addCriterion("coupon_end_time is null");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeIsNotNull() {
            addCriterion("coupon_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeEqualTo(String value) {
            addCriterion("coupon_end_time =", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeNotEqualTo(String value) {
            addCriterion("coupon_end_time <>", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeGreaterThan(String value) {
            addCriterion("coupon_end_time >", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_end_time >=", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeLessThan(String value) {
            addCriterion("coupon_end_time <", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeLessThanOrEqualTo(String value) {
            addCriterion("coupon_end_time <=", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeLike(String value) {
            addCriterion("coupon_end_time like", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeNotLike(String value) {
            addCriterion("coupon_end_time not like", value, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeIn(List<String> values) {
            addCriterion("coupon_end_time in", values, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeNotIn(List<String> values) {
            addCriterion("coupon_end_time not in", values, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeBetween(String value1, String value2) {
            addCriterion("coupon_end_time between", value1, value2, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponEndTimeNotBetween(String value1, String value2) {
            addCriterion("coupon_end_time not between", value1, value2, "couponEndTime");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlIsNull() {
            addCriterion("coupon_click_url is null");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlIsNotNull() {
            addCriterion("coupon_click_url is not null");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlEqualTo(String value) {
            addCriterion("coupon_click_url =", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlNotEqualTo(String value) {
            addCriterion("coupon_click_url <>", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlGreaterThan(String value) {
            addCriterion("coupon_click_url >", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_click_url >=", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlLessThan(String value) {
            addCriterion("coupon_click_url <", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlLessThanOrEqualTo(String value) {
            addCriterion("coupon_click_url <=", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlLike(String value) {
            addCriterion("coupon_click_url like", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlNotLike(String value) {
            addCriterion("coupon_click_url not like", value, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlIn(List<String> values) {
            addCriterion("coupon_click_url in", values, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlNotIn(List<String> values) {
            addCriterion("coupon_click_url not in", values, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlBetween(String value1, String value2) {
            addCriterion("coupon_click_url between", value1, value2, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andCouponClickUrlNotBetween(String value1, String value2) {
            addCriterion("coupon_click_url not between", value1, value2, "couponClickUrl");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionIsNull() {
            addCriterion("item_description is null");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionIsNotNull() {
            addCriterion("item_description is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionEqualTo(String value) {
            addCriterion("item_description =", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionNotEqualTo(String value) {
            addCriterion("item_description <>", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionGreaterThan(String value) {
            addCriterion("item_description >", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("item_description >=", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionLessThan(String value) {
            addCriterion("item_description <", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionLessThanOrEqualTo(String value) {
            addCriterion("item_description <=", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionLike(String value) {
            addCriterion("item_description like", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionNotLike(String value) {
            addCriterion("item_description not like", value, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionIn(List<String> values) {
            addCriterion("item_description in", values, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionNotIn(List<String> values) {
            addCriterion("item_description not in", values, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionBetween(String value1, String value2) {
            addCriterion("item_description between", value1, value2, "itemDescription");
            return (Criteria) this;
        }

        public Criteria andItemDescriptionNotBetween(String value1, String value2) {
            addCriterion("item_description not between", value1, value2, "itemDescription");
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

        public Criteria andCouponIdEqualTo(String value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(String value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(String value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(String value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLike(String value) {
            addCriterion("coupon_id like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotLike(String value) {
            addCriterion("coupon_id not like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<String> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<String> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(String value1, String value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(String value1, String value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeIsNull() {
            addCriterion("coupon_start_fee is null");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeIsNotNull() {
            addCriterion("coupon_start_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeEqualTo(BigDecimal value) {
            addCriterion("coupon_start_fee =", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeNotEqualTo(BigDecimal value) {
            addCriterion("coupon_start_fee <>", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeGreaterThan(BigDecimal value) {
            addCriterion("coupon_start_fee >", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_start_fee >=", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeLessThan(BigDecimal value) {
            addCriterion("coupon_start_fee <", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_start_fee <=", value, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeIn(List<BigDecimal> values) {
            addCriterion("coupon_start_fee in", values, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeNotIn(List<BigDecimal> values) {
            addCriterion("coupon_start_fee not in", values, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_start_fee between", value1, value2, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponStartFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_start_fee not between", value1, value2, "couponStartFee");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNull() {
            addCriterion("coupon_amount is null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNotNull() {
            addCriterion("coupon_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountEqualTo(BigDecimal value) {
            addCriterion("coupon_amount =", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotEqualTo(BigDecimal value) {
            addCriterion("coupon_amount <>", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThan(BigDecimal value) {
            addCriterion("coupon_amount >", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_amount >=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThan(BigDecimal value) {
            addCriterion("coupon_amount <", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_amount <=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIn(List<BigDecimal> values) {
            addCriterion("coupon_amount in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotIn(List<BigDecimal> values) {
            addCriterion("coupon_amount not in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_amount between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_amount not between", value1, value2, "couponAmount");
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

        public Criteria andProductType1IdIsNull() {
            addCriterion("product_type1_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdIsNotNull() {
            addCriterion("product_type1_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType1IdEqualTo(Integer value) {
            addCriterion("product_type1_id =", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotEqualTo(Integer value) {
            addCriterion("product_type1_id <>", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdGreaterThan(Integer value) {
            addCriterion("product_type1_id >", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type1_id >=", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdLessThan(Integer value) {
            addCriterion("product_type1_id <", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type1_id <=", value, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdIn(List<Integer> values) {
            addCriterion("product_type1_id in", values, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotIn(List<Integer> values) {
            addCriterion("product_type1_id not in", values, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type1_id between", value1, value2, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType1IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type1_id not between", value1, value2, "productType1Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNull() {
            addCriterion("product_type2_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIsNotNull() {
            addCriterion("product_type2_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType2IdEqualTo(Integer value) {
            addCriterion("product_type2_id =", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotEqualTo(Integer value) {
            addCriterion("product_type2_id <>", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThan(Integer value) {
            addCriterion("product_type2_id >", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id >=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThan(Integer value) {
            addCriterion("product_type2_id <", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type2_id <=", value, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdIn(List<Integer> values) {
            addCriterion("product_type2_id in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotIn(List<Integer> values) {
            addCriterion("product_type2_id not in", values, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id between", value1, value2, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type2_id not between", value1, value2, "productType2Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdIsNull() {
            addCriterion("product_type3_id is null");
            return (Criteria) this;
        }

        public Criteria andProductType3IdIsNotNull() {
            addCriterion("product_type3_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductType3IdEqualTo(Integer value) {
            addCriterion("product_type3_id =", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdNotEqualTo(Integer value) {
            addCriterion("product_type3_id <>", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdGreaterThan(Integer value) {
            addCriterion("product_type3_id >", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type3_id >=", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdLessThan(Integer value) {
            addCriterion("product_type3_id <", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdLessThanOrEqualTo(Integer value) {
            addCriterion("product_type3_id <=", value, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdIn(List<Integer> values) {
            addCriterion("product_type3_id in", values, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdNotIn(List<Integer> values) {
            addCriterion("product_type3_id not in", values, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdBetween(Integer value1, Integer value2) {
            addCriterion("product_type3_id between", value1, value2, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andProductType3IdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type3_id not between", value1, value2, "productType3Id");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIsNull() {
            addCriterion("search_fields is null");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIsNotNull() {
            addCriterion("search_fields is not null");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsEqualTo(String value) {
            addCriterion("search_fields =", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotEqualTo(String value) {
            addCriterion("search_fields <>", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsGreaterThan(String value) {
            addCriterion("search_fields >", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsGreaterThanOrEqualTo(String value) {
            addCriterion("search_fields >=", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLessThan(String value) {
            addCriterion("search_fields <", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLessThanOrEqualTo(String value) {
            addCriterion("search_fields <=", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsLike(String value) {
            addCriterion("search_fields like", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotLike(String value) {
            addCriterion("search_fields not like", value, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsIn(List<String> values) {
            addCriterion("search_fields in", values, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotIn(List<String> values) {
            addCriterion("search_fields not in", values, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsBetween(String value1, String value2) {
            addCriterion("search_fields between", value1, value2, "searchFields");
            return (Criteria) this;
        }

        public Criteria andSearchFieldsNotBetween(String value1, String value2) {
            addCriterion("search_fields not between", value1, value2, "searchFields");
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

        public Criteria andAutoUpDateIsNull() {
            addCriterion("auto_up_date is null");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateIsNotNull() {
            addCriterion("auto_up_date is not null");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateEqualTo(Date value) {
            addCriterion("auto_up_date =", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateNotEqualTo(Date value) {
            addCriterion("auto_up_date <>", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateGreaterThan(Date value) {
            addCriterion("auto_up_date >", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("auto_up_date >=", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateLessThan(Date value) {
            addCriterion("auto_up_date <", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateLessThanOrEqualTo(Date value) {
            addCriterion("auto_up_date <=", value, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateIn(List<Date> values) {
            addCriterion("auto_up_date in", values, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateNotIn(List<Date> values) {
            addCriterion("auto_up_date not in", values, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateBetween(Date value1, Date value2) {
            addCriterion("auto_up_date between", value1, value2, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoUpDateNotBetween(Date value1, Date value2) {
            addCriterion("auto_up_date not between", value1, value2, "autoUpDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateIsNull() {
            addCriterion("auto_down_date is null");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateIsNotNull() {
            addCriterion("auto_down_date is not null");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateEqualTo(Date value) {
            addCriterion("auto_down_date =", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateNotEqualTo(Date value) {
            addCriterion("auto_down_date <>", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateGreaterThan(Date value) {
            addCriterion("auto_down_date >", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateGreaterThanOrEqualTo(Date value) {
            addCriterion("auto_down_date >=", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateLessThan(Date value) {
            addCriterion("auto_down_date <", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateLessThanOrEqualTo(Date value) {
            addCriterion("auto_down_date <=", value, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateIn(List<Date> values) {
            addCriterion("auto_down_date in", values, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateNotIn(List<Date> values) {
            addCriterion("auto_down_date not in", values, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateBetween(Date value1, Date value2) {
            addCriterion("auto_down_date between", value1, value2, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andAutoDownDateNotBetween(Date value1, Date value2) {
            addCriterion("auto_down_date not between", value1, value2, "autoDownDate");
            return (Criteria) this;
        }

        public Criteria andWeightsIsNull() {
            addCriterion("weights is null");
            return (Criteria) this;
        }

        public Criteria andWeightsIsNotNull() {
            addCriterion("weights is not null");
            return (Criteria) this;
        }

        public Criteria andWeightsEqualTo(BigDecimal value) {
            addCriterion("weights =", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotEqualTo(BigDecimal value) {
            addCriterion("weights <>", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsGreaterThan(BigDecimal value) {
            addCriterion("weights >", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weights >=", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsLessThan(BigDecimal value) {
            addCriterion("weights <", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weights <=", value, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsIn(List<BigDecimal> values) {
            addCriterion("weights in", values, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotIn(List<BigDecimal> values) {
            addCriterion("weights not in", values, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weights between", value1, value2, "weights");
            return (Criteria) this;
        }

        public Criteria andWeightsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weights not between", value1, value2, "weights");
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