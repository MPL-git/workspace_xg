package com.jf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ActivityGroupExample() {
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

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andSignPicIsNull() {
            addCriterion("sign_pic is null");
            return (Criteria) this;
        }

        public Criteria andSignPicIsNotNull() {
            addCriterion("sign_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSignPicEqualTo(String value) {
            addCriterion("sign_pic =", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicNotEqualTo(String value) {
            addCriterion("sign_pic <>", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicGreaterThan(String value) {
            addCriterion("sign_pic >", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicGreaterThanOrEqualTo(String value) {
            addCriterion("sign_pic >=", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicLessThan(String value) {
            addCriterion("sign_pic <", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicLessThanOrEqualTo(String value) {
            addCriterion("sign_pic <=", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicLike(String value) {
            addCriterion("sign_pic like", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicNotLike(String value) {
            addCriterion("sign_pic not like", value, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicIn(List<String> values) {
            addCriterion("sign_pic in", values, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicNotIn(List<String> values) {
            addCriterion("sign_pic not in", values, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicBetween(String value1, String value2) {
            addCriterion("sign_pic between", value1, value2, "signPic");
            return (Criteria) this;
        }

        public Criteria andSignPicNotBetween(String value1, String value2) {
            addCriterion("sign_pic not between", value1, value2, "signPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicIsNull() {
            addCriterion("product_wk_pic is null");
            return (Criteria) this;
        }

        public Criteria andProductWkPicIsNotNull() {
            addCriterion("product_wk_pic is not null");
            return (Criteria) this;
        }

        public Criteria andProductWkPicEqualTo(String value) {
            addCriterion("product_wk_pic =", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicNotEqualTo(String value) {
            addCriterion("product_wk_pic <>", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicGreaterThan(String value) {
            addCriterion("product_wk_pic >", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicGreaterThanOrEqualTo(String value) {
            addCriterion("product_wk_pic >=", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicLessThan(String value) {
            addCriterion("product_wk_pic <", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicLessThanOrEqualTo(String value) {
            addCriterion("product_wk_pic <=", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicLike(String value) {
            addCriterion("product_wk_pic like", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicNotLike(String value) {
            addCriterion("product_wk_pic not like", value, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicIn(List<String> values) {
            addCriterion("product_wk_pic in", values, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicNotIn(List<String> values) {
            addCriterion("product_wk_pic not in", values, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicBetween(String value1, String value2) {
            addCriterion("product_wk_pic between", value1, value2, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicNotBetween(String value1, String value2) {
            addCriterion("product_wk_pic not between", value1, value2, "productWkPic");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMIsNull() {
            addCriterion("product_wk_pic_m is null");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMIsNotNull() {
            addCriterion("product_wk_pic_m is not null");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMEqualTo(String value) {
            addCriterion("product_wk_pic_m =", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMNotEqualTo(String value) {
            addCriterion("product_wk_pic_m <>", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMGreaterThan(String value) {
            addCriterion("product_wk_pic_m >", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMGreaterThanOrEqualTo(String value) {
            addCriterion("product_wk_pic_m >=", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMLessThan(String value) {
            addCriterion("product_wk_pic_m <", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMLessThanOrEqualTo(String value) {
            addCriterion("product_wk_pic_m <=", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMLike(String value) {
            addCriterion("product_wk_pic_m like", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMNotLike(String value) {
            addCriterion("product_wk_pic_m not like", value, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMIn(List<String> values) {
            addCriterion("product_wk_pic_m in", values, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMNotIn(List<String> values) {
            addCriterion("product_wk_pic_m not in", values, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMBetween(String value1, String value2) {
            addCriterion("product_wk_pic_m between", value1, value2, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andProductWkPicMNotBetween(String value1, String value2) {
            addCriterion("product_wk_pic_m not between", value1, value2, "productWkPicM");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicIsNull() {
            addCriterion("price_wk_pic is null");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicIsNotNull() {
            addCriterion("price_wk_pic is not null");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicEqualTo(String value) {
            addCriterion("price_wk_pic =", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicNotEqualTo(String value) {
            addCriterion("price_wk_pic <>", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicGreaterThan(String value) {
            addCriterion("price_wk_pic >", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicGreaterThanOrEqualTo(String value) {
            addCriterion("price_wk_pic >=", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicLessThan(String value) {
            addCriterion("price_wk_pic <", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicLessThanOrEqualTo(String value) {
            addCriterion("price_wk_pic <=", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicLike(String value) {
            addCriterion("price_wk_pic like", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicNotLike(String value) {
            addCriterion("price_wk_pic not like", value, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicIn(List<String> values) {
            addCriterion("price_wk_pic in", values, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicNotIn(List<String> values) {
            addCriterion("price_wk_pic not in", values, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicBetween(String value1, String value2) {
            addCriterion("price_wk_pic between", value1, value2, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceWkPicNotBetween(String value1, String value2) {
            addCriterion("price_wk_pic not between", value1, value2, "priceWkPic");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorIsNull() {
            addCriterion("price_font_color is null");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorIsNotNull() {
            addCriterion("price_font_color is not null");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorEqualTo(String value) {
            addCriterion("price_font_color =", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorNotEqualTo(String value) {
            addCriterion("price_font_color <>", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorGreaterThan(String value) {
            addCriterion("price_font_color >", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("price_font_color >=", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorLessThan(String value) {
            addCriterion("price_font_color <", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorLessThanOrEqualTo(String value) {
            addCriterion("price_font_color <=", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorLike(String value) {
            addCriterion("price_font_color like", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorNotLike(String value) {
            addCriterion("price_font_color not like", value, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorIn(List<String> values) {
            addCriterion("price_font_color in", values, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorNotIn(List<String> values) {
            addCriterion("price_font_color not in", values, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorBetween(String value1, String value2) {
            addCriterion("price_font_color between", value1, value2, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andPriceFontColorNotBetween(String value1, String value2) {
            addCriterion("price_font_color not between", value1, value2, "priceFontColor");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdIsNull() {
            addCriterion("product_wk_link_id is null");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdIsNotNull() {
            addCriterion("product_wk_link_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdEqualTo(Integer value) {
            addCriterion("product_wk_link_id =", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdNotEqualTo(Integer value) {
            addCriterion("product_wk_link_id <>", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdGreaterThan(Integer value) {
            addCriterion("product_wk_link_id >", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_wk_link_id >=", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdLessThan(Integer value) {
            addCriterion("product_wk_link_id <", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_wk_link_id <=", value, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdIn(List<Integer> values) {
            addCriterion("product_wk_link_id in", values, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdNotIn(List<Integer> values) {
            addCriterion("product_wk_link_id not in", values, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("product_wk_link_id between", value1, value2, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_wk_link_id not between", value1, value2, "productWkLinkId");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionIsNull() {
            addCriterion("product_wk_position is null");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionIsNotNull() {
            addCriterion("product_wk_position is not null");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionEqualTo(String value) {
            addCriterion("product_wk_position =", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionNotEqualTo(String value) {
            addCriterion("product_wk_position <>", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionGreaterThan(String value) {
            addCriterion("product_wk_position >", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionGreaterThanOrEqualTo(String value) {
            addCriterion("product_wk_position >=", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionLessThan(String value) {
            addCriterion("product_wk_position <", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionLessThanOrEqualTo(String value) {
            addCriterion("product_wk_position <=", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionLike(String value) {
            addCriterion("product_wk_position like", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionNotLike(String value) {
            addCriterion("product_wk_position not like", value, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionIn(List<String> values) {
            addCriterion("product_wk_position in", values, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionNotIn(List<String> values) {
            addCriterion("product_wk_position not in", values, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionBetween(String value1, String value2) {
            addCriterion("product_wk_position between", value1, value2, "productWkPosition");
            return (Criteria) this;
        }

        public Criteria andProductWkPositionNotBetween(String value1, String value2) {
            addCriterion("product_wk_position not between", value1, value2, "productWkPosition");
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